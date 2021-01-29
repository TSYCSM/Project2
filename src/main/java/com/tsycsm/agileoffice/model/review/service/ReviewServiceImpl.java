package com.tsycsm.agileoffice.model.review.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsycsm.agileoffice.exception.AsyncInventoryDMLException;
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
	public void regist(Review review) throws AsyncInventoryDMLException{
		reviewDAO.insert(review);
	}

	@Override
	public void update(Review review) throws AsyncInventoryDMLException {
		reviewDAO.update(review);
	}

	@Override
	public void delete(int review_id) throws AsyncInventoryDMLException {
		reviewDAO.delete(review_id);
	}

}








