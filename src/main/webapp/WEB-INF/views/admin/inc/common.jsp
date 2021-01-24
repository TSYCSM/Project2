<%@ page contentType="text/html;charset=utf-8"%>

<div>
	<div class = "header">
		<span align = "left" style="font-size:20px;cursor:pointer" onclick="openNav()">&#9776;</span>
	</div>
	<div id ="main" align="center">
		<%@ include file="sidenavi.jsp" %>
	  	<%@ include file="fixednavi.jsp" %>
	<body onload="loadNav()">