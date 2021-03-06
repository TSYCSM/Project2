package com.tsycsm.agileoffice.model.order.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tsycsm.agileoffice.exception.AsyncOrderDMLException;
import com.tsycsm.agileoffice.model.domain.OrderSummary;

@Repository
public class MybatisOrderSummaryDAO implements OrderSummaryDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List selectAllByOwner(int owner_id) {
		return sqlSessionTemplate.selectList("OrderSummary.selectJoinCustomer", owner_id);
	}
	
	@Override
	public List selectAllByOwnerWithDate(int owner_id) {
		return sqlSessionTemplate.selectList("OrderSummary.selectAllByOwnerWithDate", owner_id);
	}

	@Override
	public OrderSummary select(int order_summary_id) {
		return sqlSessionTemplate.selectOne("OrderSummary.selectById", order_summary_id);
	}

	@Override
	public void insert(OrderSummary orderSummary) throws AsyncOrderDMLException{
		
		int result = 0;
		if(orderSummary.getCustomer() !=null) {
			result = sqlSessionTemplate.insert("OrderSummary.insert", orderSummary);			
		}else {
			result = sqlSessionTemplate.insert("OrderSummary.insertWithoutCustomerId", orderSummary);						
		}
		if(result == 0) {
			throw new AsyncOrderDMLException("주문 등록에 실패하였습니다.\n관리자에게 문의하세요");
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
