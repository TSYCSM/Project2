<%@page import="com.tsycsm.agileoffice.model.domain.Review"%>
<%@page import="java.util.List"%>
<%@page import="com.tsycsm.agileoffice.model.domain.Customer"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	Customer customer = (Customer)session.getAttribute("customer");
	List<Item> itemList =(List)request.getAttribute("itemList");
	List<Review> reviewList =(List)request.getAttribute("reviewList");
	Owner owner = (Owner)session.getAttribute("owner");
	int index =0;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Items</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
@font-face {
	font-family: 'Jal_Onuel';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-10-21@1.0/Jal_Onuel.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}
* {
	font-family: 'Jal_Onuel';
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}
body {
	background-color: #F2F2F2;
}

a {
	text-decoration: none;
	color: black;
}
a:hover {
	text-decoration: underline;
	color: black;
}

#tabs {
	position: fixed;
	float: left;
	width: 10%;
	height: 100%;
}
#tabs button {
	opacity: 0.7;
	display: block;
	background-color: black;
	font-size: 19px;
	color: white;
	width: 80px;
	height: 50%;
	border: none;
	text-align: center;
	transition: 0.3s;
	border: none;
}
#tabs button:hover {
	opacity: 1;
}
#tabs button.active {
	opacity: 1;
}
.tabcontent {
	position: fixed;
	left: 10%;
	float: left;
	width: 90%;
	height: 100%;
}

h1, h3{
	padding: 30px;
}

<%@ include file="/resources/css/customer/items.css" %>
<%@ include file="/resources/css/customer/reviews.css" %>
</style>
<script type="text/javascript">
	var review_len = <%=reviewList.size()%>;
	
	
	
	$(function(){
		getAsyncList(1);
	});


	<%@ include file="/resources/js/customer/items.js" %>
	<%-- <%@ include file="/resources/js/client/customer/reviews.js" %> --%>
	//----reviews.js에서 encoding 문제가 있어 임시로 reviews.js의 내용을 붙여넣는다
	function modeChange(param) {

		var contentTd = param.parentNode.parentNode.childNodes[2];
		var contentTagName = contentTd.childNodes[0].tagName;
		var buttonTd = param.parentNode.parentNode.childNodes[4];
		
		if(contentTagName == "P") {  //보기 모드일 때, 수정 버튼을 누르면...
			var contentText = contentTd.childNodes[0].innerText;
			contentTd.innerHTML = "<textArea style=\"width:100%;font-size:16px\">" + contentText + "</textArea>";
			buttonTd.childNodes[0].style.display = "none";
			buttonTd.childNodes[1].style.display = "block";
		} else if(contentTagName == "TEXTAREA") {  //수정 모드일 때, 확인 버튼을 누르면...
			var contentText = contentTd.childNodes[0].value;
			contentTd.innerHTML = "<p>" + contentText + "</p>";
			buttonTd.childNodes[0].style.display = "block";
			buttonTd.childNodes[1].style.display = "none";
			
			var review_id = buttonTd.childNodes[2].value;
			var comments = contentTd.childNodes[0].innerText;
			
			console.log(review_id);
			console.log(comments);
			
			updateReview(review_id, comments);
			
			getAsyncList(1);
		}
	}	
	
	
	function updateReview(review_id, comments) {
		$.ajax({
			url: "/client/review/update",
			data: {
				review_id: review_id,
				comments: comments
			},
			type: "POST",
			success: function(messageData) {
				if(messageData.resultCode == 1) {
					alert(messageData.msg);
					getAsyncList(1);
				} else {
					alert(messageData.msg);
				}
			}
			
		});
	}
	
	function deleteReview(param) {
		var review_id = param.getElementsByTagName("input")[0].value;
		
		$.ajax({
			url: "/client/review/delete",
			data: {review_id: review_id},
			type: "GET",
			success: function(messageData) {
				if(messageData.resultCode == 1) {
					alert(messageData.msg);
					getAsyncList(1);
				} else {
					alert(messageData.msg);
				}
			}
		});
	}
	
	function getAsyncList(cPage){
		
		$.ajax({
			url:"/client/review/asyncList",
			type:"post",
			data:{
				owner_id: <%=owner.getOwner_id()%>,
				currentPage:cPage
			},
			success:function(pager){
				//review_len =reviewArray.length;
				//var pager = getPager(cPage, review_len);
				var tag="";
				var current_customer_id= 0;
				
				<%if(customer != null){%>
					current_customer_id = <%=customer.getCustomer_id()%>
				<%}%>
				
				for(var i=0; i<pager.pageSize;i++){
					if(pager.num < 1) break;
					reviewList = pager.list; 
					var review = reviewList[pager.curPos++];
					var item = review.item;
					tag += "<tr>";
					tag += "<td>"+(pager.num--)+"</td>";
					tag += "<td>"+item.item_name+"</td>";
					tag += "<td><p>"+review.comments+"</p></td>";
					tag += "<td>"+review.regdate+"</td>";
					tag += "<td>";
					
					if(current_customer_id == review.customer_id){
						tag += "<button type='button' onclick='modeChange(this)'>수정</button>";						
					}
					
					tag += "<button type='button' onclick='modeChange(this)' style='display:none'>확인</button>";
					tag += "<input type='hidden' value='" + review.review_id + "'/>";
					tag += "</td>";
					tag += "<td>";
					
					if(current_customer_id == review.customer_id){
						tag += "<button type='button' onclick='deleteReview(this)'>삭제<input type='hidden' value='"+review.review_id+"'/></button>"
					}
					
					tag += "</td>"
					tag += "</tr>";
								   
				}
				$(".review-box").html(tag);
				
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
		
	function order(){
		if(confirm("주문하시겠습니까")){
			
			var formData = $(".order-form").serialize()
			$.ajax({
				url: "/client/order/orderRegist",
				method: "post",
				data: formData,
				success : function(responseData){
					alert(responseData.msg);
					if(responseData.resultCode==1){
						location.href= responseData.url;					
					}
				}
			})
		}
	}
	
	function identifyCustomer(){
		$.ajax({
			url: "/client/review/identifyCustomer",
			type: "get",
			success:function(responseData){
				if(responseData.resultCode==1){
					showRegist();
				}else{
					alert(responseData.msg);
				}
			}
		
		});
	}

	function showRegist(){
		$(".regist-tr").show();
	}
	
	function hideRegist(){
		$(".regist-tr").hide();
		$(".comments").val("");
	}

	function registReview(){
		var formData = $(".review-form").serialize();
		if($("select[name='item.item_id']").val() !=0){
			$.ajax({
				url:"/client/review/regist",
				type:"post",
				data:formData,
				success:function(responseData){
					alert(responseData.msg);
					if(responseData.resultCode==1){
						getAsyncList(1);
						hideRegist();
					}
				}
			})			
		}else{
			alert("상품을 선택하세요");
		}
	}
	
	
	
	//----Tab 전환 function----
	function openTab(evt, tabName) {
		var i, tabcontent, tab;
		tabcontent = document.getElementsByClassName("tabcontent");
		for (i = 0; i < tabcontent.length; i++) {
			tabcontent[i].style.display = "none";
		}
		tab = document.getElementsByClassName("tab");
		for (i = 0; i < tab.length; i++) {
			tab[i].className = tab[i].className.replace(" active", "");
		}
		document.getElementById(tabName).style.display = "block";
		evt.currentTarget.className += " active";
	}

	window.addEventListener("load", function() {
		openTab(event, 'items'); //initial tab울 items로 설정
		document.getElementsByClassName("tab")[0].className += " active"; //inital tab을 시각적으로 items로 설정
	});
		
</script>
<body>
	<div id="tabs">
		<button class="tab" onclick="openTab(event, 'items')">상품</button>
		<button class="tab" onclick="openTab(event, 'reviews')">리뷰</button>
	</div>
	<%@ include file="./items.jsp"%>
	<%@ include file="./reviews.jsp"%>
</body>
</html>