package com.tsycsm.agileoffice.model.order.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tsycsm.agileoffice.exception.DMLException;
import com.tsycsm.agileoffice.exception.OrderException;
import com.tsycsm.agileoffice.model.domain.OrderDetail;

@Repository
public class MybatisOrderDetailDAO implements OrderDetailDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	
	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List selectAll(int owner_summary_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List selectAll(OrderDetail orderDetail){		
		return sqlSessionTemplate.selectList("OrderDetail.selectAllByOwnerIdWithData", orderDetail);
	}
	
	@Override
	public OrderDetail select(int order_detail_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(OrderDetail orderDetail) throws OrderException{
		int result = sqlSessionTemplate.insert("OrderDetail.insert", orderDetail);
		if(result==0) {
			throw new OrderException("�ֹ� �� ��� ����");
		}
	}

	@Override
	public void update(OrderDetail orderDetail) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(OrderDetail orderDetail) {
		// TODO Auto-generated method stub
		
	}

}
