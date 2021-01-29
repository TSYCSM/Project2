package com.tsycsm.agileoffice.rest.controller.report;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tsycsm.agileoffice.client.controller.report.ReportController;
import com.tsycsm.agileoffice.model.common.Pager;
import com.tsycsm.agileoffice.model.customer.service.CustomerService;
import com.tsycsm.agileoffice.model.domain.Customer;
import com.tsycsm.agileoffice.model.domain.OrderSummary;
import com.tsycsm.agileoffice.model.domain.Owner;
import com.tsycsm.agileoffice.model.order.service.OrderService;

@RestController
public class RestReportController {
	private static final Logger logger = LoggerFactory.getLogger(ReportController.class);
	@Autowired
	private Pager pager;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CustomerService customerService;
	
	
	@PostMapping("/owner/reports/receiptsInfo")
	public OrderSummary viewOneReceipt(int order_summary_id) {
		
		OrderSummary orderSummary= orderService.select(order_summary_id);
		logger.debug("날짜: "+orderSummary.getOrderdate());
		
		return orderSummary;
	}
	
	//고객 가져오기
	@GetMapping("/owner/reports/customerList")
	public ModelAndView viewCustomerList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("owner/reports/customer_list");
		
		HttpSession session = request.getSession();
		Owner owner =  (Owner)session.getAttribute("owner"); 
		int owner_id = owner.getOwner_id();
		List<Customer> customerList =  customerService.selectAll(owner_id);
		pager.init(request, customerList);
		
		mav.addObject("pager", pager);
		
		return mav;
	}
	
	//고객 가져오기
	@GetMapping("/owner/reports/customerDetail")
	public ModelAndView viewCustomerDetail(HttpServletRequest request, int customer_id) {
		ModelAndView mav = new ModelAndView("owner/reports/customer_detail");
		
		HttpSession session = request.getSession();

		Customer customer =  customerService.selectJoinOrderSummary(customer_id);
		List<OrderSummary> orderSummaryList =  customer.getOrderSummaryList();
		pager.init(request, orderSummaryList);
		
		mav.addObject("pager", pager);
		mav.addObject("customer", customer);
		
		return mav;
	}
}
