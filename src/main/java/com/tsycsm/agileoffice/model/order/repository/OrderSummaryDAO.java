package com.tsycsm.agileoffice.model.order.repository;

import java.util.List;

import com.tsycsm.agileoffice.model.domain.OrderSummary;

public interface OrderSummaryDAO {
	public List selectAllByOwner();
	public OrderSummary select(int order_summary_id);
	public void insert(OrderSummary orderSummary);
	public void update(OrderSummary orderSummary);
	public void delete(OrderSummary orderSummary);
}
