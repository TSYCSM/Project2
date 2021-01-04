package com.tsycsm.agileoffice.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/member")
public class MemberController {
	/* service를 여러 개 autowired 해도 될까???? ㅠㅠㅠㅠ(1/3) */
	
	
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
}







