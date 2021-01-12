<%@page import="com.tsycsm.agileoffice.model.domain.OrderDetail"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	List<OrderDetail> orderDetailList = (List)request.getAttribute("orderDetailList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>owner page</title>
<%@ include file="../inc/header.jsp" %>
<script type="text/javascript" src="./reports_inc/charts.js"></script>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script>
	google.charts.load('current', {
		packages : [ 'corechart', 'bar' ]
	});
	google.charts.setOnLoadCallback(drawStacked);
</script>
</head>
<%@ include file="../inc/common.jsp"%>
			<!-- <div id="chart_div"></div> -->
			item list <br><br>
			<table>
				<tr>
					<th>상품명</th>
					<th>카테고리</th>
					<th>판매수량</th>
					<th>매출액</th>
					
				</tr>
				<%for(OrderDetail orderDetail : orderDetailList){ %>
					<tr>
						<td><%=orderDetail.getItem().getItem_name() %></td>
						<td><%=orderDetail.getItem().getCategory_id() %></td>
						<td><%=orderDetail.getTotal_quantity() %></td>
						<td><%=orderDetail.getTotal_price() %></td>
					</tr>
				<%} %>
			</table>
<%@ include file="../inc/footer.jsp" %>






