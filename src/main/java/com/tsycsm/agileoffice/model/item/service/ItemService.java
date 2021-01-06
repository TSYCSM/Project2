package com.tsycsm.agileoffice.model.item.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tsycsm.agileoffice.model.domain.Item;

@Service
public interface ItemService {
	public Item select(int item_id);
	public List selectByOwner(int owner_id);
	public void regist(Item item);
	public void update(Item item);
	public void delete(int item_id);
}
