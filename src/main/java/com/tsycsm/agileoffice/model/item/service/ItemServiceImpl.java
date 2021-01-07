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
	

	@Override
	public Item select(int item_id) {
		return itemDAO.select(item_id);
	}

	@Override
	public List selectByOwner(int owner_id) {
		return itemDAO.selectByOwner(owner_id);
	}

	@Override
	public void regist(Item item, FileManager fileManager) {
		MultipartFile photo = item.getPhoto();
		String ext = fileManager.getExtend(photo.getOriginalFilename());
	
		item.setFilename(ext);
		itemDAO.insert(item);

		String newFilename = item.getItem_id() + "." + ext;
		fileManager.saveFile(fileManager.getSaveDir() + File.separator + newFilename, photo);
	}

	@Override
	public void update(Item item, FileManager fileManager) {
		MultipartFile photo = item.getPhoto();

		if(photo.getOriginalFilename() == "") {
			itemDAO.update(item);
		} else {
			String ext = fileManager.getExtend(photo.getOriginalFilename());
			fileManager.deleteFile(fileManager.getSaveDir() + File.separator + item.getItem_id() + "." + item.getFilename());
			item.setFilename(ext);
			itemDAO.update(item);
			String newFilename = item.getItem_id() + "." + ext;
			fileManager.saveFile(fileManager.getSaveDir() + File.separator + newFilename, photo);
		}
		
	}

	@Override
	public void delete(Item item, FileManager fileManager) {
		itemDAO.delete(item.getItem_id());
		fileManager.deleteFile(fileManager.getSaveDir() + File.separator + item.getItem_id() + "." + item.getFilename());
		System.out.println(fileManager.getSaveDir() + File.separator + item.getItem_id() + "." + item.getFilename());
	}

}
