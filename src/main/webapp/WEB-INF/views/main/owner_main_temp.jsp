<%@page import="com.tsycsm.agileoffice.model.domain.Owner"%>
<%@ page contentType="text/html;charset=utf-8"%>
<% Owner owner = (Owner)session.getAttribute("owner"); %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<meta charset="utf-8">
<title>choice page</title>
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
		<a href="/client/main/customerCredential"><i class="fa fa-shopping-cart" aria-hidden="true"></i></a><br>
	<i class="fa fa-shopping-cart" aria-hidden="true"></i>
		Front Office
		<a href="/client/owner/inventory/item/list"><i class="fa fa-file-text" aria-hidden="true"></i></a><br>
		Back Office
	</div>

</body>
</html>





