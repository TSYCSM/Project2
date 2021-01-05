package com.tsycsm.agileoffice.model.domain;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Item {
	private int item_id;
	private int owner_id;
	private int category_id;
	private String item_name;
	private int price;
	private String filename;
	private int stock;
	private String regdate;

	private MultipartFile repImg;
	
}
