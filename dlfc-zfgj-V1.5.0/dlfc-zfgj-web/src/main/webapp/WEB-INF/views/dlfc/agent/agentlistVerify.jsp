<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head2.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->

<script type="text/javascript">
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$('#loginForm').submit();
		return false;
	}
</script>
<title>经纪人审核</title>
</head>
<body>
<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span><i class="Hui-iconfont"></i> 人事管理 <span class="c-gray en">&gt;</span>
		经纪人审核 <span class="c-gray en"></span><a
			class="btn btn-success radius r mr-20"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="pd-20">
		<form id="loginForm" class="form-signin  form-horizontal" action="${ctx}/agt/agentlistVerify" method="post">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" /> 
			<div class="row mb-20 cl">
				<div class="formControl l">
					<input id="input" name="input" class="input-text" type="text" style="width: 300px;" 
						placeholder="按照姓名，电话搜索" value="${input}">
				</div>
				<div class="formControl l">
					<input class="btn btn-primary radius ml-15" type="submit" value="搜索" />
				</div>
			</div>
			<div id="content">
				<table
					class="table table-border table-bordered table-hover table-bg table-sort">
					<thead>
						<tr class="text-c">
							<th width="60">编号</th>
							<th width="100">申请时间</th>
							<th width="100">姓名</th>
							<th width="160">电话</th>
							<th width="160">身份证</th>
							<th width="160">经纪人证件</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.list}" var="agent" varStatus="status">
							<tr class="text-c">
								<td>${status.index+1}</td>
								<td><fmt:formatDate value="${agent.createTime}" pattern="yyyy-MM-dd" /></td>
								<td>${agent.sysPerson.name}</td>
								<td>${agent.usrUser.mobile}</td>
								<td>${agent.sysPerson.idNo}</td>
								<td>${agent.agtCertLink.certCardNo}
								<a class="agt-card agt-card-on"><i class="Hui-iconfont">&#xe613;</i><span><img src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/img/${agent.fImg}?key=${accessKey}"></img></span></a>
								<a class="agt-card agt-card-off"><i class="Hui-iconfont">&#xe613;</i><span><img src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/img/${agent.sImg}?key=${accessKey}"></img></span></a>
								<%-- <img src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/img/${agent.fImg}?key=${accessKey}"></img> --%>
								</td>
								<td>
									<shiro:hasPermission name="hr:emp:verify">
										<a href="${ctx}/agt/verifydetail?re=0&ids=${agent.id}"
											class="btn btn-primary ml-5"><i class="Hui-iconfont">&#xe60c;</i>登记</a>
										<a href="${ctx}/agt/verifydetail?re=1&ids=${agent.id}"
											class="btn btn-primary ml-5"><i class="Hui-iconfont">&#xe60c;</i>拒绝</a>
									</shiro:hasPermission>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="pagination">${page}</div>
		</form>
	<%@ include file="/WEB-INF/views/include/foot.jsp"%>
	</div>
	<script type="text/javascript">
function over(){
    var div = $('#img');
    div.show();
}
 
function out(){
    var div = $('#img');
    div.hide();
}
</script>
</body>
</html>