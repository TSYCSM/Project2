<%@page import="com.tsycsm.agileoffice.model.domain.Board"%>
<%@page import="com.tsycsm.agileoffice.model.common.Pager"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	Pager pager = (Pager)request.getAttribute("pager");
	List<Board> boardList = (List<Board>)pager.getList();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>owner page</title>
<%@ include file="../inc/header.jsp" %>
</head>
<%@ include file="../inc/common.jsp"%>
<table>
	<tr>
		<th>No</th>
		<th>제목</th>
		<th>작성자</th>
		<th>등록일</th>
		<th>조회수</th>
	</tr>
	
	<%
		int num = pager.getNum();
		int curPos = pager.getCurPos();
	%>
	<%for(int i=0; i<pager.getPageSize(); i++){ %>
		<%if(num < 1) break; %>
		<%Board board = boardList.get(curPos++); %>
		<tr>
			<td><%=num-- %></td>
			<td><a href="/client/owner/board/detail?board_id=<%=board.getBoard_id()%>"><%=board.getTitle() %></a></td>
			<td><%=board.getWriter() %></td>
			<td><%=board.getRegdate().substring(0, 10) %></td>
			<td><%=board.getHit() %></td>
		</tr>
	<%} %>
	<tr>
		<td colspan="5" style="text-align:center">
			<%if(pager.getFirstPage() >1){ %>
				<a href="/client/owner/board/list?currentPage=<%=pager.getFirstPage()-1%>">◀</a>					
			<%}else{ %>
				<a href="javascript:alert('처음 페이지 입니다')">◀</a>
			<%} %>
			<%for(int i=pager.getFirstPage(); i<=pager.getLastPage(); i++){ %>
			<%if(i > pager.getTotalPage()) break; %>
			<a href="/client/owner/board/list?currentPage=<%=i%>">[<%=i %>]</a>
			<%} %>
			<%if(pager.getLastPage() < pager.getTotalPage()) {%>
				<a href="/client/owner/board/list?currentPage=<%=pager.getLastPage()+1%>">▶</a>
			<%}else{ %>
				<a href = "javascript:alert('마지막 페이지입니다.')">▶</a>
			<%} %>
		</td>
	</tr>
	<tr style="background-color: #f0f0f0;">
		<td colspan="5">
			<button onClick="location.href='/client/owner/board/registform'">등록</button>
		</td>
	</tr>
</table>
<%@ include file="../inc/footer.jsp" %>












