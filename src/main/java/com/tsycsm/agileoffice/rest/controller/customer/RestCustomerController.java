package com.tsycsm.agileoffice.rest.controller.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tsycsm.agileoffice.client.controller.customer.CustomerController;
import com.tsycsm.agileoffice.model.common.MessageData;
import com.tsycsm.agileoffice.model.customer.service.CustomerService;
import com.tsycsm.agileoffice.model.domain.Customer;

@RestController
public class RestCustomerController {
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerService customerService;
	
	//customer등록
	@PostMapping("/main/customerRegist")
	public ResponseEntity<MessageData> customerRegist(Customer customer) {
		logger.debug("customer의 id "+customer.getCustomer_id());
		logger.debug("customer의 owner_id "+customer.getCustomer_id());
		logger.debug("customer의 name"+customer.getCustomer_name());
		logger.debug("customer의 point"+customer.getPoint());
	
		customerService.regist(customer);
		
		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		messageData.setMsg("고객등록 성공");
		
		ResponseEntity<MessageData> entity = ResponseEntity.ok().body(messageData);
		
		return entity;
	}

}
