package com.tsycsm.agileoffice.rest.controller.main;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tsycsm.agileoffice.model.common.Pager;

@RestController
public class RestMainController {
	
	
	@PostMapping("/getPager")
	public Pager getPager(int curPage, int listSize) {
		Pager pager = new Pager();
		pager.init(curPage, listSize);
	
		return pager;
	}

}
