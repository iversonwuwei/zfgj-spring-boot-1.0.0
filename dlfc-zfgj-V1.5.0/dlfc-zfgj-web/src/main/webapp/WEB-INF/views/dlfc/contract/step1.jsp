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
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		租房合同 <span class="c-gray en">&gt;</span> 新建合同<a
			class="btn btn-success radius r mr-20"
			style="line-height: 1.6em; margin-top: 3px"
			onclick="javascript:location.replace(location.href);"
			href="#" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<form action="${ctx}/con/step1" method="get" id="getForm">
		<input type="hidden" id="cid" name="cid" value="${con.id}">
	</form>
	<div class="pd-20">
		<form action="${ctx}/con/step1" method="post" id="step1Form"
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
					<div class="formControls col-3" id="lessorIdNoDiv">
						<form:input path="con.lessorIdNo" id="lessorIdNo" placeholder="证件编号" class="input-text validate[required,funcCall[checkIdCard]]" data-prompt-target="lessorError" data-prompt-position="inline" />
					</div>
					<div id="lessorError" class="formControls cl col-3">
					</div>
				</div>
			</div>
			<%-- <div class="panel panel-default mb-30 mt-20 hide" id="lessorBCEditDiv">
				<div class="panel-header">&nbsp;</div>
				<div class="panel-body">
					<div class="responsive">
						<div class="row cl">
							<label class="form-label col-3"><span class="c-red">*</span>手机：</label>
							<div class="formControls col-3">
								<span id="lessorMobileSpan">${con.lessorMobile}</span>
								<form:input path="con.lessorMobile" id="lessorMobile" placeholder="手机" class="input-text validate[required,custom[mobile]]"/>
							</div>
						</div>
						<div class="row cl">
							<label class="form-label col-3"><span class="c-red">*</span>银行卡号：</label>
							<div class="formControls col-4">
								<span id="lessorBankNumSpan" class="form-text">${con.userBankcardList[0].bankNum}</span>
								<form:input path="con.userBankcardList[0].bankNum" id="lessorBankNum" placeholder="银行卡号" class="input-text validate[required,funcCall[checkBankCardLen]]" data-prompt-target="lnError" data-prompt-position="inline"/>
							<p><a id="tipCard"  class="btn-link" title="请添加以您自己姓名开通的储蓄卡或信用卡" onclick="displayBank();">支持银行卡</a></p>
							</div>
							<div class="form_text text-l col-3" id="lessorLinkDiv"><a class="btn btn-link" onclick="showEditEl('lessor')">修改银行卡</a>
							<p class="form_text" id="lnError"></p>
							</div>
						</div>
						
					</div>
				</div>
			</div> --%>
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
						<form:input path="con.lessorBackupName" id="lessorName" class="input-text validate[maxSize[10]]" placeholder="备用联系人姓名"/>
					</div>
				</div>
			</div>
			<div class="responsive">
				<div class="row cl">
					<label class="form-label col-3"><span class="c-red"></span>电话：</label>
					<div class="formControls col-3">
						<form:input path="con.lessorBackupTel" id="lessorBackupTel" class="input-text validate[maxSize[50],custom[mobile]]" placeholder="电话"/>
					</div>
				</div>
			</div>
			<!-- 代理人功能暂时删除 未来不知是否会开放
			<div class="responsive">
				<div class="row cl">
					<label class="form-label col-2"></label>
					<div class="formControls">
						<div class="radio-box skin-minimal">
							<input type="radio" id="proxyNo" name="proxyFlg" value="0" onclick="hideProxyDiv()" />
							<label for="proxyNo">本人签约</label>
						</div>
						<div class="radio-box skin-minimal">
							<input type="radio" id="proxyYes" name="proxyFlg" value="1" onclick="showProxyDiv()" />
							<label for="proxyYes">代理人签约</label>
						</div>
					</div>
				</div>
			</div> -->
			<%-- <div class="responsive hide" id="proxyDiv">
				<div class="row cl">
					<label class="form-label col-3"><span class="c-red">*</span>代理人：</label>
					<div class="formControls col-3">
						<form:input path="con.proxyName" id="proxyName" placeholder="代理人" onchange="checkIdNo('#proxyName','#proxyCardNo')" class="input-text validate[required,minSize[1],maxSize[100]]"/>
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-3"><span class="c-red">*</span>身份证号：</label>
					<div class="formControls col-3">
						<form:input path="con.proxyCardNo" id="proxyCardNo" placeholder="证件编号" class="input-text validate[required,funcCall[checkIdCard]]"/>
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-3"><span class="c-red">*</span>代理协议：</label>
					<div class="formControls col-3">
						<div id="uploader" class="wu-example">
							<!--用来存放文件信息-->
							<div id="fileList" class="upload_contract">
								<c:if test="${!empty con.infoAttList[0].filePath && !empty con.infoAttList[0].fileRealName && !empty con.infoAttList[0].fileName}">
								<div id="WU_FILE_0" class="file-item thumbnail">
									<a href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/img/${con.infoAttList[0].filePath}" class="fancybox"><img src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/img/${con.infoAttList[0].filePath}" /></a>
									<div class="info" title=" ${con.infoAttList[0].fileRealName} ">${con.infoAttList[0].fileRealName}</div>
								</div>
								</c:if>
							</div>
							<div class="btns">
								<div id="filePicker">选择图片</div>
							</div>
						</div>
					</div>
					<div class="formControls col-3">
						<input type="text" name="cert" value="1" class="validate[funcCall[checkImg]] opacity0"/>
					</div>
				</div>
			</div> --%>


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
						<form:input path="con.lesseeIdNo" id="lesseeIdNo" placeholder="证件编号" class="input-text validate[required,funcCall[checkIdCard]]" data-prompt-target="lesseeError" data-prompt-position="inline" />
					</div>
					<div id="lesseeError" class="formControls cl col-3">
					</div>
				</div>
			</div>
			<%-- <div class="panel panel-default mb-30 mt-20 hide" id="lesseeBCEditDiv">
				<div class="panel-header">&nbsp;</div>
				<div class="panel-body">
					<div class="responsive">
						<div class="row cl">
							<label class="form-label col-3"><span class="c-red">*</span>手机：</label>
							<div class="formControls col-3">
								<span id="lesseeMobileSpan">${con.lesseeMobile}</span>
								<form:input path="con.lesseeMobile" id="lesseeMobile" placeholder="手机" class="input-text validate[required,custom[mobile]]" />
							</div>
						</div>
						<div class="row cl">
							<label class="form-label col-3"><span class="c-red">*</span>银行卡号：</label>
							<div class="formControls col-4">
								<span id="lesseeBankNumSpan">${con.userBankcardList[1].bankNum}</span>
								<form:input path="con.userBankcardList[1].bankNum" id="lesseeBankNum" placeholder="银行卡号" class="input-text validate[required,funcCall[checkBankCardLen]]" data-prompt-target="lnsError" data-prompt-position="inline"/>
							<p><a id="tipCard"  class="btn-link" title="请添加以您自己姓名开通的储蓄卡或信用卡" onclick="displayBank();">支持银行卡</a></p>
							</div>
							<div class="form_text text-l col-3" id="lesseeLinkDiv"><a class="btn btn-link" onclick="showEditEl('lessee')">修改银行卡</a>
								<p class="form_text" id="lnsError"></p>
							</div>
						</div>
						
					</div>
				</div>
			</div> --%>
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
					<label class="form-label col-3"><span class="c-red"></span>电话：</label>
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
	<div id="allpaySupportBankListTip" style="display:none;">
			${allpaySupportBankList} 
	</div>
	<%@ include file="/WEB-INF/views/include/foot.jsp"%>
	<!-- 页面特有js -->
	<script type="text/javascript"
		src="${ctxStatic}${hui}lib/layer/1.9.3/layer.js"></script>
	<script type="text/javascript" src="${ctxStatic}${hui}lib/icheck/jquery.icheck.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}${hui}lib/webuploader/0.1.5/webuploader.js"></script>
	<script type="text/javascript" src="${ctxStatic}${hui}lib/webuploader/0.1.5/webuploaderdlfc.js"></script>
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
			/* $('.fancybox').fancybox();
			if ('${con.id}' != '') {
				$("#lessorBCEditDiv").show();
				$("#lesseeBCEditDiv").show();
			}
			if ('${con.lesseeMobile}' == '') {
				$("#lessorMobileSpan").css("display", "none");
			} else {
				$("#lessorMobile").css("display", "none");
			}
			if ('${con.userBankcardList[0].bankType}' == '') {
				$("#lessorBankTypeSpan").css("display", "none");
			} else {
				$("#lessorBankTypeSpan").text($("#lessorBankType").find("option:selected").text());
				$("#lessorBankTypeSelSpan").css("display", "none");
			}
			if ('${con.userBankcardList[0].bankNum}' == '') {
				$("#lessorBankNumSpan").css("display", "none");
				$("#lessorLinkDiv").css("display", "none");
			} else {
				$("#lessorBankNum").css("display", "none");
			}
			if ('${con.lesseeMobile}' == '') {
				$("#lesseeMobileSpan").css("display", "none");
			} else {
				$("#lesseeMobile").css("display", "none");
			}
			if ('${con.userBankcardList[1].bankType}' == '') {
				$("#lesseeBankTypeSpan").css("display", "none");
			} else {
				$("#lesseeBankTypeSpan").text($("#lesseeBankType").find("option:selected").text());
				$("#lesseeBankTypeSelSpan").css("display", "none");
			}
			if ('${con.userBankcardList[1].bankNum}' == '') {
				$("#lesseeBankNumSpan").css("display", "none");
				$("#lesseeLinkDiv").css("display", "none");
			} else {
				$("#lesseeBankNum").css("display", "none");
			}
			if ($("#lessorBCEditDiv").is(":visible") && $("#lesseeBCEditDiv").is(":visible")) {
				$("#submitBtn").removeClass().addClass("btn btn-primary radius");
			} */
			
			$("#step1Form").validationEngine('attach', {
				showOneMessage: true,
				maxErrorsPerField : 1,
				addPromptClass: 'formError-noArrow formError-text',
				promptPosition: 'centerRight'
			});
			
			uploader = WebUploader.create({
				pick : {
					id : '#filePicker',
					multiple : false
				},
				swf : '${ctxStatic}${hui}lib/webuploader/0.1.5/Uploader.swf',
				server : '${ctx}/con/upload',//上传的URL
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
				//后台验证失败（文件大小 文件类型验证）
				if (arguments[1].success == 0) {
					layer.alert(arguments[1].message, {icon : 5});
				} else if (arguments[1].success == 1) {//上传临时文件夹成功后的操作写在这里 返回filepath和filename
					$("#proxyFilePath").val(arguments[1].data.filePath);
					$("#proxyFileName").val(arguments[1].data.fileName);
					$("#proxyFileRealName").val(arguments[1].data.fileRealName);
				}
			});
			
			initUploader(uploader, 300, 300, "#fileList");
		});
			
		function getBankcard(person) {
			var name = $("#"+person+"Name").val();
			var idType = "1";
			var idNo = $("#"+person+"IdNo").val();
			if (!$("#"+person+"Name").validationEngine('validate') || !$("#"+person+"IdNo").validationEngine('validate')) {
				/* $("#"+person+"BCEditDiv").css("display", "none"); */
				return; 
			}else if($("#lesseeIdType").val() == $("#lessorIdType").val() && 
					$("#lesseeIdNo").val() == $("#lessorIdNo").val()){
				return layer.alert("出租方跟承租方不能是同一人", {
					icon : 5
				});
			}
			
			
			$.ajax({
				type : "POST",
				url : '${ctx}/con/gbc',
				data : {
					name : name,
					idType : idType,
					idNo : idNo
				},
				dataType : 'json',
				cache : false,
				success : function(data) {
					if (1 == data.success) {
						/* $("#"+person+"BCEditDiv").css("display", "block");
						$("#"+person+"BankNum").val(data.data.bankNum);
						$("#"+person+"BankNumSpan").text(data.data.bankNum);
						$("#"+person+"MobileSpan").text(data.data.mobile); */
						var mobile =$("#"+person+"Mobile").val(data.data.mobile);
						/* if (data.data.mobile == '') {
							$("#"+person+"MobileSpan").css("display", "none");
							$("#"+person+"Mobile").css("display", "block");
						} else {
							$("#"+person+"MobileSpan").css("display", "block");
							$("#"+person+"Mobile").css("display", "none");
						} 
						if (data.data.bankNum == '') {
							$("#"+person+"BankNumSpan").css("display", "none");
							$("#"+person+"BankNum").css("display", "block");
							$("#"+person+"LinkDiv").css("display", "none");
						} else {
							$("#"+person+"BankNumSpan").css("display", "block");
							$("#"+person+"BankNum").css("display", "none");
							$("#"+person+"LinkDiv").css("display", "block");
						}
						if ($("#lessorBCEditDiv").is(":visible") && $("#lesseeBCEditDiv").is(":visible")) {
							$("#submitBtn").removeClass().addClass("btn btn-primary radius");
						}*/
					} else {
						 disableSubmitBtn();
						 /*$("#"+person+"BCEditDiv").hide();
						$("#"+person+"BankNum").val('');
						$("#"+person+"Mobile").val(''); */
						layer.alert(data.message, {
							icon : 5
						});
					}
				},
				error : function() {
					disableSubmitBtn();
					$("#"+person+"BCEditDiv").css("display", "none");
					layer.alert("实名认证失败！", {
							icon : 5
						});
				}
			});
		}

		function submitForm(type) {
			var success = jQuery('#step1Form').validationEngine('validate');
			if(success){
				disableSubmitBtn();
			} 
			$("#step1Form").submit();
		}
		
		function showEditEl(val) {
			$("#"+val+"BankTypeSpan").css("display", "none");
			$("#"+val+"BankTypeSelSpan").css("display", "block");
			$("#"+val+"BankNumSpan").css("display", "none");
			$("#"+val+"BankNum").css("display", "block");
		}
		
		function showProxyDiv() {
			$("#proxyDiv").show();
			if (refreshTime == 1) {
				uploader.refresh();
				refreshTime = 2;
			}
		}
		
		function hideProxyDiv() {
			$("#proxyDiv").hide();
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
			if (success) {
				var nameObj = obj.attr("id").replace('IdNo', '').replace('CardNo', '');
				var name = $("#" + nameObj + "Name").val();
				return checkIdentity(name, idType, obj.val());
			} 
			
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
			var message;
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
						$("#submitBtn").removeClass().addClass("btn btn-primary radius");
						success = true;
					} else {
						message = data.message;
					}
				}
			});
			if (success) {
				return true;
			} else {
				disableSubmitBtn()
				return "*" + message;
			}
		}
		
		function checkImg() {
			if ($("#proxyFilePath").val() =='' || $("#proxyFileName").val() == '' || $("#proxyFileRealName").val() == '') {
				return "*请上传代理附件";
			}
			return true;
		}
		
		/**
		增加对银行卡长度的验证
		*/
		function checkBankCardLen(elem)
		{
			var len=elem.val().length;
			if(16!=len&&19!=len&&18!=len)
			{
				return "*卡号为16或18或19位";
			}
			return true;
		}
		
		/**
		显示可使用银行卡
		*/
		var $allpaySupportBankListTip=$("#allpaySupportBankListTip");
		function displayBank(){
			var $self=$(this);
			layer.open({
				type: 1, //page层
				area: ['600px', '500px'],
				title: "支持银行卡",
				shade: 0.6, //遮罩透明度
				moveType: 1, //拖拽风格，0是默认，1是传统拖动
				shift: 0, //0-6的动画形式，-1不开启
				content: $allpaySupportBankListTip,
				cancel : function(index){
					$allpaySupportBankListTip.hide();
				}
			});
			$allpaySupportBankListTip.show();
		}
		
	</script>
</body>
</html>