<%@page import="com.tsycsm.agileoffice.model.domain.Owner"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	Owner owner = (Owner)session.getAttribute("owner");
%>
<div>
	<div class = "header">
		<span style="font-size:20px;cursor:pointer" onclick="openNav()">&#9776;&emsp;</span>
		<span style="font-size:20px;"><%=owner.getUser_id() %>님 환영합니다.</span>
		<a href="javascript:logout()" style="color:white; float:right; padding-right:50px; font-size:23px;">
			<i class="fa fa-sign-out" aria-hidden="true"></i>
		</a>
		<a href="/client/main/ownerMain" style="color:white; float:right; padding-right:50px; font-size:23px;">
			<i class="fa fa-home" aria-hidden="true"></i>
		</a>
	</div>
	<div id ="main" align="center">
		<%@ include file="sidenavi.jsp" %>
	  	<%@ include file="fixednavi.jsp" %>
	  	<%@ include file="childbar.jsp" %>
	<body onload="loadNav()">