package com.tsycsm.agileoffice.model.item.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tsycsm.agileoffice.exception.AsyncDMLException;
import com.tsycsm.agileoffice.exception.DMLException;
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
	public void insert(Item item) throws DMLException {
		int result = 0;
		if(item.getCategory_id() != 0) {
			result = sqlSessionTemplate.insert("Item.insert", item);
		} else {
			result = sqlSessionTemplate.insert("Item.insertWithoutCategoryId", item);
		}
		result = 0;
		if(result == 0) {
			throw new DMLException("상품 등록에 실패하였습니다.");
		}
	}

	@Override
	public Item select(int item_id) {
		return sqlSessionTemplate.selectOne("Item.select", item_id);
	}


	@Override
	public void update(Item item) throws AsyncDMLException {
		int result = 0;

		if(item.getCategory_id() != 0) {
			result = sqlSessionTemplate.update("Item.update", item);
		} else {
			result = sqlSessionTemplate.update("Item.updateNullifingCategoryId", item);
		}
		result = 0;
		if(result == 0) {
			throw new DMLException("상품 정보 수정에 실패하였습니다.");
		}
	}


	@Override
	public void delete(int item_id) throws DMLException {
		int result = sqlSessionTemplate.delete("Item.delete", item_id);
		result = 0;
		if(result == 0) {
			throw new DMLException("상품 삭제에 실패하였습니다");
		}
	}


	@Override
	public Item selectByName(String item_name) {
		return sqlSessionTemplate.selectOne("Item.selectByName", item_name);
	}
	
	


}
