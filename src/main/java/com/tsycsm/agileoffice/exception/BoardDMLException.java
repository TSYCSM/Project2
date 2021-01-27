package com.tsycsm.agileoffice.exception;

public class BoardDMLException extends RuntimeException {
	
	public BoardDMLException(String msg) {
		super(msg);
	}
	
	public BoardDMLException(String msg, Throwable e) {
		super(msg, e);
	}

}
