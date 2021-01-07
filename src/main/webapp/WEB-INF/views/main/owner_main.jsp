<%@page import="com.tsycsm.agileoffice.model.domain.Owner"%>
<%@ page contentType="text/html;charset=utf-8"%>
<% Owner owner = (Owner)session.getAttribute("owner"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<style>
#divElement{
    position: relative;
    top: 50%;
    left: 50%;
    margin-left: -500px;
    margin-top: -350px;
    width: 1000px;
    height: 700px;
    background: black;
}​


.customerBox{
	posision:relative;
	float:left;
	width: 30%;
	height: 50%;
	background-color: orange;
}

.backOfficeBox{
	posision:relative;
	float:right;
	width: 30%;
	height: 50%;
	background-color: blue;
}
</style>
</head>
<body>
main_choice 나오는 곳<br>
<%=owner.getUser_id() %>님 환영합니다.
<div id="divElement">
	<div class="customerBox">주문하기</div>
	<div class="backOfficeBox">백오피스</div>
</div>
</body>
</html>





