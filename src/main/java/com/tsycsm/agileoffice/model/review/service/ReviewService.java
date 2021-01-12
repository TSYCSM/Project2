package com.tsycsm.agileoffice.model.review.service;

import java.util.List;

import com.tsycsm.agileoffice.model.domain.Review;

public interface ReviewService {
	public List selectAllOwner(int owner_id);
	public Review select(int review_id);
	public void regist(Review reivew);
	public void update(Review reivew);
	public void delete(int review_id);

}
