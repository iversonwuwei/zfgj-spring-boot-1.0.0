<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>	
<%
	String path = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="${ctxStatic}${theme }lib/html5.js"></script>
<script type="text/javascript" src="${ctxStatic}${theme }lib/respond.min.js"></script>
<script type="text/javascript" src="${ctxStatic}${theme }lib/PIE-2.0beta1/PIE_IE678.js"></script>
<![endif]-->
<link
	href="${pageContext.request.contextPath}/static/hui/css/H-ui.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/static/hui/css/H-ui.admin.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/static/hui/css/style.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/static/hui/lib/icheck/icheck.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/static/hui/lib/Hui-iconfont/1.0.1/iconfont.css"
	rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>添加管理员</title>
</head>
<body>
<div>
<div class="info_box fn_clear">

				<div class="user_box">
					<form action="" method="post" name="form" id="form">
						<input type="hidden" name="username" id="username"
							value="${theuser.username}" />
						<div class="user_title">变更邮箱</div>



						<div class="form_box border_none" id="submit">
							<div class="form_list col_1_1 fn_clear">
								<p class="form_name">
									<i class="icos ico_smile"></i>
								</p>
								<p class="form_text">出于安全考虑，15分钟后才能进行下一次邮箱变更哦！</p>
							</div>
							<div class="item border_none">
								<div class="form_list col_1_1 fn_clear">
									<p class="form_name">登录密码：</p>
									<input type="password" name="password" id="password"
										placeholder="请输入密码" value="" class="text validate[required]" />
								</div>
							</div>
							<div class="item border_none">
								<div class="form_list col_1_1 fn_clear">
									<p class="form_name">旧邮箱：</p>
									<input type="text" name="emailo" id="emailo"
										class="text_minlarge validate[required,custom[email]]"
										placeholder="请输入旧邮箱" />
								</div>
							</div>
							<div class="item border_none">
								<div class="form_list col_1_1 fn_clear">
									<p class="form_name">新邮箱：</p>
									<input type="text" name="email" id="email"
										class="text_minlarge validate[required,custom[email],funcCall[notEqualTo[emailo]]]"
										placeholder="请输入新邮箱" />
								</div>
							</div>
							<div class="item border_none">
								<div class="form_list col_1_1 fn_clear">
									<p class="form_name">&nbsp;</p>
									<a onclick="doMailChange();" class="btn btn_enter"
										id="to-recover">立即绑定</a>
								</div>
							</div>
						</div>

					</form>
					<div class="form_box border_none" style="display: none" id="auth">
						<div class="item border_none">
							<div class="form_list col_1_1 fn_clear">
								<p class="form_name">
									<i class="icos ico_smile"></i>
								</p>
								<p class="form_text binding_sucess">信息保存成功</p>
							</div>

							<div class="form_list col_1_1 fn_clear">
								<p class="form_name">&nbsp;</p>
								<p class="form_text">已向您的邮箱发送了一封邮件，请在24小时内完成验证</p>
							</div>

						</div>

						<div class="item border_none">
							<div class="form_list col_1_1 fn_clear">
								<p class="form_name">&nbsp;</p>
								<a onclick="doAuth();" class="btn btn_enter" id="to-recover">立即验证</a>
							</div>
						</div>
					</div>
				</div>




			</div>
	</div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/icheck/jquery.icheck.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/Validform/5.3.2/Validform.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/layer/1.9.3/layer.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/js/H-ui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/js/H-ui.admin.js"></script>
<script type="text/javascript">
		$("#auth").hide();

		$(document).ready(function() {
			$('#form').validationEngine();
			$('#highLight_as').addClass("active");
		});


		function notEqualTo(field, rules, i, options) {
			if (field.val() == $("#emailo").val())
				return "请输入与上面不同的邮箱";

		};

		function doMailChange() {
				var username = $("#username").val();
				var password = $("#password").val();
				var emailo = $("#emailo").val();
				var email = $("#email").val();
				$.ajax({
					type : "POST",
					url : 'mc',
					data : {
						username : username,
						password : password,
						email : email,
						emailo : emailo,
						tm : new Date().getTime()
					},
					dataType : 'json',
					cache : false,
					success : function(data) {
						if (1 == data.success) {

							$("#submit").hide();
							$("#auth").show();
						} else {
							layer.alert(data.message);

						}
					},
					error : function() {
						layer.alert("网络异常，请稍后重试！");
					}
				});
		}

		function doAuth() {
			window.open("http://mail." + $("#email").val().split('@')[1]);
		}
	</script>
</body>
</html>