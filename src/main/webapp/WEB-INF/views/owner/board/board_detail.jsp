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
<script>
$(function() {
	getList();
});

function getList() {
	$.ajax({
		url: "/rest/comments/" + <%=board.getBoard_id() %>,
		type: "GET",
		success: function(responseData) {
			console.log("success : ", responseData);
			var tag = "";
			
		},
		error: function(xhr, status, error) {
			console.log("error : ", xhr);
		}
	});
}

function regist() {
	var obj = {};
	obj["owner_id"] = $("#owner_id").val();
	obj["board_id"] = $("#board_id").val();
	obj["content"] = $("#content").val();
		
	var jsonString = JSON.stringify(obj);

	$.ajax({
		url: "/rest/comments",
		type: "POST",
		data: jsonString,
		contentType: "application/json;charset=utf-8",
		success: function(responseData) {
			getList();
		},
		error: function(xhr, status, error) {
		}
	});
}

</script>
</head>
<%@ include file="../inc/common.jsp"%>

<div id="board-container">
	<h1 class="title"><%=board.getTitle()%></h1>
	<p class="writer"><%=board.getWriter()%></p>
	<p class="content"><%=board.getContent()%></p>
	<button>삭제</button>
	<button>수정</button>
</div>
<table id="comments-container">
	<tr>
		<td colspan="2">
			<input id="owner_id" type="hidden" value="<%=owner.getOwner_id() %>"/>
			<input id="board_id" type="hidden" value="<%=board.getBoard_id() %>"/>
			<input id="content" type="text" name="content"/>
		</td>
		<td>
			<button onClick="getList()">새로고침</button>
			<button onClick="regist()">등록</button>
		</td>
	</tr>
	<tbody id="comments-list">
	
	</tbody>
</table>


<%@ include file="../inc/footer.jsp"%>