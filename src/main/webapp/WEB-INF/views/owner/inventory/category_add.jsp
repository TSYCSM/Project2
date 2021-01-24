<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>owner page</title>
<%@ include file="../inc/header.jsp" %>
<style>

</style>
<script>
function regist(){
	$("form").attr({
		method: "post",
		action: "/client/owner/inventory/category/regist"
	});
	$("form").submit();
}
</script>
</head>

<%@ include file="../inc/common.jsp"%>

<div class="container">
	<form>
		<div class="outerbox">
			<label for="fname">카테고리이름</label>
			<div class="box">
				<input type="hidden" name="owner_id" value="<%=owner.getOwner_id()%>"/>
				<input type="text" name="category_name" placeholder="카테고리 이름 입력" required>
			</div>					
		</div>
		
		<div class="outerbox">
			<label for="lname">색상 및 모양</label><br>
			<div class="box">
				<input type="color" name="color" list="presetColors">
				  <datalist id="presetColors">

				  </datalist>
			</div>
		</div>					
		<div>
			<div class="buttonbox">
				<input type="reset" value="취소">
				<input type="button" value="저장" onClick="regist()">
			</div>
		</div>
	</form>
</div>
<%@ include file="../inc/footer.jsp" %>