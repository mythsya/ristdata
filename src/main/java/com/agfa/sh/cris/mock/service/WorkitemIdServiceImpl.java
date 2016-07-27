package com.agfa.sh.cris.mock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.agfa.sh.cris.mock.domain.ActiveTask;

@Component("workitemIdService")
@Transactional
public class WorkitemIdServiceImpl implements WorkitemIdService {
	
	@Autowired
	@Qualifier("workitemIdCache")
	private WorkitemIdCache workitemIdCache;

	@Override
	public ActiveTask getNextToRegister() {
		return workitemIdCache.getNextValue("ris.register");
	}

	@Override
	public ActiveTask getNextToCheckin() {
		return workitemIdCache.getNextValue("ris.checkin");
	}

	@Override
	public ActiveTask getNextToInjection() {
		return workitemIdCache.getNextValue("ris.injection");
	}

	@Override
	public ActiveTask getNextToExamination() {
		return workitemIdCache.getNextValue("ris.examination");
	}

	@Override
	public ActiveTask getNextToReconstruct() {
		return workitemIdCache.getNextValue("ris.reconstructing");
	}

	@Override
	public ActiveTask getNextToReport() {
		return workitemIdCache.getNextValue("ris.reporting.interpretation");
	}

	@Override
	public ActiveTask getNextToVerification() {
		return workitemIdCache.getNextValue("ris.reporting.verification");
	}

	@Override
	public ActiveTask getNextToAdvancedVerification() {
		return workitemIdCache.getNextValue("ris.reporting.verification.advanced");
	}

	@Override
	public ActiveTask getNextToPauseReport() {
		return workitemIdCache.getNextValue("ris.reporting.renew");
	}

	@Override
	public ActiveTask getNextToPrint() {
		return workitemIdCache.getNextValue("ris.reporting.printing");
	}

	@Override
	public ActiveTask getNextToDelivery() {
		return workitemIdCache.getNextValue("ris.distribution");
	}

	@Override
	public ActiveTask getNextToPrintDelivery() {
		return workitemIdCache.getNextValue("ris.distribution-print");
	}

}
