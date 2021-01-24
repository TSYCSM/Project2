package com.tsycsm.agileoffice.exception;

public class OrderException extends RuntimeException{
	public OrderException(String msg) {
		super(msg);
	}
	
	public OrderException(String msg, Throwable e) {
		super(msg, e);
	}
}
