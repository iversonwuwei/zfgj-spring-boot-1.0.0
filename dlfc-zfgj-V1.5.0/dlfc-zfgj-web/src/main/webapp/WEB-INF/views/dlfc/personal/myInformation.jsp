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
<link
	href="${pageContext.request.contextPath}/static/hui/css/citySelector.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/static/hui/lib/webuploader/0.1.5/webuploader.css"
	rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>添加管理员</title>
<c:if test="${flag == '1' }">
	<script type="text/javascript">
var Vcity = {};
Vcity.allCity =${comInfo};
</script>
</c:if>
</head>
<body>
<c:if test="${flag == '1' }">
<input type="text" class="cityinput" id="citySelect" >
<a class="btn btn-primary radius mr-10" onclick="doSave();">提交</a>
</c:if>
<c:if test="${flag == '0' }">
公司：${comAll.agtCompInfo.name }
门店名称：
门店地址：${comAll.agtCompInfo.address }
</c:if>
用户名：${comAll.usrUser.username }
真实姓名：${comAll.sysPerson.name }
证件号码：${fn:substring(comAll.sysPerson.idNo,0,3)}******${fn:substring(comAll.sysPerson.idNo,fn:length(comAll.sysPerson.idNo)-2,fn:length(comAll.sysPerson.idNo))}
座机：${comAll.sysPerson.contactWay }
qq：${comAll.sysPerson.qq }
我的头像 :<img id="headp"src="${pageContext.request.contextPath}/static/hui/images/admin-login-bg.jpg" height="200" width="200" onclick="doLayer();"/>
	  <div id="uploader" class="wu-example" style="display: none;">
    <!--用来存放文件信息-->
    <div id="fileList" class="uploader-list"></div>
    <div class="btns">
        <div id="filePicker">选择文件</div>
        <button id="ctlBtn" class="btn btn-default">开始上传</button>
    </div>
</div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/icheck/jquery.icheck.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/Validform/5.3.2/Validform.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/webuploader/0.1.5/webuploader.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/layer/1.9.3/layer.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/js/H-ui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/js/H-ui.admin.js"></script>
	<c:if test="${flag == '1' }">
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/js/citySelector.js"></script>
	</c:if>
<script type="text/javascript">

<c:if test="${flag == '1' }">
    var test=new Vcity.CitySelector({input:'citySelect'});
    </c:if>  
    function doSave() {
		var citySelect = $("#citySelect").val();
		$.ajax({
			type : "POST",
			url : 'jcs',
			data : {
				citySelect : citySelect
			},
			dataType : 'json',
			cache : false,
			success : function(data) {
				if (1 == data.success) {
						window.location.href = "${ctx}/my/asecu";

				} else {
					layer.alert(data.message);
				}
			},
			error : function() {
				layer.alert("网络异常，请稍后重试！")

			}
		}); 
	}
    
    function doLayer(){
    	_didx=layer.open({
			type: 1, //page层
			area: ['500px', '500px'],
			title: '修改头像',
			shade: 0.6, //遮罩透明度
			moveType: 1, //拖拽风格，0是默认，1是传统拖动
			shift: 0, //0-6的动画形式，-1不开启
			content: $('#uploader')
		});
    	$("#uploader").show();
    }
    
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
        auto:false,
        fileNumLimit:1,
        fileSingleSizeLimit:2097152,
        compress:false
    });
	
	uploader.on( 'uploadSuccess', function( file ) {
		
		if(arguments[1].code == 200){
			//layer.alert(arguments[1].uuid);
			 $("#imgId").val(arguments[1].uuid);
			 //layer.alert($("#imgId").val()+"sqqqqqqq");
		}else{
			layer.alert(arguments[1].error);
		}
		$("#headp").attr("src", "${pageContext.request.contextPath}/static/hui/images/ico_tel.png"); 
		layer.close(_didx);
	});
	
	$(function(){  
    	var $btn = $('#ctlBtn');
    	var $state = "pending";
    	 
    	
    	
    	uploader.on( 'beforeFileQueued', function( file ) {
    		uploader.reset();
    	});
    	// 当有文件添加进来的时候
    	uploader.on( 'fileQueued', function( file ) {
    	    var $li = $(
    	            '<div id="' + file.id + '" class="file-item thumbnail">' +
    	                '<img>' +
    	                '<div class="info">' + file.name + '</div>' +
    	            '</div>'
    	            ),
    	        $img = $li.find('img');
    	    $("#fileList").html( $li );
    	    uploader.makeThumb( file, function( error, src ) {
    	        if ( error ) {
    	            $img.replaceWith('<span>不能预览</span>');
    	            return;
    	        }

    	        $img.attr( 'src', src );
    	    }, 100, 100 );
    	    
    	});

    	uploader.on('error', function(type){
    		if(type=='Q_TYPE_DENIED'){
    			layer.alert("上传类型有错");
    		}
    		if(type=='Q_EXCEED_SIZE_LIMIT'){
    			layer.alert("上传文件尺寸过大");
    		}
        });
    	
    	

    	uploader.on( 'uploadError', function( file ) {
    		layer.alert("上传出错");
    	});
    	
		$btn.on( 'click', function() {
			alert(1);
	        uploader.upload();
	    });
	
    });  
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/webuploader/0.1.5/webuploaderdlfc.js"></script>
</body>
</html>