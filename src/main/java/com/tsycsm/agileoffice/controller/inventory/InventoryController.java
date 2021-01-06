package com.tsycsm.agileoffice.controller.inventory;

import java.util.List;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.tsycsm.agileoffice.common.FileManager;
import com.tsycsm.agileoffice.exception.DMLException;
import com.tsycsm.agileoffice.model.category.service.CategoryService;
import com.tsycsm.agileoffice.model.domain.Category;
import com.tsycsm.agileoffice.model.domain.Item;
import com.tsycsm.agileoffice.model.item.service.ItemService;

@Controller
public class InventoryController implements ServletContextAware {
	private static final Logger logger = LoggerFactory.getLogger(InventoryController.class);

	@Autowired
	private FileManager fileManager;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ItemService itemService;
	

	@Autowired
	private ServletContext servletContext;

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		
		fileManager.setSaveDir(servletContext.getRealPath(fileManager.getSaveDir()));
		
		logger.debug(fileManager.getSaveDir());
	}


	/*---------------------------------------------------------------------
	  category CRUD
	 --------------------------------------------------------------------- */
	@RequestMapping(value = "/owner/inventory/category/list", method = RequestMethod.GET)
	public ModelAndView getCategoryList() {
		ModelAndView mav = new ModelAndView();
		int owner_id = 1;
		List categoryList = categoryService.selectByOwner(owner_id);
		mav.setViewName("owner/inventory/category_list");
		mav.addObject("categoryList", categoryList);
		return mav;
	}

	@RequestMapping(value = "/owner/inventory/category/registform", method = RequestMethod.GET)
	public String getCategoryAdd(Category category) {

		return "owner/inventory/category_add";

	}

	@RequestMapping(value = "/owner/inventory/category/regist", method = RequestMethod.POST)
	public String registCategory(Category category) {
		categoryService.insert(category);

		return "redirect:/owner/inventory/categorylist";
	}

	@RequestMapping(value = "/owner/inventory/category/detail", method = RequestMethod.GET)
	public ModelAndView detailCategory(int category_id) {
		ModelAndView mav = new ModelAndView();
		Category category = categoryService.select(category_id);

		mav.addObject("category", category);
		mav.setViewName("/owner/inventory/category_detail");

		return mav;
	}

	@RequestMapping(value = "/owner/inventory/category/delete", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String deleteCategory(int category_id) {
		logger.info("category_id: " + category_id);

		categoryService.delete(category_id);

		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"result\": 1,");
		sb.append("\"msg\": \"ī�װ� ���� ����\"");
		sb.append("}");
		return sb.toString();
	}

	@RequestMapping(value = "/owner/inventory/category/update", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String updateCategory(Category category) {

		categoryService.update(category);

		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"result\": 1,");
		sb.append("\"msg\": \"ī�װ� ���� ����\"");
		sb.append("}");
		return sb.toString();
	}
	
	
	
	
	
	
	/*---------------------------------------------------------------------
	  item CRUD
	 --------------------------------------------------------------------- */
	
	@RequestMapping(value = "/owner/inventory/item/list", method = RequestMethod.GET)
	public ModelAndView getItemList() {
		int owner_id = 1;
		List itemList = itemService.selectByOwner(owner_id);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("itemList", itemList);
		mav.setViewName("owner/inventory/item_list");

		return mav;
	}

	@RequestMapping(value = "/owner/inventory/item/registform", method = RequestMethod.GET)
	public String getRegistForm() {
		return "owner/inventory/item_add";
	}

	@RequestMapping(value = "/owner/inventory/item/regist", method = RequestMethod.POST)
	public String registItem(Item item) {
		itemService.regist(item);
		return "owner/inventory/item_list";
	}
	
	@RequestMapping(value = "/owner/inventory/item/detail", method = RequestMethod.GET)
	public ModelAndView getDetail(int item_id) {
		Item item = itemService.select(item_id);

		ModelAndView mav = new ModelAndView();
		mav.addObject("item", item);
		mav.setViewName("owner/inventory/item_detail");

		return mav;
	}
	
	@RequestMapping(value = "/owner/inventory/item/update", method = RequestMethod.POST)
	public String updateItem(Item item) {
		itemService.update(item);
		return "owner/inventory/item_detail" + item.getItem_id();
	}
	
	@RequestMapping(value = "/owner/inventory/item/delete", method = RequestMethod.GET)
	public String deleteItem(int item_id) {
		itemService.delete(item_id);
		return "redirect:/owner/inventory/item/list";
	}
	
	




	//----���� �ڵ鷯 ó��----
	@ExceptionHandler(DMLException.class)
	@ResponseBody
	public String handleException(DMLException e) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"result\": 0,");
		sb.append("\"msg\": \"" + e.getMessage() + "\"");
		sb.append("}");
		return sb.toString();
	}


}
