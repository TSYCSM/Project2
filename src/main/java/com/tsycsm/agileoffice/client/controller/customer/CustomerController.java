package com.tsycsm.agileoffice.client.controller.customer;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tsycsm.agileoffice.exception.AsyncCustomerDMLException;
import com.tsycsm.agileoffice.exception.CustomerNotFoundException;
import com.tsycsm.agileoffice.model.common.MessageData;
import com.tsycsm.agileoffice.model.customer.service.CustomerService;
import com.tsycsm.agileoffice.model.domain.Customer;

@Controller
public class CustomerController {
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerService customerService;
	
	/**************************************** 
	  customer CRUD
	 ***************************************/
	//customerµî·Ï
	
	@PostMapping("/main/customerLogin")
	public String customerLogin(HttpSession session, Customer customer) {
		Customer obj = customerService.select(customer);
		session.setAttribute("customer", obj);
		return "redirect:/client/order/main";
	}
	
	
		
}













