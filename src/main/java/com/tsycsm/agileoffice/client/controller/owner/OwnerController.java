package com.tsycsm.agileoffice.client.controller.owner;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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

import com.tsycsm.agileoffice.exception.MailSendException;
import com.tsycsm.agileoffice.exception.OwnerException;
import com.tsycsm.agileoffice.exception.OwnerNotFoundException;
import com.tsycsm.agileoffice.exception.OwnerPasswordFailException;
import com.tsycsm.agileoffice.model.common.MessageData;
import com.tsycsm.agileoffice.model.domain.Email;
import com.tsycsm.agileoffice.model.domain.Owner;
import com.tsycsm.agileoffice.model.owner.service.OwnerService;
import com.tsycsm.agileoffice.model.qna.service.QnaService;

@Controller
public class OwnerController {
	private static final Logger logger = LoggerFactory.getLogger(OwnerController.class);
	
	@Autowired
	private OwnerService ownerService;
	
	@Autowired
	private QnaService qnaService;
	
	
	/************************************************
	  
	  
	 * ***********************************************/
	
	
	@RequestMapping(value="/admin/list")
	public String memberList(HttpServletRequest request, int currentPage) {
		System.out.println("currentPage: "+currentPage);
		return "/admin/member/memberlist";
	}
	
	@RequestMapping(value="/admin/memberdetail")
	public String memberDetail(HttpServletRequest request, int currentPage) {
		System.out.println("currentPage: "+currentPage);
		return "/admin/member/memberdetail";
	}
	
	/*페이징 비동기 구현 미완성*/
	@RequestMapping(value="/admin/getpage", method=RequestMethod.GET)
	@ResponseBody
	public List getPage(HttpServletRequest request, int currentPage) {
		List<Integer> currenPageList =  new ArrayList<Integer>();
		currenPageList.add(currentPage);

		return currenPageList;
	}
	
	/************************************************
	  main 폴더에서 이동
	  
	 ************************************************/
	@RequestMapping(value="/main/ownerMain", method=RequestMethod.GET)
	public String viewOwnerMain(HttpServletRequest request) {
		return "main/owner_main_temp";
	}
	
	@RequestMapping(value="/main/customerCredential", method=RequestMethod.GET)
	public String viewPreOrder(HttpServletRequest request) {
		HttpSession session = null;
		session = request.getSession();
		
		session.removeAttribute("customer");
		
		return "main/customer_credential";
	}
	
	/************************************************
	  owner 등록, 중복체크,  로그인, 로그아웃
	  
	 ************************************************/
	
	//로그아웃
	@GetMapping(value="/main/ownerLogout")
	@ResponseBody
	public MessageData ownerLogout(HttpServletRequest request) {
		HttpSession session = request.getSession();
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
	public MessageData ownerUpdate(Owner owner, HttpServletRequest request) {
		logger.debug("owner_id: "+owner.getOwner_id());
		logger.debug("email_id: "+owner.getEmail_id());
		logger.debug("email_server: "+owner.getEmail_server());
		logger.debug("shopname: "+owner.getShopname());
		
		ownerService.update(owner);
		
		HttpSession session = request.getSession();
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
	public MessageData ownerQuit(HttpServletRequest request, Owner owner) {
		
		ownerService.delete(owner);
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		MessageData messageData = new MessageData();
		//messageData.setResultCode(1);
		messageData.setMsg("회원탈퇴되었습니다. 그동안 이용해주셔서 감사합니다.");
		messageData.setUrl("/main/ownerCredential");
		
		return messageData;
	}
	
	//마이페이지 가기
	@GetMapping(value="/owner/account/mypage")
	public String viewMypage(HttpServletRequest request) {
		return "owner/mypage/mypage";
	}
	
	//마이페이지 비밀번호 확인 페이지 가기
	@GetMapping(value="/owner/account/checkPassword")
	public String viewCheckPassword(HttpServletRequest request) {
		return "owner/mypage/checkpassword";
	}
	
	//비밀번호 확인
	@RequestMapping(value="/main/checkPassword", method=RequestMethod.POST)
	@ResponseBody
	public MessageData checkPassword(HttpServletRequest request, Owner owner) {
		ownerService.passwordCheck(owner);
		
		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		messageData.setMsg("비밀번호가 확인되었습니다.");
		
		return messageData;
	}
	
	//새로운 비밀번호 등록
	@PostMapping(value="/main/ownerPasswordUpdate")
	@ResponseBody
	public MessageData changePassword(HttpServletRequest request, Owner owner) {
		ownerService.update(owner);
		
		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		messageData.setMsg("비밀번호가 바뀌었습니다.");
		
		return messageData;
	}
	
	//Q&A Email 보내기 Form
	@RequestMapping(value="/owner/qna/sendform", method=RequestMethod.GET)
	public String getQnaSendForm(HttpServletRequest request) {
		return "owner/qna/send_form";
	}
	
	//Q&A Email 보내기
	@RequestMapping(value="/owner/qna/send", method=RequestMethod.POST)
	public String sendEmailToAdmin(HttpServletRequest request, Email email) {
		qnaService.send(email);
		
		return "redirect:/client/owner/inventory/item/list";
	}	
	
	
	/************************************************
	  exception handler 메소드
	 ************************************************/
	
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
		mav.setViewName("error/result");
		mav.addObject("messageData", messageData);
		return mav;
	}
	
	@ExceptionHandler(MailSendException.class)
	public ModelAndView handleException(MailSendException e) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("error/result");
		mav.addObject("msg", e.getMessage());
		return mav;
	}
	
}







