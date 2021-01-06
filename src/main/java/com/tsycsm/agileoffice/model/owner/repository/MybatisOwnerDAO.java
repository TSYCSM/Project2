package com.tsycsm.agileoffice.model.owner.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tsycsm.agileoffice.exception.OwnerDMLException;
import com.tsycsm.agileoffice.model.domain.Owner;

@Repository
public class MybatisOwnerDAO implements OwnerDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	
	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Owner select(int owner_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Owner owner) throws OwnerDMLException{
		int result = sqlSessionTemplate.insert("Owner.insert", owner);
		
		if(result == 0) {
			throw new OwnerDMLException("회원 등록 실패");
			
		}
	}

	@Override
	public void delete(Owner owner) throws OwnerDMLException{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Owner owner) throws OwnerDMLException{
		// TODO Auto-generated method stub
		
	}

	public int checkId(String user_id) {

		return sqlSessionTemplate.selectOne("Owner.selectUserId", user_id);
	}

}










