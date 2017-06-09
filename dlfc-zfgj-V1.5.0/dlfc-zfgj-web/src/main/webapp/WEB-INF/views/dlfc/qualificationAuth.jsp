<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>	
<%
	String path = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="${ctxStatic}${theme }lib/html5.js"></script>
<script type="text/javascript" src="${ctxStatic}${theme }lib/respond.min.js"></script>
<script type="text/javascript" src="${ctxStatic}${theme }lib/PIE-2.0beta1/PIE_IE678.js"></script>
<![endif]-->
<link href="${pageContext.request.contextPath}/static/hui/lib/webuploader/0.1.5/webuploader.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/static/hui/css/H-ui.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/static/hui/css/H-ui.admin.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/static/hui/css/style.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/static/hui/lib/icheck/icheck.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/static/hui/lib/Hui-iconfont/1.0.1/iconfont.css"
	rel="stylesheet" type="text/css" />
	
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>添加管理员</title>
</head>
<body>
<c:if test="${flag != '1' }">
    <div id="uploader" class="wu-example">
    <!--用来存放文件信息-->
    <div id="fileList" class="uploader-list"></div>
    <div class="btns">
        <div id="filePicker">选择文件</div>
        <button id="ctlBtn" class="btn btn-default">开始上传</button>
    </div>
</div>

<div class="row cl">
			<div class="formControls col-3">
				<input  placeholder="资格证号" autocomplete="off" value="" class="input-text" name="certCardNo" id="certCardNo" >
				<input type="hidden" id="imgId" />		
				${ctx }	
			</div>
			<div class="col-4"> </div>
		</div>
		<a class="btn btn-primary radius mr-10"  onclick="doSubmit();"></a>
		</c:if>
		<c:if test="${flag == '1' }">
		认证中
		</c:if>
		<img src="http://localhost:8080/img/2015-11-26/20151126160916034.jpg"><% out.println(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()); %>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/webuploader/0.1.5/webuploader.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/icheck/jquery.icheck.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/Validform/5.3.2/Validform.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/layer/1.9.3/layer.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/js/H-ui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/js/H-ui.admin.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/webuploader/0.1.5/webuploaderdlfc.js"></script>
	
	<script type="text/javascript">
	
	function showfilename(){
		var path = $('#file').val();
		$('#fileText').val(path);
	};
	
		var uploader = WebUploader.create({
			pick: {
	            id: '#filePicker',
	            multiple: false
	        },
	        swf: '${pageContext.request.contextPath}/static/hui/lib/webuploader/0.1.5/Uploader.swf',
	        server: '${ctx}/upload/uploadHeadPic/',//上传的URL
	        accept: {
	             title: '请上传图片格式',
	             extensions: 'jpg,jpeg,bmp,png'
	         }, 
	        auto:true,
	        fileNumLimit:1,
	        fileSingleSizeLimit:2097152,
	        compress:false
	    });
		
		uploader.on( 'uploadSuccess', function( file ) {
			//后台验证失败（文件大小 文件类型验证）
			if(arguments[1].success == 0){
				layer.alert(arguments[1].message);
			}else if(arguments[1].success == 1){//上传临时文件夹成功后的操作写在这里 返回filepath和filename
				layer.alert(arguments[1].data.filepath);
			}
    	});
    
		initUploader(uploader,300,300,"#fileList");
    function doSubmit() {
		var certCardNo = $("#certCardNo").val();
		var imgId = $("#imgId").val();
		$.ajax({
			type : "POST",
			url : 'agtcs',
			data : {
				certCardNo : certCardNo,
				imgId : imgId
			},
			dataType : 'json',
			cache : false,
			success : function(data) {
				if (1 == data.success) {
					window.location.href = "${ctx}/my/asecu";
					$("#submit").hide();

				} else {
					layer.alert(data.message);
				}
			},
			error : function() {
				layer.alert("网络异常，请稍后重试！")
			}
		}); 
	}
    
	</script>
</body>
</html>