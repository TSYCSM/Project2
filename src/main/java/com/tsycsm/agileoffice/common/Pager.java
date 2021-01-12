package com.tsycsm.agileoffice.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class Pager {
	private List list;
	private int totalRecord;
	private int pageSize = 10;
	private int totalPage;
	private int blockSize = 10;
	private int currentPage = 1;
	private int firstPage;
	private int lastPage;
	private int curPos;
	private int num;

	
	public void init(HttpServletRequest request, int listSize) {
		totalRecord = listSize;
		totalPage = (int)Math.ceil((float)totalRecord/pageSize);
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		firstPage = currentPage - (currentPage-1) % blockSize;
		lastPage = firstPage + blockSize - 1;
		curPos = (currentPage - 1) * pageSize;
		num = totalRecord - curPos;
	}
	
	public void init(HttpServletRequest request, List list) {
		this.list = list;
		totalRecord = list.size();
		totalPage = (int)Math.ceil((float)totalRecord/pageSize);
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		firstPage = currentPage - (currentPage-1) % blockSize;
		lastPage = firstPage + blockSize - 1;
		curPos = (currentPage - 1) * pageSize;
		num = totalRecord - curPos;
	}

	public void init(int curPage, int listSize) {
		totalRecord = listSize;
		totalPage = (int)Math.ceil((float)totalRecord/pageSize);
		currentPage = curPage;
		firstPage = currentPage - (currentPage-1) % blockSize;
		lastPage = firstPage + blockSize - 1;
		curPos = (currentPage - 1) * pageSize;
		num = totalRecord - curPos;
	}
	
	
}
