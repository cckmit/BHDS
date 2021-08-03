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
@Table(name = "BHDS_PROJ_MASTER")

public class BHDS_Project_Master_Log {
	
	@Id
	private String srl_no;
	private String group1;
	private String team;
	private String proj_id;
	private String proj_name;
	private String proj_type;
	private BigDecimal period;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date start_date;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date end_date;
	
	private String proj_ref;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date proposal_date;
	
	
	private String proposal_ref;
	private String remarks;
	private String client_id;
	private String client_name;
	private String client_type;
	private String po_ref;
	private String sow_det;
	private String client_remarks;
	private String address;
	private String country;
	private String spoc1_name;
	private String spoc1_design;
	private String spoc1_mobile;
	private String spoc1_email;
	private String spoc2_name;
	private String spoc2_design;
	private String spoc2_mobile;
	private String spoc2_email;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date warranty_period;
	

	
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date amc_due_date;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date amc_period_from;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date amc_end_date;
	
	private String amc_status;
	private String entry_user;
	private String modify_user;
	private String auth_user;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date auth_time;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date entry_time;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date modify_time;
	
	
	private String delete_flag;
	private String modify_flag;
	private String entity_flag;
	
	
	
	public String getGroup1() {
		return group1;
	}

	public void setGroup1(String group1) {
		this.group1 = group1;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getProj_id() {
		return proj_id;
	}

	public void setProj_id(String proj_id) {
		this.proj_id = proj_id;
	}

	public String getProj_name() {
		return proj_name;
	}

	public void setProj_name(String proj_name) {
		this.proj_name = proj_name;
	}

	public String getProj_type() {
		return proj_type;
	}

	public void setProj_type(String proj_type) {
		this.proj_type = proj_type;
	}

	public BigDecimal getPeriod() {
		return period;
	}

	public void setPeriod(BigDecimal period) {
		this.period = period;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getWarranty_period() {
		return warranty_period;
	}
	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public String getProj_ref() {
		return proj_ref;
	}

	public void setProj_ref(String proj_ref) {
		this.proj_ref = proj_ref;
	}

	public Date getProposal_date() {
		return proposal_date;
	}

	public void setProposal_date(Date proposal_date) {
		this.proposal_date = proposal_date;
	}

	public String getProposal_ref() {
		return proposal_ref;
	}

	public void setProposal_ref(String proposal_ref) {
		this.proposal_ref = proposal_ref;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public String getClient_type() {
		return client_type;
	}

	public void setClient_type(String client_type) {
		this.client_type = client_type;
	}

	public String getPo_ref() {
		return po_ref;
	}

	public void setPo_ref(String po_ref) {
		this.po_ref = po_ref;
	}

	public String getSow_det() {
		return sow_det;
	}

	public void setSow_det(String sow_det) {
		this.sow_det = sow_det;
	}

	public String getClient_remarks() {
		return client_remarks;
	}

	public void setClient_remarks(String client_remarks) {
		this.client_remarks = client_remarks;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getSpoc1_name() {
		return spoc1_name;
	}

	public void setSpoc1_name(String spoc1_name) {
		this.spoc1_name = spoc1_name;
	}

	public String getSpoc1_design() {
		return spoc1_design;
	}

	public void setSpoc1_design(String spoc1_design) {
		this.spoc1_design = spoc1_design;
	}

	public String getSpoc1_mobile() {
		return spoc1_mobile;
	}

	public void setSpoc1_mobile(String spoc1_mobile) {
		this.spoc1_mobile = spoc1_mobile;
	}

	public String getSpoc1_email() {
		return spoc1_email;
	}
	public void setWarranty_period(Date warranty_period) {
		this.warranty_period = warranty_period;
	}


	public void setSpoc1_email(String spoc1_email) {
		this.spoc1_email = spoc1_email;
	}

	public String getSpoc2_name() {
		return spoc2_name;
	}

	public void setSpoc2_name(String spoc2_name) {
		this.spoc2_name = spoc2_name;
	}

	public String getSpoc2_design() {
		return spoc2_design;
	}

	public void setSpoc2_design(String spoc2_design) {
		this.spoc2_design = spoc2_design;
	}

	public String getSpoc2_mobile() {
		return spoc2_mobile;
	}

	public void setSpoc2_mobile(String spoc2_mobile) {
		this.spoc2_mobile = spoc2_mobile;
	}

	public String getSpoc2_email() {
		return spoc2_email;
	}

	public void setSpoc2_email(String spoc2_email) {
		this.spoc2_email = spoc2_email;
	}



	public Date getAmc_due_date() {
		return amc_due_date;
	}

	public void setAmc_due_date(Date amc_due_date) {
		this.amc_due_date = amc_due_date;
	}

	public Date getAmc_period_from() {
		return amc_period_from;
	}

	public void setAmc_period_from(Date amc_period_from) {
		this.amc_period_from = amc_period_from;
	}

	public Date getAmc_end_date() {
		return amc_end_date;
	}

	public void setAmc_end_date(Date amc_end_date) {
		this.amc_end_date = amc_end_date;
	}

	public String getAmc_status() {
		return amc_status;
	}

	public void setAmc_status(String amc_status) {
		this.amc_status = amc_status;
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

	public Date getAuth_time() {
		return auth_time;
	}

	public void setAuth_time(Date auth_time) {
		this.auth_time = auth_time;
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

	public String getDelete_flag() {
		return delete_flag;
	}

	public void setDelete_flag(String delete_flag) {
		this.delete_flag = delete_flag;
	}

	public String getModify_flag() {
		return modify_flag;
	}

	public void setModify_flag(String modify_flag) {
		this.modify_flag = modify_flag;
	}

	public String getEntity_flag() {
		return entity_flag;
	}

	public void setEntity_flag(String entity_flag) {
		this.entity_flag = entity_flag;
	}

	

	public String getSrl_no() {
		return srl_no;
	}

	public void setSrl_no(String srl_no) {
		this.srl_no = srl_no;
	}

	
	public BHDS_Project_Master_Log(String srl_no, String group1, String team, String proj_id, String proj_name,
			String proj_type, BigDecimal period, Date start_date, Date end_date, String proj_ref, Date proposal_date,
			String proposal_ref, String remarks, String client_id, String client_name, String client_type,
			String po_ref, String sow_det, String client_remarks, String address, String country, String spoc1_name,
			String spoc1_design, String spoc1_mobile, String spoc1_email, String spoc2_name, String spoc2_design,
			String spoc2_mobile, String spoc2_email, Date warranty_period, Date amc_due_date, Date amc_period_from,
			Date amc_end_date, String amc_status, String entry_user, String modify_user, String auth_user,
			Date auth_time, Date entry_time, Date modify_time, String delete_flag, String modify_flag,
			String entity_flag) {
		super();
		this.srl_no = srl_no;
		this.group1 = group1;
		this.team = team;
		this.proj_id = proj_id;
		this.proj_name = proj_name;
		this.proj_type = proj_type;
		this.period = period;
		this.start_date = start_date;
		this.end_date = end_date;
		this.proj_ref = proj_ref;
		this.proposal_date = proposal_date;
		this.proposal_ref = proposal_ref;
		this.remarks = remarks;
		this.client_id = client_id;
		this.client_name = client_name;
		this.client_type = client_type;
		this.po_ref = po_ref;
		this.sow_det = sow_det;
		this.client_remarks = client_remarks;
		this.address = address;
		this.country = country;
		this.spoc1_name = spoc1_name;
		this.spoc1_design = spoc1_design;
		this.spoc1_mobile = spoc1_mobile;
		this.spoc1_email = spoc1_email;
		this.spoc2_name = spoc2_name;
		this.spoc2_design = spoc2_design;
		this.spoc2_mobile = spoc2_mobile;
		this.spoc2_email = spoc2_email;
		this.warranty_period = warranty_period;
		this.amc_due_date = amc_due_date;
		this.amc_period_from = amc_period_from;
		this.amc_end_date = amc_end_date;
		this.amc_status = amc_status;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.auth_user = auth_user;
		this.auth_time = auth_time;
		this.entry_time = entry_time;
		this.modify_time = modify_time;
		this.delete_flag = delete_flag;
		this.modify_flag = modify_flag;
		this.entity_flag = entity_flag;
	}

	public BHDS_Project_Master_Log() {
		super();
		// TODO Auto-generated constructor stub
	}

}
