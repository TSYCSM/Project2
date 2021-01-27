<%@page import="com.tsycsm.agileoffice.model.domain.Board"%>
<%@page import="com.tsycsm.agileoffice.model.common.Pager"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
Board board = (Board) request.getAttribute("board");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>owner page</title>
<%@ include file="../inc/header.jsp"%>
<style>
#board-container {
	position: absolute;
	left: 150px;
	top: 50px;
	background-color: #fff;
	border: 1px solid #ddd;
	width: 70%;
	height: 500px;
	text-align: left;
	padding-left: 50px;
	padding-right: 50px;
}

#comments-container {
	position: absolute;
	left: 150px;
	top: 600px;
	background-color: #fff;
	border: 1px solid #ddd;
	width: 70%;
	text-align: left;
}

.title {
}
.writer {
	float: right;
}
.content {
	margin-top: 80px;
	margin-bottom: 50px;
}


</style>
</head>
<%@ include file="../inc/common.jsp"%>

<div id="board-container">
	<h1 class="title"><%=board.getTitle()%></h1>
	<p class="writer"><%=board.getWriter()%></p>
	<p class="content"><%=board.getContent()%></p>
</div>
<table id="comments-container">
	<tr>
		<td colspan="2">
			<input type="text" name="content"/>
		</td>
		<td>
			<button>등록</button>
		</td>
	</tr>
</table>


<%@ include file="../inc/footer.jsp"%>