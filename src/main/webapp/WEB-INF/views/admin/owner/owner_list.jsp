<%@page import="com.tsycsm.agileoffice.model.common.Pager"%>
<%@page import="com.tsycsm.agileoffice.model.domain.Owner"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	Pager pager = (Pager)request.getAttribute("pager");
	List<Owner> ownerList = pager.getList();
 %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>ownerList</title>
	<%@ include file="../inc/header.jsp" %>
</head>
	
<%@ include file="../inc/common.jsp"%>
<table>
	<tr>
		<th>상호명</th>
		<th>이름</th>
		<th>이메일</th>
		<th>등록일</th>
	</tr>
	<%
		int num = pager.getNum();
		int curPos = pager.getCurPos();
	%>
	<%for(int i=0; i<pager.getPageSize(); i++) { %>
	<%if(num < 1) {break;} %>
	<%Owner owner = ownerList.get(curPos++); %>
	<tr>
		<td><a href="/admin/owner/detail?owner_id=<%=owner.getOwner_id()%>"><%=owner.getShopname() %></a></td>
		<td><%=owner.getUser_name() %></td>
		<td><%=owner.getEmail_id() %>@<%=owner.getEmail_server() %></td>
		<td><%=owner.getRegdate().substring(0, 10) %></td>
	</tr>
	<%
		num = num-1; 
	%>
	<%} %>
	<tr>
		<td colspan="4" style="text-align:center">
			<%if(pager.getFirstPage() != 1) { %>
				<a href="/admin/owner/list?currentPage=<%=pager.getFirstPage()-1 %>">◀</a>
			<%} %>
			<%for(int i=pager.getFirstPage(); i<=pager.getLastPage(); i++) { %>
				<%if(i > pager.getTotalPage()) {break;} %>
					<a href="/admin/owner/list?currentPage=<%=i %>">[<%=i %>]</a>
				<%} %>
			<%if(pager.getTotalPage() > pager.getLastPage()) { %>
				<a href="/admin/owner/list?currentPage=<%=pager.getLastPage()+1 %>">▶</a>
			<%} %>
		</td>
	</tr>
</table>
<%@ include file="../inc/footer.jsp" %>
		