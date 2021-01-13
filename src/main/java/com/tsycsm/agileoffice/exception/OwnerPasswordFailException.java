package com.tsycsm.agileoffice.exception;

public class OwnerPasswordFailException extends RuntimeException{
	public OwnerPasswordFailException(String msg) {
		super(msg);
	}
	
	public OwnerPasswordFailException(String msg, Throwable e) {
		super(msg, e);
	}
}
