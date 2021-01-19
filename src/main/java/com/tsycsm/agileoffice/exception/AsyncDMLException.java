package com.tsycsm.agileoffice.exception;

public class AsyncDMLException extends RuntimeException{
	public AsyncDMLException(String msg) {
		super(msg);
	}
	
	public AsyncDMLException(String msg, Throwable e) {
		super(msg, e);
	}
}
