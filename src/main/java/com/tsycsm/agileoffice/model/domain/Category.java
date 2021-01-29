package com.tsycsm.agileoffice.model.domain;

import lombok.Data;

@Data
public class Category {
	private int category_id;
	private int owner_id;
	private String category_name;
	private String regdate;
	private String color;
}
