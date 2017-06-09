<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<!DOCTYPE HTML>
<html>
<head>
<title>公司介绍</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/hui/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/hui/lib/ueditor/1.4.3/ueditor.config.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/hui/lib/ueditor/1.4.3/ueditor.all.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/hui/lib/ueditor/1.4.3/lang/zh-cn/zh-cn.js"></script>
</head>
<body>
	<form id="showDataForm" action="${ctx}/agt/index" method="post">
		<div id="base" class="">
			<div id="u0" class="ax_h2">
				<div id="u1" class="text">
					<p>
						<span>${agtCompInfo.name}</span>
					</p>
				</div>
			</div>

			<div id="u2" class="ax_文本段落">
				<div id="u3" class="text">
					<p>
						<span>门店：30</span>
					</p>
				</div>
			</div>

			<div id="u4" class="ax_文本段落">
				<div id="u5" class="text">
					<p>
						<span>经纪人</span><span>：</span><span>120</span><span>人</span>
					</p>
				</div>
			</div>

			<div id="u6" class="ax_文本段落">
				<div id="u7" class="text">
					<p>
						<span>综合评分：</span>
					</p>
				</div>
			</div>

			<div class="row cl">
				<label class="form-label col-2">公司简介：</label>
				<div class="formControls col-10">
					<script id="editor" type="text/plain" name="introduction"
						style="width: 100%; height: 400px;">${agtCompInfo.introduction}</script>
				</div>
			</div>
			<div class="row cl">
				<div class="col-10 col-offset-2">
					<input id="alter" type = "button" value = "编辑"/>
					<input id="alter" type = "submit" style="display:none;" value = "保存"/>
				</div>
			</div>
		</div>
	</form>

	<script type="text/javascript">
		$(function() {
			var ue = UE.getEditor('editor');
			ue.addListener("ready", function() {
				ue.setDisabled();
			});
			
			$("#alter").click(function(){
				ue.addListener("ready", function() {
					ue.setEnabled();
				});
			});
		});
	</script>

</body>
</html>
