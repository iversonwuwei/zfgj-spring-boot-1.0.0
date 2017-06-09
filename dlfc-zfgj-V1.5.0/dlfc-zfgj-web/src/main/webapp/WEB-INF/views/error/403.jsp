<%
response.setStatus(403);

//获取异常类
Throwable ex = Exceptions.getThrowable(request);

// 如果是异步请求或是手机端，则直接返回信息
if (Servlets.isAjaxRequest(request)) {
	if (ex!=null && StringUtils.startsWith(ex.getMessage(), "msg:")){
		out.print(StringUtils.replace(ex.getMessage(), "msg:", ""));
	}else{
		out.print("操作权限不足.");
	}
}

//输出异常信息页面
else {
%>
<%@page import="com.dlfc.admin.common.web.Servlets"%>
<%@page import="com.dlfc.admin.common.utils.Exceptions"%>
<%@page import="com.dlfc.admin.common.utils.StringUtils"%>
<%@page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<%@include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>403 - 操作权限不足</title>
	<%@include file="/WEB-INF/views/include/head2.jsp" %>
</head>
<body>

	<div class="pd-20">
		<article class="page-404 minWP text-c">
			<p class="error-title">
				<i class="Hui-iconfont va-m">&#xe688;</i><span class="va-m"> 403</span>
			</p>
			<p class="error-description">操作权限不足,请重新登录。（重新登录后仍存在操作权限不足问题，请联系管理员。）</p>
<!-- 			<p class="error-info"> -->
<!-- 				您可以：<a class="c-primary" onclick="history.go(-1)" href="javascript:" target="_top">&lt; 返回上一页</a><span class="ml-20">|</span> -->
<%-- 					 <a class="c-primary ml-20"  href="${ctx}" target="_top">去首页 &gt;</a> --%>
<!-- 			</p> -->
		</article>
	</div>
</body>
</html>
<%
} out = pageContext.pushBody();
%>