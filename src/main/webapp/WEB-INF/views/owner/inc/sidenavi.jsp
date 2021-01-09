<%@ page contentType="text/html;charset=utf-8"%>
<div id="mySidenav" class="sidenav">
  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&#9776;</a>
  <button class="dropdown-btn">마이페이지
    <i class="fa fa-caret-down"></i>
  </button>
  <div class="dropdown-container">
    <a href="/view/owner/mypage/mypage.jsp">내 계정</a>
    <a href="#">로그아웃</a>
  </div>
  
  <button class="dropdown-btn">보고서
    <i class="fa fa-caret-down"></i>
  </button>
  <div class="dropdown-container">
    <a href="/view/owner/reports/sales_summary.jsp">매출 요약</a>
    <a href="/view/owner/reports/sales_item.jsp">상품별 매출</a>
    <a href="/view/owner/reports/sales_category.jsp">카테고리별 매출</a>
    <a href="/view/owner/reports/sales_employee.jsp">직원별 매출</a>
    <a href="/view/owner/reports/discount.jsp">할인</a>
    <a href="/view/owner/reports/receipts.jsp">영수증 보관함</a>
  </div>
  
  <button class="dropdown-btn">물품 목록 
    <i class="fa fa-caret-down"></i>
  </button>
  <div class="dropdown-container">
    <a href="/view/owner/item/item_list.jsp">상품</a>
    <a href="#">카테고리</a>
    <a href="#">할인</a>
  </div>
  
   <a href="#">고객 관리</a>
   <a href="#">직원 관리</a>

  
</div>