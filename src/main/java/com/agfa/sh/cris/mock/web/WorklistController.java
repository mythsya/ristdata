package com.agfa.sh.cris.mock.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agfa.sh.cris.mock.service.WorkitemIdService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class WorklistController {
	
	@Autowired
	private WorkitemIdService workitemIdService;

	@RequestMapping("/workitem/register")
	@Transactional(readOnly=true)
	@JsonView(WorkitemJson.WithJsonView.class)
	public WorkitemJson toRegister() {
		return new WorkitemJson(workitemIdService.getNextToRegister());
	}
	
	@RequestMapping("/workitem/checkin")
	@Transactional(readOnly=true)
	@JsonView(WorkitemJson.WithJsonView.class)
	public WorkitemJson toCheckin() {
		return new WorkitemJson(workitemIdService.getNextToCheckin());
	}
	
	@RequestMapping("/workitem/injection")
	@Transactional(readOnly=true)
	@JsonView(WorkitemJson.WithJsonView.class)
	public WorkitemJson toInjection() {
		return new WorkitemJson(workitemIdService.getNextToInjection());
	}
	
	@RequestMapping("/workitem/examination")
	@Transactional(readOnly=true)
	@JsonView(WorkitemJson.WithJsonView.class)
	public WorkitemJson toExamination() {
		return new WorkitemJson(workitemIdService.getNextToExamination());
	}
	
	@RequestMapping("/workitem/reconstruct")
	@Transactional(readOnly=true)
	@JsonView(WorkitemJson.WithJsonView.class)
	public WorkitemJson toReconstruct() {
		return new WorkitemJson(workitemIdService.getNextToReconstruct());
	}
	
	@RequestMapping("/workitem/report")
	@Transactional(readOnly=true)
	@JsonView(WorkitemJson.WithJsonView.class)
	public WorkitemJson toReport() {
		return new WorkitemJson(workitemIdService.getNextToReport());
	}
	
	@RequestMapping("/workitem/verification")
	@Transactional(readOnly=true)
	@JsonView(WorkitemJson.WithJsonView.class)
	public WorkitemJson toVerification() {
		return new WorkitemJson(workitemIdService.getNextToVerification());
	}
	
	@RequestMapping("/workitem/adv-verification")
	@Transactional(readOnly=true)
	@JsonView(WorkitemJson.WithJsonView.class)
	public WorkitemJson toAdvancedVerification() {
		return new WorkitemJson(workitemIdService.getNextToAdvancedVerification());
	}
	
	@RequestMapping("/workitem/pause-report")
	@Transactional(readOnly=true)
	@JsonView(WorkitemJson.WithJsonView.class)
	public WorkitemJson toPauseReport() {
		return new WorkitemJson(workitemIdService.getNextToPauseReport());
	}
	
	@RequestMapping("/workitem/print")
	@Transactional(readOnly=true)
	@JsonView(WorkitemJson.WithJsonView.class)
	public WorkitemJson toPrint() {
		return new WorkitemJson(workitemIdService.getNextToPrint());
	}
	
	@RequestMapping("/workitem/delivery")
	@Transactional(readOnly=true)
	@JsonView(WorkitemJson.WithJsonView.class)
	public WorkitemJson toDelivery() {
		return new WorkitemJson(workitemIdService.getNextToDelivery());
	}
	
	@RequestMapping("/workitem/print-delivery")
	@Transactional(readOnly=true)
	@JsonView(WorkitemJson.WithJsonView.class)
	public WorkitemJson toPrintDelivery() {
		return new WorkitemJson(workitemIdService.getNextToPrintDelivery());
	}
}
