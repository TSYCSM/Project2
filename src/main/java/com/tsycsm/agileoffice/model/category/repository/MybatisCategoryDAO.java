package com.tsycsm.agileoffice.model.category.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tsycsm.agileoffice.model.domain.Category;

@Repository
public class MybatisCategoryDAO implements CategoryDAO{
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	
	public List selectByOwner(int owner_id) {
		List list = null;
		list = sessionTemplate.selectList("Item.selectByOwner", owner_id);
		return list;
	}

	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Category category) {
		sessionTemplate.insert("Category.insert", category);
	}


}





