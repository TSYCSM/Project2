package com.tsycsm.agileoffice.controller.owner;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/item")
public class ItemController {
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String getItemList() {
		
		
		return "owner/item/item_list";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String AddItem() {
		
		
		return "owner/item/item_add";
	}
}
