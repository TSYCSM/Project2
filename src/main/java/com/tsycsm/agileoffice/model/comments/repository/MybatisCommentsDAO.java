package com.tsycsm.agileoffice.model.comments.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tsycsm.agileoffice.exception.AsyncBoardDMLException;
import com.tsycsm.agileoffice.exception.BoardDMLException;
import com.tsycsm.agileoffice.model.domain.Comments;

@Repository
public class MybatisCommentsDAO implements CommentsDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<Comments> selectAll(int board_id) {
		return sqlSessionTemplate.selectList("Comments.selectAll", board_id);
	}

	@Override
	public Comments select(int comments_id) {
		return sqlSessionTemplate.selectOne("Comments.select", comments_id);
	}

	@Override
	public void insert(Comments comments) throws BoardDMLException {
		int result = sqlSessionTemplate.insert("Comments.insert", comments);
		if(result == 0) {
			throw new BoardDMLException("댓글 등록에 실패했습니다");
		}
	}

	@Override
	public void update(Comments comments) throws AsyncBoardDMLException {
		int result = sqlSessionTemplate.update("Comments.update", comments);
		if(result == 0) {
			throw new AsyncBoardDMLException("댓글 수정에 실패했습니다");
		}
	}

	@Override
	public void delete(int comments_id) throws BoardDMLException {
		int result = sqlSessionTemplate.delete("Comments.delete", comments_id);
		if(result == 0) {
			throw new BoardDMLException("댓글 삭제에 실패했습니다");
		}
	}


}
