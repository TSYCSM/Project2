<%@page import="com.tsycsm.agileoffice.model.domain.Category"%>
<%@page import="com.tsycsm.agileoffice.model.domain.Item"%>
<%@page import="java.util.List"%>
<%@page import="com.tsycsm.agileoffice.common.Pager"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
List<Item> itemList = (List) request.getAttribute("itemList");
List<Category> categoryList = (List) request.getAttribute("categoryList");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>ownerDetail</title>
<%@ include file="../inc/header.jsp"%>
<script>
	function getItemDetail(item_id) {
		$.ajax({
			url: "admin/owner/detail/item",
			type: "GET",
			data: {
				item_id: item_id 
			}
		});
	}
</script>
</head>

<%@ include file="../inc/common.jsp"%>
<div class="bigcontainer">
	<div class="container">
		<form>
			<div class="outerbox">
				<label for="fname">등록된 상품 수</label>
				<div class="box"><%=itemList.size() %> 개</div>
			</div>
			<div class="outerbox">
				<label for="lname">등록된 카테고리 수</label>
				<div class="box"><%=categoryList.size() %> 개</div>
			</div>
			<div class="outerbox">
				<label for="lname">등록된 고객 수</label>
				<div class="box">총 개수</div>
			</div>
		</form>
	</div>

	<div id="reportbox" class="container" style="left: 800px;">
		<div class="bigbox">
			<div class="smallbox">
				<form>
					<table style="width: 100%">
						<tr>
							<th>No</th>
							<th>상품명</th>
							<th>카테고리</th>
							<th>수량</th>
							<th>등록일자</th>
						</tr>
						<% for (int i = 0; i < itemList.size(); i++) { %>
						<% Item item = itemList.get(i); %>
						<tr>
							<td>No</td>
							<td><a href="javascript:getItemDetail() "><%=item.getItem_name()%></a></td>
							<td><%=item.getCategory_id()%></td>
							<td><%=item.getQuantity()%></td>
							<td><%=item.getRegdate()%></td>
						</tr>
						<% } %>
					</table>
				</form>
			</div>
		</div>
	</div>
</div>

<%@ include file="../inc/footer.jsp"%>