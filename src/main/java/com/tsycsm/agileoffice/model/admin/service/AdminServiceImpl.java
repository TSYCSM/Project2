package com.tsycsm.agileoffice.model.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsycsm.agileoffice.model.admin.repository.AdminDAO;
import com.tsycsm.agileoffice.model.domain.Admin;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	AdminDAO adminDAO;

	@Override
	public List<Admin> loginCheck(Admin admin) {
		return adminDAO.selectByIdPassword(admin);
	}

}
