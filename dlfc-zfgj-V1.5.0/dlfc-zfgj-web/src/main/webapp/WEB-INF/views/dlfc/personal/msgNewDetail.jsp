<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head2.jsp"%>
<script type='text/javascript' src='/dwr/engine.js'></script>
<script type="text/javascript" src='/dwr/util.js'></script>
<script type="text/javascript" src='/dwr/interface/msgCtl.js'></script>
<script type="text/javascript">
	$(document).ready(function() {
		//这个方法用来启动该页面的ReverseAjax功能
		dwr.engine.setActiveReverseAjax(true);
		//设置在页面关闭时，通知服务端销毁会话       
		dwr.engine.setNotifyServerOnPageUnload(true);
		//设置DWR调用服务出错时，不打印(alert)调试信息    
		dwr.engine.setErrorHandler(function() {
		});

		
		parent.Check();
	})
	
</script>
</head>
<body>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal"
			id="form-member-add">
			<div class="panel panel-default">
				<div class="panel-header">站内信</div>
				<div class="panel-body">
					<table class="table table-border table-bordered">
						<input type="hidden" id="msgId" value="${msgDetail.id}" />
						<tbody>
							<tr>
								<td class="text-r" width="120">文章标题：</td>
								<td>${msgDetail.title}</td>
							</tr>
							<tr>
								<td class="text-r">发信时间：</td>
								<td><fmt:formatDate value="${msgDetail.sendTime}"
										pattern="yyyy-MM-dd HH:mm:ss" /></td>
							</tr>
							<%-- <tr>
								<td class="text-r">发信人：</td>
								<td>${msgDetail.senderName}</td>
							</tr> --%>
							<tr>

								<td colspan="2">${msgDetail.content}</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="panel-footer">
					<c:if test="${flg != 'end' }">
					<a href="${ctx}/msg/up?pindex=${msgDetail.pindex}&id=${msgDetail.id}" class="r">上一封</a>
					</c:if>
					<c:if test="${flg != 'dend' }">
					<a href="${ctx}/msg/down?pindex=${msgDetail.pindex}&id=${msgDetail.id}" class="r mr-5">下一封</a>
					</c:if>
					共<strong class="c-red">${msgDetail.count}</strong>封信，
					<strong class="c-red">${msgDetail.statusCount}</strong>封未读

					 <a href="${ctx}/msg/list?pageNo=${msgDetail.currentPage}&status=${mstatus}">返回上一页</a>
				</div>
			</div>
		</form>
	</div>
	<%@ include file="/WEB-INF/views/include/foot.jsp"%>
</body>
</html>