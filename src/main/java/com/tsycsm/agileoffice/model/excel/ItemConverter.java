package com.tsycsm.agileoffice.model.excel;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;


@Component
public class ProductConverter {

	public List convertFromFile(String path) {
		List<Product> productList = new ArrayList<Product>();
		
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(path);
			XSSFWorkbook book = new XSSFWorkbook(fis);
			
			XSSFSheet sheet = book.getSheetAt(0);
			
			int rowCount = sheet.getPhysicalNumberOfRows();
			
			for(int i=1; i<rowCount; i++) {
				Product product = new Product();
				XSSFRow row = sheet.getRow(i);

				for(int a=0; a<row.getPhysicalNumberOfCells(); a++) {
					XSSFCell cell = row.getCell(a);

					if(a == 0) {
						SubCategory subCategory = new SubCategory();
						subCategory.setSubcategory_id((int)cell.getNumericCellValue());
						product.setSubCategory(subCategory);
					} else if(a == 1) {
						product.setProduct_name(cell.getStringCellValue());
					} else if(a == 2) {
						product.setPrice((int)cell.getNumericCellValue());
					} else if(a == 3) {
						product.setBrand(cell.getStringCellValue());
					} else if(a == 4) {
						String[] colors = cell.getStringCellValue().trim().split(",");
						List colorList = new ArrayList();
						for(String color : colors) {
							Color obj = new Color();
							obj.setPicker(color);
							colorList.add(obj);
						}
						product.setColorList(colorList);
					} else if(a == 5) {
						String[] psizes = cell.getStringCellValue().trim().split(",");
						List psizeList = new ArrayList();
						for(String psize : psizes) {
							Psize obj = new Psize();
							obj.setFit(psize);
							psizeList.add(obj);
						}
						product.setPsizeList(psizeList);
					} else if(a == 6) {
						product.setDetail(cell.getStringCellValue());
					} else if(a == 7) {
						product.setFilename(cell.getStringCellValue());
					}
				}
				productList.add(product);
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
		
		return productList;
	}


}
