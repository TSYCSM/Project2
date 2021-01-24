package com.tsycsm.agileoffice.model.review.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tsycsm.agileoffice.exception.AsyncInventoryDMLException;
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
	public void insert(Review review) throws AsyncInventoryDMLException{
		int result = sqlSessionTemplate.insert("Review.insert", review);
		
		if(result==0) {
			throw new AsyncInventoryDMLException("리뷰 등록 실패");
		}
	}

	@Override
	public void update(Review review) {
		int result = sqlSessionTemplate.update("Review.update", review);

		if(result==0) {
			throw new AsyncInventoryDMLException("리뷰 수정 실패");
		}
	}

	@Override
	public void delete(int review_id) {
		int result = sqlSessionTemplate.delete("Review.delete", review_id);
		
		if(result == 0) {
			throw new AsyncInventoryDMLException("리뷰 삭제 실패");
		}
	}
	
}
