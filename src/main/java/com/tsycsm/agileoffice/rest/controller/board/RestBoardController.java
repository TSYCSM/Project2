package com.tsycsm.agileoffice.rest.controller.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tsycsm.agileoffice.model.comments.service.CommentsService;
import com.tsycsm.agileoffice.model.domain.Comments;

@RestController
public class RestBoardController {
	
	@Autowired
	private CommentsService commentsService;
	
	@GetMapping(value="/comments/{board_id}")
	public List<Comments> getList(@PathVariable int board_id) {
		List<Comments> commentsList = commentsService.selectAll(board_id);
		return commentsList;
	}
	
	@RequestMapping(value="/comments", method=RequestMethod.POST)
	public ResponseEntity<Comments> regist(@RequestBody Comments comments) {
		commentsService.regist(comments);
		return ResponseEntity.ok().body(comments);
	}

}