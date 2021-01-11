<%@page import="com.tsycsm.agileoffice.model.domain.Review"%>
<%@page import="com.tsycsm.agileoffice.model.domain.Item"%>
<%@page import="com.tsycsm.agileoffice.common.Pager"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<div id="reviews" class="tabcontent">
	<div>
		<h1>리뷰</h1>
		<%if(customer != null){ %>
			<button class="regist-area" type="button" class="showRegist_btn" onClick="showRegist()">등록</button>
		<%}else{ %>
			<h3 class="regist-area">맴버쉽 회원만 댓글을 쓸 수 있습니다.</h3>
		<%} %>
	</div>
	
	<form class="review-form">
		<table id="reviewTable">
			<tr class="first-tr">
				<th>No</th>
				<th>상품</th>
				<th>리뷰</th>
				<th>작성일</th>
				<th></th>
				<th></th>
			</tr>
			<tr class="regist-tr" style="display: none;">
				<td></td>
				<td>
					<select name="item.item_id">
						<option value="0">상품 선택</option>
						<%for(int i =0; i<itemList.size();i++){ %>
							<%Item item = itemList.get(i); %>
							<option value="<%=item.getItem_id()%>"><%=item.getItem_name() %></option>
						<%} %>
					</select>
				</td>
				<td>
					<input type="hidden" name="owner_id" value="<%=owner.getOwner_id()%>">
					<input type="hidden" name="customer_id" <%if(customer !=null){ %>value="<%=customer.getCustomer_id()%><%}%>">
					<input type='text' name='comments' class="comments" width="100%">
				</td>
				<td></td>
				<td>
					<button type="button" class="regist_btn" onClick="registReview()">확인</button>
				</td>
				<td>
					<button type="button" class="hide_btn" onClick="hideRegist()">X</button>
				</td>
			</tr>
		</table>
		
		<table class="review-box">
	
		</table>
			
		<table width="100%" class="page-box">
		
		</table>
	</form>
	
		
</div>


