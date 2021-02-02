<%@page import="com.tsycsm.agileoffice.model.common.Pager"%>
<%@page import="com.tsycsm.agileoffice.model.domain.Customer"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	Pager pager = (Pager)request.getAttribute("pager");
	List<Customer> customerList = pager.getList();
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
		<th>No</th>
		<th>Customer</th>
		<th>point</th>
		<th>Tel.No</th>
		<th>Registered Date</th>
	</tr>
	
	<%
		int num = pager.getNum();
		int curPos = pager.getCurPos();
	%>
	<%for(int i=0; i<pager.getPageSize(); i++){ %>
		<%if(num < 1) break; %>
		<%Customer customer= customerList.get(curPos++); %>
		<tr>
			<td><%=num-- %></td>
			<td><a href="/rest/owner/reports/customerDetail?customer_id=<%=customer.getCustomer_id()%>"><%=customer.getCustomer_name() %></a></td>
			<td><%=customer.getPoint() %></td>
			<td><%=customer.getPhone() %></td>
			<td><%=customer.getRegdate().substring(0, 10) %></td>
		</tr>
	<%} %>
	<tr>
		<td colspan="5" style="text-align:center">
			<%if(pager.getFirstPage() >1){ %>
				<a href="/rest/owner/reports/customerList?currentPage=<%=pager.getFirstPage()-1%>">◀</a>					
			<%}else{ %>
				<a href = "javascript:alert('처음 페이지 입니다')">◀</a>
			<%} %>
			<%for(int i=pager.getFirstPage(); i<=pager.getLastPage(); i++){ %>
			<%if(i > pager.getTotalPage()) break; %>
			<a href="/rest/owner/reports/customerList?currentPage=<%=i%>">[<%=i %>]</a>
			<%} %>
			<%if(pager.getLastPage() < pager.getTotalPage()) {%>
				<a href="/rest/owner/reports/customerList?currentPage=<%=pager.getLastPage()+1%>">▶</a>
			<%}else{ %>
				<a href = "javascript:alert('마지막 페이지입니다.')">▶</a>
			<%} %>
		</td>
	</tr>
</table>
<%@ include file="../inc/footer.jsp" %>












