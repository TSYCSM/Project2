package com.tsycsm.agileoffice.client.controller.review;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tsycsm.agileoffice.exception.AsyncDMLException;
import com.tsycsm.agileoffice.exception.LoginRequiredException;
import com.tsycsm.agileoffice.model.common.MessageData;
import com.tsycsm.agileoffice.model.common.Pager;
import com.tsycsm.agileoffice.model.domain.Review;
import com.tsycsm.agileoffice.model.review.service.ReviewService;

@Controller
public class ReviewController {
	private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);
	
	@Autowired
	private Pager pager;
	
	@Autowired
	private ReviewService reviewService;
	/****************************
	 review CRUD
	 ****************************/
	
	@PostMapping("/review/regist")
	@ResponseBody
	public MessageData reviewRegist(Review review) {
		reviewService.regist(review);
		MessageData messageData = new MessageData();
		messageData.setMsg("리뷰 등록 성공");
		messageData.setResultCode(1);
	
		return messageData;
	}

	@PostMapping("/review/update")
	@ResponseBody
	public MessageData updateReview(Review review) {
		reviewService.update(review);

		MessageData messageData = new MessageData();
		messageData.setMsg("리뷰 수정 성공");
		messageData.setResultCode(1);
	
		return messageData;
	}
	
	@GetMapping("/review/delete")
	@ResponseBody
	public MessageData deleteReview(int review_id) {
		reviewService.delete(review_id);
		MessageData messageData = new MessageData();
		messageData.setMsg("리뷰 삭제 성공");
		messageData.setResultCode(1);
		
		return messageData;
	}
	
	@PostMapping("/review/asyncList")
	@ResponseBody
	public Pager asyncReviewList(HttpServletRequest request, int owner_id) {
		List reviewList = reviewService.selectAllOwner(owner_id);
		
		pager.init(request, reviewList);
		
		return pager;
	}
	
	/******************************** 
	  review 등록 session 여부 판단
	 ********************************/
	@GetMapping("/review/identifyCustomer")
	@ResponseBody
	public MessageData identifyCustomer(HttpServletRequest request) {
		
		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		
		return messageData;
		
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
	
	@ExceptionHandler(LoginRequiredException.class)
	@ResponseBody
	public MessageData handleException(LoginRequiredException e) {
		MessageData messageData = new MessageData();
		messageData.setResultCode(0);
		messageData.setMsg(e.getMessage());
		
		return messageData;
	}

}







