package com.tsycsm.agileoffice.model.comments.repository;

import java.util.List;

import com.tsycsm.agileoffice.model.domain.Comments;

public interface CommentsDAO {
	public List<Comments> selectAll(int board_id);
	public Comments select(int comments_id);
	public void insert(Comments comments);
	public void update(Comments comments);
	public void delete(int comments_id);
}
