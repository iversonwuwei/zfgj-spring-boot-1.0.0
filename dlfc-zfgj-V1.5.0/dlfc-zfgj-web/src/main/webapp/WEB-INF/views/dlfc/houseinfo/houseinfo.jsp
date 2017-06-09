<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<!DOCTYPE HTML>
<html>
<head>

<%@ include file="/WEB-INF/views/include/head2.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctxStatic}${hui}lib/fancybox/jquery.fancybox.css?v=2.1.5" media="screen" />

<title>添加管理员</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		房源管理 <span class="c-gray en">&gt;</span> 房源明细<a
			class="btn btn-success radius r mr-20"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	
		<form action="javascript:void(0)" method="post" class="form form-horizontal" id="houseadd" enctype="multipart/form-data">
			<legend>房屋产权</legend>
			<div class="responsive">
				<div class="row cl">
					<label class="form-label col-3"><span class="c-red">*</span>产权类型：</label>
					<div class="formControls col-2">
						${houseInfo.propertyIdTypeName}
					</div>
				</div>
			</div>
			
			<div class="responsive">
				<div class="row cl">
					<label class="form-label col-3"><span class="c-red">*</span>证件图片：</label>
					<c:if test="${!empty atts}">
						<c:forEach items="${atts}" var="att" varStatus="vstatus">
							<div class="col-1">
								<a href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/img/${att.filePath}" class="fancybox">
									<img src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/img/${att.filePath}" width="110" height="110">
								</a>
							</div>
						</c:forEach>
					</c:if>
				</div>
			</div>
			
			<div class="responsive">
				<div class="row cl">
					<label class="form-label col-3"><span class="c-red">*</span>证件编号：</label>
					<div class="formControls col-5">
						${houseInfo.propertyIdNo}
					</div>
					<p class="col-4 form_text"></p>
				</div>
			</div>

			<legend>产权所有人信息</legend>

			<div class="responsive cl mb-10">
				<div class="cl row form_owner ">
					<label class="form-label col-3"><span class="c-red">*</span>房主姓名：</label>
					<div class="formControls mb-10 col-6">
						${houseInfo.houseOwnerName}
					</div>
					<div class="col-3"></div>
					<div class="cl"></div>
					<label class="form-label col-3"><span class="c-red">*</span>房主证件：</label>
					<div class="formControls col-3">
						${houseInfo.idTypeName}
					</div>
					<div class="formControls col-3 ">
						${houseInfo.idNo}
					</div>
					<div class="col-3"></div>
				</div>
				
				<c:if test="${houseInfo.owners != null}">
				<c:forEach items="${houseInfo.owners}" var="coOwner">
					<div class="co_box cl row form_owner ">	
						<label class="form-label col-3"><span class="c-red">*</span>共有人姓名：</label>
						<div class="formControls mb-10 col-6">
							${coOwner.name}	
						</div>
						<div class="col-3"><span class="Validform_checktip"></span></div>
						<div class="cl"></div>
						<label class="form-label col-3"><span class="c-red">*</span>共有人证件：</label>
						<div class="formControls col-3">
								${coOwner.idTypeName}
						</div>
						<div class="formControls col-3 ">
							${coOwner.idNo} 
						</div>
					</div> 
				</c:forEach>
				</c:if>
			</div>
			<legend>房源信息</legend>

			<div class="row cl">
				<label class="form-label col-6-1"><span class="c-red">*</span>详细地址：</label>
				<div class="formControls col-5">
					大连地区&nbsp;
					${houseInfo.districtName}&nbsp;
					${houseInfo.houseAddr}
				</div>
				<div class="formControls col-1-1">&nbsp;</div>
			</div>

			<div class="row cl">
				<label class="form-label col-6-1"><span class="c-red">*</span>建筑面积：</label>
				<div class="formControls col-5">
					<p class="form_text">${houseInfo.buildingArea}&nbsp;平方米</p>
				</div>
				<div class="formControls col-1-1">&nbsp;</div>
			</div>

			<div class="row cl btn_group">
				<div class="col-9 col-offset-3">
					<a class="btn btn-default radius" id="saveInfo" href="javascript:history.go(-1)">返回</a> 
				</div>
			</div>
			
			<iframe name="uploadIframe" id="uploadIframe" style="display: none"></iframe>
		</form>
	
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/icheck/jquery.icheck.min.js"></script>
	<%@ include file="/WEB-INF/views/include/foot.jsp"%>
	<script type="text/javascript" src="${ctxStatic}${hui}lib/fancybox/jquery.fancybox.js?v=2.1.5"></script>
	<script type="text/javascript">
		$(function() {
			$('.fancybox').fancybox();
		});
	</script>
</body>
</html>