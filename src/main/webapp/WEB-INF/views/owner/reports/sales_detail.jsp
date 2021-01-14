<%@page import="com.tsycsm.agileoffice.model.common.Pager"%>
<%@page import="com.tsycsm.agileoffice.model.domain.OrderDetail"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	Pager pager = (Pager)request.getAttribute("pager");
	List<OrderDetail> orderDetailList = pager.getList();
	String orderDate = orderDetailList.get(0).getOrderdate().substring(0, 10);
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
					<th>판매수량</th>
					<th>매출액</th>
					<th>재고</th>
					
				</tr>
				<%
					int num = pager.getNum();
					int curPos = pager.getCurPos();
				%>
				<%for(int i=0; i<pager.getPageSize(); i++){ %>
					<%if(num < 1) break; %>
					<%OrderDetail orderDetail= orderDetailList.get(curPos++); %>
					<tr>
						<td>
							<input type="hidden" value="<%=num--%>">
							<%=orderDetail.getItem().getItem_name() %>
						</td>
						<td><%=orderDetail.getTotal_quantity() %></td>
						<td><%=orderDetail.getTotal_price() %></td>
						<td><%=orderDetail.getItem().getStock() %></td>
					</tr>
				<%} %>
				<tr>
					<td colspan="4" style="text-align:center">
						<%if(pager.getFirstPage() >1){ %>
							<a href="/client/owner/reports/salesDetail?orderdate=<%=orderDate%>&currentPage=<%=pager.getFirstPage()-1%>">◀</a>					
						<%}else{ %>
							<a href = "javascript:alert('처음 페이지 입니다')">◀</a>
						<%} %>
						<%for(int i=pager.getFirstPage(); i<=pager.getLastPage(); i++){ %>
						<%if(i > pager.getTotalPage()) break; %>
						<a href="/client/owner/reports/salesDetail?orderdate=<%=orderDate%>&currentPage=<%=i%>">[<%=i %>]</a>
						<%} %>
						<%if(pager.getLastPage() < pager.getTotalPage()) {%>
							<a href="/client/owner/reports/salesDetail?orderdate=<%=orderDate%>&currentPage=<%=pager.getLastPage()+1%>">▶</a>
						<%}else{ %>
							<a href = "javascript:alert('마지막 페이지입니다.')">▶</a>
						<%} %>
					</td>
				</tr>
			</table>
<%@ include file="../inc/footer.jsp" %>






