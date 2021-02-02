<%@page import="com.tsycsm.agileoffice.model.common.Formatter"%>
<%@page import="com.tsycsm.agileoffice.model.domain.OrderSummary"%>
<%@page import="com.tsycsm.agileoffice.model.common.Pager"%>
<%@page import="com.tsycsm.agileoffice.model.domain.Customer"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	Pager pager = (Pager)request.getAttribute("pager");
	Customer customer = (Customer)request.getAttribute("customer");
	List<OrderSummary> orderSummaryList = pager.getList();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>owner page</title>
<%@ include file="../inc/header.jsp" %>
</head>
<%@ include file="../inc/common.jsp"%>
<table>
	<tr>
		<td style="background-color: #F0F0F1; color: black;" colspan="3">
			<span><%=customer.getCustomer_name() %>'s Info</span>
			<span style="float: right;">Tel.No: <%=customer.getPhone() %></span>
		</td>
	</tr>
	<tr>
		<th>No</th>
		<th>Total</th>
		<th>Date</th>
	</tr>
	
	<%
		int num = pager.getNum();
		int curPos = pager.getCurPos();
	%>
	<%for(int i=0; i<pager.getPageSize(); i++){ %>
		<%if(num < 1) break; %>
		<%OrderSummary orderSummary= orderSummaryList.get(curPos++); %>
		<tr>
			<td><%=num-- %></td>
			<td><%=Formatter.getCurrency(orderSummary.getTotal_price()) %></td>
			<td><%=orderSummary.getOrderdate().substring(0, 10) %></td>
		</tr>
	<%} %>
	<tr>
		<td colspan="3" style="text-align:center">
			<%if(pager.getFirstPage() >1){ %>
				<a href="/rest/owner/reports/customerDetail?currentPage=<%=pager.getFirstPage()-1%>
					&customer_id=<%=customer.getCustomer_id()%>">◀</a>					
			<%}else{ %>
				<a href = "javascript:alert('처음 페이지 입니다')">◀</a>
			<%} %>
			<%for(int i=pager.getFirstPage(); i<=pager.getLastPage(); i++){ %>
			<%if(i > pager.getTotalPage()) break; %>
			<a href="/rest/owner/reports/customerDetail?currentPage=<%=i%>
				&customer_id=<%=customer.getCustomer_id()%>">[<%=i %>]</a>
			<%} %>
			<%if(pager.getLastPage() < pager.getTotalPage()) {%>
				<a href="/rest/owner/reports/customerDetail?currentPage=<%=pager.getLastPage()+1%>
					&customer_id=<%=customer.getCustomer_id()%>">▶</a>
			<%}else{ %>
				<a href = "javascript:alert('마지막 페이지입니다.')">▶</a>
			<%} %>
		</td>
	</tr>
</table>
<%@ include file="../inc/footer.jsp" %>












