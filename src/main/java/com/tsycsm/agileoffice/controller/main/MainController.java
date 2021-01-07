package com.tsycsm.agileoffice.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	@RequestMapping(value="/main/first", method=RequestMethod.GET)
	public String viewFirst() {
		return "main/first";
	}
	
	@RequestMapping(value="/main/signup", method=RequestMethod.GET)
	public String viewSignup() {
		return "main/signup";
	}
	
	@RequestMapping(value="/main/preorder", method=RequestMethod.GET)
	public String viewPreOrder() {
		return "main/preorder";
	}
	
	
	
	
}
