<%@page import="com.tsycsm.agileoffice.model.domain.Owner"%>
<%@page import="com.tsycsm.agileoffice.model.domain.Category"%>
<%@page import="com.tsycsm.agileoffice.model.domain.Item"%>
<%@page import="java.util.List"%>
<%@page import="com.tsycsm.agileoffice.common.Pager"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
List<Item> itemList = (List) request.getAttribute("itemList");
List<Category> categoryList = (List)request.getAttribute("categoryList");
int customer_amount = (Integer)request.getAttribute("customer_amount");

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>ownerDetail</title>
<%@ include file="../inc/header.jsp"%>
<script>
	var item_len = <%=itemList.size()%>;
	$(function(){
		//getAsyncList(1);
	});
	function getItemDetail(item_id) {
		$.ajax({
			url: "/admin/owner/detail/item/detail",
			type: "GET",
			data: {
				item_id: item_id 
			},
			success: function(responseData) {
				var itemJson= JSON.parse(responseData);
				console.log(itemJson);
				$("#show-box").html("");

				var tag = "";

				tag += "<div class=\"outerbox\">";
				tag += "<label>상품 이름</label>";
				tag += "<div class=\"box\">" + itemJson.item_name + "</div>";
				tag += "</div>";

				tag += "<div class=\"outerbox\">";
				tag += "<label>카테고리</label>";
				tag += "<div class=\"box\">" + itemJson.category_name + "</div>";
				tag += "</div>";

				tag += "<div class=\"outerbox\">";
				tag += "<label>남은 수량</label>";
				tag += "<div class=\"box\">" + itemJson.stock + "</div>";
				tag += "</div>";

				$("#show-box").html(tag);
			}
		});
	}
	
	function getParameterByName(name) {
	    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
	        results = regex.exec(location.search);
	    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
	}
	
	function getAsyncList(cPage){
		
		$.ajax({
			url:"/admin/owner/detail/item/asyncList",
			type:"get",
			data:{
				owner_id: getParameterByName("owner_id")
			},
			success:function(itemArray){
				alert(itemArray);
				item_len = itemArray.length;
				var pager = getPager(cPage, item_len);
				var tag="";
				for(var i=0; i<pager.pageSize;i++){
					if(pager.num < 1) break;
					var item = itemArray[pager.curPos++];
					tag += "<tr>";
					tag += "<td>" + (pager.num--) + "</td>";
					tag += "<td>" + item.item_name + "</td>";
					tag += "<td><p>" + item.category.category_name + "</p></td>";
					tag += "<td>" + item.stock + "</td>";
					tag += "<td>" + item.regdate + "</td>";
					tag += "</tr>";
				}

				$("#item-list").html(tag);
				
				tag = "";
				tag += "<tr>";
				tag += "<td style=\"text-align:center\">";
				tag += "<a href=\"#\">◀</a>";

				for(var i=pager.firstPage; i<=pager.lastPage;i++){
					if(i>pager.totalPage) break;
					tag += "<a href=\"javascript:getAsyncList("+i+")\"> ["+i+"] </a>"
				}
				
				tag += "<a href=\"#\">▶</a>";
				
				tag += "</td>";
				tag += "</tr>";
				
				$(".page-box").html(tag);
			}
		})
	}
		
	function getPager(cPage, size){
		var result;
		$.ajax({
			url: "/getPager",
			dataType: "json",
			async: false,
			type: "post",
			data:{
				curPage: cPage,
				listSize: size
			},
			success:function(pager){
				result = pager;
			}
			
		})
		return result;
	}

	
</script>
</head>
<%@ include file="../inc/common.jsp"%>

<div class="bigcontainer">
	<div class="container" id="show-box">
		<div class="outerbox">
			<label>등록된 상품 수</label>
			<div class="box"><%=itemList.size() %> 개</div>
		</div>
		<div class="outerbox">
			<label>등록된 카테고리 수</label>
			<div class="box"><%=categoryList.size() %> 개</div>
		</div>
		<div class="outerbox">
			<label>등록된 고객 수</label>
			<div class="box"><%=customer_amount %> 명</div>
		</div>
	</div>

	<div id="item-list" class="container" style="left: 800px;">
		<div class="bigbox">
			<div class="smallbox">
				<form>
					<table style="width: 100%">
						<tr>
							<th>No</th>
							<th>상품명</th>
							<th>카테고리</th>
							<th>수량</th>
							<th>등록일자</th>
						</tr>
						<% for (int i = 0; i < itemList.size(); i++) { %>
						<% Item item = itemList.get(i); %>
						<tr>
							<td>No</td>
							<td><a href="javascript:getItemDetail(<%=item.getItem_id() %>) "><%=item.getItem_name()%></a></td>
							<td>
							<%if(item.getCategory() != null) { %>
								<%=item.getCategory().getCategory_name() %>
							<%} else { %>
								카테고리 없음
							<%} %>
							</td>
							<td><%=item.getStock() %></td>
							<td><%=item.getRegdate().substring(0, 10) %></td>
						</tr>
						<% } %>
						
					</table>
				</form>
				<button type="button" onClick="getAsyncList(1)"></button>
			</div>
		</div>
	</div>
</div>

<%@ include file="../inc/footer.jsp"%>