<%@page import="com.tsycsm.agileoffice.model.domain.Category"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>owner page</title>
<%@ include file="../inc/header.jsp"%>
<style>

</style>
<script>
function regist() {
	var formData = new FormData($("form")[0]);
	$.ajax({
		url : "/client/owner/inventory/item/dump/regist",
		data : formData,
		contentType : false,
		processData : false,
		type : "post",
		success : function(responseData) {
			if(responseData.resultCode == 1) {
				alert(responseData.msg);
				location.href = responseData.url;
			} else {
				alert(responseData.msg);
			}
		}
	});
}	
	
</script>
</head>
<%@ include file="../inc/common.jsp"%>
<div class="container">
	<form>
		<p>
			엑셀 파일 선택 : <input type="file" name="dump"/>
		</p>
		<input type="button" value="데이터 등록" onclick="regist()"> 
	</form>
</div>
<%@ include file="../inc/footer.jsp"%>