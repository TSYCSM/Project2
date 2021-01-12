package com.tsycsm.agileoffice.model.owner.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsycsm.agileoffice.common.MailSender;
import com.tsycsm.agileoffice.common.SecureManager;
import com.tsycsm.agileoffice.exception.MailSendException;
import com.tsycsm.agileoffice.exception.OwnerException;
import com.tsycsm.agileoffice.exception.OwnerNotFoundException;
import com.tsycsm.agileoffice.model.domain.Owner;
import com.tsycsm.agileoffice.model.owner.repository.OwnerDAO;

@Service
public class OwnerServiceImpl implements OwnerService{
	@Autowired
	private OwnerDAO ownerDAO;
	
	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private SecureManager secureManager;
	
	@Override
	public List selectAll() {
		return ownerDAO.selectAll();
	}

	@Override
	public Owner select(Owner owner) throws OwnerNotFoundException{
		String secureData = secureManager.getSecureData(owner.getPassword());
		owner.setPassword(secureData);
		Owner obj = ownerDAO.select(owner);
		
		return obj;
	}

	@Override
	public void regist(Owner owner) throws OwnerException, MailSendException{
		//비밀번호 암호화 하기
		String secureData = secureManager.getSecureData(owner.getPassword());
		System.out.println(secureData);
		owner.setPassword(secureData);
		
		//db에 insert하기
		ownerDAO.insert(owner);
		
		//이메일 보내기
		String name = owner.getShopname();
		String email = owner.getEmail_id()+"@"+owner.getEmail_server();
		mailSender.send(email, name+"님 [Agile Office]회원가입", "회원가입을 진심으로 축하드립니다!!");
	}

	@Override
	public void delete(Owner owner) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Owner owner) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void duplicateCheck(String user_id) throws OwnerException{
		ownerDAO.duplicateCheck(user_id);
	}

	
	

}









