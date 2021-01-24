package com.tsycsm.agileoffice.client.controller.order;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tsycsm.agileoffice.exception.OrderException;
import com.tsycsm.agileoffice.model.category.service.CategoryService;
import com.tsycsm.agileoffice.model.common.MessageData;
import com.tsycsm.agileoffice.model.domain.Category;
import com.tsycsm.agileoffice.model.domain.Item;
import com.tsycsm.agileoffice.model.domain.Owner;
import com.tsycsm.agileoffice.model.domain.Review;
import com.tsycsm.agileoffice.model.item.service.ItemService;
import com.tsycsm.agileoffice.model.order.service.OrderService;
import com.tsycsm.agileoffice.model.review.service.ReviewService;

@Controller
public class OrderController {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ReviewService reviewService;
	
	/*******************************************
	 	order main page
	 *******************************************/
	
	@GetMapping(value="/order/main")
	public ModelAndView viewOrderMain(HttpSession session) {
		Owner owner = (Owner)session.getAttribute("owner");
		int owner_id = owner.getOwner_id();

		List<Category> categoryList = categoryService.selectByOwner(owner_id);
		List<Item> itemList = itemService.selectByOwnerId(owner_id);
		List<Review> reviewList = reviewService.selectAllOwner(owner_id);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("categoryList", categoryList);
		mav.addObject("itemList", itemList);
		mav.addObject("reviewList", reviewList);
		mav.setViewName("customer/main");

		return mav;
	}
	
	
	/*******************************************
		order exception handler
	 *******************************************/
	@ExceptionHandler(OrderException.class)
	@ResponseBody
	public MessageData handleException(OrderException e) {
		MessageData messageData = new MessageData();
		messageData.setResultCode(0);
		messageData.setMsg(e.getMessage());
		return messageData;
	}
	
}
















