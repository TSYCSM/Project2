package com.tsycsm.agileoffice.model.category.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsycsm.agileoffice.exception.AsyncDMLException;
import com.tsycsm.agileoffice.exception.DMLException;
import com.tsycsm.agileoffice.model.category.repository.CategoryDAO;
import com.tsycsm.agileoffice.model.domain.Category;

@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	private CategoryDAO categoryDAO;
	
	

	@Override
	public Category select(int category_id) {
		return categoryDAO.select(category_id);
	}

	
	@Override
	public List selectByOwner(int owner_id) {
		return categoryDAO.selectByOwner(owner_id);
	}

	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Category category) throws DMLException{
		categoryDAO.insert(category);
	}

	@Override
	public void delete(int category_id) throws DMLException{
		categoryDAO.delete(category_id);
	}

	@Override
	public void update(Category category) throws AsyncDMLException{
		categoryDAO.update(category);
		
	}

}



