package com.tsycsm.agileoffice.exception;

public class OwnerException extends RuntimeException{
	public OwnerException(String msg) {
		super(msg);
	}
	
	public OwnerException(String msg, Throwable e) {
		super(msg, e);
	}
}