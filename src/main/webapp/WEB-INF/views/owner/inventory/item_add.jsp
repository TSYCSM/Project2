<%@page import="com.tsycsm.agileoffice.model.domain.Category"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	List<Category> categoryList = (List<Category>)request.getAttribute("categoryList");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>owner page</title>
<%@ include file="../inc/header.jsp"%>
<style>

</style>
<script>
	var nameCheckFlag = false;
	
	function regist() {
		if(nameCheckFlag == true) {
			$("form").attr({
				action:"/client/owner/inventory/item/regist",
				enctype:"multipart/form-data",
				method:"post"
			});
			$("form").submit();
		} else {
			alert("상품명 중복 확인을 진행해주세요.");
		}
	}
	
	function nameCheck() {
		var item_name = $("#item_name").val();
		var owner_id = $("input[name='owner_id']").val();
		
		if(item_name.length > 0) {
			$.ajax({
				url:"/client/owner/inventory/item/nameCheck",
				type: "POST",
				data: {
					item_name: item_name,
					owner_id: owner_id
				},
				success: function(responseData) {
					if(responseData.resultCode == 1) {
						alert(responseData.msg);
					} else {
						alert(responseData.msg);				
					}
				}
			});
			nameCheckFlag = true;
		} else {
			alert("상품명을 입력해주세요.");
			nameCheckFlag = false;
		}
	}
	
	
</script>
</head>
<%@ include file="../inc/common.jsp"%>
<div class="container">
	<form>
		<input type="hidden" name="owner_id" value="<%=owner.getOwner_id()%>"/>
		<div class="outerbox">
			<label for="item_name">상품이름</label>
			<div class="box">
				<input type="text" id="item_name" name="item_name" placeholder="상품이름을 등록하세요">
				<input type="button" value="중복 확인" onClick="nameCheck()">
			</div>
		</div>
		<div class="outerbox">
			<label>카테고리</label>
			<div class="box">
				<div style="width: 60%;">
					<select name="category_id">
						<option value="0">선택하세요</option>
						<%for(Category category : categoryList){ %>
						<option value="<%=category.getCategory_id() %>">
							<%=category.getCategory_name() %>
						</option>
						<%} %>
					</select>
				</div>
			</div>
		</div>
		<div class="outerbox">
			<label for="lname">상품가격</label>
			<div class="box">
				<input type="number" min="0" name="price" placeholder="상품가격을 등록하세요" required />
			</div>
		</div>
		<div class="outerbox">
			<label for="lname">상품 수량</label><br>
			<div class="box">
				<input type="number" min="0" name="stock" placeholder="상품 수량을 등록하세요" required />
			</div>
		</div>

		<div class="outerbox">
			<label for="lname">상품 이미지</label><br>
			<div class="box">
				<input type="file" name="photo" required />
			</div>
		</div>
		<div>
			<div class="buttonbox">
				<input type="reset" value="취소"> 
				<input onClick="regist()" type="button" value="추가">
			</div>
		</div>
	</form>
</div>
<%@ include file="../inc/footer.jsp"%>