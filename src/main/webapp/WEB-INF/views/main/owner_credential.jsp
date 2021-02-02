<%@page import="com.tsycsm.agileoffice.model.domain.Owner"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	Owner owner = (Owner)session.getAttribute("owner");
%>
<!DOCTYPE html>
<html>
<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}


#credentialBox{
	position: absolute;
	width: 500px;
	height: 300px;
	top: 40%;
	left: 50%;
	margin-left: -250px;
	margin-top: -80px;
	border-style: solid;
	border-color: black;
}

/* Full-width input fields */
input[type=text], input[type=password], select {
  width: 100%;
  padding: 15px;
  margin: 5px 0 22px 0;
  display: inline-block;
  border: none;
  background: #f1f1f1;
}

/* Add a background color when the inputs get focus */
input[type=text]:focus, input[type=password]:focus, select:focus{
  background-color: #ddd;
  outline: none;
}

/* Set a style for all buttons */
button {
  background-color: #081f99;
  color: white;
  padding: 14px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  opacity: 0.9;
}

#credentialBox h2{
	text-align: center;
}

#credentialBox button{
	position: absolute;
	margin-left: 170px;
	width: 150px;
}
#credentialBox .open_login_btn{
	top: 90px;
}
#credentialBox .open_signup_btn{
	top: 180px;
}

#credentialBox .welcome_back_btn{
	margin-left : 180px;
	margin-top : 50px;
	width: 200px;
	height: 50px;
}

#credentialBox .welcome_box{
	
	margin-top : 180px;
	text-align: center;
}


button:hover {
  opacity:1;
}

/* Extra styles for the cancel button */
.cancelbtn {
  padding: 14px 20px;
  background-color: #f44336;
}

/* Float cancel and signup buttons and add an equal width */
.cancelbtn, .signupbtn, .loginbtn {
  float: right;
  width: 100px;
}

/* Add padding to container elements */
.container {
  padding: 16px;
}

/* The Modal (background) */
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: #474e5d;
  padding-top: 50px;
}

/* Modal Content/Box */
.modal form {
  background-color: #fefefe;
  margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
  border: 1px solid #888;
  width: 80%; /* Could be more or less, depending on screen size */
}

/* Style the horizontal ruler */
hr {
  border: 1px solid #f1f1f1;
  margin-bottom: 25px;
}
 
/* The Close Button (x) */
.close {
  position: absolute;
  right: 35px;
  top: 15px;
  font-size: 40px;
  font-weight: bold;
  color: #f1f1f1;
}

.close:hover,
.close:focus {
  color: #f44336;
  cursor: pointer;
}

/* Clear floats */
.clearfix::after {
  content: "";
  clear: both;
  display: table;
}

/* Change styles for cancel button and signup button on extra small screens */
@media screen and (max-width: 300px) {
  .cancelbtn, .signupbtn, .loginbtn {
     width: 100%;
  }
}

.loader {
  position: fixed;
  z-index: 2;
  left: 45%;
  border: 16px solid #f3f3f3;
  border-radius: 50%;
  border-top: 16px solid #3498db;
  width: 120px;
  height: 120px;
  -webkit-animation: spin 2s linear infinite; /* Safari */
  animation: spin 2s linear infinite;
}

/* Safari */
@-webkit-keyframes spin {
  0% { -webkit-transform: rotate(0deg); }
  100% { -webkit-transform: rotate(360deg); }
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}


</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(function(){
	
	$(".open_login_btn").removeAttr("style");	
	$(".open_signup_btn").removeAttr("style");
	$(".signupbtn").click(function(){
		var password = $("#password").val();
		var repassword = $("#repassword").val();
		
		if(password == repassword) {
			regist();
		} else {
			alert("Passwords dont match");
		}
	});
	
})

function login(){
	$(".login_form").attr({
		action: "/client/main/ownerLogin",
		method: "POST"
	});
	
	$(".login_form").submit();
}


function regist(){
	//로딩바 시작
	if($(".signup_form")[0].checkValidity()){
		$("#loader").addClass("loader");//class 동적 적용
		$("body").show().css({"opacity":"0.5"});
		
		var obj={}; //define empty json
		obj["user_id"]=$("#user_id").val();
		obj["user_name"]=$("#user_name").val();
		obj["shopname"]=$("#shopname").val();
		obj["password"]=$("#password").val();
		obj["email_id"]=$("#email_id").val();
		obj["email_server"]=$("#email_server").val();
		
		var jsonString = JSON.stringify(obj);
		
		$.ajax({
			url: "/rest/main/owner",
			type: "POST",
			data: jsonString,
			contentType:"application/json;charset=utf-8",
			success: function(responseData){
				console.log(responseData);
				//서버로 부터 완료 응답을 받으면 로딩바 효과를 중단!!
				$("#loader").removeClass("loader");//클래스 동작 제거
				$("body").show().css({"opacity":"1"});
				
				if(responseData.resultCode ==1){
					alert(responseData.msg);
					$("#id01").hide();
				}else{
					alert(responseData.msg);				
				}
			}
		});
		
	}else{
		alert("ID and Password should be longer than 6 characters");
	}
}

function checkId(){
	var id = $("#user_id").val()
	
	if(id.length<6){
		alert("IDshould be longer than 6 characters");
	}else{
		$.ajax({
			url:"/rest/main/owner/"+id,
			type: "POST",
			data: {
				user_id: id
			},
			success: function(responseData){
				if(responseData.resultCode ==1){
					alert("succeed! "+responseData.msg);
					
				}else{
					alert(responseData.msg);				
	
				}
			},
			
			error: function(xhr, status,error){
				console.log(xhr);
				alert(xhr.responseJSON.msg);
				
			}
		});
		
	}
}



</script>
<body>
<div id="credentialBox">
<div id="loader" style="margin:auto"></div>
	<h2>Agile Office</h2>
	<%if(owner !=null){ %>
		<button class="welcome_back_btn" onclick="location.href='/client/main/ownerMain'" style="width:auto;">Welcome Back!</button>	
		<div class="welcome_box"><%=owner.getUser_id() %> is aleady logged in</div>
	<%}else{ %>
		<button class="open_login_btn" onclick="document.getElementById('id02').style.display='block'" style="width:auto;">Sign in</button>
		<button class="open_signup_btn" onclick="document.getElementById('id01').style.display='block'" style="width:auto;">Sign up</button>
	
	<%} %>
</div>

<div id="id01" class="modal">
  <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
  <form class="signup_form">
    <div class="container">
 
      <h1>Sign up</h1>
      <hr>
      
      <label for="ID"><b>ID<span style="margin:20px; font-size: 12px; display:lnline-block"> (6~15 characters)</span></b></label>
      <button type="button" style="background-color:gray;" onclick="checkId()">check ID</button>
      <input type="text" placeholder="Input your Id..." id="user_id" minlength="6" maxlength="15" required>

      <label for="id"><b>Name</b></label>
      <input type="text" id = "user_name" placeholder="Input your name..." required>
      
      <label for="id"><b>Shopname</b></label>
      <input type="text" id= "shopname" placeholder="Input your shopname..." required>

      <label for="psw"><b>Password <span style="margin:20px; font-size: 12px; display:lnline-block">(6~15 characters)</span></b></label>
      <input type="password" placeholder="Input your password..." id="password" minlength="6" maxlength="15" required>

      <label for="repsw"><b>Repassword<span style="margin:20px; font-size: 12px; display:lnline-block">(Make sure your password)</span></b></label>
      <input type="password" placeholder="Input your password agian..." id="repassword" minlength="6" maxlength="15" required>

      <label for="email"><b>Email</b></label><br>
      <input type="text" placeholder="Input your email ID..." id="email_id" style="width: 35%" required>
      <select id="email_server" style="width: 35%">
      		<option value="gmail.com">gmail.com</option>
      		<option value="naver.com">naver.com</option>
      		<option value="daum.net">daum.net</option>
      </select><br>
      
      <div class="clearfix">
        <button type="button" class="signupbtn">Signup</button>
        <button type="button" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">Cancel</button>
      </div>
    </div>
  </form>
</div>

<div id="id02" class="modal">
  <span onclick="document.getElementById('id02').style.display='none'" class="close" title="Close Modal">&times;</span>
  <form class="login_form">
    <div class="container">
      <h1>Sign in</h1>
      <hr>
      <label for="id"><b>ID</b></label>
      <input type="text" name = "user_id" placeholder="Input your ID...">
      

      <label for="psw"><b>Password</b></label>
      <input type="password" placeholder="Input your password..." name="password" required>

      <div class="clearfix">
        <button type="button" class="loginbtn" onClick="login()">Sign in</button>
        <button type="button" onclick="document.getElementById('id02').style.display='none'" class="cancelbtn">Cancel</button>
      </div>
    </div>
  </form>
</div>

<script>
// Get the modal
var modal = document.getElementById('id01');

// When the user clicks anywhere outside of the modal, close it
/* window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
} */
</script>

</body>
</html>
