package com.tsycsm.agileoffice.client.controller.inventory;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.tsycsm.agileoffice.model.category.service.CategoryService;
import com.tsycsm.agileoffice.model.common.FileManager;
import com.tsycsm.agileoffice.model.domain.Category;
import com.tsycsm.agileoffice.model.domain.Item;
import com.tsycsm.agileoffice.model.domain.Owner;
import com.tsycsm.agileoffice.model.item.service.DumpService;
import com.tsycsm.agileoffice.model.item.service.ItemService;

@Controller
public class InventoryController {
	private static final Logger logger = LoggerFactory.getLogger(InventoryController.class);

	@Autowired
	private FileManager fileManager;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ItemService itemService;


	/*---------------------------------------------------------------------
	  category CRUD
	 --------------------------------------------------------------------- */
	@RequestMapping(value = "/owner/inventory/category/list", method = RequestMethod.GET)
	public ModelAndView getCategoryList(HttpSession session) {
		ModelAndView mav = new ModelAndView();

		Owner owner = (Owner) session.getAttribute("owner");
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


	/*---------------------------------------------------------------------
	  item CRUD
	 --------------------------------------------------------------------- */

	@RequestMapping(value = "/owner/inventory/item/list", method = RequestMethod.GET)
	public ModelAndView getItemList(HttpSession session) {
		Owner owner = (Owner) session.getAttribute("owner");
		int owner_id = owner.getOwner_id();

		List<Category> categoryList = categoryService.selectByOwner(owner_id);
		List<Item> itemList = itemService.selectByOwnerId(owner_id);

		ModelAndView mav = new ModelAndView();
		mav.addObject("categoryList", categoryList);
		mav.addObject("itemList", itemList);
		mav.setViewName("owner/inventory/item_list");

		return mav;
	}

	@RequestMapping(value = "/owner/inventory/item/registform", method = RequestMethod.GET)
	public ModelAndView getItemRegistForm(HttpSession session) {
		Owner owner = (Owner) session.getAttribute("owner");
		int owner_id = owner.getOwner_id();

		List<Category> categoryList = categoryService.selectByOwner(owner_id);

		ModelAndView mav = new ModelAndView();
		mav.addObject("categoryList", categoryList);
		mav.setViewName("owner/inventory/item_add");

		return mav;
	}

	@RequestMapping(value = "/owner/inventory/item/dump/registform", method = RequestMethod.GET)
	public String getDumpItemRegistForm() {
		return "owner/inventory/dump_add";
	}

	@RequestMapping(value = "/owner/inventory/item/regist", method = RequestMethod.POST)
	public String registItem(Item item) {
		itemService.regist(item, fileManager);

		return "redirect:/client/owner/inventory/item/list";
	}

	@RequestMapping(value = "/owner/inventory/item/detail", method = RequestMethod.GET)
	public ModelAndView getItemDetail(HttpSession session, int item_id) {
		Owner owner = (Owner) session.getAttribute("owner");
		int owner_id = owner.getOwner_id();

		Item item = itemService.select(item_id);
		List<Category> categoryList = categoryService.selectByOwner(owner_id);

		ModelAndView mav = new ModelAndView();
		mav.addObject("item", item);
		mav.addObject("categoryList", categoryList);
		mav.setViewName("owner/inventory/item_detail");

		return mav;
	}

	@RequestMapping(value = "/owner/inventory/item/del", method = RequestMethod.POST)
	public String deleteItem(Item item) {
		itemService.delete(item, fileManager);

		return "redirect:/client/owner/inventory/item/list";
	}

	// ----예외 핸들러 처리----
	

	

}
