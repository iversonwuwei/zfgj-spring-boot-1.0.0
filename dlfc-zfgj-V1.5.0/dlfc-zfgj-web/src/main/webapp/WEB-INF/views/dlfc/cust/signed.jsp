<%-- ﻿﻿<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<!DOCTYPE HTML>
<html>
<head>

<%@ include file="/WEB-INF/views/include/head2.jsp"%>
<script type="text/javascript">
function page(n,s){
	$("#pageNo").val(n);
	$("#pageSize").val(s);
	doSearch();
	return false;
}

</script>
<title>删除的用户</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		租客管理<span class="c-gray en">&gt;</span>租客列表<a
			class="btn btn-success radius r mr-20"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="content">
		<div class="mt-20">
			<legend>客户列表</legend>
			<div class="list_tabs cl ">
				<a id="0" href="${ctx}/cust/list?st=" <c:if test="${st == null || st == '' }">class="active"</c:if> >待签</a>
				<a id="1" href="${ctx}/cust/list?st=1" <c:if test="${st == 1}">class="active"</c:if>>已签</a>
			</div>
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
			 <c:choose>
				<c:when test="${eSort == null || eSort ==  '' || eSort == 'none'}">
					<a id="allsort" href="javascript:void(0);" title="升序 " onclick="doSort('eSort', 'asc')"  >到期时间 <span>&uarr;</span></a>
				</c:when>
				<c:when test="${eSort == 'asc'}">
					<a id="upsort" href="javascript:void(0);" title="降序" onclick="doSort('eSort', 'desc')" class="active">到期时间 <span>&darr;</span></a>
				</c:when>
				<c:when test="${eSort == 'desc'}">
					<a id="downsort" href="javascript:void(0);" title="无排序" onclick="doSort('eSort', 'none')" class="active">到期时间 <span>&uarr;</span></a>
				</c:when>
			</c:choose>
			</div>
			<form name="custinfolist" action="${ctx}/cust/list">
				<input type="hidden" name="st" value="${st}">
				<input id="butimesort" type="hidden" name="bs" value="${bs}">
				<input type="hidden" id="eSort" name="eSort" value="${eSort}"/>
				<input id="pageNo" type="hidden" name="pageNo" value="${page.pageNo}">
				<input id="pageSize" type="hidden" name="pageSize" value="${page.pageSize}">
				<input type="hidden" name="currentPage" value="">
			</form>
			
			<form name="submitForm" action="${ctx}/cust/edit"  method="post">
				<input id="custid" type="hidden" name="custid" value="">
				<input id="require" type="hidden" name="require" value="">
				<input id="repage" type="hidden" name="repage" value="1">
			</form>
			
			<div class="cl pd-5 bg-1 bk-gray mt-20">
				<shiro:hasPermission name="emp:cust:del">
				<span class="l">
					<a href="javascript:;" onclick= "datadel('${cust.id}');" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i>删除</a>
				</span>
				</shiro:hasPermission>
			</div>
			<table class="table table-border table-bordered  table-bg">
				<thead>
					<tr class="text-c">
						<th width="55"><input type="checkbox" name="" value=""></th>
						<th width="120">客源详情</th>
						<th width="300">合同房源</th>
						<th width="110">合同时间</th>
						<th width="100">操作</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${page.list}" var="cust">
					<tr class="text-c">
						<td><input type="checkbox" value="${cust.id}" name="checkboxid"></td>
						<td><p class="house_name">
								 ${cust.name}<span class="label label-primary">${cust.resourceName}</span>
							</p>
							<p class="text-l pt-5">${cust.mobile}</p></td>
							
						<td>
							<p>	
								<c:if test="${cust.monthlyRent!=''&&cust.monthlyRent!=null}">
								<strong class="c-orange">${cust.monthlyRent}</strong> 元/月
								
								</c:if>
							</p>
							<p>
								${cust.houseAddr}<span class="label label-success">${cust.rentalMode}</span>
							</p>
						</td>
						
						<td>
							<c:if test="${cust.startTime!=''&&cust.startTime!=null}">
							<p><fmt:formatDate value="${cust.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
							<p>至<fmt:formatDate value="${cust.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
							</c:if>
						</td>

						<td class="text-c">
						<shiro:hasPermission name="emp:cust:del">
							<a class="btn btn-danger radius" onclick="doDelete('${cust.id}');">删除</a>
						</shiro:hasPermission>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="pagination">${page}</div>
		</div>
	</div>
	<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="lib/laypage/1.2/laypage.js"></script>
	<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
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
		}
		
		function doSort(prop, sort) {
			$("#" + prop).val(sort);
			doSearch();
		};
		
		function doSearch() {
			var form = document.forms['custinfolist'];
			form.submit();
		};
		
		
		
		function datadel(id){
			var idStr = "";
 			$("input[name='checkboxid']:checked").each(function () {
 				idStr = idStr + this.value +"_"
			});
			
			if(idStr==""){
				return;
			}
			
			layer.confirm(
				'确认删除所选客户吗？',
				{
					btn : [ '确认', '返回']
				//按钮
				}, function() {
					var tar = "${ctx}/cust/deled";
					doSubmit(tar,idStr);
				}, function() {
					
				});
		};
		function doDelete(id){
			layer.confirm(
				'确认删除此客户吗？',
				{
					btn : [ '确认', '返回']
				//按钮
				}, function() {
					var tar = "${ctx}/cust/deled";
					doSubmit(tar,id,2);
				}, function() {
					
				});
		}; 
		
		function doSubmit(tar,id,datadel){
			var form = document.forms['submitForm'];
			form.action = tar;
			form['custid'].value = id;
			form.submit();
		};
		
		
		
		
	</script>
</body>
</html> --%>