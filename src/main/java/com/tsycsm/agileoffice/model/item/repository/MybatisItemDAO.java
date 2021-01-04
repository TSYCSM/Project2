package com.tsycsm.agileoffice.model.item.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tsycsm.agileoffice.model.domain.Item;

@Repository
public class MybatisItemDAO implements ItemDAO{
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	
	
	public List selectByOwner(int owner_id) {
		List list = null;
		list = sessionTemplate.selectList("Item.selectByOwner", owner_id);
		return list;
	}


	@Override
	public void insert(Item item) {
		sessionTemplate.insert("Item.insert", item);
	}
	
	


}
