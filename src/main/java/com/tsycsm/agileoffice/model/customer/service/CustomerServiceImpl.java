package com.tsycsm.agileoffice.model.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsycsm.agileoffice.exception.CustomerException;
import com.tsycsm.agileoffice.model.customer.repository.CustomerDAO;
import com.tsycsm.agileoffice.model.domain.Customer;
import com.tsycsm.agileoffice.model.domain.Owner;

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
	public Customer select(String phone) {
		Customer obj = customerDAO.select(phone);
		
		return obj;
	}

	@Override
	public void regist(Customer customer) throws CustomerException{
		customerDAO.duplicateCheck(customer.getPhone());
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
