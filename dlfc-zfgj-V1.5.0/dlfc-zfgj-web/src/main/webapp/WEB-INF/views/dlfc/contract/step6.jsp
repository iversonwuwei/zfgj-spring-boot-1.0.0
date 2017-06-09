<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head2.jsp"%>
<!-- 页面特有css -->
<link href="${ctxStatic}${hui}lib/webuploader/0.1.5/webuploader.css"
	rel="stylesheet" type="text/css" />
<link href="${ctxStatic}${hui}lib/icheck/icheck.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctxStatic}${hui}lib/fancybox/jquery.fancybox.css?v=2.1.5" media="screen" />
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>租房合同 <span class="c-gray en">&gt;</span> 
		新建合同<a class="btn btn-success radius r mr-20" style="line-height: 1.6em; margin-top: 3px" onclick="javascript:$('#getForm').submit();"
		href="#" title="刷新"><i class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<form action="${ctx}/contp/step1" method="get" id="getForm">
		<input type="hidden" id="cid" name="cid" value="${con.id}">
	</form>
	<div class="pd-20">
		<form action="${ctx}/contp/step1" method="post" id="step1Form"
			class="form form-horizontal">
			<form:hidden path="con.id" />
			<c:if test="${con.id != ''}">
				<form:hidden path="con.version" />
			</c:if>
			<legend>出租方</legend>
			<div class="responsive">
				<div class="row cl">
					<label class="form-label col-3"><span class="c-red">*</span>姓名：</label>
					<div class="formControls col-3">
						<form:input path="con.lessorName" id="lessorName" onchange="checkIdNo('#lessorName','#lessorIdNo')" class="input-text validate[required,maxSize[100]]" placeholder="姓名"/>
					</div>
				</div>
			</div>
			<div class="responsive">
				<div class="row cl">
					<label class="form-label col-3"><span class="c-red">*</span>身份证号：</label>
					<div class="formControls col-3">
						<form:input path="con.lessorIdNo" id="lessorIdNo" placeholder="证件编号" class="input-text validate[required,funcCall[checkIdCard]]"/>
					</div>
				</div>
			</div>
			<div class="responsive">
				<div class="row cl">
					<label class="form-label col-3"><span class="c-red">*</span>手机：</label>
					<div class="formControls col-3">
						<form:input path="con.lessorMobile" id="lessorMobile" placeholder="手机" class="input-text validate[required,custom[mobile]]"/>
					</div>
				</div>
			</div>
			<div class="responsive">
				<div class="row cl">
					<label class="form-label col-3"><span class="c-red"></span>送达地址：</label>
					<div class="formControls col-3">
						<form:input path="con.lessorAddress" id="lessorName"  class="input-text validate[maxSize[500]]" placeholder="送达地址"/>
					</div>
					<span class="form-text">平台会陆续推出增值服务，请填写真实地址。</span>
				</div>
			</div>
			<div class="responsive">
				<div class="row cl">
					<label class="form-label col-3"><span class="c-red"></span>邮箱：</label>
					<div class="formControls col-3">
						<form:input path="con.lessorEmail" id="lessorName" class="input-text validate[maxSize[200],custom[email]]" placeholder="邮箱"/>
					</div>
				</div>
			</div>
			<div class="responsive">
				<div class="row cl">
					<label class="form-label col-3"><span class="c-red"></span>备用联系人姓名：</label>
					<div class="formControls col-3">
						<form:input path="con.lessorBackupName" id="lessorName" class="input-text validate[maxSize[200]]" placeholder="备用联系人姓名"/>
					</div>
				</div>
			</div>
			<div class="responsive">
				<div class="row cl">
					<label class="form-label col-3"><span class="c-red"></span>备用联系人电话：</label>
					<div class="formControls col-3">
						<form:input path="con.lessorBackupTel" id="lessorBackupTel" class="input-text validate[maxSize[50],custom[mobile]]" placeholder="电话"/>
					</div>
				</div>
			</div>
			<legend>承租方</legend>
			<div class="responsive">
				<div class="row cl">
					<label class="form-label col-3"><span class="c-red">*</span>姓名：</label>
					<div class="formControls col-3">
						<form:input path="con.lesseeName" id="lesseeName" placeholder="姓名"
							class="input-text validate[required,maxSize[100]]" onchange="checkIdNo('#lesseeName','#lesseeIdNo')"/>
					</div>
				</div>
			</div>
			<div class="responsive">
				<div class="row cl">
					<label class="form-label col-3"><span class="c-red">*</span>身份证号：</label>
					<div class="formControls col-3">
						<form:input path="con.lesseeIdNo" id="lesseeIdNo" placeholder="证件编号" class="input-text validate[required,funcCall[checkIdCard]]"/>
					</div>
				</div>
			</div>
			<div class="responsive">
				<div class="row cl">
					<label class="form-label col-3"><span class="c-red">*</span>手机：</label>
						<div class="formControls col-3">
							<form:input path="con.lesseeMobile" id="lesseeMobile" placeholder="手机" class="input-text validate[required,custom[mobile]]" />
						</div>
				</div>
			</div>
			<div class="responsive">
				<div class="row cl">
					<label class="form-label col-3"><span class="c-red"></span>送达地址：</label>
					<div class="formControls col-3">
						<form:input path="con.lesseeAddress" id="lessorName"  class="input-text validate[maxSize[500]]" placeholder="送达地址"/>
					</div>
					<span class="form-text">平台会陆续推出增值服务，请填写真实地址。</span>
				</div>
			</div>
			<div class="responsive">
				<div class="row cl">
					<label class="form-label col-3"><span class="c-red"></span>邮箱：</label>
					<div class="formControls col-3">
						<form:input path="con.lesseeEmail" id="lessorName" class="input-text validate[maxSize[200],custom[email]]" placeholder="邮箱"/>
					</div>
				</div>
			</div>
			<div class="responsive">
				<div class="row cl">
					<label class="form-label col-3"><span class="c-red"></span>备用联系人姓名：</label>
					<div class="formControls col-3">
						<form:input path="con.lesseeBackupName" id="lessorName" class="input-text validate[maxSize[200]]" placeholder="备用联系人姓名"/>
					</div>
				</div>
			</div>
			<div class="responsive">
				<div class="row cl">
					<label class="form-label col-3"><span class="c-red"></span>备用联系人电话：</label>
					<div class="formControls col-3">
						<form:input path="con.lesseeBackupTel" id="lessorName" class="input-text validate[maxSize[50],custom[mobile]]" placeholder="电话"/>
					</div>
				</div>
			</div>
			<input type="hidden" name="${tokenName}" value="${token }"/>
			<input type="hidden" name="tokenName" value="${tokenName}"/>
			<div class="row cl btn_group">
				<div class="col-9 col-offset-3">
					<a class="btn btn-primary radius" id="submitBtn" onclick="submitForm()">下一步</a>
				</div>
			</div>
		</form>
	</div>
	<%@ include file="/WEB-INF/views/include/foot.jsp"%>
	<!-- 页面特有js -->
	<script type="text/javascript"
		src="${ctxStatic}${hui}lib/layer/1.9.3/layer.js"></script>
	<script type="text/javascript" src="${ctxStatic}${hui}lib/icheck/jquery.icheck.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}${hui}lib/fancybox/jquery.fancybox.js?v=2.1.5"></script>
	<script type="text/javascript">
		var Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 ];// 加权因子;
		var ValideCode = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ];// 身份证验证位值，10代表X;
		var uploader;
		var refreshTime = 1;
		$(function() {
			$('.skin-minimal input').iCheck({
				checkboxClass: 'icheckbox-blue',
				radioClass: 'iradio-blue',
				increaseArea: '20%'
			});
			$('.fancybox').fancybox();
			
			$("#step1Form").validationEngine('attach', {
				binded : false,
				showOneMessage: true,
				maxErrorsPerField : 1,
				addPromptClass: 'formError-noArrow formError-text',
				promptPosition: 'centerRight'
			});
		});
		
		function submitForm() {
			var success = jQuery('#step1Form').validationEngine('validate');
			if(success){
				disableSubmitBtn();
			} 
			$("#step1Form").submit();
		}
		
		function checkIdCard(obj) {
			var success = false;
			var idType = 1;
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
			/* if (success) {
				var nameObj = obj.attr("id").replace('IdNo', '').replace('CardNo', '');
				var name = $("#" + nameObj + "Name").val();
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
		
		function checkIdNo(self, idNoObj) {
			var success = true;
			if (self != '') {
				success = $(self).validationEngine('validate');
			}
			if (success) {
				var idNoVal = $(idNoObj).val(); 
				if (idNoVal != "") {
					$(idNoObj).validationEngine('validate');
				}
			}
		}
		
		function disableSubmitBtn() {
			$("#submitBtn").removeClass().addClass("btn radius disabled");
		}
		
		function checkIdentity(name, idType, idNo) {
			if (name == '') {
				return true;
			}
			var success = false;
			$.ajax({
				type : "POST",
				url : '${ctx}/contp/ckid',
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
						return true;
					}else{
						return data.message;
					}
				}
			});
		}
		
	</script>
</body>
</html>