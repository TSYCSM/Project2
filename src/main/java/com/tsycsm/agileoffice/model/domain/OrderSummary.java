package com.tsycsm.agileoffice.model.domain;

import java.util.List;

import lombok.Data;

@Data
public class OrderSummary {
	private int order_summary_id;
	private int owner_id;
	private String orderdate;
	private int total_price;
	
	private Customer customer;
	
	private List<OrderDetail> orderDetailList;
}
