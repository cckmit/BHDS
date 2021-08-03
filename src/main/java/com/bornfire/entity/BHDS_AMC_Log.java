package com.bornfire.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "BHDS_PRJ_PROJ_AMC")

public class BHDS_AMC_Log {
	
	@Id
	private String amc_srl;
	private String proj_id;
	private String client_id;
	private BigDecimal amc_period;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date amc_contract_date;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date amc_start_date;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date amc_end_date;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date amc_next_due_dte;
	
	
	private String amc_status;
	private String spoc_person;
	private String remarks;
	private String entry_user;
	private String modify_user;
	private String auth_user;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date entry_time;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date modify_time;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date auth_time;
	
	
	private String del_flag;
	private String entity_flag;
	private String modify_flag;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date present_date;
	
	
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

	public String getAmc_srl() {
		return amc_srl;
	}

	public void setAmc_srl(String amc_srl) {
		this.amc_srl = amc_srl;
	}

	public String getProj_id() {
		return proj_id;
	}

	public void setProj_id(String proj_id) {
		this.proj_id = proj_id;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public BigDecimal getAmc_period() {
		return amc_period;
	}

	public void setAmc_period(BigDecimal amc_period) {
		this.amc_period = amc_period;
	}

	public Date getAmc_contract_date() {
		return amc_contract_date;
	}

	public void setAmc_contract_date(Date amc_contract_date) {
		this.amc_contract_date = amc_contract_date;
	}

	public Date getAmc_start_date() {
		return amc_start_date;
	}

	public void setAmc_start_date(Date amc_start_date) {
		this.amc_start_date = amc_start_date;
	}

	public Date getAmc_end_date() {
		return amc_end_date;
	}

	public void setAmc_end_date(Date amc_end_date) {
		this.amc_end_date = amc_end_date;
	}

	public Date getAmc_next_due_date() {
		return amc_next_due_dte;
	}

	public void setAmc_next_due_date(Date amc_next_due_date) {
		this.amc_next_due_dte = amc_next_due_date;
	}

	public String getAmc_status() {
		return amc_status;
	}

	public void setAmc_status(String amc_status) {
		this.amc_status = amc_status;
	}

	public String getSpoc_person() {
		return spoc_person;
	}

	public void setSpoc_person(String spoc_person) {
		this.spoc_person = spoc_person;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getEntry_user() {
		return entry_user;
	}

	public void setEntry_user(String entry_user) {
		this.entry_user = entry_user;
	}

	public String getModify_user() {
		return modify_user;
	}

	public void setModify_user(String modify_user) {
		this.modify_user = modify_user;
	}

	public String getAuth_user() {
		return auth_user;
	}

	public void setAuth_user(String auth_user) {
		this.auth_user = auth_user;
	}

	public Date getEntry_time() {
		return entry_time;
	}

	public void setEntry_time(Date entry_time) {
		this.entry_time = entry_time;
	}

	public Date getModify_time() {
		return modify_time;
	}

	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}

	public Date getAuth_time() {
		return auth_time;
	}

	public void setAuth_time(Date auth_time) {
		this.auth_time = auth_time;
	}

	public String getDel_flag() {
		return del_flag;
	}

	public void setDel_flag(String del_flag) {
		this.del_flag = del_flag;
	}

	public String getEntity_flag() {
		return entity_flag;
	}

	public void setEntity_flag(String entity_flag) {
		this.entity_flag = entity_flag;
	}

	public String getModify_flag() {
		return modify_flag;
	}

	public void setModify_flag(String modify_flag) {
		this.modify_flag = modify_flag;
	}

	public Date getPresent_date() {
		return present_date;
	}

	public void setPresent_date(Date present_date) {
		this.present_date = present_date;
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

	public BHDS_AMC_Log(String amc_srl, String proj_id, String client_id, BigDecimal amc_period, Date amc_contract_date,
			Date amc_start_date, Date amc_end_date, Date amc_next_due_dte, String amc_status, String spoc_person,
			String remarks, String entry_user, String modify_user, String auth_user, Date entry_time, Date modify_time,
			Date auth_time, String del_flag, String entity_flag, String modify_flag, Date present_date,
			String lodged_by, String module, String issue_details, String priority, String observations,
			String resolution, String fixes, Date date_of_fix, String status) {
		super();
		this.amc_srl = amc_srl;
		this.proj_id = proj_id;
		this.client_id = client_id;
		this.amc_period = amc_period;
		this.amc_contract_date = amc_contract_date;
		this.amc_start_date = amc_start_date;
		this.amc_end_date = amc_end_date;
		this.amc_next_due_dte = amc_next_due_dte;
		this.amc_status = amc_status;
		this.spoc_person = spoc_person;
		this.remarks = remarks;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.auth_user = auth_user;
		this.entry_time = entry_time;
		this.modify_time = modify_time;
		this.auth_time = auth_time;
		this.del_flag = del_flag;
		this.entity_flag = entity_flag;
		this.modify_flag = modify_flag;
		this.present_date = present_date;
		this.lodged_by = lodged_by;
		this.module = module;
		this.issue_details = issue_details;
		this.priority = priority;
		this.observations = observations;
		this.resolution = resolution;
		this.fixes = fixes;
		this.date_of_fix = date_of_fix;
		this.status = status;
	}

	public BHDS_AMC_Log() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	


	
}
 