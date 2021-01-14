<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>owner page</title>
<%@ include file="../inc/header.jsp" %>

<style>
input[name='user_name'], input[name='shopname']{
	width:70%;
}

input[name='email_id']{
	width:20%;
}

input[name='email_server']{
	width:40%;
}


</style>
<script>

function showCheckBox() {
	$(".check-box").show();

}

function hideCheckBox(){
	$(".check-box:input[type='password']").html("");
	$(".check-box").hide();
}

function hideNewBox(){
	$(".new-box").hide();
	$(".new-box:input[type='password']").html("");
}

function checkPassword(){
	var formData = $("#check-box-form").serialize();
	$.ajax({
		url: "/client/main/checkPassword",
		type: "post",
		data: formData,
		success:function(responseData){
			alert(responseData.msg)
			$(".check-box:input[type='password']").html("");
			$(".check-box").hide();
			if(responseData.resultCode==1){
				$(".new-box").show();				
			}
		}
	});
}

function changePassword(){
	var formData = $("#new-box-form").serialize();
	$.ajax({
		url: "/client/main/ownerPasswordUpdate",
		type: "post",
		data: formData,
		success:function(responseData){
			alert(responseData.msg)
			$(".new-box").hide();
			$(".new-box:input[type='password']").html("");
		}
	});
}

function deleteAccount(){
	if(confirm("회원탈퇴 하시겠습니까?")){
		var formData = $("#mypage-form").serialize();
		$.ajax({
			url:"/client/main/ownerQuit",
			type:"post",
			data:formData,
			success:function(responseData){
				alert(responseData.msg)
				location.href=responseData.url;
			}
		});
	}
	
}

function updateInfo(){
	var formData = $("#mypage-form").serialize();
	$.ajax({
		url:"/client/main/ownerUpdate",
		type:"post",
		data:formData,
		success:function(responseData){
			alert(responseData.msg)
			location.href=responseData.url;
			
		}
	});
}
</script>

</head>
<%@ include file="../inc/common.jsp"%>
			<div class="container">
				<form id="mypage-form">
					<input type="hidden" name="owner_id" value="<%=owner.getOwner_id()%>"/>
					<input type="hidden" name="user_id" value="<%=owner.getUser_id()%>"/>
					<input type="hidden" name="regdate" value="<%=owner.getRegdate()%>"/>
					
					<div class="outerbox">
						<label for="fname">상호명</label>
						<div class="box">
							<input type="text" id="fname" name="shopname"  value="<%=owner.getShopname() %>" placeholder="Your shop name..">
						</div>					
					</div>
					<div class="outerbox">
						<label for="fname">사용자 이름</label>
						<div class="box">
							<input type="text" id="fname" name="user_name"  value="<%=owner.getUser_name() %>" placeholder="Your name..">
						</div>					
					</div>
					<div class="outerbox">
						<label for="lname">id</label>
						<div class="box">
							<div style="width:60%;"><%=owner.getUser_id() %></div>
						</div>
					</div>
					<div class="outerbox">
						<label for="lname">이메일</label>
						<div class="box">
							<input type="text" width="20%" name="email_id"  value="<%=owner.getEmail_id() %>" required>@
							<input type="text" width="40%" name="email_server"  value="<%=owner.getEmail_server() %>" required>
						</div>
					</div>
					<div class="passwordbox" >
						<label for="lname">비밀번호</label><br>
						<div class="box">
							<div>*******</div>
							<input type="button" class="change_btn" onclick="showCheckBox()" value="변경"><br>
						</div>
					</div>
					</form>
					<form id="check-box-form">
						<div class="check-box" style ="display:none;">
							<input type="password" name="password" placeholder="비밀번호를 입력하세요">
							<input type="hidden" name="user_id" value="<%=owner.getUser_id()%>">
							<input type="button" onClick="checkPassword()" value="확인">
							<input type="button" onClick="hideCheckBox()" value="취소">
						</div>
					</form>
					<form id="new-box-form">
						<div class="new-box" style ="display:none;">
								<input type="password" name="password" placeholder="새로운 비밀번호를 입력하세요">
								<input type="hidden" name="owner_id" value="<%=owner.getOwner_id()%>">
								<input type="button" onClick="changePassword()" value="확인">
								<input type="button" onClick="hideNewBox()" value="취소">
						</div>
					</form>		
					<div class="outerbox" style ="padding-top:30px">
						<label for="lname">계정삭제</label><br>
						<div class="box">
							<div>계정과 관련된 모든 데이터를 영구적으로 <br>삭제할 수 있습니다.</div>
							<input type="button" onclick="deleteAccount()" value="삭제"><br>
						</div>
					</div>					
					<div>
						<div class="buttonbox">
							<input type="reset" value="reset">
							<input type="button" class="save_btn" onClick="updateInfo()" value="저장">
						</div>
					</div>
			</div>
		</div>
		<%@ include file="../inc/footer.jsp"%>
	</div>
</body>
</html>