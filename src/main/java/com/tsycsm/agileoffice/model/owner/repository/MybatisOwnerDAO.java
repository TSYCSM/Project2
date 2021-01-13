package com.tsycsm.agileoffice.model.owner.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tsycsm.agileoffice.exception.OwnerException;
import com.tsycsm.agileoffice.exception.OwnerNotFoundException;
import com.tsycsm.agileoffice.exception.OwnerPasswordFailException;
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
			throw new OwnerNotFoundException("�α��� ������ �ùٸ��� �ʽ��ϴ�.");
		}
		
		return obj;
	}

	@Override
	public void insert(Owner owner) throws OwnerException{
		int result = sqlSessionTemplate.insert("Owner.insert", owner);
		
		if(result == 0) {
			throw new OwnerException("ȸ�� ��� ����");
			
		}
	}

	@Override
	public void delete(Owner owner) throws OwnerException{
		int result = sqlSessionTemplate.insert("Owner.delete", owner);
		
		if(result == 0) {
			throw new OwnerException("ȸ�� ���� ����");
			
		}
			
	}

	@Override
	public void update(Owner owner) throws OwnerException{
		int result = sqlSessionTemplate.update("Owner.update", owner);
	}

	public void duplicateCheck(String user_id) throws OwnerException{

		List list = sqlSessionTemplate.selectList("Owner.duplicateCheck", user_id);
		if(list.size() > 0) {
			throw new OwnerException("�ߺ��� ID�Դϴ�.");
		}
	}

	@Override
	public void passwordCheck(Owner owner)  throws OwnerPasswordFailException{
		Owner obj = sqlSessionTemplate.selectOne("Owner.select", owner);
		
		if(obj==null) {
			throw new OwnerPasswordFailException("��й�ȣ�� �ٸ��ϴ�.");
		}
		
				
	}

}










