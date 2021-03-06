package com.tsycsm.agileoffice.model.owner.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tsycsm.agileoffice.exception.AsyncOwnerDMLException;
import com.tsycsm.agileoffice.exception.AsyncOwnerNameDuplicatedException;
import com.tsycsm.agileoffice.exception.AsyncOwnerPasswordFailException;
import com.tsycsm.agileoffice.exception.OwnerNotFoundException;
import com.tsycsm.agileoffice.model.domain.Owner;

@Repository
public class MybatisOwnerDAO implements OwnerDAO{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<Owner> selectAll() {
		return sqlSessionTemplate.selectList("Owner.selectAll");
	}

	@Override
	public Owner select(Owner owner) throws OwnerNotFoundException{
		Owner obj = sqlSessionTemplate.selectOne("Owner.select", owner);
		
		if(obj==null) {
			throw new OwnerNotFoundException("로그인 정보가 올바르지 않습니다.");
		}
		
		return obj;
	}

	@Override
	public void insert(Owner owner) throws AsyncOwnerDMLException{
		int result = sqlSessionTemplate.insert("Owner.insert", owner);
		
		if(result == 0) {
			throw new AsyncOwnerDMLException("회원 등록 실패");
			
		}
	}

	@Override
	public void delete(Owner owner) throws AsyncOwnerDMLException{
		int result = sqlSessionTemplate.delete("Owner.delete", owner);
		
		if(result == 0) {
			throw new AsyncOwnerDMLException("회원 삭제 실패");
			
		}
			
	}

	@Override
	public void update(Owner owner) throws AsyncOwnerDMLException{
		int result = 0;
		
		if(owner.getPassword() != null) {
			result = sqlSessionTemplate.update("Owner.updatePassword", owner);			
		}else {
			result = sqlSessionTemplate.update("Owner.updateWithoutPassword", owner);
		}
		
		if(result==0) {
			throw new AsyncOwnerDMLException("회원수정 실패");
		}
	}

	public void duplicateCheck(String user_id) throws AsyncOwnerNameDuplicatedException{

		List list = sqlSessionTemplate.selectList("Owner.duplicateCheck", user_id);
		if(list.size() > 0) {
			throw new AsyncOwnerNameDuplicatedException("중복된 ID입니다.");
		}
	}

	@Override
	public void passwordCheck(Owner owner)  throws AsyncOwnerPasswordFailException{
		Owner obj = sqlSessionTemplate.selectOne("Owner.select", owner);
		
		if(obj==null) {
			throw new AsyncOwnerPasswordFailException("비밀번호가 다릅니다.");
		}
		
				
	}

}










