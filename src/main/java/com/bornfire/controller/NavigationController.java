package com.bornfire.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bornfire.Services.AlertManagementServices;
import com.bornfire.Services.BHDSIssueRegServices;
import com.bornfire.Services.ProjectMasterServices;
import com.bornfire.Services.RefCodeService;
import com.bornfire.Services.ReferenceCodeConfigure;
import com.bornfire.Services.TroubleShootingServices;
import com.bornfire.Services.UserProfileDao;
import com.bornfire.entity.AlertManagementEntity;
import com.bornfire.entity.AlertManagementRepository;
import com.bornfire.entity.BHDSIssueMaster;
import com.bornfire.entity.BHDS_AMC_Reg;
import com.bornfire.entity.BHDS_Client_Issue_Log;
import com.bornfire.entity.BHDS_Notification_Master_Rep;
import com.bornfire.entity.BHDS_Notification_master;
import com.bornfire.entity.BHDS_Project_Master_Log;
import com.bornfire.entity.BHDS_Project_Master_rep;
import com.bornfire.entity.BHDS_client_issue_Rep;
import com.bornfire.entity.IssueMasterRep;
import com.bornfire.entity.RecordTypeRepository;
import com.bornfire.entity.RefCodeMasterEmbeddedID;
import com.bornfire.entity.RefCodeRepository;
import com.bornfire.entity.RefcodeEntity;
import com.bornfire.entity.Trouble_shooting_Log;
import com.bornfire.entity.Trouble_shooting_Reg;
import com.bornfire.entity.UserProfile;

import ch.qos.logback.core.net.SyslogOutputStream;

@Controller
@ConfigurationProperties("default")

public class NavigationController {

	@Autowired
	UserProfileDao userProfileDao;
	@Autowired
	BHDSIssueRegServices bhdsIssueRegServices;
	@Autowired
	IssueMasterRep issueMasterRep;
	@Autowired
	BHDS_client_issue_Rep clientIssueMasterRep;
	@Autowired
	private AlertManagementRepository alertmanagementrepository;
	@Autowired
	ReferenceCodeConfigure referenceCodeConfigure;
	@Autowired
	private RefCodeRepository refCodeRepository;
	@Autowired
	RefCodeService refCodeService;
	@Autowired
	private RecordTypeRepository recordTypeRepository;
	@Autowired
	AlertManagementServices alertservices;
	@Autowired
	BHDSIssueRegServices bhdsIssueService;
	@Autowired
	BHDS_Project_Master_rep projectMasterrep;
	@Autowired
	ProjectMasterServices projectMasterServices;
	@Autowired
	BHDS_Notification_Master_Rep notification_Master_Rep;
	@Autowired
	BHDS_AMC_Reg amcMasterrep;
	
	@Autowired
	Trouble_shooting_Log troubleShootinglog;
	
	@Autowired
	TroubleShootingServices troubleShootingService;
	
	
	private String pagesize;

	public String getPagesize() {
		return pagesize;
	}

	public void setPagesize(String pagesize) {
		this.pagesize = pagesize;
	}

	@RequestMapping("/logout")
	public String index8() {
		return "BHDSStart.html";
	}
	


	/*
	 * @RequestMapping("/issueentry") public String index25() { return
	 * "BHDSissueentry.html"; }
	 */

	/*
	 * @RequestMapping("/AMLRefCodeConfig") public String index9() { return
	 * "AMLRefCodeConfig.html";}
	 */

	@RequestMapping(value = "Dashboard", method = { RequestMethod.GET, RequestMethod.POST })
	public String dashboard(Model md, HttpServletRequest req) {

		String domainid = (String) req.getSession().getAttribute("DOMAINID");
		String userid = (String) req.getSession().getAttribute("USERID");

		md.addAttribute("changepassword", userProfileDao.checkPasswordChangeReq(userid));
		md.addAttribute("checkpassExpiry", userProfileDao.checkpassexpirty(userid));
		md.addAttribute("checkAcctExpiry", userProfileDao.checkAcctexpirty(userid));
		int completed = 0;
		int uncompleted = 0;

		/* List<ReportTitle> ls = reportServices.getDashBoardRepList(domainid); */

		// md.addAttribute("reportList", ls);
		md.addAttribute("completed", completed);
		md.addAttribute("uncompleted", uncompleted);
		md.addAttribute("menu", "Dashboard");
		return "AMLDashboard";
	}

	/*************************************
	 * Dashboard ends
	 ****************************************/

	/*************************************
	 * Admin ---> UserProfile ---> User creation starts
	 ****************************************/
	@RequestMapping(value = "UserProfile", method = { RequestMethod.GET, RequestMethod.POST })
	public String userprofile(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid,
			@RequestParam(value = "page", required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		String loginuserid = (String) req.getSession().getAttribute("USERID");
		// Logging Navigation
		// loginServices.SessionLogging("USERPROFILE", "M2", req.getSession().getId(),
		// loginuserid, req.getRemoteAddr(),
		// "ACTIVE");

		md.addAttribute("menu", "UserProfile"); // To highlight the menu

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"
			md.addAttribute("userProfiles", userProfileDao.getUsersList());

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);
			// md.addAttribute("domains", userProfileDao.getDomainList());
			md.addAttribute("userProfile", userProfileDao.getUser(userid));

		} else {

			md.addAttribute("formmode", formmode);
			// md.addAttribute("domains", reportServices.getDomainList());
			// md.addAttribute("FinUserProfiles", userProfileDao.getFinUsersList());
			md.addAttribute("userProfile", userProfileDao.getUser(""));

		}

		return "AMLUserprofile";
	}

	@RequestMapping(value = "createUser", method = RequestMethod.POST)
	@ResponseBody
	public String createuser(@RequestParam("formmode") String formmode, @ModelAttribute UserProfile userform, Model md,
			HttpServletRequest rq) {
		System.out.println("createUser");
		/* String userid = (String) rq.getSession().getAttribute("USERID"); */

		String msg = userProfileDao.addUser(userform, formmode);
		md.addAttribute("menu", "UserProfile"); // To highlight the menu

		return msg;

	}

	@RequestMapping(value = "verifyUser", method = RequestMethod.POST)
	@ResponseBody
	public String verifyUser(@ModelAttribute UserProfile userprofile, Model md, HttpServletRequest rq) {
		String userid = (String) rq.getSession().getAttribute("USERID");
		String msg = userProfileDao.verifyUser(userprofile, userid);
		md.addAttribute("menu", "UserProfile"); // To highlight the menu

		return msg;

	}

	@RequestMapping(value = "passwordReset", method = RequestMethod.POST)
	@ResponseBody
	public String passwordReset(@ModelAttribute UserProfile userprofile, Model md, HttpServletRequest rq) {
		String userid = (String) rq.getSession().getAttribute("USERID");
		String msg = userProfileDao.passwordReset(userprofile, userid);
		md.addAttribute("menu", "UserProfile"); // To highlight the menu

		return msg;

	}
	@RequestMapping(value = "deleteuser", method = RequestMethod.POST)
	@ResponseBody
	public String deleteuser(@ModelAttribute UserProfile userprofile, Model md, HttpServletRequest rq) {
		String userid = (String) rq.getSession().getAttribute("USERID");
		String msg = userProfileDao.DeleteUser(userprofile, userid);
		
		md.addAttribute("menu", "UserProfile"); // To highlight the menu

		return msg;

	}
	/*************************************
	 * ClientLogIsssue Starts
	 ****************************************/

	@RequestMapping(value = "BHDSIssueLogReg", method = { RequestMethod.GET, RequestMethod.POST })
	public String BHDSIssueLogReg(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("menu", "BHDSMenu");
			md.addAttribute("menuname", "BHDS Issue Log Register");
			md.addAttribute("IssueMaster", issueMasterRep.findAllCustom());
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"
			/*
			 * md.addAttribute("RuleLists",
			 * ruleenginerepository.findAll(PageRequest.of(currentPage, pageSize)));
			 */
			System.out.println("AlertList");

		} else if (formmode.equals("add")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("formmode", "add"); // to set which form - valid values are "edit" , "add" & "list"
			
			md.addAttribute("srlno", bhdsIssueService.getSrlNoValue1());
			md.addAttribute("menu", "BHDSMenu");
			md.addAttribute("menuname1", "Issue Log Register- Add");
			// md.addAttribute("AlertSrlNo", alertservices.getSrlNoValue());
		}

		return "BHDSIssueLogReg";
	}

	@RequestMapping(value = "addIssue", method = RequestMethod.POST)
	@ResponseBody
	public String addIssue(@RequestParam("formmode") String formmode, @RequestParam(required = false) String refcode,
			@RequestParam(required = false) String reportcode, @RequestParam(required = false) String recordtype,
			@ModelAttribute BHDSIssueMaster IssueMaster, Model md, HttpServletRequest rq) {

		String msg = bhdsIssueService.BHDSIssueLogReg(IssueMaster, formmode);

		md.addAttribute("menu", "ReferenceCodeMaster");

		return msg;

	}

	/*************************************
	 * ClientLogIsssue Starts
	 ****************************************/

	@RequestMapping(value = "BHDSClientIssueLog", method = { RequestMethod.GET, RequestMethod.POST })
	public String BHDSClientIssueLog(@RequestParam(required = false) String formmode,
			@RequestParam(value="srlno",required = false) String issue_srl_no, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("menu", "BHDSMenu");
			md.addAttribute("menuname", "Client log Register");
			md.addAttribute("IssueMaster", clientIssueMasterRep.findAllCustom());
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"
			/*
			 * md.addAttribute("RuleLists",
			 * ruleenginerepository.findAll(PageRequest.of(currentPage, pageSize)));
			 */
			System.out.println("AlertList");

		} else if (formmode.equals("add")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("formmode", "add"); // to set which form - valid values are "edit" , "add" & "list"
			md.addAttribute("menu", "BHDSMenu");
			md.addAttribute("menuname1", "Client log Register - Add");
			md.addAttribute("CLIENTSRL", bhdsIssueRegServices.getSrlNoValue());
			/// md.addAttribute("AlertSrlNo", alertservices.getSrlNoValue());
		}else if (formmode.equals("edit")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("formmode", "edit"); // to set which form - valid values are "edit" , "add" & "list"
			md.addAttribute("menu", "BHDSMenu");
			md.addAttribute("menuname1", "Client log Register - Edit");
			md.addAttribute("ReviewNO",clientIssueMasterRep.getNO(issue_srl_no));
			BHDS_Client_Issue_Log un=clientIssueMasterRep.findBySrl(issue_srl_no);
						
			md.addAttribute("IssueMaster", un);

			System.out.println("formmode->"+un.getClient());

			System.out.println("formmode->"+formmode);
			md.addAttribute("CLIENTSRL", un.getIssue_srl_no());
			// md.addAttribute("AlertSrlNo", alertservices.getSrlNoValue());
		}else if (formmode.equals("view")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("formmode", "view"); // to set which form - valid values are "edit" , "add" & "list"
			md.addAttribute("menu", "BHDSMenu");
			md.addAttribute("menuname1", "Client log Register - view");
			md.addAttribute("ReviewNO",clientIssueMasterRep.getNO(issue_srl_no));
			
			System.out.println("ReviewNO"+clientIssueMasterRep.getNO(issue_srl_no));
			md.addAttribute("CLIENTSRL", bhdsIssueRegServices.getSrlNoValue1());
			md.addAttribute("IssueMaster", clientIssueMasterRep.findBySrl(issue_srl_no));
			/// md.addAttribute("AlertSrlNo", alertservices.getSrlNoValue());
			//md.addAttribute("IssueMaster", alertservices.getSrlno(issue_srl_no));
		}
		else if (formmode.equals("accept")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("formmode", "accept"); // to set which form - valid values are "edit" , "add" & "list"
			md.addAttribute("menu", "BHDSMenu");
			md.addAttribute("menuname1", "Client log Register - Edit");
			md.addAttribute("ReviewNO",clientIssueMasterRep.getNO(issue_srl_no));
			BHDS_Client_Issue_Log un=clientIssueMasterRep.findBySrl(issue_srl_no);
						
			md.addAttribute("IssueMaster", un);

			System.out.println("formmode->"+un.getClient());

			System.out.println("formmode->"+formmode);
			md.addAttribute("CLIENTSRL", un.getIssue_srl_no());
			// md.addAttribute("AlertSrlNo", alertservices.getSrlNoValue());
		}
		else if (formmode.equals("reopen")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("formmode", "reopen"); // to set which form - valid values are "edit" , "add" & "list"
			md.addAttribute("menu", "BHDSMenu");
			md.addAttribute("menuname1", "Client log Register - Edit");
			md.addAttribute("ReviewNO",clientIssueMasterRep.getNO(issue_srl_no));
			BHDS_Client_Issue_Log un=clientIssueMasterRep.findBySrl(issue_srl_no);
						
			md.addAttribute("IssueMaster", un);

			System.out.println("formmode->"+un.getClient());

			System.out.println("formmode->"+formmode);
			md.addAttribute("CLIENTSRL", un.getIssue_srl_no());
			// md.addAttribute("AlertSrlNo", alertservices.getSrlNoValue());
		}
		return "BHDSClientIssueLog";
	}

	/*************************************
	 * IsssueMaster Starts
	 ****************************************/
	@RequestMapping(value = "BHDSIssueMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String issueMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {
		md.addAttribute("IssueMaster", issueMasterRep.findAllCustom());

		md.addAttribute("menu", "BHDSMenu");
		return "BHDSIssueMaster";
	}

	/*************************************
	 * Admin ---> NotificationList ---> NotificationList Starts
	 ****************************************/
	@RequestMapping(value = "BHDSNotificationList", method = { RequestMethod.GET, RequestMethod.POST })
	public String AMLAlertManagement(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String srlno, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		System.out.println("page" + currentPage);
		System.out.println("page" + pageSize);
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("menu", "BHDSMenu");
			md.addAttribute("menuname", "BHDS Notification Master");
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"
			/*
			 * md.addAttribute("RuleLists",
			 * ruleenginerepository.findAll(PageRequest.of(currentPage, pageSize)));
			 */
			System.out.println("AlertList");
			md.addAttribute("AlertParameterList",
					notification_Master_Rep.alertlist(PageRequest.of(currentPage, pageSize)));
		} else if (formmode.equals("add")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("AlertSrlNo", alertservices.getSrlNoValue());
			md.addAttribute("menuname1", "Notification Master - Add");
			md.addAttribute("formmode", "add"); // to set which form - valid values are "edit" , "add" & "list"
			md.addAttribute("menu", "BHDSMenu");
		}else if (formmode.equals("edit")) {
			System.out.println("editrule");
			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "Notification Master - Modify");

			// md.addAttribute("domains", userProfileDao.getDomainList());
			md.addAttribute("AlertParameter", alertservices.getSrlNo(srlno));

		} else if (formmode.equals("view")) {
			System.out.println("viewrule");
			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname1", "Notification Master - Inquiry");

			md.addAttribute("AlertParameter", alertservices.getSrlNo(srlno));
		}
		
		return "BHDSNotificationList";
	}

	@RequestMapping(value = "createAlert", method = RequestMethod.POST)
	@ResponseBody
	public String createRule(@RequestParam("formmode") String formmode,
			@ModelAttribute BHDS_Notification_master notification_master, Model md, HttpServletRequest rq) {
		System.out.println("createalert");
		/* String userid = (String) rq.getSession().getAttribute("USERID"); */

		String msg = alertservices.addAlert(notification_master, formmode);

		return msg;

	}

	/*************************************
	 * Admin ---> NotificationList---> NotificationList ends
	 ****************************************/

	/*************************************
	 * Admin ---> REFERENCE CODE MASTER starts
	 ****************************************/

	@RequestMapping(value = "ReferenceCodeMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String ReferenceCodeMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String refcode, @RequestParam(required = false) String reportcode,
			@RequestParam(required = false) String recordtype, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(Integer.parseInt(pagesize));

		System.out.println("page" + currentPage);
		System.out.println("page" + pageSize);
		String roleId = (String) req.getSession().getAttribute("ROLEID");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("menuname", "REFERENCE CODE MASTER - List ");
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"

			//md.addAttribute("RefCodemasterList", refCodeRepository.refcodelist(PageRequest.of(currentPage, pageSize)));

		} else if (formmode.equals("add")) {
			md.addAttribute("formmode", formmode);
			//md.addAttribute("RECORDTYPELIST", recordTypeRepository.findAllCustom());
			//req.getSession().getAttribute("USERID");
			md.addAttribute("menuname1", "REFERENCE CODE MASTER - Add");
			//md.addAttribute("refCodeId", new RefCodeMasterEmbeddedID());

		} else if (formmode.equals("edit")) {
			System.out.println("edicxctrule" + refcode);
			md.addAttribute("formmode", formmode);
			md.addAttribute("RECORDTYPELIST", recordTypeRepository.findAllCustom());
			/* md.addAttribute("userProfile", userProfileDao.getUser(userid)); */
			md.addAttribute("menuname1", "REFERENCE CODE MASTER - Modify");
			md.addAttribute("RefCodeMaster", new RefcodeEntity());
			md.addAttribute("refCodeId", new RefCodeMasterEmbeddedID());
			md.addAttribute("RefCodeMaster", refCodeService.getRefcode(refcode, recordtype, reportcode));
			System.out.println(refCodeService.getRefcode(refcode, recordtype, reportcode).toString());

		} else if (formmode.equals("verify")) {
			System.out.println("verifyrule");
			md.addAttribute("formmode", formmode);
			md.addAttribute("RefCodeMaster", new RefcodeEntity());
			md.addAttribute("refCodeId", new RefCodeMasterEmbeddedID());
			md.addAttribute("RefCodeMaster", refCodeService.getRefcode(refcode, recordtype, reportcode));
			md.addAttribute("menuname1", "REFERENCE CODE MASTER - Verify");

		}

		else if (formmode.equals("view")) {
			System.out.println("viewrule1");
			md.addAttribute("formmode", formmode);
			md.addAttribute("RefCodeMaster", new RefcodeEntity());
			md.addAttribute("refCodeId", new RefCodeMasterEmbeddedID());
			md.addAttribute("menuname1", "REFERENCE CODE MASTER - Inquiry");
			md.addAttribute("RefCodeMaster", refCodeService.getRefcode(refcode, recordtype, reportcode));

		} else if (formmode.equals("delete")) {
			System.out.println("deleteParameter");
			md.addAttribute("formmode", "list");
			md.addAttribute("menuname1", "REFERENCE CODE MASTER - Delete");
			String msg = refCodeService.deleteParameter(refcode, recordtype, reportcode);
			md.addAttribute("RefCodemasterList", refCodeRepository.refcodelist(PageRequest.of(currentPage, pageSize)));
			/* md.addAttribute("RefCodeMaster", refCodeService.getRefcode(refcode)); */

		}
		md.addAttribute("adminflag", "adminflag");
		md.addAttribute("menu", "ReferenceCodeMaster");

		return "BHDSReferenceCodeMaster";
	}

	@RequestMapping(value = "createReferenceCodeMaster", method = RequestMethod.POST)
	@ResponseBody
	public String createCodeMaster(@RequestParam("formmode") String formmode,
			@RequestParam(required = false) String refcode, @RequestParam(required = false) String reportcode,
			@RequestParam(required = false) String recordtype, @ModelAttribute RefcodeEntity alertparam, Model md,
			HttpServletRequest rq) {
		System.out.println("createCodeMaster");
		System.out.println(recordtype + " " + refcode + " " + alertparam);

		String msg = refCodeService.addPARAMETER(alertparam, formmode, refcode, recordtype, reportcode);
		md.addAttribute("adminflag", "adminflag");
		md.addAttribute("menu", "ReferenceCodeMaster");

		return msg;

	}

	/***************************************************************************************************/
	/*************************************
	 * Admin ---> REFERENCE CODE MASTER ends
	 ****************************************/

	@RequestMapping(value = "createClientIssue", method = RequestMethod.POST)
	@ResponseBody
	public String createClientIssue(@RequestParam("formmode") String formmode,
			@ModelAttribute BHDS_Client_Issue_Log bhds_Client_Issue_Log, Model md, HttpServletRequest rq,
			@RequestParam(value = "file1",required = false) MultipartFile file1,@RequestParam (value = "file2",required = false)MultipartFile file2,
			@RequestParam(value = "file3",required = false) MultipartFile file3) throws IOException {

		String fileName ="";
		
		if(file1!=null) {
			byte[] byteArr1 = file1.getBytes();
			bhds_Client_Issue_Log.setAttachment_1(byteArr1);
			fileName=file1.getOriginalFilename();
			bhds_Client_Issue_Log.setAttachment_1path("BHDSFileManager/"+fileName);

		}
		
		if(file2!=null) {
			byte[] byteArr2 = file2.getBytes();
			bhds_Client_Issue_Log.setAttachment_2(byteArr2);
		}
		
		if(file3!=null) {
			byte[] byteArr3 = file3.getBytes();
			bhds_Client_Issue_Log.setAttachment_3(byteArr3);
		}
		
		
		System.out.println("Output---->");

		String msg = bhdsIssueService.addPARAMETER(bhds_Client_Issue_Log, formmode);
		// md.addAttribute("adminflag", "adminflag");
		// md.addAttribute("menu", "ReferenceCodeMaster");

		return msg;

	}
	
	
	@RequestMapping(value = "modifyClientIssue", method = RequestMethod.POST)
	@ResponseBody
	public String modifyClientIssue(@RequestParam("formmode") String formmode,
			@ModelAttribute BHDS_Client_Issue_Log bhds_Client_Issue_Log, Model md, HttpServletRequest rq,
			@RequestParam(value = "file1",required = false) MultipartFile file1,@RequestParam (value = "file2",required = false)MultipartFile file2,
			@RequestParam(value = "file3",required = false) MultipartFile file3) throws IOException {

		String fileName ="";
		if(file1!=null) {
			byte[] byteArr1 = file1.getBytes();
			bhds_Client_Issue_Log.setAttachment_1(byteArr1);
			fileName=file1.getOriginalFilename();
			bhds_Client_Issue_Log.setAttachment_1path("BHDSFileManager/"+fileName);

		}
			
//			 PDDocument document = PDDocument.load(new File(PDFFILE));
//             PDPage pd;
//
//             PDFRenderer pdfRenderer = new PDFRenderer(document);
//             for (int page = 0; page < document.getNumberOfPages(); ++page)
//             {
//
//
//               pd = document.getPage(page);
//               pd.setCropBox(new PDRectangle(100, 100,100,100));
//               BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
//               ImageIOUtil.writeImage(bim, outputpath + (page+1) + ".png", 300);
		/*
		 * byte[] byteArr1 = file1.getBytes();
		 * bhds_Client_Issue_Log.setAttachment_1(byteArr1);
		 * 
		 * 
		 * 
		 * 
		 * }
		 */
		
		if(file2!=null) {
			byte[] byteArr2 = file2.getBytes();
			bhds_Client_Issue_Log.setAttachment_2(byteArr2);
		}
		
		if(file3!=null) {
			byte[] byteArr3 = file3.getBytes();
			bhds_Client_Issue_Log.setAttachment_3(byteArr3);
		}
		
		

		String msg = bhdsIssueService.editParameter(bhds_Client_Issue_Log, formmode);
		// md.addAttribute("adminflag", "adminflag");
		// md.addAttribute("menu", "ReferenceCodeMaster");

		return msg;

	}

	
	@RequestMapping(value = "getBlobImage/{ref_no}/{fileType}", method = RequestMethod.GET)
	@ResponseBody
	public String BlobImage(@PathVariable("ref_no") String issue_srl_no,@PathVariable("fileType") String fileType, Model md) {
		System.out.println("Get Blob Image"+issue_srl_no);
		
		BHDS_Client_Issue_Log bHDS_Client_Issue_Log = clientIssueMasterRep.findBySrl(issue_srl_no);
		
		byte[] file=null;
		if(fileType.equals("1")) {
			
			/*
			 * try{ // session = getSession(); BHDS_Client_Issue_Log project =
			 * clientIssueMasterRep.findBySrl(issue_srl_no); // read the file name or the
			 * full path String fileName=project.getFile_type();
			 * 
			 * // read the content of the file byte[] fileContent =
			 * project.getAttachment_1(); System.out.println("fileContent"+fileContent);
			 * //compute the output file path (combine directory and file name) Path path =
			 * Paths.get("C:\\Users\\vishn\\OneDrive\\Documents\\R"+fileName); // this path
			 * was missing - fill the file with content Files.write(path,fileContent);
			 * System.out.println("Downloaded Content:"+Files.write(path,fileContent));
			 * System.out.println("file created succefully"); } catch(IOException e){
			 * System.out.println(e.getMessage()); }
			 */
			
			
			file=bHDS_Client_Issue_Log.getAttachment_1();
		}else if(fileType.equals("2")) {
			 file=bHDS_Client_Issue_Log.getAttachment_2();
		}else if(fileType.equals("3")) {
			 file=bHDS_Client_Issue_Log.getAttachment_3();

		}
		return Base64.getEncoder().encodeToString(file);
	}
	@RequestMapping(value = "Projectmaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String projectmaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid,@RequestParam(required = false) String srl_no,
			@RequestParam(value = "page", required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		//String loginuserid = (String) req.getSession().getAttribute("USERID");
		// Logging Navigation
		// loginServices.SessionLogging("USERPROFILE", "M2", req.getSession().getId(),
		// loginuserid, req.getRemoteAddr(),
		// "ACTIVE");

		md.addAttribute("menu", "projectmaster"); // To highlight the menu

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"
			md.addAttribute("IssueMaster", projectMasterrep.findAllCustom());

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);
		//	md.addAttribute("formmode", "add"); // to set which form - valid values are "edit" , "add" & "list"
			md.addAttribute("menuname", "Project Master Edit : ");
			md.addAttribute("AlertSrlNo", srl_no);
			md.addAttribute("projectmaster", projectMasterrep.findBySrl(srl_no));
			// md.addAttribute("domains", userProfileDao.getDomainList());
			//md.addAttribute("projectmaster", userProfileDao.getUser(userid));

		}else if (formmode.equals("view")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname", "Project Master View : ");
			md.addAttribute("AlertSrlNo", srl_no);
		//	md.addAttribute("CLIENTSRL", bhdsIssueRegServices.getSrlNoValue());
			System.out.println("srl_no :"+srl_no);
			md.addAttribute("projectmaster", projectMasterrep.findBySrl(srl_no));
		} 
		else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname", "Project Master Add : ");
			md.addAttribute("AlertSrlNo", projectMasterServices.getSrlNoValue());
			md.addAttribute("projectmaster", new BHDS_Project_Master_Log());
			// md.addAttribute("domains", userProfileDao.getDomainList());
			//md.addAttribute("projectmaster", userProfileDao.getUser(userid));

		}
		else {

			md.addAttribute("formmode", formmode);
			// md.addAttribute("domains", reportServices.getDomainList());
			// md.addAttribute("FinUserProfiles", userProfileDao.getFinUsersList());
			//md.addAttribute("projectmaster", userProfileDao.getUser(""));

		}

		return "ProjectMaster";
	}
	
	@RequestMapping(value = "createProjectMaster", method = RequestMethod.POST)
	@ResponseBody
	public String createProjectMaster(@RequestParam("formmode") String formmode,
			@RequestParam(required = false) String refcode, @RequestParam(required = false) String reportcode,
			@RequestParam(required = false) String recordtype, @ModelAttribute BHDS_Project_Master_Log alertproject, Model md,
			HttpServletRequest rq) {
		System.out.println("createCodeMaster");
		System.out.println(recordtype + " " + refcode + " " + alertproject);

		String msg = projectMasterServices.BHDSProjectReg(alertproject, formmode);
		md.addAttribute("adminflag", "adminflag");
		md.addAttribute("menu", "ReferenceCodeMaster");

		return msg;

	}
	
	
	@RequestMapping(value = "Troubleshooting", method = { RequestMethod.GET, RequestMethod.POST })
	public String Troubleshoot(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid,
			@RequestParam(value = "page", required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		String loginuserid = (String) req.getSession().getAttribute("USERID");
		// Logging Navigation
		// loginServices.SessionLogging("USERPROFILE", "M2", req.getSession().getId(),
		// loginuserid, req.getRemoteAddr(),
		// "ACTIVE");

		md.addAttribute("menu", "Troubleshooting"); // To highlight the menu

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"
			md.addAttribute("IssueMaster", troubleShootinglog.findAllCustom());

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);
			// md.addAttribute("domains", userProfileDao.getDomainList());
			md.addAttribute("Troubleshooting", userProfileDao.getUser(userid));
			md.addAttribute("menuname", "Trouble Shooting Register Edit");

		}
		 else if (formmode.equals("add")) {

				md.addAttribute("formmode", formmode);
				md.addAttribute("menuname", "Trouble Shooting Register Add");
				//md.addAttribute("AlertSrlNo", TroubleShootingServices.getSrlNoValue());

				// md.addAttribute("domains", userProfileDao.getDomainList());
				//md.addAttribute("projectmaster", userProfileDao.getUser(userid));

			}else {

			md.addAttribute("formmode", formmode);
			md.addAttribute("menuname", "Trouble Shooting Register");
			// md.addAttribute("domains", reportServices.getDomainList());
			// md.addAttribute("FinUserProfiles", userProfileDao.getFinUsersList());
			md.addAttribute("Troubleshooting", userProfileDao.getUser(""));

		}

		return "TroubleShooting";
	}
	
	@RequestMapping(value = "CreateTroubleShootingMaster", method = RequestMethod.POST)
	@ResponseBody
	public String CreateTroubleShootingMaster(@RequestParam("formmode") String formmode,@RequestParam("srl_no") String srl_no,
			 @ModelAttribute Trouble_shooting_Reg alertproject, Model md,
			HttpServletRequest rq) {
		System.out.println("createCodeMaster");
				String msg = troubleShootingService.TroubleShootReg(alertproject, formmode);
		md.addAttribute("adminflag", "adminflag");
		return msg;

	}



}
