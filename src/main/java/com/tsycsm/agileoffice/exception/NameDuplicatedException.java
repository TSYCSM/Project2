package com.tsycsm.agileoffice.exception;

public class NameDuplicatedException extends RuntimeException {
	public NameDuplicatedException(String msg, Throwable e) {
		super(msg, e);
	}

	public NameDuplicatedException(String msg) {
		super(msg);
	}
}
