<%@page import="com.tsycsm.agileoffice.model.domain.Owner"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	Owner owner = (Owner)session.getAttribute("owner");
%>
<div>
	<div class = "header">
		<span align = "left" style="font-size:20px;cursor:pointer" onclick="openNav()">&#9776;&emsp;<%=owner.getUser_id() %>님 환영합니다.</span>
	</div>
	<div id ="main" align="center">
		<%@ include file="sidenavi.jsp" %>
	  	<%@ include file="fixednavi.jsp" %>
	  	<%@ include file="childbar.jsp" %>
	<body onload="loadNav()">