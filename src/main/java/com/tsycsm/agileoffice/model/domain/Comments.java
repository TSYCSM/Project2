package com.tsycsm.agileoffice.model.domain;

import lombok.Data;

@Data
public class Comments {
	private int comments_id;
	private int owner_id;
	private int board_id;
	private String content;
	private String regdate;
}
