<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>owner page</title>
<%@ include file="../inc/header.jsp" %>
<script>
function changeInfo() {
	var top = window.screen.height;
    top = top > 0 ? top/2 : 0;
            
	var left = window.screen.width;
	    left = left > 0 ? left/2 : 0;
	    
	window.open("checkpassword.jsp", "","width=350 height=100 left="+left+" top="+top);
	
}
function checkIdFormClose(sId) {
	opener.joinForm.user_id.value = sId;
	window.close();
}

</script>
</head>
<%@ include file="../inc/common.jsp"%>
			여기는 mypage 메인
			<div class="container">
				<form>
					<div class="outerbox">
						<label for="fname">상호명</label>
						<div class="box">
							<input type="text" id="fname" name="shopname" placeholder="Your name..">
						</div>					
					</div>
					<div class="outerbox">
						<label for="lname">id</label>
						<div class="box">
							<div style="width:60%;">아이디 들어올 곳</div>
						</div>
					</div>
					<div class="outerbox">
						<label for="lname">이메일</label>
						<div class="box">
							<div style="width:60%;">fge503@naver.com</div>
							<button type="button" onclick="changeInfo()">변경</button>
						</div>
					</div>
					<div class="outerbox">
						<label for="lname">비밀번호</label><br>
						<div class="box">
							<div>*******</div>
							<button type="button" onclick="changeInfo()">변경</button><br>
						</div>
					</div>
										
					<div class="outerbox">
						<label for="lname">계정삭제</label><br>
						<div class="box">
							<div>계정과 관련된 모든 데이터를 영구적으로 <br>삭제할 수 있습니다.</div>
							<button type="button" onclick="changeInfo()">삭제</button><br>
						</div>
					</div>					
					<div>
						<div class="buttonbox">
							<input type="reset" value="취소">
							<input type="submit" value="저장">
						</div>
					</div>
				</form>
			</div>
		</div>
		<%@ include file="../inc/footer.jsp"%>
	</div>
</body>
</html>