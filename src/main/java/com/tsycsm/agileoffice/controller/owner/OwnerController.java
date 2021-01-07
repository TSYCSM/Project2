package com.tsycsm.agileoffice.controller.owner;

import java.util.ArrayList;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tsycsm.agileoffice.common.MessageData;
import com.tsycsm.agileoffice.exception.MailSendException;
import com.tsycsm.agileoffice.exception.OwnerException;
import com.tsycsm.agileoffice.model.domain.Owner;
import com.tsycsm.agileoffice.model.owner.service.OwnerService;

@Controller
public class OwnerController {
	private static final Logger logger = LoggerFactory.getLogger(OwnerController.class);
	
	@Autowired
	private OwnerService ownerService;
	
	@RequestMapping(value="/admin/list")
	public String memberList(int currentPage) {
		System.out.println("currentPage: "+currentPage);
		return "/admin/member/memberlist";
	}
	
	@RequestMapping(value="/admin/memberdetail")
	public String memberDetail(int currentPage) {
		System.out.println("currentPage: "+currentPage);
		return "/admin/member/memberdetail";
	}
	
	/*페이징 비동기 구현 미완성*/
	@RequestMapping(value="/admin/getpage", method=RequestMethod.GET)
	@ResponseBody
	public List getPage(int currentPage) {
		List<Integer> currenPageList =  new ArrayList<Integer>();
		currenPageList.add(currentPage);

		return currenPageList;
	}
	
	//owner-member 등록
	@RequestMapping(value="/main/ownerregist", method=RequestMethod.POST, produces="text/html;charset=utf-8")
	@ResponseBody
	public MessageData ownerMemberRegist(Owner owner) {
		logger.debug("owner의 user_id "+owner.getUser_id());
		logger.debug("owner의 password "+owner.getPassword());
		logger.debug("owner의 shopname "+owner.getShopname());
		logger.debug("owner의 email_id "+owner.getEmail_id());
		logger.debug("owner의 email_server "+owner.getEmail_server());
		ownerService.duplicateCheck(owner.getUser_id());
		ownerService.regist(owner);
		
		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		messageData.setMsg("회원이 등록 되었습니다.");
		
		return messageData;
	}
	
	@RequestMapping(value="/main/mainchoice", method=RequestMethod.GET)
	public String viewMainChoice() {
		return "main/main_choice";
	}
	
	
	//owner id 중복체크
	@RequestMapping(value="/main/checkid", method=RequestMethod.POST)
	@ResponseBody
	public MessageData checkId(String user_id) {
		
		System.out.println("예외처리 전");
		ownerService.duplicateCheck(user_id);
		System.out.println("예외처리 후");
		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		messageData.setMsg("사용가능한 아이디 입니다.");
		
		return messageData;
	}
	
	
	
	// 예외 핸들러 2가지 처리
	@ExceptionHandler(OwnerException.class)
	@ResponseBody
	public MessageData handleException(OwnerException e) {
		MessageData messageData = new MessageData();
		messageData.setResultCode(0);
		messageData.setMsg(e.getMessage());
		return messageData;
	}
	
	@ExceptionHandler(MailSendException.class)
	public ModelAndView handleException(MailSendException e) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("shop/error/result");
		mav.addObject("msg", e.getMessage());
		return mav;
	}
	
}







