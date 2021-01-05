package com.tsycsm.agileoffice.model.item.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tsycsm.agileoffice.common.FileManager;
import com.tsycsm.agileoffice.model.domain.Item;
import com.tsycsm.agileoffice.model.item.repository.ItemDAO;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private ItemDAO itemDAO;
	
	@Autowired
	private FileManager fileManager;

	@Override
	public List selectByOwner(int owner_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void regist(Item item) {
	
		
		System.out.println(item.getPrice());
		System.out.println(item.getRepImg());
		MultipartFile repImg = item.getRepImg();
	
		String ext = fileManager.getExtend(item.getRepImg().getOriginalFilename());
		String newFilename = item.getItem_id() + "." + ext;
		//fileManager.saveFile(fileManager.getSaveDir() + File.separator + newFilename, image);
		fileManager.saveFile("/resources/data" + File.separator + newFilename, repImg);

		item.setFilename(newFilename);
		itemDAO.insert(item);
		
	}

}
