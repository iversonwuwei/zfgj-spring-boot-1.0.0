<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/head2.jsp"%>
<div class="list_head cl">
	<div class="house-list-tab cl">
	<c:choose>
		<c:when test="${role == 0}">
			<a href="#" onclick="changRole(0)"><span class="current">全部</span></a>
			<a href="#" onclick="changRole(1)"><span>经理</span></a>
			<a href="#" onclick="changRole(2)"><span>经纪人</span></a>
		</c:when>
		<c:when test="${role == 1}">
			<a href="#" onclick="changRole(0)"><span >全部</span></a>
			<a href="#" onclick="changRole(1)"><span class="current">经理</span></a>
			<a href="#" onclick="changRole(2)"><span>经纪人</span></a>
		</c:when>
		<c:when test="${role == 2}">
			<a href="#" onclick="changRole(0)"><span>全部</span></a>
			<a href="#" onclick="changRole(1)"><span>经理</span></a>
			<a href="#" onclick="changRole(2)"><span class="current">经纪人</span></a>
		</c:when>
	</c:choose>
	</div>
</div>
<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width="60">编号</th>
				<th width="100">姓名</th>
				<th width="160">电话号</th>
				<th width="250">部门</th>
				<th width="120">角色</th>
				<th width="150">
					<c:if test="${status == '0'}">
						入职时间
					</c:if>
					<c:if test="${status == '1'}">
						离职时间
					</c:if>
				</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="empInfo" varStatus="status">
			<tr class="text-c">
				<td>${status.index+1}</td>
				<td><a class="btn-link" href="${ctx}/agt/detail?id=${empInfo.id}">${empInfo.name}</a></td>
				<td>${empInfo.mobile}</td>
				<td>${empInfo.officename}</td>
				<td>${empInfo.rolename}</td>
				<td>
					<fmt:formatDate value="${empInfo.statustime}" pattern="yyyy-MM-dd"/> 
				</td>
				<td>
					<c:if test="${empInfo.status == '0'}">
						<shiro:hasPermission name="hr:emp:del">
							<a onclick="doQuit('${empInfo.id}','${empInfo.name}')" href="javascript:void(0);" class="btn btn-danger ml-5"><i class="Hui-iconfont">&#xe631;</i>离职</a>
						</shiro:hasPermission>
						<shiro:hasPermission name="hr:emp:edit">
							<a href="${ctx}/agt/detail?id=${empInfo.id}&edit=1" class="btn btn-primary ml-5"><i class="Hui-iconfont">&#xe60c;</i>编辑</a>
						</shiro:hasPermission>
					</c:if>
					<c:if test="${empInfo.status == '1'}">
						<a href="${ctx}/agt/detail?id=${empInfo.id}" class="btn btn-default ml-5"><i class="Hui-iconfont">&#xe665;</i>查看</a>
						<c:choose>
							<c:when test="${empInfo.agtCusInfoCount == 0 and empInfo.houHouseInfoCount == 0}">
								<a href="#" class="btn disabled ml-5"></i>已移交</a>								
							</c:when>
							<c:otherwise>
								<a href="#" class="btn btn-primary ml-5" onclick="transfer('${empInfo.id}','${empInfo.companyId}');"></i>移&nbsp;&nbsp;&nbsp;交</a>
							</c:otherwise>
						</c:choose>
					</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<c:if test="${page.list.size() gt 0 }">
		<div class="pagination">${page}</div>
	</c:if>
	<c:if test="${page.list.size() eq 0 }">
		<p class="pd-20 text-c f-18">无任何查询结果！</p>
	</c:if>
</div>