$(function(){
	//input设置为readOnly后，焦点在此input时按下Backspace按键，不返回上一个页面
	$("input[readOnly]").keydown(function(e) {
		e.preventDefault();
	});
});

function newDate(str) {
	str = str.replace(/-/g,"/");
	var date = new Date(str); 
	return date; 
}

function formatDate(date) {
	return (date.getFullYear()) + '-'
		+ formatNum(date.getMonth() + 1) + '-'
		+ formatNum(date.getDate());
}

function formatNum(num) {
	var numStr = num + "";
	if (numStr.length == 2) {
		return numStr;
	} else {
		return +"0" + numStr;
	}
}

/**
 * 初始化返回信息显示框
 * 
 * @param type
 * @param msg
 */
function initMsgBox(type, msg) {
	var $msgBox = $(".message_box");
	
	if ($msgBox.length <= 0) {
		$(".breadcrumb").after("<div class='message_box'></div>");
	}
	
	var mapper = {
		error : 'message_box Huialert Huialert-danger',
		warning : 'message_box Huialert Huialert-danger',
		message : 'message_box Huialert Huialert-success'
	}
	
	var cssName = mapper[type];
	
	$(".message_box").append("<div class='" + cssName + "'><i onclick='hideIconfont(this);' class='Hui-iconfont'>&#xe6a6;</i>" + msg + "</div>");
}

/**
 * 移除错误提示div
 * 
 * @param this
 */
function hideIconfont(it){
	$(it).parent().remove();
};