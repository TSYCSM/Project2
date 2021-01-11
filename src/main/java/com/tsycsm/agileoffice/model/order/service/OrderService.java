package com.tsycsm.agileoffice.model.order.service;

import java.util.List;

import com.tsycsm.agileoffice.model.domain.Item;
import com.tsycsm.agileoffice.model.domain.OrderDetail;
import com.tsycsm.agileoffice.model.domain.OrderSummary;

public interface OrderService {
	public List selectAllByOwner();
	public OrderSummary select(int order_summary_id);
	public void regist(Item[] itemArr, OrderSummary orderSummary, OrderDetail[] orderDetailArr);
	public void update(OrderSummary orderSummary);
	public void delete(OrderSummary orderSummary);

	
}
