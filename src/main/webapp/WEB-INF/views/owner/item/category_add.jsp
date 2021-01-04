<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>owner page</title>
<%@ include file="../inc/header.jsp" %>
<style>
#dragArea{
	width:200px;
	height:200px;
	overflow:scroll;
	border:1px solid #ccc
}
.dragBorder{
	background:#ffffff;
}
</style>
<script>
function regist(){
	$("form").attr({
		method: "post",
		action: "/owner/item/categoryregist"
	});
	$("form").submit();
}
</script>
</head>
<%@ include file="../inc/common.jsp"%>

			category add <br><br>
			<div class="container">
				<form>
					<div class="outerbox">
						<label for="fname">상품이름</label>
						<div class="box">
							<input type="hidden" name="owner_id" value="1"/>
							<input type="text" name="category_name" placeholder="카테고리 이름을 등록하세요">
						</div>					
					</div>
					
					<div class="outerbox">
						<label for="lname">색상 및 모양</label><br>
						<div class="box">
							<p>
								<input type="color" name="color" value="#dedede"/>
								<!-- <input type="color" name="color" value="#ff0808"/>
								<input type="color" name="color" value="#f308ff"/>
								<input type="color" name="color" value="#fc9c00"/>
								<input type="color" name="color" value="#19fc00"/>
								<input type="color" name="color" value="#0093fc"/>
								<input type="color" name="color" value="#000000"/> -->
							</p>
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
		</div>
<%@ include file="../inc/footer.jsp" %>