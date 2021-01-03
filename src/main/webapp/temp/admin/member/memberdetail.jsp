<%@page import="com.tsycsm.agileoffice.common.board.Pager"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	int listSize = 234;
	Pager pager = new Pager();
	pager.init(request, listSize);
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>memberlist</title>
	<%@ include file="../inc/header.jsp" %>
<script>
/* $(function(){
	$("#reportbox").hide();
});

function showReport(){
	$("#reportbox").show();
} */
</script>
</head>
	
<%@ include file="../inc/common.jsp"%>
<div class="bigcontainer">
	<%@ include file="inc/memberbox.jsp" %>
		
	<div id="reportbox" class="container" style="left: 800px;">
					<div class="bigbox">
						<label for="fname">등록된 상품</label>
						<div class="smallbox">
						<form>
							<table  style="width: 100%">
								<tr>
									<th>No</th>
									<th>상품명</th>
									<th>카테고리</th>
									<th>수량</th>
									<th>등록일자</th>
								</tr>
								<%
								int num = pager.getNum();
								int curPos = pager.getCurPos();
								%>
								<%for(int i=0; i<pager.getPageSize(); i++){ %>
									<%if(num < 1) break; %>
									<tr>
										<td><%=num-- %></td>
										<td>redbox</td>
										<td>box</td>
										<td>5</td>
										<td>2021-01-03</td>
									</tr>
								<%} %>
								<tr>
									<td colspan="5" style="text-align:center">
										<a href="/temp/admin/member/memberdetail.jsp?currentPage=<%=pager.getFirstPage()-1%>">◀</a>
										<%for(int i=pager.getFirstPage(); i<=pager.getLastPage(); i++){ %>
										<%if(i > pager.getTotalPage()) break; %>
										<a href="/temp/admin/member/memberdetail.jsp?currentPage=<%=i %>">[<%=i %>]</a>
										<%} %>
										<a href="/temp/admin/member/memberdetail.jsp?currentPage=<%=pager.getLastPage()+1%>">▶</a>
									</td>
								</tr>
							</table>
							</form>
							
						</div>					
					</div>

			</div>
		</div>
</div>
		
<%@ include file="../inc/footer.jsp" %>
		