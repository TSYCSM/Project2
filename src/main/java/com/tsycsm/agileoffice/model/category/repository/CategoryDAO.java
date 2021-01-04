package com.tsycsm.agileoffice.model.category.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tsycsm.agileoffice.model.domain.Category;

@Repository
public interface CategoryDAO {
	public List selectByOwner(int owner_id);
	public List selectAll();
	public void insert(Category category);
	
}
