package com.tsycsm.agileoffice.model.category.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tsycsm.agileoffice.model.domain.Category;

@Service
public interface CategoryService {
	public List selectByOwner(int owner_id);
	public List selectAll();
	public void insert(Category category);
}
