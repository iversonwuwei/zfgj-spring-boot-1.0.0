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
<div class="pd-20">
  <form action="" method="post" class="form form-horizontal" id="form">
    
    <legend>安全设置：</legend>
    <div class="responsive">
    <div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>当前密码：</label>
			<div class="formControls col-3">
				<input type="password" placeholder="当前密码" autocomplete="off" value="" class="input-text" name="password" id="password" nullmsg="当前密码不能为空" datatype="*">
			</div>
			<div class="col-4"> </div>
		</div>
    <div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>新密码：</label>
			<div class="formControls col-3">
				<input type="password" placeholder="新密码" autocomplete="off" value="" class="input-text" datatype="*" nullmsg="新密码不能为空" name="passwordn" id="passwordn"
				onKeyUp="pwStrength(this.value)" onBlur="pwStrength(this.value)">
			</div>
			<div class="col-4"> </div>
		</div>
        <div class="item cl">
			<label class="form-label col-3"></label>
			<div class="formControls col-3">
				<p class="form_text"> &nbsp;<i class="security_level" id="strength_L"></i></p>
			</div>

		</div>
		<div class="item cl">
			<label class="form-label col-3"><span class="c-red">*</span>确认密码：</label>
			<div class="formControls col-3">
				<input type="password" placeholder="确认新密码" autocomplete="off" class="input-text " errormsg="您两次输入的新密码不一致！" datatype="*" nullmsg="请再输入一次新密码！"
				 recheck="passwordn"  name="passwordc" id="passwordc">
			</div>
			<div class="col-4"> </div>
		</div>
        
        <div class="responsive">
      <div class="row cl btn_group">
        <div class="col-3 col-offset-3"> <input class="btn btn-primary radius mr-10" type="submit" value="提交"/>提交</div><a class="btn btn-primary radius mr-10">提交</a>
      </div>
    </div>
        
    </div>
    
  </form>
</div>

	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/icheck/jquery.icheck.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/Validform/5.3.2/Validform.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/layer/1.9.3/layer.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/js/H-ui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/js/H-ui.admin.js"></script>
	<script type="text/javascript">
	$(function(){
		
		/* $("#form").Validform({
			tiptype:2,
			callback:function(form){
			}
		});
	}); */
	
	$("#form").Validform({
		tiptype:2,
		callback:function(form){
			doPassChange();
			return false;
		}
	});
});
	function doPassChange() {
		var password = $("#password").val();
		var passwordn = $("#passwordn").val();
		var passwordc = $("#passwordc").val();
		var passwordLevel = checkStrong(passwordn);
		$.ajax({
			type : "POST",
			url : 'pwdcs',
			data : {
				password : password,
				passwordn : passwordn,
				passwordc : passwordc,
				passwordLevel : passwordLevel
			},
			dataType : 'json',
			cache : false,
			success : function(data) {
				if (1 == data.success) {
						window.location.href = "${ctx}/my/asecu";
					$("#submit").hide();

				} else {
					layer.alert(data.message);
				}
			},
			error : function() {
				layer.alert("网络异常，请稍后重试！")

			}
		}); 
	}
	
	//判断输入密码的类型  
	function CharMode(iN) {
		if (iN >= 48 && iN <= 57) //数字  
			return 1;
		if (iN >= 65 && iN <= 90) //大写  
			return 2;
		if (iN >= 97 && iN <= 122) //小写  
			return 4;
		else
			return 8;
	}
	//bitTotal函数  
	//计算密码模式  
	function bitTotal(num) {
		modes = 0;
		for (i = 0; i < 4; i++) {
			if (num & 1)
				modes++;
			num >>>= 1;
		}
		return modes;
	}
	//返回强度级别  
	function checkStrong(sPW) {
		if (sPW.length <= 4)
			return 0; //密码太短  
		Modes = 0;
		for (i = 0; i < sPW.length; i++) {
			//密码模式  
			Modes |= CharMode(sPW.charCodeAt(i));
		}
		return bitTotal(Modes);
	}

	//显示颜色  
	function pwStrength(pwd) {
		L_color = "security_level";

		if (pwd == null || pwd == '') {

		} else {
			S_level = checkStrong(pwd);
			switch (S_level) {
			case 0:

				Lcolor = "security_level";
			case 1:
				Lcolor = "security_level level_low";

				break;
			case 2:
				Lcolor = "security_level level_mid";
				break;
			default:

				Lcolor = "security_level level_hig";
			}
		}

		$("#strength_L").attr('class', Lcolor);
		return;
	}
	</script>
</body>
</html>