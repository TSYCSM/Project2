package com.tsycsm.agileoffice.client.controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tsycsm.agileoffice.model.board.service.BoardService;
import com.tsycsm.agileoffice.model.common.Pager;
import com.tsycsm.agileoffice.model.domain.Board;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/owner/board/registform", method=RequestMethod.GET)
	public String getRegistForm() {
		return "owner/board/board_form";
	}
	
	@RequestMapping(value="/owner/board/regist", method=RequestMethod.POST)
	public String registBoard(Board board) {
		boardService.regist(board);
		
		return "redirect:/client/owner/board/list";
	}
	
	@RequestMapping(value="/owner/board/list", method=RequestMethod.GET)
	public ModelAndView getBoardList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		List<Board> boardList = boardService.selectAll();
		
		Pager pager = new Pager();
		pager.init(request, boardList);
		
		mav.addObject("pager", pager);
		mav.setViewName("owner/board/board_list");
		
		return mav;
	}
	
	@RequestMapping(value="/owner/board/detail", method=RequestMethod.GET)
	public ModelAndView getBoardDetail(int board_id) {
		ModelAndView mav = new ModelAndView();
		Board board = boardService.select(board_id);
		
		mav.addObject("board", board);
		mav.setViewName("owner/board/board_detail");
		
		return mav;
	}
	
	@RequestMapping(value="/owner/board/searchList", method=RequestMethod.GET)
	public ModelAndView getSearchList(HttpServletRequest request, String title) {
		ModelAndView mav = new ModelAndView();
		List<Board> boardList = boardService.search(title);
		log.debug("title= "+title);
		
		Pager pager = new Pager();
		pager.init(request, boardList);
		
		mav.addObject("pager", pager);
		mav.setViewName("owner/board/board_list");
		
		return mav;
	}

}
