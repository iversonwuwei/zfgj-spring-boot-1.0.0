<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<!DOCTYPE HTML>
<html>
<head>

<%@ include file="/WEB-INF/views/include/head2.jsp"%>
<link href="${pageContext.request.contextPath}/static/hui/css/citySelector.css" rel="stylesheet" type="text/css" />
<style type="text/css">
        .cityinput{
            border-width: 1px;
            border-style: solid;
            border-color: #666 #ccc #ccc #666;
            height: 24px;
            line-height: 24px;
            width: 175px;
            font-size: 12px;
            padding-left: 2px;
            background: url(http://img02.taobaocdn.com/tps/i2/T1EPyLXm0hXXXXXXXX-200-100.png) no-repeat 150px 5px;
            }
    </style>
<title>添加管理员</title>
<c:if test="${flag == '1' }">
	<script type="text/javascript">
var Vcity = {};
Vcity.allCity =${comInfo};
</script>
</c:if>
</head>
<body>
<div class="pd-20">
  <form action="" method="post" class="form form-horizontal" id="form-admin-add">
    <c:if test="${flag == '1' }">
<input type="text" class="cityinput" id="citySelect" >
<a class="btn btn-primary radius mr-10" onclick="doSave();">提交</a>
</c:if>
<c:if test="${flag == '0' }">
公司：${comAll.agtCompInfo.name }
门店名称：
门店地址：${comAll.agtCompInfo.address }
</c:if>
<c:if test="${flag == '3' }">
审核中
</c:if>
  </form>
</div>
<!--  
<div>
${user.username} <a href="${ctx}/my/pwdc">修改密码</a><a href="${ctx}/my/mb">绑定邮箱</a><a href="${ctx}/my/mobilec">修改手机</a><a href="${ctx}/agtc/login">从业</a><a href="${ctx}/my/mul">多图片上传</a><c:if test="${user.email == null}">123123</c:if>
	</div>
-->
	<%@ include file="/WEB-INF/views/include/foot.jsp"%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/js/citySelector.js"></script>
	<script type="text/javascript">
    var test=new Vcity.CitySelector({input:'citySelect'});
    
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
						window.location.href = "${ctx}/agtc/login";

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