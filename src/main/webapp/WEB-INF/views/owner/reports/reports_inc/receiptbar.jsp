<%@ page contentType="text/html;charset=utf-8"%>
<div id="myReceiptbar" class="receiptbar">
	<div class="receipt_content">
		<a href="javascript:void(0)" class="closebtn" onclick="hideReceipt()">&times;</a>
		<div class="total_price">orderSummary(total_price)</div>
		<div class="total_name">총계</div>
		<div>POS:POS1</div>
		<div class="orderDetail">
			<div>orderDeatilJoinedItem(item_name)</div>
			<div>orderDetail(quantity) X orderDetail(price)</div>
		</div>
		<div class="date">날짜</div>
	</div>
</div>