package com.tsycsm.agileoffice.model.item.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tsycsm.agileoffice.common.FileManager;
import com.tsycsm.agileoffice.model.domain.Item;

@Service
public interface ItemService {
	public Item select(int item_id);
	public Item duplicationCheck(String item_name);
	public List selectByOwner(int owner_id);
	public void regist(Item item, FileManager fileManager);
	public void update(Item item, FileManager fileManager);
	public void delete(Item item, FileManager fileManager);
}
