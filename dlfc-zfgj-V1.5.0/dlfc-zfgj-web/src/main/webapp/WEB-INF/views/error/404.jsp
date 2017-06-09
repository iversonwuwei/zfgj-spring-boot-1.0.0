<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head2.jsp"%>
<link href="${pageContext.request.contextPath}/static/hui/lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/static/hui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/static/hui/css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>404页面</title>
</head>
<body>
<section class="page-404 minWP text-c">
  <p class="error-title"><i class="Hui-iconfont va-m" style="font-size:80px">&#xe63c;</i><span class="va-m"> 404</span></p>
  <p class="error-description">不好意思，您访问的页面不存在~</p>
  <p class="error-info">您可以：<a href="javascript:;" onclick="history.go(-1)" class="c-primary">&lt; 返回上一页</a><span class="ml-20">|</span><a href="javascript:;" onclick="history.go(-1)" class="c-primary ml-20">去首页 &gt;</a></p>
</section>
</body>
</html>