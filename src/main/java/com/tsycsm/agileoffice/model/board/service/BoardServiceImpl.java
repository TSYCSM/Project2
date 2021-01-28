package com.tsycsm.agileoffice.model.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsycsm.agileoffice.exception.AsyncBoardDMLException;
import com.tsycsm.agileoffice.exception.BoardDMLException;
import com.tsycsm.agileoffice.model.board.repository.BoardDAO;
import com.tsycsm.agileoffice.model.domain.Board;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDAO boardDAO;

	@Override
	public List<Board> selectAll() {
		return boardDAO.selectAll();
	}

	@Override
	public Board select(int board_id) {
		return boardDAO.select(board_id);
	}

	@Override
	public void regist(Board board) throws BoardDMLException {
		boardDAO.insert(board);
	}

	@Override
	public void update(Board board) throws AsyncBoardDMLException {
		boardDAO.update(board);
	}

	@Override
	public void delete(int board_id) throws BoardDMLException {
		boardDAO.delete(board_id);
	}
	
}
