package com.bornfire.entity;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "BORNFIRE_CLIENT_ISSUELOG")


public class BHDS_Client_Issue_Log {

	@Id
	private String issue_srl_no;
	private String client;
	private Date report_date;
	private String lodgedby;
	private String emailid;
	private String application;
	private String issue_type;
	private String priority;
	private String reportCode;
	private String subject;
	private String description;
	@Lob
	private byte[] attachment_1;
	@Lob
	private byte[] attachment_2;
	@Lob
	private byte[] attachment_3;
	private String assign_to;
	private String resolution;
	private String comments;
	private String current_status;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date lodged_date;
	private String reviews;
	private String file_type;
	private String attachment_1path;
	private String attachment_2_path;
	private String attachment_3_path;
	public String getIssue_srl_no() {
		return issue_srl_no;
	}
	public void setIssue_srl_no(String issue_srl_no) {
		this.issue_srl_no = issue_srl_no;
	}
	
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public Date getReport_date() {
		return report_date;
	}
	public void setReport_date(Date report_date) {
		this.report_date = report_date;
	}
	public String getLodgedby() {
		return lodgedby;
	}
	public void setLodgedby(String lodgedby) {
		this.lodgedby = lodgedby;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	public String getIssue_type() {
		return issue_type;
	}
	public void setIssue_type(String issue_type) {
		this.issue_type = issue_type;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getReportCode() {
		return reportCode;
	}
	public void setReportCode(String reportCode) {
		this.reportCode = reportCode;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public byte[] getAttachment_1() {
		return attachment_1;
	}
	public void setAttachment_1(byte[] attachment_1) {
		this.attachment_1 = attachment_1;
	}
	public byte[] getAttachment_2() {
		return attachment_2;
	}
	public void setAttachment_2(byte[] attachment_2) {
		this.attachment_2 = attachment_2;
	}
	public byte[] getAttachment_3() {
		return attachment_3;
	}
	public void setAttachment_3(byte[] attachment_3) {
		this.attachment_3 = attachment_3;
	}
	public String getAssign_to() {
		return assign_to;
	}
	public void setAssign_to(String assign_to) {
		this.assign_to = assign_to;
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getCurrent_status() {
		return current_status;
	}
	public void setCurrent_status(String current_status) {
		this.current_status = current_status;
	}
	public Date getLodged_date() {
		return lodged_date;
	}
	public void setLodged_date(Date lodged_date) {
		this.lodged_date = lodged_date;
	}
	public String getReviews() {
		return reviews;
	}
	public void setReviews(String reviews) {
		this.reviews = reviews;
	}
	public String getFile_type() {
		return file_type;
	}
	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}
	
	
	public String getAttachment_1path() {
		return attachment_1path;
	}
	public void setAttachment_1path(String attachment_1path) {
		this.attachment_1path = attachment_1path;
	}
	public String getAttachment_2_path() {
		return attachment_2_path;
	}
	public void setAttachment_2_path(String attachment_2_path) {
		this.attachment_2_path = attachment_2_path;
	}
	public String getAttachment_3_path() {
		return attachment_3_path;
	}
	public void setAttachment_3_path(String attachment_3_path) {
		this.attachment_3_path = attachment_3_path;
	}
	public BHDS_Client_Issue_Log(String issue_srl_no, String client, Date report_date, String lodgedby, String emailid,
			String application, String issue_type, String priority, String reportCode, String subject,
			String description, byte[] attachment_1, byte[] attachment_2, byte[] attachment_3, String assign_to,
			String resolution, String comments, String current_status, Date lodged_date, String reviews,
			String file_type, String attachment_1path, String attachment_2_path, String attachment_3_path) {
		super();
		this.issue_srl_no = issue_srl_no;
		this.client = client;
		this.report_date = report_date;
		this.lodgedby = lodgedby;
		this.emailid = emailid;
		this.application = application;
		this.issue_type = issue_type;
		this.priority = priority;
		this.reportCode = reportCode;
		this.subject = subject;
		this.description = description;
		this.attachment_1 = attachment_1;
		this.attachment_2 = attachment_2;
		this.attachment_3 = attachment_3;
		this.assign_to = assign_to;
		this.resolution = resolution;
		this.comments = comments;
		this.current_status = current_status;
		this.lodged_date = lodged_date;
		this.reviews = reviews;
		this.file_type = file_type;
		this.attachment_1path = attachment_1path;
		this.attachment_2_path = attachment_2_path;
		this.attachment_3_path = attachment_3_path;
	}
	public BHDS_Client_Issue_Log() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}