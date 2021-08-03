package com.bornfire.controller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


import com.bornfire.Services.LoginServices;
import com.bornfire.Services.RefCodeService;
import com.bornfire.Services.ReferenceCodeConfigure;
import com.bornfire.entity.GenRefCodeMast;
import com.bornfire.entity.RefcodeEntity;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@Transactional
public class AMLRestController {
	@Autowired
	ReferenceCodeConfigure referenceCodeConfigure;

	

	
	@Autowired
	LoginServices loginServices;

	
	@Autowired
	RefCodeService refCodeService;

	@Autowired
	SessionFactory sessionFactory;

	@RequestMapping(value = "AMLRefCodeConfig/{refCodeView}", method = RequestMethod.GET)
	public List<GenRefCodeMast> refcode(@PathVariable("refCodeView") String refCodeView, Model md) {

		return referenceCodeConfigure.genRefCodeView(refCodeView);
	}

	
	@RequestMapping(value = "AMLRefCodeConfig/refEdit", method = RequestMethod.POST)
	public int refEdit(@RequestParam String refCode, @RequestParam String refType, @RequestParam String oldSourceCode,
			@RequestParam String newSourceCode, Model md) {

		return referenceCodeConfigure.refEdit(refCode, refType, oldSourceCode, newSourceCode);
	}

	@RequestMapping(value = "AMLRefCodeConfig/refAdd", method = RequestMethod.POST)
	public int refAdd(@RequestParam String refCode, @RequestParam String refType, @RequestParam String SourceCode,
			Model md) {

		return referenceCodeConfigure.refAdd(refCode, refType, SourceCode);
	}

	@RequestMapping(value = "AMLRefCodeConfig/refDelete", method = RequestMethod.POST)
	public int refDelete(@RequestParam String refCode, @RequestParam String refType, @RequestParam String SourceCode,
			Model md) {

		return referenceCodeConfigure.refDelete(refCode, refType, SourceCode);
	}

	
	
	@RequestMapping(value = "getReportCode/{repcode}", method = RequestMethod.GET)
	public String ReportCode(@PathVariable("repcode") String repcode, Model md) {

		return refCodeService.getReportCodedesc(repcode);
	}

	@RequestMapping(value = "getRecordtypeDESC/{repcode}", method = RequestMethod.GET)
	public String RecordTypeDesc(@PathVariable("repcode") String repcode, Model md) {

		return refCodeService.getRecordTypedesc(repcode);
	}

	@RequestMapping(value = "getReferenceCodedescribition/{rectype}/{refcode}", method = RequestMethod.GET)
	public String ReferenceCodeDesc(@PathVariable("rectype") String rectype, @PathVariable("refcode") String refcode,
			Model md) {

		return refCodeService.getReferenceCodedesc(rectype, refcode);
	}

	

	@RequestMapping(value = "getReferenceCode/{rulecode}", method = RequestMethod.GET)
	public List<RefcodeEntity> getReferenceCode(@PathVariable("rulecode") String rulecode, Model md) {

		return refCodeService.getReferenceCodeselect(rulecode);
	}

	@RequestMapping(value = "getReferenceType/{rectype}/{refcode}", method = RequestMethod.GET)
	public String getReferenceType(@PathVariable("rectype") String rectype, @PathVariable("refcode") String refcode,
			Model md) {

		return refCodeService.getReferenceType(rectype, refcode);
	}

	

	/*
	 * @RequestMapping(value="marshall") public void unmarshall() throws
	 * JAXBException, IOException {
	 * 
	 * File xmlFile = ResourceUtils.getFile("classpath:static/xmlfile/sample.XML");
	 * JAXBContext context = JAXBContext.newInstance(Individual.class); Unmarshaller
	 * um = context.createUnmarshaller(); Individual ind = (Individual)
	 * um.unmarshal(new FileReader(xmlFile));
	 * 
	 * System.out.println(ind.toString());
	 * 
	 * List<Nationality> desigList = ind.getNationality();
	 * 
	 * 
	 * int i=0; while (i < desigList.size()) {
	 * System.out.println(desigList.toString()); i++; }
	 * 
	 * }
	 */

	

}
