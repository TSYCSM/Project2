package com.tsycsm.agileoffice.model.category.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsycsm.agileoffice.model.category.repository.CategoryDAO;
import com.tsycsm.agileoffice.model.domain.Category;

@Service
public class MybatisCategoryService implements CategoryService{
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Override
	public List selectByOwner(int owner_id) {
		List list = null;
		list = categoryDAO.selectByOwner(owner_id);
		return list;
	}

	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Category category) {
		categoryDAO.insert(category);
	}

}



