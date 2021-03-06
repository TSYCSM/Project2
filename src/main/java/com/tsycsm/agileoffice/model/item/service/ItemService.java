package com.tsycsm.agileoffice.model.item.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tsycsm.agileoffice.model.common.FileManager;
import com.tsycsm.agileoffice.model.domain.Item;

@Service
public interface ItemService {
	public Item select(int item_id);
	public void duplicationCheck(Item item);
	public Item selectJoinCategory(int item_id);
	public List<Item> selectAllJoinCategory(int owner_id);
	public List<Item> selectByOwnerId(int owner_id);
	public List<Item> selectByCategoryId(Item item);
	public List<Item> selectByMultiCategoryId(int[] category_ids, int owner_id);
	public void regist(Item item, FileManager fileManager);
	public void update(Item item, FileManager fileManager);
	public void delete(Item item, FileManager fileManager);
}
