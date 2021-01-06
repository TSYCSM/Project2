package com.tsycsm.agileoffice.model.item.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tsycsm.agileoffice.model.domain.Item;

@Repository
public interface ItemDAO {
	public Item select(int item_id);
	public List selectByOwner(int owner_id);
	public void insert(Item item);
	public void update(Item item);
	public void delete(int item_id);
}
