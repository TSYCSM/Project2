package com.tsycsm.agileoffice.controller.owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.tsycsm.agileoffice.model.category.service.CategoryService;
import com.tsycsm.agileoffice.model.domain.Category;

import com.tsycsm.agileoffice.model.domain.Item;
import com.tsycsm.agileoffice.model.item.service.ItemService;


@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private CategoryService categoryService; 
	


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
	

	//category CRUD
	@RequestMapping(value="/categorylist", method=RequestMethod.GET)
	public String getCategoryList() {
		
		return "owner/item/category_list";
	}
	
	@RequestMapping(value="/categoryadd", method=RequestMethod.GET)
	public String getCategoryAdd(Category category) {
		
		return "owner/item/category_add";
		
	}
	
	@RequestMapping(value="/categoryregist", method=RequestMethod.POST)
	public String registCategory(Category category) {
		categoryService.insert(category);
		
		return "redirect:/owner/item/categorylist";
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








