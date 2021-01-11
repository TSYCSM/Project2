<%@page import="com.tsycsm.agileoffice.model.domain.Review"%>
<%@page import="com.tsycsm.agileoffice.model.domain.Item"%>
<%@page import="com.tsycsm.agileoffice.common.Pager"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	
	Pager pager = new Pager();
	pager.init(request, reviewList.size());
%>

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
			<%
			int num = pager.getNum();
			int curPos = pager.getCurPos();
			%>
			<%for(int i=0; i<pager.getPageSize(); i++){ %>
			<%if(num < 1) break; %>
			<%Review review= reviewList.get(curPos++); %>
			<tr>
				<td><%=num-- %></td>
				<td><%=review.getItem_id() %></td>
				<td><p><%=review.getComments() %></p></td>
				<td><%=review.getRegdate() %></td>
				<td><button onclick="modeChange(this)">수정</button></td>
				<td><button>삭제</button></td>
			</tr>
			<%} %>
			</table>
			<table class="page-box">
			<tr>
				<td colspan="6" style="text-align:center">
					<%if(pager.getFirstPage() >1){ %>
						<a href="/customer/main.jsp?currentPage=<%=pager.getFirstPage()-1%>">◀</a>					
					<%}else{ %>
						<a href = "javascript:alert('처음 페이지 입니다')">◀</a>
					<%} %>
					<%for(int i=pager.getFirstPage(); i<=pager.getLastPage(); i++){ %>
					<%if(i > pager.getTotalPage()) break; %>
					<a href="/customer/main.jsp?currentPage=<%=i %>">[<%=i %>]</a>
					<%} %>
					<%if(pager.getLastPage() < pager.getTotalPage()) {%>
						<a href="/customer/main.jsp?currentPage=<%=pager.getLastPage()+1%>">▶</a>
					<%}else{ %>
						<a href = "javascript:alert('마지막 페이지입니다.')">▶</a>
					<%} %>
					
				</td>
			</tr>
		</table>
	</form>
	<button type="button" class="showRegist_btn" onClick="showRegist()">등록</button>
</div>


