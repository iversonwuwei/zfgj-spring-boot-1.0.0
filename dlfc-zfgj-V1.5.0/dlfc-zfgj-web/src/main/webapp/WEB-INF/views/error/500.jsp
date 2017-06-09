<%
response.setStatus(500);

// 获取异常类
Throwable ex = Exceptions.getThrowable(request);
if (ex != null){
	LoggerFactory.getLogger("500.jsp").error(ex.getMessage(), ex);
}

// 编译错误信息
StringBuilder sb = new StringBuilder("错误信息：\n");
if (ex != null) {
	sb.append(Exceptions.getStackTraceAsString(ex));
} else {
	sb.append("未知错误.\n\n");
}

// 如果是异步请求或是手机端，则直接返回信息
if (Servlets.isAjaxRequest(request)) {
	out.print(sb);
}

// 输出异常信息页面
else {
%>
<%@page import="org.slf4j.Logger,org.slf4j.LoggerFactory"%>
<%@page import="com.dlfc.admin.common.web.Servlets"%>
<%@page import="com.dlfc.admin.common.utils.Exceptions"%>
<%@page import="com.dlfc.admin.common.utils.StringUtils"%>
<%@page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<%@include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>500 - 系统内部错误</title>
	<%@ include file="/WEB-INF/views/include/head2.jsp"%>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		房源管理 <span class="c-gray en">&gt;</span> 新建房源<a
			class="btn btn-success radius r mr-20"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="pd-20">
		<article class="page-404 minWP text-c">
			<p class="error-title">
				<i class="Hui-iconfont va-m"></i><span class="va-m"> 500</span>
			</p>
			<p class="error-description">不好意思，服务器开小差儿了，请联系系统管理员，谢谢！~</p>
			<p class="error-info">
				您可以：<a class="c-primary" onclick="history.go(-1)" href="javascript:" target="_top">&lt; 返回上一页</a><span class="ml-20">|</span>
					 <a class="c-primary ml-20"  href="${ctx}" target="_top">去首页 &gt;</a>
			</p>
		</article>
	</div>


	<%-- <div class="container-fluid">
		<div class="page-header"><h1>系统内部错误.</h1></div>
		<div class="errorMessage">
			错误信息：<%=ex==null?"未知错误.":StringUtils.toHtml(ex.getMessage())%> <br/> <br/>
			请点击“查看详细信息”按钮，将详细错误信息发送给系统管理员，谢谢！<br/> <br/>
			<a href="javascript:" onclick="history.go(-1);" class="btn">返回上一页</a> &nbsp;
			<a href="javascript:" onclick="$('.errorMessage').toggle();" class="btn">查看详细信息</a>
		</div>
		<div class="errorMessage hide">
			<%=StringUtils.toHtml(sb.toString())%> <br/>
			<a href="javascript:" onclick="history.go(-1);" class="btn">返回上一页</a> &nbsp;
			<a href="javascript:" onclick="$('.errorMessage').toggle();" class="btn">隐藏详细信息</a>
			<br/> <br/>
		</div>
		<script>try{top.$.jBox.closeTip();}catch(e){}</script>
	</div> --%>
</body>
</html>
<%
} /* out = pageContext.pushBody(); */
%>