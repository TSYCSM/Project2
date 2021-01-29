package com.tsycsm.agileoffice.exception;

public class AsyncOwnerPasswordFailException extends RuntimeException{
	public AsyncOwnerPasswordFailException(String msg) {
		super(msg);
	}
	
	public AsyncOwnerPasswordFailException(String msg, Throwable e) {
		super(msg, e);
	}
}
