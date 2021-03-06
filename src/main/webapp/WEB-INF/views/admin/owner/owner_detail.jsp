<%@page import="com.tsycsm.agileoffice.model.domain.Category"%>
<%@page import="com.tsycsm.agileoffice.model.domain.Item"%>
<%@page import="java.util.List"%>
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
<style type="text/css">
tr {
	border-top: 1px solid #ddd;
}
</style>
<script>
	var item_len = <%=itemList.size()%>;
	$(function(){
		getAsyncList(1);
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
				$("#show-box").html("");

				var tag = "";

				tag += "<div class=\"outerbox\">";
				tag += "<label>상품 이름</label>";
				tag += "<div class=\"box\">" + itemJson.item_name + "</div>";
				tag += "</div>";

				tag += "<div class=\"outerbox\">";
				tag += "<label>범주</label>";
				tag += "<div class=\"box\">" + itemJson.category_name + "</div>";
				tag += "</div>";

				tag += "<div class=\"outerbox\">";
				tag += "<label>남은 수량</label>";
				tag += "<div class=\"box\">" + itemJson.stock + "</div>";
				tag += "</div>";

				tag += "<div class=\"outerbox\">";
				tag += "<div class=\"box\">";
				tag += "<img width=\"80%\" src='/resources/data/" + itemJson.item_id + "." + itemJson.filename + "'/>"
				tag += "</div>";
				tag += "</div>";

				tag += "<div class=\"outerbox\">";
				tag += "등록 : <div style=\"display:inline-block\" class=\"box\">" + itemJson.regdate + "</div>";
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
				item_len = itemArray.length;
				var pager = getPager(cPage, item_len);
				var tag="";
				tag += "<tr>";
				tag += "<th>No</th>";
				tag += "<th>상품명</th>";
				tag += "<th>범주</th>";
				tag += "<th>재고</th>";
				tag += "<th>등록일</th>";
				tag += "</tr>"

				for(var i=0; i<pager.pageSize; i++){
					if(pager.num < 1) break;
					var item = itemArray[pager.curPos++];
					tag += "<tr>";
					tag += "<td>" + (pager.num--) + "</td>";
					tag += "<td><a href='javascript:getItemDetail(" + item.item_id + ")'>" + item.item_name + "</a></td>";
					tag += "<td>" + item.category.category_name + "</td>";
					tag += "<td>" + item.stock + "</td>";
					tag += "<td>" + item.regdate.substring(0, 10) + "</td>";
					tag += "</tr>";
				}
				tag += "<tr>";
				tag += "<td colspan='5' style=\"text-align:center\">";
				tag += "<a href=\"javascript:getAsyncList(" + (parseInt(pager.firstPage)-1) + ")\">◀</a>";
				for(var i=pager.firstPage; i<=pager.lastPage;i++){
					if(i>pager.totalPage) break;
					tag += "<a href=\"javascript:getAsyncList(" + i + ")\"> [" + i + "] </a>"
				}
				tag += "<a href=\"javascript:getAsyncList(" + (parseInt(pager.lastPage)+1) + ")\">▶</a>";
				tag += "</td>";
				tag += "</tr>";
				$("#item-list").html(tag);
			}
		})
	}
		
	function getPager(cPage, size){
		var result;
		$.ajax({
			url: "/client/getPager",
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

	<table id="item-list" class="container" style="background: white; left: 800px;">
			
	</table>
</div>

<%@ include file="../inc/footer.jsp"%>