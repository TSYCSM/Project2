package com.tsycsm.agileoffice.exception;

public class OwnerNotFoundException extends RuntimeException{
	public OwnerNotFoundException(String msg) {
		super(msg);
	}
	
	public OwnerNotFoundException(String msg, Throwable e) {
		super(msg, e);
	}
}
