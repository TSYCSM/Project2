package com.tsycsm.agileoffice.exception;

public class InventoryDMLException extends RuntimeException{
	public InventoryDMLException(String msg) {
		super(msg);
	}
	
	public InventoryDMLException(String msg, Throwable e) {
		super(msg, e);
	}
}
