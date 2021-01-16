package com.tsycsm.agileoffice.model.admin.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tsycsm.agileoffice.model.domain.Admin;

@Repository
public class MybatisAdminDAO implements AdminDAO {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<Admin> selectByIdPassword(Admin admin) {
		return sqlSessionTemplate.selectList("selectByIdPassword", admin);
	}

}
