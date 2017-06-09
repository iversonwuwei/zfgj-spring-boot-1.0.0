<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>	
<!DOCTYPE html">
<html>
<head>
<%@ include file="/WEB-INF/views/include/head2.jsp"%>
<script type="text/javascript">
function page(n,s){
	$("#pageNo").val(n);
	$("#pageSize").val(s);
	doSelect();
	return false;
}
</script>
<title>移交列表</title>
</head>
<body>
<div id = "content">
	<div class="pd-20">
		<div class="list_head cl">
			<div class="house-list-tab cl">
			<c:choose>
				<c:when test="${type == 0}">
					<a href="#" onclick="changType(0)"><span class="current">房源列表</span></a>
					<a href="#" onclick="changType(1)"><span>客源列表</span></a>
				</c:when>
				<c:when test="${type == 1}">
					<a href="#" onclick="changType(0)"><span >房源列表</span></a>
					<a href="#" onclick="changType(1)"><span class="current">客源列表</span></a>
				</c:when>
			</c:choose>
			</div>
		</div>
		
		<div class="mt-20">
			<table class="table table-border table-bordered table-hover table-bg ">
				<thead>
					<tr class="text-c">
						<th width="70"></th>
						<th width="120">姓名</th>
						<th width="120">手机号</th>
						<c:if test="${type == 0}">
							<th width="160">服务区域</th>
							<th>门店名称</th>
						</c:if>
					</tr>	
				</thead>
				<tbody>
					<c:forEach items="${page.list}" var="empInfo" varStatus="status">
						<tr class="text-c">
							<td><input type="radio" name="selected" onclick="assignment('${empInfo.id}');"></td>
							<td>${empInfo.name}</td>
							<td>${empInfo.mobile}</td>
							<c:if test="${type == 0}">
								<td>
								<c:forEach items="${empInfo.sysOfficeAreaLink}" var="tradeName">
									<label>${tradeName.name}</label>
								</c:forEach>
								</td>
								<td>${empInfo.agtCompInfo.name}</td>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<input id="companyId" type="hidden" value="${companyId}">
			<input id="type" type="hidden" value="${type}">
			<input id="oldEid" type="hidden" value="${oldEid}">
			<input id="eid" type="hidden" value="">
			<p class="mt-10"><a id="submit" class="btn btn-primary" onclick="submit();">确定</a></p>
			
			<c:if test="${page.list.size() gt 0 }">
				<div class="pagination">${page}</div>
			</c:if>
			<c:if test="${page.list.size() eq 0 }">
				<p class="pd-20 text-c f-18">无任何查询结果！</p>
			</c:if>
		</div>
	</div>
</div>


<%@ include file="/WEB-INF/views/include/foot.jsp"%>
<script type="text/javascript">

function doSelect() {
	var type = $("#type").val();
	var companyId = $("#companyId").val();
	var no = $("#pageNo").val();
	var size = $("#pageSize").val();
	$.ajax({
		type : "POST",
		url : '${ctx}/agt/ajaxlist',
		data : {
			type : type,
			companyId : companyId,
			pageNo : no,
			pageSize : size
		},
		dataType : 'text',
		cache : false,
		async: false,
		success : function(data) {
			$("#content").html(data);
		},
		error : function(data) {
			layer.msg("通讯失败，请稍后再试。");
		}
	});
}

function changType(type) {
	var companyId = $("#companyId").val();
	var oldEid = $("#oldEid").val();
	$.ajax({
		type : "GET",
		url : '${ctx}/agt/transfer',
		data : {
			companyId : companyId,
			eid : oldEid,
			type : type
			
		},
		dataType : 'text',
		cache : false,
		async: false,
		success : function(data) {
			$("#content").html(data);
		},
		error : function(data) {
			layer.msg("通讯失败，请稍后再试。");
		}
	});
}

function assignment(eid){
	$("#eid").val(eid);
}

function submit(){
	var eid = $("#eid").val();
	var oldEid = $("#oldEid").val();
	var type = $("#type").val();
	if(eid == ''){
		layer.msg("请选择经纪人");
	}else{
		$.ajax({
			type : "POST",
			url : '${ctx}/agt/tc',
			data : {
				eid : eid,
				oldEid : oldEid,
				type : type
			},
			dataType : 'json',
			cache : false,
			async: false,
			success : function(data) {
				if(data.success == 1){
					layer.msg("移交成功");
					 parent.location.reload();
				}else{
					layer.msg("移交失败");
				}
			},
			error : function(data) {
				layer.msg("通讯失败，请稍后再试。");
			}
		});
	}
}
</script>
</body>
</html>