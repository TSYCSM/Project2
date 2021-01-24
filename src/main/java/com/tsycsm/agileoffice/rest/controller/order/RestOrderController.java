package com.tsycsm.agileoffice.rest.controller.order;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tsycsm.agileoffice.model.common.MessageData;
import com.tsycsm.agileoffice.model.domain.Customer;
import com.tsycsm.agileoffice.model.domain.Item;
import com.tsycsm.agileoffice.model.domain.OrderDetail;
import com.tsycsm.agileoffice.model.domain.OrderSummary;
import com.tsycsm.agileoffice.model.domain.Owner;
import com.tsycsm.agileoffice.model.order.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RestOrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping(value="/order/orderRegist")
	public ResponseEntity<MessageData> orderRegist(HttpSession session, OrderSummary orderSummary, 
			@RequestParam("item_id") int[] item_id_arr
			,@RequestParam("quantity") int[] quantity_arr
			,@RequestParam("price") int[] price_arr
			,@RequestParam("stock") int[] stock_arr) {
		//owner_id, customer_id 전해주시
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
			item.setStock(stock_arr[i]);
		
			log.debug(i+"번째 stock: "+item.getStock());
			
			itemArr[i] = item;
			
			orderDetailArr[i] = orderDetail;
			orderDetailArr[i].setItem(item);
			orderDetailArr[i].setQuantity(quantity_arr[i]);
			orderDetailArr[i].setPrice(price_arr[i]);
		
		}
		
		int cnt = 0;
		
		for(Item item : itemArr) {
			if(item.getStock()-item.getQuantity()>=0) {
				cnt++;
			}
		}
		
		MessageData messageData = new MessageData();
		
		if(cnt==itemArr.length) {
			orderService.regist(itemArr, orderSummary, orderDetailArr);			
			messageData.setResultCode(1);
			messageData.setMsg("주문이 완료되었습니다.");
			messageData.setUrl("/client/main/customerCredential");
		}else {
			messageData.setResultCode(0);
			messageData.setMsg("상품 수량이 부족합니다.");
		}
		
		ResponseEntity<MessageData> entity = ResponseEntity.ok().body(messageData);
		
		return entity;
	}
	
}
