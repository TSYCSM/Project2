var itemNameArray = [];

function appendItem(param) {

	var itemName = param.getElementsByTagName("h2")[0].innerText;
	var itemPrice = param.getElementsByTagName("h3")[0].innerText;
	var table = document.getElementById("itemTable");
	var items_table = table.getElementsByTagName("tr"); //table의 item들이 들어있는 array

	if (itemNameArray.includes(itemName)) { //이미 table에 item이 있다면 up count

		for (i = 0; i < items_table.length; i++) {

			var item_table = items_table[i]; //items_table에서 row 하나를 꺼내와 item_table에 할당
			var itemName_table = item_table.getElementsByTagName('td')[0].childNodes[0].nodeValue; //꺼내온 table의 row에서 상품명을 추출해낸다
			var itemAmount_table = item_table.getElementsByTagName("td")[1].childNodes[0];
			var itemTotalPrice_table = item_table.getElementsByTagName("td")[2].childNodes[0];  //td>span

			if (itemName_table == itemName) {
				itemAmount_table.value = parseInt(itemAmount_table.value) + 1;
				itemTotalPrice_table.innerText = parseInt(itemPrice) * parseInt(itemAmount_table.value);
			}
		}


	} else { //table에 item이 없다면 추가
		itemNameArray.push(itemName);

		var price = param.getElementsByTagName("span")[0].innerText;

		var table = document.getElementById("itemTable");
		var row = document.createElement("tr");
		var col1 = document.createElement("td");
		var col2 = document.createElement("td");
		var col3 = document.createElement("td");
		var col4 = document.createElement("td");

		var html1 = param.getElementsByTagName("h2")[0].innerText + "<input type='hidden' value='" + price + "'/>";
		var html2 = "<input onchange='changeItemAmount(this)' type='number' value='1' min='1'/>";
		var html3 = "<span>" + price + "</span>";
		var html4 = "<button onclick='removeItem(this)'>X</button>";

		row.appendChild(col1);
		row.appendChild(col2);
		row.appendChild(col3);
		row.appendChild(col4);

		col1.innerHTML = html1;
		col2.innerHTML = html2;
		col3.innerHTML = html3;
		col4.innerHTML = html4;

		table.appendChild(row);

	}

	document.getElementById("cartlist").getElementsByTagName("p")[0].getElementsByTagName("span")[0].innerText = returnTotalPrice();  //totalPrice 계산 및 출력
	document.getElementById("cartlist").getElementsByTagName("p")[0].getElementsByTagName("input")[0].value= returnTotalPrice();  //totalPrice 계산 및 출력
}

function removeItem(param) {
	var table = document.getElementById("itemTable");
	var row = param.parentNode.parentNode;
	var itemName = row.getElementsByTagName("td")[0].innerText;

	itemNameArray.splice(itemNameArray.indexOf(itemName), 1); //itemNameArray에 들어있는 itemName도 자워야 나중에 다시 넣어진다
	table.removeChild(param.parentNode.parentNode); //시각적으로 table에서 item 하나를 지운다

	document.getElementById("cartlist").getElementsByTagName("p")[0].getElementsByTagName("span")[0].innerText = returnTotalPrice();  //totalPrice 계산 및 출력
	document.getElementById("cartlist").getElementsByTagName("p")[0].getElementsByTagName("input")[0].value = returnTotalPrice();  //totalPrice 계산 및 출력
}

function changeItemAmount(param) {
	var item = param.parentNode.parentNode;
	var itemPrice = item.getElementsByTagName("td")[0].getElementsByTagName("input")[0].value;
	var itemAmount = item.getElementsByTagName("td")[1].childNodes[0].value;
	var itemTotalPrice = item.getElementsByTagName("td")[2].childNodes[0];
	
	itemTotalPrice.innerText = itemAmount * itemPrice;

	document.getElementById("cartlist").getElementsByTagName("p")[0].getElementsByTagName("span")[0].innerText = returnTotalPrice();  //totalPrice 계산 및 출력
	document.getElementById("cartlist").getElementsByTagName("p")[0].getElementsByTagName("input")[0].value= returnTotalPrice();  //totalPrice 계산 및 출력
}

function returnTotalPrice() {
	var table = document.getElementById("itemTable");
	var items_table = table.getElementsByTagName("tr"); //table의 item들이 들어있는 array
	var totalPrice = 0;

	for (i = 0; i < items_table.length; i++) {
		var item_table = items_table[i];
		var itemTotalPrice_table = item_table.getElementsByTagName("td")[2].childNodes[0];
		totalPrice = totalPrice + parseInt(itemTotalPrice_table.innerText);
	}

	return totalPrice;
}





