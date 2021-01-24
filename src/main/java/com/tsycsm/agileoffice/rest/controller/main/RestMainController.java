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
	
	//owner-member ���
	@RequestMapping(value="/main/ownerRegist", method=RequestMethod.POST)
	public ResponseEntity<MessageData> ownerMemberRegist(Owner owner) {
		log.debug("owner�� user_id "+owner.getUser_id());
		log.debug("owner�� password "+owner.getPassword());
		log.debug("owner�� shopname "+owner.getShopname());
		log.debug("owner�� email_id "+owner.getEmail_id());
		log.debug("owner�� email_server "+owner.getEmail_server());
		ownerService.duplicateCheck(owner.getUser_id());
		ownerService.regist(owner);
		
		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		messageData.setMsg("ȸ���� ��� �Ǿ����ϴ�.");
		
		ResponseEntity<MessageData> entity = ResponseEntity.ok().body(messageData);
		
		return entity;
	}	
	
	//owner id �ߺ�üũ
	@RequestMapping(value="/main/checkid", method=RequestMethod.POST)
	public ResponseEntity<MessageData> checkId(String user_id) {
		
		ownerService.duplicateCheck(user_id);

		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		messageData.setMsg("��밡���� ���̵� �Դϴ�.");
		
		ResponseEntity<MessageData> entity = ResponseEntity.ok().body(messageData);
		
		return entity;
	}

}
