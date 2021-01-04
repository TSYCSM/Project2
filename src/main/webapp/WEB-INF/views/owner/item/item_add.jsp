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
var uploadFiles=[]; //미리보기 이미지 목록 
$(function(){

	$("#dragArea").on("dragover", function(e){ //드래그영역 위에 있는 동안...
		e.preventDefault();
	});

	$("#dragArea").on("drop", function(e){ //드래그영역 위에서 이미지를 떨구면..
		e.preventDefault(); //여타 다른 이벤트를 비활성화시키자...
		$(this).append("drop<br>");
		
		var fileList = e.originalEvent.dataTransfer.files;
		
		for(var i=0; i<fileList.length; i++){
			uploadFiles.push(fileList[i]);
			preview(fileList[i], i)
			
		}
		
	});
	
	/* 이미지 삭제 이벤트 처리 */

});

function preview(file, index){
	var reader = new FileReader();
	reader.onload=function(e){
		console.log(e.currentTarget.result);
		/* 이미지 생성 */
	};
	
	reader.readAsDataURL(file);
}
/* 추가할것 이미지 보이게하고 삭제하는 것... */
</script>
</head>
<%@ include file="../inc/common.jsp"%>

			item add <br><br>
			<div class="container">
				<form>
					<div class="outerbox">
						<label for="fname">상품이름</label>
						<div class="box">
							<input type="text" id="fname" name="name" placeholder="상품이름을 등록하세요">
						</div>					
					</div>
					<div class="outerbox">
						<label for="lname">카테고리</label>
						<div class="box">
							<div style="width:60%;">
								<select name="category_id">
									<option>선택하세요</option>
									<option>선택하세요</option>
									<option>선택하세요</option>
								</select>
							</div>
						</div>
					</div>
					<div class="outerbox">
						<label for="lname">상품가격</label>
						<div class="box">
							<input type="text" name="price" placeholder="상품가격을 등록하세요" required/>
						</div>
					</div>
					<div class="outerbox">
						<label for="lname">상품 수량</label><br>
						<div class="box">
							<input type="number" name="price" placeholder="상품수량을 등록하세요" required/>
						</div>						
					</div>
										
					<div class="outerbox">
						<label for="lname">색상 및 모양</label><br>
						<div class="box">
							<p>
								<input type="color" name="color" value="#dedede"/>
								<input type="color" name="color" value="#ff0808"/>
								<input type="color" name="color" value="#f308ff"/>
								<input type="color" name="color" value="#fc9c00"/>
								<input type="color" name="color" value="#19fc00"/>
								<input type="color" name="color" value="#0093fc"/>
								<input type="color" name="color" value="#0093fc"/>
							</p>
							<div id="dragArea"></div>
							<input type="file" name="photo" required/>
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
<%@ include file="../inc/footer.jsp" %>