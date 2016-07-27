package com.agfa.sh.cris.mock.web;

import com.agfa.sh.cris.mock.domain.ActiveTask;
import com.fasterxml.jackson.annotation.JsonView;

public class WorkitemJson {

	public static interface WithJsonView{}
	
	@JsonView(WithJsonView.class)
	public String workitemId;
	
	@JsonView(WithJsonView.class)
	public String actId;
	
	@JsonView(WithJsonView.class)
	public String accessionNumber;
	
	@JsonView(WithJsonView.class)
	public String patientId;
	
	public WorkitemJson() {}
	
	public WorkitemJson(ActiveTask t) {
		if (t == null) {
			this.workitemId = "";
			this.patientId = "";
			this.actId = "";
			this.accessionNumber = "";
		} else {
			this.workitemId = t.getWorkitemId()+"";
			this.patientId = t.getPatientId();
			this.actId = t.getActId();
			this.accessionNumber = t.getAccessionNumber();
		}
	}
}
