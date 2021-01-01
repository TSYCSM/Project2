<%@ page contentType="text/html;charset=utf-8"%>
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
					<th>상품</th>
					<th>카테고리</th>
					<th>판매수량</th>
					<th>매출액</th>
					
				</tr>
				<tr>
					<td>item1</td>
					<td>box</td>
					<td>3</td>
					<td>150</td>
				</tr>
			</table>
<%@ include file="../inc/footer.jsp" %>