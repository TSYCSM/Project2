package com.tsycsm.agileoffice.exception;

public class AsyncOrderDMLException extends RuntimeException{
	public AsyncOrderDMLException(String msg) {
		super(msg);
	}
	
	public AsyncOrderDMLException(String msg, Throwable e) {
		super(msg, e);
	}
}
