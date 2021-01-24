package com.tsycsm.agileoffice.rest.controller.inventory;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import com.tsycsm.agileoffice.client.controller.inventory.InventoryController;
import com.tsycsm.agileoffice.model.category.service.CategoryService;
import com.tsycsm.agileoffice.model.common.FileManager;
import com.tsycsm.agileoffice.model.common.MessageData;
import com.tsycsm.agileoffice.model.domain.Category;
import com.tsycsm.agileoffice.model.domain.Item;
import com.tsycsm.agileoffice.model.domain.Owner;
import com.tsycsm.agileoffice.model.item.service.DumpService;
import com.tsycsm.agileoffice.model.item.service.ItemService;

@RestController
public class RestInventoryController implements ServletContextAware {

	private static final Logger logger = LoggerFactory.getLogger(InventoryController.class);

	@Autowired
	private FileManager fileManager;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ItemService itemService;

	@Autowired
	private DumpService dumpService;

	@Autowired
	private ServletContext servletContext;

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;

		fileManager.setSaveDir(servletContext.getRealPath(fileManager.getSaveDir()));

		logger.info("Rest Controller : " + fileManager.getSaveDir());
	}


	@RequestMapping(value = "/owner/inventory/category/update", method = RequestMethod.POST)
	public ResponseEntity<MessageData> updateCategory(Category category) {
		categoryService.update(category);
		
		MessageData messageData = new MessageData();
		messageData.setMsg("카테고리 정보 수정 성공");

		ResponseEntity<MessageData> entity = ResponseEntity.ok().body(messageData);

		return entity;
	}


	@RequestMapping(value = "/owner/inventory/item/list/filtered", method = RequestMethod.GET)
	public ResponseEntity<List<Item>> getFilteredItemList(String[] categoryArray, int owner_id) {
		Item item = new Item();

		int[] category_ids = new int[categoryArray.length];

		for (int i = 0; i < categoryArray.length; i++) {
			category_ids[i] = Integer.parseInt(categoryArray[i]);
		}
		
		List<Item> itemList = itemService.selectByMultiCategoryId(category_ids, owner_id);
		
		ResponseEntity<List<Item>> entity = ResponseEntity.ok().body(itemList);

		return entity;
	}


	@RequestMapping(value = "/owner/inventory/item/dump/regist", method = RequestMethod.POST)
	public ResponseEntity<MessageData> registItemByExcel(HttpSession session, MultipartFile dump) {
		String path = fileManager.getSaveDir() + File.separator + dump.getOriginalFilename();
		fileManager.saveFile(path, dump);
		
		Owner owner = (Owner)session.getAttribute("owner");
		int owner_id = owner.getOwner_id();

		MessageData messageData = new MessageData();
		messageData.setResultCode(1);
		messageData.setMsg("대량 등록 성공");
		messageData.setUrl("/client/owner/inventory/item/list");

		dumpService.regist(path, owner_id);

		ResponseEntity<MessageData> entity = ResponseEntity.ok().body(messageData);

		return entity;
	}

	@RequestMapping(value = "/owner/inventory/item/nameCheck", method = RequestMethod.POST)
	public ResponseEntity<MessageData> checkItemName(Item item) {
		itemService.duplicationCheck(item);

		MessageData messageData = new MessageData();
		messageData.setMsg("가능한 상품 이름입니다.");
		messageData.setResultCode(1);
		
		ResponseEntity<MessageData> entity = ResponseEntity.ok().body(messageData);

		return entity;
	}


	@RequestMapping(value = "/owner/inventory/item/update", method = RequestMethod.POST)
	public ResponseEntity<MessageData> updateItem(Item item) {
		itemService.update(item, fileManager);

		MessageData messageData = new MessageData();
		messageData.setMsg("상품 정보 수정 완료되었습니다.");
		messageData.setResultCode(1);
		
		ResponseEntity<MessageData> entity = ResponseEntity.ok().body(messageData);

		return entity;
	}

	
}
