package com.tsycsm.agileoffice.model.item.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface ItemDAO {
	public List selectByOwner(int owner_id);
}
