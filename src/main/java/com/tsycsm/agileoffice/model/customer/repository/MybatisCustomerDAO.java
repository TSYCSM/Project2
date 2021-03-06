package com.tsycsm.agileoffice.model.customer.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tsycsm.agileoffice.exception.AsyncCustomerDMLException;
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
	public Customer select(Customer Customer) throws CustomerNotFoundException{
		Customer obj = sqlSessionTemplate.selectOne("Customer.select", Customer);
		
		if(obj==null) {
			throw new CustomerNotFoundException("회원 정보가 올바르지 않습니다.");
		}
		
		return obj;
	}
	
	@Override
	public Customer selectJoinOrderSummary(int customer_id) {
		return sqlSessionTemplate.selectOne("Customer.selectJoinOrderSummary", customer_id);
	}
	
	@Override
	public List<Customer> selectAllByOwnerId(int owner_id) {
		return sqlSessionTemplate.selectList("Customer.selectAllByOwnerId", owner_id);
	}

	@Override
	public void insert(Customer customer) throws AsyncCustomerDMLException{
		int result = sqlSessionTemplate.insert("Customer.insert", customer);
		
		if(result==0) {
			throw new AsyncCustomerDMLException("고객 등록 실패");
		}
	}

	@Override
	public void update(Customer customer) throws AsyncCustomerDMLException{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Customer customer) throws AsyncCustomerDMLException{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void duplicateCheck(Customer customer) throws AsyncCustomerDMLException{
		List list = sqlSessionTemplate.selectList("Customer.select", customer);
		
		if(list.size() > 0) {
			throw new AsyncCustomerDMLException("이미 등록된 전화번호 입니다.");
		}
	}
	
}






