package com.tsycsm.agileoffice.model.domain;

import lombok.Data;

@Data
public class OrderDetail {
	private int order_detail_id;
	private int order_summary_id;
	private int item_id;
	private int quantity;
	private int price;
	private String orderdate; 
}
