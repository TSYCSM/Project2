package com.tsycsm.agileoffice.model.order.repository;

import java.util.List;

import com.tsycsm.agileoffice.model.domain.OrderDetail;

public interface OrderDetailDAO {
	public List selectAll();
	public List selectAll(int owner_summary_id);
	public List selectAll(OrderDetail orderDetail);
	public OrderDetail select(int order_detail_id);
	public void insert(OrderDetail orderDetail);
	public void update(OrderDetail orderDetail);
	public void delete(OrderDetail orderDetail);
}
