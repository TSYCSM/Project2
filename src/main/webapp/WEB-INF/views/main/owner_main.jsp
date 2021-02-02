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
	background-color: #f0f0f0;
	text-align: center;
}

h1 {
	margin-top: 50px;
}

#wrapper {
	margin: auto;
	margin-top: 150px;
	width: 700px;
}

#front, #back {
	float: left;
	width:50%;
	font-size: 30px;
}

#front a, #back a {
	display: inline-block;
	padding: 20px;
	text-decoration: none;
}

#front a:hover, #back a:hover {
	transition: 0.3s;
	opacity: 0.7;
}

#front a {
	color: #bd6800;
}
#back a {
	color: #3d4c9c;
}

#front i, #back i {
	font-size: 150px;
}

</style>
</head>
<body>
	<h1>Welcome!, <%=owner.getUser_id() %></h1>

	<div id="wrapper">
		<div id="front">
			<a href="/client/main/customerCredential"><i class="fa fa-shopping-cart" aria-hidden="true"></i><br><br>Front Office</a><br>
		</div>
		<div id="back">
			<a href="/client/owner/inventory/item/list"><i class="fa fa-file-text" aria-hidden="true"></i><br><br>Back Office</a><br>
		</div>
		<div style="clear:both"></div>
	</div>

</body>
</html>





