package com.tsycsm.agileoffice.model.order.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsycsm.agileoffice.exception.OrderException;
import com.tsycsm.agileoffice.model.domain.OrderDetail;
import com.tsycsm.agileoffice.model.domain.OrderSummary;
import com.tsycsm.agileoffice.model.order.repository.OrderDetailDAO;
import com.tsycsm.agileoffice.model.order.repository.OrderSummaryDAO;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderSummaryDAO orderSummaryDAO;
	
	@Autowired
	private OrderDetailDAO orderDetailDAO;
	
	@Override
	public List selectAllByOwner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderSummary select(int order_summary_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void regist(OrderSummary orderSummary, OrderDetail[] orderDetailArr) throws OrderException{
		orderSummaryDAO.insert(orderSummary);
		
		for(OrderDetail orderDetail : orderDetailArr) {
			orderDetail.setOrder_summary_id(orderSummary.getOrder_summary_id());
			orderDetailDAO.insert(orderDetail);
		}
	}

	@Override
	public void update(OrderSummary orderSummary) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(OrderSummary orderSummary) {
		// TODO Auto-generated method stub
		
	}

}
