<%@page import="com.tsycsm.agileoffice.model.domain.Category"%>
<%@page import="com.tsycsm.agileoffice.model.common.Pager"%>
<%@page import="com.tsycsm.agileoffice.model.domain.Item"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	List<Item> itemList = (List<Item>)request.getAttribute("itemList");
	List<Category> categoryList = (List<Category>)request.getAttribute("categoryList");
	Pager pager = new Pager();
	pager.init(request, itemList.size());
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>owner page</title>
<%@ include file="../inc/header.jsp" %>
<style>
.add_btn{
	position: absolute;
	left: 300px;
}
</style>
</head>
<%@ include file="../inc/common.jsp"%>
<button class="add_btn" type="button" onclick="location.href='/client/owner/inventory/item/registform'">상품추가</button>
<br>
<br>
<input name="categoryIdList" onChange="filtering()" type="checkbox" value="0" checked/>카테고리 없음  |  
<%for(Category category : categoryList) { %>
<input name="categoryIdList" onChange="filtering()" type="checkbox" value="<%=category.getCategory_id() %>" checked/><%=category.getCategory_name() %>  |  
<%} %>
<br>
<br>
<table id="item-list">
	<tr id="list-title">
		<th>No</th>
		<th>품명</th>
		<th>가격</th>
		<th>재고</th>
		<th>등록일</th>
	</tr>
	<tbody id="list-contents">
		<%
		int num = pager.getNum();
		int curPos = pager.getCurPos();
		%>
		<%for(int i=0; i<pager.getPageSize(); i++){ %>
		<%if(num < 1) break; %>
		<%Item item = itemList.get(curPos++); %>
		<tr>
			<td><%=num-- %></td>
			<td><a href="/client/owner/inventory/item/detail?item_id=<%=item.getItem_id()%>"><%=item.getItem_name() %></a></td>
			<td><%=item.getPrice() %></td>
			<td><%=item.getStock() %></td>
			<td><%=item.getRegdate() %></td>
		</tr>
		<%} %>
	</tbody>
	<tr>
		<td colspan="6" style="text-align:center">
			<%if(pager.getFirstPage() >1){ %>
				<a href="/client/owner/inventory/item/list?currentPage=<%=pager.getFirstPage()-1%>">◀</a>					
			<%}else{ %>
				<a href = "javascript:alert('처음 페이지 입니다')">◀</a>
			<%} %>
			<%for(int i=pager.getFirstPage(); i<=pager.getLastPage(); i++){ %>
			<%if(i > pager.getTotalPage()) break; %>
			<a href="/client/owner/inventory/item/list?currentPage=<%=i %>">[<%=i %>]</a>
			<%} %>
			<%if(pager.getLastPage() < pager.getTotalPage()) {%>
				<a href="/client/owner/inventory/item/list?currentPage=<%=pager.getLastPage()+1%>">▶</a>
			<%}else{ %>
				<a href = "javascript:alert('마지막 페이지입니다.')">▶</a>
			<%} %>
		</td>
	</tr>
</table>
<%@ include file="../inc/footer.jsp" %>

<script>
	
	$(function() {
		filtering();
	});

	function filtering() {
		$("#list-contents").html("");

		for(var i=0; i<$("input[name='categoryIdList']").length; i++) {
			if($($("input[name='categoryIdList']")[i]).is(":checked") == true) {
				$.ajax({
					url: "/client/owner/inventory/item/list/filtered?category_id=" + $($("input[name='categoryIdList']"))[i].value
						+ "&owner_id=" + <%=owner.getOwner_id() %>,
					success: function(data) {
						for(var i=0; i<data.length; i++) {
							var tag = "";
							tag += "<tr>";
							tag += "<td>1</td>";
							tag += "<td><a href='/client/owner/inventory/item/detail?item_id=" + data[i].item_id + "'>" + data[i].item_name + "</a></td>";
							tag += "<td>" + data[i].price + "</td>";
							tag += "<td>" + data[i].stock + "</td>";
							tag += "<td>" + data[i].regdate + "</td>";
							tag += "</tr>";
							
							$("#list-contents").append(tag);
						}
					}
				});
			} 
		}
	}
</script>





