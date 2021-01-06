<%@page import="com.tsycsm.agileoffice.model.domain.Item"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	Item item = (Item)request.getAttribute("item");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>owner page</title>
<%@ include file="../inc/header.jsp"%>
<style>
#dragArea {
	width: 200px;
	height: 200px;
	overflow: scroll;
	border: 1px solid #ccc
}

.dragBorder {
	background: #ffffff;
}
</style>
<script>
	
	function update() {
		$("form").attr({
			action:"/owner/sale/item/update",
			enctype:"multipart/form-data",
			method:"post"
		});
		$("form").submit();
	}

	function del() {
		location.href="/owner/sale/item/delete?item_id=" + <%=item.getItem_id()%>;
	}

</script>
</head>
<%@ include file="../inc/common.jsp" %>
<div class="container">
	<form>
		<input type="hidden" name="owner_id" value="1"/>
		<div class="outerbox">
			<label for="fname">상품이름</label>
			<div class="box">
				<input type="text" id="fname" name="item_name" value="<%=item.getItem_name() %>">
			</div>
		</div>
		<div class="outerbox">
			<label for="lname">카테고리</label>
			<div class="box">
				<div style="width: 60%;">
					<select name="category_id">
						<option value="0">선택하세요</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
					</select>
				</div>
			</div>
		</div>
		<div class="outerbox">
			<label for="lname">상품가격</label>
			<div class="box">
				<input type="text" name="price" value="<%=item.getPrice()%>" required />
			</div>
		</div>
		<div class="outerbox">
			<label for="lname">상품 수량</label><br>
			<div class="box">
				<input type="number" name="stock" value="<%=item.getStock()%>" required />
			</div>
		</div>

		<div class="outerbox">
			<label for="lname">이미지</label><br>
			<div class="box">
				<input type="file" name="photo" required />
			</div>
		</div>
		<div>
			<div class="buttonbox">
				<input onClick="update()" type="button" value="수정">
				<input onClick="del()" type="button" value="삭제">
			</div>
		</div>
	</form>
</div>
<%@ include file="../inc/footer.jsp"%>