<%@page import="com.tsycsm.agileoffice.model.common.Formatter"%>
<%@page import="com.tsycsm.agileoffice.model.domain.OrderSummary"%>
<%@page import="java.util.List"%>
<%@page import="com.tsycsm.agileoffice.model.common.Pager"%>
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
const formatter = new Intl.NumberFormat('ko-KR', {
	  style: 'currency',
	  currency: 'KRW'
	})

function showReceipt(summary_id){
	var tag="";
	$.ajax({
		url:"/client/owner/reports/receiptsInfo",
		type:"post",
		data:{
			order_summary_id:summary_id
		},
		success:function(orderSummary){
			var orderDetailList = orderSummary.orderDetailList
			$(".total_price").html(formatter.format(orderSummary.total_price))
			
			for(var i=0; i<orderDetailList.length;i++){
				var orderDetail= orderDetailList[i];
				var item= orderDetailList[i].item;
				tag += "<div style=\"border-top : 1px solid #c7c7c7; padding-top: 8px;\">"
				tag += item.item_name
				tag += "</div>"
				tag += "<div>"
				tag += formatter.format(orderDetail.price)+" X "+orderDetail.quantity
				tag += "<span class=\"sub\">"+formatter.format(orderDetail.price*orderDetail.quantity)+"</span>"
				tag += "</div>"
				
				
			}
			$(".orderDetail").html(tag);
			$(".date").html(orderSummary.orderdate);
			
		}
		
		
	})
	
	
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
					<tr onClick="showReceipt('<%=orderSummary.getOrder_summary_id() %>')" onmouseover="this.style.color='#fc9003'"
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
						<td><%=Formatter.getCurrency(orderSummary.getTotal_price() ) %></td>
					</tr>
				<%} %>
				
				<tr>
					<td colspan="4" style="text-align:center">
						<%if(pager.getFirstPage() >1){ %>
							<a href="/client/owner/reports/receipts?currentPage=<%=pager.getFirstPage()-1%>">◀</a>					
						<%}else{ %>
							<a href = "javascript:alert('처음 페이지 입니다')">◀</a>
						<%} %>
						<%for(int i=pager.getFirstPage(); i<=pager.getLastPage(); i++){ %>
						<%if(i > pager.getTotalPage()) break; %>
						<a href="/client/owner/reports/receipts?currentPage=<%=i%>">[<%=i %>]</a>
						<%} %>
						<%if(pager.getLastPage() < pager.getTotalPage()) {%>
							<a href="/client/owner/reports/receipts?currentPage=<%=pager.getLastPage()+1%>">▶</a>
						<%}else{ %>
							<a href = "javascript:alert('마지막 페이지입니다.')">▶</a>
						<%} %>
					</td>
				</tr>
			</table>
<%@ include file="../inc/footer.jsp" %>





