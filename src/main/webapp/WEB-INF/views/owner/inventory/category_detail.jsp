<%@page import="com.tsycsm.agileoffice.model.domain.Category"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	Category category = (Category)request.getAttribute("category");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>owner page</title>
<%@ include file="../inc/header.jsp" %>
<style>

</style>
<script>
$(function(){
	$(".update_btn").click(function(){
		update();
	});
	
	$(".delete_btn").click(function(){
		del();
	});
});

function del(){
	if(confirm("삭제하시겠습니까")){
		location.href = "/client/owner/inventory/category/delete?category_id=" + <%=category.getCategory_id()%>;
	}	
}


function update(){
	var obj={}; //define empty json
	obj["owner_id"]=$("#owner_id").val();
	obj["category_id"]=$("#category_id").val();
	obj["category_name"]=$("#category_name").val();
	obj["color"]=$("#color").val();
	
	var jsonString = JSON.stringify(obj);
	console.log(jsonString);
	var formData = $("#category_form").serialize();
	if(confirm("수정하시겠습니까")){
		$.ajax({
			url: "/rest/owner/inventory/category/"+$("#category_id").val(),
			type: "put",
			data: jsonString,
			contentType:"application/json;charset=utf-8",
			success: function(responseData){
				alert(responseData.msg);
				location.href="/client/owner/inventory/category/detail?category_id="+<%=category.getCategory_id()%>;
			}
		});
	}	
}	
</script>
</head>
<%@ include file="../inc/common.jsp"%>

<div class="container">
	<form id="category_form">
		<div class="outerbox">
			<label for="fname">상품이름</label>
			<div class="box">
				<input type="hidden" id="owner_id" value="<%=category.getOwner_id()%>"/>
				<input type="hidden" id="category_id" value="<%=category.getCategory_id()%>"/>
				<input type="text" id="category_name" value="<%=category.getCategory_name()%>">
			</div>					
		</div>
		
		<div class="outerbox">
			<label for="lname">색상 및 모양</label><br>
			<div class="box">
				<input type="color" value = "<%=category.getColor()%>" id="color" list="presetColors">
				  <datalist id="presetColors">

				  </datalist>
			</div>
		</div>					
		<div>
			<div class="buttonbox">
				<input type="button" onClick="location.href='/client/owner/inventory/category/list'" value="목록으로">
				<input type="button" class= "update_btn" value="수정">
				<input type="button" class="delete_btn" value="삭제">
			</div>
		</div>
	</form>
</div>
<%@ include file="../inc/footer.jsp" %>