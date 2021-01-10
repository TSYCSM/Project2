package com.tsycsm.agileoffice.model.order.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tsycsm.agileoffice.exception.OrderException;
import com.tsycsm.agileoffice.model.domain.OrderSummary;

@Repository
public class MybatisOrderSummaryDAO implements OrderSummaryDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List selectAllByOwner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderSummary select(int order_summary_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(OrderSummary orderSummary) throws OrderException{
		int customer_id = orderSummary.getCustomer_id();
		int result = 0;
		if(customer_id !=0) {
			result = sqlSessionTemplate.insert("OrderSummary.insert", orderSummary);			
		}else {
			result = sqlSessionTemplate.insert("OrderSummary.insertWithoutCustomerId", orderSummary);						
		}
		if(result == 0) {
			throw new OrderException("주문 등록에 실패하였습니다.\n관리자에게 문의하세요");
		}
	}

	@Override
	public void update(OrderSummary orderSummary) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(OrderSummary orderSummary) {
		// TODO Auto-generated method stub
		
	}

}
