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

import com.tsycsm.agileoffice.exception.MailSendException;
import com.tsycsm.agileoffice.exception.OwnerDMLException;
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
	
	/*����¡ �񵿱� ���� �̿ϼ�*/
	@RequestMapping(value="/admin/getpage", method=RequestMethod.GET)
	@ResponseBody
	public List getPage(int currentPage) {
		List<Integer> currenPageList =  new ArrayList<Integer>();
		currenPageList.add(currentPage);

		return currenPageList;
	}
	
	//owner-member ���
	@RequestMapping(value="/main/ownerregist", method=RequestMethod.POST, produces="text/html;charset=utf-8")
	@ResponseBody
	public String ownerMemberRegist(Owner owner) {
		logger.debug("owner�� user_id "+owner.getUser_id());
		logger.debug("owner�� password "+owner.getPassword());
		logger.debug("owner�� shopname "+owner.getShopname());
		logger.debug("owner�� email_id "+owner.getEmail_id());
		logger.debug("owner�� email_server "+owner.getEmail_server());
		
		ownerService.regist(owner);
		
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"result\": 1,");
		sb.append("\"msg\": \"ȸ�� ��� ����\"");
		sb.append("}");
		return sb.toString();
	}
	
	@RequestMapping(value="/main/mainchoice", method=RequestMethod.GET)
	public String viewMainChoice() {
		return "main/main_choice";
	}
	
	//owner id �ߺ�üũ
	@RequestMapping(value="/main/checkid", method=RequestMethod.POST,
			produces="text/html;charset=utf-8")
	@ResponseBody
	public String checkId(String user_id) {
		
		int result = ownerService.checkId(user_id);
		
		return Integer.toString(result);
	}
	
	
	
	// ���� �ڵ鷯 2���� ó��
	@ExceptionHandler(OwnerDMLException.class)
	@ResponseBody
	public String handleException(OwnerDMLException e) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"result\": 0,");
		sb.append("\"msg\": \"" + e.getMessage() + "\"");
		sb.append("}");
		return sb.toString();
	}
	
	@ExceptionHandler(MailSendException.class)
	public ModelAndView handleException(MailSendException e) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("shop/error/result");
		mav.addObject("msg", e.getMessage());
		return mav;
	}
	
}







