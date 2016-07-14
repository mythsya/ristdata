package com.agfa.sh.cris.mock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("workitemIdService")
@Transactional
public class WorkitemIdServiceImpl implements WorkitemIdService {
	
	@Autowired
	@Qualifier("workitemIdCache")
	private WorkitemIdCache workitemIdCache;

	@Override
	public long getNextToRegister() {
		return workitemIdCache.getNextValue("ris.register");
	}

	@Override
	public long getNextToCheckin() {
		return workitemIdCache.getNextValue("ris.checkin");
	}

	@Override
	public long getNextToInjection() {
		return workitemIdCache.getNextValue("ris.injection");
	}

	@Override
	public long getNextToExamination() {
		return workitemIdCache.getNextValue("ris.examination");
	}

	@Override
	public long getNextToReconstruct() {
		return workitemIdCache.getNextValue("ris.reconstructing");
	}

	@Override
	public long getNextToReport() {
		return workitemIdCache.getNextValue("ris.reporting.interpretation");
	}

	@Override
	public long getNextToVerification() {
		return workitemIdCache.getNextValue("ris.reporting.verification");
	}

	@Override
	public long getNextToAdvancedVerification() {
		return workitemIdCache.getNextValue("ris.reporting.verification.advanced");
	}

	@Override
	public long getNextToPauseReport() {
		return workitemIdCache.getNextValue("ris.reporting.renew");
	}

	@Override
	public long getNextToPrint() {
		return workitemIdCache.getNextValue("ris.reporting.printing");
	}

	@Override
	public long getNextToDelivery() {
		return workitemIdCache.getNextValue("ris.distribution");
	}

	@Override
	public long getNextToPrintDelivery() {
		return workitemIdCache.getNextValue("ris.distribution-print");
	}

}
