package com.tsycsm.agileoffice.controller.owner;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tsycsm.agileoffice.exception.DMLException;
import com.tsycsm.agileoffice.model.category.service.CategoryService;
import com.tsycsm.agileoffice.model.domain.Category;
import com.tsycsm.agileoffice.model.domain.Item;
import com.tsycsm.agileoffice.model.item.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	private static final Logger logger = LoggerFactory.getLogger(ItemController.class);
	
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ItemService itemService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getItemList() {

		return "owner/item/item_list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String AddItem() {

		return "owner/item/item_add";
	}

	/*---------------------------------------------------------------------
	  category CRUD
	  
	  
	 --------------------------------------------------------------------- 
	 */
	@RequestMapping(value = "/categorylist", method = RequestMethod.GET)
	public ModelAndView getCategoryList() {
		ModelAndView mav = new ModelAndView();
		int owner_id = 1;
		List categoryList = categoryService.selectByOwner(owner_id);
		mav.setViewName("owner/item/category_list");
		mav.addObject("categoryList", categoryList);
		return mav;
	}

	@RequestMapping(value = "/categoryadd", method = RequestMethod.GET)
	public String getCategoryAdd(Category category) {

		return "owner/item/category_add";

	}

	@RequestMapping(value = "/categoryregist", method = RequestMethod.POST)
	public String registCategory(Category category) {
		categoryService.insert(category);

		return "redirect:/owner/item/categorylist";
	}

	@RequestMapping(value="/categorydetail", method=RequestMethod.GET)
	public ModelAndView detailCategory(int category_id) {
		ModelAndView mav = new ModelAndView();
		Category category =  categoryService.select(category_id);
		
		mav.addObject("category", category);
		mav.setViewName("/owner/item/categorydetail");
		
		return mav;
	}

	@RequestMapping(value = "/categorydelete", method = RequestMethod.POST, produces="text/html;charset=utf-8")
	@ResponseBody
	public String deleteCategory(int category_id) {
		logger.info("category_id: "+category_id);
		
		categoryService.delete(category_id);
		
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"result\": 1,");
		sb.append("\"msg\": \"카테고리 삭제 성공\"");
		sb.append("}");
		return sb.toString();
	}
	
		
	@RequestMapping(value="/registform", method=RequestMethod.GET)
	public String registForm() {
		return "owner/item/item_add";
	}
	
	@RequestMapping(value="/regist", method=RequestMethod.POST)
	@RequestMapping(value = "/categoryupdate", method = RequestMethod.POST, produces="text/html;charset=utf-8")
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
	   CRUD
	  
	  
	 --------------------------------------------------------------------- 
	 */

	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String registItem(Item item) {
		itemService.regist(item);
		return "owner/item/item_list";
	}

	//예외 핸들러 2가지 처리
		@ExceptionHandler(DMLException.class)
		@ResponseBody
		public String handleException(DMLException e) {
			StringBuffer sb = new StringBuffer();
			sb.append("{");
			sb.append("\"result\": 0,");
			sb.append("\"msg\": \""+e.getMessage()+"\"");
			sb.append("}");
			return sb.toString();
		}
}












