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
	public Item select(int item_id) {
		return itemDAO.select(item_id);
	}

	@Override
	public List selectByOwner(int owner_id) {
		return itemDAO.selectByOwner(owner_id);
	}

	@Override
	public void regist(Item item) {
		MultipartFile photo = item.getPhoto();
		String ext = fileManager.getExtend(photo.getOriginalFilename());
	
		item.setFilename(ext);
		itemDAO.insert(item);

		String newFilename = item.getItem_id() + "." + ext;
		fileManager.saveFile(fileManager.getSaveDir() + File.separator + newFilename, photo);
	}

	@Override
	public void update(Item item) {
		itemDAO.update(item);
	}

	@Override
	public void delete(int item_id) {
		itemDAO.delete(item_id);
	}

}
