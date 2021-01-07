<%@page import="com.tsycsm.agileoffice.model.domain.Category"%>
<%@page import="com.tsycsm.agileoffice.common.Pager"%>
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
<script>
	function filtering() {
		//alert();
		console.log($("input[name='categoryIdList']"));
	}
</script>
</head>
<%@ include file="../inc/common.jsp"%>
<button class="add_btn" type="button" onclick="location.href='/owner/inventory/item/registform'">상품추가</button>
<br>
<br>
<%for(Category category : categoryList) { %>
<input name="categoryIdList" onChange="filtering()" type="checkbox" value="<%=category.getCategory_id() %>"/><%=category.getCategory_name() %>  |  
<%} %>
<br>
<br>
<table>
	<tr>
		<th>No</th>
		<th>품명</th>
		<th>가격</th>
		<th>재고</th>
		<th>등록일</th>
	</tr>
	<%
	int num = pager.getNum();
	int curPos = pager.getCurPos();
	%>
	<%for(int i=0; i<pager.getPageSize(); i++){ %>
	<%if(num < 1) break; %>
	<%Item item = itemList.get(i); %>
	<tr>
		<td><%=num-- %></td>
		<td><a href="/owner/inventory/item/detail?item_id=<%=item.getItem_id()%>"><%=item.getItem_name() %></a></td>
		<td><%=item.getPrice() %></td>
		<td><%=item.getStock() %></td>
		<td><%=item.getRegdate() %></td>
	</tr>
	<%} %>
	<tr>
		<td colspan="6" style="text-align:center">
			<%if(pager.getFirstPage() >1){ %>
				<a href="/owner/inventory/item/list?currentPage=<%=pager.getFirstPage()-1%>">◀</a>					
			<%}else{ %>
				<a href = "javascript:alert('처음 페이지 입니다')">◀</a>
			<%} %>
			<%for(int i=pager.getFirstPage(); i<=pager.getLastPage(); i++){ %>
			<%if(i > pager.getTotalPage()) break; %>
			<a href="/owner/inventory/item/list?currentPage=<%=i %>">[<%=i %>]</a>
			<%} %>
			<%if(pager.getLastPage() < pager.getTotalPage()) {%>
				<a href="/owner/inventory/item/list?currentPage=<%=pager.getLastPage()+1%>">▶</a>
			<%}else{ %>
				<a href = "javascript:alert('마지막 페이지입니다.')">▶</a>
			<%} %>
			
		</td>
	</tr>

</table>
<%@ include file="../inc/footer.jsp" %>






