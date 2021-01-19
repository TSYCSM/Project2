package com.tsycsm.agileoffice.exception;

public class AdminNotFoundException extends RuntimeException{
	public AdminNotFoundException(String msg) {
		super(msg);
	}
	
	public AdminNotFoundException(String msg, Throwable e) {
		super(msg, e);
	}
}
