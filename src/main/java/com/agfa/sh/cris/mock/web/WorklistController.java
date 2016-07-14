package com.agfa.sh.cris.mock.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.agfa.sh.cris.mock.service.WorkitemIdService;

@RestController
public class WorklistController {
	
	@Autowired
	private WorkitemIdService workitemIdService;

	@RequestMapping("/workitem/register")
	@Transactional(readOnly=true)
	public String toRegister() {
		return String.valueOf(workitemIdService.getNextToRegister());
	}
	
	@RequestMapping("/workitem/checkin")
	@Transactional(readOnly=true)
	public String toCheckin() {
		return String.valueOf(workitemIdService.getNextToCheckin());
	}
	
	@RequestMapping("/workitem/injection")
	@Transactional(readOnly=true)
	public String toInjection() {
		return String.valueOf(workitemIdService.getNextToInjection());
	}
	
	@RequestMapping("/workitem/examination")
	@Transactional(readOnly=true)
	public String toExamination() {
		return String.valueOf(workitemIdService.getNextToExamination());
	}
	
	@RequestMapping("/workitem/reconstruct")
	@Transactional(readOnly=true)
	public String toReconstruct() {
		return String.valueOf(workitemIdService.getNextToReconstruct());
	}
	
	@RequestMapping("/workitem/report")
	@Transactional(readOnly=true)
	public String toReport() {
		return String.valueOf(workitemIdService.getNextToReport());
	}
	
	@RequestMapping("/workitem/verification")
	@Transactional(readOnly=true)
	public String toVerification() {
		return String.valueOf(workitemIdService.getNextToVerification());
	}
	
	@RequestMapping("/workitem/adv-verification")
	@Transactional(readOnly=true)
	public String toAdvancedVerification() {
		return String.valueOf(workitemIdService.getNextToAdvancedVerification());
	}
	
	@RequestMapping("/workitem/pause-report")
	@Transactional(readOnly=true)
	public String toPauseReport() {
		return String.valueOf(workitemIdService.getNextToPauseReport());
	}
	
	@RequestMapping("/workitem/print")
	@Transactional(readOnly=true)
	public String toPrint() {
		return String.valueOf(workitemIdService.getNextToPrint());
	}
	
	@RequestMapping("/workitem/delivery")
	@Transactional(readOnly=true)
	public String toDelivery() {
		return String.valueOf(workitemIdService.getNextToDelivery());
	}
	
	@RequestMapping("/workitem/print-delivery")
	@Transactional(readOnly=true)
	public String toPrintDelivery() {
		return String.valueOf(workitemIdService.getNextToPrintDelivery());
	}
}
