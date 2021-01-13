<%@page import="com.tsycsm.agileoffice.model.domain.Owner"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	Owner owner = (Owner)session.getAttribute("owner");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type = "text/javascript" src="/resources/js/owner/library.js"></script>
<script>

</script>

</head>
<body>
<form id="checkPassword-box">
	새로운 비밀번호를 입력하세요
	<input type="password" name="password">
	<input type="hidden" name="user_id" value="<%=owner.getUser_id()%>">
	<input type="button" onClick="getNewPassword()" value="확인">
</form>
</body>
</html>