package com.tsycsm.agileoffice.model.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelManager {
	FileInputStream fis;

	public ExcelManager() {
		try {
			fis = new FileInputStream("C:/workspace/spring_workspace/FashionShop/src/main/webapp/resources/excel/myskill.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			int rows = sheet.getPhysicalNumberOfRows();
			System.out.println("�젅肄붾뱶 �닔 : " + rows);
			
			for(int i=0; i<rows; i++) {
				XSSFRow row = sheet.getRow(i);
				
				int columnCount = row.getPhysicalNumberOfCells();
				for(int a=0; a<columnCount; a++) {
					XSSFCell cell = row.getCell(a);

					if(cell.getCellType() == CellType.STRING) {
						System.out.print(cell.getStringCellValue() + "\t\t\t");
					} else if (cell.getCellType() == CellType.NUMERIC) {
						System.out.print((int)cell.getNumericCellValue() + "\t\t\t");
					}
				}
				System.out.println();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public static void main(String[] args) {
		new ExcelManager();
	}

}
