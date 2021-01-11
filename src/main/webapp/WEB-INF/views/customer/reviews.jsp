<%@page import="com.tsycsm.agileoffice.model.domain.Review"%>
<%@page import="com.tsycsm.agileoffice.model.domain.Item"%>
<%@page import="com.tsycsm.agileoffice.common.Pager"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<div id="reviews" class="tabcontent">
	<h1>리뷰</h1>
	<form class="review-form">
		<table id="reviewTable">
			<tr class="first-tr">
				<th>No</th>
				<th>상품명</th>
				<th>제목</th>
				<th>작성일</th>
				<th></th>
				<th></th>
			</tr>
			<tr class="regist-tr" style="display: none;">
				<td></td>
				<td>
					<select name="item_id">
						<option>선택하세요</option>
						<%for(int i =0; i<itemList.size();i++){ %>
							<%Item item = itemList.get(i); %>
							<option value="<%=item.getItem_id()%>"><%=item.getItem_name() %></option>
						<%} %>
					</select>
				</td>
				
				<td colspan='4'>
					<input type="hidden" name="owner_id" value="<%=owner.getOwner_id()%>">
					<input type="hidden" name="customer_id" value="<%=customer.getCustomer_id()%>">
					<input type='text' name='comments' width='60%'>
					<button type="button" class="regist_btn" onClick="registReview()">등록</button>
				</td>
			</tr>
		</table>
		
			<table class="review-box">
		
			</table>
			
		<table width= "90%" class="page-box">
		
		</table>
	</form>
	<button type="button" class="showRegist_btn" onClick="showRegist()">등록</button>
</div>


