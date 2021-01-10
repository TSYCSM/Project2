package com.tsycsm.agileoffice.controller.order;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tsycsm.agileoffice.common.MessageData;
import com.tsycsm.agileoffice.exception.CustomerNotFoundException;
import com.tsycsm.agileoffice.exception.OrderException;
import com.tsycsm.agileoffice.model.category.service.CategoryService;
import com.tsycsm.agileoffice.model.domain.Category;
import com.tsycsm.agileoffice.model.domain.Customer;
import com.tsycsm.agileoffice.model.domain.Item;
import com.tsycsm.agileoffice.model.domain.OrderSummary;
import com.tsycsm.agileoffice.model.domain.Owner;
import com.tsycsm.agileoffice.model.item.service.ItemService;
import com.tsycsm.agileoffice.model.order.service.OrderService;

@Controller
public class OrderController {
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private OrderService orderService;
	
	/*******************************************
	 	order main page
	 *******************************************/
	
	@GetMapping(value="/order/main")
	public ModelAndView viewOrderMain(HttpSession session) {

		Owner owner = (Owner)session.getAttribute("owner");
		int owner_id = owner.getOwner_id();

		List<Category> categoryList = categoryService.selectByOwner(owner_id);
		List<Item> itemList = itemService.selectByOwner(owner_id);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("categoryList", categoryList);
		mav.addObject("itemList", itemList);
		mav.setViewName("customer/main");

		return mav;
	}
	
	/*******************************************
 		order CRUD
	 *******************************************/

	
	@PostMapping(value="/order/orderRegist")
	@ResponseBody
	public MessageData orderRegist(HttpSession session, OrderSummary orderSummary) {
		Owner owner = (Owner)session.getAttribute("owner");
		Customer customer = (Customer)session.getAttribute("customer");
		
		logger.debug("��) orderSummary customer_id: "+orderSummary.getCustomer_id());
		
		if(customer !=null) {
			orderSummary.setCustomer_id(customer.getCustomer_id());			
		}
		
		orderSummary.setOwner_id(owner.getOwner_id());
		
		logger.debug("��) orderSummary customer_id: "+orderSummary.getCustomer_id());
		logger.debug("orderSummary owner_id: "+orderSummary.getOwner_id());
		logger.debug("orderSummary total_price: "+orderSummary.getTotal_price());
		
		orderService.regist(orderSummary);
		
		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		messageData.setMsg("�ֹ��� �Ϸ�Ǿ����ϴ�.");
		messageData.setUrl("/main/customerCredential");
		
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
















