package com.tsycsm.agileoffice.model.comments.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsycsm.agileoffice.exception.AsyncBoardDMLException;
import com.tsycsm.agileoffice.exception.BoardDMLException;
import com.tsycsm.agileoffice.model.comments.repository.CommentsDAO;
import com.tsycsm.agileoffice.model.domain.Comments;

@Service
public class CommentsServiceImpl implements CommentsService {
	@Autowired
	private CommentsDAO commentsDAO;

	@Override
	public List<Comments> selectAll(int board_id) {
		return commentsDAO.selectAll(board_id);
	}

	@Override
	public Comments select(int comments_id) {
		return commentsDAO.select(comments_id);
	}

	@Override
	public void regist(Comments comments) throws BoardDMLException {
		commentsDAO.insert(comments);
	}

	@Override
	public void update(Comments comments) throws AsyncBoardDMLException {
		commentsDAO.update(comments);
	}

	@Override
	public void delete(int comments_id) throws BoardDMLException {
		commentsDAO.delete(comments_id);
	}
	
}
