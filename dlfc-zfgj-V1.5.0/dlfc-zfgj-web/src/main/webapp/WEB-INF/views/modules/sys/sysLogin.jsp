<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<c:set var="hui" value="/hui/"/>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="${ctxStatic}${theme }lib/html5.js"></script>
<script type="text/javascript" src="${ctxStatic}${theme }lib/respond.min.js"></script>
<script type="text/javascript" src="${ctxStatic}${theme }lib/PIE-2.0beta1/PIE_IE678.js"></script>
<![endif]-->
<link href="${ctxStatic}${hui}css/H-ui.min.css" rel="stylesheet"
	type="text/css" />
<link href="${ctxStatic}${hui}css/H-ui.admin.css" rel="stylesheet"
	type="text/css" />
<link href="${ctxStatic}${hui}css/style.css" rel="stylesheet"
	type="text/css" />
<link href="${ctxStatic}${hui}lib/icheck/icheck.css" rel="stylesheet"
	type="text/css" />
<link href="${ctxStatic}${hui}lib/Hui-iconfont/1.0.1/iconfont.css"
	rel="stylesheet" type="text/css" />
	
<link href="${ctxStatic}${hui}lib/jQuery-Validation-Engine-master/css/validationEngine.jquery.css" type="text/css" rel="stylesheet" />
<link href="${ctxStatic}${hui}lib/jQuery-Validation-Engine-master/css/validationEngine_modify.css" type="text/css" rel="stylesheet" />

<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>${fns:getConfig('productName')}登录</title>

<script type="text/javascript">
	var number= 0;
	var loginType = '${loginType }';
	var message = '${message}';
	if(null != loginType && "mgr" ==  loginType ){
		number = 1;
	}
	
</script>

</head>
<body >
	<div class="index_head cl">
		<div class="container">
			<p class="head_logo l">
				<img src="${ctxStatic}${hui}images/head_logo.png" width="185" height="34">
			</p>
			<p class="head_tel r">
				<i class="ico_tel"></i>客服电话：400-8288-628
			</p>
		</div>
	</div>
	<div class="content_body">
		<div class="container cl">
			<div class="index_login">
				<p class="login_box_til">欢迎登录</p>
				<div class="login_box">
					<div class="login_box_bg">
						<div class="login_box_min">
							<div id="login_tabs" class="HuiTab">
								<div class="tabBar cl">
									<span class="hui-animation" data-tra="hui-flipoutY" id="emp">经纪人登录<i class="arrow_top"></i></span>
									<span id="mgr">公司用户登录<i class="arrow_top"></i></span>
								</div>
								<form id="loginForm" class="user_form form-signin" action="${ctx}/login" method="post">
									<input type="hidden" id="loginType" value="emp" name="loginType">
									<input type="hidden" id="username" value="" name="username">
									<input type="hidden" id="password" value="" name="password">
									<input type="hidden" id="validateCode" value="" name="validateCode">
								</form>
								<div class="tabCon" id="tabCon1">
									<div class=" Huialert Huialert-danger  ${empty message ? 'hide' : '' }" id="messageBox1">
										<div class="formError parentFormloginForm formError formError-text inline}" style="opacity: 0.87; position: relative; top: 0px; left: 0px; right: initial; margin-top: 0px;">
											<label id="loginError" class="formErrorContent ${empty message ? 'hide' : '' } ">${message}</label>
										</div>
									</div>
									<form id="loginForm1" class="user_form form-signin" action="" method="post">
<!-- 										<input type="hidden" id="logonType1" value="emp" name="loginType1"> -->
						                <div class="login_item">
						                	<span class="form_icon"><i class="Hui-iconfont">&#xe60d;</i></span>
						                	<input type="text" id="username1" name="username1" class="login_text text-input validate[required]" value="${username}" placeholder="经纪人用户名" 
												data-prompt-target="messageBox1" data-prompt-position="inline" data-errormessage-value-missing="* 请输入用户名.      ">
						                </div>
						                <div class="login_item">
						                	<span class="form_icon"><i class="Hui-iconfont">&#xe60e;</i></span>
											<input type="password" id="password1" name="password1" class="login_text text-input validate[required]" placeholder="经纪人密码"
												data-prompt-target="messageBox1" data-prompt-position="inline" data-errormessage-value-missing="* 请输入密码.        ">
						                </div>
						                <div class="login_item">
						                	<span class="form_icon"><i class="Hui-iconfont">&#xe63f;</i></span>
												<sys:validateCode name="validateCode1"
 													inputCssStyle="font-weight:bold;width:100px;height:26px;margin-bottom:0;"
 													inputCssClass="login_text validate[required,funcCall[validateCode1]]" 
 													target="messageBox1"
 													position="inline" 
 													errormessage="* 请输入验证码."/> 
						                </div>
						                <div class="login_item cl">
							                <div class="skin-minimal l">
											    <div class="check-box">
											  	    <input type="checkbox" id="checkbox-1">
											  	    <label for="checkbox-1">记住我</label>
											    </div>
							  				</div>
						  					<p class="r"><a class="btn-link" href="#">忘记密码？</a></p>
						                </div>
						                <div class="login_item">
						               	    <a class="btn btn-block btn-primary radius" id="saveInfo1" onclick="login()">立即登录</a>
						                </div>
						                <div class="login_item">
						               	    <p class="text-r">还没有账号吗？ <a href="#" class="btn-link">立即注册</a></p>
						                </div> 
									</form>
						        </div>
								<div class="tabCon manager_login" id="tabCon2">
									<div class=" Huialert Huialert-danger  ${empty message ? 'hide' : '' }" id="messageBox2">
										<div class="formError parentFormloginForm formError formError-text inline}" style="opacity: 0.87; position: relative; top: 0px; left: 0px; right: initial; margin-top: 0px;">
											<label id="loginError" class="formErrorContent ${empty message ? 'hide' : '' } ">${message}</label>
										</div>
									</div>
									<form id="loginForm2" class="user_form form-signin" action="" method="post">
<!-- 										<input type="hidden" id="logonType2" value="emp" name="loginType2"> -->
						                <div class="login_item">
						                	<span class="form_icon"><i class="Hui-iconfont">&#xe60d;</i></span>
						                	<input type="text" id="username2" name="username2" class="login_text text-input validate[required]" value="${username}" placeholder="公司用户名" 
												data-prompt-target="messageBox2" data-prompt-position="inline" data-errormessage-value-missing="* 请输入用户名.      ">
						                </div>
						                <div class="login_item">
						                	<span class="form_icon"><i class="Hui-iconfont">&#xe60e;</i></span>
											<input type="password" id="password2" name="password2" class="login_text text-input validate[required]" placeholder="公司用户密码"
												data-prompt-target="messageBox2" data-prompt-position="inline" data-errormessage-value-missing="* 请输入密码.        ">
						                </div>
						                <div class="login_item">
						                	<span class="form_icon"><i class="Hui-iconfont">&#xe63f;</i></span>
												<sys:validateCode name="validateCode2"
 													inputCssStyle="font-weight:bold;width:100px;height:26px;margin-bottom:0;"
 													inputCssClass="login_text validate[required,funcCall[validateCode2]]" 
 													target="messageBox2"
 													position="inline" 
 													errormessage="* 请输入验证码."/> 
						                </div>
						                <div class="login_item">
						               	    <a class="btn btn-block btn-primary radius" id="saveInfo2" onclick="login()">立即登录</a>
						                </div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="footer">
		<div class="foot_link container">
			<a href="#">大连市房屋租赁公共服务平台 </a>|<a href="#">中介crm系统操作平台</a>|<a href="#">大连租房合同管理平台版权所有
				2004-2015</a>
		</div>
	</div>
	
	<script type="text/javascript" src="${ctxStatic}${hui}lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}${hui}lib/icheck/jquery.icheck.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}${hui}lib/layer/1.9.3/layer.js"></script>
	<script type="text/javascript" src="${ctxStatic}${hui}js/H-ui.js"></script>
	<script type="text/javascript" src="${ctxStatic}${hui}js/H-ui.admin.js"></script>
	<script src="${ctxStatic}${hui}lib/jQuery-Validation-Engine-master/js/jquery.validationEngine.js" type="text/javascript"></script>
	<script src="${ctxStatic}${hui}lib/jQuery-Validation-Engine-master/js/languages/jquery.validationEngine-zh_CN.js" type="text/javascript"></script>

	<script type="text/javascript">	
		$(function() {
			$('.skin-minimal input').iCheck({
				checkboxClass : 'icheckbox-blue',
				radioClass : 'iradio-blue',
				increaseArea : '20%'
			});
			$.Huitab("#login_tabs .tabBar span", "#login_tabs .tabCon",
					"current", "click", number);
			
			$('#loginForm1').validationEngine({
				showOneMessage: true,
				maxErrorsPerField:"1",
				addPromptClass: 'formError-text',
				binded:false
			});
			
// 			$('#loginForm2').validationEngine({
// 				showOneMessage: true,
// 				maxErrorsPerField:"1",
// 				addPromptClass: 'formError-text',
// 				binded:false
// 			});
			
// 			$('#saveInfo').click(function(){
// 				$('#loginForm').validationEngine('validate');
// 			});

			//enter键提交
			document.onkeydown=function(event){
				e = event ? event :(window.event ? window.event : null);
				if(e.keyCode==13){
					//执行的方法
					var loginType = $("#loginType").val();
					if("emp" == loginType){
						empLogin();
					}
					if("mgr" == loginType){
						mgrLogin();
					}
					
				}
			} 
		});
		
		function validateCode1(){
			var validateCode = $("#validateCode1").val();
			var flag = false;
			$.ajax({
				type:"GET",
				url:"${pageContext.request.contextPath}/servlet/validateCodeServlet",
				data:"validateCode="+validateCode,
				async:false,
				dataType:"json",
				success:function(data){
					if(data){
						flag = true;
					}else{
						flag = false;
					}
				}
			});
			if(!flag){
				$('.validateCode1Refresh').click();
				return "* 验证码输入错误.";
			}
		}
		
		function validateCode2(){
			var validateCode = $("#validateCode2").val();
			var flag = false;
			$.ajax({
				type:"GET",
				url:"${pageContext.request.contextPath}/servlet/validateCodeServlet",
				data:"validateCode="+validateCode,
				async:false,
				dataType:"json",
				success:function(data){
					if(data){
						flag = true;
					}else{
						flag = false;
					}
				}
			});
			if(!flag){
				$('.validateCode2Refresh').click();
				return "* 验证码输入错误.";
			}
		}
		
		$("#emp").click(function(){
			$("#loginType").val("emp"); 
		});
		$("#mgr").click(function(){
			$("#loginType").val("mgr"); 
		});
		
		function login(){
			var loginType = $("#loginType").val();
			if("emp" == loginType){
				empLogin();
			}
			if("mgr" == loginType){
				mgrLogin();
			}
		}
		
		function empLogin(){
			$("#messageBox1").empty();
			var success = $("#loginForm1").validationEngine("validate");
			if(!success){
				$("#messageBox1").show();
				return;
			}else{
				$("#messageBox1").hide();
			}
			$("#loginType").val("emp");
			$("#username").val($("#username1").val());
			$("#password").val($("#password1").val());
			$("#validateCode").val($("#validateCode1").val());
			var form = document.forms['loginForm'];
			form.submit();
		}
		
		function mgrLogin(){
			$("#messageBox2").empty();
			var success = $("#loginForm2").validationEngine("validate");
			if(!success){
				$("#messageBox2").show();
				return;
			}else{
				$("#messageBox2").hide();
			}
			$("#loginType").val("mgr");
			$("#username").val($("#username2").val());
			$("#password").val($("#password2").val());
			$("#validateCode").val($("#validateCode2").val());
			var form = document.forms['loginForm'];
			form.submit();
		}

// 		$('#saveInfo1').on('click',function() {
// 			$("#messageBox1").empty();
// 			var success = $("#loginForm1").validationEngine("validate");
// 			if(!success){
// 				$("#messageBox1").show();
// 				return;
// 			}else{
// 				$("#messageBox1").hide();
// 			}
// 			$("#loginType").val("emp");
// 			$("#username").val($("#username1").val());
// 			$("#password").val($("#password1").val());
// 			$("#validateCode").val($("#validateCode1").val());
// 			var form = document.forms['loginForm'];
// 			form.submit();
// 		});
		
// 		$('#saveInfo2').on('click',function() {
// 			$("#messageBox2").empty();
// 			var success = $("#loginForm2").validationEngine("validate");
// 			if(!success){
// 				$("#messageBox2").show();
// 				return;
// 			}else{
// 				$("#messageBox2").hide();
// 			}
// 			$("#loginType").val("mgr");
// 			$("#username").val($("#username2").val());
// 			$("#password").val($("#password2").val());
// 			$("#validateCode").val($("#validateCode2").val());
// 			var form = document.forms['loginForm'];
// 			form.submit();
// 		});
		
		
		
		if(self.frameElement && self.frameElement.tagName == "IFRAME" || $('#left').length > 0 || $('.jbox').length > 0){
			alert('未登录或登录超时。请重新登录，谢谢！');
			top.location = "${ctx}";
		}
	</script>
</body>
</html>