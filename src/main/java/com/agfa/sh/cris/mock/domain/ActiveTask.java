package com.agfa.sh.cris.mock.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="active_task")
public class ActiveTask implements Serializable{

	private static final long serialVersionUID = 5076079144850810352L;

	@Id
	@Column(name = "id", length = 32)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	
	@Column(name = "authordomain")
	private String authorDomain;
	
	@Column(name = "actid")
	private String actId;
	
	@Column(name="modalitytypebizid")
	private String modalityTypeCode;
	
	@Column(name = "observationprimaryid")
	private String accessionNumber;
	
	@Column(name="patientclassbizid")
	private String patientClassCode;
	
	@Column(name="patientsexcode")
	private String patientSexCode;
	
	@Column(name = "patientprimaryid")
	private String patientId;
	
	@Column(name = "taskinstanceid")
	private Long workitemId;
	
	@Column(name = "taskinstancename")
	private String workitemName;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthorDomain() {
		return authorDomain;
	}

	public void setAuthorDomain(String authorDomain) {
		this.authorDomain = authorDomain;
	}

	public String getActId() {
		return actId;
	}

	public void setActId(String actId) {
		this.actId = actId;
	}

	public String getModalityTypeCode() {
		return modalityTypeCode;
	}

	public void setModalityTypeCode(String modalityTypeCode) {
		this.modalityTypeCode = modalityTypeCode;
	}

	public String getAccessionNumber() {
		return accessionNumber;
	}

	public void setAccessionNumber(String accessionNumber) {
		this.accessionNumber = accessionNumber;
	}

	public String getPatientClassCode() {
		return patientClassCode;
	}

	public void setPatientClassCode(String patientClassCode) {
		this.patientClassCode = patientClassCode;
	}

	public String getPatientSexCode() {
		return patientSexCode;
	}

	public void setPatientSexCode(String patientSexCode) {
		this.patientSexCode = patientSexCode;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public Long getWorkitemId() {
		return workitemId;
	}

	public void setWorkitemId(Long workitemId) {
		this.workitemId = workitemId;
	}

	public String getWorkitemName() {
		return workitemName;
	}

	public void setWorkitemName(String workitemName) {
		this.workitemName = workitemName;
	}


	
}
