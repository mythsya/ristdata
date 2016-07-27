package com.agfa.sh.cris.mock.service;

import com.agfa.sh.cris.mock.domain.ActiveTask;

public interface WorkitemIdService {

	ActiveTask getNextToRegister();
	
	ActiveTask getNextToCheckin();
	
	ActiveTask getNextToInjection();
	
	ActiveTask getNextToExamination();
	
	ActiveTask getNextToReconstruct();
	
	ActiveTask getNextToReport();
	
	ActiveTask getNextToVerification();
	
	ActiveTask getNextToAdvancedVerification();
	
	ActiveTask getNextToPauseReport();
	
	ActiveTask getNextToPrint();
	
	ActiveTask getNextToDelivery();
	
	ActiveTask getNextToPrintDelivery();
}
