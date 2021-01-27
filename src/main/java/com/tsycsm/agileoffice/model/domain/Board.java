package com.tsycsm.agileoffice.model.domain;

import java.util.List;

import org.apache.poi.xssf.model.Comments;

import lombok.Data;

@Data
public class Board {
	private int board_id;
	private int owner_id;
	private String writer;
	private String title;
	private String content;
	private String regdate;
	private int hit;
}
