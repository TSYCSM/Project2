package com.tsycsm.agileoffice.model.category.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tsycsm.agileoffice.exception.DMLException;
import com.tsycsm.agileoffice.model.domain.Category;

@Repository
public class MybatisCategoryDAO implements CategoryDAO{
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	
	@Override
	public Category select(int category_id) {
		return sessionTemplate.selectOne("Category.select", category_id);
	}
	
	public List selectByOwner(int owner_id) {
		return sessionTemplate.selectList("Category.selectByOwner", owner_id);
	}

	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Category category) throws DMLException{
		int result = sessionTemplate.insert("Category.insert", category);
		
		if(result == 0) {
			throw new DMLException("ī�װ� ��� ����");
		}
	}


	@Override
	public void delete(int category_id) throws DMLException{
		int result = sessionTemplate.delete("Category.delete", category_id);
		
		if(result == 0) {
			throw new DMLException("ī�װ� ���� ����");
		}
	}

	@Override
	public void update(Category category) throws DMLException{
		int result = sessionTemplate.update("Category.update", category);
		System.out.println("result: "+result);
		
		if(result == 0) {
			throw new DMLException("ī�װ� ���� ����");
		}
		
	}


}





