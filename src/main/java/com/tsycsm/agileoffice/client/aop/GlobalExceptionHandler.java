package com.tsycsm.agileoffice.client.aop;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tsycsm.agileoffice.exception.CustomerNotFoundException;
import com.tsycsm.agileoffice.exception.InventoryDMLException;
import com.tsycsm.agileoffice.exception.AsyncLoginRequiredException;
import com.tsycsm.agileoffice.exception.MailSendException;
import com.tsycsm.agileoffice.exception.OwnerNotFoundException;
import com.tsycsm.agileoffice.model.common.MessageData;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	//owner 로그인 실패시 예외 핸들러
	@ExceptionHandler(OwnerNotFoundException.class)
	public ModelAndView handleException(OwnerNotFoundException e) {
		MessageData messageData = new MessageData();
		messageData.setResultCode(0);
		messageData.setMsg(e.getMessage());
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("error/result");
		mav.addObject("messageData", messageData);
		return mav;
	}
	
	//inventory, non-rest DML요청에 대한 예외 핸들러 
	@ExceptionHandler(InventoryDMLException.class)
	public ModelAndView handleException(InventoryDMLException e) {
		MessageData messageData = new MessageData();
		messageData.setMsg(e.getMessage());
		messageData.setResultCode(0);

		ModelAndView mav = new ModelAndView();
		mav.addObject("messageData", messageData);
		mav.setViewName("/error/result");

		return mav;
	}
	
	// Customer, non-rest 고객id에 대한 예외 핸들러
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
	
	//mail 성공여부에 대한 예외 핸들러
	@ExceptionHandler(MailSendException.class)
	public ModelAndView handleException(MailSendException e) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("error/result");
		mav.addObject("msg", e.getMessage());
		return mav;
	}
}
