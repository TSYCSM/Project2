<%@ page contentType="text/html;charset=utf-8"%>
<div id="mySidenav" class="sidenav">
	<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&#9776;</a>
	<img alt="agile office" src="/resources/image/logo/logo.png" width="120px;">
	<button onClick="location.href='/client/owner/account/mypage'" class="dropdown-btn">
		내 정보
	</button>

	<button class="dropdown-btn">
		보고서 <i class="fa fa-caret-down"></i>
	</button>
	<div class="dropdown-container">
		<a href="/client/owner/reports/salesSummary">매출 요약</a> 
		<a href="/client/owner/reports/receipts">영수증 보관함</a> 
		<a href="/client/owner/reports/customerList">고객 관리</a> 
		<a href="javascript:alert('서비스 준비중입니다')">할인</a>
	</div>

	<button class="dropdown-btn">
		물품 목록 <i class="fa fa-caret-down"></i>
	</button>
	<div class="dropdown-container">
		<a href="/client/owner/inventory/item/list">상품</a> 
		<a href="/client/owner/inventory/category/list">카테고리</a> 
		<a href="javascript:alert('서비스 준비중입니다')">할인</a>
	</div>

	<button onClick="alert('서비스 준비 중입니다')" class="dropdown-btn">
		직원 관리
	</button>


</div>