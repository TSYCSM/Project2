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
	  owner 등록, 중복체크,  로그인, 로그아웃
	 ************************************************/
	
	//owner-member 등록
	@PostMapping("/main/owner")
	public ResponseEntity<MessageData> ownerMemberRegist(@RequestBody Owner owner) {
	
		ownerService.duplicateCheck(owner.getUser_id());
		ownerService.regist(owner);
		
		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		messageData.setMsg("회원이 등록 되었습니다.");
		
		ResponseEntity<MessageData> entity = ResponseEntity.ok().body(messageData);
		
		return entity;
	}	
	
	//owner id 중복체크
	@PostMapping("/main/owner/{user_id}")
	public ResponseEntity<MessageData> checkId(@PathVariable String user_id) {
		
		ownerService.duplicateCheck(user_id);

		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		messageData.setMsg("사용가능한 아이디 입니다.");
		
		ResponseEntity<MessageData> entity = ResponseEntity.ok().body(messageData);
		
		return entity;
	}
	
	
	//로그아웃
	@GetMapping("/main/owner")
	public ResponseEntity<MessageData> ownerLogout(HttpSession session) {
		session.removeAttribute("owner");
		
		MessageData messageData = new MessageData();
		//messageData.setResultCode(1);
		messageData.setMsg("로그아웃되었습니다.");
		messageData.setUrl("/client/main/ownerCredential");
		
		ResponseEntity<MessageData> entity = ResponseEntity.ok().body(messageData);
		
		return entity;
	}
	
	//회원정보 수정
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
		messageData.setMsg("회원정보 수정되었습니다.");
		messageData.setUrl("/client/owner/account/mypage");
		
		ResponseEntity<MessageData> entity = ResponseEntity.ok().body(messageData);
		
		return entity;
	}
	
	//회원탈퇴
	@DeleteMapping("/main/owner")
	public ResponseEntity<MessageData> ownerQuit(HttpSession session, @RequestBody Owner owner) {
		
		ownerService.delete(owner);
		
		session.invalidate();
		
		MessageData messageData = new MessageData();
		//messageData.setResultCode(1);
		messageData.setMsg("회원탈퇴되었습니다. 그동안 이용해주셔서 감사합니다.");
		messageData.setUrl("/client/main/ownerCredential");
		
		ResponseEntity<MessageData> entity = ResponseEntity.ok().body(messageData);
		
		return entity;
	}
	
	
	//비밀번호 확인
	@RequestMapping(value="/main/checkPassword", method=RequestMethod.POST)
	public ResponseEntity<MessageData> checkPassword(Owner owner) {
		ownerService.passwordCheck(owner);
		
		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		messageData.setMsg("비밀번호가 확인되었습니다.");
		
		ResponseEntity<MessageData> entity = ResponseEntity.ok().body(messageData);
		
		return entity;
	}
	
	//새로운 비밀번호 등록
	@PostMapping(value="/main/ownerPasswordUpdate")
	public ResponseEntity<MessageData> changePassword(Owner owner) {
		ownerService.update(owner);
		
		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		messageData.setMsg("비밀번호가 바뀌었습니다.");
		
		ResponseEntity<MessageData> entity = ResponseEntity.ok().body(messageData);
		
		return entity;
	}
}
