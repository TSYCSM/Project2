package com.tsycsm.agileoffice.model.comments.service;

import java.util.List;

import com.tsycsm.agileoffice.model.domain.Comments;

public interface CommentsService {
	public List<Comments> selectAll();
	public Comments select(int comments_id);
	public void regist(Comments comments);
	public void update(Comments comments);
	public void delete(int comments_id);
}
