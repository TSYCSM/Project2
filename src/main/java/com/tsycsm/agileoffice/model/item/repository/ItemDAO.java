package com.tsycsm.agileoffice.model.item.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tsycsm.agileoffice.model.domain.Item;

@Repository
public interface ItemDAO {
	public List selectByOwner(int owner_id);
	public void insert(Item item);
}
