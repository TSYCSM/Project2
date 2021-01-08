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
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public Category select(int category_id) {
		return sqlSessionTemplate.selectOne("Category.select", category_id);
	}
	
	public List selectByOwner(int owner_id) {
		return sqlSessionTemplate.selectList("Category.selectByOwner", owner_id);
	}

	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Category category) throws DMLException{
		int result = sqlSessionTemplate.insert("Category.insert", category);
		
		if(result == 0) {
			throw new DMLException("카테고리 등록 실패");
		}
	}


	@Override
	public void delete(int category_id) throws DMLException{
		int result = sqlSessionTemplate.delete("Category.delete", category_id);
		
		if(result == 0) {
			throw new DMLException("카테고리 삭제 실패");
		}
	}

	@Override
	public void update(Category category) throws DMLException{
		int result = sqlSessionTemplate.update("Category.update", category);
		System.out.println("result: "+result);
		
		if(result == 0) {
			throw new DMLException("카테고리 수정 실패");
		}
		
	}


}





