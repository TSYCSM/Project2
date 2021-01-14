<%@page import="com.tsycsm.agileoffice.model.domain.Owner"%>
<%@ page contentType="text/html;charset=utf-8"%>
<% Owner owner = (Owner)session.getAttribute("owner"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<style>

</style>
</head>
<body>
main_choice 나오는 곳<br>
<%=owner.getUser_id() %>님 환영합니다.

	<button type="button" onClick="location.href='/client/main/customerCredential'">주문하기</button>
	<button type="button" onClick="location.href='/client/owner/inventory/item/list'">백오피스</button>

</body>
</html>





