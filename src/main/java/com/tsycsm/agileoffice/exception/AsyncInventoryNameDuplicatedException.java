package com.tsycsm.agileoffice.exception;

public class AsyncInventoryNameDuplicatedException extends RuntimeException {
	public AsyncInventoryNameDuplicatedException(String msg, Throwable e) {
		super(msg, e);
	}

	public AsyncInventoryNameDuplicatedException(String msg) {
		super(msg);
	}
}
