package com.tsycsm.agileoffice.controller.customer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TempController {
	@RequestMapping(value="/main", method=RequestMethod.GET)
	public String viewMain() {
		return "customer/main";
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String viewFirst() {
		return "first";
	}

}