<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>用户管理</title>
<%@ include file="/WEB-INF/views/include/head2.jsp"%>
<script type="text/javascript">
	function page(n, s) {
		if (n)
			$("#pageNo").val(n);
		if (s)
			$("#pageSize").val(s);
		$("#searchForm").attr("action", "${ctx}/hr/user/list");
		$("#searchForm").submit();
		return false;
	}
</script>
</head>
<body>
<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span><i class="Hui-iconfont"></i> 人事管理 <span class="c-gray en">&gt;</span>
		管理员账户 <span class="c-gray en"></span><a
			class="btn btn-success radius r mr-20"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<form:form action="${ctx}/hr/user/list" method="post"
		class="form form-horizontal" id="searchForm" modelAttribute="user">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}"
			callback="page();" />
			<div class="form_search">
				<div class="row cl mb-20">
					<div class="form-group">
						<p class="form_text">
							<form:input path="name" htmlEscape="false" maxlength="50"
								class="input-text input-name" placeholder="按照帐号，姓名搜索" />
						</p>
						<div class="form_text">
							<a href="#" class="btn btn-primary radius"
								onclick="return page();">搜索</a>
							<shiro:hasPermission name="hr:user:edit">
								<a href="${ctx}/hr/user/form?cz=1" class="btn btn-primary radius">新增</a>
							</shiro:hasPermission>
						</div>
					</div>
				</div>
			</div>
		<div class="responsive">
			<div class="row cl">
				<div class="right_parts col-1-1">
					
<%-- 					<sys:message content="${message}" /> --%>
					<table id="contentTable"
						class="table table-border table-bordered table-hover table-bg">
						<thead>
							<tr class="text-c">
								<th width="60">编号</th>
								<th width="160">账号</th>
								<th width="120">姓名</th>
								<th width="80">职务(角色)</th>
								<th width="80">电话</th>
								<th width="100">邮箱</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.list}" var="user" varStatus="status">
								<tr class="text-c ${user.loginFlag eq 0  ?'td-disable':''}">
									<td>${status.index+1}</td>
									<td>${user.loginName}</td>
									<td>${user.name}</td>
									<td>${user.roleName}</td>
									<td>${user.mobile}</td>
									<td>${user.email}</td>
									<td>
										<c:if test="${superId ne user.id}"> 
											<shiro:hasPermission name="hr:user:edit">
												<c:if test="${user.loginFlag eq 1}">
													<a href="${ctx}/hr/user/loginFlag?id=${user.id}&loginFlag=0"
														class="btn btn-primary ml-5"><i class="Hui-iconfont">&#xe60c;</i>禁用</a>
												</c:if>
												<c:if test="${user.loginFlag eq 0}">
													<a href="${ctx}/hr/user/loginFlag?id=${user.id}&loginFlag=1"
														class="btn btn-primary ml-5"><i class="Hui-iconfont">&#xe60c;</i>启用</a>
												</c:if>
												<a href="${ctx}/hr/user/form?id=${user.id}&cz=2"
													class="btn btn-primary ml-5"><i class="Hui-iconfont">&#xe60c;</i>编辑</a>
												<c:if test="${loginId ne user.id}"> 
													<a href="#" onclick="return doDel('确认要删除该用户吗？', '${ctx}/hr/user/delete?id=${user.id}')"
														class="ml-5 btn btn-danger"><i class="Hui-iconfont">&#xe6e2;</i>删除</a>
												</c:if>
											</shiro:hasPermission>
										 </c:if> 
<%-- 										 <c:if test="${superId eq user.id}">  --%>
<%-- 										     <a href="${ctx}/hr/user/form?id=${user.id}&detail=detail" class="btn btn-primary ml-5"> --%>
<!-- 											 <i class="Hui-iconfont">&#xe60c;</i>查看</a> -->
<%-- 										 </c:if> --%>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="pagination">${page}</div>
				</div>
			</div>
		</div>
	</form:form>

<%@ include file="/WEB-INF/views/include/foot.jsp"%>
<script type="text/javascript">
function doDel(id, name) {
	layer.confirm(id, function() {
		//alert(id);
		location.href=name;
	});
}
</script>

</body>
</html>