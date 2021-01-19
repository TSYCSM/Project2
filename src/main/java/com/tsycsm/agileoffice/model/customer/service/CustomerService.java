package com.tsycsm.agileoffice.model.customer.service;

import java.util.List;

import com.tsycsm.agileoffice.model.domain.Customer;

public interface CustomerService {
	public List selectAll();
	public List selectAll(int owner_id);
	public Customer select(Customer customer);
	public Customer selectJoinOrderSummary(int customer_id);
	public int getTotalNumberOfCutomer(int owner_id);
	public void regist(Customer customer);
	public void update(Customer customer);
	public void delete(Customer customer);
}
