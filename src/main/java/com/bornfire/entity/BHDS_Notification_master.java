package com.bornfire.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BHDS_NOTIFICATION_MASTER")
public class BHDS_Notification_master {

	
	@Id
	private String srlno;
	private String client_name;
	private String application_code;
	private String application_name;
	private String location;
	private String country;
	private String spoc1_name;
	private String s1_designation;
	private String s1_email_id;
	private String s1_mobile_number;
	private String spoc2_name;
	private String s2_designation;
	private String s2_email_id;
	private String s2_mobile_number;
	private String spoc3_name;
	private String s3_designation;
	private String s3_email_id;
	private String s3_mobile_number;
	private String entity_flg;
	private String modify_flg;
	private String del_flg;
	private String entry_user;
	private String modify_user;
	private String verify_user;
	private Date entry_time;
	private Date modify_time;
	private Date verify_time;
	
	
	public String getSrlno() {
		return srlno;
	}
	public void setSrlno(String srlno) {
		this.srlno = srlno;
	}
	public String getClient_name() {
		return client_name;
	}
	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}
	public String getApplication_code() {
		return application_code;
	}
	public void setApplication_code(String application_code) {
		this.application_code = application_code;
	}
	public String getApplication_name() {
		return application_name;
	}
	public void setApplication_name(String application_name) {
		this.application_name = application_name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
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
	public String getS1_designation() {
		return s1_designation;
	}
	public void setS1_designation(String s1_designation) {
		this.s1_designation = s1_designation;
	}
	public String getS1_email_id() {
		return s1_email_id;
	}
	public void setS1_email_id(String s1_email_id) {
		this.s1_email_id = s1_email_id;
	}
	public String getS1_mobile_number() {
		return s1_mobile_number;
	}
	public void setS1_mobile_number(String s1_mobile_number) {
		this.s1_mobile_number = s1_mobile_number;
	}
	public String getSpoc2_name() {
		return spoc2_name;
	}
	public void setSpoc2_name(String spoc2_name) {
		this.spoc2_name = spoc2_name;
	}
	public String getS2_designation() {
		return s2_designation;
	}
	public void setS2_designation(String s2_designation) {
		this.s2_designation = s2_designation;
	}
	public String getS2_email_id() {
		return s2_email_id;
	}
	public void setS2_email_id(String s2_email_id) {
		this.s2_email_id = s2_email_id;
	}
	public String getS2_mobile_number() {
		return s2_mobile_number;
	}
	public void setS2_mobile_number(String s2_mobile_number) {
		this.s2_mobile_number = s2_mobile_number;
	}
	public String getSpoc3_name() {
		return spoc3_name;
	}
	public void setSpoc3_name(String spoc3_name) {
		this.spoc3_name = spoc3_name;
	}
	public String getS3_designation() {
		return s3_designation;
	}
	public void setS3_designation(String s3_designation) {
		this.s3_designation = s3_designation;
	}
	public String getS3_email_id() {
		return s3_email_id;
	}
	public void setS3_email_id(String s3_email_id) {
		this.s3_email_id = s3_email_id;
	}
	public String getS3_mobile_number() {
		return s3_mobile_number;
	}
	public void setS3_mobile_number(String s3_mobile_number) {
		this.s3_mobile_number = s3_mobile_number;
	}
	public String getEntity_flg() {
		return entity_flg;
	}
	public void setEntity_flg(String entity_flg) {
		this.entity_flg = entity_flg;
	}
	public String getModify_flg() {
		return modify_flg;
	}
	public void setModify_flg(String modify_flg) {
		this.modify_flg = modify_flg;
	}
	public String getDel_flg() {
		return del_flg;
	}
	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
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
	public String getVerify_user() {
		return verify_user;
	}
	public void setVerify_user(String verify_user) {
		this.verify_user = verify_user;
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
	public Date getVerify_time() {
		return verify_time;
	}
	public void setVerify_time(Date verify_time) {
		this.verify_time = verify_time;
	}
	public BHDS_Notification_master(String srlno, String client_name, String application_code, String application_name,
			String location, String country, String spoc1_name, String s1_designation, String s1_email_id,
			String s1_mobile_number, String spoc2_name, String s2_designation, String s2_email_id,
			String s2_mobile_number, String spoc3_name, String s3_designation, String s3_email_id,
			String s3_mobile_number, String entity_flg, String modify_flg, String del_flg, String entry_user,
			String modify_user, String verify_user, Date entry_time, Date modify_time, Date verify_time) {
		super();
		this.srlno = srlno;
		this.client_name = client_name;
		this.application_code = application_code;
		this.application_name = application_name;
		this.location = location;
		this.country = country;
		this.spoc1_name = spoc1_name;
		this.s1_designation = s1_designation;
		this.s1_email_id = s1_email_id;
		this.s1_mobile_number = s1_mobile_number;
		this.spoc2_name = spoc2_name;
		this.s2_designation = s2_designation;
		this.s2_email_id = s2_email_id;
		this.s2_mobile_number = s2_mobile_number;
		this.spoc3_name = spoc3_name;
		this.s3_designation = s3_designation;
		this.s3_email_id = s3_email_id;
		this.s3_mobile_number = s3_mobile_number;
		this.entity_flg = entity_flg;
		this.modify_flg = modify_flg;
		this.del_flg = del_flg;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.verify_user = verify_user;
		this.entry_time = entry_time;
		this.modify_time = modify_time;
		this.verify_time = verify_time;
	}
	public BHDS_Notification_master() {
		super();
		// TODO Auto-generated constructor stub
	}
	



	
}
