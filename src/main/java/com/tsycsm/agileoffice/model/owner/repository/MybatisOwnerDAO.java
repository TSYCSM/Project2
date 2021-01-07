package com.tsycsm.agileoffice.model.owner.repository;

import java.util.List;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tsycsm.agileoffice.exception.OwnerException;
import com.tsycsm.agileoffice.exception.OwnerNotFoundException;
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
	public Owner select(Owner Owner) throws OwnerNotFoundException{
		Owner obj = sqlSessionTemplate.selectOne("Owner.select", Owner);
		
		if(obj==null) {
			throw new OwnerNotFoundException("로그인 정보가 올바르지 않습니다.");
		}
		
		return obj;
	}

	@Override
	public void insert(Owner owner) throws OwnerException{
		int result = sqlSessionTemplate.insert("Owner.insert", owner);
		
		if(result == 0) {
			throw new OwnerException("회원 등록 실패");
			
		}
	}

	@Override
	public void delete(Owner owner) throws OwnerException{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Owner owner) throws OwnerException{
		// TODO Auto-generated method stub
		
	}

	public void duplicateCheck(String user_id) throws OwnerException{

		List list = sqlSessionTemplate.selectList("Owner.duplicateCheck", user_id);
		if(list.size() > 0) {
			throw new OwnerException("중복된 ID입니다.");
		}
	}

}










