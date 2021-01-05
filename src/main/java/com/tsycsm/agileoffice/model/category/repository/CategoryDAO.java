package com.tsycsm.agileoffice.model.category.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tsycsm.agileoffice.model.domain.Category;

@Repository
public interface CategoryDAO {
	public List selectByOwner(int owner_id);
	public List selectAll();
	public Category select(int category_id);
	public void insert(Category category);
	public void delete(int category_id);
	public void update(Category category);
	
}
