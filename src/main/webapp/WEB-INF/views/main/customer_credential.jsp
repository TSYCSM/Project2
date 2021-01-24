<%@page import="com.tsycsm.agileoffice.model.domain.Owner"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	Owner owner = (Owner)session.getAttribute("owner");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>customer credential</title>
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
	font-size: 1rem;
	padding: 0;
	margin: 0;
}

body {
	background-color: #f0f0f0;
	text-align: center;
	padding: 50px;
}

input[type=text], input[type=tel] {
	font-size: 16px;
	border-radius: 5px;
	padding: 10px;
	background-color: wthie;
	border: 1px solid #373636;
}

button {
	padding: 10px 15px;
	margin: 10px;
	border: none;
	border-radius: 3px;;
	font-size: 16px;
}

button:hover {
	transition: 0.3s;
	opacity: 0.7;
}

table {
	margin: auto;
}

table td {
	padding: 10px 20px;
}

div.loginArea, div.registArea {
	background-color: white;
	width: 45%;
	height: 500px;
	padding-top: 40px;
	border-radius: 5px;
	border: 2px solid gray;
	overflow: auto;
}

div.loginArea {
	float: left;
}

div.registArea {
	float: right;
}

div.loginArea>h2, div.registArea>h2 {
	padding: 20px;
	font-size: 24px;
}


#customer button {
	background-color: #373636;
	color: #E6E6E6;
}



</style>
<script>
function regist(){
	if($(".signup_form")[0].checkValidity()){
		var formData = $(".signup_form").serialize();
		$.ajax({
			url: "/rest/main/customerRegist",
			type: "POST",
			data: formData,
			success: function(responseData){
				alert(responseData.msg);
			},
			error:function(xhr, status, error){
				console.log("에러 응답데이터 받음: xhr = ",xhr);
				alert(xhr.responseJSON.msg);
			}
		});
	}else{
		alert("잘못된 표기입니다.");
	}
}

function login(){
	$(".login_form").attr({
		action: "/client/main/customerLogin",
		method: "post"
		
	})
	$(".login_form").submit();
	
	
}

	
</script>
</head>
<body>
	<div id="customer">
		<div class="loginArea">
			<h2>로그인</h2>
			<form class="login_form">
				<table>
					<tr>
						<td>전화번호</td>
						<td>
							<input type="tel" name="phone" placeholder="ex) 010-1234-5678">
							<input type="hidden" name="owner_id" value="<%=owner.getOwner_id()%>">
						</td>
						<td>
							<button type="button" onClick="login()">로그인</button>
						</td>
					</tr>
				</table>
				<br>
				<button type="button" onClick="location.href='/client/order/main'">로그인하지 않고 상품 보러가기</button>
			</form>
		</div>
		
		<div class="registArea">
			<h2>가입</h2>
			<form class="signup_form">
				<input type="hidden" name="owner_id" value="<%=owner.getOwner_id()%>">
				<table>
					<tr>
						<td>이름</td>
						<td>
							<input type="text" name="customer_name" placeholder="이름 입력..." min="2" required>
						</td>
					</tr>
					<tr>
						<td>전화번호</td>
						<td>
							<input type="tel" id="phone" name="phone" placeholder="ex) 000-1111-2222" 
								pattern="[0-9]{3}-[0-9]{4}-[0-9]{4}" required>
						</td>
					</tr>
					<tr>
						<td colspan="2" align=center>
							<button type="button" onClick="regist()">가입</button>
						</td>
					</tr>
				</table>
				<hr style="margin: 5% 10%;">
				<p style="font-size: 18px; line-height: 24px;">
					<가입 혜택>
					<br>
					<br>
					- 물건 구매시 적립<br>
					- 리뷰 작성 기능 사용
				</p>
			</form>
		</div>
	</div>

</body>
</html>