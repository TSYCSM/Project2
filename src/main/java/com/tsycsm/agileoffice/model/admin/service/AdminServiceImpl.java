package com.tsycsm.agileoffice.model.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsycsm.agileoffice.exception.AdminNotFoundException;
import com.tsycsm.agileoffice.model.admin.repository.AdminDAO;
import com.tsycsm.agileoffice.model.domain.Admin;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	AdminDAO adminDAO;

	@Override
	public void loginCheck(Admin admin) {
		List<Admin> adminList = adminDAO.selectByIdPassword(admin);
		
		if(adminList.size() == 0) {
			throw new AdminNotFoundException("아이디와 비밀번호를 확인해주세요");
		} 
	}

}
