package com.bornfire.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "BORNFIRE_ISSUE_LOG_REGISTER")
public class BHDSIssueMaster implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String issue_srl_no;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	
	private Date rpt_date;
	private String client;
	private String lodged_by;

	private String application;
	private String issue_type;
	private String report_code;
	private String issue_description;
	private String priority;
	private BigDecimal age;
	private String assigned_to;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date assigned_date;
	private String current_status;
	private String entity_flg;
	private String del_flg;
	private String modify_flg;
	private String entry_user;
	private Date entry_time;
	private String modify_user;
	private Date modify_time;
	private String verify_user;
	private Date verify_time;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getIssue_srl_no() {
		return issue_srl_no;
	}

	public Date getRpt_date() {
		return rpt_date;
	}

	public String getClient() {
		return client;
	}

	public String getLodged_by() {
		return lodged_by;
	}

	public String getApplication() {
		return application;
	}

	public String getIssue_type() {
		return issue_type;
	}

	public String getReport_code() {
		return report_code;
	}

	public String getIssue_description() {
		return issue_description;
	}

	public String getPriority() {
		return priority;
	}

	public BigDecimal getAge() {
		return age;
	}

	public String getAssigned_to() {
		return assigned_to;
	}

	public Date getAssigned_date() {
		return assigned_date;
	}

	public String getCurrent_status() {
		return current_status;
	}

	public String getEntity_flg() {
		return entity_flg;
	}

	public String getDel_flg() {
		return del_flg;
	}

	public String getModify_flg() {
		return modify_flg;
	}

	public String getEntry_user() {
		return entry_user;
	}

	public Date getEntry_time() {
		return entry_time;
	}

	public String getModify_user() {
		return modify_user;
	}

	public Date getModify_time() {
		return modify_time;
	}

	public String getVerify_user() {
		return verify_user;
	}

	public Date getVerify_time() {
		return verify_time;
	}

	public void setIssue_srl_no(String issue_srl_no) {
		this.issue_srl_no = issue_srl_no;
	}

	public void setRpt_date(Date rpt_date) {
		this.rpt_date = rpt_date;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public void setLodged_by(String lodged_by) {
		this.lodged_by = lodged_by;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public void setIssue_type(String issue_type) {
		this.issue_type = issue_type;
	}

	public void setReport_code(String report_code) {
		this.report_code = report_code;
	}

	public void setIssue_description(String issue_description) {
		this.issue_description = issue_description;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public void setAge(BigDecimal age) {
		this.age = age;
	}

	public void setAssigned_to(String assigned_to) {
		this.assigned_to = assigned_to;
	}

	public void setAssigned_date(Date assigned_date) {
		this.assigned_date = assigned_date;
	}

	public void setCurrent_status(String current_status) {
		this.current_status = current_status;
	}

	public void setEntity_flg(String entity_flg) {
		this.entity_flg = entity_flg;
	}

	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
	}

	public void setModify_flg(String modify_flg) {
		this.modify_flg = modify_flg;
	}

	public void setEntry_user(String entry_user) {
		this.entry_user = entry_user;
	}

	public void setEntry_time(Date entry_time) {
		this.entry_time = entry_time;
	}

	public void setModify_user(String modify_user) {
		this.modify_user = modify_user;
	}

	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}

	public void setVerify_user(String verify_user) {
		this.verify_user = verify_user;
	}

	public void setVerify_time(Date verify_time) {
		this.verify_time = verify_time;
	}

	public BHDSIssueMaster(String issue_srl_no, Date rpt_date, String client, String lodged_by, String application,
			String issue_type, String report_code, String issue_description, String priority, BigDecimal age,
			String assigned_to, Date assigned_date, String current_status, String entity_flg, String del_flg,
			String modify_flg, String entry_user, Date entry_time, String modify_user, Date modify_time,
			String verify_user, Date verify_time) {
		super();
		this.issue_srl_no = issue_srl_no;
		this.rpt_date = rpt_date;
		this.client = client;
		this.lodged_by = lodged_by;
		this.application = application;
		this.issue_type = issue_type;
		this.report_code = report_code;
		this.issue_description = issue_description;
		this.priority = priority;
		this.age = age;
		this.assigned_to = assigned_to;
		this.assigned_date = assigned_date;
		this.current_status = current_status;
		this.entity_flg = entity_flg;
		this.del_flg = del_flg;
		this.modify_flg = modify_flg;
		this.entry_user = entry_user;
		this.entry_time = entry_time;
		this.modify_user = modify_user;
		this.modify_time = modify_time;
		this.verify_user = verify_user;
		this.verify_time = verify_time;
	}

	public BHDSIssueMaster() {
		super();
	}

}
