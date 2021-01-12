package com.tsycsm.agileoffice.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sun.mail.imap.protocol.Item;
import com.tsycsm.agileoffice.common.Pager;
import com.tsycsm.agileoffice.model.domain.Owner;
import com.tsycsm.agileoffice.model.item.service.ItemService;
import com.tsycsm.agileoffice.model.owner.service.OwnerService;

@Controller
public class AdminController {

	@Autowired
	private OwnerService ownerService;
	
	@Autowired
	private ItemService itemService;

	@Autowired
	private Pager pager;

	@RequestMapping(value="/admin", method=RequestMethod.GET)
	public String viewAdmin() {
		return "admin/index";
	}

	@RequestMapping(value="/admin/owner/list", method=RequestMethod.GET)
	public ModelAndView viewOwnerList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		List<Owner> ownerList = ownerService.selectAll();
		
		pager.init(request, ownerList);
		mav.addObject("pager", pager);

		mav.setViewName("admin/owner/owner_list");

		return mav;
	}

	@RequestMapping(value="/admin/owner/detail", method=RequestMethod.GET)
	public ModelAndView viewOwnerDetail(int owner_id) {
		ModelAndView mav = new ModelAndView();
		List<com.tsycsm.agileoffice.model.domain.Item> itemList = itemService.selectByOwnerId(owner_id);
		
		return mav;
	}
	
	
}
