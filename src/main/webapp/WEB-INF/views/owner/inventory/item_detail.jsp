<%@page import="com.tsycsm.agileoffice.model.domain.Category"%>
<%@page import="java.util.List"%>
<%@page import="com.tsycsm.agileoffice.model.domain.Item"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	Item item = (Item)request.getAttribute("item");
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
	$(function(){
		$("#bt_update").click(function(){
			update();
		});	
	});

	function update() {
		var formData = $("#item-form").serialize();
		if(confirm("수정하시겠습니까")){
			$.ajax({
				url: "/rest/owner/inventory/item/update",
				type: "post",
				data: formData,
				success: function(responseData){
					if(responseData.resultCode==1){
						alert(responseData.msg);
						location.href="/client/owner/inventory/item/detail?item_id="+<%=item.getItem_id()%>;
					}else{
						alert(responseData.msg);					
					}
				}
			});
		}	
	}		
		

	function del() {
		if(confirm("삭제하시겠습니까?")){
			$("form").attr({
				action:"/client/owner/inventory/item/del",
				enctype:"multipart/form-data",
				method:"post"
			});
			$("form").submit();
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
<%@ include file="../inc/common.jsp" %>
<div class="container">
	<form id="item-form">
		<input type="hidden" name="owner_id" value="<%=item.getOwner_id()%>"/>
		<input type="hidden" name="item_id" value="<%=item.getItem_id()%>"/>
		<input type="hidden" name="filename" value="<%=item.getFilename()%>"/>
		<div class="outerbox">
			<label for="fname">상품이름</label>
			<div class="box">
				<input type="text" id="item_name" name="item_name" value="<%=item.getItem_name() %>">
				<input type="button" value="중복 확인" onClick="nameCheck()">
			</div>
		</div>
		<div class="outerbox">
			<label for="lname">카테고리</label>
			<div class="box">
				<div style="width: 60%;">
					<select name="category_id">
						<option value="0">선택하세요</option>
						<%for(Category category : categoryList){ %>
						<option value="<%=category.getCategory_id() %>" <%if(item.getCategory_id()==category.getCategory_id()) {%>selected<%}%>>
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
				<input type="number" min="0" name="price" value="<%=item.getPrice()%>" required />
			</div>
		</div>
		<div class="outerbox">
			<label for="lname">상품 수량</label><br>
			<div class="box">
				<input type="number" min="0" name="stock" value="<%=item.getStock()%>" required />
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
				<input id="bt_update" type="button" value="수정">
				<input onClick="del()" type="button" value="삭제">
			</div>
		</div>
	</form>
</div>
<%@ include file="../inc/footer.jsp"%>