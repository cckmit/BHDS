package com.bornfire.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "TROUBLE_SHOOTING_REGISTER")

public class Trouble_shooting_Reg {
	
	@Id
	private String srl_no;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date dte;
	private String lodged_by;
	private String module;
	private String issue_details;
	private String priority;
	private String observations;
	private String resolution;
	private String fixes;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date date_of_fix;
	private String status;
	private String project_name;
	private String client;
	public String getSrl_no() {
		return srl_no;
	}
	public void setSrl_no(String srl_no) {
		this.srl_no = srl_no;
	}
	public Date getDte() {
		return dte;
	}
	public void setDte(Date dte) {
		this.dte = dte;
	}
	public String getLodged_by() {
		return lodged_by;
	}
	public void setLodged_by(String lodged_by) {
		this.lodged_by = lodged_by;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getIssue_details() {
		return issue_details;
	}
	public void setIssue_details(String issue_details) {
		this.issue_details = issue_details;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getObservations() {
		return observations;
	}
	public void setObservations(String observations) {
		this.observations = observations;
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public String getFixes() {
		return fixes;
	}
	public void setFixes(String fixes) {
		this.fixes = fixes;
	}
	public Date getDate_of_fix() {
		return date_of_fix;
	}
	public void setDate_of_fix(Date date_of_fix) {
		this.date_of_fix = date_of_fix;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public Trouble_shooting_Reg(String srl_no, Date dte, String lodged_by, String module, String issue_details,
			String priority, String observations, String resolution, String fixes, Date date_of_fix, String status,
			String project_name, String client) {
		super();
		this.srl_no = srl_no;
		this.dte = dte;
		this.lodged_by = lodged_by;
		this.module = module;
		this.issue_details = issue_details;
		this.priority = priority;
		this.observations = observations;
		this.resolution = resolution;
		this.fixes = fixes;
		this.date_of_fix = date_of_fix;
		this.status = status;
		this.project_name = project_name;
		this.client = client;
	}
	public Trouble_shooting_Reg() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
