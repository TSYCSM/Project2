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
import com.tsycsm.agileoffice.exception.AsyncDMLException;
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

	@RequestMapping(value = "/owner/inventory/category/delete", method = RequestMethod.GET)
	@ResponseBody
	public String deleteCategory(int category_id) {
		categoryService.delete(category_id);

		return "redirect:/owner/inventory/category/list";
	}

	@RequestMapping(value = "/owner/inventory/category/update", method = RequestMethod.POST)
	@ResponseBody
	public MessageData updateCategory(Category category) {
		categoryService.update(category);

		MessageData messageData = new MessageData();
		messageData.setMsg("카테고리 정보 수정 성공");
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
	
		List<Category> categoryList = categoryService.selectByOwner(owner_id);
	
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
	public MessageData checkItemName(Item item) {
		System.out.println("simin owner_id : " + item.getOwner_id());
		System.out.println("simin item_name : " + item.getItem_name());
	
		itemService.duplicationCheck(item);
	
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
		messageData.setMsg("상품 정보 수정 완료되었습니다.");
		messageData.setResultCode(1);
	
		return messageData;
	}
	
	@RequestMapping(value="/owner/inventory/item/del", method=RequestMethod.POST)
	public String deleteItem(Item item) {
		itemService.delete(item, fileManager);
		return "redirect:/owner/inventory/item/list";
	}
	


	//----예외 핸들러 처리----
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
