package com.tsycsm.agileoffice.model.review.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tsycsm.agileoffice.exception.AsyncDMLException;
import com.tsycsm.agileoffice.model.domain.Review;

@Repository
public class MybatisReviewDAO implements ReviewDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List selectAllOwner(int owner_id) {
		return sqlSessionTemplate.selectList("Review.selectAllByOwner", owner_id);
	}

	@Override
	public Review select(int review_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Review reivew) throws AsyncDMLException{
		int result = sqlSessionTemplate.insert("Review.insert", reivew);
		
		if(result==0) {
			throw new AsyncDMLException("리뷰 등록 실패");
		}
	}

	@Override
	public void update(Review reivew) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Review reivew) {
		// TODO Auto-generated method stub
		
	}
	
}
