package com.bornfire.Services;



import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bornfire.entity.Trouble_shooting_Log;
import com.bornfire.entity.Trouble_shooting_Reg;


@Service
@Transactional
public class TroubleShootingServices {
	
	@Autowired
	SessionFactory sessionFactory;
	
	
	public String TroubleShootReg(Trouble_shooting_Reg troubleMaster, String formmode) {
		Session hs = sessionFactory.getCurrentSession();

		/*
		 * Optional<KycHistory> up = cmgRepository.findById(kychistory.getCustId());
		 */ // TODO Auto-generated method stub
		String msg = "";

		if (formmode.equals("add")) {
			System.out.println(formmode);

			Trouble_shooting_Reg up = troubleMaster;
		//	System.out.println("ID"+up.getSrl_no());
			//up.setSrl_no(srl_no);
   
    
			hs.saveOrUpdate(up);
            

			msg = "Added Successfully";
		}
		return msg;

	}

	public static Object getSrlNoValue() {
		// TODO Auto-generated method stub
		return null;
	}
}