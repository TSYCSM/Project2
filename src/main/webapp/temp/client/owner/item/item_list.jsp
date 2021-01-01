<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>owner page</title>
<%@ include file="../inc/header.jsp" %>
<style>
.add_btn{
	position: absolute;
	left: 300px;
}
</style>
<script type="text/javascript" src="../common/library.js"></script>

</head>
<%@ include file="../inc/common.jsp"%>
				<button class="add_btn" type="button" onclick="location.href='item_add.jsp'">상품추가</button>
				item list <br><br>
			<table>
				<tr>
					<th>이름</th>
					<th>매출액</th>
				</tr>
				<tr>
					<td>james</td>
					<td>350</td>
				</tr>
			</table>
			</table>
<%@ include file="../inc/footer.jsp" %>