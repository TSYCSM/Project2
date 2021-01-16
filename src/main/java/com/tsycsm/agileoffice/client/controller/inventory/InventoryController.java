package com.tsycsm.agileoffice.client.controller.inventory;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
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

import com.tsycsm.agileoffice.exception.AsyncDMLException;
import com.tsycsm.agileoffice.exception.DMLException;
import com.tsycsm.agileoffice.exception.NameDuplicatedException;
import com.tsycsm.agileoffice.model.category.service.CategoryService;
import com.tsycsm.agileoffice.model.common.FileManager;
import com.tsycsm.agileoffice.model.common.MessageData;
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

		return "redirect:/client/owner/inventory/category/list";
	}

	@RequestMapping(value = "/owner/inventory/category/detail", method = RequestMethod.GET)
	public ModelAndView detailCategory(int category_id) {
		ModelAndView mav = new ModelAndView();
		Category category = categoryService.select(category_id);

		mav.addObject("category", category);
		mav.setViewName("/owner/inventory/category_detail");

		return mav;
	}

	@RequestMapping(value = "/owner/inventory/category/delete", method = RequestMethod.GET)
	public String deleteCategory(int category_id) {
		categoryService.delete(category_id);

		return "redirect:/client/owner/inventory/category/list";
	}

	@RequestMapping(value = "/owner/inventory/category/update", method = RequestMethod.POST)
	@ResponseBody
	public MessageData updateCategory(Category category) {
		categoryService.update(category);

		MessageData messageData = new MessageData();
		messageData.setMsg("ī�װ� ���� ���� ����");
		messageData.setResultCode(1);
	
		return messageData;
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
	public List<Item> getFilteredItemList(String[] categoryArray, int owner_id) {
		Item item = new Item();
		
		int[] category_ids = new int[categoryArray.length];
		
		for(int i=0; i<categoryArray.length; i++) {
			category_ids[i] = Integer.parseInt(categoryArray[i]);
		}
		
		return itemService.selectByMultiCategoryId(category_ids, owner_id);
	}
	

	@RequestMapping(value = "/owner/inventory/item/registform", method = RequestMethod.GET)
	public ModelAndView getItemRegistForm(HttpSession session) {
		Owner owner = (Owner)session.getAttribute("owner");
		int owner_id = owner.getOwner_id();
	
		List<Category> categoryList = categoryService.selectByOwner(owner_id);
	
		ModelAndView mav = new ModelAndView();
		mav.addObject("categoryList", categoryList);
		mav.setViewName("owner/inventory/item_add");

		return mav;
	}
	
	@RequestMapping(value="/owner/inventory/item/regist", method=RequestMethod.POST)
	public String getDumpItemRegistForm() { 
		
		return "";
	}

	@RequestMapping(value = "/owner/inventory/item/regist", method = RequestMethod.POST)
	public String registItem(Item item) {
		itemService.regist(item, fileManager);

		return "redirect:/client/owner/inventory/item/list";
	}
	
	@RequestMapping(value = "/owner/inventory/item/nameCheck", method = RequestMethod.POST)
	@ResponseBody
	public MessageData checkItemName(Item item) {
		itemService.duplicationCheck(item);
	
		MessageData messageData = new MessageData();
		messageData.setMsg("������ ��ǰ �̸��Դϴ�.");
		messageData.setResultCode(1);
	
		return messageData;
	}
	
	@RequestMapping(value = "/owner/inventory/item/detail", method = RequestMethod.GET)
	public ModelAndView getItemDetail(HttpSession session, int item_id) {
		Owner owner = (Owner)session.getAttribute("owner");
		int owner_id = owner.getOwner_id();
		
	
		Item item = itemService.select(item_id);
		List<Category> categoryList = categoryService.selectByOwner(owner_id);

		ModelAndView mav = new ModelAndView();
		mav.addObject("item", item);
		mav.addObject("categoryList", categoryList);
		mav.setViewName("owner/inventory/item_detail");

		return mav;
	}
	
	@RequestMapping(value="/owner/inventory/item/update", method=RequestMethod.POST)
	@ResponseBody
	public MessageData updateItem(Item item) {
		itemService.update(item, fileManager);

		MessageData messageData = new MessageData();
		messageData.setMsg("��ǰ ���� ���� �Ϸ�Ǿ����ϴ�.");
		messageData.setResultCode(1);
	
		return messageData;
	}
	
	@RequestMapping(value="/owner/inventory/item/del", method=RequestMethod.POST)
	public String deleteItem(Item item) {
		itemService.delete(item, fileManager);

		return "redirect:/client/owner/inventory/item/list";
	}
	


	//----���� �ڵ鷯 ó��----
	@ExceptionHandler(DMLException.class)
	@ResponseBody
	public ModelAndView handleException(DMLException e) {
		MessageData messageData = new MessageData();
		messageData.setMsg(e.getMessage());
		messageData.setResultCode(0);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("messageData", messageData);
		mav.setViewName("/error/result");
	
		return mav;
	}
	
	@ExceptionHandler(AsyncDMLException.class)
	@ResponseBody
	public MessageData handleException(AsyncDMLException e) {
		MessageData messageData = new MessageData();
		messageData.setMsg(e.getMessage());
		messageData.setResultCode(0);
	
		return messageData;
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
