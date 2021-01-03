<%@ page contentType="text/html;charset=utf-8"%>
<div id="mySidenav" class="sidenav">
  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&#9776;</a>
  <button class="dropdown-btn">Home 
    <i class="fa fa-caret-down"></i>
  </button>
  <div class="dropdown-container">
    <a href="/temp/admin/index.jsp">Home</a>
    <a href="#">Sign Out</a>
  </div>
  
  <button class="dropdown-btn">회원관리
    <i class="fa fa-caret-down"></i>
  </button>
  <div class="dropdown-container">
    <a href="/temp/admin/member/memberlist.jsp">회원리스트</a>
  </div>
  
  <button class="dropdown-btn">고객관리 
    <i class="fa fa-caret-down"></i>
  </button>
  <div class="dropdown-container">
    <a href="/temp/admin/cs/qna.jsp">QnA</a>
  </div>

  
</div>