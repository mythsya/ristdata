package com.agfa.sh.cris.mock.service;

public interface WorkitemIdService {

	long getNextToRegister();
	
	long getNextToCheckin();
	
	long getNextToInjection();
	
	long getNextToExamination();
	
	long getNextToReconstruct();
	
	long getNextToReport();
	
	long getNextToVerification();
	
	long getNextToAdvancedVerification();
	
	long getNextToPauseReport();
	
	long getNextToPrint();
	
	long getNextToDelivery();
	
	long getNextToPrintDelivery();
}
