package com.bornfire.entity;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "ALERT_MGMT_TABLE")
public class AlertManagementEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private String	srl_no;
	private String	client_id;
	private String	client_name;
	private String	application_code;
	private String	application_name;
	private String	location;
	private String	country;
	private String	spoc_name;
	private String	designation_2;
	private String	email_id;
	private String mobile_user_1;
	private String	spoc_name_1;
	private String	designation_1;
	private String	email_id_1;
	private String	mobile_number_1;
	private String	spoc_name_2;
	private String	designation_22;
	private String	email_user_3;
	private String	mobile_number_2;
	private String	entity_flg;
	private String	del_flg;
	private String	mod_flg;
	private String	entry_user;
	private String	modify_user;
	private String	auth_user;
	private Date	entry_time;
	private Date	modify_time;
	private Date	auth_time;
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	public String getMobile_user_1() {
		return mobile_user_1;
	}


	public void setMobile_user_1(String mobile_user_1) {
		this.mobile_user_1 = mobile_user_1;
	}


	public String getSrl_no() {
		return srl_no;
	}
	public String getClient_id() {
		return client_id;
	}
	public String getClient_name() {
		return client_name;
	}
	public String getApplication_code() {
		return application_code;
	}
	public String getApplication_name() {
		return application_name;
	}
	public String getLocation() {
		return location;
	}
	public String getCountry() {
		return country;
	}
	public String getSpoc_name() {
		return spoc_name;
	}
	public String getDesignation_2() {
		return designation_2;
	}
	public String getEmail_id() {
		return email_id;
	}
	public String getSpoc_name_1() {
		return spoc_name_1;
	}
	public String getDesignation_1() {
		return designation_1;
	}
	public String getEmail_id_1() {
		return email_id_1;
	}
	public String getMobile_number_1() {
		return mobile_number_1;
	}
	public String getSpoc_name_2() {
		return spoc_name_2;
	}
	public String getDesignation_22() {
		return designation_22;
	}
	public String getEmail_user_3() {
		return email_user_3;
	}
	public String getMobile_number_2() {
		return mobile_number_2;
	}
	public String getEntity_flg() {
		return entity_flg;
	}
	public String getDel_flg() {
		return del_flg;
	}
	public String getMod_flg() {
		return mod_flg;
	}
	public String getEntry_user() {
		return entry_user;
	}
	public String getModify_user() {
		return modify_user;
	}
	public String getAuth_user() {
		return auth_user;
	}
	public Date getEntry_time() {
		return entry_time;
	}
	public Date getModify_time() {
		return modify_time;
	}
	public Date getAuth_time() {
		return auth_time;
	}
	public void setSrl_no(String srl_no) {
		this.srl_no = srl_no;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}
	public void setApplication_code(String application_code) {
		this.application_code = application_code;
	}
	public void setApplication_name(String application_name) {
		this.application_name = application_name;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setSpoc_name(String spoc_name) {
		this.spoc_name = spoc_name;
	}
	public void setDesignation_2(String designation_2) {
		this.designation_2 = designation_2;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public void setSpoc_name_1(String spoc_name_1) {
		this.spoc_name_1 = spoc_name_1;
	}
	public void setDesignation_1(String designation_1) {
		this.designation_1 = designation_1;
	}
	public void setEmail_id_1(String email_id_1) {
		this.email_id_1 = email_id_1;
	}
	public void setMobile_number_1(String mobile_number_1) {
		this.mobile_number_1 = mobile_number_1;
	}
	public void setSpoc_name_2(String spoc_name_2) {
		this.spoc_name_2 = spoc_name_2;
	}
	public void setDesignation_22(String designation_22) {
		this.designation_22 = designation_22;
	}
	public void setEmail_user_3(String email_user_3) {
		this.email_user_3 = email_user_3;
	}
	public void setMobile_number_2(String mobile_number_2) {
		this.mobile_number_2 = mobile_number_2;
	}
	public void setEntity_flg(String entity_flg) {
		this.entity_flg = entity_flg;
	}
	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
	}
	public void setMod_flg(String mod_flg) {
		this.mod_flg = mod_flg;
	}
	public void setEntry_user(String entry_user) {
		this.entry_user = entry_user;
	}
	public void setModify_user(String modify_user) {
		this.modify_user = modify_user;
	}
	public void setAuth_user(String auth_user) {
		this.auth_user = auth_user;
	}
	public void setEntry_time(Date entry_time) {
		this.entry_time = entry_time;
	}
	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}
	public void setAuth_time(Date auth_time) {
		this.auth_time = auth_time;
	}



}
