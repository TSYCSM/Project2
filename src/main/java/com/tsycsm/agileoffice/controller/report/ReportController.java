package com.tsycsm.agileoffice.controller.report;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tsycsm.agileoffice.model.domain.OrderDetail;
import com.tsycsm.agileoffice.model.domain.Owner;
import com.tsycsm.agileoffice.model.order.service.OrderService;

@Controller
public class ReportController {
	private static final Logger logger = LoggerFactory.getLogger(ReportController.class);
	
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/owner/reports/salesSummary")
	public ModelAndView viewSalesSummary(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Owner owner= (Owner)session.getAttribute("owner");
		
		List orderSummaryList = orderService.selectAllByOwner(owner.getOwner_id());
		
		ModelAndView mav =new ModelAndView("owner/reports/sales_summary");
		mav.addObject("orderSummaryList", orderSummaryList);
		
		logger.debug("orderSummaryList¿« ≈©±‚: "+orderSummaryList.size());
		
		return mav;
	}
	
	@GetMapping("/owner/reports/salesDetail")
	public ModelAndView viewSalesDetail(String orderdate, HttpSession session) {
		
		Owner owner = (Owner)session.getAttribute("owner");
		
		logger.debug("orderdate: "+orderdate);
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrderdate(orderdate);
		orderDetail.setOwner_id(owner.getOwner_id());
		
		List orderDetailList = orderService.selectAllByOwnerWithDate(orderDetail);
		
		ModelAndView mav = new ModelAndView("/owner/reports/sales_detail");
		mav.addObject("orderDetailList", orderDetailList);
		
		return mav;
	}
}






















