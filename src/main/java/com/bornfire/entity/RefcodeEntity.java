package com.bornfire.entity;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name = "REFERENCE_CODE_TABLE")
public class RefcodeEntity {
	

	
	

	public RefcodeEntity() {
		super();
		// TODO Auto-generated constructor stub
	}



	@EmbeddedId
	RefCodeMasterEmbeddedID refCodeId;
	
	public RefCodeMasterEmbeddedID getRefCodeId() {
		return refCodeId;
	}
	public void setRefCodeId(RefCodeMasterEmbeddedID refCodeId) {
		this.refCodeId = refCodeId;
	}


	
	private String	del_flg;
	private String	ref_desc;
	private String	long_ref_code;
	private String	ref_rec_desc;
	private String	ref_type;
	private String	module;

	
	private String	rpt_desc;
	private String	entity_flag;
	private String	modify_flag;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	entry_time;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	modify_time;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	verify_time;
	private String	entry_user;
	private String	modify_user;
	private String	verify_user;
	

	
	public String getDel_flg() {
		return del_flg;
	}
	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
	}
	public String getRef_desc() {
		return ref_desc;
	}
	public void setRef_desc(String ref_desc) {
		this.ref_desc = ref_desc;
	}
	public String getLong_ref_code() {
		return long_ref_code;
	}
	public void setLong_ref_code(String long_ref_code) {
		this.long_ref_code = long_ref_code;
	}
	public String getRef_rec_desc() {
		return ref_rec_desc;
	}
	public void setRef_rec_desc(String ref_rec_desc) {
		this.ref_rec_desc = ref_rec_desc;
	}
	public String getRef_type() {
		return ref_type;
	}
	public void setRef_type(String ref_type) {
		this.ref_type = ref_type;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}

	public String getRpt_desc() {
		return rpt_desc;
	}
	public void setRpt_desc(String rpt_desc) {
		this.rpt_desc = rpt_desc;
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
	public void setRefCodeId(String rpt_code) {
		
		
	}
	@Override
	public String toString() {
		return "RefcodeEntity [refCodeId=" + refCodeId + ", del_flg=" + del_flg + ", ref_desc=" + ref_desc
				+ ", long_ref_code=" + long_ref_code + ", ref_rec_desc=" + ref_rec_desc + ", ref_type=" + ref_type
				+ ", module=" + module + ", rpt_desc=" + rpt_desc + ", entity_flag=" + entity_flag + ", modify_flag="
				+ modify_flag + ", entry_time=" + entry_time + ", modify_time=" + modify_time + ", verify_time="
				+ verify_time + ", entry_user=" + entry_user + ", modify_user=" + modify_user + ", verify_user="
				+ verify_user + "]";
	}


	
	
	
	
	

}