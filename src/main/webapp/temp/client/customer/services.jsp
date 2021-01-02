<%@ page contentType="text/html;charset=utf-8"%>

<div id="services" class="tabcontent">
	<div id="servicelist">
		<h1>서비스 목록</h1>

		<div class="service" onclick="appendService(this)">
			<img src="/resources/image/client/customer/cup.jpg" />
			<h2>김치</h2>
			<h3>시원한 김치를 드립니다.</h3>
		</div>

		<div class="service" onclick="appendService(this)">
			<img src="/resources/image/client/customer/cycle.jpg" />
			<h2>물</h2>
			<h3>시원한 물을 드립니다.</h3>
		</div>

		<div class="service" onclick="appendService(this)">
			<img src="/resources/image/client/customer/cycle.jpg" />
			<h2>단무지</h2>
			<h3>시원한 단무지를 드립니다.</h3>
		</div>

	</div>

	<div id="requestlist">
		<h1>요청 사항</h1>
		<table id="serviceTable"></table>
		<textArea placeholder="세부 요구 사항 입력"></textArea>
		<button>요청</button>
	</div>

</div>
