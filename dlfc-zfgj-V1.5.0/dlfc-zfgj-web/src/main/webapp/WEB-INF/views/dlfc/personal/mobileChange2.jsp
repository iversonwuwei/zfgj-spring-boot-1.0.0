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
						<form action="" method="post" name="form" id="form">
							<div class="phone_change_step phone_change_step_2"></div>

							<div class="item border_none">
								<div class="form_list col_1_1 fn_clear">
									<p class="form_name">新手机号：</p>
									<input type="text" name="mobilen" id="mobilen"
										placeholder="请输入新手机号" value=""
										class="text validate[required,custom[phone]]" />
								</div>
							</div>
							<div class="item border_none">
								<div class="form_list col_1_1 fn_clear">
									<p class="form_name">验证码：</p>
									<p class="form_text">
										<input type="text" name="idcode" id="idcode"
											placeholder="请输入密码" class="text_small validate[required]"
											value="" />
									</p>
									<p class="form_text">
										<a id="getIdcode" onclick="getIdcode('${ctx}');" class="btn btn_enter">获取验证码</a><a
											id="waitNextIdcode" class="btn btn_finish"
											style="display: none;">重新获取</a>
									</p>
								</div>
							</div>
							<div class="item mt20 border_none">
								<div class="form_list col_1_1 fn_clear">
									<p class="form_name">&nbsp;</p>
									<a onclick="doMobileChange();" class="btn btn_enter">提交</a>
								</div>
							</div>

							<div class="change_tips">
								<p class="bold">
									<i class="icos ico_point"></i>更换手机遇到问题？
								</p>
								<p class="pl40">1、请认真填写手机信息，避免填写错误。</p>
								<p class="pl40">
									2、如新手机号已被他人注册了账户，请拨打大连市房屋租赁公共服务平台客服热线 <span class="bold txt_red">400-87304847</span>
								</p>




							</div>
						</form>
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
		var minput = "mobilen";
		$(document).ready(function() {
			$('#form').validationEngine();
			$('#highLight_as').addClass("active");
		});

		function doMobileChange() {
				var idcode = $("#idcode").val();
				var mobilen = $("#mobilen").val();
				$.ajax({
					type : "POST",
					url : 'mobilec',
					data : {
						idcode : idcode,
						mobilen : mobilen,
						tm : new Date().getTime()
					},
					dataType : 'json',
					cache : false,
					success : function(data) {
						if (1 == data.success) {
							layer.alert(data.message,function(){
								window.location.href = "${ctx}/my/asecu";
							});

						} else {
							layer.alert(data.message);

						}
					},
					error : function() {
						layer.alert("网络异常，请稍后重试！");
					}
				});
		}
	</script>

</body>
</html>