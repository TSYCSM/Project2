<%@page import="com.tsycsm.agileoffice.model.domain.Category"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	List<Category> categoryList = (List)request.getAttribute("categoryList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>owner page</title>
<%@ include file="../inc/header.jsp" %>
<style>
.add_btn{
	float: right;
}
.colorBox{
	width: 50px;
	height: 50px;
	border-radius:15px;
}
</style>
<script type="text/javascript" src="../common/library.js"></script>

</head>
<%@ include file="../inc/common.jsp"%>
<table style="width: 50%;">
	<tr>
		<th>색상</th>
		<th>이름</th>
	</tr>
	<%for(int i=0; i<categoryList.size(); i++){ %>
		<%Category category = categoryList.get(i); %>
		<tr onclick="location.href='/client/owner/inventory/category/detail?category_id=<%=category.getCategory_id() %>'" style="cursor: pointer;">
			<td><div class="colorBox" style=" background-color: <%=category.getColor() %>"></div></td>
			<td><%=category.getCategory_name() %></td>
		</tr>
	<%} %>
	<tr style="background-color: #f0f0f0;">
		<td colspan="2">
			<button class="add_btn" type="button" onclick="location.href='/client/owner/inventory/category/registform'">카테고리 추가</button>
		</td>
	</tr>
</table>
<%@ include file="../inc/footer.jsp" %>






