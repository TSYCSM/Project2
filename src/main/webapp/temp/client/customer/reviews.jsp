<%@page import="com.tsycsm.agileoffice.common.board.Pager"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	int listSize = 234;
	Pager pager = new Pager();
	pager.init(request, listSize);
%>

<div id="reviews" class="tabcontent">
	<h1>리뷰</h1>
	<table id="reviewTable">
		<tr>
			<th>No</th>
			<th>상품명</th>
			<th>제목</th>
			<th>작성일</th>
			<th></th>
			<th></th>
		</tr>
		<%
		int num = pager.getNum();
		int curPos = pager.getCurPos();
		%>
		<%for(int i=0; i<pager.getPageSize(); i++){ %>
		<%if(num < 1) break; %>
		<tr>
			<td><%=num-- %></td>
			<td>복사기</td>
			<td><p>가격 대비 사성능이 좋아요</p></td>
			<td>2020-11-10 12:24:12</td>
			<td><button onclick="modeChange(this)">수정</button></td>
			<td><button>삭제</button></td>
		</tr>
		<%} %>
		<tr>
			<td colspan="6" style="text-align:center">
				<a href="/customer/main.jsp?currentPage=<%=pager.getFirstPage()-1%>">◀</a>
				<%for(int i=pager.getFirstPage(); i<=pager.getLastPage(); i++){ %>
				<%if(i > pager.getTotalPage()) break; %>
				<a href="/customer/main.jsp?currentPage=<%=i %>">[<%=i %>]</a>
				<%} %>
				<a href="/customer/main.jsp?currentPage=<%=pager.getLastPage()+1%>">▶</a>
			</td>
		</tr>
	</table>
	<button>등록</button>
</div>