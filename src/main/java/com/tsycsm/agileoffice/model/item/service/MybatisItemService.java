package com.tsycsm.agileoffice.model.item.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsycsm.agileoffice.model.item.repository.ItemDAO;

@Service
public class MybatisItemService implements ItemDAO{

	@Autowired
	private ItemDAO itemDAO;
	
	@Override
	public List selectByOwner(int owner_id) {
		List list = null;
		list = itemDAO.selectByOwner(owner_id);
		return list;
	}

}



