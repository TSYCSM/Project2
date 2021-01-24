package com.tsycsm.agileoffice.client.controller.review;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tsycsm.agileoffice.exception.AsyncDMLException;
import com.tsycsm.agileoffice.exception.LoginRequiredException;
import com.tsycsm.agileoffice.model.common.MessageData;

@Controller
public class ReviewController {
	
	/****************************
	 exception handler
	 ****************************/
	@ExceptionHandler(AsyncDMLException.class)
	@ResponseBody
	public MessageData handleException(AsyncDMLException e) {
		MessageData messageData = new MessageData();
		messageData.setMsg(e.getMessage());
		messageData.setResultCode(0);
	
		return messageData;
	}
	
	@ExceptionHandler(LoginRequiredException.class)
	@ResponseBody
	public MessageData handleException(LoginRequiredException e) {
		MessageData messageData = new MessageData();
		messageData.setResultCode(0);
		messageData.setMsg(e.getMessage());
		
		return messageData;
	}

}







