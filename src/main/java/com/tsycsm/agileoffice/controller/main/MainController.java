package com.tsycsm.agileoffice.controller.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tsycsm.agileoffice.common.Pager;

@Controller
public class MainController {
	public static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@RequestMapping(value="/main/first", method=RequestMethod.GET)
	public String viewFirst() {
		return "main/first";
	}
	
	@RequestMapping(value="/main/ownerCredential", method=RequestMethod.GET)
	public String viewSignup() {
		return "main/owner_credential";
	}
	
	@RequestMapping(value="/main/customerCredential", method=RequestMethod.GET)
	public String viewPreOrder(HttpSession session) {
		session.removeAttribute("customer");
		
		return "main/customer_credential";
	}
	
	@RequestMapping(value="/error/result", method=RequestMethod.GET)
	public String viewErrorPage() {
		return "error/result";
	}
	
	@PostMapping("/getPager")
	@ResponseBody
	public Pager getPager(int curPage, int listSize) {
		Pager pager = new Pager();
		pager.init(curPage, listSize);
	
		return pager;
	}
	
	
}









