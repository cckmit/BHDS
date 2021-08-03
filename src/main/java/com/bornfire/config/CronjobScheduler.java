package com.bornfire.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.UIDFolder;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.poi.util.IOUtils;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import com.bornfire.Services.UserProfileDao;
import com.bornfire.entity.BHDS_Client_Issue_Log;
import com.bornfire.entity.BHDS_client_issue_Rep;
import com.bornfire.entity.EMAILREP;
import com.bornfire.entity.EmailAlert;


@Configuration
@Component
@Service
public class CronjobScheduler {
	@Autowired
	EMAILREP emailRep;

	@Autowired
	SessionFactory sessionFactory;

	

	@Autowired
	Environment env;
	
	
	@Autowired
	DataSource srcdataSource;

	@Autowired
	UserProfileDao userProfileDao;
	
	@Autowired
	BHDS_client_issue_Rep bHDS_client_issue_Rep;

	private static final Logger logger = LoggerFactory.getLogger(CronjobScheduler.class);

	//@Scheduled(cron = "0 * * ? * *")
	public void gmail() throws NamingException, MessagingException {
	   String status;
System.out.println("MAIL TESTING STARTS");
	  /* InitialContext ic = new InitialContext();
	   String snName = "java:comp/env/mail/MyMailSession";
	   Session session1 = (javax.mail.Session)ic.lookup(snName);
	  */ 
	   String host = "webmail.bornfire.in";
	   final String user = "baml@bornfire.in";// change accordingly
	   final String password = "baml@MCS";// change accordingly
	   String to = "manivannan.b@bornfire.in";
	   // System.out.println("host " + host + " user " + user + " password " +
	   // password);
	   Properties props = new Properties();

	   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		   	protected PasswordAuthentication getPasswordAuthentication() {
		   		return new PasswordAuthentication(user, password);
		   	}
		   });
	   // String to = "kalaivanan.r@bornfire.in,manivannan.b@bornfire.in";
	   props.put("mail.from", "baml@bornfire.in");
	   Store store = session.getStore();
	   store.connect("webmail.bornfire.in", "baml@bornfire.in", "baml@MCS");
	 /*  Properties props = new Properties();
	   props.put("mail.smtp.auth", "true");*/
	   // props.put("mail.smtp.starttls.enable", "true");
	   props.put("mail.smtp.host", host);
	        // use mail address from HTML form for from address
	   props.put("mail.from", "baml@bornfire.in");
	   Folder folder = store.getFolder("INBOX");
	   Message[] messages = folder.getMessages();
System.out.println("messages"+messages);
	 /* Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	   	protected PasswordAuthentication getPasswordAuthentication() {
	   		return new PasswordAuthentication(user, password);
	   	}
	   });
	   try {
	   	MimeMessage message = new MimeMessage(session);
	   	message.setFrom(new InternetAddress(user));
	   	InternetAddress[] parse = InternetAddress.parse(to, true);
	//
	   	message.setRecipients(javax.mail.Message.RecipientType.TO, parse);
	//
	   	message.setSubject("PARAMETER CHANGED");
	//
	   	String det = "MASTER /MANIVANNAN/BORNFIRE/";
	//
	   	String[] dt1 = det.split("/");
	   	String message1 = "<i>Please find below the alert particulars,</i><br>";
	   	for (String info : dt1) {
	//
	   		message1 += "<b>" + info + "</b><br>";
	//
	   	}
	   	System.out.println(message1);
	   	message.setContent(message1, "text/html");
	   	Transport.send(message);
	//
  	status = "success";
	   	logger.info(status);
	   } catch (Exception e) {
	   	status = "Failure";
	   	logger.info(status);
	   }*/	 
	   System.out.println(messages);
		}

   @Scheduled(cron = "0 * * ? * *")
	public String execute() throws Exception {

		logger.info("EMAIL STARTS");
		String nextPage = "";
		String status;

		int count = emailRep.getEmailSentCount();
		String host = "webmail.bornfire.in";
		final String user = "connect@bornfire.in";// change accordingly
		final String password = "TechOnLine@2021";// change accordingly
		//String host = env.getProperty("mail.host");
		//String user = env.getProperty("mail.username");// change accordingly
		//String password = env.getProperty("mail.password");// change accordingly
		//String port = env.getProperty("mail.port");
		if (count > 0) {

			List<EmailAlert> lst_Objects = emailRep.getEmailDetails();
			if (lst_Objects != null) {
				for (EmailAlert cmnVal : lst_Objects) {

					Properties props = new Properties();
					props.put("mail.smtp.auth", "true");
					// props.put("mail.smtp.starttls.enable", "true");
					props.put("mail.smtp.host", host);
				//	props.put("mail.smtp.port", port);

					Session session = Session.getInstance(props, new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(user, password);
						}
					});
					System.out.println("SEND MAIL...");
					try {
						MimeMessage msg = new MimeMessage(session);
						msg.setFrom(new InternetAddress(user));

						msg.addRecipient(Message.RecipientType.TO, new InternetAddress(cmnVal.getEmail_id()));
						msg.addRecipient(Message.RecipientType.CC, new InternetAddress(cmnVal.getEmail_id_cc1()));

						msg.setSentDate(new Date());
						msg.setSubject(cmnVal.getEmail_sub());
						// msg.setText(cmnVal.getEmail_body());
						/*
						 * StringBuilder sb = new StringBuilder();
						 * //sb.append("Dear Sir,").append(System.lineSeparator());
						 * sb.append("Please find below the alert particulars,").append(System.
						 * lineSeparator());
						 * 
						 * sb.append(cmnVal.getEmail_body());
						 * 
						 * msg.setText(sb.toString());
						 */

						String det = cmnVal.getEmail_body();

						String[] dt1 = det.split("/");
						String message1 = "<i>Hi,</i><br>";
						message1 =message1 + "<i>Greetings,</i><br>";
						for (String info : dt1) {

							message1 += "<b>" + info + "</b><br>";

						}
						System.out.println(message1);
						msg.setContent(message1, "text/html");
						Transport.send(msg);

						nextPage = "success";

					} catch (Exception E) {
						System.out.println("Oops something has gone pearshaped!");
						System.out.println(E);
						nextPage = "error";
					}
					if (nextPage.equals("success")) {
						BigDecimal srl = cmnVal.getEmail_srl_no();
						EmailAlert cv = emailRep.getEmailbySRl(srl);

						cv.setSend_flg("Y");
						cv.setMsg_status("Delivered Successfully");

						emailRep.save(cv);
					}
				}
			}
		}

		return nextPage;

	}
   
   
	@Scheduled(cron = "0 * * ? * *")
   public void OutlookEmail() throws Exception {
	    Properties props = System.getProperties();
	    props.setProperty("mail.store.protocol", "imap");
	    props.setProperty("mail.imap.ssl.enable", "true");
	    props.setProperty("mail.imaps.partialfetch", "false");
	    props.put("mail.mime.base64.ignoreerrors", "true");

	    Session mailSession = Session.getInstance(props);
	    mailSession.setDebug(true);
	    Store store = mailSession.getStore("imap");
		   store.connect("webmail.bornfire.in", "connect@bornfire.in", "TechOnLine@2021");


	    Folder folder = store.getFolder("INBOX");
	    folder.open(Folder.READ_ONLY);
	    UIDFolder uf = (UIDFolder)folder;
       
	    System.out.println("Total Message:" + folder.getMessageCount());
	    System.out.println("Unread Message:" + folder.getUnreadMessageCount());
	    Message messages[] = folder.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));


	    for (Message mail : messages) {  
	    	 long uid = uf.getUID(mail);
	    	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
	    	System.out.println("uid: " +uid);
if(formatter.format(mail.getReceivedDate()).equals(formatter.format(new Date()))) {
	Date dated = formatter.parse(formatter.format(new Date()));
	BigDecimal num = new BigDecimal(0);
	BigDecimal getcount = bHDS_client_issue_Rep.getcount(mail.getSubject(), dated);
    if(getcount.equals(num)) {
	/*            System.out.println("*********************************");
	            System.out.println("MESSAGE : \n");

	            System.out.println("Subject: " + mail.getSubject());
	            System.out.println("From: " + mail.getFrom()[0]);
	            System.out.println("To: " + mail.getAllRecipients()[0]);
	            System.out.println("Date: " + mail.getReceivedDate());
	            System.out.println("Size: " + mail.getSize());
	            System.out.println("Flags: " + mail.getFlags());
	            System.out.println("ContentType: " + mail.getContentType());
	        System.out.println("Body: \n" + getTextFromMessage(mail)+" BODY END");
	        System.out.println("getAttachments :" +getAttachments(mail));*/
	        InputStream is = null;
	        Multipart multipart = (Multipart) mail.getContent();
	        System.out.println("getAttachments : " +multipart.getCount());
	        byte[] bytes_1 = null;
	        byte[] bytes_2 = null;
	        byte[] bytes_3 = null;
	        BodyPart bodyPart =null;
	        for (int i = 0; i < multipart.getCount(); i++) {
	             bodyPart = multipart.getBodyPart(i);
	            if(!Part.ATTACHMENT.equalsIgnoreCase(bodyPart.getDisposition()) &&
	                   StringUtils.isEmpty(bodyPart.getFileName())) {
	                continue; // dealing with attachments only
	            } 
	             is = bodyPart.getInputStream();
	             if(i==1) {
	            	 bytes_1 = IOUtils.toByteArray(is); 
	             }else if(i==2) {
	            	 bytes_2 = IOUtils.toByteArray(is); 
	             }else if(i==3) {
	            	 bytes_3 = IOUtils.toByteArray(is); 
	             }
	             
	        }
	        
	        
	        String contentType = mail.getContentType();
	        String filename_1 = null;
       	 String filename_2 = null;
       	 String filename_3 = null;
	        if (contentType.contains("multipart")) {
	        	Multipart multiPart = (Multipart) mail.getContent();
	        	 
	        	for (int i = 0; i < multiPart.getCount(); i++) {
	        	    MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(i);
	        	    if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
	        	        // this part is attachment
	        	        // code to save attachment...
	        	    	String destFilePath = "C:/FTPUSER/" + part.getFileName();
	        	    	 
	        	    	FileOutputStream output = new FileOutputStream(destFilePath);
	        	    	 
	        	    	InputStream input = part.getInputStream();
	        	    	 
	        	    	byte[] buffer = new byte[4096];
	        	    	 
	        	    	int byteRead;
	        	    	 
	        	    	while ((byteRead = input.read(buffer)) != -1) {
	        	    	    output.write(buffer, 0, byteRead);
	        	    	}
	        	    	output.close();
	        	    }
	        	    if(i==1) {
	        	    	filename_1 = part.getFileName(); 
		             }else if(i==2) {
		            	 filename_2 = part.getFileName();  
		             }else if(i==3) {
		            	 filename_3 = part.getFileName(); 
		             }
	        	}	        }
	            System.out.println("*******************************");  

	           String Issue_srl_no = "1";
	           String Subject= mail.getSubject();
	           String Application=mail.getSubject();
	           String Emailid = mail.getFrom()[0].toString();
	           String Lodgedby=mail.getFrom()[0].toString();
	           String Description = getTextFromMessage(mail);
	           String filename = bodyPart.getFileName();
	           Date Lodged_date=new Date();
	           String Priority= "HIGH";
	           String status ;
	           ;
	           if (filename == null || filename.length() == 0) {
	        	   System.out.println("contentType 1"+bodyPart.getFileName());
	        	  
	           status = userProfileDao.uploaddata(Issue_srl_no,Subject,Application,Emailid,Description,Lodged_date,bytes_1,filename_1,bytes_2,filename_2,bytes_3,filename_3,is);
	          
	           }else {
	        	   System.out.println("contentType 2"+bodyPart.getFileName());
	        	   
		           status = userProfileDao.uploaddata(Issue_srl_no,Subject,Application,Emailid,Description,Lodged_date,bytes_1,filename_1,bytes_2,filename_2,bytes_3,filename_3,is);
	        	   
	           }
	          System.out.println("STATUS :" +status);
	          
}

else {
	
}

   }else {
	   
   }
	    }
	    folder.close();
	    store.close();
	}
	 private String getTextFromMessage(Message message) throws Exception {
		  String result = "";
		  if (message.isMimeType("text/plain")) {
			  logger.info( message.getContentType());
		    result = message.getContent().toString();
		  } else if (message.isMimeType("multipart/*")) {
			  logger.info( message.getContentType());
		    MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
		    result = getTextFromMimeMultipart(mimeMultipart);
		  }else if (message.isMimeType("multipart/mixed")) {
			    Multipart multipart = (Multipart) message.getContent();
			    result = getTextFromMultiPartMixed(multipart);
			    System.out.println("RESULT :"+result);

		  }
		  return result;
		}

		private String getTextFromMimeMultipart(
		    MimeMultipart mimeMultipart) throws Exception{
		  String result = "";
		  int count = mimeMultipart.getCount();
		  for (int i = 0; i < count; i++) {
		    BodyPart bodyPart = mimeMultipart.getBodyPart(i);
		    if (bodyPart.isMimeType("text/plain")) {
		      result = result + "\n" + bodyPart.getContent();
		      break; // without break same text appears twice in my tests
		    } else if (bodyPart.isMimeType("text/html")) {
		      String html = (String) bodyPart.getContent();
		     // result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
		    } else if (bodyPart.getContent() instanceof MimeMultipart){
		      result = result + getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
		    }
		  }
		  return result;
		}
		private String getTextFromMultiPartMixed(Multipart multipart) throws IOException, MessagingException {
			  StringBuilder textBuilder = new StringBuilder();
			  for (int i = 0; i < multipart.getCount(); i++) {
			    BodyPart bodyPart = multipart.getBodyPart(i);
			    if (bodyPart.isMimeType("text/*")) {
			      textBuilder.append((String) bodyPart.getContent());
			    } else if (bodyPart.isMimeType("multipart/*")) {
			    	String text = "";
			    //  String text = getTextFromMessage(bodyPart.toString());
			      if (text != null) {
			        textBuilder.append(text);
			      }
			    }
			  }
			  String text = textBuilder.toString();
			  if (text.isEmpty()) {
			    return null;
			  }
			  return text;
			}
		
		public List<InputStream> getAttachments(Message message) throws Exception {
		    Object content = message.getContent();
		    if (content instanceof String)
		        return null;        

		    if (content instanceof Multipart) {
		        Multipart multipart = (Multipart) content;
		        List<InputStream> result = new ArrayList<InputStream>();

		        for (int i = 0; i < multipart.getCount(); i++) {
		            result.addAll(getAttachments(multipart.getBodyPart(i)));
		        }
		        return result;

		    }
		    return null;
		}

		private List<InputStream> getAttachments(BodyPart part) throws Exception {
		    List<InputStream> result = new ArrayList<InputStream>();
		    Object content = part.getContent();
		    if (content instanceof InputStream || content instanceof String) {
		        if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
		            result.add(part.getInputStream());
		            return result;
		        } else {
		            return new ArrayList<InputStream>();
		        }
		    }

		    if (content instanceof Multipart) {
		            Multipart multipart = (Multipart) content;
		            for (int i = 0; i < multipart.getCount(); i++) {
		                BodyPart bodyPart = multipart.getBodyPart(i);
		                result.addAll(getAttachments(bodyPart));
		            }
		    }
		    return result;
		}
	}
