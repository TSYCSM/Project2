package com.tsycsm.agileoffice.exception;

public class AsyncBoardDMLException extends RuntimeException {
	
	public AsyncBoardDMLException(String msg) {
		super(msg);
	}
	
	public AsyncBoardDMLException(String msg, Throwable e) {
		super(msg, e);
	}

}
