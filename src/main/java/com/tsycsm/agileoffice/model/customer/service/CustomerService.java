package com.tsycsm.agileoffice.model.customer.service;

import java.util.List;

import com.tsycsm.agileoffice.model.domain.Customer;

public interface CustomerService {
	public List selectAll();
	public Customer select(String phone);
	public void regist(Customer customer);
	public void update(Customer customer);
	public void delete(Customer customer);
}
