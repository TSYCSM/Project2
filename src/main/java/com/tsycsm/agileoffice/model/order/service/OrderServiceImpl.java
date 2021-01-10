package com.tsycsm.agileoffice.model.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsycsm.agileoffice.exception.OrderException;
import com.tsycsm.agileoffice.model.domain.OrderSummary;
import com.tsycsm.agileoffice.model.order.repository.OrderSummaryDAO;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderSummaryDAO orderSummaryDAO;
	
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
	public void regist(OrderSummary orderSummary) throws OrderException{
		orderSummaryDAO.insert(orderSummary);
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
