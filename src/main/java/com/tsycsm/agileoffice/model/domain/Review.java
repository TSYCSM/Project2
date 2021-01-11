package com.tsycsm.agileoffice.model.domain;

import lombok.Data;

@Data
public class Review {
	private int review_id;
	private int owner_id;
	private int customer_id;
	private int item_id;
	private String comments;
	private String regdate;
}
