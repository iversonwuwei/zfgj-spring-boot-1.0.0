// JavaScript Document 防止重复提交
FC = {
	// ajax setting
	ajax: function(options){
		var defaultOpt = {
			dataType: 'json',
			type: 'post',
			beforeSend: this.ajaxBeforeSend,
			complete: this.ajaxComplate,
			success: this.ajaxSuccess,
			error: this.ajaxError
		};
		
		options = $.extend({}, defaultOpt, options);
		
		$.ajax(options);
	},
	ajaxBeforeSend: function(XHR) {
		FC.showLoading();
	},
	ajaxComplate: function(XHR, TS) {
		FC.closeLoading();
	}, 
	ajaxSuccess: function(data, textStatus) {
		var msg = data.message;
		if (data.success == 1) {
			if (!msg || msg == '') {
				msg = "操作成功！";
			}
			FC.msg(msg, {icon: 1});
			return;
		} else if (data.success == 0) {
			if (!msg || msg == '') {
				msg = "操作失败，请重试！";
			}
			FC.alert(msg, {icon: 2});
			return;
		}
	},

	ajaxError: function(XMLHttpRequest, textStatus, errorThrown) {
		FC.alert('系统异常，请稍候再试！', {icon: 2});
	},
	
	alert: function(msg, opt) {
		layer.alert(msg, opt);
	},
	
	message: function(msg, opt) {
		layer.msg(msg, opt);
	},
	
	confirm: function(msg, opt) {
		layer.confirm(msg, opt);
	},
	
	showLoading: function() {
		_loading_idx = layer.load(1);
	},
	closeLoading: function() {
		layer.close(_loading_idx);
	}
};

$(function(){
	$.ajaxSetup({
		contentType: "application/x-www-form-urlencoded; charset=utf-8"
	});
	
	//input设置为readOnly后，焦点在此input时按下Backspace按键，不返回上一个页面
	$("input[readOnly]").keydown(function(e) {
		e.preventDefault();
	});
});