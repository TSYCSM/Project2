package com.tsycsm.agileoffice.exception;

public class CustomerException extends RuntimeException{
	public CustomerException(String msg) {
		super(msg);
	}
	
	public CustomerException(String msg, Throwable e) {
		super(msg, e);
	}
}
