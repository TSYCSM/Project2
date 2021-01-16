package com.tsycsm.agileoffice.model.item.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsycsm.agileoffice.model.common.FileManager;
import com.tsycsm.agileoffice.model.common.ItemConverter;
import com.tsycsm.agileoffice.model.domain.Item;
import com.tsycsm.agileoffice.model.item.repository.ItemDAO;

@Service
public class DumpServiceImpl implements DumpService {
	
	@Autowired
	private ItemConverter itemConverter;
	
	@Autowired
	private FileManager fileManager;
	
	@Autowired
	private ItemDAO itemDAO;

	@Override
	public void regist(String path, int owner_id) {
		List<Item> itemList = itemConverter.convertFromFile(path, owner_id);
		
		for(int i=0; i<itemList.size(); i++) {
			Item item = itemList.get(i);
			itemDAO.insert(item);

			item.setFilename(fileManager.getExtend(item.getFilename()));
			itemDAO.update(item);
		}
	}

}
