package com.tsycsm.agileoffice.model.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsycsm.agileoffice.exception.AsyncCustomerDMLException;
import com.tsycsm.agileoffice.model.customer.repository.CustomerDAO;
import com.tsycsm.agileoffice.model.domain.Customer;

@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	private CustomerDAO customerDAO;
	
	
	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List selectAll(int owner_id) {
		return customerDAO.selectAllByOwnerId(owner_id);
	}
	
	@Override
	public Customer select(Customer customer) {
		Customer obj = customerDAO.select(customer);
		
		return obj;
	}
	
	@Override
	public Customer selectJoinOrderSummary(int customer_id) {
		return customerDAO.selectJoinOrderSummary(customer_id);
	}

	
	
	@Override
	public int getTotalNumberOfCutomer(int owner_id) {
		List<Customer> customerList = customerDAO.selectAllByOwnerId(owner_id);
		return customerList.size();
	}

	@Override
	public void regist(Customer customer) throws AsyncCustomerDMLException{
		customerDAO.duplicateCheck(customer);
		customerDAO.insert(customer);
	}

	@Override
	public void update(Customer customer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Customer customer) {
		// TODO Auto-generated method stub
		
	}

	

}
