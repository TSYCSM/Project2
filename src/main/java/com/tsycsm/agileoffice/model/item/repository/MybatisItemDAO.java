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
		return sessionTemplate.selectList("Item.selectByOwner", owner_id);
	}


	@Override
	public void insert(Item item) {
		sessionTemplate.insert("Item.insert", item);
	}


	@Override
	public Item select(int item_id) {
		return sessionTemplate.selectOne("Item.select", item_id);
	}


	@Override
	public void update(Item item) {
		sessionTemplate.update("Item.update", item);
	}


	@Override
	public void delete(int item_id) {
		sessionTemplate.delete("Item.delete", item_id);
	}
	
	


}
