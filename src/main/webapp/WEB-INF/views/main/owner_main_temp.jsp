<%@page import="com.tsycsm.agileoffice.model.domain.Owner"%>
<%@ page contentType="text/html;charset=utf-8"%>
<% Owner owner = (Owner)session.getAttribute("owner"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<style>
@font-face {
	font-family: 'Jal_Onuel';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-10-21@1.0/Jal_Onuel.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}
*{
	font-family: 'Jal_Onuel';
}

body {
	text-align: center;
	background-color: #f0f0f0;
}


#wrapper button {
	margin-top: 100px;
	border: none;
	height: 300px;
	width: 300px;
	font-size: 30px;
	font-weight: bold;
	color: white;
}
#wrapper button:hover {
	transition: 0.3s;
	opacity: 0.7;
}

#wrapper button:nth-child(1) {
	background-color: #e67200;
	border-radius: 5px;
}

#wrapper button:nth-child(2) {
	margin-left: 50px;
	background-color: #0063d9;
	border-radius: 5px;
}

</style>
</head>
<body>
	<h1><%=owner.getUser_id() %>님 환영합니다.</h1>

	<div id="wrapper">
		<button type="button" onClick="location.href='/client/main/customerCredential'">주문하기</button>
		<button type="button" onClick="location.href='/client/owner/inventory/item/list'">백오피스</button>
	</div>

</body>
</html>





