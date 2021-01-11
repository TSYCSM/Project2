package com.tsycsm.agileoffice.controller.review;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tsycsm.agileoffice.common.MessageData;
import com.tsycsm.agileoffice.exception.AsyncDMLException;
import com.tsycsm.agileoffice.model.domain.Review;
import com.tsycsm.agileoffice.model.review.service.ReviewService;

@Controller
public class ReviewController {
	private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);
	
	@Autowired
	private ReviewService reviewService;
	/****************************
	 review CRUD
	 ****************************/
	
	@PostMapping("/review/regist")
	@ResponseBody
	public MessageData reviewRegist(Review review) {
		logger.debug("review의 owner_id: "+review.getOwner_id());
		logger.debug("review의 customer_id: "+review.getCustomer_id());
		logger.debug("review의 item_id: "+review.getItem_id());
		logger.debug("review의 comments: "+review.getComments());
		
		reviewService.regist(review);
		MessageData messageData = new MessageData();
		messageData.setMsg("리뷰 등록 성공");
		messageData.setResultCode(1);
	
		return messageData;
	}
	
	@PostMapping("/review/asyncList")
	@ResponseBody
	public List<Review> asyncReviewList(int owner_id) {
		List reviewList = reviewService.selectAllOwner(owner_id);
	
		return reviewList;
	}
	
	
	
	/****************************
	 exception handler
	 ****************************/
	
	@ExceptionHandler(AsyncDMLException.class)
	@ResponseBody
	public MessageData handleException(AsyncDMLException e) {
		MessageData messageData = new MessageData();
		messageData.setMsg(e.getMessage());
		messageData.setResultCode(0);
	
		return messageData;
	}

}







