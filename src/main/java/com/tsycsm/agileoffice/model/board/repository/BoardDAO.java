package com.tsycsm.agileoffice.model.board.repository;

import java.util.List;

import com.tsycsm.agileoffice.model.domain.Board;

public interface BoardDAO {
	public List<Board> selectAll();
	public List<Board> search(String title);
	public Board select(int board_id);
	public void insert(Board board);
	public void update(Board board);
	public void delete(int board_id);
}
