package com.tsycsm.agileoffice.model.owner.repository;

import java.util.List;

import com.tsycsm.agileoffice.model.domain.Owner;

public interface OwnerDAO {
	public List<Owner> selectAll();
	public Owner select(Owner owner);
	public void insert(Owner owner);
	public void delete(Owner owner);
	public void update(Owner owner);
	
	public void duplicateCheck(String user_id);
	public void passwordCheck(Owner owner);
}
