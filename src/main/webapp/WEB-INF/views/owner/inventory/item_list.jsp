<%@page import="com.tsycsm.agileoffice.model.domain.Category"%>
<%@page import="com.tsycsm.agileoffice.model.common.Pager"%>
<%@page import="com.tsycsm.agileoffice.model.domain.Item"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	List<Category> categoryList = (List<Category>)request.getAttribute("categoryList");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>owner page</title>
<%@ include file="../inc/header.jsp" %>
<style>
.add_btn {
	border: none;
	border-radius: 4px;
	background-color: #3d4c9c;
	color: white;
	font-size: 15px;
	padding: 10px 15px;
}
.add_btn:hover {
	transition: 0.3s;
	opacity: 0.7;
}
#category-checkbox {
	width: 100%;
	text-align: center;
}
</style>
</head>
<%@ include file="../inc/common.jsp"%>
<table id="item-list">
	<tr>
		<td colspan="5">
			<div id="category-checkbox">
			<input name="categoryIdList" onChange="filtering(1)" type="checkbox" value="0" checked/>카테고리 없음
			<%for(int i=0; i<categoryList.size(); i++) { %>
				<%Category category = categoryList.get(i); %>
				<input name="categoryIdList" onChange="filtering(1)" type="checkbox" value="<%=category.getCategory_id() %>" checked/><%=category.getCategory_name() %>
			<%} %>
			</div>
		</td>
	</tr>
	<tr id="list-title">
		<th>No</th>
		<th>품명</th>
		<th>가격</th>
		<th>재고</th>
		<th>등록일</th>
	</tr>
	<tbody id="list-contents"></tbody>
	<tbody id="page-box" style="text-align:center"></tbody>
	<tbody style="background-color:#f0f0f0;">
		<tr style=" border:none;">
			<td colspan="5">
				<button class="add_btn" type="button" onclick="location.href='/client/owner/inventory/item/dump/registform'">상품 엑셀로 추가</button>
				<button class="add_btn" type="button" onclick="location.href='/client/owner/inventory/item/registform'">상품 추가</button>
			</td>
		</tr>
	</tbody>
</table>





<%@ include file="../inc/footer.jsp" %>

<script>

	const formatter = new Intl.NumberFormat('ko-KR', {
	  style: 'currency',
	  currency: 'KRW'
	})

	var categoryArray = [];
	
	$(function() {
		setTimeout(function() {
			categoryArray = getCheckedCategory();
			filtering(1);
		}, 100);
	});
	
	function getCheckedCategory() {
		categoryArray = [];

		for(var i=0; i<$("input[name='categoryIdList']").length; i++) {
			if($($("input[name='categoryIdList']")[i]).is(":checked") == true) {
				categoryArray.push($($("input[name='categoryIdList']"))[i].value);
			} 
		}
		
		return categoryArray;
	}

	function filtering(curPage) {
		$("#list-contents").html("");
		categoryArray = getCheckedCategory();

		$.ajax({
			method: "POST",
			url: "/client/owner/inventory/item/list/filtered",
			traditional: "true",
			data: {
				categoryArray : categoryArray,
				owner_id : <%=owner.getOwner_id() %>
			},
			success: function(responseData) {
				var itemList_length = responseData.length;
				var pager = getPager(curPage, itemList_length);
				var tag = "";
				for(var i=0; i<pager.pageSize; i++) {
					if(pager.num < 1) break;
					var item = responseData[pager.curPos++];
					tag += "<tr>";
					tag += "<td>" + (pager.num--) + "</td>";
					tag += "<td><a href='/client/owner/inventory/item/detail?item_id=" + item.item_id + "'>" + item.item_name + "</a></td>";
					tag += "<td>" + formatter.format(item.price) + "</td>";
					tag += "<td>" + item.stock + "</td>";
					tag += "<td>" + item.regdate.substring(0,10) + "</td>";
					tag += "</tr>";
				}
				$("#list-contents").append(tag);
				
				tag = "";
				tag += "<tr>";
				tag += "<td colspan=\"5\" style=\"text-align:center\">";
				tag += "<a href=\"#\">◀</a>";
				for(var i=pager.firstPage; i<=pager.lastPage;i++){
					if(i>pager.totalPage) break;
					tag += "<a href=\"javascript:filtering("+i+")\"> ["+i+"] </a>"
				}
				tag += "<a href=\"#\">▶</a>";
				tag += "</td>";
				tag += "</tr>";
				
				$("#page-box").html(tag);
			}
		});
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





