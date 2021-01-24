package com.tsycsm.agileoffice.rest.controller.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tsycsm.agileoffice.model.common.MessageData;
import com.tsycsm.agileoffice.model.common.Pager;
import com.tsycsm.agileoffice.model.domain.Owner;
import com.tsycsm.agileoffice.model.owner.service.OwnerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RestMainController {
	
	@Autowired
	private OwnerService ownerService;
	
	@PostMapping("/getPager")
	public Pager getPager(int curPage, int listSize) {
		Pager pager = new Pager();
		pager.init(curPage, listSize);
	
		return pager;
	}
	
	//owner-member 등록
	@RequestMapping(value="/main/ownerRegist", method=RequestMethod.POST)
	public ResponseEntity<MessageData> ownerMemberRegist(Owner owner) {
		log.debug("owner의 user_id "+owner.getUser_id());
		log.debug("owner의 password "+owner.getPassword());
		log.debug("owner의 shopname "+owner.getShopname());
		log.debug("owner의 email_id "+owner.getEmail_id());
		log.debug("owner의 email_server "+owner.getEmail_server());
		ownerService.duplicateCheck(owner.getUser_id());
		ownerService.regist(owner);
		
		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		messageData.setMsg("회원이 등록 되었습니다.");
		
		ResponseEntity<MessageData> entity = ResponseEntity.ok().body(messageData);
		
		return entity;
	}	
	
	//owner id 중복체크
	@RequestMapping(value="/main/checkid", method=RequestMethod.POST)
	public ResponseEntity<MessageData> checkId(String user_id) {
		
		ownerService.duplicateCheck(user_id);

		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		messageData.setMsg("사용가능한 아이디 입니다.");
		
		ResponseEntity<MessageData> entity = ResponseEntity.ok().body(messageData);
		
		return entity;
	}

}
