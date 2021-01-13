package com.tsycsm.agileoffice.controller.owner;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tsycsm.agileoffice.common.MessageData;
import com.tsycsm.agileoffice.exception.MailSendException;
import com.tsycsm.agileoffice.exception.OwnerException;
import com.tsycsm.agileoffice.exception.OwnerNotFoundException;
import com.tsycsm.agileoffice.exception.OwnerPasswordFailException;
import com.tsycsm.agileoffice.model.domain.Owner;
import com.tsycsm.agileoffice.model.owner.service.OwnerService;

@Controller
public class OwnerController {
	private static final Logger logger = LoggerFactory.getLogger(OwnerController.class);
	
	@Autowired
	private OwnerService ownerService;
	
	
	/************************************************
	  
	  
	 * ***********************************************/
	
	
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
	
	/************************************************
	  main 폴더에서 이동
	  
	 * ***********************************************/
	@RequestMapping(value="/main/ownerMain", method=RequestMethod.GET)
	public String viewOwnerMain() {
		return "main/owner_main_temp";
	}
	
	
	/************************************************
	  owner 등록, 중복체크,  로그인, 로그아웃
	  
	 * ***********************************************/
	
	
	//owner-member 등록
	@RequestMapping(value="/main/ownerRegist", method=RequestMethod.POST)
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
	
	//owner id 중복체크
	@RequestMapping(value="/main/checkid", method=RequestMethod.POST)
	@ResponseBody
	public MessageData checkId(String user_id) {
		
		ownerService.duplicateCheck(user_id);

		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		messageData.setMsg("사용가능한 아이디 입니다.");
		
		return messageData;
	}
	
	//로그인
	@RequestMapping(value="/main/ownerLogin", method=RequestMethod.POST)
	public String ownerLogin(Owner owner, HttpSession session) {
		
		Owner obj = ownerService.select(owner);
		session.setAttribute("owner", obj);
		
		return "redirect:/main/ownerMain";
	}
	
	//로그아웃
	@GetMapping(value="/main/ownerLogout")
	@ResponseBody
	public MessageData ownerLogout(HttpSession session) {
		session.removeAttribute("owner");
		
		MessageData messageData = new MessageData();
		//messageData.setResultCode(1);
		messageData.setMsg("로그아웃되었습니다.");
		messageData.setUrl("/main/ownerCredential");
		
		return messageData;
	}
	
	//회원정보 수정
	@PostMapping("/main/ownerUpdate")
	@ResponseBody
	public MessageData ownerUpdate(Owner owner, HttpSession session) {
		logger.debug("owner_id: "+owner.getOwner_id());
		logger.debug("email_id: "+owner.getEmail_id());
		logger.debug("email_server: "+owner.getEmail_server());
		logger.debug("shopname: "+owner.getShopname());
		
		ownerService.update(owner);
		session.setAttribute("owner", owner);
		
		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		messageData.setMsg("회원정보 수정되었습니다.");
		messageData.setUrl("/owner/account/mypage");
		
		return messageData;
	}
	
	//회원탈퇴
	@PostMapping(value="/main/ownerQuit")
	@ResponseBody
	public MessageData ownerQuit(HttpSession session, Owner owner) {
		
		ownerService.delete(owner);
		
		session.invalidate();
		
		MessageData messageData = new MessageData();
		//messageData.setResultCode(1);
		messageData.setMsg("회원탈퇴되었습니다. 그동안 이용해주셔서 감사합니다.");
		messageData.setUrl("/main/ownerCredential");
		
		return messageData;
	}
	
	//마이페이지 가기
	@GetMapping(value="/owner/account/mypage")
	public String viewMypage() {
		
		
		return "owner/mypage/mypage";
	}
	
	//마이페이지 비밀번호 확인 페이지 가기
	@GetMapping(value="/owner/account/checkPassword")
	public String viewCheckPassword() {
		
		
		return "owner/mypage/checkpassword";
	}
	
	//비밀번호 확인
	@RequestMapping(value="/main/checkPassword", method=RequestMethod.POST)
	@ResponseBody
	public MessageData checkPassword(Owner owner, HttpSession session) {
		
		ownerService.passwordCheck(owner);
		
		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		messageData.setMsg("비밀번호가 확인되었습니다.");
		
		return messageData;
	}
	
	//새로운 비밀번호 등록
	@PostMapping(value="/main/ownerPasswordUpdate")
	@ResponseBody
	public MessageData changePassword(Owner owner) {
		
		ownerService.update(owner);
		
		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		messageData.setMsg("비밀번호가 바뀌었습니다.");
		
		return messageData;
	}
	
	
	
	
	/************************************************
	  exception handler 메소드
	  
	 * ***********************************************/
	
	
	// 예외 핸들러 2가지 처리
	@ExceptionHandler(OwnerException.class)
	@ResponseBody
	public MessageData handleException(OwnerException e) {
		MessageData messageData = new MessageData();
		messageData.setResultCode(0);
		messageData.setMsg(e.getMessage());
		return messageData;
	}
	
	@ExceptionHandler(OwnerPasswordFailException.class)
	@ResponseBody
	public MessageData handleException(OwnerPasswordFailException e) {
		MessageData messageData = new MessageData();
		messageData.setResultCode(0);
		messageData.setMsg(e.getMessage());
		return messageData;
	}
	
	@ExceptionHandler(OwnerNotFoundException.class)
	
	public ModelAndView handleException(OwnerNotFoundException e) {
		MessageData messageData = new MessageData();
		messageData.setResultCode(0);
		messageData.setMsg(e.getMessage());
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/error/result");
		mav.addObject("messageData", messageData);
		return mav;
	}
	
	@ExceptionHandler(MailSendException.class)
	public ModelAndView handleException(MailSendException e) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("shop/error/result");
		mav.addObject("msg", e.getMessage());
		return mav;
	}
	
}







