package com.tsycsm.agileoffice.model.board.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tsycsm.agileoffice.exception.AsyncBoardDMLException;
import com.tsycsm.agileoffice.exception.BoardDMLException;
import com.tsycsm.agileoffice.model.domain.Board;

@Repository
public class MybatisBoardDAO implements BoardDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<Board> selectAll() {
		return sqlSessionTemplate.selectList("Board.selectAll");
	}

	@Override
	public Board select(int board_id) {
		return sqlSessionTemplate.selectOne("Board.select", board_id);
	}

	@Override
	public void insert(Board board) throws BoardDMLException {
		int result = sqlSessionTemplate.insert("Board.insert", board);
		if(result == 0) {
			throw new BoardDMLException("게시물 등록 실패");
		}
	}

	@Override
	public void update(Board board) throws AsyncBoardDMLException {
		int result = sqlSessionTemplate.update("Board.update", board);
		if(result == 0) {
			throw new AsyncBoardDMLException("게시물 수정 실패");
		}
	}

	@Override
	public void delete(int board_id) throws BoardDMLException {
		int result = sqlSessionTemplate.delete("Board.delete", board_id);
		if(result == 0) {
			throw new BoardDMLException("게시물 삭제 실페");
		}
	}

}
