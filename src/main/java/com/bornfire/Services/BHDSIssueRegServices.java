package com.bornfire.Services;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bornfire.entity.BHDSIssueMaster;
import com.bornfire.entity.BHDS_Client_Issue_Log;
import com.bornfire.entity.BHDS_client_issue_Rep;
import com.bornfire.entity.EMAILREP;
import com.bornfire.entity.EmailAlert;

@Service
@Transactional
public class BHDSIssueRegServices {
	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	BHDS_client_issue_Rep bHDS_client_issue_Rep;
	
	@Autowired
	EMAILREP emailRep;

	public String BHDSIssueLogReg(BHDSIssueMaster IssueMaster, String formmode) {
		Session hs = sessionFactory.getCurrentSession();

		/*
		 * Optional<KycHistory> up = cmgRepository.findById(kychistory.getCustId());
		 */ // TODO Auto-generated method stub
		String msg = "";
		

		if (formmode.equals("add")) {
			System.out.println(formmode);


			BHDSIssueMaster up = IssueMaster;

			hs.saveOrUpdate(up);

			msg = "Issue Added Successfully";
		}
		return msg;
	}

	public String addPARAMETER(BHDS_Client_Issue_Log bhds_Client_Issue_Log, String formmode) {
		Session hs = sessionFactory.getCurrentSession();
		String msg = "";
		/* try { */
		if (formmode.equals("add")) {
			BHDS_Client_Issue_Log up = bhds_Client_Issue_Log;
			up.setReport_date(new Date());

			hs.save(up);
			System.out.println("Output---->saving-------");
			msg = "Added Successfully";
			BigDecimal EMAIL = (BigDecimal) hs.createNativeQuery("SELECT EMAILSEQUENCE.NEXTVAL AS SRL_NO FROM DUAL")
					.getSingleResult();
			EmailAlert EA = new EmailAlert();
			
				EA.setEmail_id(up.getEmailid());
				EA.setEmail_id_cc1("manivannan.b@bornfire.in");
				EA.setEmail_id_cc2("vishnu.pa@bornfire.in");
				EA.setEmail_sub("HELP DESK QURIES");
				EA.setEmail_body(up.getDescription());
				EA.setEmail_date(new Date());
				EA.setEmail_srl_no(EMAIL);
				EA.setSend_flg("N");
				emailRep.save(EA);
				
				uploadImage(up);
		}
		return msg;
	}
	
	private void uploadImage(BHDS_Client_Issue_Log att) {
		
		String server = "103.224.241.244";
		int port = 21;
		String user = "FTPUSER";
		String pass = "Bornfire@123";
		String Folder = "BHDSFileManager";
		
		
		
		FTPClient ftpClient=new FTPClient();
		
		try {
			ftpClient.connect(server, port);
			
			int replyCode = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(replyCode)) {
				System.out.println("Failure");
			}
			
			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			boolean success=ftpClient.login(user, pass);
			if(success) {
				
				
				ftpClient.makeDirectory(Folder);
				
				
				InputStream inputStream =null;
				
				if(!att.getAttachment_1path().equals("")) {
					inputStream = new ByteArrayInputStream(att.getAttachment_1());
				    String 	imgPath = att.getAttachment_1path();
				    boolean done = ftpClient.storeFile(imgPath, inputStream);
				    System.out.println(imgPath);
				    
				    inputStream.close();
				    
				}
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public String getSrlNoValue() {
		Session hs = sessionFactory.getCurrentSession();

		DecimalFormat numformate = new DecimalFormat("00");
		BigDecimal billNumber = (BigDecimal) hs.createNativeQuery("SELECT CLIENT.NEXTVAL AS SRL_NO FROM DUAL")
				.getSingleResult();
		String serialno = "CLIENT" + numformate.format(billNumber);
		System.out.println("billno" + serialno);
		return serialno;
	}

	public String getSrlNoValue1() {
		Session hs = sessionFactory.getCurrentSession();

		DecimalFormat numformate = new DecimalFormat("000");
		BigDecimal billNumber = (BigDecimal) hs.createNativeQuery("SELECT SRLNO.NEXTVAL AS SRL_NO FROM DUAL")
				.getSingleResult();
		String serialno = "" + numformate.format(billNumber);
		System.out.println("billno" + serialno);
		return serialno;
	}

	public String editParameter(BHDS_Client_Issue_Log bhds_Client_Issue_Log, String formmode) {

		String msg = "";
		try {

			BHDS_Client_Issue_Log mhds_issue_log_old = bHDS_client_issue_Rep
					.findBySrl(bhds_Client_Issue_Log.getIssue_srl_no());
			System.out.println(bhds_Client_Issue_Log.getAttachment_1path());
			if(mhds_issue_log_old!=null) {
				mhds_issue_log_old.setLodgedby(bhds_Client_Issue_Log.getLodgedby()); 
				mhds_issue_log_old.setEmailid(bhds_Client_Issue_Log.getEmailid());
				mhds_issue_log_old.setApplication(bhds_Client_Issue_Log.getApplication());
				mhds_issue_log_old.setIssue_type(bhds_Client_Issue_Log.getIssue_type());
				mhds_issue_log_old.setPriority(bhds_Client_Issue_Log.getPriority());
				mhds_issue_log_old.setReportCode(bhds_Client_Issue_Log.getReportCode());
				mhds_issue_log_old.setSubject(bhds_Client_Issue_Log.getSubject());
				mhds_issue_log_old.setDescription(bhds_Client_Issue_Log.getDescription());
				mhds_issue_log_old.setAttachment_1(bhds_Client_Issue_Log.getAttachment_1());
				mhds_issue_log_old.setAttachment_2(bhds_Client_Issue_Log.getAttachment_2());
				mhds_issue_log_old.setAttachment_3(bhds_Client_Issue_Log.getAttachment_3());
				mhds_issue_log_old.setResolution(bhds_Client_Issue_Log.getResolution());
				mhds_issue_log_old.setComments(bhds_Client_Issue_Log.getComments());
				mhds_issue_log_old.setCurrent_status(bhds_Client_Issue_Log.getCurrent_status());
				mhds_issue_log_old.setReviews(bhds_Client_Issue_Log.getReviews());
				mhds_issue_log_old.setLodged_date(bhds_Client_Issue_Log.getLodged_date());
				mhds_issue_log_old.setAttachment_1path(bhds_Client_Issue_Log.getAttachment_1path());
				bHDS_client_issue_Rep.save(mhds_issue_log_old);
				msg = "Saved Successfully";
				return msg;
			}else {
				msg = "Unknown Error Occured, Please contact Administrator";
				return msg;
			}
			
		} catch (Exception e) {
			msg = "Error Occured, Please contact Administrator";
			return msg;
		}

	}
}