package com.tsycsm.agileoffice.controller.inventory;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

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
import com.tsycsm.agileoffice.common.MessageData;
import com.tsycsm.agileoffice.exception.DMLException;
import com.tsycsm.agileoffice.exception.NameDuplicatedException;
import com.tsycsm.agileoffice.model.category.service.CategoryService;
import com.tsycsm.agileoffice.model.domain.Category;
import com.tsycsm.agileoffice.model.domain.Item;
import com.tsycsm.agileoffice.model.domain.Owner;
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
		
		logger.info(fileManager.getSaveDir());
		System.out.println(fileManager.getSaveDir());
	}


	/*---------------------------------------------------------------------
	  category CRUD
	 --------------------------------------------------------------------- */
	@RequestMapping(value = "/owner/inventory/category/list", method = RequestMethod.GET)
	public ModelAndView getCategoryList(HttpSession session) {
	
		ModelAndView mav = new ModelAndView();

		Owner owner = (Owner)session.getAttribute("owner");
		int owner_id = owner.getOwner_id();

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

		return "redirect:/owner/inventory/category/list";
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
		sb.append("\"msg\": \"카테고리 삭제 성공\"");
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
		sb.append("\"msg\": \"카테고리 수정 성공\"");
		sb.append("}");
		return sb.toString();
	}
	
	
	
	
	
	
	/*---------------------------------------------------------------------
	  item CRUD
	 --------------------------------------------------------------------- */
	
	@RequestMapping(value = "/owner/inventory/item/list", method = RequestMethod.GET)
	public ModelAndView getItemList(HttpSession session) {

		Owner owner = (Owner)session.getAttribute("owner");
		int owner_id = owner.getOwner_id();

		List<Category> categoryList = categoryService.selectByOwner(owner_id);
		List<Item> itemList = itemService.selectByOwnerId(owner_id);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("categoryList", categoryList);
		mav.addObject("itemList", itemList);
		mav.setViewName("owner/inventory/item_list");

		return mav;
	}
	
	@RequestMapping(value = "/owner/inventory/item/list/filtered", method = RequestMethod.GET)
	@ResponseBody
	public List<Item> getFilteredItemList(int category_id, int owner_id) {
		Item item = new Item();
		item.setOwner_id(owner_id);
		item.setCategory_id(category_id);
		System.out.println("controller owner_id" + owner_id);
		System.out.println("controller category_id" + category_id);
		return itemService.selectByCategoryId(item);
	}
	

	@RequestMapping(value = "/owner/inventory/item/registform", method = RequestMethod.GET)
	public ModelAndView getItemRegistForm(HttpSession session) {
	
		Owner owner = (Owner)session.getAttribute("owner");
		int owner_id = owner.getOwner_id();
	
		List categoryList = categoryService.selectByOwner(owner_id);
	
		ModelAndView mav = new ModelAndView();
		mav.addObject("categoryList", categoryList);
		mav.setViewName("owner/inventory/item_add");

		return mav;
	}

	@RequestMapping(value = "/owner/inventory/item/regist", method = RequestMethod.POST)
	public String registItem(Item item) {
		itemService.regist(item, fileManager);
		return "redirect:/owner/inventory/item/list";
	}
	
	@RequestMapping(value = "/owner/inventory/item/nameCheck", method = RequestMethod.POST)
	@ResponseBody
	public MessageData checkItemName(String item_name) {
		itemService.duplicationCheck(item_name);
	
		MessageData messageData = new MessageData();
		messageData.setMsg("가능한 상품 이름입니다.");
		messageData.setResultCode(1);
	
		return messageData;
	}
	
	@RequestMapping(value = "/owner/inventory/item/detail", method = RequestMethod.GET)
	public ModelAndView getItemDetail(int item_id, HttpSession session) {
	
		Owner owner = (Owner)session.getAttribute("owner");
		int owner_id = owner.getOwner_id();
		
	
		Item item = itemService.select(item_id);
		List categoryList = categoryService.selectByOwner(owner_id);

		ModelAndView mav = new ModelAndView();
		mav.addObject("item", item);
		mav.addObject("categoryList", categoryList);
		mav.setViewName("owner/inventory/item_detail");

		return mav;
	}
	
	@RequestMapping(value = "/owner/inventory/item/update", method = RequestMethod.POST)
	public String updateItem(Item item) {
		itemService.update(item, fileManager);

		return "redirect:/owner/inventory/item/detail?item_id=" + item.getItem_id();
	}
	
	@RequestMapping(value = "/owner/inventory/item/del", method = RequestMethod.POST)
	public String deleteItem(Item item) {
		itemService.delete(item, fileManager);
		return "redirect:/owner/inventory/item/list";
	}
	
	
	


	//----예외 핸들러 처리----
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
	
	@ExceptionHandler(NameDuplicatedException.class)
	@ResponseBody
	public MessageData handleException(NameDuplicatedException e) {
		MessageData messageData = new MessageData();
		messageData.setMsg(e.getMessage());
		messageData.setResultCode(0);
	
		return messageData;
	}


}
