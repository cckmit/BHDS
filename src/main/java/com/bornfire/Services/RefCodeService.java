package com.bornfire.Services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import com.bornfire.entity.RefCodeMasterEmbeddedID;
import com.bornfire.entity.RefCodeRepository;
import com.bornfire.entity.RefcodeEntity;


@Service
@ConfigurationProperties("output")
@Transactional
public class RefCodeService {

	@Autowired
	private  RefCodeRepository refCodeRepository;


	@Autowired
	SessionFactory sessionFactory;
	public String addPARAMETER(RefcodeEntity alertparam, String formmode, String refcode,String recordtype,String reportcode) {
		// TODO Auto-generated method stub
		System.out.println("alertparam ->"+alertparam);
		String msg = "";
		/*
		 * Optional<RefcodeEntity> ref= refCodeRepository.getValue(refcode,recordtype);
		 * ref.isPresent(); System.out.println(ref.isPresent()); RefcodeEntity reg =
		 * ref.get();
		 */
		
		  
		RefcodeEntity ref = alertparam;
		/* try { */
		if (formmode.equals("add")) {
		// Optional<RefcodeEntity> reg = refCodeRepository.findById(new RefCodeMasterEmbeddedID(recordtype,refcode,reportcode));
			
		//	 RefcodeEntity reg1= ref.get();
			 System.out.println("+_+_+"+alertparam.getRefCodeId()+"+_+_+");
			 ref.setRefCodeId(alertparam.getRefCodeId());
			 ref.setDel_flg("N");
			 ref.setEntity_flag("N");
			refCodeRepository.save(ref);
			msg = "Parameter Created Successfully";
		}	
		  else if (formmode.equals("edit")) {
			  
			  Optional<RefcodeEntity> reg = refCodeRepository.findById(new RefCodeMasterEmbeddedID(recordtype,refcode,reportcode));
			  if(reg.isPresent()) {
				  
				 
				  
				  RefcodeEntity reg1= reg.get();
				  reg1.setDel_flg("N");
				  reg1.setModify_flag("Y");
				  reg1.setEntity_flag("N");
				  reg1.setModule(alertparam.getModule());
				  reg1.setRef_type(alertparam.getRef_type());
				  reg1.setRefCodeId(alertparam.getRefCodeId());
				  reg1.setRpt_desc(alertparam.getRpt_desc());
				  reg1.setModify_user(alertparam.getModify_user());
				  reg1.setModify_time(alertparam.getModify_time());
				  refCodeRepository.save(reg1);
			  }
			  
			  
			  	  
		  msg = "Parameter Edited Successfully";		  
		  }		
		  else if (formmode.equals("delete")) {

			  Optional<RefcodeEntity> reg = refCodeRepository.findById(new RefCodeMasterEmbeddedID(recordtype,refcode,reportcode));
			  if(reg.isPresent()) {
				  RefcodeEntity reg1= reg.get();
				  reg1.setDel_flg("Y");
				  reg1.setEntity_flag("N");
				  refCodeRepository.save(reg1);
				  
			  }
		  msg = "Parameter Deleted Successfully";  
		  }
		  else if (formmode.equals("verify")) {
			  Optional<RefcodeEntity> reg = refCodeRepository.findById(new RefCodeMasterEmbeddedID(recordtype,refcode,reportcode));
				  RefcodeEntity reg1= reg.get();
				  reg1.setDel_flg("N");
				  reg1.setEntity_flag("Y");
				  reg1.setVerify_user(alertparam.getVerify_user());
				  reg1.setVerify_time(alertparam.getVerify_time());
				  refCodeRepository.save(reg1);
				  msg = "Parameter Verified Successfully";
				  
			
			
			  }
		return msg;
	}
	
	public RefcodeEntity getRefcode(String id,String rectype,String reportcode) {
		
		System.out.println("getrefcode"+id+""+rectype+""+reportcode);
		Session session = sessionFactory.getCurrentSession();
		Query<RefcodeEntity> query = session
				.createQuery(" from RefcodeEntity where ref_code=?1 and ref_rec_type=?2 and rpt_code=?3  ",RefcodeEntity.class);
		query.setParameter(1, id);
		query.setParameter(2, rectype);
		query.setParameter(3, reportcode);
		
		List<RefcodeEntity> result =  query.getResultList();

		

		if (!result.isEmpty()) {
			
			return result.get(0);
		} else {
			
			return new RefcodeEntity();
		}

	};
	
	public String deleteParameter(String refcode,String recordtype,String reportcode) {
		System.out.println("hhhhhh");
		String msg = "";
		 Optional<RefcodeEntity> user= refCodeRepository.findById(new RefCodeMasterEmbeddedID(recordtype,refcode,reportcode));
		
		 RefcodeEntity reg = user.get(); 
		 reg.setDel_flg("Y");
		
			  msg = "User Deleted Successfully";
		return msg;
	}
	
	public String verifysrlno(String refcode,String recordtype,String reportcode) {
		System.out.println("hhhhhh");
		String msg = "";
		 Optional<RefcodeEntity> user= refCodeRepository.findById(new RefCodeMasterEmbeddedID(recordtype,refcode,reportcode));
		 
		 RefcodeEntity reg = user.get(); 
		 reg.setDel_flg("N");
		/* refCodeRepository.save(reg); */
			  msg = "User Deleted Successfully";
		return msg;
	}
	
	
	@SuppressWarnings("unchecked")
	public String getReportCodedesc(String repcode) {

		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createNativeQuery("select distinct(rule_code_desc) from AML_REFERENCE_CODE_TABLE where rule_code=?1  ");
		query.setParameter(1, repcode);

		String result = (String) query.getSingleResult();

		System.out.println(result);
		return result;
	}
	
	
	@SuppressWarnings("unchecked")
	public String getRecordTypedesc(String repcode) {

		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createNativeQuery("select distinct(rec_desc) from RECORD_TYPE_TABLE where ref_rec_type=?1  ");
		query.setParameter(1, repcode);

		String result = (String) query.getSingleResult();

		System.out.println(result);
		return result;
	} 
	
	
	@SuppressWarnings("unchecked")
	public String getReferenceCodedesc(String rectype, String refcode) {
		System.out.println(rectype);
		System.out.println(refcode);
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createNativeQuery("select distinct(ref_desc) from REFERENCE_CODE_TABLE where ref_rec_type=?1 and ref_code=?2 ");
		query.setParameter(1, rectype);
		query.setParameter(2, refcode);

		String result = (String) query.getSingleResult();

		System.out.println(result);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public String getReferenceType(String rectype, String refcode) {

		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createNativeQuery("select distinct ref_type from REFERENCE_CODE_TABLE where ref_rec_type=?1 and ref_code=?2");
		query.setParameter(1, rectype);
		query.setParameter(2, refcode);

		String result = (String) query.getSingleResult();

		System.out.println(result);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<RefcodeEntity> getReferenceCodeselect(String rulecode) {

		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("from RefcodeEntity where ref_rec_type=?1 ",RefcodeEntity.class);
		query.setParameter(1, rulecode);

		List<RefcodeEntity> result =  query.getResultList();

		System.out.println(result);
		return result;
	}
	
}


