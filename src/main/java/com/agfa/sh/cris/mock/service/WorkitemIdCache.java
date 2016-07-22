package com.agfa.sh.cris.mock.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.agfa.sh.cris.mock.domain.ActiveTask;


public class WorkitemIdCache {
	
	class WorkitemIdCacheRefresher {
		
		private final ExecutorService executors = Executors.newFixedThreadPool(20);
		private final ExecutorService executorsCK = Executors.newFixedThreadPool(10);
		private final ConcurrentHashMap<String, String> keys = new ConcurrentHashMap<String, String>();
		
		void init() {
			
		}
		
		private boolean isDuplicated(String key) {
			if (keys.get(key) == null) {
				String ret = keys.putIfAbsent(key, key);
				if (ret == null) {
					return false;
				}
			}
			return true;

		}
		
		void notifiy(final String workitemName, final long minWorkitemId, final WorkitemIdCacheRefreshListener listener) {
			if (isDuplicated(workitemName)) {
				if (logger.isDebugEnabled()) {
					logger.debug("Workitemid refreshing job of [ "+workitemName+" ] is running, duplicated notification ignored. ");
				}
				return ;
			}
			
			executors.submit(new Runnable() {
				@Override
				public void run() {
					keys.put(workitemName, workitemName);
					try {
						if (logger.isInfoEnabled()) {
							logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");							
							logger.info("authordomain => [ "+DEFAULT_DOMAIN +" ], taskinstancename => [ "+workitemName + " ], taskinstanceid => [ "+minWorkitemId +" ], rownum => [ "+DEFAULT_MAX_RESULTS+" ]");
						}
						Sort sort =  new Sort(Direction.ASC, "workitemId");
						Pageable pageable = new PageRequest(0, DEFAULT_MAX_RESULTS, sort);
						Page<ActiveTask> tasks = worklistRepository.findRelativeTasks(DEFAULT_DOMAIN, workitemName, minWorkitemId, pageable);
						List<Long> workitemIds = new ArrayList<Long>();
						for(ActiveTask task : tasks) {
							workitemIds.add(task.getWorkitemId());
						}
						
						if (workitemIds.isEmpty()) {
							listener.onError(workitemName, "data not found", null);
						} else {
							listener.onCompleted(workitemName, workitemIds);
						}
						

					} catch(Exception e) {
						listener.onError(workitemName, e.getMessage(), e);
					} finally {
						executorsCK.submit(new Runnable() {
							@Override
							public void run() {
								try {
									Thread.sleep(2000);
								} catch (InterruptedException e) {								
								} finally {
									keys.remove(workitemName);
								}								
							}
						});	
						if (logger.isInfoEnabled()) {
							logger.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
						}
					}
				}				
			});
		}
		
		void destory() {
			try {
				executors.shutdown();
			} catch(Exception e) {				
			}
			try {
				executorsCK.shutdown();
			} catch(Exception e) {				
			}
		}
	}
	
	interface WorkitemIdCacheRefreshListener {
		void onCompleted(String key, List<Long> workitemIds);
		void onError(String key, String errorMsg, Exception rootCause);
	}
	
	private final static String DEFAULT_DOMAIN = "radiology-domain";
	private final static int DEFAULT_MAX_RESULTS = 200;
	private final static int DEFAULT_QUEUE_CAPACITY = DEFAULT_MAX_RESULTS + 2;
	private final static Logger logger = LoggerFactory.getLogger(WorkitemIdCache.class);
	
	private final ConcurrentHashMap<String, BlockingQueue<Long>> containers = new ConcurrentHashMap<String, BlockingQueue<Long>>();
	private final ConcurrentHashMap<String, Long> maxWorkitemIds = new ConcurrentHashMap<String, Long>();
	private final ConcurrentHashMap<String, Integer> prevResultCounts = new ConcurrentHashMap<String, Integer>();
	
	private final WorkitemIdCacheRefresher cacheRefresher = new WorkitemIdCacheRefresher();
	
	@Autowired
	private WorklistRepository worklistRepository;
	
	public Long getNextValue(final String workitemName) {
		BlockingQueue<Long> queue = containers.get(workitemName);
		if (queue == null) {
			queue = new ArrayBlockingQueue<Long>(DEFAULT_QUEUE_CAPACITY * 2);
			BlockingQueue<Long> oldQueue = containers.putIfAbsent(workitemName, queue);
			if (oldQueue != null) queue = oldQueue;
 		}
		
		Integer prevCount = prevResultCounts.get(workitemName);
		if (prevCount == null) prevCount = DEFAULT_QUEUE_CAPACITY;
		int currentSize = queue.size();
		if (logger.isDebugEnabled()){
			logger.debug("Current Size => "+currentSize+", previous size => "+prevCount);
		}
		
		if (currentSize <= prevCount/2) {
			Long minWorkitemId = maxWorkitemIds.get(workitemName);
			if (minWorkitemId == null) minWorkitemId = -1L;
			final BlockingQueue<Long> q = queue;

			cacheRefresher.notifiy(workitemName, minWorkitemId, new WorkitemIdCacheRefreshListener() {
				@Override
				public void onCompleted(String key, List<Long> workitemIds) {
					int workitemCount = workitemIds.size();
					maxWorkitemIds.put(workitemName, workitemIds.get(workitemCount-1));
					prevResultCounts.put(workitemName, workitemCount);
					q.addAll(workitemIds);
					if (logger.isInfoEnabled()) {
						logger.info("Succeeded to retrieve "+workitemCount+" workitem ids of [ "+key+" ]");
					}
				}

				@Override
				public void onError(String key, String errorMsg, Exception rootCause) {
					logger.warn("Failed to retrieve workitem ids of [ "+key+" ], caused by "+errorMsg);					
				}
			});
		}
		
		Long result = -1L;
		try {
			result = queue.poll(10L, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (result == null) result = -1L;
		return result;
	}
	
	public void init() {
		if (logger.isInfoEnabled()) {
			logger.info("============================= bean initializing ==============================");
		}
		cacheRefresher.init();
	}
	
	public void destroy() {
		if (logger.isInfoEnabled()) {
			logger.info("============================== bean destroying ===============================");
		}
		cacheRefresher.destory();
	}
}
