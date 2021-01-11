package com.tsycsm.agileoffice.model.review.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsycsm.agileoffice.exception.AsyncDMLException;
import com.tsycsm.agileoffice.model.domain.Review;
import com.tsycsm.agileoffice.model.review.repository.ReviewDAO;

@Service
public class ReviewServiceImpl implements ReviewService{
	@Autowired
	private ReviewDAO reviewDAO;
	
	
	@Override
	public List selectAllOwner(int owner_id) {
		return reviewDAO.selectAllOwner(owner_id);
	}

	@Override
	public Review select(int review_id) {

		return null;
	}

	@Override
	public void regist(Review reivew) throws AsyncDMLException{
		reviewDAO.insert(reivew);
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








