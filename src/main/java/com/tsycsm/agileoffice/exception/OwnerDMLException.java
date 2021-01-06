package com.tsycsm.agileoffice.exception;

public class OwnerDMLException extends RuntimeException{
	public OwnerDMLException(String msg) {
		super(msg);
	}
	
	public OwnerDMLException(String msg, Throwable e) {
		super(msg, e);
	}
}
