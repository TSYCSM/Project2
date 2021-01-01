<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>owner page</title>
<%@ include file="../inc/header.jsp" %>

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
							color picker 나올 곳<br>
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