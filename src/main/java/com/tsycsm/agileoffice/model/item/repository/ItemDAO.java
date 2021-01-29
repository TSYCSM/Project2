package com.tsycsm.agileoffice.model.item.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tsycsm.agileoffice.model.domain.Item;

@Repository
public interface ItemDAO {
	public Item select(int item_id);
	public void selectByNameInOwner(Item item);
	public Item selectJoinCategory(int item_id);
	public List<Item> selectAllJoinCategory(int owner_id);
	public List<Item> selectByOwnerId(int owner_id);
	public List<Item> selectByCategoryId(Item item);
	public List<Item> selectByOwnerIdCategoryId(Item item);
	public void insert(Item item);
	public void update(Item item);
	public void updateStock(Item item);
	
	public void delete(int item_id);
}
