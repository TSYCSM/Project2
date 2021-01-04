<%@page import="com.tsycsm.agileoffice.common.board.Pager"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	int listSize = 234;
	Pager pager = new Pager();
	pager.init(request, listSize);
%>

<!-- 일단 memberdetail에 들어오면 owner_id로 select 된 itemlist를 받아온다.
	각 페이지를 클릭할때 currentPage를 getPage function에 매개변수로 넘겨준다.
	$.ajax해서 각 페이지에 있는 subList를 제이슨으로 가져온다.
	table단에 새롭게 subList를 차례대로 뿌려준다.
	문제) subList를 가져올때 controller에 어느 변수를 넘겨주어야 할까??
		currentPage로 새롭게 갱신된 Pager객체로 넘겨야 될까????
		정말 모르겠다.... -->

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>memberlist</title>
	<%@ include file="../inc/header.jsp" %>
<script>
$(function(){
	/* $("#reportbox").hide(); */
	
	
	
});

/* function showReport(){
	$("#reportbox").show();
} */

function getPage(page){
	/* 페이징 비동기 처리 미완성 */
	$.ajax({
		url: "/admin/member/getpage",
		type: "get",
		data: {
			currentPage = page
		},
		success: function(result){
			
		}
	});
}

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
									<tr class="asynctr">
										<td><%=num-- %></td>
										<td>redbox</td>
										<td>box</td>
										<td>5</td>
										<td>2021-01-03</td>
									</tr>
								<%} %>
								<tr>
									<td colspan="5" style="text-align:center">
										<%if(pager.getFirstPage() >1){ %>
											<a href="/admin/member/detail?currentPage=<%=pager.getFirstPage()-1%>">◀</a>					
										<%}else{ %>
											<a href = "javascript:alert('처음 페이지 입니다')">◀</a>
										<%} %>
										<%for(int i=pager.getFirstPage(); i<=pager.getLastPage(); i++){ %>
										<%if(i > pager.getTotalPage()) break; %>
										<a href="/admin/member/detail?currentPage=<%=i%>">[<%=i %>]</a>
										<%} %>
										<%if(pager.getLastPage() < pager.getTotalPage()) {%>
											<a href="/admin/member/detail?currentPage=<%=pager.getLastPage()+1%>">▶</a>
										<%}else{ %>
											<a href = "javascript:alert('마지막 페이지입니다.')">▶</a>
										<%} %>
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
		