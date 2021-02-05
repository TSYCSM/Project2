package com.tsycsm.agileoffice.client.controller.main;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tsycsm.agileoffice.exception.AsyncOwnerDMLException;
import com.tsycsm.agileoffice.exception.OwnerNotFoundException;
import com.tsycsm.agileoffice.model.common.MessageData;
import com.tsycsm.agileoffice.model.common.Pager;
import com.tsycsm.agileoffice.model.domain.Owner;
import com.tsycsm.agileoffice.model.owner.service.OwnerService;

@Controller
public class MainController {
	public static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	private OwnerService ownerService;
	
	@RequestMapping(value="/main/first", method=RequestMethod.GET)
	public String viewFirst() {
		return "main/first";
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String viewSignup() {
		return "main/owner_credential";
	}

	
	@RequestMapping(value="/error/result", method=RequestMethod.GET)
	public String viewErrorPage() {
		return "error/result";
	}
	

	// ---------------------------------
	// Owner : Credential Box
	// ---------------------------------
	
	
	//·Î±×ÀÎ
	@RequestMapping(value="/main/ownerLogin", method=RequestMethod.POST)
	public String ownerLogin(HttpSession session, Owner owner) {
		Owner obj = ownerService.select(owner);
		session.setAttribute("owner", obj);
		
		return "redirect:/client/main/ownerMain";
	}

	/*****************************************
	 * 
	 *****************************************/
	

	
}









