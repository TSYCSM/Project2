package com.tsycsm.agileoffice.exception;

public class AsyncOwnerDMLException extends RuntimeException{
	public AsyncOwnerDMLException(String msg) {
		super(msg);
	}
	
	public AsyncOwnerDMLException(String msg, Throwable e) {
		super(msg, e);
	}
}
