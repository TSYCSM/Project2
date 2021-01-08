package com.tsycsm.agileoffice.exception;

public class CustomerNotFoundException extends RuntimeException{
	public CustomerNotFoundException(String msg) {
		super(msg);
	}
	
	public CustomerNotFoundException(String msg, Throwable e) {
		super(msg, e);
	}
}
