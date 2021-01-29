package com.tsycsm.agileoffice.rest.controller.owner;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tsycsm.agileoffice.client.controller.owner.OwnerController;
import com.tsycsm.agileoffice.model.common.MessageData;
import com.tsycsm.agileoffice.model.domain.Owner;
import com.tsycsm.agileoffice.model.owner.service.OwnerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class RestOwnerController {
	private static final Logger logger = LoggerFactory.getLogger(OwnerController.class);
	
	@Autowired
	private OwnerService ownerService;
	

	
	/************************************************
	  owner ���, �ߺ�üũ,  �α���, �α׾ƿ�
	 ************************************************/
	
	//owner-member ���
	@PostMapping("/main/owner")
	public ResponseEntity<MessageData> ownerMemberRegist(@RequestBody Owner owner) {
	
		ownerService.duplicateCheck(owner.getUser_id());
		ownerService.regist(owner);
		
		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		messageData.setMsg("ȸ���� ��� �Ǿ����ϴ�.");
		
		ResponseEntity<MessageData> entity = ResponseEntity.ok().body(messageData);
		
		return entity;
	}	
	
	//owner id �ߺ�üũ
	@PostMapping("/main/owner/{user_id}")
	public ResponseEntity<MessageData> checkId(@PathVariable String user_id) {
		
		ownerService.duplicateCheck(user_id);

		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		messageData.setMsg("��밡���� ���̵� �Դϴ�.");
		
		ResponseEntity<MessageData> entity = ResponseEntity.ok().body(messageData);
		
		return entity;
	}
	
	
	//�α׾ƿ�
	@GetMapping("/main/owner")
	public ResponseEntity<MessageData> ownerLogout(HttpSession session) {
		session.removeAttribute("owner");
		
		MessageData messageData = new MessageData();
		//messageData.setResultCode(1);
		messageData.setMsg("�α׾ƿ��Ǿ����ϴ�.");
		messageData.setUrl("/client/main/ownerCredential");
		
		ResponseEntity<MessageData> entity = ResponseEntity.ok().body(messageData);
		
		return entity;
	}
	
	//ȸ������ ����
	@PutMapping("/main/owner")
	public ResponseEntity<MessageData> ownerUpdate(HttpSession session,@RequestBody Owner owner) {
		logger.debug("owner_id: "+owner.getOwner_id());
		logger.debug("email_id: "+owner.getEmail_id());
		logger.debug("email_server: "+owner.getEmail_server());
		logger.debug("shopname: "+owner.getShopname());
		
		ownerService.update(owner);
		
		session.setAttribute("owner", owner);
		
		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		messageData.setMsg("ȸ������ �����Ǿ����ϴ�.");
		messageData.setUrl("/client/owner/account/mypage");
		
		ResponseEntity<MessageData> entity = ResponseEntity.ok().body(messageData);
		
		return entity;
	}
	
	//ȸ��Ż��
	@DeleteMapping("/main/owner")
	public ResponseEntity<MessageData> ownerQuit(HttpSession session, @RequestBody Owner owner) {
		
		ownerService.delete(owner);
		
		session.invalidate();
		
		MessageData messageData = new MessageData();
		//messageData.setResultCode(1);
		messageData.setMsg("ȸ��Ż��Ǿ����ϴ�. �׵��� �̿����ּż� �����մϴ�.");
		messageData.setUrl("/client/main/ownerCredential");
		
		ResponseEntity<MessageData> entity = ResponseEntity.ok().body(messageData);
		
		return entity;
	}
	
	
	//��й�ȣ Ȯ��
	@RequestMapping(value="/main/checkPassword", method=RequestMethod.POST)
	public ResponseEntity<MessageData> checkPassword(Owner owner) {
		ownerService.passwordCheck(owner);
		
		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		messageData.setMsg("��й�ȣ�� Ȯ�εǾ����ϴ�.");
		
		ResponseEntity<MessageData> entity = ResponseEntity.ok().body(messageData);
		
		return entity;
	}
	
	//���ο� ��й�ȣ ���
	@PostMapping(value="/main/ownerPasswordUpdate")
	public ResponseEntity<MessageData> changePassword(Owner owner) {
		ownerService.update(owner);
		
		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		messageData.setMsg("��й�ȣ�� �ٲ�����ϴ�.");
		
		ResponseEntity<MessageData> entity = ResponseEntity.ok().body(messageData);
		
		return entity;
	}
}
