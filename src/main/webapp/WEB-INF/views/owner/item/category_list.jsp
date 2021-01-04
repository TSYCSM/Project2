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
				<button class="add_btn" type="button" onclick="location.href='/owner/item/categoryadd'">카테고리 추가</button>
				category list <br><br>
			<table style="width: 50%;">
				<tr>
					<th>모양</th>
					<th>이름</th>
				</tr>
				<tr>
					<td>모양나올곳</td>
					<td>Box</td>
				</tr>
			</table>
			</table>
<%@ include file="../inc/footer.jsp" %>






