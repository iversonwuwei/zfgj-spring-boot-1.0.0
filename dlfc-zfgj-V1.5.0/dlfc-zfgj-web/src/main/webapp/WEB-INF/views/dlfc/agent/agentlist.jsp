<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>	
<%
	String path = request.getContextPath();
%>
<!DOCTYPE HTML>
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
<title>浏览记录</title>
</head>
<body>
<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span><i class="Hui-iconfont"></i> 人事管理 <span class="c-gray en">&gt;</span>
		经纪人管理 <span class="c-gray en"></span><a
			class="btn btn-success radius r mr-20"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:void(0);" onclick="location.reload();" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
<div id="body" class="pd-20">
	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
	<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	<input id="status" name="status" type="hidden" value="${status}"/>
	<div class="row mb-20 cl">
	    <div class="formControl l">
			<p class="form_text l">部门:</p>
			<div class="formControl l">
	            <sys:treeselect id="office" name="office.id" value="${user.office.id}" labelName="office.name" labelValue="${user.office.name}"
					title="部门" url="/sys/office/treeData?type=2"  cssStyle="width:100px;" cssClass="input-text" notAllowSelectRoot="true" allowClear="true"/>
			</div>
		</div>
	      <div class="formControl l ml-20">
			<div class="formControl l">
	            <input type="text" id="selectValue" style="width:300px;" class="input-text" placeholder="输入姓名/电话">
			</div>	
	    </div>
	    <div class="formControl l">
			<a class="btn btn-primary radius ml-15" href="#" onclick="doSelect()">搜索</a>
		</div>
    </div>
    <div id="content">
	    <%@ include file="/WEB-INF/views/dlfc/agent/agentlistcontent.jsp"%>
    </div>
</div>
<%@ include file="/WEB-INF/views/include/foot.jsp"%>
<script type="text/javascript">
function doQuit(id, name) {
	//判断是否可以离职，合同存在（创建中：1，等待上传：2，审核中：9）不可以离职
	layer.confirm('确定设置[' + name + ']为离职状态？', function() {
		$.ajax({
			type : "GET",
			url : '${ctx}/agt/quit',
			data : {
				id : id
			},
			dataType : 'json',
			cache : false,
			success : function(data) {
			    if (data.success == 0) {
			        layer.msg(data.message);
			    } else {
					location.reload();
			    }
			},
			error : function() {
				location.reload();
			}
		});
	});
}

function doSelect() {
	var sv = $('#selectValue').val();
	var oId = $('#officeId').val();
	var no = $("#pageNo").val();
	var size = $("#pageSize").val();
	var status = $("#status").val();
	$.ajax({
		type : "POST",
		url : '${ctx}/agt/ajaxlist',
		data : {
			sv : sv,
			oId : oId,
			pageNo : no,
			pageSize : size,
			status : status
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

function changRole(id) {
	var status = $("#status").val();
	$.ajax({
		type : "POST",
		url : '${ctx}/agt/ajaxlist',
		data : {
			role : id,
			status : status
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

function transfer(eid,companyId){
	layer.open({
		type: 2,
        title: "移交房源客源",
        closeBtn: 1, //不显示关闭按钮
        shade: [0.3],
        area: ['1200px', '450px'],
        offset: 'auto', //右下角弹出
        shift: 2,
        content: '${ctx}/agt/transfer?companyId='+companyId+'&eid='+eid+'&type=0' //iframe的url，no代表不显示滚动条
	});
}

</script>
</body>
</html>