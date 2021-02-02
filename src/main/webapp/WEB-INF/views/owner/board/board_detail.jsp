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
#comments-list {
	position: absolute;
	top: 600px;
}
table {
	position: absolute;
    top: 100px;
    left: 150px;
    width: 1150px;
}
</style>
<script src="https:/unpkg.com/react@16/umd/react.production.min.js"></script>
<script src="https://unpkg.com/react-dom@16/umd/react-dom.production.min.js"></script>
<script src="https://unpkg.com/babel-standalone@6.15.0/babel.min.js"></script>
<script type="text/babel">
class TableContent extends React.Component{
	render(){
		console.log("dataList is ", this.props.dataList);			
		var row = [];
		
		for(var i=0; i<this.props.dataList.length; i++){
			var comments = this.props.dataList[i];
			row.push(
				<tr>
					<td>{comments.writer}</td>
					<td>{comments.content}</td>
					<td>{comments.regdate.substring(0, 10)}</td>
				</tr>
			);
		}
		return (
			<table>
				{row}
			</table>
		);
	}
}
$(function() {
	getList();
});
function getList() {
	$.ajax({
		url: "/rest/comments/" + <%=board.getBoard_id() %>,
		type: "GET",
		success:function(responseData){
			console.log("서버로부터 받은 json배열은 ", responseData);
			//우리가 정의한 컴포넌트를 사용해보자
			ReactDOM.render(<TableContent dataList={responseData} /> , document.getElementById("comments-list"));
		},
		error:function(xhr, status, error){
			console.log(xhr);
		}		
	});
}
function regist() {
	var obj = {};
	obj["owner_id"] = $("#owner_id").val();
	obj["board_id"] = $("#board_id").val();
	obj["writer"] = $("#writer").val();
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
	<button>Delete</button>
	<button>Update</button>
</div>
<table id="comments-container">
	<tr>
		<td colspan="2">
			<input id="owner_id" type="hidden" value="<%=owner.getOwner_id() %>"/>
			<input id="board_id" type="hidden" value="<%=board.getBoard_id() %>"/>
			<input id="writer" type="hidden" value="<%=board.getWriter() %>"/>
			<input id="content" type="text" name="content"/>
		</td>
		<td>
			<button onClick="getList()">Refresh</button>
			<button onClick="regist()">Register</button>
		</td>
	</tr>
</table>
<div id="comments-list"></div>




<%@ include file="../inc/footer.jsp"%>