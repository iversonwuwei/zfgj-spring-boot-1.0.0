var waitTime = 5000;// 等待时间(毫秒)
var j = 0;

function getNextIdcode() {
	$("#getIdcode").show();
	$("#waitNextIdcode").hide();
	j = 0;
}
function waitNextIdcode() {
	if (j < waitTime / 1000) {
		setTimeout(function() {
			j++;
			waitNextIdcode();

		}, 1000);
		$("#getIdcode").hide();
		$("#waitNextIdcode").show();
		$("#waitNextIdcode").text("重新获取(" + (waitTime / 1000 - j) + "S)");
	} else {
		j = 0;
		getNextIdcode();
	}
}
function getIdcode(x) {

	var tm = new Date().getTime();
	var mobile = $("#"+minput).val();
	$.ajax({
		type : "POST",
		url : x+"/my/getidcode",
		data : {
			mobile : mobile,
			tm : tm
		},
		dataType : 'json',
		cache : false,
		success : function(data) {

			if (1 == data.success) {
				$("#idcode").focus();
				//以后取消
				$("#idcode").val(data.data.idcode);
				
				now = new Date().getTime();

				if (now - data.data.idcodetm < waitTime) {
					// 获取下次验证码

					waitNextIdcode();

				} else {
					$("#getIdcode").text("获取验证码");
				}

			} else {
				//showErr(data.message);
			}
		},
		error : function() {
			//showErr("网络异常，请稍后重试！");
		}
	});
}

function getRootPath(){
    var curWwwPath=window.document.location.href;
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    var localhostPaht=curWwwPath.substring(0,pos);
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}