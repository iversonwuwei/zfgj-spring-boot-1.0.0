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
		房源管理 <span class="c-gray en">&gt;</span> 编辑房源
		<a class="btn btn-success radius r mr-20" style="line-height: 1.6em; margin-top: 3px"
			href="javascript:void();" title="刷新"><i class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	
		<form action="javascript:void(0)" method="post" class="form form-horizontal" id="edithouse" enctype="multipart/form-data">
			<c:if test="${houseinfo.auditStatus!=null && houseinfo.auditStatus=='3'}">
				<div class=" contract_fail"><i class="Hui-iconfont">&#xe6e0;</i>审核失败：${houseinfo.rejectReason}</div>
			</c:if>
			<form:hidden path="houseinfo.id" id="houseId"/>
			<input id="e_hid" type="hidden" name="hid" value="${houseinfo.id}">
			<form:hidden path="houseinfo.version" id="version"/>
			<input type="hidden" value="${houseinfo.propertyIdType}" id="propertyIdType_old">
			<input type="hidden" value="${houseinfo.propertyIdNo}" id="propertyIdNo_old">


			<legend>房源信息</legend>

			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span><strong>详细地址：</strong></label>
				<div class="formControls col-5">
					<form:input type="text" class="validate[required,maxSize[100],minSize[1],funcCall[checkAddr]] input-text"  path="houseinfo.houseAddr" id="houseAddr"  maxlength="100" datatype="s1-100" nullmsg="请输入房源地址！" placeholder="房源地址"/>
				</div>
				<div class="formControls col-1-1">&nbsp;</div>
				<div class="col-offset-3 col-6 c-red">注：请确保您输入的地址信息与房产证上的信息一致。</div>
			</div>
			
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>建筑面积：</label>
				<div class="formControls col-1">
					<form:input type="text" class="validate[required,maxSize[11],funcCall[checkArea]] input-text" path="houseinfo.buildingArea" id="buildingArea" maxlength="9" placeholder="建筑面积" data-prompt-target="errorTd_buildingarea" data-prompt-position="inline"/>
				</div>
				<p class="col-1 form_text">平方米</p>
				<div class="form_text col-1" id="errorTd_buildingarea"></div>
			</div>
			
			<!-- 房屋结构 -->
			<div class="row cl">
		        <label class="form-label col-3"><span class="c-red">*</span>房屋结构：</label>
		        <div class="formControls col-2">
		          <span class="select-box">
			        <form:select path="houseinfo.structure" id="structure" class="select" size="1">
						<form:options items="${structure_list}" itemValue="code" itemLabel="name" />
					</form:select>
				</span>
		        </div>
		     </div>
			 <!-- 房屋结构 -->



			<legend>产权所有人信息</legend>

			<div class="responsive cl mb-10">
				<div class="cl row form_owner ">
					<label class="form-label col-3 removeSpan"><span class="c-red">*</span>房主姓名：</label>
					<div class="formControls mb-10 col-6">
						<form:input type="text" class="validate[required,minSize[2],maxSize[15],funcCall[checkName]] input-text" path="houseinfo.houseOwnerName" id="houseOwnerName"  maxlength="10" datatype="checkname" nullmsg="请输入房主姓名！" placeholder="房主姓名"/>
					</div>
					<div class="col-3"></div>
					<div class="cl"></div>
					<label class="form-label col-3 removeSpan"><span class="c-red">*</span>房主证件：</label>
					<div class="formControls col-3">
						<span class="select-box">
						<form:select path="houseinfo.idType" id="houseOwnerIdType" class="select" size="1">
							<form:options items="${id_list}" itemValue="code" itemLabel="name" />
						</form:select>
						</span>
					</div>
					<div class="formControls col-3 ">
						<form:input type="text" class="validate[required,maxSize[18],funcCall[checkIdCard]] input-text" path="houseinfo.houseOwnerIdNo" id="houseOwnerIdNo"  maxlength="18" placeholder="证件编号"/>
					</div>
					<div class="col-3"></div>
					<div class="cl mb-10"></div>
					<label class="form-label col-3">手机：</label>
					<div class="formControls col-6">
						<form:input type="text" class="validate[custom[mobile]] input-text" path="houseinfo.phone" id="phone"  maxlength="11" placeholder="手机"/>
					</div>
				</div>
				
				<div id="ownerPanel">
				<c:if test="${houseinfo.owners != null}">
				<c:forEach items="${houseinfo.owners}" var="coOwner" begin="1">
					<div class="co_box cl row form_owner ">	
						<label class="form-label col-3"><span class="c-red">*</span>共有人姓名：</label>
						<div class="formControls mb-10 col-6">
							<input type="text" placeholder="共有人姓名" class="validate[required,minSize[2],maxSize[15],funcCall[checkName]] input-text coOwner" maxlength="10" value="${coOwner.name}" name="coname" id="coname">
						</div>
						<div class="col-3"><span class="Validform_checktip"></span></div>
						<div class="cl"></div>
						<label class="form-label col-3"><span class="c-red">*</span>共有人证件：</label>
						<div class="formControls col-3">
							<span class="select-box">
								<select class="select" size="1" name="coidtype" >
									<c:forEach items="${id_list}" var="id">
										<option value="${id.code }" <c:if test="${coOwner.idType == id.code}">selected="selected"</c:if>>${id.name }</option>
									</c:forEach>
								</select>
							</span>	
						</div>
						<div class="formControls col-3 ">
						<input type="text" placeholder="证件编号" class="validate[required,maxSize[18],funcCall[checkIdCard]] input-text" value="${coOwner.idNo}" name="coidcode" maxlength="18" id="coidcode">
						</div>
						<div class="col-3"></div>
						<div class="formControls text-r mt-10 col-12">
						<a class="label label-danger" onclick="delCoOwner(this);"><i class="Hui-iconfont">&#xe6a6;</i></a>
					</div>
					</div> 
				</c:forEach>
				</c:if> 
				</div>
				
				<div class="item add_item fn_clear" style="display:none" id="coOwnerPanel"></div>
			
				<div class="cl row " id="addCoOwnerFirstBtn">
					<div class="formControls text-r mt-10 col-12">
						<a class="btn btn-primary radius" onclick="addCoOwner(this);"><i class="Hui-iconfont">&#xe600;</i>添加共有人</a>
					</div>
				</div>
			</div>
			
			
			<legend>房屋产权</legend>
			<div class="responsive">
				<div class="row cl">
					<label class="form-label col-3"><span class="c-red">*</span>产权类型：</label>
					<div class="formControls col-2">
						<span class="select-box">
						<form:select path="houseinfo.propertyIdType" id="propertyIdType" class="select" size="1">
							<form:options items="${property_list}" itemValue="code" itemLabel="name" />
						</form:select>
						</span>
					</div>
				</div>
			</div>
			
			<div class="responsive">
				<div class="row cl">
					<label class="form-label col-3 removeSpan"><span class="c-red">*</span>上传证件图片：</label> 
					<div class="col-3">
					<div id="uploader" class="wu-example" style="width:100%;">
						<div class="queueList" id="queueList">
							<ul class="filelist">
							<c:if test="${!empty atts}">
							<c:forEach items="${atts}" var="att" varStatus="vstatus">
								<li class="state-complete" onmouseenter="bigImg(this)">
									<p class="title"></p>
									<p class="imgWrap">
										<img src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/img/${att.filePath}?key=${accessKey}" width="110" height="110">
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
					<div class="col-offset-3 col-6 c-red">注：请上传jpg、png、bmp格式的图片；共2张；最大4M。请上传含产权所属编号、产权所属人信息的照片。</div>
				</div>
			</div>
			
			<div class="responsive">
				<div class="row cl">
					<label class="form-label col-3">证件编号：</label>
					<div class="formControls col-5">
						<form:input path="houseinfo.propertyIdNo" type="text" class="input-text" id="propertyIdNo"  maxlength="100"  />
					</div>
					<p class="col-4 form_text"></p>
				</div>
			</div>
			
			
						<div class="row cl btn_group">
				<div class="col-9 col-offset-3">
					<a class="btn btn-primary radius" id="saveInfo">修改</a> 
					<a class="btn btn-default radius"href="javascript:history.go(-1)">返回</a> 
				</div>
			</div>
			

			
			<iframe name="uploadIframe" id="uploadIframe" style="display: none"></iframe>
		</form>
	
	<div id="hou_co_owner">
		<div class="co_box cl row form_owner ">
			<label class="form-label col-3"><span class="c-red">*</span>共有人姓名：</label>
			<div class="formControls mb-10 col-6">
				<input type="text" placeholder="共有人姓名" class="validate[required,minSize[2],maxSize[15],funcCall[checkName]] input-text" maxlength="10" name="coname" id="coname">	
			</div>
			<div class="col-3"><span class="Validform_checktip"></span></div>
			<div class="cl"></div>
			<label class="form-label col-3"><span class="c-red">*</span>共有人证件：</label>
			<div class="formControls col-3">
				<span class="select-box"> <select class="select" size="1" name="coidtype">
					<c:forEach items="${id_list}" var="id">
						<option value="${id.code }">${id.name }</option>
					</c:forEach>
				</select>
				</span>
			</div>
			<div class="formControls col-3 ">
				<input type="text" placeholder="证件编号" class="validate[required,maxSize[18],funcCall[checkIdCard]] input-text" name="coidcode" maxlength="18" id="coidcode"> 
			</div>
			<div class="col-3"><span class="Validform_checktip"></span></div>
			<div class="formControls text-r mt-10 col-12">
				<a class="label label-danger" onclick="delCoOwner(this);"><i class="Hui-iconfont">&#xe6a6;</i></a>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/icheck/jquery.icheck.min.js"></script>
	<%@ include file="/WEB-INF/views/include/foot.jsp"%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/webuploader/0.1.5/webuploader.js"></script>
	<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/webuploader/0.1.5/webuploaderdlfc.js"></script> --%>
	<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/webuploader/0.1.5/mulImages.js"></script> --%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/webuploader/0.1.5/mulImg.js"></script>
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
			
			var propertyIdType='${propertyIdType}';
			
			if (propertyIdType == 4) { 
				$("#propertyIdNo").removeClass().addClass("input-text");
				$('#houseadd').validationEngine('attach', { binded : false ,showOneMessage: true});
			} 
			
			var num = new Number('${houseinfo.buildingArea}');
			$("#buildingArea").val(num);
			
			$("#hou_co_owner").hide();
			
			$("a[title=刷新]").on("click", function(){
				$("#edithouse").attr("action","${ctx}/house/edit");
				$("#edithouse").submit();
			})
			
			var flg = false;
			var propertyIdType = $("#propertyIdType").val();
			if(propertyIdType == '4'){
				setRequired($("#houseOwnerName"), false);
				setRequired($("#houseOwnerIdNo"), false);
				$(".removeSpan").each(function(){
					$(this).find("span").remove();
				});
				flg = true;
			}
			$("#propertyIdType").on("change", function(){
				var val = $(this).val();
				if (val == '4') {
					$(".removeSpan").each(function(){
						$(this).find("span").remove();
					});
					setRequired($("#houseOwnerName"), false);
					setRequired($("#houseOwnerIdNo"), false);
					flg = true;
				} else {
					if (flg) {
						$(".removeSpan").each(function(){
							$(this).prepend($('<span class="c-red">*</span>'));
						});
						setRequired($("#houseOwnerName"), true);
						setRequired($("#houseOwnerIdNo"), true);
						flg = false;
					}
				}
			});
			
		});
		
		function addCoOwner(btn) {
			var $html = $("#hou_co_owner").children().clone();

			var length = $("#coOwnerPanel").find(".co_box").length;
			var len = $("#ownerPanel").find(".co_box").length;
			
			length = length + len;
			$html.find("input[name=coname]").addClass("coOwner");
			$("#coOwnerPanel").append($html);
			
 			if (length +1 >= _max_co_owner_count) {
				$("#addCoOwnerFirstBtn").hide();
			} 
			
			$("#coOwnerPanel").show();
		};
		
		function delCoOwner(btn) {
			var $btn = $(btn);
			
			var length = $("#coOwnerPanel").find(".co_box").length;
			
			$btn.parent().parent().remove();
			
			if (length > 0) {
				$("#addCoOwnerFirstBtn").show();
			}else{
				$("#coOwnerPanel")[0].style.display = 'none'; 
			} 
		};
		
		$('#saveInfo').on('click',function() {
			var success = $("#edithouse").validationEngine("validate");
			
			if(!success){
				return;
			}
			
			var propertyIdType=$("#propertyIdType").val();
			var pic_len = $("input[name=pics]").length;
			if(pic_len == 0 && propertyIdType != '4'){
				layer.msg("请上传证件图片！");
				return;
			} else if(pic_len != 2 && propertyIdType != '4'){
				layer.msg("房屋产权证件必须为2张！");
				return;
			}
			
			var propertyIdNo = $("#propertyIdNo").val();
			var owFlg = false;
			$(".coOwner").each(function(){
				var val = $(this).val();
				var houseOwnerName = $("#houseOwnerName").val()
				var houseOwnerIdNo = $("#houseOwnerIdNo").val()
				if (!val && !houseOwnerName && !houseOwnerIdNo) {
					layer.msg("请先填写房主！");
					owFlg = true;
					return false;
				}
			});
			if (owFlg) {
				return false;
			}
			// 房主信息是空时
			if (propertyIdType == "4") {
				if (!houseOwnerName && houseOwnerIdNo) {
					layer.msg("请填写完整的房主信息！");
					return false;
				}
			}
			var propertyIdType_old = $("#propertyIdType_old").val();
			var propertyIdNo_old = $("#propertyIdNo_old").val();
			
			if(propertyIdType!=propertyIdType_old||propertyIdNo!=propertyIdNo_old){
				if(!checkId(propertyIdNo)){
					layer.confirm(
						'房源已经存在，点击确认浏览房源信息，如需修改，请联系房主！',
						{
							btn : [ '确认', '返回']
						//按钮
						}, function() {
							history.go(-1) ;
						}, function() {
							//layer.msg('返回成功');
						});
				}else{
					layer.confirm(
						'您的修改内容将提交后台审核！',
						{
							btn : [ '确认', '返回']
						//按钮
						}, function() {
							var form = document.forms['edithouse'];
							form.action="${ctx}/house/update"
							form.submit();
						}, function() {
							//layer.msg('返回成功');
						});
				}
			}else{
				layer.confirm(
					'您的修改内容将提交后台审核！',
					{
						btn : [ '确认', '返回']
					//按钮
					}, function() {
						var form = document.forms['edithouse'];
						form.action="${ctx}/house/update";
						form.submit();
					}, function() {
						//layer.msg('返回成功');
					});
			}
		});
		
		// 设置非空验证flg = false删除required
		function setRequired($obj,flg) {
			if($obj[0]){
				var css = $obj.attr("Class");
				if (!flg){
					css = css.replace("required","");
					$obj.attr("Class", css);
				} else {
					if (css.indexOf(",") >= 0) {
						var cssArr = css.split(",");
						cssArr[0] = cssArr[0] + "required";
						$obj.attr("Class", cssArr.join(","));
					} else {
						var cssArr = css.split(",");
						cssArr[0] = "validate[required] "+ cssArr[0] ;
						$obj.attr("Class", cssArr.join(","));
					}
				}
			}
		}
		
		function checkId(propertyIdNo) {
			var propertyIdType=$("#propertyIdType").val();
			var houseAddr=$("#houseAddr").val();
			var flag = true;
			
			$.ajax({
				type: "GET",
				url: '${ctx}/house/cid',
				data: {
					houseAddr: houseAddr,
					propertyIdNo: propertyIdNo,
					propertyIdType: propertyIdType
				},
				dataType:'json',
				cache: false,
				async : false, 
				success: function(data){
					if(0 == data.success) {
						flag = false;
					}
				},
				error: function() {
					flag = false;
				}
			}); 
			
			return flag;
		};
		
		function dochange(btn) {
			var $btn = $(btn);
			
			$("#checkfileid").val($btn.val());
		};
		
		function checkNum(field){
			if (isNaN(jQuery.trim(field.val()))) {
				return "必须是纯数字";
			}
		};
		
		function checkIdType(){
			var propertyIdType=$("#propertyIdType").val();
			
			if (propertyIdType == 4) { 
				$("#propertyIdNo").removeClass().addClass("input-text");
				$('#houseadd').validationEngine('attach', { binded : false ,showOneMessage: true,maxErrorsPerField : 1,promptPosition: 'centerRight',addPromptClass: 'formError-noArrow formError-text'});
			}
		};
		
		function checkName(field){
			var m =  field.val().match(/^[\u4e00-\u9fa5]{2,15}$/i);
			if (!m) {
				return "必须2-15位汉字";
			}
		};
		
		function checkArea(field){
			var area = field.val();
			if (isNaN(jQuery.trim(area))) {
				return "必须是纯数字";
			}
			if(area.indexOf(".") > 0){
				var zheng = area.split(".")[0].length;
				var xiao = area.split(".")[1].length;
				if(area.split(".")[0] <7){
					return "建筑面积不能小于7";
				}
				
				if(zheng > 5){
					return "不能大于99999";
				}
				
				if(xiao > 2){
					return "小数位不能超过三位";
				}
			}else{
				if(area <7){
					return "建筑面积不能小于7";
				}
				
				if(area.length>5){
					return "不能大于99999";
				}
			}
		};
		/*
		 * 详细地址真实性验证
		 */
		function checkAddr(field){
			var addr = field.val();
			var success;
			$.ajax({
				type : "POST",
				url : 'https://restapi.amap.com/v3/geocode/geo',
				data : {
					address : addr,
					output : 'JSON',
					key : '7f792de5b31a9f403cc5dc39b595f197'
				},
				dataType : 'json',
				cache : false,
				async : false,
				success : function(data) {
					if (!data || data.geocodes.length == 0) {
						success = true;
					}
				}
			});
			if(success){
				return "请填写真实地址";
			}
		}
		function checkFile(obj){
			var photoExt=obj.val().substr(obj.val().lastIndexOf(".")).toLowerCase();//获得文件后缀名
			if(!(photoExt=='.jpg'||photoExt=='.png'||photoExt=='.bmp')){
				return "请上传后缀名为jpg/png/bmp的图片";
			}
		};
		
		function checkIdCard(obj) {
			var success = false;
			var idType = obj.parent("div").parent("div").find("select").eq(0).val();
			if (idType == 1) {
				var Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 ];// 加权因子;
				var ValideCode = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ];// 身份证验证位值，10代表X;
				if (obj.val().length == 18) {
					var a_idCard = obj.val().split("");// 得到身份证数组
					if (isValidityBrithBy18IdCard(obj.val()) && isTrueValidateCodeBy18IdCard(a_idCard)) {   
						success = true;
					} else {
						return "*身份证格式不正确";
					}
				} else {
					return "*身份证格式不正确";
				}
			} else {
				var partten = /^[\u4E00-\u9FA5\uf900-\ufa2d\w\.\s]{1,50}$/;
				if (!partten.test(obj.val())) {
					return "*证件编号至少1个字符,最多50个字符";
				} else {
					success = true;
				}
			}
			/* if (success) {
				var name = $("#houseOwnerName").val();
				return checkIdentity(name, idType, obj.val());
			} */
			
		}
		
		function isTrueValidateCodeBy18IdCard(a_idCard) {
			var sum = 0; // 声明加权求和变量   
			if (a_idCard[17].toLowerCase() == 'x') {
				a_idCard[17] = 10;// 将最后位为x的验证码替换为10方便后续操作 
			}   
			for ( var i = 0; i < 17; i++) {
				sum += Wi[i] * a_idCard[i];// 加权求和 
			}   
			valCodePosition = sum % 11;// 得到验证码所位置
			if (a_idCard[17] == ValideCode[valCodePosition]) {
				return true;
			}
			return false;
		}
		
		function isValidityBrithBy18IdCard(idCard18){
			var year = idCard18.substring(6,10);
			var month = idCard18.substring(10,12);
			var day = idCard18.substring(12,14);
			var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));
			// 这里用getFullYear()获取年份，避免千年虫问题
			if(temp_date.getFullYear()!=parseFloat(year) || temp_date.getMonth()!=parseFloat(month)-1 || temp_date.getDate()!=parseFloat(day)){
				return false;
			}
			return true;
		}
		
		function checkIdentity(name, idType, idNo) {
			if (name == '') {
				return true;
			}
			var success = false;
			$.ajax({
				type : "POST",
				url : '${ctx}/con/ckid',
				data : {
					name : name,
					idType : idType,
					idNo : idNo
				},
				dataType : 'json',
				cache : false,
				async : false,
				success : function(data) {
					if (1 == data.success) {
						success = true;
					}
				}
			});
			if (success) {
				return true;
			} else {
				return "*实名认证失败";
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