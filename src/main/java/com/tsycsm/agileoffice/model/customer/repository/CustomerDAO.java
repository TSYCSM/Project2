package com.tsycsm.agileoffice.model.customer.repository;

import java.util.List;

import com.tsycsm.agileoffice.model.domain.Customer;

public interface CustomerDAO {
	public List selectAll();
	public Customer select(String phone);
	public void insert(Customer customer);
	public void update(Customer customer);
	public void delete(Customer customer);
	public void duplicateCheck(String phone);
}
