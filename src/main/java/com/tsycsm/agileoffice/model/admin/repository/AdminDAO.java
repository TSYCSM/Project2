package com.tsycsm.agileoffice.model.admin.repository;

import java.util.List;

import com.tsycsm.agileoffice.model.domain.Admin;

public interface AdminDAO {
	public List<Admin> selectByIdPassword(Admin admin);
}
