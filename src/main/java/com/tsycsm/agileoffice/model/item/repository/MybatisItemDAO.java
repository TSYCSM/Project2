package com.tsycsm.agileoffice.model.item.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tsycsm.agileoffice.exception.AsyncInventoryDMLException;
import com.tsycsm.agileoffice.exception.AsyncInventoryNameDuplicatedException;
import com.tsycsm.agileoffice.exception.InventoryDMLException;
import com.tsycsm.agileoffice.model.domain.Item;

@Repository
public class MybatisItemDAO implements ItemDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	
	@Override
	public List<Item> selectByOwnerId(int owner_id) {
		return sqlSessionTemplate.selectList("Item.selectByOwnerId", owner_id);
	}

	@Override
	public List<Item> selectByCategoryId(Item item) {
		List<Item> itemList = null;
		
		if(item.getCategory_id() != 0) {
			itemList = sqlSessionTemplate.selectList("Item.selectByCategoryId", item.getCategory_id());
		} else {
			itemList = sqlSessionTemplate.selectList("Item.selectByCategoryIdNull", item.getOwner_id());
		}
		
		return itemList;
	}

	@Override
	public List<Item> selectByOwnerIdCategoryId(Item item) {
		List<Item> itemList = null;
		
		if(item.getCategory_id() != 0) {
			itemList = sqlSessionTemplate.selectList("Item.selectByOwnerIdCategoryId", item);
		} else {
			itemList = sqlSessionTemplate.selectList("Item.selectByCategoryIdNull", item.getOwner_id());
		}
		
		return itemList;
	}
	
	
	@Override
	public void insert(Item item) throws InventoryDMLException {
		int result = 0;
		if(item.getCategory_id() != 0) {
			result = sqlSessionTemplate.insert("Item.insert", item);
		} else {
			result = sqlSessionTemplate.insert("Item.insertWithoutCategoryId", item);
		}
		if(result == 0) {
			throw new InventoryDMLException("��ǰ ��Ͽ� �����Ͽ����ϴ�.");
		}
	}

	@Override
	public Item select(int item_id) {
		return sqlSessionTemplate.selectOne("Item.select", item_id);
	}
	
	@Override
	public Item selectJoinCategory(int item_id) {
		Item item = sqlSessionTemplate.selectOne("Item.selectJoinCategory", item_id);

		return item;
	}	
	
	@Override
	public List<Item> selectAllJoinCategory(int owner_id) {
		return sqlSessionTemplate.selectList("Item.selectAllJoinCategory", owner_id);
	}


	@Override
	public void update(Item item) throws AsyncInventoryDMLException {
		int result = 0;

		if(item.getCategory_id() != 0) {
			result = sqlSessionTemplate.update("Item.update", item);
		} else {
			result = sqlSessionTemplate.update("Item.updateNullifingCategoryId", item);
		}
		
		if(result == 0) {
			throw new AsyncInventoryDMLException("��ǰ ���� ������ �����Ͽ����ϴ�.");
		}
	}

	@Override
	public void updateStock(Item item) throws InventoryDMLException{
		int result = sqlSessionTemplate.update("Item.updateStock", item);			

		if(result==0) {
			throw new InventoryDMLException("��ǰ ������ �����մϴ�.");
		}
	}


	@Override
	public void delete(int item_id) throws InventoryDMLException {
		int result = sqlSessionTemplate.delete("Item.delete", item_id);
		if(result == 0) {
			throw new InventoryDMLException("��ǰ ������ �����Ͽ����ϴ�");
		}
	}


	@Override
	public void selectByNameInOwner(Item item) throws AsyncInventoryNameDuplicatedException {
		List<Item> item_list = sqlSessionTemplate.selectList("Item.selectByNameInOwner", item);
		if(item_list.size() > 0) {
			throw new AsyncInventoryNameDuplicatedException("��ǰ��(" + item.getItem_name() + ")�� �ߺ��˴ϴ�.");
		}
	}

	
	


}
