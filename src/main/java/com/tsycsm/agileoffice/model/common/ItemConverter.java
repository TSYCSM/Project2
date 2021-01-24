package com.tsycsm.agileoffice.model.common;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.tsycsm.agileoffice.model.domain.Item;


@Component
public class ItemConverter {

	public List convertFromFile(String path, int owner_id) {
		List<Item> itemList = new ArrayList<Item>();
		
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(path);
			XSSFWorkbook book = new XSSFWorkbook(fis);
			
			XSSFSheet sheet = book.getSheetAt(0);
			
			int rowCount = sheet.getPhysicalNumberOfRows();
			
			for(int i=1; i<rowCount; i++) {
				Item item = new Item();
				XSSFRow row = sheet.getRow(i);

				for(int a=0; a<row.getPhysicalNumberOfCells(); a++) {
					XSSFCell cell = row.getCell(a);
					
					item.setOwner_id(owner_id);

					if(a == 0) {    // category_id
						item.setCategory_id((int)cell.getNumericCellValue());
					} else if(a == 1) {    // item_name
						item.setItem_name(cell.getStringCellValue());
					} else if(a == 2) {    // price
						item.setPrice((int)cell.getNumericCellValue());
					} else if(a == 3) {    // stock
						item.setStock((int)cell.getNumericCellValue());
					} else if(a == 4) {
						item.setFilename(cell.getStringCellValue());
					}
				}

				itemList.add(item);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fis != null) {
				try {
					fis.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return itemList;
	}


}
