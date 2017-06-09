<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>角色管理</title>
<%@ include file="/WEB-INF/views/include/head2.jsp"%>
<script type="text/javascript">
	function page(n, s) {
		if (n)
			$("#pageNo").val(n);
		if (s)
			$("#pageSize").val(s);
		$("#searchForm").attr("action", "${ctx}/hr/role/list");
		$("#searchForm").submit();
		return false;
	}
</script>
</head>
<body>
<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		角色管理 <span class="c-gray en"></span><a
			class="btn btn-success radius r mr-20"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>

	<div class="responsive pd-20">
		<div class="row cl">

			<div class="right_parts col-1-1">
				<sys:message content="${message}" />
				<form:form action="${ctx}/hr/role/list" method="post"
					class="form form-horizontal" id="searchForm" modelAttribute="role">
					<input id="pageNo" name="pageNo" type="hidden"
						value="${page.pageNo}" />
					<input id="pageSize" name="pageSize" type="hidden"
						value="${page.pageSize}" />
					<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}"
						callback="page();" />
				</form:form>
<%-- 				<shiro:hasPermission name="hr:user:add"> --%>
<!-- 					<div class="cl pd-5 bg-1 bk-gray mt-20"> -->
<%-- 						<a href="${ctx}/hr/role/form" class="btn btn-primary radius"><i --%>
<!-- 							class="Hui-iconfont">&#xe600;</i>添加角色</a> -->
<!-- 					</div> -->
<%-- 				</shiro:hasPermission> --%>

				<table id="contentTable"
					class="table table-border table-bordered table-hover table-bg">
					<thead>
						<tr class="text-c">
							<th width="60">编号</th>
							<th width="160">角色名称</th>
							<th width="120">角色类别</th>
							<th width="80">创建日期</th>
							<th width="80">添加类型</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${roleList}" var="role" varStatus="status">
							<tr class="text-c ${role.sysData==0 && role.useable==0 ?'td-disable':''} " >
								<td>${status.index+1}</td>
								<td>${role.name}</td>
								<td><c:if test="${role.roleType=='emp-role'}">经纪人</c:if> <c:if
										test="${role.roleType=='comp-role'}">管理员</c:if></td>
								<td><fmt:formatDate value="${role.createDate}"
										pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td><c:if test="${role.sysData==1}">系统内置</c:if> <c:if
										test="${role.sysData==0}">自定义</c:if></td>
									<td>
									<c:if test="${role.sysData==0}">
										<shiro:hasPermission name="hr:role:edit">
											<c:if test="${role.useable==1}">
												<a href="${ctx}/hr/role/useing?id=${role.id}"
													class="btn btn-primary ml-5"><i class="Hui-iconfont">&#xe60c;</i>禁用</a>
											</c:if>
											<c:if test="${role.useable==0}">
												<a href="${ctx}/hr/role/useing?id=${role.id}"
													class="btn btn-primary ml-5"><i class="Hui-iconfont">&#xe60c;</i>启用</a>
											</c:if>
											<c:if test="${role.roleType=='emp-role'}">
												<a href="${ctx}/hr/role/form?id=${role.id}"
													class="btn btn-primary ml-5">
													<i class="Hui-iconfont">&#xe60c;</i>编辑</a>
											</c:if>
											<c:if test="${role.roleType=='comp-role'}">
												<a href="${ctx}/hr/role/mgrForm?id=${role.id}"
													class="btn btn-primary ml-5">
													<i class="Hui-iconfont">&#xe60c;</i>编辑</a>
											</c:if>
										</shiro:hasPermission>
										<shiro:hasPermission name="hr:role:del">
											<a href="#" onclick="return doDel('确认要删除该角色吗？', '${ctx}/hr/role/delete?id=${role.id}')"
												class="ml-5 btn btn-danger"><i class="Hui-iconfont">&#xe6e2;</i>删除</a>
										</shiro:hasPermission>
									</c:if> <c:if test="${role.sysData==1}">
											<c:if test="${role.roleType=='comp-role'}"> 
												<a href="${ctx}/hr/role/mgrForm?id=${role.id}&detail=1"
													class="btn btn-primary ml-5">
													<i class="Hui-iconfont">&#xe60c;</i>查看</a>
											 </c:if> 
											 <c:if test="${role.roleType=='emp-role'}"> 
												<a href="${ctx}/hr/role/form?id=${role.id}&detail=1"
													class="btn btn-primary ml-5">
													<i class="Hui-iconfont">&#xe60c;</i>查看</a>
											 </c:if> 
											
										</c:if>
										</td>
							</tr>
						</c:forEach>


					</tbody>
				</table>

			</div>
		</div>
	</div>


	<%@ include file="/WEB-INF/views/include/foot.jsp"%>
	<script type="text/javascript">
		function doDel(id, name) {
			layer.confirm(id, function() {
				//alert(id);
				location.href = name;
			});
		}
	</script>
</body>
</html>