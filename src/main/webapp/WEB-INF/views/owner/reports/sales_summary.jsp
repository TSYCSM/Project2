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
	
	List<OrderSummary> orderSummaryList = (List)request.getAttribute("orderSummaryList");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>owner page</title>

	<%@ include file="../inc/header.jsp" %>
<script type="text/javascript" src="/resources/js/client/owner/reports/charts.js"></script>
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
				
				<%for(OrderSummary orderSummary : orderSummaryList){ %>
					<tr>
						<td><a href="/owner/reports/salesDetail?orderdate=<%=orderSummary.getOrderdate().substring(0, 10) %>"><%=orderSummary.getOrderdate().substring(0, 10) %></a></td>
						<td><%=orderSummary.getTotal_price() %></td>
					</tr>
				<%} %>
			</table>
<%@ include file="../inc/footer.jsp" %>










