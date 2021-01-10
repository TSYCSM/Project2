<%@page import="com.tsycsm.agileoffice.model.domain.Category"%>
<%@page import="com.tsycsm.agileoffice.model.domain.Item"%>
<%@page import="java.util.List"%>
<%@page import="com.tsycsm.agileoffice.model.domain.Owner"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	Owner owner = (Owner)session.getAttribute("owner");
	List<Item> itemList =(List)request.getAttribute("itemList");
	List<Category> categoryList =(List)request.getAttribute("categoryList");

%>

<div id="items" class="tabcontent">
	<div id="itemlist">
		<h1>상품 목록</h1>
		<%for(Item item : itemList){ %>		
			<div class="item" onclick="appendItem(this)">
				<input type="hidden" value="<%=item.getItem_id() %>"> 
				<img src="/resources/data/<%=item.getItem_id() %>.<%=item.getFilename() %>" />
				<h2><%=item.getItem_name() %></h2>
				<h3>
					<span><%=item.getPrice() %></span>원
				</h3>
			</div>
		<%} %>
		

	</div>

	<div id="cartlist">
		<form class="order-form">
			<h1>장바구니</h1>
			<table id="itemTable"></table>
			<textArea placeholder="세부 요구 사항 입력"></textArea>
			<p>
				합계 : <span>0</span>원
				<input type="hidden" name="total_price">
			</p>
			<button type="button" onClick="order()">주문</button>
		</form>
	</div>

</div>
