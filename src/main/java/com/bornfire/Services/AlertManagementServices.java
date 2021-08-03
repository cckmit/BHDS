package com.bornfire.Services;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import com.bornfire.entity.AlertManagementEntity;
import com.bornfire.entity.AlertManagementRepository;
import com.bornfire.entity.BHDS_Notification_Master_Rep;
import com.bornfire.entity.BHDS_Notification_master;

@Service
@ConfigurationProperties("output")
@Transactional

public class AlertManagementServices {
	
	
	private static final Logger logger = LoggerFactory.getLogger(LoginServices.class);
	@Autowired
	AlertManagementRepository alertrepository;
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	BHDS_Notification_Master_Rep notification_Master_Rep;
	
	
	public String addAlert(BHDS_Notification_master notification_master, String formmode) {
		// TODO Auto-generated method stub
		String msg = "";
		  
		/* try { */

		if (formmode.equals("add")) {

			BHDS_Notification_master up = notification_master;
			up.setEntity_flg("Y");
			up.setDel_flg("N");
			notification_Master_Rep.save(up);
			msg = "User Created Successfully";

		}
		// When the user data modifed and submitted.
		
		  else if (formmode.equals("edit")) {
				BHDS_Notification_master up = notification_master;
			
		  
			  up.setDel_flg("N");
				notification_Master_Rep.save(up);
		  
		  msg = "User Edited Successfully";
		  
		  }
		
		  else if (formmode.equals("delete")) {
			 
		  
		  msg = "User Deleted Successfully";
		  
		  }
		 

		return msg;
	}
	
	public String getSrlNoValue() {
	 Session hs =sessionFactory.getCurrentSession(); 
	
			 DecimalFormat numformate = new  DecimalFormat("000");
	 BigDecimal billNumber = (BigDecimal) hs.createNativeQuery("SELECT NOTIFICATION_SEQ.NEXTVAL AS SRL_NO FROM DUAL").getSingleResult();
	String serialno=""+numformate.format(billNumber);
	System.out.println("billno" + serialno);
	 return serialno;
	}
	public BHDS_Notification_master getSrlNo(String id) {
		/* logger.info(id); */
		if (notification_Master_Rep.existsById(id)) {
			System.out.println("getsrlno");
			BHDS_Notification_master up = notification_Master_Rep.findById(id).get();
			
			return up;
		} else {
			return new BHDS_Notification_master();
		}

	};
	
	
	public String deletealert(String inputSrlNo) {
		String msg = "";
		 Optional<AlertManagementEntity> user= alertrepository.findById(inputSrlNo);
		 AlertManagementEntity reg = user.get();       
			reg.setDel_flg("Y");
			alertrepository.save(reg);
			  msg = "User Deleted Successfully";
		return msg;
	}
}