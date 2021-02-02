<%@page import="com.tsycsm.agileoffice.model.domain.Category"%>
<%@page import="com.tsycsm.agileoffice.model.domain.Item"%>
<%@page import="java.util.List"%>
<%@page import="com.tsycsm.agileoffice.model.domain.Owner"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	List<Category> categoryList =(List)request.getAttribute("categoryList");
 
%>

<div id="items" class="tabcontent">
	<div id="itemlist">
		<h1>Menu</h1>
		<%for(Item item : itemList){ %>		
			<div class="item" onclick="appendItem(this)">
				<input type="hidden" value="<%=item.getItem_id() %>"> 
				<input type="hidden" value="<%=item.getStock()%>"> 
				<img src="/resources/data/<%=item.getItem_id() %>.<%=item.getFilename() %>" />
				<h2><%=item.getItem_name() %></h2>
				<h3>
					<span><%= item.getPrice() %></span> Won
				</h3>
			</div>
		<%} %>
		
	
	</div>

	<div id="cartlist">
		<form class="order-form">
			<h1>Cart</h1>
			<table id="itemTable"></table>
			<!-- <textArea placeholder="세부 요구 사항 입력"></textArea> -->
			<p>
				Total : <span>0</span> Won
				<input type="hidden" name="total_price">
			</p>
			<button type="button" onClick="order()">Order</button>
		</form>
	</div>

</div>