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
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		房源管理 <span class="c-gray en">&gt;</span> 新建房源<a
			class="btn btn-success radius r mr-20"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	
	<div class="responsive">
		<div class="row cl">
			<c:if test="${msg != null && msg != '' }">
				<div class="col-offset-5 col-7">
					<p class="tips_fail">
						<i class="Hui-iconfont working_fail">&#xe6dd;</i>${msg}
					</p>
				</div>
			</c:if>
			<c:if test="${msg == null || msg == '' }">
				<div class="col-offset-5 col-7">
					<p class="tips_suess">
						<i class="Hui-iconfont working_suess">&#xe6e1;</i>房源新建成功
					</p>
				</div>
			</c:if>
		</div>
		<c:if test="${msg == null || msg == '' }">
		<div class="row cl">
			<div class="col-offset-5 col-7">
				请等待客服审核，审核周期为 <strong class="c-orange">1</strong> 个工作日
			</div>
		</div>
		</c:if>
	</div>
	<div class="responsive">
		<div class="row cl btn_group">
			<div class="col-7 col-offset-5">
				<a class="btn btn-primary radius mr-10" href="${ctx}/house/list">全部房源</a> 
				<c:if test="${msg == null || msg == '' }">
					<a href="${ctx}/lease/toadd?id=${hid}" class="btn btn-primary radius">发布出租信息</a>
				</c:if>
			</div>
		</div>
	</div>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/jquery/1.9.1/jquery.min.js"></script>
	<%@ include file="/WEB-INF/views/include/foot.jsp"%>
</body>
</html>