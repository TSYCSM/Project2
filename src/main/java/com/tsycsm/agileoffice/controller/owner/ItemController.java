package com.tsycsm.agileoffice.controller.owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tsycsm.agileoffice.model.domain.Item;
import com.tsycsm.agileoffice.model.item.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;


	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String getItemList() {
		
		return "owner/item/item_list";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String AddItem() {
		
		return "owner/item/item_add";
	}
	
	
	@RequestMapping(value="/regist", method=RequestMethod.POST)
	public String registItem(Item item) {
		System.out.println(item.getCategory_id());
		System.out.println(item.getFilename());
		System.out.println(item.getItem_name());
		System.out.println(item.getRegdate());
		System.out.println(item.getStock());
		System.out.println(item.getItem_id());
		System.out.println(item.getOwner_id());
		System.out.println(item.getPrice());
		itemService.regist(item);
		return "owner/item/item_list";
	}
	
}
