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
	 
	 Owner ���� �ڵ鷯
	 
	 **************************************/
	
	//Owner, rest id�ߺ�üũ
	@ExceptionHandler(AsyncOwnerNameDuplicatedException.class)
	@ResponseBody
	public ResponseEntity<MessageData> handleException(AsyncOwnerNameDuplicatedException e) {
		MessageData messageData = new MessageData();
		messageData.setResultCode(0);
		messageData.setMsg(e.getMessage());
			
		ResponseEntity<MessageData> entity = new ResponseEntity<MessageData>(messageData, null, HttpStatus.INTERNAL_SERVER_ERROR);
		
		return entity;
	}
	
	//Owner, rest DMlüũ
	@ExceptionHandler(AsyncOwnerDMLException.class)
	@ResponseBody
	public ResponseEntity<MessageData> handleException(AsyncOwnerDMLException e) {
		MessageData messageData = new MessageData();
		messageData.setResultCode(0);
		messageData.setMsg(e.getMessage());
			
		ResponseEntity<MessageData> entity = new ResponseEntity<MessageData>(messageData, null, HttpStatus.INTERNAL_SERVER_ERROR);
		
		return entity;
	}
	
	//Owner �α��� ���� ���� �ڵ鷯
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
	 
	 Customer ���� �ڵ鷯
	 
	 **************************************/
	
	//Customer �α��� �ʼ� ���� �ڵ鷯
	@ExceptionHandler(AsyncLoginRequiredException.class)
	@ResponseBody
	public ResponseEntity<MessageData> handleException(AsyncLoginRequiredException e) {
		MessageData messageData = new MessageData();
		messageData.setResultCode(0);
		messageData.setMsg(e.getMessage());
		
		ResponseEntity<MessageData> entity = new ResponseEntity<MessageData>(messageData, null, HttpStatus.INTERNAL_SERVER_ERROR);
		
		return entity;
	}
	
	// Customer, rest ����Ͽ� ���� ���� �ڵ鷯
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
	 
	 Inventory ���� �ڵ鷯
	 
	 **************************************/
	
	//inventory, rest DML��û�� ���� ���� �ڵ鷯 
	@ExceptionHandler(AsyncInventoryDMLException.class)
	@ResponseBody
	public ResponseEntity<MessageData> handleException(AsyncInventoryDMLException e) {
		MessageData messageData = new MessageData();
		messageData.setMsg(e.getMessage());
		messageData.setResultCode(0);

		ResponseEntity<MessageData> entity = new ResponseEntity<MessageData>(messageData, null, HttpStatus.INTERNAL_SERVER_ERROR);
		
		return entity;
	}

	//inventory, rest id �ߺ�üũ ��û�� ���� ���� �ڵ鷯 
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
	 
	 Order ���� �ڵ鷯
	 
	 **************************************/
	
	// Order, rest �ֹ��� ���� ���� �ڵ鷯
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
