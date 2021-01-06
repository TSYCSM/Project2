package com.tsycsm.agileoffice.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tsycsm.agileoffice.model.domain.Owner;

@Controller
@RequestMapping("/member")
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@RequestMapping(value="/list")
	public String memberList(int currentPage) {
		System.out.println("currentPage: "+currentPage);
		return "/admin/member/memberlist";
	}
	
	@RequestMapping(value="/detail")
	public String memberDetail(int currentPage) {
		System.out.println("currentPage: "+currentPage);
		return "/admin/member/memberdetail";
	}
	
	/*페이징 비동기 구현 미완성*/
	@RequestMapping(value="/getpage", method=RequestMethod.GET)
	@ResponseBody
	public List getPage(int currentPage) {
		List<Integer> currenPageList =  new ArrayList<Integer>();
		currenPageList.add(currentPage);

		return currenPageList;
	}
	
	//owner-member 등록
	@RequestMapping(value="/regist", method=RequestMethod.POST, produces="text/html;charset=utf-8")
	@ResponseBody
	public String ownerMemberRegist(Owner owner) {
		logger.debug("owner의 user_id "+owner.getUser_id());
		logger.debug("owner의 password "+owner.getPassword());
		logger.debug("owner의 shopname "+owner.getShopname());
		logger.debug("owner의 email_id "+owner.getEmail_id());
		logger.debug("owner의 email_server "+owner.getEmail_server());
		
		return "비동기까지 되네?";
		
	}
}







