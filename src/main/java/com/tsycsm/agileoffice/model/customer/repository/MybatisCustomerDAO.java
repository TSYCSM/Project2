package com.tsycsm.agileoffice.model.customer.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tsycsm.agileoffice.exception.CustomerException;
import com.tsycsm.agileoffice.exception.CustomerNotFoundException;
import com.tsycsm.agileoffice.model.domain.Customer;

@Repository
public class MybatisCustomerDAO implements CustomerDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer select(String phone) throws CustomerNotFoundException{
		Customer obj = sqlSessionTemplate.selectOne("Customer.select", phone);
		
		if(obj==null) {
			throw new CustomerNotFoundException("��ȭ��ȣ�� �ùٸ��� �ʽ��ϴ�.");
		}
		
		return obj;
	}

	@Override
	public void insert(Customer customer) throws CustomerException{
		int result = sqlSessionTemplate.insert("Customer.insert", customer);
		
		if(result==0) {
			throw new CustomerException("�� ��� ����");
		}
	}

	@Override
	public void update(Customer customer) throws CustomerException{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Customer customer) throws CustomerException{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void duplicateCheck(String phone) throws CustomerException{
		List list = sqlSessionTemplate.selectList("Customer.select", phone);
		
		if(list.size() > 0) {
			throw new CustomerException("�̹� ��ϵ� ��ȭ��ȣ �Դϴ�.");
		}
	}
	
}






