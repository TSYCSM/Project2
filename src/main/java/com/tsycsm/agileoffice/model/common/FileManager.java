package com.tsycsm.agileoffice.model.common;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
@Component
public class FileManager {
	private String saveDir = "/resources/data";
	
	public String getExtend(String path) {
		int lastIndex = path.lastIndexOf(".");
		String ext = path.substring(lastIndex+1, path.length());
		return ext;
	}
	
	public void saveFile(String realDir, MultipartFile multi) {
		try {
			multi.transferTo(new File(realDir));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean deleteFile(String path) {
		File file = new File(path);
		return file.delete();
	}

}
