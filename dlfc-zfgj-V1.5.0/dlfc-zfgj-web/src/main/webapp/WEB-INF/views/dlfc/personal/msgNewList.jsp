<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head2.jsp"%>
<script type="text/javascript">
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		selected();
		return false;
	}
</script>
</head>
<body>
<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		站内信 <span class="c-gray en">&gt;</span>站内信列表<a
			class="btn btn-success radius r mr-20"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	</nav>
	<div class="pd-20">
		<form id="form" action="${ctx}/msg/list" method="post">
			<input id="pageNo" type="hidden" name="pageNo" value="${page.pageNo}">
			<input id="pageSize" type="hidden" name="pageSize"
				value="${page.pageSize}">
			<div class="cl pd-5 bg-1 bk-gray mt-20">
				<span class="l"><a onclick="allDelete()"
					class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i>
						批量删除</a></span><span class="select-box r inline"> <form:select
						class="select" path="msg.status" id="status" onchange="selected()"
						style="width:80px;">
						<form:option value="9">全部</form:option>
						<form:option value="1">已读</form:option>
						<form:option value="0">未读</form:option>
					</form:select>
				</span>
			</div>
			<table
				class="table table-border table-bordered table-bg table-hover table-sort">
				<thead>
					<tr class="text-c">
						<th width="25"><input type="checkbox" name="" value=""></th>
						<th width="80">ID</th>
						<th>标题</th>
						<!-- <th width="120">发信人</th> -->
						<th width="120">发布时间</th>
						<th width="180">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.list}" var="msg" varStatus="status">
						<tr class="text-c">
							<td><input type="checkbox" value="${msg.id}" name="checkboxid"
								id="${status.index+1}"></td>
							<td>${status.index+1}</td>
							<td class="text-l"><a href="${ctx}/msg/detail?id=${msg.id}&page=${page.pageNo}">
							<c:choose>
							<c:when test="${msg.status == 0}">
							<strong>${msg.title}</strong>
							</c:when>
							<c:otherwise >
							 ${msg.title} 
							</c:otherwise>
							</c:choose>
							</td>

							<%-- <td>${msg.senderName}</td> --%>
							<td><fmt:formatDate value="${msg.sendTime}"
									pattern="yyyy/MM/dd" /></td>

							<td class="f-14 td-manage"><a
								href="${ctx}/msg/detail?id=${msg.id}&page=${page.pageNo}"
								class="btn mr-10 btn-primary">查看</a> <a
								href="${ctx}/msg/del?id=${msg.id}" class="btn btn-danger">删除</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="pagination">${page}</div>
		</form>
	</div>
	<%@ include file="/WEB-INF/views/include/foot.jsp"%>
	<script type="text/javascript">

		function selected() {
			var form = document.forms['form'];
			form.submit();
		}
		
		function allDelete(){
			var idStr = "";
 			$("input[name='checkboxid']:checked").each(function () {
 				idStr = idStr + this.value +"_"
			});
			
			if(idStr==""){
				return;
			}
			
			layer.confirm(
				'确认删除所选消息吗？',
				{
					btn : [ '确认', '返回']
				//按钮
				}, function() {
					var tar = "${ctx}/msg/alldels";
					doSubmit(tar,idStr);
				}, function() {
					
				});
		};
		function doSubmit(tar,id){
			var form = document.forms['form'];
			form.action = tar;
			form['id'].value = id;
			form.submit();
		};
	</script>
</body>
</html>