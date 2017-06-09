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
					<div class="user_title">更换手机</div>

					<div class="form_box border_none">
						<form action="" method="post" name="form" id="form"
							id="mobileChangeForm">
							<div class="phone_change_step phone_change_step_1"></div>

							<div class="item border_none" id="selectT">
								<div class="form_list col_1_1 fn_clear">
									<p class="form_name">选择验证身份的方式：</p>
									<span class="select_form ml35"> <select id="authtype"
										class="select">
											<option value="0">手机号码</option>
											<option value="1">邮箱</option>
									</select>
									</span>

								</div>
							</div>
							<div id="authtype_0">
								
								<div class="item border_none">
									<div class="form_list col_1_1 fn_clear">
										<p class="form_name">手机号码：</p>
										<p class="form_text">${theuser.mobile}</p>
									</div>
								</div>
								<div class="item border_none">
									<div class="form_list col_1_1 fn_clear">
										<p class="form_name">验证码：</p>
										<p class="form_text">
											<input type="text" name="idcode" id="idcode"
												placeholder="请输入验证码" value=""
												class="text_small validate[required]" />
										</p>
										<p class="form_text">
											<a id="getIdcode" onclick="getIdcode('${ctx}');"
												class="btn btn_enter">获取验证码</a><a id="waitNextIdcode"
												class="btn btn_finish" style="display: none;">重新获取</a>
										</p>
									</div>
								</div>
								<div class="item border_none">
									<div class="form_list col_1_1 fn_clear">
										<p class="form_name">当前密码：</p>
										<p class="form_text">
											<input type="password" name="password" id="password"
												placeholder="请输入密码" value="" class="text validate[required]" />
										</p>
									</div>
								</div>
								<div class="item border_none">
									<div class="form_list col_1_1 fn_clear">
										<p class="form_name">&nbsp;</p>
										<a onclick="doMobileAuth();" class="btn btn_enter">下一步</a>
									</div>
								</div>
							</div>
							<div id="authtype_1" style="display: none;">
								
								<div class="item border_none">
									<div class="form_list col_1_1 fn_clear">
										<p class="form_name">邮箱地址：</p>
										<p class="form_text">${theuser.email}</p>
									</div>
									<div class="item mt20 border_none" id="submit">
										<div class="form_list col_1_1 fn_clear">
											<p class="form_name">&nbsp;</p>
											<a onclick="doMailAuth();" class="btn btn_enter">发送验证邮件</a>
										</div>
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
	</div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/icheck/jquery.icheck.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/Validform/5.3.2/Validform.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/layer/1.9.3/layer.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/js/H-ui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/js/idcode.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/js/H-ui.admin.js"></script>
<script type="text/javascript">
		$(document).ready(function() {
			if ($("#authtype").children('option:selected').val() == 1) {
				$("#authtype_" + 1).show();
				$("#authtype_" + 0).hide();
			} else if ($("#authtype").children('option:selected').val() == 0) {
				$("#authtype_" + 0).show();
				$("#authtype_" + 1).hide();
			}

		});
		$("#authtype").change(function() {

			if ($(this).children('option:selected').val() == 1) {
				$("#authtype_" + 1).show();
				$("#authtype_" + 0).hide();
			} else if ($(this).children('option:selected').val() == 0) {
				$("#authtype_" + 0).show();
				$("#authtype_" + 1).hide();
			}

		});

		$(document).ready(function() {
			$('#form').validationEngine();
			$('#highLight_as').addClass("active");
		});
		var minput = "mobile";
		function doMobileAuth() {
				var idcode = $("#idcode").val();
				var password = $("#password").val();
				$.ajax({
					type : "POST",
					url : 'mobileauth',
					data : {
						idcode : idcode,
						password : password,
						tm : new Date().getTime()
					},
					dataType : 'json',
					cache : false,
					success : function(data) {
						if (1 == data.success) {
							//saveCookie();
							window.location.href = "mobilec2?t="
									+ data.data.t;
						} else {
							layer.alert(data.message);

						}
					},
					error : function() {
						layer.alert("网络异常，请稍后重试！");
					}
				});
		}

		function doMailAuth() {
			$.ajax({
				type : "POST",
				url : 'domailauth',
				data : {
					tm : new Date().getTime()
				},
				dataType : 'json',
				cache : false,
				success : function(data) {
					if (1 == data.success) {
						$("#auth").show();
						$("#selectT").hide();
						$("#submit").hide();
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
			window.open("http://mail." + "${theuser.email}".split('@')[1]);
		}
	</script>

</body>
</html>