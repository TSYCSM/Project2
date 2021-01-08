package com.tsycsm.agileoffice.controller.order;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {
	@GetMapping(value="/order/main")
	public String viewOrderMain() {
		
		return "customer/main";
	}
}
