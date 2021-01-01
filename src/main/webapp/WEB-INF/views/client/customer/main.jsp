<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Items</title>
</head>
<style>
@font-face {
    font-family: 'Jal_Onuel';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-10-21@1.0/Jal_Onuel.woff') format('woff');
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
	background-color: #F2F2F2	;
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
	height: 33.33%;
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

<%@ include file="./css/items.css" %>
<%@ include file="./css/services.css" %>
<%@ include file="./css/reviews.css" %>


</style>
<script type="text/javascript">
	<%@ include file="./js/items.js" %>
	<%@ include file="./js/services.js" %>
	<%@ include file="./js/reviews.js" %>

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
		<button class="tab" onclick="openTab(event, 'services')">서비스</button>
		<button class="tab" onclick="openTab(event, 'reviews')">리뷰</button>
	</div>
	
	<%@ include file="items.jsp" %>
	<%@ include file="services.jsp" %>
	<%@ include file="reviews.jsp" %>
	

</body>
</html>