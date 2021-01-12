<%@page import="com.tsycsm.agileoffice.model.domain.Owner"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	List<Owner> ownerList = (List)request.getAttribute("ownerList");
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
				<%for(Owner owner : ownerList) { %>
				<tr>
					<td><a href="/admin/owner/owner_id="<%=owner.getOwner_id()%>><%=owner.getShopname() %></a></td>
					<td><%=owner.getUser_name() %></td>
					<td><%=owner.getEmail_id() %>@<%=owner.getEmail_server() %></td>
					<td><%=owner.getRegdate().substring(0, 10) %></td>
				</tr>
				<%} %>
			</table>
<%@ include file="../inc/footer.jsp" %>
		