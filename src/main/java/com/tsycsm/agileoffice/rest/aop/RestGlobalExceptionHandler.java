package com.tsycsm.agileoffice.rest.aop;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tsycsm.agileoffice.exception.AsyncCustomerDMLException;
import com.tsycsm.agileoffice.exception.AsyncInventoryDMLException;
import com.tsycsm.agileoffice.exception.AsyncInventoryNameDuplicatedException;
import com.tsycsm.agileoffice.exception.AsyncLoginRequiredException;
import com.tsycsm.agileoffice.exception.AsyncOrderDMLException;
import com.tsycsm.agileoffice.exception.AsyncOwnerDMLException;
import com.tsycsm.agileoffice.exception.AsyncOwnerNameDuplicatedException;
import com.tsycsm.agileoffice.exception.AsyncOwnerPasswordFailException;
import com.tsycsm.agileoffice.model.common.MessageData;

@ControllerAdvice
public class RestGlobalExceptionHandler {

	/**************************************
	 
	 Owner 예외 핸들러
	 
	 **************************************/
	
	//Owner, rest id중복체크
	@ExceptionHandler(AsyncOwnerNameDuplicatedException.class)
	@ResponseBody
	public ResponseEntity<MessageData> handleException(AsyncOwnerNameDuplicatedException e) {
		MessageData messageData = new MessageData();
		messageData.setResultCode(0);
		messageData.setMsg(e.getMessage());
			
		ResponseEntity<MessageData> entity = new ResponseEntity<MessageData>(messageData, null, HttpStatus.INTERNAL_SERVER_ERROR);
		
		return entity;
	}
	
	//Owner, rest DMl체크
	@ExceptionHandler(AsyncOwnerDMLException.class)
	@ResponseBody
	public ResponseEntity<MessageData> handleException(AsyncOwnerDMLException e) {
		MessageData messageData = new MessageData();
		messageData.setResultCode(0);
		messageData.setMsg(e.getMessage());
			
		ResponseEntity<MessageData> entity = new ResponseEntity<MessageData>(messageData, null, HttpStatus.INTERNAL_SERVER_ERROR);
		
		return entity;
	}
	
	//Owner 로그인 실패 예외 핸들러
	@ExceptionHandler(AsyncOwnerPasswordFailException.class)
	@ResponseBody
	public ResponseEntity<MessageData> handleException(AsyncOwnerPasswordFailException e) {
		MessageData messageData = new MessageData();
		messageData.setResultCode(0);
		messageData.setMsg(e.getMessage());
		ResponseEntity<MessageData> entity = new ResponseEntity<MessageData>(messageData, null, HttpStatus.INTERNAL_SERVER_ERROR);
		
		return entity;
	}
	
	/**************************************
	 
	 Customer 예외 핸들러
	 
	 **************************************/
	
	//Customer 로그인 필수 예외 핸들러
	@ExceptionHandler(AsyncLoginRequiredException.class)
	@ResponseBody
	public ResponseEntity<MessageData> handleException(AsyncLoginRequiredException e) {
		MessageData messageData = new MessageData();
		messageData.setResultCode(0);
		messageData.setMsg(e.getMessage());
		
		ResponseEntity<MessageData> entity = new ResponseEntity<MessageData>(messageData, null, HttpStatus.INTERNAL_SERVER_ERROR);
		
		return entity;
	}
	
	// Customer, rest 고객등록에 대한 예외 핸들러
	@ExceptionHandler(AsyncCustomerDMLException.class)
	@ResponseBody
	public ResponseEntity<MessageData> handleException(AsyncCustomerDMLException e) {
		MessageData messageData = new MessageData();
		messageData.setResultCode(0);
		messageData.setMsg(e.getMessage());
		
		ResponseEntity<MessageData> entity = new ResponseEntity<MessageData>(messageData, null, HttpStatus.INTERNAL_SERVER_ERROR);
		
		return entity;
	}

	
	/**************************************
	 
	 Inventory 예외 핸들러
	 
	 **************************************/
	
	//inventory, rest DML요청에 대한 예외 핸들러 
	@ExceptionHandler(AsyncInventoryDMLException.class)
	@ResponseBody
	public ResponseEntity<MessageData> handleException(AsyncInventoryDMLException e) {
		MessageData messageData = new MessageData();
		messageData.setMsg(e.getMessage());
		messageData.setResultCode(0);

		ResponseEntity<MessageData> entity = new ResponseEntity<MessageData>(messageData, null, HttpStatus.INTERNAL_SERVER_ERROR);
		
		return entity;
	}

	//inventory, rest id 중복체크 요청에 대한 예외 핸들러 
	@ExceptionHandler(AsyncInventoryNameDuplicatedException.class)
	@ResponseBody
	public ResponseEntity<MessageData> handleException(AsyncInventoryNameDuplicatedException e) {
		MessageData messageData = new MessageData();
		messageData.setMsg(e.getMessage());
		messageData.setResultCode(0);

		ResponseEntity<MessageData> entity = new ResponseEntity<MessageData>(messageData, null, HttpStatus.INTERNAL_SERVER_ERROR);
		
		return entity;
	}
	
	/**************************************
	 
	 Order 예외 핸들러
	 
	 **************************************/
	
	// Order, rest 주문에 대한 예외 핸들러
	@ExceptionHandler(AsyncOrderDMLException.class)
	@ResponseBody
	public ResponseEntity<MessageData> handleException(AsyncOrderDMLException e) {
		MessageData messageData = new MessageData();
		messageData.setResultCode(0);
		messageData.setMsg(e.getMessage());
		ResponseEntity<MessageData> entity = new ResponseEntity<MessageData>(messageData, null, HttpStatus.INTERNAL_SERVER_ERROR);
		
		return entity;
	}
	
	
	

}
