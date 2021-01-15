<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>owner page</title>
<%@ include file="../inc/header.jsp" %>
<style>

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


.container {
	border-radius: 5px;
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
			<input style="float: right" type="button" value="메일 보내기" onClick="send()">
		</form>
	</div>
<%@ include file="../inc/footer.jsp" %>

