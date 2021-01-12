package com.tsycsm.agileoffice.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tsycsm.agileoffice.common.Pager;
import com.tsycsm.agileoffice.model.domain.Owner;
import com.tsycsm.agileoffice.model.owner.service.OwnerService;

@Controller
public class AdminController {

	@Autowired
	private OwnerService ownerService;
	
	@Autowired
	private Pager pager;

	@RequestMapping(value="/admin", method=RequestMethod.GET)
	public String viewAdmin() {
		return "admin/index";
	}

	@RequestMapping(value="/admin/owner/list", method=RequestMethod.GET)
	public ModelAndView viewOwnerList() {
		List<Owner> ownerList = ownerService.selectAll();
		pager.setList(ownerList);
	
		ModelAndView mav = new ModelAndView();
		mav.addObject("ownerList", ownerList);
		mav.setViewName("admin/owner/owner_list");
		return mav;
	}

	@RequestMapping(value="/admin/owner/detail", method=RequestMethod.GET)
	public String viewOwnerDetail(int owner_id) {
		return "admin/owner/owner_detail?owner_id=" + owner_id;
	}
	
	
}
