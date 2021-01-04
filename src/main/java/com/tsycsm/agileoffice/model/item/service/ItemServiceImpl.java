package com.tsycsm.agileoffice.model.item.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsycsm.agileoffice.model.domain.Item;
import com.tsycsm.agileoffice.model.item.repository.ItemDAO;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private ItemDAO itemDAO;

	@Override
	public List selectByOwner(int owner_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void regist(Item item) {
		System.out.println("service Àü");
	
		itemDAO.insert(item);
		
		System.out.println("service ÈÄ");
	}

}
