<%@ page contentType="text/html;charset=utf-8"%>
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
					<th>영수증 번호</th>
					<th>날짜</th>
					<th>직원</th>
					<th>고객</th>
					<th>합계</th>
					
				</tr>
				<tr onClick="showReceipt()" onmouseover="this.style.color='#fc9003'"
					onmouseout="this.style.color='black'">
					<td>1-1000</td>
					<td>2020-12-25</td>
					<td>james</td>
					<td>customer1</td>
					<td>100</td>
				</tr>
			</table>
<%@ include file="../inc/footer.jsp" %>