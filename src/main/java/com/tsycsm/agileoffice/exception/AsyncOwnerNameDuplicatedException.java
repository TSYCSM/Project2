package com.tsycsm.agileoffice.exception;

public class AsyncOwnerNameDuplicatedException extends RuntimeException{
	public AsyncOwnerNameDuplicatedException(String msg) {
		super(msg);
	}
	
	public AsyncOwnerNameDuplicatedException(String msg, Throwable e) {
		super(msg, e);
	}
}
