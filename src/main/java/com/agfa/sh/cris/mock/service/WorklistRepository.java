package com.agfa.sh.cris.mock.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.agfa.sh.cris.mock.domain.ActiveTask;

public interface WorklistRepository extends Repository<ActiveTask, String> {

	//@Query("select t from ActiveTask t where t.authorDomain = ?1 and t.workitemName = ?2 and t.workitemId > ?3 and rownum <= ?4 order by t.workitemId ")
	@Query("select t from ActiveTask t where t.authorDomain = ?1 and t.workitemName = ?2 and t.workitemId > ?3 ")
	Page<ActiveTask> findRelativeTasks(String domain, String workitemName, Long minWorkitemId, Pageable pageable);
}
