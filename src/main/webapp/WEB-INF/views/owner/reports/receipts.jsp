<%@page import="com.tsycsm.agileoffice.model.domain.OrderSummary"%>
<%@page import="java.util.List"%>
<%@page import="com.tsycsm.agileoffice.common.Pager"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	Pager pager = (Pager)request.getAttribute("pager");
	List<OrderSummary> orderSummaryList = pager.getList();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>owner page</title>
<%@ include file="../inc/header.jsp" %>
<link rel="stylesheet" type="text/css" href="/resources/css/owner/reports/receipt_layout.css">

<script>
function showReceipt(){
	$(".receipt_content").show();
	$("#myReceiptbar").width('400px');
}

function hideReceipt(){
	$(".receipt_content").hide();
	$("#myReceiptbar").width('0px');
}
</script>

</head>
<%@ include file="../inc/common.jsp"%>
<%@ include file="./reports_inc/receiptbar.jsp"%>
			receipts <br><br>
			<table>
				<tr>
					<th>No</th>
					<th>날짜</th>
					<th>고객</th>
					<th>합계</th>
					
				</tr>
				<%
					int num = pager.getNum();
					int curPos = pager.getCurPos();
				%>
				<%for(int i=0; i<pager.getPageSize(); i++){ %>
					<%if(num < 1) break; %>
					<%OrderSummary orderSummary = orderSummaryList.get(curPos++); %>
					<tr onClick="showReceipt()" onmouseover="this.style.color='#fc9003'"
						onmouseout="this.style.color='black'">
						<td><%=num-- %></td>
						<td><%=orderSummary.getOrderdate().substring(0, 10) %></td>
						<td>
							<%if(orderSummary.getCustomer() !=null ){ %>
								<%=orderSummary.getCustomer().getCustomer_name() %>
							<%}else{ %>
								맴버십 고객이 아닙니다.
							<%} %>
						</td>
						<td><%=orderSummary.getTotal_price() %></td>
					</tr>
				<%} %>
			</table>
<%@ include file="../inc/footer.jsp" %>





