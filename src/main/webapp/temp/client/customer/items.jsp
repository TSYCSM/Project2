<%@ page contentType="text/html;charset=utf-8"%>

<div id="items" class="tabcontent">
	<div id="itemlist">
		<h1>상품 목록</h1>

		<div class="item" onclick="appendItem(this)">
			<img src="/resources/image/client/customer/machine.png" />
			<h2>복사기</h2>
			<h3>
				<span>30000</span>원
			</h3>
		</div>

		<div class="item" onclick="appendItem(this)">
			<img src="/resources/image/client/customer/cycle.jpg" />
			<h2>자전거</h2>
			<h3>
				<span>90000</span>원
			</h3>
		</div>

		<div class="item" onclick="appendItem(this)">
			<img src="/resources/image/client/customer/ball.jpg" />
			<h2>공</h2>
			<h3>
				<span>500</span>원
			</h3>
		</div>

		<div class="item" onclick="appendItem(this)">
			<img src="/resources/image/client/customer/cup.jpg" />
			<h2>컵</h2>
			<h3>
				<span>300</span>원
			</h3>
		</div>

		<div class="item" onclick="appendItem(this)">
			<img src="/resources/image/client/customer/plate.jpg" />
			<h2>그릇</h2>
			<h3>
				<span>1000</span>원
			</h3>
		</div>

	</div>

	<div id="cartlist">
		<h1>장바구니</h1>
		<table id="itemTable"></table>
		<textArea placeholder="세부 요구 사항 입력"></textArea>
		<p>
			합계 : <span>0</span>원
		</p>
		<button>주문</button>
	</div>

</div>
