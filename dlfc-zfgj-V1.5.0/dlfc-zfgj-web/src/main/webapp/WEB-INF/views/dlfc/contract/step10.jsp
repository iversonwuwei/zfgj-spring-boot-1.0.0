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
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}${hui}lib/fancybox/jquery.fancybox.css?v=2.1.5"
	media="screen" />
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i>首页 <span class="c-gray en">&gt;</span>
		<c:choose>
			<c:when
				test="${(fns:getUser().roleList[0].roleType) ==  'emp-role' || (fns:getUser().roleList[0].roleType) ==  'assignment'}">合同管理</c:when>
			<c:otherwise>租房合同</c:otherwise>
		</c:choose>
		<span class="c-gray en">&gt;</span> 合同详情<a
			class="btn btn-success radius r mr-20"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="pd-20">
		<form action="${ctx}/contp/renewal" method="post"
			class="form form-horizontal" id="renewalForm">

			<c:choose>
				<c:when test="${isRenewal}">
					<input type="hidden" name="renewalCid" value="${con.id}" />
					<form:hidden path="con.leaseTermYear" id="leaseTermYearHid" />
					<form:hidden path="con.leaseTermMonth" id="leaseTermMonthHid" />
					<form:hidden path="con.monthlyRent" id="monthlyRentHid" />
					<form:hidden path="con.ownerBears" id="lesseeBearsHid" />
					<form:hidden path="con.ownerBearOther" id="lesseeBearOtherHid" />
				</c:when>
				<c:otherwise>
					<form:hidden path="con.id" />
					<form:hidden path="con.version" />
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${isRenewal}">
					<c:if test="${con.status == 1}">
						<div class="row cl text-r">
							<a class="btn-link" onclick="openDialog()">修改合同</a>
						</div>
					</c:if>
				</c:when>
				<c:otherwise>
					<c:if test="${con.status == 1}">
						<div class="row cl text-r">
							<a class="btn-link" href="${ctx}/contp/step1?cid=${con.id}">修改</a>
						</div>
					</c:if>
				</c:otherwise>
			</c:choose>
			<div class="responsive mb-30">
				<div
					class="contract_head <c:if test="${con.rentalMode == 1}">mh</c:if> cl">
					<p class="contract_til col-1-1">
						${con.houseAddr}<span class="label label-primary ml-10">${con.rentalModeName}</span>
					</p>
					<div class="formControls col-6" style="border-left: 0;">
						<div class="row cl">
							<div class="formControls col-6">
								<div class="row cl">
									<label class="form-label col-6">房屋性质：</label>
									<div class="form_text col-6">
										<strong>${con.propertyIdTypeName}</strong>
									</div>
								</div>
							</div>
							<div class="formControls col-6">
								<div class="row cl">
									<label class="form-label col-6">租赁用途：</label>
									<div class="form_text col-6">
										<strong>${con.rentalPurposeName}</strong>
									</div>
								</div>
							</div>
						</div>
						<div class="row cl">
							<div class="formControls col-6">
								<div class="row cl">
									<label class="form-label col-6">建筑面积：</label>
									<div class="form_text col-6">
										<strong>${con.buildingArea}平方米</strong>
									</div>
								</div>
							</div>
						</div>
						<c:if test="${con.rentalMode == 2}">
							<div class="row cl">
								<div class="formControls col-6">
									<div class="row cl">
										<label class="form-label col-6">出租区域：</label>
										<div class="form_text col-6">
											<strong>${con.leaseDomain}</strong>
										</div>
									</div>
								</div>
								<div class="formControls col-6">
									<div class="row cl">
										<label class="form-label col-6">出租面积：</label>
										<div class="form_text col-6">
											<strong>${con.leaseArea}平方米</strong>
										</div>
									</div>
								</div>
							</div>
						</c:if>
					</div>
					<div class="formControls col-4">
						<div class="row cl">
							<div class="formControls col-1-1">
								<c:choose>
									<c:when test="${isRenewal}">
										<div class="row cl">
											<label class="form-label col-offset-3 col-7"><strong
												id="timeLabel1"><fmt:formatDate
														value='${con.startTime}' pattern='yyyy-MM-dd' />至<fmt:formatDate
														value='${con.endTime}' pattern='yyyy-MM-dd' />（共
													${con.leaseTermYear * 12 + con.leaseTermMonth}个月）</strong></label>
										</div>
										<div class="row cl">
											<label class="form-label col-7">房屋租金：</label>
											<div class="form_text col-5">
												<strong class="c-red">${con.monthlyRent}</strong>元/月
											</div>
										</div>
									</c:when>
									<c:otherwise>
										<div class="row cl">
											<label class="form-label col-7">房屋租金：</label>
											<div class="form_text col-5">
												<strong class="c-red">${con.monthlyRent}</strong>元/月
											</div>
										</div>
										<div class="row cl">
											<label class="form-label col-7">中介费：</label>
											<div class="form_text col-5">
												<strong class="c-red">${con.agencyFee}</strong>元
											</div>
										</div>
									</c:otherwise>
								</c:choose>
								<div class="row cl">
									<label class="form-label col-7">押金：</label>
									<div class="form_text col-5">
										<strong class="c-red">${con.depositAmt}</strong>元
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="formControls col-2">
						<div class="row cl">
							<div class="row cl text-c">
								<c:choose>
									<c:when
										test="${con.status == 13 || con.status == 10 || con.status == 1}">
										<shiro:hasPermission name="emp:cont:add">
											<a class="btn btn-primary radius" onclick="confirmDialog()">确认下载</a>
										</shiro:hasPermission>
									</c:when>
									<c:otherwise>
										<shiro:hasPermission name="emp:cont:add">
											<a class="btn radius disabled">确认下载</a>
										</shiro:hasPermission>
									</c:otherwise>
								</c:choose>
							</div>
							<div class="row cl text-c">
								<c:choose>
									<c:when test="${con.status == 13 || con.status == 10}">
										<a class="btn btn-primary radius"
											onclick="openUploadDialog('600px', '400px')">上传合同</a>
									</c:when>
									<c:when
										test="${con.status == 4 || con.status == 5 || con.status == 6 || con.status == 7 || con.status == 8 || con.status == 9 }">
										<shiro:hasPermission name="emp:cont:add">
											<a class="btn btn-primary radius"
												onclick="openUploadDialog('600px', '350px')">查看上传</a>
										</shiro:hasPermission>
									</c:when>
									<c:otherwise>
										<a class="btn radius disabled">上传合同</a>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
				</div>
			</div>
			<c:if test="${con.status == 10}">
				<div class=" contract_fail" style="height: auto;">
					<i class="Hui-iconfont">&#xe6e0;</i>审核失败：${con.rejectReason}
				</div>
			</c:if>
			<legend>合同方</legend>
			<div class="panel panel-default mb-20">
				<div class="panel-body">
					<div class="responsive">
						<legend>出租方（以下称甲方）</legend>
						<div class="row cl">
							<label class="form-label col-3">姓名 ：</label>
							<div class="formControls col-3">
								<strong>${con.lessorName}</strong>
							</div>
						</div>
						<div class="row cl">
							<label class="form-label col-3">身份证件 ：</label>
							<div class="formControls col-3">
								<strong>${con.lessorIdTypeName} ${con.lessorIdNo}</strong>
							</div>
						</div>
						<div class="row cl">
							<label class="form-label col-3">电话 ：</label>
							<div class="formControls col-3">
								<strong>${con.lessorMobile}</strong>
							</div>
						</div>
						<div class="row cl">
							<label class="form-label col-3">送达地址 ：</label>
							<div class="formControls col-9">
								<strong>${con.lessorAddress}</strong>
							</div>
						</div>
						<div class="row cl">
				            <label class="form-label col-3"></label>
				            <div class="formControls form_text col-5 c-999">平台会陆续推出增值服务，请填写真实地址。</div>
				        </div>
						<div class="row cl">
							<label class="form-label col-3">邮箱 ：</label>
							<div class="formControls col-3">
								<strong>${con.lessorEmail}</strong>
							</div>
						</div>
						<div class="row cl">
							<label class="form-label col-3">备用联系人姓名 ：</label>
							<div class="formControls col-3">
								<strong>${con.lessorBackupName}</strong>
							</div>
						</div>
						<div class="row cl">
							<label class="form-label col-3">电话 ：</label>
							<div class="formControls col-3">
								<strong>${con.lessorBackupTel}</strong>
							</div>
						</div>
						<legend>承租方（以下称乙方）</legend>
						<div class="row cl">
							<label class="form-label col-3">姓名 ：</label>
							<div class="formControls col-3">
								<strong>${con.lesseeName}</strong>
							</div>
						</div>
						<div class="row cl">
							<label class="form-label col-3">身份证件 ：</label>
							<div class="formControls col-3">
								<strong>${con.lesseeIdTypeName} ${con.lesseeIdNo}</strong>
							</div>
						</div>
						<div class="row cl">
							<label class="form-label col-3">电话 ：</label>
							<div class="formControls col-3">
								<strong>${con.lesseeMobile}</strong>
							</div>
						</div>
						<div class="row cl">
							<label class="form-label col-3">送达地址 ：</label>
							<div class="formControls col-9">
								<strong>${con.lesseeAddress}</strong>
							</div>
						</div>
						<div class="row cl">
				            <label class="form-label col-3"></label>
				            <div class="formControls form_text col-5 c-999">平台会陆续推出增值服务，请填写真实地址。</div>
				        </div>
						<div class="row cl">
							<label class="form-label col-3">邮箱 ：</label>
							<div class="formControls col-3">
								<strong>${con.lesseeEmail}</strong>
							</div>
						</div>
						<div class="row cl">
							<label class="form-label col-3">备用联系人姓名 ：</label>
							<div class="formControls col-3">
								<strong>${con.lesseeBackupName}</strong>
							</div>
						</div>
						<div class="row cl">
							<label class="form-label col-3">电话 ：</label>
							<div class="formControls col-3">
								<strong>${con.lesseeBackupTel}</strong>
							</div>
						</div>
						<c:if test="${con.source == 2}">
							<legend> 居间方（以下称丙方） </legend>
							<div class="row cl">
								<label class="form-label col-3">公司名称 ：</label>
								<div class="formControls col-3">
									<strong>${con.agtEmpInfo.agtCompInfo.name}</strong>
								</div>
							</div>
							<div class="row cl">
								<label class="form-label col-3">签约人 ：</label>
								<div class="formControls col-3">
									<strong>${con.agtEmpInfo.sysPerson.name}</strong>
								</div>
							</div>
							<div class="row cl">
								<label class="form-label col-3">电话 ：</label>
								<div class="formControls col-3">
									<strong>${con.agtEmpInfo.usrUser.mobile}</strong>
								</div>
							</div>
						</c:if>
					</div>
				</div>
			</div>
			<legend>房屋所有权人信息</legend>
			<c:forEach var="owner" items="${con.houCoOwnerList}"
				varStatus="status">
				<div class="panel panel-primary mb-30 mt-20">
					<div class="panel-header">所有权人${status.count}</div>
					<div class="panel-body">
						<div class="responsive">
							<div class="row cl">
								<label class="form-label col-3">房屋所有权人姓名 ：</label>
								<div class="form_text col-2">${owner.sysPerson.name}</div>
							</div>
						</div>
						<div class="responsive">
							<div class="row cl">
								<label class="form-label col-3">有效身份证件：</label>
								<div class="form_text col-3">${owner.sysPerson.perIdTypeName}
									${owner.sysPerson.idNo}</div>
							</div>
						</div>
						<c:if test="${status.index == 0}">
						<div class="responsive">
							<div class="row cl">
								<label class="form-label col-3">手机：</label>
								<div class="form_text col-2">${con.lessorMobile}</div>
							</div>
						</div>
						</c:if>
					</div>
				</div>
			</c:forEach>
			<legend>实际使用人</legend>
			<c:forEach var="houUser" items="${con.houUserList}"
				varStatus="status">
				<div class="panel panel-primary mb-30 mt-20">
					<div class="panel-header">使用者${status.count}</div>
					<div class="panel-body">
						<div class="responsive">
							<div class="row cl">
								<label class="form-label col-3">姓名 ：</label>
								<div class="form_text col-2">${houUser.name}</div>
							</div>
						</div>
						<div class="responsive">
							<div class="row cl">
								<label class="form-label col-3">身份证件：</label>
								<div class="form_text col-3">${houUser.perIdTypeName}
									${houUser.idNo}</div>
							</div>
						</div>
						<div class="responsive">
							<div class="row cl">
								<label class="form-label col-3">手机：</label>
								<div class="form_text col-2">${houUser.mobile}</div>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
			<legend>租赁期限</legend>
			<div class="responsive">
				<div class="row cl">
					<label class="form-label col-3">起止时间 ：</label>
					<div class="formControls col-6" id="timeLabel2">
						<fmt:formatDate value='${con.startTime}' pattern='yyyy-MM-dd' />
						至
						<fmt:formatDate value='${con.endTime}' pattern='yyyy-MM-dd' />
						（共 ${con.leaseTermYear * 12 + con.leaseTermMonth}个月）
					</div>
				</div>
				<label class="form-label col-3">交付使用 ：</label>
				<div class="form_text col-9">甲方须在<fmt:formatDate value='${con.startTime}' pattern='yyyy年MM月dd日' />之前将房屋及室内附属设备（详见附件一《房屋交割清单》）交付乙方使用。《房屋交割清单》经甲乙双方交验签署并移交房门钥匙后视为交付完成。</div>
			</div>

			<legend>租金</legend>
			<div class="responsive">
				<div class="row cl">
					<label class="form-label col-3">月租金：</label>
					<div class="form_text col-2" id="monthlyRentDiv">${con.monthlyRent}元</div>
					<label class="form-label col-3">结算周期：</label>
					<div class="form_text col-2">${con.settlementCycleName}</div>
				</div>
				<div class="row cl">
					<label class="form-label col-3">结算时间：</label>
					<div class="formControls col-8">
						<div class="contract_pay_tips">
							<ul id="payTimeUl">
							</ul>
							<p>
								<a href="" class="choose_more link_help">展开 <i
									class="icos icos_more"></i></a>
							</p>
						</div>
					</div>
				</div>
			</div>

			<legend>费用</legend>
			<div class="responsive">
				<div class="row cl">
					<label class="form-label col-3">房主承担的费用：</label>
					<div class="form_text col-6" id="leaseBearDiv">
						<c:forEach items="${leaseBear }" var="code" varStatus="status">
							<c:choose>
								<c:when test="${status.index != 0}">
								、${code.name }
							</c:when>
								<c:otherwise>
								${code.name }
							</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if
							test="${con.ownerBearOther != null && lesseeBear.size() != 12 && con.ownerBearOther != ''}">
							、${con.ownerBearOther }
						</c:if>
						<c:if
							test="${con.ownerBearOther != null && lesseeBear.size() == 12}">
							${con.ownerBearOther }
						</c:if>
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-3">房客承担的费用：</label>
					<div class="form_text col-6" id="lesseeBearDiv">
						<c:forEach items="${lesseeBear }" var="code" varStatus="status">
							<c:choose>
								<c:when test="${status.index != 0}">
								、${code.name }
							</c:when>
								<c:otherwise>
								${code.name }
							</c:otherwise>
							</c:choose>
						</c:forEach>

					</div>
				</div>
			</div>
			<legend>押金</legend>
			<div class="responsive">
				<div class="row cl">
					<label class="form-label col-3">押金：</label>
					<div class="form_text col-9">
						${con.depositAmt}元
						<lable>(乙方需在<fmt:formatDate value='${con.startTime}' pattern='yyyy年MM月dd日' />前按双方的约定交付。)</lable>
						<%-- <c:if test="${con.paymentMethod == 1}">
							<a onclick="depositInfo()" class="btn-link">押金监管协议</a>
						</c:if> --%>
					</div>
					<div class="row cl">
						<div class="form_text col-offset-3 col-9">押金用作乙方履行本合同约定义务的担保，合同期内或合同履行期届满，如乙方违反合同约定，甲方可从押金中抵扣应由乙方承担的租金、违约金、物品损坏赔偿金和相关欠费等，扣款数额由双方协商确认。合同期满，如出现押金不足以抵扣前述款项及费用时，乙方需在10日内补足。</div>
					</div>
				</div>
			</div>

			<legend>甲乙丙三方确认的附加条款</legend>
			<div class="responsive">
				<div class="row cl">
					<label class="form-label col-3">签约当日甲方提供的证件原件：</label>
					<div class="form_text col-3" id="leaseBearDiv">
						<c:forEach items="${LessorCosts}" var="code" varStatus="status">
							<c:choose>
								<c:when test="${status.index != 0}">
								、${code.name }
							</c:when>
								<c:otherwise>
								${code.name }
							</c:otherwise>
							</c:choose>
						</c:forEach>
					</div>
				</div>
				<c:if
					test="${con.lessorIdsOther != null && con.lessorIdsOther != '' }">
					<div class="row cl">
						<label class="form-label col-3">其他证件：</label>
						<div class="form_text col-3">${con.lessorIdsOther}</div>
					</div>
				</c:if>
				<div class="row cl">
					<label class="form-label col-3">签约当日乙方提供的证件原件：</label>
					<div class="form_text col-3" id="lesseeBearDiv">
						<c:forEach items="${LesseeCosts}" var="code" varStatus="status">
							<c:choose>
								<c:when test="${status.index != 0}">
								、${code.name }
							</c:when>
								<c:otherwise>
								${code.name }
							</c:otherwise>
							</c:choose>
						</c:forEach>
					</div>
				</div>
				<c:if
					test="${con.lesseeIdsOther != null && con.lesseeIdsOther != '' }">
					<div class="row cl">
						<label class="form-label col-3">其他证件：</label>
						<div class="form_text col-3">${con.lesseeIdsOther}</div>
					</div>
				</c:if>
				<div class="row cl">
				<div class="formControls col-2"></div>
				<div class="content_box col-7 ">
					<div class="text_con">甲、乙双方同意将上述证件的复印件留存于丙方处。</div>
				</div>
			</div>
			</div>

			<legend>甲乙丙三方其他约定</legend>
			<div class="row cl">
				<div class="formControls col-2"></div>
				<div class="content_box col-7 ">
					<div class="text_con">${con.additionalTerms}</div>
					<a href="" class="text_more">展开 <i class="icos icos_more"></i></a>
				</div>
			</div>
			<legend>甲乙丙三方保证</legend>
			<div class="row cl">
				<div class="formControls col-2"></div>
				<div class="content_box col-7 ">
					<div class="text_con">
						1.甲乙丙三方保证并承诺均为完全民事行为能力人或为依法成立的法人或组织，有权利进行本合同项下的任一事项，对合同约定的权利与义务已充分知晓并认可，并保证本合同所有信息的真实有效性，若因私下签署影子合同造成的一切后果，由各方自行承担。甲乙丙三方违反上述承诺及保证对第三人或本合同其他方造成侵权、伤害或损害，与平台无关，由违反承诺方负责解决，若给平台或本合同其他方造成损害的，违反承诺方应赔偿一切损失。<br />
						2.甲方保证：该房屋权属清楚，甲方有权进行本合同项下任一事项且不侵犯任何第三人的权利。同时甲方须保证在签署本合同之前该房屋不存在出租、转让、查封等影响出租的权利限制和相关债务，如有纠纷，由甲方负责处理。若因甲方违反上述保证对第三人或对乙方造成侵权、伤害或损害，与平台无关，甲方负责解决，若给平台或乙方造成损害的，甲方应赔偿一切损失。<br />
					</div>
					<a href="" class="text_more">展开 <i class="icos icos_more"></i></a>
				</div>
			</div>
			<legend>甲方权利义务</legend>
			<div class="row cl">
				<div class="formControls col-2"></div>
				<div class="content_box col-7 ">
					<div class="text_con">
						1.租赁期内，该房屋自然损坏的维修养护由甲方负责，因人为使用造成的损坏由乙方自行负责维修，双方另有约定的除外。<br />
						2.在租赁期间，甲方如需将该房屋转让，甲方应提前三个月书面通知乙方并保证本合同的继续履行，乙方有该房屋的优先购买权。<br />
						3.甲乙双方确认，该房屋交付乙方前，与该房屋有关的水、电、煤气、采暖等费用由甲方承担并负责结清；该房屋交付乙方后的租赁期内，与该房屋有关的相关费用按本合同的约定执行。<br />
						4.甲方有权对乙方使用房屋的情况进行监督，但不得对乙方正常、合理使用房屋进行干扰或者妨碍。<br />
						5.若房屋租赁期间，房屋装修、设施、家具，电器等发生损坏、遗失，甲方有权向乙方索取赔偿。对于保险公司已经赔付的房屋损失，甲方不得再向乙方追偿。<br />
						6.租赁期满或合同解除后，甲方有权收回房屋，乙方应按照原状返还房屋及其附属物品、设备设施。甲乙双方应对房屋和附属物品、设备设施及水电使用等情况进行验收，结清各自应当承担的费用。<br />
						7.租赁期满，若乙方欲继续承租该房屋，须在租期届满前1个月向甲方提出续租申请，甲乙双方经过协商，在平台共同签署《大连市房屋租赁续租合同》，完成续租流程。<br />
						8.甲方不得将房屋出租用于传销等违法犯罪活动。<br />
					</div>
					<a href="" class="text_more">展开 <i class="icos icos_more"></i></a>
				</div>
			</div>
			<legend>乙方权利义务</legend>
			<div class="row cl">
				<div class="formControls col-2"></div>
				<div class="content_box col-7 ">
					<div class="text_con">
						1.在租赁期间，如出现需由甲方负责维修的情况时，乙方应及时通知甲方，如因乙方延误通知给甲方造成损失的，由乙方承担维修责任，无法维修的由乙方承担赔偿责任。<br />
						2.乙方使用该房屋时，未经甲方书面同意，不得改变该房屋的用途；不得利用该房屋从事任何违法、非法、不道德、不恰当或有伤风化的活动；不得储存任何违禁品，易燃、爆炸等限制类物品（另行约定的除外）；同时必须严格遵守中华人民共和国法律和当地的有关规定。否则视为根本违约，甲方有权解除本合同。<br />
						3.乙方若要装修翻新房屋（不破坏结构）或更换设施，需先征得甲方书面同意，费用自付，未经允许不得擅自改动。<br />
						4.本合同因双方协议解除，或租期届满而终止履行时，乙方应在接到甲方解除合同的通知或租期届满之日迁出该房屋，并将该房屋及甲方所属家电、家具等内部设施及乙方对该房屋所进行的装修（如有）完好地返还给甲方，如造成损坏，由乙方负责修缮或按价赔偿,对于乙方遗留在该房屋内的任何财产、物品、文件、资料、设备等，甲方有权通过公安局或该房屋所在地居委会或小区物业等相关部门，将上述物品妥善放置其他处所并书面通知乙方。<br />
						5.未经甲方书面同意，乙方不得擅自转租该房屋。<br />
					</div>
					<a href="" class="text_more">展开 <i class="icos icos_more"></i></a>
				</div>
			</div>
			<legend>丙方权利义务</legend>
			<div class="row cl">
				<div class="formControls col-2"></div>
				<div class="content_box col-7 ">
					<div class="text_con">
						1.丙方应当认真负责地为甲乙双方订立房屋租赁合同提供机会或媒介服务，如实报告平台有关订立房屋租赁合同的事项，对合同中甲乙双方的信息、签署合同合意的真实合法性、合同订立等事项负责，并协助甲乙完成平台的全部操作流程，配合双方办理物业交验。<br />
						2.丙方有责任督促甲乙双方按照合同约定按时缴费。<br />
						3.丙方作为居间服务提供者，负责根据平台的要求完成合同签署、交接等操作流程。<br />
						4.甲乙双方无论任何原因致使本合同不能正常履行或提前解除（除丙方有过错外），由甲乙双方自行解决, 居间服务费用将不予退还。<br />
					</div>
					<a href="" class="text_more">展开 <i class="icos icos_more"></i></a>
				</div>
			</div>
			<legend>违约责任</legend>
			<div class="row cl">
				<div class="formControls col-2"></div>
				<div class="content_box col-7 ">
					甲方违约责任<br />
					<div class="text_con">
						（1）甲方因故迟延交付房屋，或交付的房屋严重不符合合同约定，影响乙方安全、健康的，按日期扣除日租金；<br />
						（2）若房屋存有隐患至乙方受损，甲方应按实际损失或约定金额向乙方赔偿损失；<br />
						（3）甲方若不承担约定的维修义务，致使乙方无法正常使用房屋的，按日期扣除日租金；<br />
						（4）本该甲方承担费用由乙方支付的，甲方应予以返还；<br />
						（5）如因甲方原因导致本合同解除的，甲方须向乙方支付违约金，违约金金额相当于本合同约定的一个月租金，如该违约金不足以弥补乙方损失的，甲方还须赔偿乙方的损失。<br />



						乙方违约责任<br />

						（1）乙方欠缴第四条第3款中的各项费用之和超过押金金额的，或欠缴各项费用达二个月的，视为乙方违约，甲方有权解除本合同，并按本合同第五条第1
						款处理；<br /> （2）如乙方违反本合同第四条第1款的约定，自逾期之日起每日按应交付租金的1%向甲方支付违约金。<br />
						（3）乙方无故拖延支付下一期租金（甲、乙双方另有约定除外）超过七日或逾期未迁出该房屋时，甲方有权解除合同，并采取相关措施收回该房屋，而无需给予乙方任何赔偿、补偿，同时不予退还，对于乙方遗留在该房屋内的任何财产、物品、文件、资料、设备等，甲方有权通过公安局或该房屋所在地居委会或小区物业等相关部门，将上述物品妥善放置其他处所并书面通知乙方；<br />
						（4）乙方擅自改变房屋用途的，从事违法活动、损害公共利益或者妨碍他人正常工作、生活的，擅自将房屋转租给第三人的，甲方有权单方解约；<br />
						（5）乙方擅自拆改变动或损坏房屋主体结构的，甲方可要求乙方将房屋恢复原状或赔偿相应损失；<br />
						（6）乙方保管不当或不合理使用导致附属物品、设备设施损坏，应按《房屋交割清单》约定赔偿甲方损失。<br />
						（7）如因乙方原因导致本合同解除的，乙方须向甲方支付违约金，违约金数额相当于本合同约定的一个月租金，如违约金不足以弥补甲方损失的，乙方还须赔偿甲方的损失。<br />

						丙方违约责任<br />

						(1)丙方应保证房源的真实性，若提供虚假信息、隐瞒重要事实或有恶意串通行为的，除退还已收取的佣金外，还应赔偿由此给甲方或乙方造成的损失。<br />
						(2)丙方应按平台要求协助甲乙双方合同签署、解除等全部过程的操作流程，若因丙方怠于履行上述义务，甲方有权要求丙方返还服务费，若给甲乙双方造成损失的，应予以赔偿。<br />
					</div>
					<a href="" class="text_more">展开 <i class="icos icos_more"></i></a>

				</div>
			</div>

			<legend>免责</legend>
			<div class="row cl">
				<div class="formControls col-2"></div>
				<div class="content_box col-7 ">
					<div class="text_con">
						1.如果甲乙丙三方未按照合同约定履行，经三方协商一致，可以免除违约责任，对各方信用评价没有任何影响。<br />
						2.因不可抗力而导致本合同无法履行的，本合同自行解除，甲乙双方免责。<br />
						3.若合同未到期，一方提起解除，而另一方提出异议时，则合同解除的流程暂停，待双方协商一致再按协商结果进行下一步操作。<br />
					</div>
					<a href="" class="text_more">展开 <i class="icos icos_more"></i></a>
				</div>
			</div>
			<legend>合同解除</legend>
			<div class="row cl">
				<div class="formControls col-2"></div>
				<div class="content_box col-7 ">
					<div class="text_con">
						1.若合同到期，在平台提醒甲、乙、丙三方后仍无人回应，则合同当天自行解除，同时平台默认合同签署三方全程履约，无异议。<br />
						2.若合同未到期，经甲、乙双方协商一致，合同可以提前解除，向平台提出解除合同申请，或到指定窗口办理合同解除手续。<br />
						3.若合同没有到期，甲乙双方
						中任一方提起解除，而甲乙双方中另一方提出异议时，则合同解除的流程挂起，待双方协商一致再按协商结果进行下一步操作。<br />
						4、若房屋被征收或者拆迁，导致本合同无法继续履行的，本合同自动解除，甲乙双方免责，该房屋被征收或拆迁事宜按国家法律和地方规定执行。<br />
					</div>
					<a href="" class="text_more">展开 <i class="icos icos_more"></i></a>
				</div>
			</div>
			<legend>其他</legend>
			<div class="row cl">
				<div class="formControls col-2"></div>
				<div class="content_box col-7 ">
					<div class="text_con">
						1.合同争议。本合同的订立、生效、解释及争议，均适用中华人民共和国法律。发生争议时由当事人协商解决；协商不成的，可以向有管辖权的人民法院提起诉讼。平台有权将违约方违约情况记入信用体系。
					</div>
					<a href="" class="text_more">展开 <i class="icos icos_more"></i></a>
				</div>
			</div>
			<legend>提醒</legend>
			<div class="row cl">
				<div class="formControls col-2"></div>
				<div class="content_box col-7 ">
					<div class="text_con">
						1.甲乙双方向丙方交付的款项，丙方均出具正式的发票，请向收取人索取。<br />
						2.乙方及实际使用人若非本地户口，应到房屋所在地派出所或通过平台办理居住证。<br />
						3.每次缴租日之前，甲乙双方应对房屋各项使用费用的阶段费用进行核对，以免造成长时间欠缴。<br />
						4.甲乙丙三方在签约时已见过对方本人或有授权书的代理人并认可对方的签约权利。<br />
						5.甲乙双方不要将款项交由丙方员工（无论任何职位）个人处保管，以保障甲乙双方资金安全，若因此产生损失，由甲乙双方自行承担。<br />
						6.甲乙丙三方均明确本合同中所登记的各方电话、邮箱信息、送达地址、备用联系人电话、常住地址等信息均是真实有效的，平台以短信提醒、快递邮寄、邮件送达、电话联系等任一方式发送相关信息即视为已送达。若有变更，三方应提前10日以书面形式通知平台，否则视为无变更。<br />
						7.乙方若因故长时间不能居住该房屋，需提前与甲方联系，留下变更联系方式，并将备用钥匙留给甲方，以备不时之需。<br />
						8.甲乙丙三方须登陆平台网站（http://www.housecenter.cn）仔细阅读《大连市房屋租赁运营管理平台信用体系》，并按照要求维护好自己的信用分数。<br />
						9.本合同附件构成本协议不可分割的组成部分，与本合同正文具有同等法律效力，本合同应包括本合同附件。 附件一《房屋交割清单》
					</div>
					<a href="" class="text_more">展开 <i class="icos icos_more"></i></a>
				</div>
			
			</div>
			<div class="row cl">
				<div class="formControls col-2"></div>
				<div class="col-7" style="margin-left:20px">根据《中华人民共和国合同法》、《中华人民共和国城市房地产管理法》等法律规定，甲乙双方在丙方的居间促成下协商一致，签署本合同。本合同为大连市房屋租赁管理平台（以下简称平台）出具的大连市政府规定的统一合同文本。</div>
			</div>			
			<div class="row cl">
				<div class="formControls col-2"></div>
				<div class="col-7" style="margin-left:20px">甲乙丙三方对上述合同内容已完全知晓认同并自愿履行本合同的各项约定，若出现问题，甲乙丙三方自行承担，甲乙丙三方确认签署</div>
			</div>
			

			<c:if
				test="${con.houItemsList != null && con.houItemsList.size() != 0}">
				<legend>房屋交割清单</legend>
				<div class="row cl">
					<div class="formControls col-2"></div>
					<div class="formControls col-9">
						<div class="cl pd-5 bg-1 bk-gray mt-20">
							房屋附属家具、电器、装修及其他设备设施状况及损赔（不在列表内物件不予追偿）</div>
						<table class="table table-border table-bordered  table-bg ">
							<thead>
								<tr class="text-c">
									<th width="80" colspan="2">名称</th>
									<th width="120">品牌</th>
									<th width="50">数量</th>
									<th width="60">单价</th>
									<th width="80">损赔金额</th>

								</tr>
							</thead>
							<tbody>
								<c:forEach var="houItems" items="${con.houItemsList}"
									varStatus="status">
									<tr class="text-c">
										<td>${status.count}</td>
										<td>${houItems.item}</td>
										<td>${houItems.brand}</td>
										<td>${houItems.num}</td>
										<td>${houItems.price}元</td>
										<td>${houItems.compenAmount}元</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</c:if>
			<c:if
				test="${con.conOtherCosts != null && con.conOtherCosts.size() != 0}">
				<legend>其他相关费用</legend>
				<div class="row cl">
					<div class="formControls col-2"></div>
					<div class="formControls col-9">
						<table class="table table-border table-bordered  table-bg ">
							<thead>
								<tr class="text-c">
									<th width="50">项目</th>
									<th width="50">单位</th>
									<th width="50">单价</th>
									<th width="80">起计时间</th>
									<th width="80">起计底数</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="conOtherCosts" items="${con.conOtherCosts}">
									<tr class="text-c">

										<td>${conOtherCosts.item}</td>
										<td>${conOtherCosts.unit}</td>
										<td>${conOtherCosts.price}</td>
										<td><fmt:formatDate value='${conOtherCosts.stime}'
												pattern='yyyy-MM-dd' /></td>
										<td>${conOtherCosts.scount}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</c:if>
		</form>
	</div>

	<div id="modifyConPanel" style="display: none">
		<form action="" method="post" class="form form-horizontal"
			id="modifyForm">
			<legend>修改合同</legend>
			<div class="responsive">
				<div class="row cl">
					<label class="form-label col-3"><span class="c-red">*</span>租赁期限：</label>
					<div class="formControls col-1">
						<span class="select-box"> <form:select
								path="con.leaseTermYear" id="leaseTermYear"
								onchange="initSelTermMonth(),countEndTime()" class="select">
								<c:forEach var="i" begin="0" end="10">
									<form:option value="${i }" />
								</c:forEach>
							</form:select>
						</span>
					</div>
					<p class="form_text">年</p>
					<div class="formControls col-1">
						<span class="select-box"> <form:select
								path="con.leaseTermMonth" id="leaseTermMonth"
								onchange="countEndTime()" class="select">
								<c:forEach var="i" begin="0" end="12">
									<form:option value="${i }" />
								</c:forEach>
							</form:select>
						</span>
					</div>
					<p class="form_text">个月</p>
				</div>
				<div class="row cl">
					<label class="form-label col-3">合同起止：</label>
					<div class="formControls col-2">
						<strong id="startTimeLabel"><fmt:formatDate
								value='${con.startTime}' pattern='yyyy-MM-dd' /></strong>
					</div>
					<div class="form_text" id="endTimeDiv">
						至<strong id="endTimeLabel"><fmt:formatDate
								value='${con.endTime}' pattern='yyyy-MM-dd' /></strong>
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-4"><span class="c-red">*</span>月租金：</label>
					<div class="formControls col-1">
						<form:input path="con.monthlyRent" placeholder="月租金"
							id="monthlyRent"
							class="input-text validate[required,custom[integer],max[1000000]]"
							data-prompt-target="monthlyRentError"
							data-prompt-position="inline" />
					</div>
					<p class="form_text">元</p>
					<p class="form_text" id="monthlyRentError"></p>
				</div>
				<div class="row cl">
					<label class="form-label col-3">房客承担：</label>
					<div class="formControls col-8">
						<div class="skin-minimal">
							<div class="check-box">
								<form:checkbox path="con.ownerBears" id="lesseeBear1" value="1"
									class="ml15 mr5" />
								<label for="lesseeBear1"> 水费 </label>
							</div>
							<div class="check-box">
								<form:checkbox path="con.ownerBears" id="lesseeBear2" value="2"
									class="ml15 mr5" />
								<label for="lesseeBear2"> 电费 </label>
							</div>
							<div class="check-box">
								<form:checkbox path="con.ownerBears" id="lesseeBear3" value="3"
									class="ml15 mr5" />
								<label for="lesseeBear3"> 电话费</label>
							</div>
							<div class="check-box">
								<form:checkbox path="con.ownerBears" id="lesseeBear4" value="4"
									class="ml15 mr5" />
								<label for="lesseeBear4"> 电视收视费 </label>
							</div>
							<div class="check-box">
								<form:checkbox path="con.ownerBears" id="lesseeBear5" value="5"
									class="ml15 mr5" />
								<label for="lesseeBear5"> 供暖费 </label>
							</div>
							<div class="check-box">
								<form:checkbox path="con.ownerBears" id="lesseeBear6" value="6"
									class="ml15 mr5" />
								<label for="lesseeBear6"> 燃气费 </label>
							</div>
							<div class="check-box">
								<form:checkbox path="con.ownerBears" id="lesseeBear7" value="7"
									class="ml15 mr5" />
								<label for="lesseeBear7"> 物业管理费 </label>
							</div>
							<div class="check-box">
								<form:checkbox path="con.ownerBears" id="lesseeBear8" value="8"
									class="ml15 mr5" />
								<label for="lesseeBear8"> 房屋租赁税费 </label>
							</div>
							<div class="check-box">
								<form:checkbox path="con.ownerBears" id="lesseeBear9" value="9"
									class="ml15 mr5" />
								<label for="lesseeBear9"> 卫生费 </label>
							</div>
							<div class="check-box">
								<form:checkbox path="con.ownerBears" id="lesseeBear10"
									value="10" class="ml15 mr5" />
								<label for="lesseeBear10"> 上网费 </label>
							</div>
							<div class="check-box">
								<form:checkbox path="con.ownerBears" id="lesseeBear11"
									value="11" class="ml15 mr5" />
								<label for="lesseeBear11"> 车位费 </label>
							</div>
							<div class="check-box">
								<form:checkbox path="con.ownerBears" id="lesseeBear12"
									value="12" class="ml15 mr5" />
								<label for="lesseeBear12"> 室内设施维修费 </label>
							</div>
						</div>
					</div>
				</div>
				<div class="row cl">
					<div class="formControls col-3"></div>
					<p class="form_text">其他</p>
					<div class="formControls col-3">
						<form:input path="con.ownerBearOther" id="lesseeBearOther"
							placeholder="其他" class="input-text validate[maxSize[50]]" />
					</div>
					<div class="formControls col-9 col-offset-3">
						<p class="form_text c-999">（除以上约定的费用外，在租住过程中发生费用都由房主承担）</p>
					</div>

				</div>
				<div class="row cl">
					<label class="form-label col-3">履约保证金：</label>
					<div class="formControls col-2">
						<p class="form_text">${con.depositAmt}</p>
						<p class="form_text">元</p>
					</div>
				</div>
				付款说明
			</div>
			<div class="responsive">
				<div class="row cl">
					<div class="col-6 col-offset-4">
						<a class="btn btn-primary radius mr-10" onclick="ok()">提交</a> <a
							class="btn btn-default radius mr-10" onclick="cancel()">取消</a>
					</div>
				</div>
			</div>
		</form>
	</div>
	<div id="uploadDiv" style="display: none">
		<label class="c-red">注：请上传jpg、png、bmp格式的图片；最大4M</label>
		<form action="${ctx}/contp/uploCon" method="post"
			class="form form-horizontal" id="uploadContract">
			<form:hidden path="con.id" />
			<form:hidden path="con.version" />
			<input type="hidden" name="infoAttList[0].filePath"
				id="oneSideFilePath" value="${con.infoAttList[0].filePath}" /> <input
				type="hidden" name="infoAttList[0].fileRealName"
				id="oneSideFileRealName" value="${con.infoAttList[0].fileRealName}" />
			<input type="hidden" name="infoAttList[0].fileName"
				id="oneSideFileName" value="${con.infoAttList[0].fileName}" /> <input
				type="hidden" name="infoAttList[1].filePath" id="otherSideFilePath"
				value="${con.infoAttList[1].filePath}" /> <input type="hidden"
				name="infoAttList[1].fileRealName" id="otherSideFileRealName"
				value="${con.infoAttList[1].fileRealName}" /> <input type="hidden"
				name="infoAttList[1].fileName" id="otherSideFileName"
				value="${con.infoAttList[1].fileName}" />
			<div class="row cl">
				<div class="form_text col-6 text-c">上传合同正面：</div>
				<div class="form_text col-6 text-c">上传合同背面：</div>
			</div>
			<div class="row cl ">
				<div class=" formControls text-c col-6">
					<div id="uploader" class="wu-example">
						<!--用来存放文件信息-->
						<div id="fileList" class="fileList">
							<c:if
								test="${!empty con.infoAttList[0].filePath && !empty con.infoAttList[0].fileRealName && !empty con.infoAttList[0].fileName}">
								<div id="WU_FILE_0" class="file-item thumbnail">
									<a
										href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/img/${con.infoAttList[0].filePath}"
										class="fancybox"><img
										src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/img/${con.infoAttList[0].filePath}?key=${accessKey}" /></a>
									<div class="info" title=" ${con.infoAttList[0].fileRealName} ">${con.infoAttList[0].fileRealName}</div>
								</div>
							</c:if>
						</div>
						<c:if test="${con.status == 13 || con.status == 10}">
							<div class="btns">
								<div id="filePicker" style="float: none;">选择图片</div>
							</div>
						</c:if>
					</div>
				</div>
				<div class=" formControls text-c col-6">
					<div id="uploader2" class="wu-example">
						<!--用来存放文件信息-->
						<div id="fileList2" class="fileList">
							<c:if
								test="${!empty con.infoAttList[1].filePath && !empty con.infoAttList[1].fileRealName && !empty con.infoAttList[1].fileName}">
								<div id="WU_FILE_0" class="file-item thumbnail">
									<a
										href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/img/${con.infoAttList[1].filePath}"
										class="fancybox"><img
										src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/img/${con.infoAttList[1].filePath}?key=${accessKey}" /></a>
									<div class="info" title=" ${con.infoAttList[1].fileRealName} ">${con.infoAttList[1].fileRealName}</div>
								</div>
							</c:if>
						</div>
						<c:if test="${con.status == 13 || con.status == 10}">
							<div class="btns">
								<div id="filePicker2" style="float: none;">选择图片</div>
							</div>
						</c:if>
					</div>
				</div>
			</div>
			<c:if test="${con.status == 13 || con.status == 10}">
				<div>
					<div class="cl row text-c">
						<a class="btn btn-primary mr-10" onclick="submitUploadForm()">确定</a>
						<a class="btn btn-default" onclick="closeUploadDialog()">取消</a>
					</div>
				</div>
			</c:if>
		</form>
	</div>

	<%@ include file="/WEB-INF/views/include/foot.jsp"%>
	<!-- 页面特有js -->
	<script type="text/javascript"
		src="${ctxStatic}${hui}lib/layer/1.9.3/layer.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}${hui}lib/icheck/jquery.icheck.min.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}${hui}js/jquery-migrate-1.1.0.min.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}${hui}lib/webuploader/0.1.5/webuploader.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}${hui}lib/webuploader/0.1.5/webuploaderdlfc.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}${hui}lib/fancybox/jquery.fancybox.js?v=2.1.5"></script>
	<script type="text/javascript">
		var _didx;
		var _didxx;
		var uploader;
		var uploader2;
		var refreshTime = 1;
		var cycleNum = "${con.settlementCycle}";
		$(function() {
			countPayTime(cycleNum);
			$('.skin-minimal input').iCheck({
				checkboxClass: 'icheckbox-blue',
				radioClass: 'iradio-blue',
				increaseArea: '20%'
			});
			$('.fancybox').fancybox();
			
			$("#modifyForm").validationEngine('attach', {
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
				server : '${ctx}/contp/upload?cid=${con.id}&fileType=1',//上传的URL
				accept : {
					title : '请上传图片格式',
					extensions : 'jpg,jpeg,bmp,png'
				},
				auto : true,
				fileNumLimit : 1,
				fileSingleSizeLimit : 4194304,
				compress : false
			});

			uploader.on('uploadSuccess', function(file) {
				//后台验证失败（文件大小 文件类型验证）
				if (arguments[1].success == 0) {
					layer.alert(arguments[1].message, {icon : 5});
				} else if (arguments[1].success == 1) {//上传临时文件夹成功后的操作写在这里 返回filepath和filename
					$("#oneSideFilePath").val(arguments[1].data.filePath);
					$("#oneSideFileName").val(arguments[1].data.fileName);
					$("#oneSideFileRealName").val(arguments[1].data.fileRealName);
				}
			});

			uploader2 = WebUploader.create({
				pick : {
					id : '#filePicker2',
					multiple : false
				},
				swf : '${ctxStatic}${hui}lib/webuploader/0.1.5/Uploader.swf',
				server : '${ctx}/contp/upload?cid=${con.id}&fileType=2',//上传的URL
				accept : {
					title : '请上传图片格式',
					extensions : 'jpg,jpeg,bmp,png'
				},
				auto : true,
				fileNumLimit : 1,
				fileSingleSizeLimit : 4194304,
				compress : false
			});

			uploader2.on('uploadSuccess', function(file) {
				//后台验证失败（文件大小 文件类型验证）
				if (arguments[1].success == 0) {
					layer.alert(arguments[1].message, {icon : 5});
				} else if (arguments[1].success == 1) {//上传临时文件夹成功后的操作写在这里 返回filepath和filename
					$("#otherSideFilePath").val(arguments[1].data.filePath);
					$("#otherSideFileName").val(arguments[1].data.fileName);
					$("#otherSideFileRealName").val(arguments[1].data.fileRealName);
				}
			});

			initUploader(uploader, 150, 150, "#fileList");
			initUploader(uploader2, 150, 150, "#fileList2");
				
			<c:choose>
				<c:when test="${isRenewal}">
					$("#renewalForm").attr("action", "${ctx}/contp/renewal");
				</c:when>
				<c:otherwise>
					$("#renewalForm").attr("action", "${ctx}/contp/step5");
				</c:otherwise>
			</c:choose>
			<c:if test="${downloadFlag}">
				window.location.href = "${ctx}/dl/pdf?id=${con.id}&cType=6";
			</c:if>
		});
			
		function confirmDialog() {
			layer.confirm("点击此按钮，合同内容将永远不能修改，确定要下载合同吗？", function(){
				$("#renewalForm").submit();
			}, function(){
				//closeDialog();
			});
		};

		function closeDialog() {
			layer.close(_didx);
			$("#modifyConPanel").hide();
		};

		function openDialog() {
			_didx = layer.open({
				type: 1, //page层
				area: ['800px', '450px'],
				title: '修改合同',
				shade: 0.6, //遮罩透明度
				moveType: 1, //拖拽风格，0是默认，1是传统拖动
				shift: 0, //0-6的动画形式，-1不开启
				content: $('#modifyConPanel')
			});
			$("#modifyConPanel").show();
		};
		
		function openUploadDialog(width, height) {
			_didxx = layer.open({
				type: 1, //page层
				area: [width, height],
				title: '上传合同',
				shade: 0.6, //遮罩透明度
				moveType: 1, //拖拽风格，0是默认，1是传统拖动
				shift: 0, //0-6的动画形式，-1不开启
				content: $('#uploadDiv')
			});
			$("#uploadDiv").show();
			if (refreshTime == 1) {
				uploader.refresh();
				uploader2.refresh();
			}
		};
		
		function closeUploadDialog() {
			layer.close(_didxx);
			$("#uploadDiv").hide();
		}
		
		function ok() {
			if ($("#modifyForm").validationEngine('validate')) {
				resetView();
				closeDialog();
			}
		}
		
		function cancel() {
			closeDialog();
		}
		
		function initSelTermMonth() {
			var year = $("#leaseTermYear").val();
			if (year == "0") {
				$('#leaseTermMonth option[value=0]').remove();
			} else {
				var length = $('#leaseTermMonth').find("option[value=0]").length;
				if (length == 0) {
					$('#leaseTermMonth').prepend('<option value="0">0</option>');
				}
			}
		}
		function submitForm() {
			$("#renewalForm").submit();
		}
		
		function countEndTime() {
			var year = parseInt($("#leaseTermYear").val()) * 12;
			var month = parseInt($("#leaseTermMonth").val());
			var totalMonth = year + month;
			var startTime = $("#startTimeLabel").text();
			if (startTime == "" || totalMonth == 0) {
				$("#endTimeDiv").css("display", "none");
				$("#endTimeLabel").text("");
				return;
			}
			var startDate = newDate(startTime);
			var endDate = newDate(startTime);
			endDate.setMonth(startDate.getMonth() + totalMonth);
			if (startDate.getDate() != endDate.getDate()) {
				endDate.setDate(0);
			}
			endDate.setDate(endDate.getDate()-1);
			var endTime = formatDate(endDate);
			$("#endTimeDiv").css("display", "inline");
				$("#endTimeLabel").text(endTime);
		}
		
		function resetView() {
			var startTime = $("#startTimeLabel").text();
			var endTime = $("#endTimeLabel").text();
			var year = parseInt($("#leaseTermYear").val()) * 12;
			var month = parseInt($("#leaseTermMonth").val());
			var totalMonth = year + month;
			var html = startTime + "至" + endTime + "（共 " + totalMonth + "个月）";
			$("#timeLabel1").html(html);
			$("#timeLabel2").html(html);
			$("#monthlyRentDiv").html($("#monthlyRent").val());
			var leaseBearDivHtml = "";
			var lesseeBearDivHtml = "";
			var lesseeBearVal = "";
			$("input[name='ownerBears']").each(function() {
				var checked = $(this).attr("checked");
				var text = $(this).parent("div").next().next("label").text();
				if (text != "") {
					if (checked == "checked") { 
					//房客承担
					lesseeBearDivHtml = lesseeBearDivHtml + "、" + text;
					lesseeBearVal = lesseeBearVal + "," + $(this).val();
					} else {
						//房主承担
						leaseBearDivHtml = leaseBearDivHtml + "、" + text;
					}
				}
			 });
			var lesseeBearOtherVal = $("#lesseeBearOther").val();
			if (lesseeBearOtherVal != "") {
				lesseeBearDivHtml = lesseeBearDivHtml + "、" + lesseeBearOtherVal;
			}
			if (leaseBearDivHtml.length > 0) {
				leaseBearDivHtml = leaseBearDivHtml.substring(1);
			}
			if (lesseeBearDivHtml.length > 0) {
				lesseeBearDivHtml = lesseeBearDivHtml.substring(1);
			}
			if (lesseeBearVal.length > 0) {
				lesseeBearVal =  lesseeBearVal.substring(1);
			}
			$("#leaseBearDiv").html(leaseBearDivHtml);
			$("#lesseeBearDiv").html(lesseeBearDivHtml);
			
			$("#leaseTermYearHid").val($("#leaseTermYear").val());
			$("#leaseTermMonthHid").val($("#leaseTermMonth").val());
			$("#monthlyRentHid").val($("#monthlyRent").val());
			$("#lesseeBearsHid").val(lesseeBearVal);
			$("#lesseeBearOtherHid").val(lesseeBearOtherVal);
		}
		
		function submitUploadForm() {
			if ($("#oneSideFilePath").val() == '' || $("#oneSideFileName").val() == '' || $("#oneSideFileRealName").val() == '') {
				layer.alert("请上传合同正面", {icon : 5});
			} else if ($("#otherSideFilePath").val() == '' || $("#otherSideFileName").val() == '' || $("#otherSideFileRealName").val() == '') {
				layer.alert("请上传合同背面", {icon : 5});
			} else {
				$("#uploadContract").submit();
			}
			
		}
		
		function countPayTime(num) {
			var startTime = '${con.dateStartTime}';
			var endTime = '${con.dateEndTime}';
			var year = parseInt('${con.leaseTermYear}') * 12;
			var month = parseInt('${con.leaseTermMonth}');
			
			var checkedVal = num;
			var monthlyRent = "${con.monthlyRent}";
			var startDate = newDate(startTime);
			var startMonth = startDate.getMonth() + 1;
			var endDate = newDate(endTime);
			var fromDate = newDate(startTime);
			var totalMonth = year + month;
			var html = "";
			var cycle = 1;
			var payTimeMonth = fromDate.getMonth() -1;
			if (checkedVal == "2") {
				cycle = 3;
				 payTimeMonth = fromDate.getMonth() ;
			} else if (checkedVal == "3") {
				cycle = 6;
				payTimeMonth = fromDate.getMonth() +3;
			} else if (checkedVal == "4") {
				cycle = 12;
			}
			var payTimeDay = fromDate.getDate();
			var fromAfterPayDate = newDate(startTime);
			//本套算法唯一注意的就是没换到一个将要去的月份都要判断当前月份的天数会不会比起始当天日的天数小，解决2月30日这类问题
			for (var i = 0; i < totalMonth; i = i + cycle) {
				var surplus = totalMonth - i;
				var toDateStr = "";
				var rent = 0;
				var afterPayDateStr = "";
				var fromDateStr = "";
				//当第一个循环单独拿出来考虑
				if(i == 0){
					//支付日期就是起始日期
					afterPayDateStr = formatDate(fromAfterPayDate);
					//起始日期就是当天的起始日期
					fromDateStr = formatDate(fromDate);
					//结束日期就需要判断结束的那个月的总天数与起始日期的天数谁大谁小
					var days = new Date(fromDate.getFullYear(), (fromDate.getMonth()+cycle+1), 0).getDate();//获得结束月的天数
					//谁的天数小就取谁的天数-1 这样保证了不真实日期的出现
					if(fromDate.getDate() <= days){
						toDateStr = formatDate(new Date(fromDate.getFullYear(), (fromDate.getMonth()+cycle), fromDate.getDate()-1));
					}else{
						toDateStr = formatDate(new Date(fromDate.getFullYear(), (fromDate.getMonth()+cycle), days-1));
					}
					//金额就是单价*周期
					rent = monthlyRent * cycle; 
				}else{//从第二个循环开始就有了变化，如果是月付还是和以前一样，如果是其他支付情况，需要提前一个月支付
					//如果是月付，其他的判断和之前类似，只需要提前判断一下每一个结束周期月的天数即可
					if(cycle == 1){
						var firstDays = new Date(fromDate.getFullYear(), (fromDate.getMonth()+i+1), 0).getDate();
						if(payTimeDay <= firstDays){
							afterPayDateStr = formatDate(new Date(fromDate.getFullYear(), (fromDate.getMonth()+i), fromDate.getDate()));
						}else{
							afterPayDateStr = formatDate(new Date(fromDate.getFullYear(), (fromDate.getMonth()+i), firstDays));
						}
						var afterPayDate = new Date(afterPayDateStr);
						fromDateStr = formatDate(new Date(afterPayDate.getFullYear(), (afterPayDate.getMonth()), afterPayDate.getDate()));
						var firstOnedays = new Date(fromDate.getFullYear(), (fromDate.getMonth()+cycle+i+1), 0).getDate();
						if(fromDate.getDate() <= firstOnedays){
							toDateStr = formatDate(new Date(fromDate.getFullYear(), (fromDate.getMonth()+cycle+i), fromDate.getDate()-1));
						}else{
							toDateStr = formatDate(new Date(fromDate.getFullYear(), (fromDate.getMonth()+cycle+i), firstOnedays-1));
						}
						rent = monthlyRent * cycle;
					}else{//如果是非月付，提前一个月支付其他判断情况雷同
						var elseDays = new Date(fromDate.getFullYear(), (fromDate.getMonth()+i), 0).getDate();
						if(payTimeDay <= elseDays){
							afterPayDateStr = formatDate(new Date(fromDate.getFullYear(), (fromDate.getMonth()+i-1), fromDate.getDate()));
						}else{
							afterPayDateStr = formatDate(new Date(fromDate.getFullYear(), (fromDate.getMonth()+i-1), elseDays));
						}
						var secondDays = new Date(fromDate.getFullYear(), (fromDate.getMonth()+i+1), 0).getDate();
						if(payTimeDay <= secondDays){
							fromDateStr = formatDate(new Date(fromDate.getFullYear(), (fromDate.getMonth()+i), fromDate.getDate()));
						}else{
							fromDateStr = formatDate(new Date(fromDate.getFullYear(), (fromDate.getMonth()+i), secondDays));
						}
						if (surplus >= cycle) {//这一部分是处理小尾巴的，如果填入2年零5个月，判断零头是否满足一个周期，如果满足就正常按周期取结束时间
							var thirdDays = new Date(fromDate.getFullYear(), (fromDate.getMonth()+i+cycle+1), 0).getDate();
							if(fromDate.getDate() <= thirdDays){
								toDateStr = formatDate(new Date(fromDate.getFullYear(), (fromDate.getMonth()+cycle+i), fromDate.getDate()-1));
							}else{
								toDateStr = formatDate(new Date(fromDate.getFullYear(), (fromDate.getMonth()+cycle+i), thirdDays-1));
							}
							rent = monthlyRent * cycle; 
						} else {//如果不满足一个周期就把剩下的这些小尾巴放一起算出时间和金钱
							toDateStr = endTime;
							rent = monthlyRent * surplus;
						} 
					}
				}
				html = html + '<li><span>' + afterPayDateStr
						+ '前</span> 支付 <span>' + fromDateStr
						+ '</span> 至 <span>' + toDateStr
						+ '</span> 的租金 ，共计 <em>' + rent + '</em> 元</li>';
			}
			$("#payTimeUl").html(html);
			$("#payTimeUl").parent().parent().css("display", "block");
		}
		function newDate(str) {
			str = str.replace(/-/g,"/");
			var date = new Date(str); 
			return date; 
		}
		function formatDate(date) {
			return (date.getFullYear()) + '-'
					+ formatNum(date.getMonth() + 1) + '-'
					+ formatNum(date.getDate());
		}
		function formatNum(num) {
			var numStr = num + "";
			if (numStr.length == 2) {
				return numStr;
			} else {
				return +"0" + numStr;
			}
		}
		
		var _layer;
		var depurl = '${ctx}/contp/dinfo';
		function depositInfo(){
		_layer =layer.open({
				type: 2, //page层
				area: ['700px', '800px'],
				title: "押金监管协议",
				shade: 0.6, //遮罩透明度
				moveType: 1, //拖拽风格，0是默认，1是传统拖动
				shift: 0, //0-6的动画形式，-1不开启
				closeBtn: 1,
				content: depurl
			});
		}
		
		function close(){
			layer.close(_layer);
		}
		
	</script>

</body>
</html>