<%@page import="com.tsycsm.agileoffice.model.common.Pager"%>
<%@page import="com.tsycsm.agileoffice.model.domain.OrderSummary"%>
<%@page import="java.util.List"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html;charset=utf-8"%>
<% 	Calendar cal = Calendar.getInstance();
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	String today = dateFormat.format(cal.getTime());
	cal.add(Calendar.DATE, -1);
	String yesterday = dateFormat.format(cal.getTime());
	
	Pager pager = (Pager)request.getAttribute("pager");
	List<OrderSummary> orderSummaryList = pager.getList();
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>owner page</title>

	<%@ include file="../inc/header.jsp" %>
<script type="text/javascript" src="/resources/js/client/client/owner/reports/charts.js"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script>
//google.charts.load('current', {'packages':['corechart']});
//google.charts.setOnLoadCallback(drawChart);//파라미터 줄수 있음...
</script>
</head>
<%@ include file="../inc/common.jsp"%>
			  	 <!-- <div id="chart_div" style="width: 100%; height: 500px;"></div> --> 
			  	전체 리스트<br><br>
				<table>
				<tr>
					<th>날짜</th>
					<th>총 매출액</th>
				</tr>
				<%
					int num = pager.getNum();
					int curPos = pager.getCurPos();
				%>
				<%for(int i=0; i<pager.getPageSize(); i++){ %>
					<%if(num < 1) break; %>
					<%OrderSummary orderSummary = orderSummaryList.get(curPos++); %>
					<tr>
						
						<td>
							<input type="hidden" value="<%=num--%>">
							<a href="/client/owner/reports/salesDetail?orderdate=<%=orderSummary.getOrderdate().substring(0, 10) %>&currentPage=1"><%=orderSummary.getOrderdate().substring(0, 10) %></a>
						</td>
						<td><%=orderSummary.getTotal_price() %></td>
					</tr>
				<%} %>
				<tr>
					<td colspan="2" style="text-align:center">
						<%if(pager.getFirstPage() >1){ %>
							<a href="/client/owner/reports/salesSummary?currentPage=<%=pager.getFirstPage()-1%>">◀</a>					
						<%}else{ %>
							<a href = "javascript:alert('처음 페이지 입니다')">◀</a>
						<%} %>
						<%for(int i=pager.getFirstPage(); i<=pager.getLastPage(); i++){ %>
						<%if(i > pager.getTotalPage()) break; %>
						<a href="/client/owner/reports/salesSummary?currentPage=<%=i%>">[<%=i %>]</a>
						<%} %>
						<%if(pager.getLastPage() < pager.getTotalPage()) {%>
							<a href="/client/owner/reports/salesSummary?currentPage=<%=pager.getLastPage()+1%>">▶</a>
						<%}else{ %>
							<a href = "javascript:alert('마지막 페이지입니다.')">▶</a>
						<%} %>
					</td>
				</tr>
			</table>
<%@ include file="../inc/footer.jsp" %>










