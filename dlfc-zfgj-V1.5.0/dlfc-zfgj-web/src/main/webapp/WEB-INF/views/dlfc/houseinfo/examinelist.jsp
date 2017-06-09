<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<c:set var="ctxFront" value="${fns:getSiteUrl()}${fns:getFrontPath()}"/>
<!DOCTYPE HTML>
<html>
<head>

<%@ include file="/WEB-INF/views/include/head2.jsp"%>
<link href="${pageContext.request.contextPath}/static/hui/lib/bootstrap-Switch/bootstrapSwitch.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript">
function page(n,s){
	$("#pageNo").val(n);
	$("#pageSize").val(s);
	doSearch();
	return false;
}

</script>
<title>添加管理员</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		我的任务 <span class="c-gray en">&gt;</span>验房预约<a
			class="btn btn-success radius r mr-20"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="content">
		<div class="mt-20">
			<legend>验房预约</legend>
			<div class="list_sort cl">
				<c:if test="${bs == null || bs ==  '' || bs == 'none'}">
					<a id="allsort" href="javascript:void(0);" title="无排序" onclick="doSort('butimesort', 'desc')">添加时间 <span> &uarr;</span></a>
				</c:if>
				<c:if test="${bs == 'desc'}">
					<a id="upsort" href="javascript:void(0);" title="降序" onclick="doSort('butimesort', 'asc')" class="active">添加时间 <span> &darr;</span></a>
				</c:if>
				<c:if test="${bs == 'asc'}">
					<a id="downsort" href="javascript:void(0);" title="升序" onclick="doSort('butimesort', 'none')" class="active">添加时间<span> &uarr;</span></a>
				</c:if>
				
				<c:if test="${ss == null || ss ==  '' || ss == 'none'}">
					<a id="allsort" href="javascript:void(0);" title="无排序" onclick="doSort('signtimesort', 'desc')">预约时间 <span> &uarr;</span></a>
				</c:if>
				<c:if test="${ss == 'desc'}">
					<a id="upsort" href="javascript:void(0);" title="降序" onclick="doSort('signtimesort', 'asc')" class="active">预约时间 <span> &darr;</span></a>
				</c:if>
				<c:if test="${ss == 'asc'}">
					<a id="downsort" href="javascript:void(0);" title="升序" onclick="doSort('signtimesort', 'none')" class="active">预约时间<span> &uarr;</span></a>
				</c:if>
			</div>
			
			<form name="entForm" action="${ctx}/exa/list">
				<input id= "st" type="hidden" name="st" value="${st}">
				<input id="butimesort" type="hidden" name="bs" value="${bs}">
				<input id="signtimesort" type="hidden" name="ss" value="${ss}">
				<input id="pageNo" type="hidden" name="pageNo" value="${page.pageNo}">
				<input id="pageSize" type="hidden" name="pageSize" value="${page.pageSize}">
				<input type="hidden" name="currentPage" value="">
			</form>
			
			<!-- <div class="cl pd-5 bg-1 bk-gray mt-20">
				<span class="l"><a href="javascript:;" onclick="datadel()"
					class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i>
						删除</a></span>
			</div> -->
			<table class="table table-border table-bordered  table-bg">
				<thead>
					<tr class="text-c">
						<th width="140">小区信息</th>
						<th width="110">联系方式</th>
						<th width="110">客户姓名</th>
						<th width="110">预约时间</th>
						<th width="100">操作</th>
					</tr>
				</thead>
			</table>
				<c:forEach items="${page.list}" var="info">
			<table class="table table-border table-bordered  table-bg  mb-20"">
				<tbody>
					<tr class="text-l">
						<td colspan="5" class="hoser_info"><div class="row cl">
								<div class="col-3"><fmt:formatDate value="${info.publishTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
							</div></td>
					</tr>
					<tr class="text-c">
						<td width="140"><p class="house_name">
								${info.community}
							</p></td>
						<td width="110">${info.mobile}</td>
						<td width="110">${info.name}</td>
						<td width="110">
							<p><fmt:formatDate value="${info.examineTime}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
							<!-- 小于一小时：即将到期 -->
							<c:if test="${info.time > 0 && info.time < 3600 }">
								<span class="label label-warning mr-5">将到期</span>
							</c:if>
							<c:if test="${info.time < 0 }">
								<span class="label label-default mr-5">已过期</span>
							</c:if>
							
						</td>
						<td class="text-c" width="100">
							<c:choose>
								<c:when test="${info.status == 1}">
									<a href="${ctx}/house/toadd?per_id=${info.perId}&ent_id=${info.entId}" class="btn btn-primary radius">发布房源</a>
								</c:when>
								<c:otherwise>
									<a href="#" class="btn btn-default disabled">发布房源</a>
								</c:otherwise>
							</c:choose>
							<!-- 验房预约的记录在“已过期”的状态可以删除，其他状态不可删除。 -->
							<c:if test="${info.time < 0 }">
								<a class="btn btn-danger radius" href="${ctx}/exa/del?id=${info.id}">删除</a></td>
							</c:if>
					</tr>
					<tr>
						<td colspan="5" class="tab_fold"><ul id="Huifold1"
								class="Huifold">
								<li class="item cl">
									<h4>
										<b class="Hui-iconfont">&#xe600;</b>查看备注信息
									</h4>
									<div class="info">
										<div class="responsive">
											<!-- <div class="row cl">
												<div class="col-3 f-16">
													<strong>锦绣园 18号楼 3楼3号 </strong><span class="label label-primary">产权</span>
												</div>
											</div> -->
											<!-- <div class="row cl">
												<div class="col-3 col-offset-2">备注信息：</div>
											</div> -->
											<div class="row cl">
												<div class="col-6 col-offset-2">
													<textarea class="textarea" placeholder="说点什么..." rows="" cols="" name="" >${info.comment}</textarea>
												</div>
											</div>
											<div class="row cl">
												<div class="col-6 col-offset-2">
													<a onclick="updateComment('${info.id}',this);" class="btn btn-default" style="display: inline;">修改</a>
												</div>
											</div>
										</div>
									</div>
								</li>
							</ul></td>
					</tr>
				</tbody>
			</table>
				</c:forEach>
			<div class="pagination">${page}</div>
		</div>
	</div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/laypage/1.2/laypage.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/bootstrap-Switch/bootstrapSwitch.js"></script>
	<%@ include file="/WEB-INF/views/include/foot.jsp"%>
	<script type="text/javascript">
		$(function() {
			$('.table-sort').dataTable({
				"aaSorting" : [ [ 1, "desc" ] ],//默认第几个排序
				"bStateSave" : true,//状态保存
				"aoColumnDefs" : [
				//{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
				{
					"orderable" : false,
					"aTargets" : [ 0, 8, 9 ]
				} // 制定列不参与排序
				]
			});
			$('.table-sort tbody').on('click', 'tr', function() {
				if ($(this).hasClass('selected')) {
					$(this).removeClass('selected');
				} else {
					table.$('tr.selected').removeClass('selected');
					$(this).addClass('selected');
				}
			});
			$.Huifold("#Huifold1 .item h4", "#Huifold1 .item .info", "fast", 1,
					"click");
		});

		/*用户-还原*/
		function member_huanyuan(obj, id) {
			layer.confirm('确认要还原吗？', function(index) {

				$(obj).remove();
				layer.msg('已还原!', {
					icon : 6,
					time : 1000
				});
			});
		}

		/*用户-删除*/
		function member_del(obj, id) {
			layer.confirm('确认要删除吗？', function(index) {
				$(obj).parents("tr").remove();
				layer.msg('已删除!', {
					icon : 1,
					time : 1000
				});
			});
		};
		
		//排序
		function doSort(prop, sort) {
			$("#" + prop).val(sort);
			doSearch();
		};
		
		function doSearch() {
			var form = document.forms['entForm'];
			form.submit();
		};
		
		//修改备注
		function updateComment(id,it){
			var com = $(it).parent().parent().prev().find(".textarea").val();
			
			$.ajax({
				type: "GET",
				url: '${ctx}/exa/updc',
				data: {
					id: id,
					comment:com
				},
				dataType:'json',
				cache: false,
				async : false, 
				success: function(data){
					layer.msg("修改成功！");
				}
			}); 
		};
	</script>
</body>
</html>