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
				url:"/rest/owner/inventory/item/nameCheck",
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
				},
				error:function(xhr, status, error){
					console.log("에러 응답데이터 받음: xhr = ",xhr);
					var json = JSON.parse(xhr.responseText);
					alert(json.msg);
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
			<label for="item_name">Item name</label>
			<div class="box">
				<input type="text" id="item_name" name="item_name" placeholder="Input an Item name">
				<input type="button" value="Check name" onClick="nameCheck()">
			</div>
		</div>
		<div class="outerbox">
			<label>Category</label>
			<div class="box">
				<div style="width: 60%;">
					<select name="category_id">
						<option value="0">select a category</option>
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
			<label for="lname">Price</label>
			<div class="box">
				<input type="number" min="0" name="price" placeholder="input price..." required />
			</div>
		</div>
		<div class="outerbox">
			<label for="lname">Stock</label><br>
			<div class="box">
				<input type="number" min="0" name="stock" placeholder="input stock..." required />
			</div>
		</div>

		<div class="outerbox">
			<label for="lname">Image</label><br>
			<div class="box">
				<input type="file" name="photo" required />
			</div>
		</div>
		<div>
			<div class="buttonbox">
				<input type="reset" value="Reset"> 
				<input onClick="regist()" type="button" value="Add">
			</div>
		</div>
	</form>
</div>
<%@ include file="../inc/footer.jsp"%>