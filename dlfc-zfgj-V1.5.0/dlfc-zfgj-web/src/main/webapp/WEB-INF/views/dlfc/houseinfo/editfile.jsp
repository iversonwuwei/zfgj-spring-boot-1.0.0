<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<!DOCTYPE HTML>
<html>
<head>

<%@ include file="/WEB-INF/views/include/head2.jsp"%>
<link href="${pageContext.request.contextPath}/static/hui/lib/webuploader/0.1.5/webuploader.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/static/hui/lib/webuploader/0.1.5/mulImages.css" rel="stylesheet" type="text/css" />
<title>添加管理员</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		房源管理 <span class="c-gray en">&gt;</span> 编辑房源<a
			class="btn btn-success radius r mr-20"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	
		<form action="javascript:void(0)" method="post" class="form form-horizontal" id="edithouse" enctype="multipart/form-data">
			<c:if test="${houseinfo.auditStatus!=null && houseinfo.auditStatus=='3'}">
				<p class="listing_num">
					审核未通过：<span>${houseinfo.rejectReason}</span>
				</p>
			</c:if>
			<form:hidden path="houseinfo.id" id="houseId"/>
			<form:hidden path="houseinfo.version" id="version"/>
			<input type="hidden" value="${houseinfo.propertyIdType}" id="propertyIdType_old">
			<input type="hidden" value="${houseinfo.propertyIdNo}" id="propertyIdNo_old">
			<legend>房屋产权</legend>
			<div class="responsive">
				<div class="row cl">
					<label class="form-label col-3"><span class="c-red">*</span>产权类型：</label>
					<div class="formControls col-2">
						${houseinfo.propertyIdTypeName}
					</div>
				</div>
			</div>
			
			<div class="responsive">
				<div class="row cl">
					<label class="form-label col-3"><span class="c-red">*</span>上传证件图片：</label> 
					<div class="col-3">
					<div id="uploader" class="wu-example" style="width:100%;">
						<div class="queueList" id="queueList">
							<ul class="filelist">
							<c:if test="${!empty atts}">
							<c:forEach items="${atts}" var="att" varStatus="vstatus">
								<li class="state-complete" onmouseenter="bigImg(this)">
									<p class="title"></p>
									<p class="imgWrap">
										<img src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/img/${att.filePath}" width="110" height="110">
									</p>
									<p class="moreInfo">
										<input type="hidden" name="pics" value="${att.filePath}_already">
									</p>
									<p class="progress">
										<span style="display: none; width: 0px;"></span>
									</p>
									<div class="file-panel" id="delete_panel" style="height: 0px;" onmouseleave="normalImg(this)">
										<span class="cancel" onclick="deleteImg(this)">删除</span>
									</div>
									<span class="success"></span>
								</li>
								
							</c:forEach>
							</c:if>
							</ul>
						</div>
						<div class="statusBar" id="statusBar" style="display:none;">
							<div class="progress" id="progress">
								<span class="text">0%</span>
								<span class="percentage" id="percentage"></span>
							</div><div class="info" id="info"></div>
						</div>
						<div class="btns">
								<div id="filePicker"></div><!-- <div class="uploadBtn">开始上传</div> -->
							</div>
					</div>
					</div>
				</div>
			</div>
			
			<div class="responsive">
				<div class="row cl">
					<label class="form-label col-3"><span class="c-red">*</span>证件编号：</label>
					<div class="formControls col-5">
						${houseinfo.propertyIdNo}
					</div>
					<p class="col-4 form_text"></p>
				</div>
			</div>

			<legend>产权所有人信息</legend>

			<div class="responsive cl mb-10">
				<div class="cl row form_owner ">
					<label class="form-label col-3"><span class="c-red">*</span>房主姓名：</label>
					<div class="formControls mb-10 col-6">
						${houseinfo.houseOwnerName}
					</div>
					<div class="col-3"></div>
					<div class="cl"></div>
					<label class="form-label col-3"><span class="c-red">*</span>房主证件：</label>
					<div class="formControls col-3">
						${houseinfo.idTypeName}
					</div>
					<div class="formControls col-3 ">
						${houseinfo.idNo}
					</div>
					<div class="col-3"></div>
				</div>
				
				<div id="ownerPanel">
				<c:if test="${houseinfo.owners != null}">
				<c:forEach items="${houseinfo.owners}" var="coOwner">
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
				
			</div>
			<legend>房源信息</legend>

			<div class="row cl">
				<label class="form-label col-6-1"><span class="c-red">*</span>详细地址：</label>
				<div class="formControls col-5">
					大连地区&nbsp;
					${houseinfo.districtName}&nbsp;
					${houseinfo.houseAddr}
				</div>
				<div class="formControls col-1-1">&nbsp;</div>
			</div>

			<div class="row cl">
				<label class="form-label col-6-1"><span class="c-red">*</span>建筑面积：</label>
				<div class="formControls col-5">
					<p class="form_text">${houseinfo.buildingArea}&nbsp;平方米</p>
				</div>
				<div class="formControls col-1-1">&nbsp;</div>
			</div>

			<div class="row cl btn_group">
				<div class="col-9 col-offset-3">
					<a class="btn btn-primary radius" id="saveInfo">修改</a> 
					<a class="btn btn-default radius"href="javascript:history.go(-1)">返回</a> 
				</div>
			</div>
			
			<iframe name="uploadIframe" id="uploadIframe" style="display: none"></iframe>
		</form>
	
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/icheck/jquery.icheck.min.js"></script>
	<%@ include file="/WEB-INF/views/include/foot.jsp"%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/webuploader/0.1.5/webuploader.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/webuploader/0.1.5/webuploaderdlfc.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/webuploader/0.1.5/mulImages.js"></script>
	<script type="text/javascript">
		var file_size = 2 ;
		var _max_co_owner_count = 20;
		var Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 ];// 加权因子;
		var ValideCode = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ];// 身份证验证位值，10代表X;
		$(function() {
			$('.skin-minimal input').iCheck({
				checkboxClass : 'icheckbox-blue',
				radioClass : 'iradio-blue',
				increaseArea : '20%'
			});
			
			$('#edithouse').validationEngine('attach', { binded : false ,showOneMessage: true,maxErrorsPerField : 1,promptPosition: 'centerRight',addPromptClass: 'formError-noArrow formError-text'});
			
			$('#saveInfo').click(function(){
				jQuery('#edithouse').validationEngine('validate');
			});
			
			var propertyIdType='${propertyIdType}';
			
			if (propertyIdType == 4) { 
				$("#propertyIdNo").removeClass().addClass("input-text");
				$('#houseadd').validationEngine('attach', { binded : false ,showOneMessage: true});
			}
			
			var num = new Number('${houseinfo.buildingArea}');
			$("#buildingArea").val(num);
		});
		
		$('#saveInfo').on('click',function() {
			var success = $("#edithouse").validationEngine("validate");
			
			if(!success){
				return;
			}
			
			var pic = $("input[name=pics]").length;
			if(pic == 0){
				layer.msg("请上传证件图片！");
				return;
			}
			

			layer.confirm(
				'您的修改内容将提交后台审核！',
				{
					btn : [ '确认', '返回']
				//按钮
				}, function() {
					var form = document.forms['edithouse'];
					form.action="${ctx}/house/upd";
					form.submit();
				}, function() {
					//layer.msg('返回成功');
				});
		});
		
		function dochange(btn) {
			var $btn = $(btn);
			
			$("#checkfileid").val($btn.val());
		};
		
		var uploader = WebUploader
		.create({
			pick : {
				id : '#filePicker',
				multiple : false
			},
			swf : '${pageContext.request.contextPath}/static/hui/lib/webuploader/0.1.5/Uploader.swf',
			server : '${ctx}/upload/uploadHeadPic/',//上传的URL
			accept : {
				title : '请上传图片格式',
				extensions : 'jpg,jpeg,bmp,png'
			},
			auto : true,
			fileNumLimit : 1,
			fileSingleSizeLimit : 2097152,
			compress : false
		});	
		
		uploader.on('uploadSuccess', function(file) {
			if(arguments[1].success == 0){
				layer.alert(arguments[1].message);
			}else if(arguments[1].success == 1){//上传临时文件夹成功后的操作写在这里 返回filepath和filename
				$("#file_path").val(arguments[1].data.filepath);
				$("#file_real_name").val(arguments[1].data.filename);
				$("#fileName").val(arguments[1].data.filerealname);
			}
		});
		
		initUploader(uploader, 168, 168, "#fileList");
		
		
		function checkFile(obj){
			var photoExt=obj.val().substr(obj.val().lastIndexOf(".")).toLowerCase();//获得文件后缀名
			if(!(photoExt=='.jpg'||photoExt=='.png'||photoExt=='.bmp')){
				return "请上传后缀名为jpg/png/bmp的图片";
			}
		};
		
		function bigImg(li) {
			var $btns = $(li).find( 'div.file-panel' );
			$btns.animate({height: 30});
		}

		function normalImg(bts) {
			var $btns = $(bts);
			$btns.animate({height: 0});
		}
		
		function deleteImg(bts){
			var $btns = $(bts);
			
			var $queue = $btns.parent().parent();
			
			if ($queue.find("li").length <= 8) {
				$queue.parent().parent().parent().find('.btns').find('.webuploader-container').show();
			}
			$queue.remove();
		}
		
		mulImagesInit('${ctx}','',file_size,'${pageContext.request.contextPath}/static/hui/lib/webuploader/0.1.5/Uploader.swf');
	</script>
</body>
</html>