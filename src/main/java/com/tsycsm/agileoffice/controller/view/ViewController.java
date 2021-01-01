package com.tsycsm.agileoffice.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
	@RequestMapping("/test")
	public String viewIndex() {
		System.out.println("view");
		return "client/owner/index";
	}
	
	@RequestMapping("/test/subdir")
	public String viewSub() {
		System.out.println("viewSub");
		return "client/owner/reports/receipts";
	}
}
