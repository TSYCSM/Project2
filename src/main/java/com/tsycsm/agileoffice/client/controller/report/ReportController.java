package com.tsycsm.agileoffice.client.controller.report;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tsycsm.agileoffice.model.common.Pager;
import com.tsycsm.agileoffice.model.customer.service.CustomerService;
import com.tsycsm.agileoffice.model.domain.OrderDetail;
import com.tsycsm.agileoffice.model.domain.Owner;
import com.tsycsm.agileoffice.model.order.service.OrderService;

@Controller
public class ReportController {
	private static final Logger logger = LoggerFactory.getLogger(ReportController.class);
	@Autowired
	private Pager pager;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/owner/reports/salesSummary")
	public ModelAndView viewSalesSummary(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Owner owner= (Owner)session.getAttribute("owner");
		
		List orderSummaryList = orderService.selectAllByOwnerWithDate(owner.getOwner_id());
		
		ModelAndView mav =new ModelAndView("owner/reports/sales_summary");
		pager.init(request, orderSummaryList);
		mav.addObject("pager", pager);
		
		return mav;
	}
	
	@GetMapping("/owner/reports/salesDetail")
	public ModelAndView viewSalesDetail(HttpServletRequest request, String orderdate) {
		HttpSession session = request.getSession();
		Owner owner = (Owner)session.getAttribute("owner");
		
		logger.debug("orderdate: "+orderdate);
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrderdate(orderdate);
		orderDetail.setOwner_id(owner.getOwner_id());
		
		List orderDetailList = orderService.selectAllByOwnerWithDateDetail(orderDetail);
		
		ModelAndView mav = new ModelAndView("/owner/reports/sales_detail");

		pager.init(request, orderDetailList);
		mav.addObject("pager", pager);
		
		return mav;
	}
	
	@GetMapping("/owner/reports/receipts")
	public ModelAndView viewReceipts(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Owner owner= (Owner)session.getAttribute("owner");
		
		List orderSummaryList = orderService.selectAllByOwner(owner.getOwner_id());
		pager.init(request, orderSummaryList);
		
		ModelAndView mav =new ModelAndView("owner/reports/receipts");
		mav.addObject("pager", pager);
		
		return mav;
	}
	
}

