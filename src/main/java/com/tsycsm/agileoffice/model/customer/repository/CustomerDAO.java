package com.tsycsm.agileoffice.model.customer.repository;

import java.util.List;

import com.tsycsm.agileoffice.model.domain.Customer;

public interface CustomerDAO {
	public List selectAll();
	public List<Customer> selectAllByOwnerId(int owner_id);
	public Customer select(Customer customer);
	public Customer selectJoinOrderSummary(int customer_id);
	public void insert(Customer customer);
	public void update(Customer customer);
	public void delete(Customer customer);
	public void duplicateCheck(Customer Customer);
}
