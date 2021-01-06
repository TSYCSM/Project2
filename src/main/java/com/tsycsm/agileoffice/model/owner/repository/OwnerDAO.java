package com.tsycsm.agileoffice.model.owner.repository;

import java.util.List;

import com.tsycsm.agileoffice.model.domain.Owner;

public interface OwnerDAO {
	public List selectAll();
	public Owner select(int owner_id);
	public void insert(Owner owner);
	public void delete(Owner owner);
	public void update(Owner owner);
	
	public int checkId(String user_id);
}