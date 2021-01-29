package com.tsycsm.agileoffice.exception;

public class AsyncLoginRequiredException extends RuntimeException{
	public AsyncLoginRequiredException(String msg) {
		super(msg);
	}
	
	public AsyncLoginRequiredException(String msg, Throwable e) {
		super(msg, e);
	}
}
