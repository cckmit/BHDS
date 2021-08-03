package com.bornfire.entity;



import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;



@Entity
@Table(name = "FILE_UPLOAD_REFERENCE")
@DynamicUpdate

public class File_upload_details {
	@Id
	
	private String	client;
	private String	del_flg;
	private String	attachment_1path;
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getDel_flg() {
		return del_flg;
	}
	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
	}
	public String getAttachment_1path() {
		return attachment_1path;
	}
	public void setAttachment_1path(String attachment_1path) {
		this.attachment_1path = attachment_1path;
	}
	public File_upload_details(String client, String del_flg, String attachment_1path) {
		super();
		this.client = client;
		this.del_flg = del_flg;
		this.attachment_1path = attachment_1path;
	}
	public File_upload_details() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}