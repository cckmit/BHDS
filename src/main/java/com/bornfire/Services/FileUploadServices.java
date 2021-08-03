package com.bornfire.Services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.bornfire.entity.File_upload_details;

@Service
@Transactional
public class FileUploadServices {
	
	@Autowired
	SessionFactory sessionFactory;
	
	
	public String BHDSIssueLogReg(File_upload_details IssueMaster, String formmode) {
	Session hs = sessionFactory.getCurrentSession();

	/*
	 * Optional<KycHistory> up = cmgRepository.findById(kychistory.getCustId());
	 */ // TODO Auto-generated method stub
	String msg = "";
	

	if (formmode.equals("add")) {
		System.out.println(formmode);


		File_upload_details up = IssueMaster;

		hs.saveOrUpdate(up);

		msg = "Issue Added Successfully";
	}
	return msg;
}


}
