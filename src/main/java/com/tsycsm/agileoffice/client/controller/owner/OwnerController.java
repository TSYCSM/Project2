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
	
	/*����¡ �񵿱� ���� �̿ϼ�*/
	@RequestMapping(value="/admin/getpage", method=RequestMethod.GET)
	@ResponseBody
	public List getPage(HttpServletRequest request, int currentPage) {
		List<Integer> currenPageList =  new ArrayList<Integer>();
		currenPageList.add(currentPage);

		return currenPageList;
	}
	
	/************************************************
	  main �������� �̵�
	  
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
	  owner ���, �ߺ�üũ,  �α���, �α׾ƿ�
	  
	 ************************************************/
	
	//�α׾ƿ�
	@GetMapping(value="/main/ownerLogout")
	@ResponseBody
	public MessageData ownerLogout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("owner");
		
		MessageData messageData = new MessageData();
		//messageData.setResultCode(1);
		messageData.setMsg("�α׾ƿ��Ǿ����ϴ�.");
		messageData.setUrl("/main/ownerCredential");
		
		return messageData;
	}
	
	//ȸ������ ����
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
		messageData.setMsg("ȸ������ �����Ǿ����ϴ�.");
		messageData.setUrl("/owner/account/mypage");
		
		return messageData;
	}
	
	//ȸ��Ż��
	@PostMapping(value="/main/ownerQuit")
	@ResponseBody
	public MessageData ownerQuit(HttpServletRequest request, Owner owner) {
		
		ownerService.delete(owner);
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		MessageData messageData = new MessageData();
		//messageData.setResultCode(1);
		messageData.setMsg("ȸ��Ż��Ǿ����ϴ�. �׵��� �̿����ּż� �����մϴ�.");
		messageData.setUrl("/main/ownerCredential");
		
		return messageData;
	}
	
	//���������� ����
	@GetMapping(value="/owner/account/mypage")
	public String viewMypage(HttpServletRequest request) {
		return "owner/mypage/mypage";
	}
	
	//���������� ��й�ȣ Ȯ�� ������ ����
	@GetMapping(value="/owner/account/checkPassword")
	public String viewCheckPassword(HttpServletRequest request) {
		return "owner/mypage/checkpassword";
	}
	
	//��й�ȣ Ȯ��
	@RequestMapping(value="/main/checkPassword", method=RequestMethod.POST)
	@ResponseBody
	public MessageData checkPassword(HttpServletRequest request, Owner owner) {
		ownerService.passwordCheck(owner);
		
		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		messageData.setMsg("��й�ȣ�� Ȯ�εǾ����ϴ�.");
		
		return messageData;
	}
	
	//���ο� ��й�ȣ ���
	@PostMapping(value="/main/ownerPasswordUpdate")
	@ResponseBody
	public MessageData changePassword(HttpServletRequest request, Owner owner) {
		ownerService.update(owner);
		
		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		messageData.setMsg("��й�ȣ�� �ٲ�����ϴ�.");
		
		return messageData;
	}
	
	//Q&A Email ������ Form
	@RequestMapping(value="/owner/qna/sendform", method=RequestMethod.GET)
	public String getQnaSendForm(HttpServletRequest request) {
		return "owner/qna/send_form";
	}
	
	//Q&A Email ������
	@RequestMapping(value="/owner/qna/send", method=RequestMethod.POST)
	public String sendEmailToAdmin(HttpServletRequest request, Email email) {
		qnaService.send(email);
		
		return "redirect:/client/owner/inventory/item/list";
	}	
	
	
	/************************************************
	  exception handler �޼ҵ�
	 ************************************************/
	
	// ���� �ڵ鷯 2���� ó��
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







