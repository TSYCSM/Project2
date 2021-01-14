package com.tsycsm.agileoffice.client.controller.main;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tsycsm.agileoffice.model.common.MessageData;
import com.tsycsm.agileoffice.model.common.Pager;
import com.tsycsm.agileoffice.model.domain.Owner;
import com.tsycsm.agileoffice.model.owner.service.OwnerService;

@Controller
public class MainController {
	public static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	private OwnerService ownerService;
	
	@RequestMapping(value="/main/first", method=RequestMethod.GET)
	public String viewFirst() {
		return "main/first";
	}
	
	@RequestMapping(value="/main/ownerCredential", method=RequestMethod.GET)
	public String viewSignup() {
		return "main/owner_credential";
	}

	
	@RequestMapping(value="/error/result", method=RequestMethod.GET)
	public String viewErrorPage() {
		return "error/result";
	}
	
	@PostMapping("/getPager")
	@ResponseBody
	public Pager getPager(int curPage, int listSize) {
		Pager pager = new Pager();
		pager.init(curPage, listSize);
	
		return pager;
	}
	
	
	
	
	// ---------------------------------
	// Owner : Credential Box
	// ---------------------------------
	
	//owner-member ���
	@RequestMapping(value="/main/ownerRegist", method=RequestMethod.POST)
	@ResponseBody
	public MessageData ownerMemberRegist(Owner owner) {
		logger.debug("owner�� user_id "+owner.getUser_id());
		logger.debug("owner�� password "+owner.getPassword());
		logger.debug("owner�� shopname "+owner.getShopname());
		logger.debug("owner�� email_id "+owner.getEmail_id());
		logger.debug("owner�� email_server "+owner.getEmail_server());
		ownerService.duplicateCheck(owner.getUser_id());
		ownerService.regist(owner);
		
		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		messageData.setMsg("ȸ���� ��� �Ǿ����ϴ�.");
		return messageData;
	}	
	
	//owner id �ߺ�üũ
	@RequestMapping(value="/main/checkid", method=RequestMethod.POST)
	@ResponseBody
	public MessageData checkId(String user_id) {
		
		ownerService.duplicateCheck(user_id);

		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		messageData.setMsg("��밡���� ���̵� �Դϴ�.");
		
		return messageData;
	}
	
	//�α���
	@RequestMapping(value="/main/ownerLogin", method=RequestMethod.POST)
	public String ownerLogin(Owner owner, HttpSession session) {
		
		Owner obj = ownerService.select(owner);
		session.setAttribute("owner", obj);
		
		return "redirect:/client/main/ownerMain";
	}

	
	
}









