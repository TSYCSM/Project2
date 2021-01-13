package com.tsycsm.agileoffice.model.domain;

import java.util.List;

import lombok.Data;

@Data
public class Customer {
	private int customer_id;
	private int owner_id;
	private String customer_name;
	private String phone;
	private int point;
	private String regdate;
	
	private List<OrderSummary> orderSummaryList;
}
