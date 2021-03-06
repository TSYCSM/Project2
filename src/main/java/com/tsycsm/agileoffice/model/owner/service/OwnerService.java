package com.tsycsm.agileoffice.model.owner.service;

import java.util.List;

import com.tsycsm.agileoffice.model.domain.Owner;

public interface OwnerService {
	public List selectAll();
	public Owner select(Owner owner);
	public void regist(Owner owner);
	public void delete(Owner owner);
	public void update(Owner owner);
	
	public void duplicateCheck(String user_id);
	public void passwordCheck(Owner owner);
}
