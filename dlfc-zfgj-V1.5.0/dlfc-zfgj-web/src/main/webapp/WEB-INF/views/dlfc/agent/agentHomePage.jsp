<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<!DOCTYPE HTML>
<html>
<head>

<%@ include file="/WEB-INF/views/include/head2.jsp"%>
<link
	href="${pageContext.request.contextPath}/static/hui/css/citySelector.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/static/hui/css/style-avatar.css"
	rel="stylesheet" type="text/css" />
<style type="text/css">
.cityinput {
	border-width: 1px;
	border-style: solid;
	border-color: #666 #ccc #ccc #666;
	height: 24px;
	line-height: 24px;
	width: 175px;
	font-size: 12px;
	padding-left: 2px;
	background:
		url(${pageContext.request.contextPath}/static/hui/images/searchIcon.png)
		no-repeat 150px 5px;
}
</style>
<title>添加管理员</title>
<c:if test="${flag == '1' || flag == '3'}">
	<script type="text/javascript">
		var Vcity = {};
		Vcity.allCity = ${comInfo};
	</script>
</c:if>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 经纪人首页 <a
			class="btn btn-success radius r mr-20"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.reload(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal"
			id="form-admin-add">
			<div class="responsive mb-30">
				<div class="contract_head agent-head cl">

					<div class="formControls col-8" style="border-left: 0;">
						<div class="row cl">
							<div class="formControls col-4">
								<c:if test="${empty avatarInfo.filePath}">
									<p class="user_photo text-c">
										<img
											src="${pageContext.request.contextPath}/static/hui/images/user.png"
											alt="..." class="thumbnail">
									</p>
									<p class="text-c">
										<a class="btn btn-link" id="avatar">上传头像</a>
									</p>
								</c:if>
								<c:if test="${!empty avatarInfo.filePath}">
									<p class="user_photo text-c">
										<img
											src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/img/${avatarInfo.filePath}?key=${accessKey}"
											alt="..." class="thumbnail">
									</p>
									<p class="text-c">
										<a class="btn btn-link" id="avatar">修改头像</a>
									</p>
								</c:if>
							</div>
							<div class="formControls col-8 cl">
								<div class="formConrtols col-7">
									<p class="agent-name">${name}
										<span class=" ico-rz agent-label-1 active" title="身份认证"></span>
										<span class="ico-rz agent-label-2 active" title="经纪人认证"></span>
									</p>
									<c:if test="${flag == '0' }">
										<p>初级经纪人</p>
										<p>
											<c:if test="${!empty phone}">${fn:substring(phone,0,3)}****${fn:substring(phone,7,11)}</c:if>
											<a class="Hui-iconfont c-orange" id="changeMobileBt">&#xe647;</a>
										</p>
									</c:if>
								</div>
								<c:if test="${flag == '0' }">
									<div class="formConrtols col-5 text-c" id="uploadForm">
										<div class="user-qr">
											<div>
												<c:if test="${empty sysErWeiMa.filePath}">
													<img
														src="${pageContext.request.contextPath}/static/hui/images/img_bg_grey_xl.png"
														alt="二维码" id="erImg" />
												</c:if>
												<c:if test="${!empty sysErWeiMa.filePath}">
													<img
														src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/img/${sysErWeiMa.filePath}?key=${accessKey}"
														alt="二维码" class="thumbnail" id="erImg">
												</c:if>
												<p class="text-c">
													<input type="file" name="image" class="hidden" id="file"
														value="" /> <a id="upload" class="btn btn-link" href="#">上传二维码</a>
												</p>
											</div>
										</div>
									</div>
								</c:if>
								<p class="cl"></p>
								<div class="panel panel-default mb-15 col-7">
									<div class="panel-body">
										<c:if test="${flag == '0' }">
											<c:if test="${empty compLogo.filePath}">
												<p class="agent-company-logo">
													<img
														src="${pageContext.request.contextPath}/static/hui/images/company-logo-s.png"
														width="180" height="79" alt="" />
												</p>
											</c:if>
											<c:if test="${!empty compLogo.filePath}">
												<p class="agent-company-logo">
													<img
														src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/${compLogo.filePath}?key=${accessKey}"
														width="180" height="79" alt="" />
												</p>
											</c:if>
											<p>
												<strong> ${comAll.agtCompInfo.name } </strong>
											</p>
											<p class="cl">${comAll.sysOffice.name}</p>
										</c:if>
										<c:if test="${flag == '1'}">
											<p class="cl">
												<span class="r"><a id="joinCompBt"
													class="btn btn-primary  size-MINI">加入公司</a> </span>
											</p>
										</c:if>
										<c:if test="${flag == '2'}">
											<div class="cl">
												<span class="label label-warning ml-5">${compName}公司审核中</span>
												<span class="r"><a id="reJoinCompBt"
													class="btn btn-primary  size-MINI"
													onclick="reJoinComp('${AgtUsrCompLogInfoId}');">取消申请</a> </span>
											</div>
										</c:if>
										<c:if test="${flag == '3'}">
											<div class="cl">
												<span class="label badge-danger ml-5">申请被拒绝</span>
											</div>
											<div class="cl">
												<span>拒绝原因:${reR}</span> <span class="r"><a
													id="joinCompBt" class="btn btn-primary  size-MINI">重新申请</a></span>
											</div>
										</c:if>
									</div>
								</div>
								<div class="formControls col-5">
									<p class=text-c>
										<a
											href="https://www.housecenter.cn/hall/form?id=bb6240bfb4ff42078036692db3e0ca01"
											target="_blank" class="btn-link">如何生成专属二维码？</a>
									</p>

								</div>
							</div>
						</div>
					</div>

					<c:if test="${flag == '0' }">
						<div class="formControls col-4 "
							style="min-height: 272px; padding-right: 0;">
							<div class="row cl">
								<div class="sign-in-box">
									<div class="row">
										<p class="text-c" style="margin-bottom: 10px">
											累计签到"<span class="c-blue">28</span>天"最高获得"<span class="c-red">30元</span>"现金奖励
										</p>
										<p class="text-c">
											<c:if test="${signFlag }">
												<p class="text-c">
													<a onclick="sign()" class="btn btn-success btn-sign-card">签到</a>
												</p>
											</c:if>
											<c:if test="${!signFlag }">
												<p class="text-c">
													<a class="btn btn-default disabled btn-sign-card">已签到</a>
												</p>
											</c:if>
										</p>
									</div>
									<div class="panel">
										<div class="panel-body">
											<table class="table">
												<tr class="text-c">
													<td><span
														class="${days >= 7 ?'sign-step sign-step-one finished':'sign-step sign-step-one' }">
													</span></td>
													<td><span
														class="${days >= 14 ?'sign-step sign-step-two finished':'sign-step sign-step-two' }">
													</span></td>
													<td><span
														class="${days >= 21 ?'sign-step sign-step-three finished':'sign-step sign-step-three' }">
													</span></td>
													<td><span
														class="${days >= 28 ?'sign-step sign-step-four finished':'sign-step sign-step-four' }">
													</span></td>
												</tr>
											</table>
											<table class="table">
												<tr class="text-c">
													<td>
														<p>
															<span>共累计签到</span><br /> <span class="c-red">${days }天</span>
														</p>
													</td>
													<td>
														<p>
															<span>共累计奖金</span><br /> <span class="c-red">${total }元</span>
														</p>
													</td>
													<c:if test="${allowFlag }">
														<c:if test="${aliFlag }">
															<td>
																<div class="pos-r" onmouseover="prizeshow()"
																	onmouseout="prizehide()"
																	style="width: 93px; margin: 0 auto;">
																	<a class="btn btn-default disabled radius">领取奖金</a>
																		<div class="deposit-tips" id="deposit-tips-prize">
																			<i class="deposit-tips-arrow"></i>
																			<p>已领奖，预计10个工作日内到账。</p>
																		</div>
																</div>
															</td>
														</c:if>
														<c:if test="${!aliFlag }">
															<td><a id="addalipay" class="btn btn-primary radius">领取奖金</a>
															</td>
														</c:if>
													</c:if>
													<c:if test="${!allowFlag }">
														<td>
															<div class="pos-r" onmouseover="signtipsshow()"
																onmouseout="signtipshide()"
																style="width: 93px; margin: 0 auto;">
																<a class="btn btn-default disabled radius">领取奖金</a>
																<div class="deposit-tips" style="width:110px;">
																	<i class="deposit-tips-arrow"></i>
																	<p>活动结束方可领奖</p>
																</div>
															</div>
														</td>
													</c:if>
												</tr>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</c:if>
				</div>
			</div>
	<div class="row cl">
		<div class="formControls col-8">
			<div class="row cl mb-15">
				<c:choose>
					<c:when test="${flag == '0' }">
						<div class="clbg-1 bk-gray pd-10">
							<a href="javascript:window.parent.entrust()"
								class="btn btn-primary radius mr-20">抢房源 <strong
								class="c-orange">(${entCount})</strong></a> <a
								href="javascript:window.parent.appointment()"
								class="btn radius btn-primary">验房预约 <strong class="c-orange">(${exaCount})</strong></a>
						</div>
					</c:when>
					<c:otherwise>
						<div class="clbg-1 bk-gray pd-10">
							<a href="#" class="btn btn-primary radius mr-20">抢房源<strong
								class="c-orange"></strong></a> <a href="#"
								class="btn radius btn-primary">验房预约<strong class="c-orange"></strong></a>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="row cl">
				<table
					class="table table-border table-bordered table-hover table-bg mb-20">
					<caption class="text-l">我的房源</caption>
					<thead>
						<tr class="text-c">
							<th>有效房源</th>
							<th>签合同房源</th>
							<th>等待审核</th>
							<th>审核未通过</th>
						</tr>
					</thead>
					<tbody>
						<tr class="text-c">
							<td><c:if test="${flag == '0' }">${actHouse}</c:if></td>
							<td><c:if test="${flag == '0' }">${conHouse}</c:if></td>
							<td><c:if test="${flag == '0' }">${wHouse}</c:if></td>
							<td><c:if test="${flag == '0' }">${unAuHouse}</c:if></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="row cl">
				<table
					class="table table-border table-bordered table-hover table-bg mb-20">
					<caption class="text-l">我的客源</caption>
					<thead>
						<tr class="text-c">
							<th>全部客源</th>
						</tr>
					</thead>
					<tbody>
						<tr class="text-c">
							<td><c:if test="${flag == '0' }">${cus}</c:if></td>
						</tr>
					</tbody>
				</table>
			</div>
			<table
				class="table table-border table-bordered table-hover table-bg mb-20">
				<caption class="text-l">我的合同</caption>
				<thead>
					<tr class="text-c">
						<th>我的合同</th>
						<th>即将到期</th>
					</tr>
				</thead>
				<tbody>
					<tr class="text-c">
						<td><c:if test="${flag == '0' }">${contract}</c:if></td>
						<td><c:if test="${flag == '0' }">${eContract}</c:if></td>
					</tr>
				</tbody>
			</table>
		</div>

		<div class="formControls col-4 pl-10">
			<div class="panel panel-default mb-20">
				<p class="panel-header">信用记录</p>
				<div class="panel-body">
					<div class="row cl pl-20">
						<div class="formControls">
							<div class="agent-leavel cl">
								<p class="l">信用等级：</p>
								<p class="borker-star <c:choose><c:when test="${ comAll.grade == 10 }">five</c:when>
        						<c:when test="${ comAll.grade == 8 }">four</c:when><c:otherwise>three</c:otherwise></c:choose> "></p>
							</div>
							<p>违规提醒：</p>
							<p>待处理的投诉：</p>
							<p>违规：</p>
							<p>虚假房源：</p>
						</div>
					</div>
				</div>
			</div>

			<div class="panel panel-default mb-20">
				<p class="panel-header">系统公告</p>
				<div class="panel-body">
					<ul>
						<c:forEach
							items="${fns:getNotice('66560778221d4e2da48bb5ed9be14886')}"
							var="notice" varStatus="status">
							<li class="cl"><span class="r"><fmt:formatDate
										value="${notice.updateDate}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
								<a onclick="noticeDetail('${notice.id}')" class="btn btn-link">
									${fns:myAbbr(notice.title,20)} </a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>
	</form>
	</div>
	<div id="joinComp" class="form_layer" style="display: none">
		<form id="joinCompSubmit" method="post">
			<input type="text" class="cityinput validate[required]"
				id="citySelect"> <a class="btn btn-primary radius mr-10"
				id="joinCompSave">提交</a>
		</form>
	</div>
	<div id="changeMobile" class="form_layer" style="display: none">
		<form id="changeMobileSubmit" class="form form-horizontal"
			method="post">
			<div class="row cl">
				<label class="form-label col-3">手机号:</label>
				<div class="formControls col-6">
					<input type="text"
						class="validate[required,custom[mobile]] input-text" id="mobile"
						placeholder="修改经纪人联系方式">
				</div>
				<div class="formControls col-3">
					<a class="btn btn-primary radius mr-10" onclick="getCapcha();">获取验证码</a>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-3">验证码:</label>
				<div class="formControls col-4">
					<input type="text"
						class="validate[required,custom[integer]] input-text" id="vCode"
						placeholder="请输入验证码">
				</div>
			</div>
			<div class="row cl">

				<div class="formControls col-3 col-offset-3">
					<a class="btn btn-primary radius mr-10" id="changeMobileSave">提交</a>
				</div>
			</div>
		</form>
	</div>
	<div class="containera" id="avatarDiv" style="display: none">
		<div class="imageBox">
			<div class="thumbBox"></div>
			<div class="spinner" style="display: none"></div>
		</div>
		<div class="action">
			<div class="new-contentarea tc">
				<a href="javascript:void(0)" class="upload-img"> <label
					for="upload-file">请先选择图片...</label>
				</a> <input type="file" class="" name="upload-file" id="upload-file" />
			</div>
			<input type="button" id="avatarButton" class="Btnsty_peyton"
				value="OK"> <input type="button" id="btnZoomIn"
				class="Btnsty_peyton" value="+"> <input type="button"
				id="btnZoomOut" class="Btnsty_peyton" value="-">
		</div>
		<div class="cropped"></div>
	</div>
	<div id="notice" class="pd-20" style="display: none;">
		<table class="table table-border table-bordered">
			<tbody>
				<tr>
					<td class="text-r" width="120">文章标题：</td>
					<td></td>
				</tr>
				<tr>
					<td class="text-r">发布时间：</td>
					<td></td>
				</tr>
				<tr>
					<td colspan="2"></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div id="notice" class="pd-20" style="display: none;">
		<table class="table table-border table-bordered">
			<tbody>
				<tr>
					<td class="text-r" width="120">文章标题：</td>
					<td></td>
				</tr>
				<tr>
					<td class="text-r">发布时间：</td>
					<td></td>
				</tr>
				<tr>
					<td colspan="2"></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div id="alidiv" style="display: none;">
		<form action="" method="post" class="form form-horizontal"
			id="signForm">
			<div class="row cl">
				<p>为保证您的资金安全到账，请填写与您注册账户名一致的账户信息。（提交后不能修改，请核对后提交）</p>
				<div class="row">
					<input type="text" class="input-text" value="${empName }"
						disabled="disabled" />
				</div>
				<div class="row" style="margin-top: 25px;">
					<input type="text" id="payAccount" name="payAccount"
						data-errormessage-value-missing="* 请输入支付宝帐号." maxlength="60"
						placeholder="请输入支付宝账号" class="input-text validate[required]">
				</div>
				<div class="row text-c">
					<input type="button" value="提交" onclick="saveAliNo()"
						class="btn btn-primary">
				</div>
			</div>
		</form>
	</div>
	<%@ include file="/WEB-INF/views/include/foot.jsp"%>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/static/hui/js/cropbox-min.js"></script>
	<c:if test="${flag == '1' || flag == '3'}">
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/static/hui/js/citySelector.js"></script>
	</c:if>
	<script type="text/javascript">
		<c:if test="${flag == '1' || flag == '3'}">
		var test = new Vcity.CitySelector({
			input : 'citySelect'
		});
		</c:if>
		$(document).ready(
				function() {
					$('#joinCompSubmit').validationEngine('attach', {
						binded : false,
						showOneMessage : true,
						maxErrorsPerField : 1,
						addPromptClass : 'formError-noArrow formError-text',
						promptPosition : 'centerRight:60,0'
					});
					$('#changeMobileSubmit').validationEngine('attach', {
						binded : false,
						showOneMessage : true,
						maxErrorsPerField : 1,
						addPromptClass : 'formError-noArrow formError-text',
						promptPosition : 'centerRight:60,0'
					});
					$('#joinCompSave').click(
							function() {
								var success = jQuery('#joinCompSubmit')
										.validationEngine('validate');
								if (success) {
									$('#joinCompSave').unbind("click");
									doSave();
								}
							});
					$('#changeMobileSave').click(
							function() {
								var success = jQuery('#changeMobileSubmit')
										.validationEngine('validate');
								if (success) {
									$('#changeMobileSave').unbind("click");
									doChangeMobile();
								}
							});

					$('#signForm').validationEngine('attach', {
						binded : false,
						showOneMessage : true,
						maxErrorsPerField : 1,
						addPromptClass : 'formError-noArrow formError-text',
						promptPosition : 'topLeft:0,0'
					});
				});

		$('#joinCompBt').on('click', function() {
			layer.open({
				type : 1, //page层
				area : [ '360px', '350px' ],
				title : "加入公司",
				shade : 0.6, //遮罩透明度
				moveType : 1, //拖拽风格，0是默认，1是传统拖动
				shift : 0, //0-6的动画形式，-1不开启
				content : $('#joinComp')
			});
			$("#joinComp").show();
		})

		$('#changeMobileBt').on('click', function() {
			layer.open({
				type : 1, //page层
				area : [ '420px', '260px' ],
				title : "修改经纪人联系方式",
				shade : 0.6, //遮罩透明度
				moveType : 1, //拖拽风格，0是默认，1是传统拖动
				shift : 0, //0-6的动画形式，-1不开启
				content : $('#changeMobile')
			});
			$("#changeMobile").show();
		})

		$('#avatar').on('click', function() {
			layer.open({
				type : 1, //page层
				area : [ '1000px', '600px' ],
				title : "头像修改",
				shade : 0.6, //遮罩透明度
				moveType : 1, //拖拽风格，0是默认，1是传统拖动
				shift : 0, //0-6的动画形式，-1不开启
				content : $('#avatarDiv')
			});
			$("#avatarDiv").show();
		})

		function noticeDetail(id) {
			$.post("${ctx}/article/detailAjax", {
				id : id
			}, function(data) {
				if (data && data.id) {
					var trs = $("#notice").find("tr");
					trs.eq(0).find("td").eq(1).text(data.title);
					trs.eq(1).find("td").eq(1).text(
							new Date(data.updateDate)
									.Format("yyyy-MM-dd hh:mm:ss"));
					trs.eq(2).find("td").eq(0).html(data.content);
				}
			});
			//公告弹出框
			_didx = layer.open({
				type : 1, //page层
				area : [ '75%', '85%' ],
				title : '公告详细',
				shade : 0.6, //遮罩透明度
				moveType : 1, //拖拽风格，0是默认，1是传统拖动
				shift : 0, //0-6的动画形式，-1不开启
				content : $('#notice')
			});
		}
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
						layer.msg(data.message, {
							icon : 1
						});
						setTimeout(function() {
							window.location.href = "${ctx}/agt/index";
						}, 1000);
					} else {
						layer.alert(data.message);
					}
				},
				error : function() {
					layer.alert("网络异常，请稍后重试！")

				}
			});
		}

		function getCapcha() {
			var phone = $("#mobile").val();
			$.ajax({
				type : "POST",
				url : 'vc',
				data : {
					phone : phone
				},
				dataType : 'json',
				cache : false,
				success : function(data) {
					if (1 == data.success) {
						layer.msg(data.message);
					} else {
						layer.alert(data.message);
					}
				},
				error : function() {
					layer.alert("网络异常，请稍后重试！")

				}
			});
		}

		function doChangeMobile() {
			var phone = $("#mobile").val();
			var vCode = $("#vCode").val();
			$.ajax({
				type : "POST",
				url : 'cm',
				data : {
					phone : phone,
					vCode : vCode
				},
				dataType : 'json',
				cache : false,
				success : function(data) {
					if (1 == data.success) {
						layer.msg(data.message, {
							icon : 1
						});
						setTimeout(function() {
							window.location.href = "${ctx}/agt/index";
						}, 1000);
					} else {
						layer.alert(data.message);
					}
				},
				error : function() {
					layer.alert("网络异常，请稍后重试！")

				}
			});
		}

		$(window)
				.load(
						function() {
							var options = {
								thumbBox : '.thumbBox',
								spinner : '.spinner',
								imgSrc : ''
							}
							var cropper = $('.imageBox').cropbox(options);
							var img = "";
							$('#upload-file').on('change', function() {
								var reader = new FileReader();
								reader.onload = function(e) {
									options.imgSrc = e.target.result;
									cropper = $('.imageBox').cropbox(options);
									//getImg();
								}
								reader.readAsDataURL(this.files[0]);
								//this.files = [];
								getImg();
							})

							$('#avatarButton')
									.on(
											'click',
											function() {
												//alert($("#avatar64")[0].src);
												if ($("#avatar64")[0] == undefined) {
													layer.alert("请选择图片");
												}
												var avatarSrc = $("#avatar64")[0].src;
												$
														.ajax({
															type : "POST",
															url : 'avas',
															data : {
																avatarSrc : avatarSrc
															},
															dataType : 'json',
															cache : false,
															success : function(
																	data) {
																if (1 == data.success) {
																	layer
																			.msg(
																					data.message,
																					{
																						icon : 1
																					});
																	setTimeout(
																			function() {
																				window.location.href = "${ctx}/agt/index";
																			},
																			1000);
																} else {
																	layer
																			.alert(data.message);
																}
															},
															error : function(
																	data) {
																layer
																		.msg("通讯失败，请稍后再试。");
															}
														});
											})

							function getImg() {

								setTimeout(
										function() {
											img = cropper.getDataURL();
											$('.cropped').html('');
											$('.cropped')
													.append(
															'<img src="'+img+'" align="absmiddle" style="width:120px;height:180px;margin-top:4px;"><p>180px*120px</p>');
											$('.cropped')
													.append(
															'<img src="'+img+'" align="absmiddle" style="width:60px;height:120px;margin-top:4px;"><p>120px*60px</p>');
											$('.cropped')
													.append(
															'<img src="'+img+'" id="avatar64" align="absmiddle" style="width:30px;height:60px;margin-top:4px;"><p>80px*30px</p>');
										}, 500);
							}

							$(".imageBox").on("mouseup", function() {
								getImg();
							});

							$('#btnZoomIn').on('click', function() {
								cropper.zoomIn();
							})
							$('#btnZoomOut').on('click', function() {
								cropper.zoomOut();
							})
						});

		function reJoinComp(id) {
			layer.confirm('是否取消申请', {
				btn : [ '确认', '返回' ]
			//按钮
			}, function() {
				$.ajax({
					type : "POST",
					url : 'rejc',
					data : {
						id : id
					},
					dataType : 'json',
					cache : false,
					success : function(data) {
						if (1 == data.success) {
							layer.msg(data.message, {
								icon : 1
							});
							setTimeout(function() {
								window.location.href = "${ctx}/agt/index";
							}, 1000);
						} else {
							layer.alert(data.message, {
								icon : 5
							});
						}
					},
					error : function() {
						layer.msg("网络异常，请稍后重试！", {
							icon : 5
						});
					}
				});
			}, function() {
			});
		}
		Date.prototype.Format = function(fmt) { //author: meizz 
			var o = {
				"M+" : this.getMonth() + 1, //月份 
				"d+" : this.getDate(), //日 
				"h+" : this.getHours(), //小时 
				"m+" : this.getMinutes(), //分 
				"s+" : this.getSeconds(), //秒 
				"q+" : Math.floor((this.getMonth() + 3) / 3), //季度 
				"S" : this.getMilliseconds()
			//毫秒 
			};
			if (/(y+)/.test(fmt))
				fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
						.substr(4 - RegExp.$1.length));
			for ( var k in o)
				if (new RegExp("(" + k + ")").test(fmt))
					fmt = fmt.replace(RegExp.$1,
							(RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k])
									.substr(("" + o[k]).length)));
			return fmt;
		}

		//上传二维码图片
		$('#upload').on('click', function() {
			$('#file').click();
		});
		$('#file')
				.on(
						'change',
						function() {
							//上传文件对象
							var formData = new FormData();
							formData.append('file', $('#file')[0].files[0]);
							formData.append('eid', "${comAll.id}");
							$
									.ajax({
										url : '${ctx}/agt/erwei',
										type : 'POST',
										cache : false,
										data : formData,
										processData : false,
										contentType : false
									})
									.done(
											function(res) {
												if (res.success == 1) {
													var src1 = "${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/img/";
													var src2 = res.data.filepath;
													$("#erImg").attr('src',
															src1 + src2);
													layer.alert(res.message);
												} else {
													layer.alert(res.message);
												}
											}).fail(function(res) {
										layer.msg("通讯失败，请稍后再试。");
									});
						});

		function sign() {
			$.ajax({
				type : "POST",
				url : '${ctx}/agt/sign',
				dataType : 'json',
				cache : false,
				success : function(data) {
					layer.msg(data.message);
					setTimeout(function() {
						window.location.href = "${ctx}/agt/index";
					}, 1000);
				},
				error : function() {
					layer.msg("通讯失败，请稍后再试。");
				}
			});
		}

		function signtipsshow() {
			$(".deposit-tips").css('display', 'block');
		}
		function signtipshide() {
			$(".deposit-tips").css('display', 'none');
		}

		function prizeshow() {
			$("#deposit-tips-prize").css('display', 'block');
		}
		function prizehide() {
			$("#deposit-tips-prize").css('display', 'none');
		}

		var layer_sign;
		$('#addalipay').on('click', function() {
			layer_sign = layer.open({
				type : 1, //page层
				area : [ '420px', '300px' ],
				title : "添加支付宝账户",
				shade : 0.6, //遮罩透明度
				moveType : 1, //拖拽风格，0是默认，1是传统拖动
				shift : 0, //0-6的动画形式，-1不开启
				content : $('#alidiv')
			});
		})

		function saveAliNo() {
			var success = $("#signForm").validationEngine("validate");
			if (!success) {
				return;
			}
			$.ajax({
				type : "POST",
				url : '${ctx}/agt/savealino',
				data : "payAccount="
						+ encodeURIComponent($("#payAccount").val()),
				dataType : 'json',
				cache : false,
				success : function(data) {
					if (1 == data.success) {
						layer.msg(data.message);
						layer.close(layer_sign);
						setTimeout(function() {
							window.location.href = "${ctx}/agt/index";
						}, 1000);
					} else {
						layer.msg(data.message);
					}
				},
				error : function() {
					layer.msg("通讯失败，请稍后再试。");
				}
			});
		}
	</script>

</body>
</html>