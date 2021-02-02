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
		
		if (confirm("회원탈퇴 하시겠습니까?")) {
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
			<label>Shop name</label>
			<div class="box">
				<input type="text" id="shopname"
					value="<%=owner.getShopname()%>" placeholder="Your shop name..">
			</div>
		</div>
		<div class="outerbox">
			<label>User name</label>
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
			<label>Email</label>
			<div class="box">
				<input type="text" width="40%" id="email_id" value="<%=owner.getEmail_id()%>" required> @ 
				<input type="text" width="40%" id="email_server" value="<%=owner.getEmail_server()%>" required>
			</div>
		</div>
		<div class="passwordbox">
			<label>Password</label><br>
			<div class="box">
				<div style="color: black">********</div>
				<input type="button" class="change_btn" onclick="showCheckBox()" value="Change">
			</div>
		</div>
	</form>
	<form id="check-box-form">
		<div class="check-box" style="display: none;">
			<input type="password" name="password" placeholder="Input your password...">
			<input type="hidden" name="user_id" value="<%=owner.getUser_id()%>">
			<input type="button" style="margin-right: 60px" onClick="hideCheckBox()" value="Cancel">
			<input type="button" class="check_btn" onClick="checkPassword()" value="Done">
		</div>
	</form>
	<form id="new-box-form">
		<div class="new-box" style="display: none;">
			<input type="password" name="password" placeholder="Input your new password">
			<input type="hidden" name="owner_id" value="<%=owner.getOwner_id()%>">
			<input type="button" style="margin-right: 60px" onClick="hideNewBox()" value="Cancel">
			<input type="button" class="check_btn" onClick="changePassword()" value="Save"> 
		</div>
	</form>
	<div class="outerbox" style="padding-top: 30px">
		<label for="lname">Leave Agile Office</label><br>
		<div class="box">
			<div style="color:black;">
				Your information will be all deleted
			</div>
			<input type="button" onclick="deleteAccount()" value="Delete"><br>
		</div>
	</div>
	<div>
		<div class="buttonbox">
			<input type="button" class="save_btn" onClick="updateInfo()" value="Save">
			<input type="reset" value="Reset"> 
		</div>
	</div>
</div>
</div>
<%@ include file="../inc/footer.jsp"%>
</div>
</body>
</html>