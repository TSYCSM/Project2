package com.tsycsm.agileoffice.exception;

public class AsyncInventoryDMLException extends RuntimeException{
	public AsyncInventoryDMLException(String msg) {
		super(msg);
	}
	
	public AsyncInventoryDMLException(String msg, Throwable e) {
		super(msg, e);
	}
}
