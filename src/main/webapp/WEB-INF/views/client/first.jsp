<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	margin: 0;
}

body {
	text-align: center;
}

input[type=text] {
	font-size: 16px;
	border-radius: 5px;
	padding: 10px;
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

#customer {
	z-index: 1;
	position: fixed;
	left: 0;
	width: 50%;
	height: 100%;
	background-color: #F2F2F2;
	color: #2E2E2E;
}

#owner {
	position: fixed;
	left: 50%;
	width: 50%;
	height: 100%;
	background-color: #2E2E2E;
	color: #F2F2F2;
}

div.mask {
	z-index: 2;
	position: fixed;
	width: 50%;
	height: 100%;
	background-size: 100% 100%;
	padding-top: 200px;
	font-size: 50px;
}

div.mask:hover {
	transition: 0.7s;
	opacity: 0.0;
}

#customer_mask {
	left: 0;
	background-image: url(/resources/image/client/customer.jpg);
	color: #2E2E2E;
	font-weight: bold;
}

#owner_mask {
	left: 50%;
	background-image: url(/resources/image/client/owner.jpg);
	color: #F2F2F2;
}

div.loginArea {
	margin: auto;
	margin-top: 5%;
	height: 30%;
	width: 80%;
	border-radius: 10px;
}

div.registArea {
	margin: auto;
	margin-top: 5%;
	height: 55%;
	width: 80%;
	border-radius: 10px;
}

div.loginArea>h2, div.registArea>h2 {
	padding: 20px;
	font-size: 24px;
}

#customer>div.loginArea, #customer>div.registArea {
	background-color: #E6E6E6;
}

#owner>div.loginArea, #owner>div.registArea {
	background-color: #373636;
}

#customer button {
	background-color: #373636;
	color: #E6E6E6;
}

#owner button {
	background-color: #E6E6E6;
	color: #373636;
}

#customer input[type=text] {
	background-color: wthie;
	border: 1px solid #373636;
}

#owner input[type=text] {
	background-color: #E6E6E6;
	border: 1px solid white;
}
</style>
<script>
	window.addEventListener("load", function() {
		var flag = null;
		var customer_mask = document.getElementById("customer_mask");
		var owner_mask = document.getElementById("owner_mask");

		customer_mask.addEventListener("click", function() {
			console.log(true);
			flag = true;
			changeMask(flag);
		});
		owner_mask.addEventListener("click", function() {
			console.log(false);
			flag = false;
			changeMask(flag);
		});

		function changeMask(flag) {
			if (flag == true) {
				customer_mask.style.display = "none";
				owner_mask.style.display = "block";
			} else {
				customer_mask.style.display = "block";
				owner_mask.style.display = "none";
			}
		}
	});
</script>
</head>
<body>
	<div class="mask" id="customer_mask">고객님 반갑습니다.</div>
	<div id="customer">
		<div class="loginArea">
			<h2>로그인</h2>
			<form>
				<table>
					<tr>
						<td>전화번호</td>
						<td><input type="text" placeholder="ex) 010-1234-5678">
						</td>
					</tr>
				</table>
				<button type="button">로그인</button>
				<br>
				<button type="button">로그인하지 않고 상품 보러가기</button>
			</form>
		</div>
		<div class="registArea">
			<h2>가입</h2>
			<form>
				<table>
					<tr>
						<td>이름</td>
						<td><input type="text" placeholder="이름 입력..."></td>
					</tr>
					<tr>
						<td>전화번호</td>
						<td><input type="text" placeholder="전화번호 입력..."></td>
					</tr>
					<tr>
						<td colspan="2" align=center>
							<button type="button">가입</button>
						</td>
					</tr>
				</table>
				<hr style="margin: 5% 10%;">
				<p style="font-size: 22px; line-height: 24px;">
					<가입 혜택>
					<br>
					<br>
					- 물건 구매시 적립<br>
					- 리뷰 작성 가능
				</p>
			</form>
		</div>
	</div>

	<div class="mask" id="owner_mask">어서오세요, 사장님.</div>
	<div id="owner">
		<div class="loginArea">
			<h2>로그인</h2>
			<form>
				<table>
					<tr>
						<td>아이디</td>
						<td><input type="text" placeholder="아이디 입력..."></td>
					</tr>
					<tr>
						<td>전화번호</td>
						<td><input type="text" placeholder="비밀번호 입력..."></td>
					</tr>
					<tr>
						<td colspan="2" align=center>
							<button type="button">백오피스 시작</button>
						</td>
					</tr>
				</table>
			</form>
		</div>

		<div class="registArea">
			<h2>가입</h2>
			<form>
				<table>
					<tr>
					<tr>
						<td>이름</td>
						<td><input type="text" placeholder="이름 입력..."></td>
					</tr>
					<tr>
						<td>전화번호</td>
						<td><input type="text" placeholder="전화번호 입력..."></td>
					</tr>
					<tr>
						<td>이메일</td>
						<td><input type="text" placeholder="이름 입력..."></td>
					</tr>
					<tr>
						<td>이름</td>
						<td><input type="text" placeholder="이름 입력..."></td>
					</tr>
					<tr>
						<td>이름</td>
						<td><input type="text" placeholder="이름 입력..."></td>
					</tr>
					<tr>
						<td>이름</td>
						<td><input type="text" placeholder="이름 입력..."></td>
					</tr>
					<tr>
						<td colspan="2" align=center>
							<button type="button">가입</button>
						</td>
					</tr>

				</table>
			</form>
		</div>
	</div>
</body>
</html>