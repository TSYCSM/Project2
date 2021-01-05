function modeChange(param) {

	var contentTd = param.parentNode.parentNode.childNodes[5];
	var contentTagName = contentTd.childNodes[0].tagName;

	var buttonTd = param.parentNode.parentNode.childNodes[9];

	if(contentTagName == "P") {  //보기 모드일 때, 수정 버튼을 누르면...
		var contentText = contentTd.childNodes[0].innerText;
		contentTd.innerHTML = "<textArea style=\"width:100%;font-size:16px\">" + contentText + "</textArea>";
		buttonTd.childNodes[0].innerText = "확인";
	} else if(contentTagName == "TEXTAREA") {  //수정 모드일 때, 확인 버튼을 누르면...
		var contentText = contentTd.childNodes[0].value;
		console.log(contentText);
		contentTd.innerHTML = "<p>" + contentText + "</p>";
		buttonTd.childNodes[0].innerText = "수정";
	}

}