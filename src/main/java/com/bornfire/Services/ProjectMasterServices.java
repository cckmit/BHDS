package com.bornfire.Services;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bornfire.entity.BHDS_Project_Master_Log;
@Service
@Transactional
public class ProjectMasterServices {

	
	@Autowired
	SessionFactory sessionFactory;
	
	public String BHDSProjectReg(BHDS_Project_Master_Log projectMaster, String formmode) {
		Session hs = sessionFactory.getCurrentSession();

		/*
		 * Optional<KycHistory> up = cmgRepository.findById(kychistory.getCustId());
		 */ // TODO Auto-generated method stub
		String msg = "";

		if (formmode.equals("add")) {
			System.out.println(formmode);

			BHDS_Project_Master_Log up = projectMaster;
    up.setDelete_flag("N");
    up.setEntity_flag("Y");
    
			hs.saveOrUpdate(up);

			msg = "Added Successfully";
		}
		return msg;
	}
	public String getSrlNoValue() {
		 Session hs =sessionFactory.getCurrentSession(); 
		
				 DecimalFormat numformate = new  DecimalFormat("000");
		 BigDecimal billNumber = (BigDecimal) hs.createNativeQuery("SELECT PMSEQUENCE.NEXTVAL AS SRL_NO FROM DUAL").getSingleResult();
		String serialno=""+numformate.format(billNumber);
		System.out.println("billno" + serialno);
		 return serialno;
		}
	
}

