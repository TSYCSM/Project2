package com.tsycsm.agileoffice.model.domain;

import lombok.Data;

@Data
public class Owner {
	private int owner_id;
	private String user_id;
	private String password;
	private String shopname;
	private String user_name;
	private String email_id;
	private String email_server;
	private String regdate;
}
