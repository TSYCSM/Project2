package com.tsycsm.agileoffice.rest.controller.review;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tsycsm.agileoffice.client.controller.review.ReviewController;
import com.tsycsm.agileoffice.model.common.MessageData;
import com.tsycsm.agileoffice.model.common.Pager;
import com.tsycsm.agileoffice.model.domain.Review;
import com.tsycsm.agileoffice.model.review.service.ReviewService;

@RestController
public class RestReviewController {
	private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);
	
	@Autowired
	private Pager pager;
	
	@Autowired
	private ReviewService reviewService;
	/****************************
	 review CRUD
	 ****************************/
	
	@PostMapping("/review/regist")
	public ResponseEntity<MessageData> reviewRegist(Review review) {
		reviewService.regist(review);
		MessageData messageData = new MessageData();
		messageData.setMsg("리뷰 등록 성공");
		messageData.setResultCode(1);
		
		ResponseEntity<MessageData> entity = ResponseEntity.ok().body(messageData);
	
		return entity;
	}

	@PostMapping("/review/update")
	public ResponseEntity<MessageData> updateReview(Review review) {
		reviewService.update(review);

		MessageData messageData = new MessageData();
		messageData.setMsg("리뷰 수정 성공");
		messageData.setResultCode(1);
	
		ResponseEntity<MessageData> entity = ResponseEntity.ok().body(messageData);
	
		return entity;
	}
	
	@GetMapping("/review/delete")
	public ResponseEntity<MessageData> deleteReview(int review_id) {
		reviewService.delete(review_id);
		MessageData messageData = new MessageData();
		messageData.setMsg("리뷰 삭제 성공");
		messageData.setResultCode(1);
		
		ResponseEntity<MessageData> entity = ResponseEntity.ok().body(messageData);
		
		return entity;
	}
	
	@PostMapping("/review/asyncList")
	public Pager asyncReviewList(HttpServletRequest request, int owner_id) {
		List reviewList = reviewService.selectAllOwner(owner_id);
		
		pager.init(request, reviewList);
		
		return pager;
	}
	
	/******************************** 
	  review 등록 session 여부 판단
	 ********************************/
	@GetMapping("/review/identifyCustomer")
	public ResponseEntity<MessageData> identifyCustomer(HttpServletRequest request) {
		
		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		
		ResponseEntity<MessageData> entity = ResponseEntity.ok().body(messageData);
		
		return entity;
		
	}
}
