package com.tsycsm.agileoffice.controller.customer;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tsycsm.agileoffice.common.MessageData;
import com.tsycsm.agileoffice.exception.CustomerException;
import com.tsycsm.agileoffice.exception.CustomerNotFoundException;
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
	//customer등록
	@PostMapping("/main/customerRegist")
	@ResponseBody
	public MessageData customerRegist(Customer customer) {
		logger.debug("customer의 id "+customer.getCustomer_id());
		logger.debug("customer의 owner_id "+customer.getCustomer_id());
		logger.debug("customer의 name"+customer.getCustomer_name());
		logger.debug("customer의 point"+customer.getPoint());
		
		customer.setOwner_id(42);
		
		customerService.regist(customer);
		
		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		messageData.setMsg("고객등록 성공");
		
		return messageData;
	}
	
	@PostMapping("/main/customerLogin")
	public String customerLogin(HttpSession session, String phone) {
		Customer obj = customerService.select(phone);
		session.setAttribute("customer", obj);
		
		return "redirect:/order/main";
		
		
	}
	
	// 예외 핸들러 2가지 처리
	@ExceptionHandler(CustomerException.class)
	@ResponseBody
	public MessageData handleException(CustomerException e) {
		MessageData messageData = new MessageData();
		messageData.setResultCode(0);
		messageData.setMsg(e.getMessage());
		return messageData;
	}
	
	@ExceptionHandler(CustomerNotFoundException.class)
	public ModelAndView handleException(CustomerNotFoundException e) {
		MessageData messageData = new MessageData();
		messageData.setResultCode(0);
		messageData.setMsg(e.getMessage());
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/error/result");
		mav.addObject("messageData", messageData);
		return mav;
	}
		
}













