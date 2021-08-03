package com.bornfire.Services;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;
import javax.validation.constraints.NotNull;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bornfire.config.PasswordEncryption;
import com.bornfire.entity.BHDS_Client_Issue_Log;
import com.bornfire.entity.BHDS_client_issue_Rep;
import com.bornfire.entity.EMAILREP;
import com.bornfire.entity.EmailAlert;
import com.bornfire.entity.UserProfile;
import com.bornfire.entity.UserProfileRep;
import com.bornfire.entity.UserProfileResponse;

@Service
@Repository
@Transactional
public class UserProfileDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	UserProfileRep userProfileRep;

	
	@Autowired
	DataSource srcdataSource;

	@Autowired
	Environment env;
	
	@NotNull
	private String exportpath;
	

	@Autowired
	EMAILREP emailRep;
	
	@Autowired
	BHDS_client_issue_Rep bHDS_client_issue_Rep;

	@Value("${default.password}")
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getExportpath() {
		return exportpath;
	}

	public void setExportpath(String exportpath) {
		this.exportpath = exportpath;
	}
	@SuppressWarnings("unchecked")
	public UserProfileResponse checkUser(String userid) {

		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<UserProfile> cr = cb.createQuery(UserProfile.class);
		Root<UserProfile> root = cr.from(UserProfile.class);

		List<UserProfile> cnt1 = (List<UserProfile>) sessionFactory.getCurrentSession()
				.createQuery("from UserProfile where userid=?1").setParameter(1, userid).getResultList();

		if (cnt1.size() > 0) {
			return new UserProfileResponse("success", cnt1.get(0));
		} else {
			return new UserProfileResponse("failure");
		}
	}

	public void updateLoginAttempt(UserProfile user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}

	public String checkPasswordChangeReq(String userid) {

		UserProfile cnt1 = (UserProfile) sessionFactory.getCurrentSession()
				.createQuery("from UserProfile where userid=?1").setParameter(1, userid).getSingleResult();

		String loginflg = cnt1.getLogin_flg();
		

		return loginflg;
	}

	public int checkpassexpirty(String userid) {
		
		UserProfile cnt1 = (UserProfile) sessionFactory.getCurrentSession()
				.createQuery("from UserProfile where userid=?1").setParameter(1, userid).getSingleResult();
		Date expDate = cnt1.getPass_exp_date();
		Date currDate = new Date();

		DateTime dt1 = new DateTime(currDate);
		DateTime dt2 = new DateTime(expDate);

		int remaindays = Days.daysBetween(dt1, dt2).getDays();

		return remaindays;
	}

	public int checkAcctexpirty(String userid) {
		UserProfile cnt1 = (UserProfile) sessionFactory.getCurrentSession()
				.createQuery("from UserProfile where userid=?1").setParameter(1, userid).getSingleResult();
		Date expDate = cnt1.getAcc_exp_date();
		Date currDate = new Date();

		DateTime dt1 = new DateTime(currDate);
		DateTime dt2 = new DateTime(expDate);

		int remaindays = Days.daysBetween(dt1, dt2).getDays();

		return remaindays;
	}

	@SuppressWarnings("unchecked")
	public Object getUsersList() {
		
		List<UserProfile> cnt1 = (List<UserProfile>) sessionFactory.getCurrentSession()
				.createQuery("from UserProfile").getResultList();
	
		return cnt1;
	}

	@SuppressWarnings("unchecked")
	public UserProfile getUser(String userid) {

		List<UserProfile> cnt1 = (List<UserProfile>) sessionFactory.getCurrentSession()
				.createQuery("from UserProfile where userid=?1").setParameter(1, userid).getResultList();

		if (cnt1.size() > 0) {
			return cnt1.get(0);
		} else {
			return new UserProfile();
		}
		 
		
	}

	public Object getFinUsersList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public String addUser( UserProfile userform, String formmode) {
		// TODO Auto-generated method stub
		String msg = "";
		  
		/* try { */

		if (formmode.equals("add")) {

			UserProfile up = userform;
			Session hs = sessionFactory.getCurrentSession();
			
			  try { String encryptedPassword =
			  PasswordEncryption.getEncryptedPassword(this.password);
			  
			  if (up.getLogin_status().equals("Active")) { up.setUser_locked_flg("N"); }
			  else { up.setUser_locked_flg("Y"); }
			  
			  if (up.getUser_status().equals("Active")) { up.setDisable_flg("N"); } else {
			  up.setDisable_flg("Y"); }
			  
			/*
			 * up.setEntity_flg("N"); up.setEntry_time(new Date());
			 * up.setEntry_user(inputUser);
			 * 
			 * up.setLogin_flg("N");//To prompt the user for changing the password at first
			 * login up.setNo_of_attmp(0);
			 */ up.setPassword(encryptedPassword);
			  
			  } catch (Exception e) { e.printStackTrace(); }
			 
			up.setDel_flg("N");
			up.setEntity_flg("Y");
			hs.saveOrUpdate(up);

			msg = "User Created Successfully";

		}
		// When the user data modifed and submitted.
		
		  else if (formmode.equals("edit")) {
				UserProfile up = userform;
				Session hs = sessionFactory.getCurrentSession();
			/*
			 * Optional<RuleEngineEntity> up
			 * =RuleEngineEntity.findById(RuleEngineEntity.getSrl_no());
			 */
		  
			/* if (up.isPresent()) { */
		  
		  
		 /* userProfile.setPassword(up.get().getPassword());
		  
		  if (userProfile.getLogin_status().equals("Active")) {
		 
		 userProfile.setUser_locked_flg("N"); } else {
		 userProfile.setUser_locked_flg("Y"); }
		  
		  if (userProfile.getUser_status().equals("Active")) {
		  userProfile.setDisable_flg("N"); } else { userProfile.setDisable_flg("Y"); }
		  
		  userProfile.setNo_of_attmp(0); userProfile.setEntity_flg("N");
		  userProfile.setModify_user(inputUser); userProfile.setModify_time(new
		  Date());*/
		  
				up.setDel_flg("N");
				up.setEntity_flg("N");
				hs.saveOrUpdate(up);
			/* } */
		  
		  msg = "User Edited Successfully";
		  
		  }
		
		  else if (formmode.equals("delete")) {
			 
		  
		  msg = "User Deleted Successfully";
		  
		  }
		 

		return msg;
	}
	
	public String verifyUser(UserProfile userProfile, String inputUser) {
		String msg = "";

		Optional<UserProfile> up = userProfileRep.findById(userProfile.getUserid());

		try {

			if (up.isPresent()) {

				userProfile.setPassword(up.get().getPassword());

				if (userProfile.getLogin_status().equals("Active")) {
					userProfile.setUser_locked_flg("N");
				} else {
					userProfile.setUser_locked_flg("Y");
				}

				if (userProfile.getUser_status().equals("Active")) {
					userProfile.setDisable_flg("N");
				} else {
					userProfile.setDisable_flg("Y");
				}

				userProfile.setNo_of_attmp(0);
				userProfile.setEntity_flg("Y");
				userProfile.setLogin_flg("N");
				userProfile.setAuth_user(inputUser);
				userProfile.setAuth_time(new Date());

				userProfileRep.save(userProfile);
			}

			msg = "User Verified Successfully";
		} catch (Exception e) {
			
			e.printStackTrace();
			msg = "Error Occured. Please contact Administrator";
		}

		return msg;
	}

	
	public String passwordReset(UserProfile userprofile, String userid) {

		String msg = "";

		try {
			String encryptedPassword = PasswordEncryption.getEncryptedPassword(this.password);

			Optional<UserProfile> up = userProfileRep.findById(userprofile.getUserid());

			if (up.isPresent()) {
				UserProfile user = up.get();
				user.setPassword(encryptedPassword);
				user.setNo_of_attmp(0);
				user.setLogin_flg("N");
				user.setUser_locked_flg("N");
				userProfileRep.save(user);
			}

			msg = "Password Resetted Successfully";

		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {

			e.printStackTrace();

			msg = "Error Occured. Please contact Administrator";
		}

		return msg;
	}
public String  uploaddata(String Issue_srl_no,String Subject,String Application,String Emailid,String Description,Date Lodged_date,byte[] bytes_1,String filename_1,byte[] bytes_2,String filename_2,byte[] bytes_3,String filename_3,InputStream IS) throws ParseException, SQLException, IOException {
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
Session hs = sessionFactory.getCurrentSession();
String email = Emailid.toString();
String status ="";
BHDS_Client_Issue_Log str = new BHDS_Client_Issue_Log();
EmailAlert EA = new EmailAlert();

Date dated = formatter.parse(formatter.format(new Date()));
BigDecimal getcount = bHDS_client_issue_Rep.getcount(Subject, dated);
System.out.println("getcount"+getcount+dated);
BigDecimal Number1 = (BigDecimal) hs.createNativeQuery("SELECT CLIENT.NEXTVAL AS SRL_NO FROM DUAL")
		.getSingleResult();
BigDecimal num = new BigDecimal(0);
String[] For_split_email = email.split("[@]");
String[] For_split_filename_1 = null;
String filetype_1=null;
String[] For_split_filename_2 = null;
String filetype_2=null;
String[] For_split_filename_3 = null;
String filetype_3=null;
SimpleDateFormat formatter2 = new SimpleDateFormat("ddMMyyyyHHmmss");
	// String d1 = formatter2.format(new Date());

	String ConDate = formatter2.format(new Date());
	System.out.println(filename_1);
if(filename_1 == null || filename_1.length() == 0) {

}else {
	 For_split_filename_1 = filename_1.split("[.]");
	 for (int j = 0; j <= For_split_filename_1.length - 1; j++)
	 {
	 	filetype_1 = For_split_filename_1[1];
	 	}
}
if(filename_2 == null || filename_2.length() == 0) {

}else {
	 For_split_filename_2 = filename_2.split("[.]");
	 for (int j = 0; j <= For_split_filename_2.length - 1; j++)
	 {
	 	filetype_2 = For_split_filename_2[1];
	 	}
}
if(filename_3 == null || filename_3.length() == 0) {

}else {
	 For_split_filename_3 = filename_3.split("[.]");
	 for (int j = 0; j <= For_split_filename_3.length - 1; j++)
	 {
	 	filetype_3 = For_split_filename_3[1];
	 	}
}

for (int j = 0; j <= For_split_email.length - 1; j++)
{
    System.out.println("splited emails----------"+j + For_split_email[j]);
    String client = For_split_email[1];
   
    str.setIssue_srl_no(Number1.toString());
    str.setClient(client);
    str.setSubject(Subject);
    str.setApplication(Subject);
    str.setEmailid(Emailid);
    str.setLodgedby(Emailid);
    str.setDescription(Description);
    str.setLodged_date(dated);
    str.setPriority("High");
    str.setReport_date(dated);
    str.setIssue_type("DATA ISSUE");
    str.setReportCode("DATA ISSUE");
    str.setAttachment_1(bytes_1);
    str.setAttachment_2(bytes_2);
    str.setAttachment_3(bytes_3);
    
    str.setFile_type(filetype_1);
    EA.setEmail_id(Emailid);
	EA.setEmail_id_cc1("manivannan.b@bornfire.in");
	//EA.setEmail_id_cc2("vishnu.pa@bornfire.in");
	EA.setEmail_sub("RE :"+Subject);
	EA.setEmail_body("We have acknowedged your mail and our team will get back to you,/Thanks and Regards,/BORNFIRE./");
	EA.setEmail_date(new Date());
	EA.setEmail_srl_no(Number1);
	EA.setSend_flg("N");
	
    }
    if(getcount.equals(num)) {
   str.setAttachment_1path(filename_1);
   str.setAttachment_2_path(filename_2);
   str.setAttachment_3_path(filename_3);
    	hs.saveOrUpdate(str);
    	emailRep.save(EA);
    	 status ="Success";
    	
    }else {
    	 status ="DATA ALREADY EXISTS";
    	
   
    }
	return status;
     	
}
}