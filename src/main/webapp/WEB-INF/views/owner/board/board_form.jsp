<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>owner page</title>
<%@ include file="../inc/header.jsp"%>
<style>

</style>
<script>
	function regist() {
		$("form").attr({
			action:"/client/owner/board/regist",
			method:"post"
		});
		$("form").submit();
	}
	
</script>
</head>
<%@ include file="../inc/common.jsp"%>
<div class="container">
	<form>
		<input type="hidden" name="owner_id" value="<%=owner.getOwner_id()%>"/>
		<input type="hidden" name="writer" value="<%=owner.getShopname()%>"/>
		<div class="outerbox">
			<label for="title">제목</label>
			<div id="title" class="box">
				<input type="text" name="title" placeholder="제목을 입력하세요">
			</div>
		</div>
		<div class="outerbox">
			<label for="content">내용</label>
			<div id="content" class="box">
				<textarea rows="20" cols="60" id="content" name="content" placeholder="내용을 입력하세요"></textarea>
			</div>
		</div>
		<div>
			<div class="buttonbox">
				<input onClick="regist()" type="button" value="추가">
			</div>
		</div>
	</form>
</div>
<%@ include file="../inc/footer.jsp"%>