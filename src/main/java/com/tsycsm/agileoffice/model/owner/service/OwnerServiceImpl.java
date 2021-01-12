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
		//��й�ȣ ��ȣȭ �ϱ�
		String secureData = secureManager.getSecureData(owner.getPassword());
		System.out.println(secureData);
		owner.setPassword(secureData);
		
		//db�� insert�ϱ�
		ownerDAO.insert(owner);
		
		//�̸��� ������
		String name = owner.getShopname();
		String email = owner.getEmail_id()+"@"+owner.getEmail_server();
		mailSender.send(email, name+"�� [Agile Office]ȸ������", "ȸ�������� �������� ���ϵ帳�ϴ�!!");
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









