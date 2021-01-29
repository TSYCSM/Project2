package com.tsycsm.agileoffice.exception;

public class AsyncCustomerDMLException extends RuntimeException{
	public AsyncCustomerDMLException(String msg) {
		super(msg);
	}
	
	public AsyncCustomerDMLException(String msg, Throwable e) {
		super(msg, e);
	}
}
