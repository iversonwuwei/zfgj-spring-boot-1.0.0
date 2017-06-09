<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="${ctxStatic}${theme }lib/html5.js"></script>
<script type="text/javascript" src="${ctxStatic}${theme }lib/respond.min.js"></script>
<script type="text/javascript" src="${ctxStatic}${theme }lib/PIE-2.0beta1/PIE_IE678.js"></script>
<![endif]-->
<link href="${ctxStatic}/hui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="${ctxStatic}/hui/css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="${ctxStatic}/hui/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctxStatic}/hui/lib/icheck/icheck.css" rel="stylesheet" type="text/css" />
<link href="${ctxStatic}/hui/lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
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
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 帮助中心<span class="c-gray en">&gt;</span> 经纪人端<a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="pd-20">
  <div class="responsive">
  	<form name="helplist" action="${ctx}/help/zhxx">
		<input id="pageNo" type="hidden" name="pageNo" value="${page.pageNo}">
		<input id="pageSize" type="hidden" name="pageSize" value="${page.pageSize}">
		<input id="gid" type="hidden" name="gid" value="${gid}">
	</form>
    <div class="help-box cl">
      <div class="panel panel-default col-3">
        <p class="panel-header">服务大厅</p>
        <div class="panel-body">
          <ul class="help-quest-menu">
            <li><a href="${ctx}/help/zhxx?gid=45185109a1824210bd0f27f3d93f3837">账户信息</a></li>
            <li><a href="${ctx}/help/zhxx?gid=5ae92a0f51cd42c5aa9b83ae7bd93e22">资金信息</a></li>
            <li><a href="${ctx}/help/zhxx?gid=1cf58f06e8b942d5b7034263d5f7ef08">房源管理</a></li>
            <li><a href="${ctx}/help/zhxx?gid=d00058377537444589a9efdc9e63edcd">合同管理</a></li>
          </ul>
        </div>
      </div>
   	  <div class="panel panel-default col-8 col-offset-1">
   		<p class="panel-header">问题列表</p>
   		<div class="panel-body">
				<ul class="help-quest-list">
					<c:forEach items="${page.list}" var="list" varStatus="status">
	   					<li><a href="${ctx}/help/form?id=${list.id}&gid=${gid}">${list.title}</a></li>
					</c:forEach>
				</ul>
       		<div class="pagination">${page}</div>
   		</div>
   	  </div>  
    </div>
  </div>
</div>
<script type="text/javascript" src="${ctxStatic}/hui/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${ctxStatic}/hui/lib/icheck/jquery.icheck.min.js"></script> 
<script type="text/javascript" src="${ctxStatic}/hui/lib/Validform/5.3.2/Validform.min.js"></script> 
<script type="text/javascript" src="${ctxStatic}/hui/lib/layer/1.9.3/layer.js"></script> 
<script type="text/javascript" src="${ctxStatic}/hui/js/H-ui.js"></script> 
<script type="text/javascript" src="${ctxStatic}/hui/js/H-ui.admin.js"></script>
<%@ include file="/WEB-INF/views/include/foot.jsp"%> 
<script type="text/javascript">
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	$("#form-admin-add").Validform({
		tiptype:2,
		callback:function(form){
			form[0].submit();
			var index = parent.layer.getFrameIndex(window.name);
			parent.$('.btn-refresh').click();
			parent.layer.close(index);
		}
	});
});

function doSearch() {
	var form = document.forms['helplist'];
	form.submit();
};
</script>
</body>
</html>