package com.tsycsm.agileoffice.model.item.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tsycsm.agileoffice.exception.AsyncInventoryDMLException;
import com.tsycsm.agileoffice.exception.InventoryDMLException;
import com.tsycsm.agileoffice.exception.AsyncInventoryNameDuplicatedException;
import com.tsycsm.agileoffice.model.common.FileManager;
import com.tsycsm.agileoffice.model.domain.Category;
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
	public void duplicationCheck(Item item) throws AsyncInventoryNameDuplicatedException {
		itemDAO.selectByNameInOwner(item);
	}
	
	@Override
	public Item selectJoinCategory(int item_id) {
		Item item = itemDAO.selectJoinCategory(item_id);
		
		if(item.getCategory() == null) {
			Category category = new Category();
			category.setCategory_name("카테고리 없음");
			item.setCategory(category);
		}
		
		return item;
	}

	@Override
	public List<Item> selectAllJoinCategory(int owner_id) {
		List<Item> itemList = itemDAO.selectAllJoinCategory(owner_id);
		for(int i=0; i<itemList.size(); i++) {
			Item item = itemList.get(i);
			if(item.getCategory() == null) {
				Category category = new Category();
				category.setCategory_name("카테고리 없음");
				item.setCategory(category);
			}
		}
		
		return itemList;
	}

	@Override
	public List<Item> selectByOwnerId(int owner_id) {
		return itemDAO.selectByOwnerId(owner_id);
	}
	
	@Override
	public List<Item> selectByMultiCategoryId(int[] category_ids, int owner_id) {
		List<Item> itemList = new ArrayList<Item>();
		Item item = new Item();
		
		for(int i=0; i<category_ids.length; i++) {
			item.setCategory_id(category_ids[i]);
			item.setOwner_id(owner_id);

			List<Item> resultList = itemDAO.selectByOwnerIdCategoryId(item);
			
			for(int a=0; a<resultList.size(); a++) {
				itemList.add(resultList.get(a));
			}
		}
		
		return itemList;
	}
	
	
	@Override
	public List<Item> selectByCategoryId(Item item) {
		return itemDAO.selectByCategoryId(item);
	}

	@Override
	public void regist(Item item, FileManager fileManager) throws InventoryDMLException {
		MultipartFile photo = item.getPhoto();
		String ext = fileManager.getExtend(photo.getOriginalFilename());
	
		item.setFilename(ext);
		itemDAO.insert(item);

		String newFilename = item.getItem_id() + "." + ext;
		
		fileManager.saveFile(fileManager.getSaveDir() + File.separator + newFilename, photo);
	}

	@Override
	public void update(Item item, FileManager fileManager) throws AsyncInventoryDMLException {
		MultipartFile photo = item.getPhoto();

		// 해결해야할 문제 : 무조건 null로 들어간다. photo를 선택하여도 null로 들어감
		
		
		if(photo != null) {
			String ext = fileManager.getExtend(photo.getOriginalFilename());
			System.out.println(fileManager.getSaveDir());
			fileManager.deleteFile(fileManager.getSaveDir() + File.separator + item.getItem_id() + "." + item.getFilename());
			item.setFilename(ext);
			itemDAO.update(item);
			String newFilename = item.getItem_id() + "." + ext;
			fileManager.saveFile(fileManager.getSaveDir() + File.separator + newFilename, photo);
		} else {
			itemDAO.update(item);
			System.out.println("null" + fileManager.getSaveDir());
		}
		
	}

	@Override
	public void delete(Item item, FileManager fileManager) throws InventoryDMLException {
		itemDAO.delete(item.getItem_id());
		fileManager.deleteFile(fileManager.getSaveDir() + File.separator + item.getItem_id() + "." + item.getFilename());
	}

}
