<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<!DOCTYPE HTML>
<html>
<head>

<%@ include file="/WEB-INF/views/include/head2.jsp"%>

<title>添加管理员</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 合同管理 <span class="c-gray en">&gt;</span> 合同解除<a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="pd-20">
  <form action="${ctx}/con/enter" method="get" class="form form-horizontal" id="tijiao">
  	<input type="hidden" id="conNo" name="conNo">
  	<input type="hidden" id="conId" name="conId">
    <legend>合同解除</legend>
    <div class="responsive">
      <div class="row cl mb-20">
        <label class="form-label col-3">合同编号：</label>
        <div class="formControls col-3">
         <input type="text" placeholder="请输入合同编号.." class=" validate[required,funcCall[checkno]] input-text" name="no" id="no" data-prompt-target="noError" data-prompt-position="inline">
         </div>
         <input type="hidden" name="contract.no" value="${contract.no}"/>
        <div class="form_text" style="margin-top:0;">
          <a class="btn btn-primary radius ml-5" id="sousuo">搜 索</a>
         </div>
        <p id="noError" class="form_text col-2"></p>
      </div>
      
    </div>
    <div class="panel panel-default mb-20 hide" id="lessor">
      <div class="panel-header">出租方</div>
      <div class="panel-body">
        <div class="responsive">
          <div class="item cl">
            <label class="form-label col-3">出租方姓名：</label>
            <div class="form_text col-3" id="lessorName">
			 	 
            </div>
          </div>
          <div class="row cl">
            <label class="form-label col-3">预留手机号：</label>
            <div class="form_text col-3" id="lessorMobile">
              
            </div>
            <div class="formControls col-3">
              <a id="getIdcode" onclick="getIdcode(0);" class="btn btn-primary radius">发送校验码</a>
            </div>
          </div>
          <div class="row cl">
          <label class="form-label col-3">校验码：</label>
        <div class="formControls col-2">
          <input type="text" placeholder="请输入校验码.." class="validate[required,funcCall[checknum]] input-text" name="idcode" id="idcode"  >
        </div>
        </div>
        </div>
      </div>
    </div>
    
    <div class="panel panel-default mb-20 hide" id="lessee">
      <div class="panel-header">承租方</div>
      <div class="panel-body">
        <div class="responsive">
          <div class="item cl">
            <label class="form-label col-3">承租方姓名：</label>
            <div class="form_text col-3" id="lesseeName">
					
            </div>
          </div>
          <div class="row cl">
            <label class="form-label col-3">预留手机号：</label>
            <div class="form_text col-3" id="lesseeMobile">
					
            </div>
            <div class="formControls col-3">
              <a  class="btn btn-primary radius" onclick="getIdpcode(1);">发送校验码</a>
            </div>
          </div>
          <div class="row cl">
          <label class="form-label col-3">校验码：</label>
        <div class="formControls col-2">
          <input type="text" placeholder="请输入校验码.." class="validate[required,funcCall[checknum]] input-text"  id="pcode" >
        </div>
        </div>
        </div>
      </div>
    </div>
    
    <div class="responsive hide" id="enter_btn_box" >
      <div class="row cl btn_group">
        <div class="col-6 col-offset-4"> <a class="btn btn-primary radius mr-10" id="enter">提交</a> </div>
      </div>
    </div>
  </form>
</div>
<%@ include file="/WEB-INF/views/include/foot.jsp"%>
<script type="text/javascript" src="${ctxStatic}${hui}js/jquery-migrate-1.1.0.min.js"></script>
<script type="text/javascript">

$(function() {	
	/* var lessee = $("#lessee").hide();
	var lessor = $("#lessor").hide(); */
/* 	var enter = $("#enter").hide(); */
	$('#tijiao').validationEngine('attach', {showOneMessage: true,maxErrorsPerField : 1,promptPosition: 'centerRight',addPromptClass: 'formError-noArrow formError-text'});
});

 $("#sousuo").click(function(){
	 var partten = /^[0-9a-zA-Z]{18}$/;
	 var no = $("#no").val();
	if(no == "" || no == null){
		return $("#tijiao").submit();
	}else if(!partten.test(no)){
		return $("#tijiao").submit();
	}
	
	initMoreInfo();
	
	
	
	$.ajax({
		type: "POST",
		url: '${ctx}/con/finddiss',
		data: {
			no: no
		},
		dataType:'json',
		cache: false,
		async : false, 
		success: function(data){
			if (1 == data.success) {
				$("#lesseeName").text(data.data.lesseeName);
				$("#lesseeMobile").text(data.data.lesseeMobile);
				$("#lessorName").text(data.data.lessorName);
				$("#lessorMobile").text(data.data.lessorMobile);
				$("#conNo").val(data.data.no);
				$("#conId").val(data.data.id);
				$("#lessee").show();
				$("#lessor").show();
				$("#enter_btn_box").show();
				
		}else if(0 == data.success){
			layer.alert(data.message);
		}
			//alert(data.data.lesseeName);
		} 
	});
  }); 



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
		
		$("#waitNextIdcode").show();
		$("#waitNextIdcode").text("重新获取(" + (waitTime / 1000 - j) + "S)");
	} else {
		j = 0;
		getNextIdcode();
	}
}

function getIdcode(flag) {
	var key = $("#conNo").val();
	var mobile = $("#lesseeMobile").val();  //电话未传到后台
	$.ajax({
		type : "POST",
		url : "${ctx}/con/getidcode",
		data : {
			mobile : mobile,
			flag:flag,
			key:key
		},
		dataType : 'json',
		cache : false,
		success : function(data) {
			if (1 == data.success) {
				//$("#idcode").focus();
				//以后取消
				$("#idcode").val(data.data.idcode);
				 
				now = new Date().getTime();

				if (now - data.data.idcodetm < waitTime) {
					// 获取下次验证码

					waitNextIdcode();

				} else {
					layer.alert(data.message);
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
function getIdpcode(flag) {
	var key = $("#conNo").val();
	var mobile = $("#lesseeMobile").val(); //电话未传到后台
	$.ajax({
		type : "POST",
		url : "${ctx}/con/getidcode",
		data : {
			mobile : mobile,
			flag:flag,
			key:key
		},
		dataType : 'json',
		cache : false,
		success : function(data) {
			if (1 == data.success) {
				//$("#idcode").focus();
				//以后取消
			 
				$("#pcode").val(data.data.pcode);
				now = new Date().getTime();

				if (now - data.data.idcodetm < waitTime) {
					// 获取下次验证码

					waitNextIdcode();

				} else {
					layer.alert(data.message);
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

	function code() {
		var key = $("#conNo").val();
		 var idcode = $("#idcode").val();
		 if(idcode =="" || idcode == null){
			return ;
		} 
		var pcode =$("#pcode").val();
		 if(pcode =="" || pcode == null){
			return ;
		}  
		$.ajax({
			type: "POST",
			url: '${ctx}/con/check',
			data: {
				idcode: idcode,
				pcode:pcode,
				key:key
			},
			dataType:'json',
			cache: false,
			async : false, 
			success: function(data){
				if (1 == data.success) {
					layer.alert(data.message);
				}else if(0 == data.success){
				$("#idcode").val("");
				$("#pcode").val("");
				layer.alert(data.message);
			}
				
				//alert(data.data.lesseeName);
				
			}
			 
	});
	}


	/* $("#idcode").blur(function(){
		var idcode =$("#idcode").val();
		if($("#idcode").val()=="" || $("#idcode").val()== null){
			return ;
		} 
		$.ajax({
			type: "POST",
			url: '${ctx}/con/check',
			data: {
				idcode: idcode
			},
			dataType:'json',
			cache: false,
			async : false, 
			success: function(data){
				if (1 == data.success) {
					layer.alert(data.message);
			}else if(0 == data.success){
				$("#idcode").val("");
				layer.alert(data.message);
			}
				
				//alert(data.data.lesseeName);
				
			}
			 
	});
}); */

$("#enter").click(function(){
	/* var idcode =$("#idcode").val();
	var pcode =$("#pcode").val();
	if(( idcode =="" || idcode == null) || (pcode =="" || pcode == null)) {
		return $("#enter").submit();
	}  */
	$("#tijiao").submit();
	
	code();
})
 
 
 function initMoreInfo() {
	var lessee = $("#lessee").hide();
	var lessor = $("#lessor").hide();
	var enter = $("#enter_btn_box").hide();	
	$("#lesseeName").text('');
	$("#lesseeMobile").text('');
	$("#lessorName").text('');
	$("#lessorMobile").text('');
	$("#conId").val('');
}
	function checknum(field){
		var num = field.val();
		if(isNaN(jQuery.trim(num))) {
			return "必须是数字";
		} 
		if(num.length > 6){
			return "必须是6位数字";
		}
	}
 
  function checkno(field){
	  var no = field.val();
	  var partten = /^[0-9a-zA-Z]{18}$/;
	  if(!partten.test(no)){
		  return "必须是18位的合同编号";
	  }
  }


 
</script>
</body>
</html>