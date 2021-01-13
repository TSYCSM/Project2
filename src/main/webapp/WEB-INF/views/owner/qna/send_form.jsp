<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Email Send</title>
<%@ include file="../inc/header.jsp" %>
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

* {
	box-sizing: border-box;
}

input[type=text], select, textarea {
	width: 100%;
	padding: 12px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	margin-top: 6px;
	margin-bottom: 16px;
	resize: vertical;
}

input[type=button] {
	background-color: #4CAF50;
	color: white;
	padding: 12px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

input[type=button]:hover {
	background-color: #45a049;
}

.container {
	border-radius: 5px;
	background-color: #f2f2f2;
	padding: 20px;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(function(){
		
	});

	function send(){
		$("form").attr({
			method: "post",
			action: "/owner/qna/send"
		});
		$("form").submit();
	}

</script>
</head>

<%@ include file="../inc/common.jsp"%>

	<div class="container">
	<h3>운영자에게 메일 보내기</h3>
		<form>
			<input type="text" name="title" placeholder="Title..">
			<input type="hidden" name="email_address" value="<%=owner.getEmail_id()%>@<%=owner.getEmail_server()%>">
			<input type="hidden" name="name" value="<%=owner.getUser_name()%>">
			<textarea id="content" name="content" placeholder="Write something.." style="height: 200px"></textarea>
			<input type="button" value="메일 보내기" onClick="send()">
			<input type="button" value="Home" onClick="location.href='/owner/inventory/item/list'">
		</form>
	</div>
<%@ include file="../inc/footer.jsp" %>

