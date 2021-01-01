var serviceNameArray = [];
var total = 0;

function appendService(param) {

	var serviceName = param.getElementsByTagName("h2")[0].innerText;

	if (!serviceNameArray.includes(serviceName)) { //이미 table에 service이 있다면 up count
		serviceNameArray.push(serviceName);

		var table = document.getElementById("serviceTable");
		var row = document.createElement("tr");
		var col1 = document.createElement("td");
		var col2 = document.createElement("td");

		var html1 = param.getElementsByTagName("h2")[0].innerText;
		var html2 = "<button onclick='removeService(this)'>X</button>";

		row.appendChild(col1);
		row.appendChild(col2);

		col1.innerHTML = html1;
		col2.innerHTML = html2;

		table.appendChild(row);

	}
}

function removeService(param) {
	var table = document.getElementById("serviceTable");
	var row = param.parentNode.parentNode;
	var serviceName = row.getElementsByTagName("td")[0].innerText;

	serviceNameArray.splice(serviceNameArray.indexOf(serviceName), 1); //serviceNameArray에 들어있는 serviceName도 자워야 나중에 다시 넣어진다
	table.removeChild(param.parentNode.parentNode); //시각적으로 table에서 service 하나를 지운다
}
