<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>owner page</title>
<%@ include file="../inc/header.jsp"%>

<style>
#user_name, #shopname {
	width: 70%;
}

#email_id {
	width: 40%;
}

#email_server {
	width: 40%;
}


</style>
<script>
	function showCheckBox() {
		$(".check-box").show();

	}

	function hideCheckBox() {
		$(".check-box:input[type='password']").html("");
		$(".check-box").hide();
	}

	function hideNewBox() {
		$(".new-box").hide();
		$(".new-box:input[type='password']").html("");
	}

	function checkPassword() {
		var formData = $("#check-box-form").serialize();
		$.ajax({
			url : "/rest/main/checkPassword",
			type : "post",
			data : formData,
			success : function(responseData) {
				alert(responseData.msg)
				$(".check-box:input[type='password']").html("");
				$(".check-box").hide();
				if (responseData.resultCode == 1) {
					$(".new-box").show();
				}
			}
		});
	}

	function changePassword() {
		var formData = $("#new-box-form").serialize();
		$.ajax({
			url : "/rest/main/ownerPasswordUpdate",
			type : "post",
			data : formData,
			success : function(responseData) {
				alert(responseData.msg)
				$(".new-box").hide();
				$(".new-box:input[type='password']").html("");
			}
		});
	}

	function deleteAccount() {
		var obj = {};
		
		obj["owner_id"]=$("#owner_id").val();
		obj["user_id"]=$("#user_id").val();
		obj["user_name"]=$("#user_name").val();
		obj["shopname"]=$("#shopname").val();
		obj["email_id"]=$("#email_id").val();
		obj["email_server"]=$("#email_server").val();
		obj["regdate"]=$("#regdate").val();

		var jsonString = JSON.stringify(obj);

		if($("#user_id").val() == "sampleID"){
			alert("sampleID는 삭제할 수 없습니다.");
		}else if (confirm("회원탈퇴 하시겠습니까?")) {
			$.ajax({
				url : "/rest/main/owner",
				type:"delete",
				data : jsonString,
				contentType:"application/json;charset=utf-8",
				success : function(responseData) {
					alert(responseData.msg)
					location.href = responseData.url;
				}
			});
		}
	}

	function updateInfo() {

		var obj = {};
		
		obj["owner_id"]=$("#owner_id").val();
		obj["user_id"]=$("#user_id").val();
		obj["user_name"]=$("#user_name").val();
		obj["shopname"]=$("#shopname").val();
		obj["email_id"]=$("#email_id").val();
		obj["email_server"]=$("#email_server").val();
		obj["regdate"]=$("#regdate").val();

		var jsonString = JSON.stringify(obj);
		
		$.ajax({
			url : "/rest/main/owner",
			type : "put",
			data : jsonString,
			contentType:"application/json;charset=utf-8",
			success : function(responseData) {
				alert(responseData.msg)
				location.href = responseData.url;

			}
		});
	}
</script>

</head>
<%@ include file="../inc/common.jsp"%>
<div class="container">
	<form id="mypage-form">
		<input type="hidden" id="owner_id" value="<%=owner.getOwner_id()%>" />
		<input type="hidden" id="user_id" value="<%=owner.getUser_id()%>" />
		<input type="hidden" id="regdate" value="<%=owner.getRegdate()%>" />
		<div class="outerbox">
			<label>상호명</label>
			<div class="box">
				<input type="text" id="shopname"
					value="<%=owner.getShopname()%>" placeholder="Your shop name..">
			</div>
		</div>
		<div class="outerbox">
			<label>사용자 이름</label>
			<div class="box">
				<input type="text" id="user_name"
					value="<%=owner.getUser_name()%>" placeholder="Your name..">
			</div>
		</div>
		<div class="outerbox">
			<label>ID</label>
			<div class="box">
				<div style="color: black; width: 60%;"><%=owner.getUser_id()%></div>
			</div>
		</div>
		<div class="outerbox">
			<label>이메일</label>
			<div class="box">
				<input type="text" width="40%" id="email_id" value="<%=owner.getEmail_id()%>" required> @ 
				<input type="text" width="40%" id="email_server" value="<%=owner.getEmail_server()%>" required>
			</div>
		</div>
		<div class="passwordbox">
			<label>비밀번호</label><br>
			<div class="box">
				<div style="color: black">********</div>
				<input type="button" class="change_btn" onclick="showCheckBox()" value="변경">
			</div>
		</div>
	</form>
	<form id="check-box-form">
		<div class="check-box" style="display: none;">
			<input type="password" name="password" placeholder="비밀번호를 입력하세요">
			<input type="hidden" name="user_id" value="<%=owner.getUser_id()%>">
			<input type="button" style="margin-right: 60px" onClick="hideCheckBox()" value="취소">
			<input type="button" class="check_btn" onClick="checkPassword()" value="확인">
		</div>
	</form>
	<form id="new-box-form">
		<div class="new-box" style="display: none;">
			<input type="password" name="password" placeholder="새로운 비밀번호를 입력하세요">
			<input type="hidden" name="owner_id" value="<%=owner.getOwner_id()%>">
			<input type="button" style="margin-right: 60px" onClick="hideNewBox()" value="취소">
			<input type="button" class="check_btn" onClick="changePassword()" value="확인"> 
		</div>
	</form>
	<div class="outerbox" style="padding-top: 30px">
		<label for="lname">계정삭제</label><br>
		<div class="box">
			<div style="color:black;">
				계정과 관련된 모든 데이터를 영구적으로 <br>삭제할 수 있습니다.
			</div>
			<input type="button" onclick="deleteAccount()" value="삭제"><br>
		</div>
	</div>
	<div>
		<div class="buttonbox">
			<input type="button" class="save_btn" onClick="updateInfo()" value="저장">
			<input type="reset" value="reset"> 
		</div>
	</div>
</div>
</div>
<%@ include file="../inc/footer.jsp"%>
</div>
</body>
</html>