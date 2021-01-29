<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>admin page</title>
<style type="text/css">
	body {
		background-color: #3b3b3b;
		text-align: center;
		
	}
	
	h1 {
		color: white;
		margin-top: 200px;
	}
	
	input {
		padding: 10px;
	}
	
	button {
		background-color: #3b3b3b;
		color: white;
		font-weight: bold;
		padding: 10px;
		margin: 10px;
		border: 1px solid white;
	}
	
	button:hover { 
		transition: 0.3s;
		background-color: white;
		color: #3b3b3b;
	}
	
	
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	function login(){
		$("form").attr({
			method: "post",
			action: "/admin/login"
		});
		$("form").submit();
	}
</script>
</head>
<body>
	<form>
		<h1>Admin</h1>
		<input type="text" name="id" placeholder="아이디"/>
		<br>
		<input type="password" name="password" placeholder="비밀번호"/>
		<br>
		<button type="button" onClick="login()">로그인</button>
	</form>
</body>
</html>