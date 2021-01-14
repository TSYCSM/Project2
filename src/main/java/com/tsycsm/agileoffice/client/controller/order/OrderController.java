package com.tsycsm.agileoffice.client.controller.order;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tsycsm.agileoffice.exception.OrderException;
import com.tsycsm.agileoffice.model.category.service.CategoryService;
import com.tsycsm.agileoffice.model.common.MessageData;
import com.tsycsm.agileoffice.model.domain.Category;
import com.tsycsm.agileoffice.model.domain.Customer;
import com.tsycsm.agileoffice.model.domain.Item;
import com.tsycsm.agileoffice.model.domain.OrderDetail;
import com.tsycsm.agileoffice.model.domain.OrderSummary;
import com.tsycsm.agileoffice.model.domain.Owner;
import com.tsycsm.agileoffice.model.domain.Review;
import com.tsycsm.agileoffice.model.item.service.ItemService;
import com.tsycsm.agileoffice.model.order.service.OrderService;
import com.tsycsm.agileoffice.model.review.service.ReviewService;

@Controller
public class OrderController {
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
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
	public ModelAndView viewOrderMain(HttpServletRequest request) {

		HttpSession session = request.getSession();
		
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
 		order CRUD
	 *******************************************/

	
	@PostMapping(value="/order/orderRegist")
	@ResponseBody
	public MessageData orderRegist(HttpServletRequest request, OrderSummary orderSummary, 
			@RequestParam("item_id") int[] item_id_arr
			,@RequestParam("quantity") int[] quantity_arr
			,@RequestParam("price") int[] price_arr) {
		//owner_id, customer_id 전해주시
		
		HttpSession session = request.getSession();
		Owner owner = (Owner)session.getAttribute("owner");
		Customer customer = (Customer)session.getAttribute("customer");

		Item[] itemArr = new Item[item_id_arr.length];
		
		if(customer !=null) {
			orderSummary.setCustomer(customer);			
		}
		orderSummary.setOwner_id(owner.getOwner_id());
		
		
		//orderDetailArr 선언...(속상함)
		OrderDetail[] orderDetailArr = new OrderDetail[item_id_arr.length];
		for(int i=0; i<orderDetailArr.length; i++) {
			OrderDetail orderDetail = new OrderDetail();
			Item item = new Item();
			item.setItem_id(item_id_arr[i]);
			item.setQuantity(quantity_arr[i]);
			
			itemArr[i] = item;
			
			orderDetailArr[i] = orderDetail;
			orderDetailArr[i].setItem(item);
			orderDetailArr[i].setQuantity(quantity_arr[i]);
			orderDetailArr[i].setPrice(price_arr[i]);
		}
		
		
		orderService.regist(itemArr, orderSummary, orderDetailArr);
		
		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		messageData.setMsg("주문이 완료되었습니다.");
		messageData.setUrl("/client/main/customerCredential");
		
		return messageData;
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
















