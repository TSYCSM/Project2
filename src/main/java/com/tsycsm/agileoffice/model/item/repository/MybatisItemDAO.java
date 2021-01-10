package com.tsycsm.agileoffice.model.item.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tsycsm.agileoffice.model.domain.Item;

@Repository
public class MybatisItemDAO implements ItemDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	
	public List<Item> selectByOwnerId(int owner_id) {
		return sqlSessionTemplate.selectList("Item.selectByOwnerId", owner_id);
	}

	@Override
	public List<Item> selectByCategoryId(Item item) {
		List<Item> itemList = null;
		
		System.out.println("DAO category_id" + item.getCategory_id());
		System.out.println("DAO owner_id" + item.getOwner_id());

		if(item.getCategory_id() != 0) {
			itemList = sqlSessionTemplate.selectList("Item.selectByCategoryId", item.getCategory_id());
			System.out.println(itemList);
		} else {
			itemList = sqlSessionTemplate.selectList("Item.selectByCategoryIdNull", item.getOwner_id());
			System.out.println(itemList);
		}
		
	
		return itemList;
	}

	@Override
	public void insert(Item item) {
		if(item.getCategory_id() != 0) {
			sqlSessionTemplate.insert("Item.insert", item);
		} else {
			sqlSessionTemplate.insert("Item.insertWithoutCategoryId", item);
		}
	}

	@Override
	public Item select(int item_id) {
		return sqlSessionTemplate.selectOne("Item.select", item_id);
	}


	@Override
	public void update(Item item) {
		if(item.getCategory_id() != 0) {
			sqlSessionTemplate.update("Item.update", item);
		} else {
			sqlSessionTemplate.update("Item.updateNullifingCategoryId", item);
		}
	}


	@Override
	public void delete(int item_id) {
		sqlSessionTemplate.delete("Item.delete", item_id);
	}


	@Override
	public Item selectByName(String item_name) {
		return sqlSessionTemplate.selectOne("Item.selectByName", item_name);
	}
	
	


}
