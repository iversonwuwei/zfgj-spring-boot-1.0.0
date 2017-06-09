<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head2.jsp"%>
<!-- 页面特有css -->
<link href="${ctxStatic}${hui}lib/icheck/icheck.css" rel="stylesheet"
	type="text/css" />
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		租房合同 <span class="c-gray en">&gt;</span> 新建合同<a
			class="btn btn-success radius r mr-20"
			style="line-height: 1.6em; margin-top: 3px"
			onclick="javascript:$('#getForm').submit();"
			href="#" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<form action="${ctx}/contp/step2" method="get" id="getForm">
		<input type="hidden" id="cid" name="cid" value="${con.id}">
	</form>
	<div class="pd-20">
		<form action="${ctx}/contp/step2" method="post"
			class="form form-horizontal" id="step2Form">
			<form:hidden path="con.id" />
			<form:hidden path="con.version" />
			<form:hidden path="con.lessorName" />
			<form:hidden path="con.lessorIdNo" />
			<legend>租赁期限</legend>
			<div class="responsive">
				<div class="row cl">
					<label class="form-label col-3"><span class="c-red">*</span>租赁期限：</label>
					<div class="formControls col-1">
						<span class="select-box"> <form:select
								path="con.leaseTermYear" id="leaseTermYear"
								onchange="initSelTermMonth(),getCanRentArea()" class="select">
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
								onchange="getCanRentArea()"
								class="select">
								<c:forEach var="i" begin="0" end="11">
									<form:option value="${i }" />
								</c:forEach>
							</form:select>
						</span>
					</div>
					<p class="form_text">个月</p>
				</div>
			</div>
			<div class="responsive">
				<div class="row cl">
					<label class="form-label col-3"><span class="c-red">*</span>合同起止：</label>
					<div class="formControls">
						<p class="form_text">
							<form:input path="con.dateStartTime"
								class="validate[required] input-text Wdate"
								data-prompt-target="startTimeError"
								data-prompt-position="inline" id="startTime" readOnly="readOnly"
								onchange="checkStartTime()"
								onclick="WdatePicker({minDate:'${beginCal}',onpicked:checkStartTime,oncleared:checkStartTime})" />
							<form:hidden path="con.dateEndTime" id="endTime" />
					</div>
					<div class="form_text" id="endTimeDiv">
						至<strong id="endTimeLabel">${con.dateEndTime}</strong>
					</div>
					<div id="startTimeError"></div>
				</div>
			</div>
			<legend>租赁标的</legend>
			<div class="responsive">
				<div class="row cl">
					<label class="form-label col-2"><span class="c-red">*</span>出租方式：</label>
							<div class="formControls col-3">
								<div class="radio-box skin-minimal">
									<form:radiobutton path="con.rentalMode" id="wholeRent"
										onclick="hideDmAndAreaDiv()" value="1" />
									<label for="wholeRent" class="form-label">整租</label>
								</div>
								<div class="radio-box skin-minimal">
									<form:radiobutton path="con.rentalMode" id="partRent"
										onclick="showDmAndAreaDiv()" value="2" />
									<label for="partRent" class="form-label">部分</label>
								</div>
							</div>
					<label class="form-label col-2"><span class="c-red">*</span>租赁用途：</label>
					<div class="formControls col-3">
						<div class="radio-box skin-minimal">
							<form:radiobutton path="con.leasePurpose" id="civil" value="1" />
							<label for="civil" class="form-label">住宅</label>
						</div>
					</div>
				</div>
				<div class="row cl">
		        <label class="form-label col-2"><span class="c-red">*</span>房屋地址：</label>
		        <div class="formControls col-6">
		          <form:input path="con.houseAddr" id="houseAddr"
							class="validate[required,minSize[1],maxSize[50]] input-text" />
		        </div>
		      </div>
		      <div class="row cl">
		        <label class="form-label col-2"><span class="c-red">*</span>结构：</label>
		        <div class="formControls col-2">
		          <span class="select-box">
			        <form:select path="con.structure" id="structure" class="select" size="1">
						<form:options items="${structure_list}" itemValue="code" itemLabel="name" />
					</form:select>
				</span>
		        </div>
		      </div>
		      <div class="row cl">
		        <label class="form-label col-2"><span class="c-red">*</span>权属：</label>
		        <div class="formControls col-2">
		        <span class="select-box">
			        <form:select path="con.propertyType" id="propertyType" class="select" size="1">
						<form:options items="${property_list}" itemValue="code" itemLabel="name" />
					</form:select>
				</span>
		        </div>
		      </div>
		      <div class="row cl">
		        <label class="form-label col-2"><span class="c-red">*</span>证号：</label>
		        <div class="formControls col-3">
		          <form:input path="con.houseIdNo" id="houseIdNo"
							class="validate[required,minSize[1],maxSize[50]] input-text" />
		        </div>
		      </div>
		      <div class="row cl">
					<label class="form-label col-2"><span class="c-red">*</span>建筑面积：</label>
					<div class="formControls col-1">
						<form:input path="con.buildingArea" id="buildingArea"
							class="validate[required,custom[number],max[9999],funcCall[checkArea]] input-text"
							data-prompt-target="areasError" data-prompt-position="inline" />
					</div>
					<div class="col-3">
						<p class="form_text">平方米</p>
						<p class="form_text" id="areasError"></p>
					</div>
				</div>
			</div>
			<div class="responsive" id="doMainAndAreaDiv">
				<div class="row cl">
					<label class="form-label col-2"><span class="c-red">*</span>出租区域：</label>
					<div class="formControls col-3">
						<form:input path="con.leaseDomain" id="leaseDomain"
							class="validate[required,minSize[1],maxSize[50]] input-text" />
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-2"><span class="c-red">*</span>出租面积：</label>
					<div class="formControls col-1">
						<form:input path="con.leaseArea" id="leaseArea"
							class="validate[required,custom[number],max[9999],funcCall[checkLeaseArea]] input-text"
							data-prompt-target="areaError" data-prompt-position="inline" />
					</div>
					<div class="col-3">
						<p class="form_text">平方米</p>
						<p class="form_text" id="areaError"></p>
					</div>
				</div>
			</div>
			<legend>房屋所有权人</legend>
			<div class="responsive">
				<div class="row cl">
					<label class="form-label col-3"><span class="c-red">*</span>所有权人数：</label>
					<div class="formControls col-2">
						<span class="select-box"> <select onchange="changeOwnerPeople()"
							class="select" name="ownerPersonNum" id="ownerPersonNum">
								<option value="1">1</option>
						</select>
						</span>
					</div>
					<div class="form_text c-999">（最多可添加4人）</div>
				</div>
			</div>
			<div class="responsive">
				<div class="row cl">
					<div class="formControls col-1-1" id="ownerPeopleDiv">
						<c:choose>
							<c:when
								test="${con.houCoOwnerList != null && con.houCoOwnerList.size() != 0}">
								<c:forEach var="ownerUser" items="${con.houCoOwnerList}"
									varStatus="status">
									<div class="panel panel-primary mb-30 mt-20 ownerPersonDiv">
										<div class="panel-header">所有权人${status.index+1}</div>
										<div class="panel-body">
											<div class="responsive">
												<div class="row cl">
													<label class="form-label col-3"><span class="c-red">*</span>姓名
														：</label>
													<div class="formControls col-2">
														<input type="text"
															name="houCoOwnerList[${status.index}].sysPerson.name"
															id="ownerName${status.index}" value="${ownerUser.sysPerson.name}"
															placeholder="姓名"
															onchange="checkIdNo('#ownerName${status.index}', '#ownerNo${status.index}"
															class="validate[required,minSize[1],maxSize[100]] input-text">
													</div>
												</div>
											</div>
											<div class="responsive">
												<div class="row cl">
													<label class="form-label col-3"><span class="c-red">*</span>身份证件：</label>
													<div class="formControls col-2">
														<span class="select-box"> <select class="select"
															name="houCoOwnerList[${status.index}].sysPerson.idType"
															id="idType${status.index}"
															onchange="checkIdNo('', '#idNo${status.index}')">
																<c:forEach var="perIdType" items="${perIdTypes}">
																	<c:choose>
																		<c:when test="${ownerUser.sysPerson.idType != perIdType.code}">
																			<option value="${perIdType.code}">${perIdType.name}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${perIdType.code}" selected>${perIdType.name}</option>
																		</c:otherwise>
																	</c:choose>
																</c:forEach>
														</select>
														</span>
													</div>
													<div class="formControls col-3">
														<input type="text"
															name="houCoOwnerList[${status.index}].sysPerson.idNo"
															id="ownerNo${status.index}" value="${ownerUser.sysPerson.idNo}"
															placeholder="证件编号"
															class="validate[required,funcCall[checkIdCard]] input-text">
													</div>
												</div>
											</div>
											<c:if test="${status.index == 0}">
											<div class="responsive">
												<div class="row cl">
													<label class="form-label col-3"><span class="c-red">*</span>手机：</label>
													<div class="formControls col-2">
														<input type="text"
															name=""
															id="idNo${status.index}" value="${con.lessorMobile}"
															placeholder="手机"
															class="validate[required,custom[mobile]] input-text">
													</div>
												</div>
											</div>
											</c:if>
										</div>
									</div>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<div class="panel panel-primary mb-30 mt-20 ownerPersonDiv">
									<div class="panel-header">所有权人1</div>
									<div class="panel-body">
										<div class="responsive">
											<div class="row cl">
												<label class="form-label col-3"><span class="c-red">*</span>姓名
													：</label>
												<div class="formControls col-2">
													<input type="text" name="houCoOwnerList[0].sysPerson.name"
														value="${con.lessorName}" id="ownerName0" placeholder="姓名"
														onchange="checkIdNo('#ownerName0', '#ownerNo0')"
														class="validate[required,minSize[1],maxSize[100]] input-text">
												</div>
											</div>
										</div>
										<div class="responsive">
											<div class="row cl">
												<label class="form-label col-3"><span class="c-red">*</span>身份证件：</label>
												<div class="formControls col-2">
													<span class="select-box"> <select class="select"
														name="houCoOwnerList[0].sysPerson.idType" id="idType0"
														onchange="checkIdNo('', '#ownerNo0')">
															<c:forEach var="perIdType" items="${perIdTypes}">
																<c:choose>
																	<c:when test="${con.lessorIdType == perIdType.code}">
																		<option value="${perIdType.code}" selected="selected">${perIdType.name}</option>
																	</c:when>
																	<c:otherwise>
																		<option value="${perIdType.code}">${perIdType.name}</option>
																	</c:otherwise>
																</c:choose>
															</c:forEach>
													</select>
													</span>
												</div>
												<div class="formControls col-3">
													<input type="text" name="houCoOwnerList[0].sysPerson.idNo" id="ownerNo0"
														value="${con.lessorIdNo}" placeholder="证件编号"
														class="validate[required,funcCall[checkIdCard]] input-text">
												</div>
											</div>
										</div>
										<div class="responsive">
											<div class="row cl">
												<label class="form-label col-3"><span class="c-red">*</span>手机：</label>
												<div class="formControls col-2">
													<input type="text"
														value="${con.lessorMobile}" id="mobile0" placeholder="手机"
														class="validate[required,custom[mobile]] input-text">
												</div>
											</div>
										</div>
									</div>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
			<legend>实际使用者</legend>
			<div class="responsive">
				<div class="row cl">
					<label class="form-label col-3"><span class="c-red">*</span>使用人数：</label>
					<div class="formControls col-2">
						<span class="select-box"> <select onchange="changePeople()"
							class="select" name="actualPersonNum" id="actualPersonNum">
								<option value="1">1</option>
						</select>
						</span>
					</div>
				</div>
			</div>
			<div class="responsive">
				<div class="row cl">
					<div class="formControls col-1-1" id="actualPeopleDiv">
						<c:choose>
							<c:when
								test="${con.houUserList != null && con.houUserList.size() != 0}">
								<c:forEach var="houUser" items="${con.houUserList}"
									varStatus="status">
									<div class="panel panel-primary mb-30 mt-20 actualPersonDiv">
										<div class="panel-header">使用者${status.index+1}</div>
										<div class="panel-body">
											<div class="responsive">
												<div class="row cl">
													<label class="form-label col-3"><span class="c-red">*</span>姓名
														：</label>
													<div class="formControls col-2">
														<input type="text"
															name="houUserList[${status.index}].name"
															id="name${status.index}" value="${houUser.name}"
															placeholder="姓名"
															onchange="checkIdNo('#name${status.index}', '#idNo${status.index}"
															class="validate[required,minSize[1],maxSize[100]] input-text">
													</div>
												</div>
											</div>
											<div class="responsive">
												<div class="row cl">
													<label class="form-label col-3"><span class="c-red">*</span>身份证件：</label>
													<div class="formControls col-2">
														<span class="select-box"> <select class="select"
															name="houUserList[${status.index}].idType"
															id="idType${status.index}"
															onchange="checkIdNo('', '#idNo${status.index}')">
																<c:forEach var="perIdType" items="${perIdTypes}">
																	<c:choose>
																		<c:when test="${houUser.idType != perIdType.code}">
																			<option value="${perIdType.code}">${perIdType.name}</option>
																		</c:when>
																		<c:otherwise>
																			<option value="${perIdType.code}" selected>${perIdType.name}</option>
																		</c:otherwise>
																	</c:choose>
																</c:forEach>
														</select>
														</span>
													</div>
													<div class="formControls col-3">
														<input type="text"
															name="houUserList[${status.index}].idNo"
															id="idNo${status.index}" value="${houUser.idNo}"
															placeholder="证件编号"
															class="validate[required,funcCall[checkIdCard]] input-text">
													</div>
												</div>
											</div>
											<div class="responsive">
												<div class="row cl">
													<label class="form-label col-3"><span class="c-red">*</span>手机：</label>
													<div class="formControls col-2">
														<input type="text"
															name="houUserList[${status.index}].mobile"
															id="idNo${status.index}" value="${houUser.mobile}"
															placeholder="手机"
															class="validate[required,custom[mobile]] input-text">
													</div>
												</div>
											</div>
										</div>
									</div>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<div class="panel panel-primary mb-30 mt-20 actualPersonDiv">
									<div class="panel-header">使用者1</div>
									<div class="panel-body">
										<div class="responsive">
											<div class="row cl">
												<label class="form-label col-3"><span class="c-red">*</span>姓名
													：</label>
												<div class="formControls col-2">
													<input type="text" name="houUserList[0].name"
														value="${con.lesseeName}" id="name0" placeholder="姓名"
														onchange="checkIdNo('#name0', '#idNo0')"
														class="validate[required,minSize[1],maxSize[100]] input-text">
												</div>
											</div>
										</div>
										<div class="responsive">
											<div class="row cl">
												<label class="form-label col-3"><span class="c-red">*</span>身份证件：</label>
												<div class="formControls col-2">
													<span class="select-box"> <select class="select"
														name="houUserList[0].idType" id="idType0"
														onchange="checkIdNo('', '#idNo0')">
															<c:forEach var="perIdType" items="${perIdTypes}">
																<c:choose>
																	<c:when test="${con.lesseeIdType == perIdType.code}">
																		<option value="${perIdType.code}" selected="selected">${perIdType.name}</option>
																	</c:when>
																	<c:otherwise>
																		<option value="${perIdType.code}">${perIdType.name}</option>
																	</c:otherwise>
																</c:choose>
															</c:forEach>
													</select>
													</span>
												</div>
												<div class="formControls col-3">
													<input type="text" name="houUserList[0].idNo" id="idNo0"
														value="${con.lesseeIdNo}" placeholder="证件编号"
														class="validate[required,funcCall[checkIdCard]] input-text">
												</div>
											</div>
										</div>
										<div class="responsive">
											<div class="row cl">
												<label class="form-label col-3"><span class="c-red">*</span>手机：</label>
												<div class="formControls col-2">
													<input type="text" name="houUserList[0].mobile"
														value="${con.lesseeMobile}" id="mobile0" placeholder="手机"
														class="validate[required,custom[mobile]] input-text">
												</div>
											</div>
										</div>
									</div>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
			<div class="row cl btn_group">
				<div class="col-9 col-offset-3">
					<a class="btn btn-default radius"
						href="${ctx}/contp/step1?cid=${con.id}">上一步</a> <a
						class="btn btn-primary radius" onclick="submitForm()">下一步</a>
				</div>
			</div>
		</form>
	</div>
	<%@ include file="/WEB-INF/views/include/foot.jsp"%>
	<!-- 页面特有js -->
	<script type="text/javascript"
		src="${ctxStatic}${hui}lib/layer/1.9.3/layer.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}${hui}lib/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}${hui}lib/icheck/jquery.icheck.min.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}${hui}js/jquery-migrate-1.1.0.min.js"></script>
	<script type="text/javascript">
			var Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 ];// 加权因子;
			var ValideCode = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ];// 身份证验证位值，10代表X;
			$(function() {
				initSelActualPerson();
				initSelTermMonth();
				initDmAndAreaDiv();
				initRadioButton();
				initValidate();
			});
			
			function checkLeaseArea(obj) {
				var leaseAreaVal = 0;
				var objVal = obj.val();
				var fLeaseAreaVal = parseFloat(objVal);
				if (fLeaseAreaVal < 4) {
					return "*出租面积不能小于4平方米";
				}
				
				var buildingArea = $("#buildingArea").val();
				if(buildingArea != ''){
					var fBuildingArea = parseFloat(buildingArea);
					if(fBuildingArea < fLeaseAreaVal){
						return "*出租面积不能大于建筑面积*0.7";
					}
				}
				changeSel(parseInt(fLeaseAreaVal));
			}
			
			function checkArea(obj) {
				var objVal = obj.val();
				var fAreaVal = parseFloat(objVal)*0.7;
				changeSel(parseInt(fAreaVal));
			}
			
			function checkIdCard(obj) {
				var success = false;
				var idType = obj.parent("div").prev().find("select").eq(0).val();
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
					var partten = /^[0-9a-zA-Z]{1,50}$/;
					if (!partten.test(obj.val())) {
						return "*至少1个数字或字母,最多50个数字或字母";
					} else {
						success = true;
					}
				}
				if (success) {
					var nameObj = obj.attr("id");
					var name;
					if(nameObj.indexOf('idNo') == -1){
						nameObj = nameObj.replace('ownerNo', '');
						name = $("#ownerName" + nameObj).val();
					}else{
						nameObj = nameObj.replace('idNo', '');
						name = $("#name" + nameObj).val();
					}
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
							success = true;
						}
					}
				});
				if (success) {
					return true;
				} else {
					return "*实名认证失败";
				}
			}
			
			function submitForm() {
				$("#step2Form").submit();
			}
			
			function hideDmAndAreaDiv() {
				$("#doMainAndAreaDiv").css("display", "none");
				changeSelPeople($("#buildingArea").val());
			}

			function showDmAndAreaDiv() {
				$("#doMainAndAreaDiv").css("display", "block");
				changeSelPeople($("#leaseArea").val());
			}
			
			function changeSelPeople(val) {
				if (val != "") {
					changeSel(val);
				} else {
					changeSel(4);
				}
			}

			function initDmAndAreaDiv() {
				var checkedVal = $("input[name='rentalMode']:checked").val();
				if (1 == checkedVal) {
					$("#doMainAndAreaDiv").css("display", "none");
				}
			}
			
			//通过面积计算使用人数
			function changeSel(val) {
				if(val < 4){
					return;
				}
				var personNum = parseInt(val / 4);
				if (personNum > 10) {
					personNum = 10;
				}
				var actualPersonNum = $("#actualPersonNum").val();
				var html = "";
				for (var i = 1; i <= personNum; i++) {
					html = html + '<option value="' + i + '">' + i + '</option>';
					if(i == 10){
						break;
					}
				}
				$("#actualPersonNum").html(html);
				if (personNum < actualPersonNum) {
					$("#actualPersonNum").val(personNum);
				} else {
					$("#actualPersonNum").val(actualPersonNum);
				}
				$("#actualPersonNum").change();
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
			
			function initSelActualPerson() {
				var leaseArea = '${con.leaseArea}';
				if (leaseArea != "") {
					var num = parseInt(leaseArea) / 4;
					var html = "";
					for (var i = 1; i <= num; i++) {
						html = html + '<option value="' + i + '">' + i
								+ '</option>';
						if(i == 10){
							break;
						}
					}
					$("#actualPersonNum").html(html);
					$("#actualPersonNum").val('${actualPersonNum}');
				}
				var htmls = "";
				for (var i = 1; i <= 4; i++) {
					htmls = htmls + '<option value="' + i + '">' + i
							+ '</option>';
				}
				$("#ownerPersonNum").html(htmls);
				$("#ownerPersonNum").val('${ownerPersonNum}');
			}
			function checkStartTime() {
				if($("#startTime").validationEngine('validate')) {
					getCanRentArea();
				}
			}
			 function getCanRentArea() {
				var year = parseInt($("#leaseTermYear").val()) * 12;
				var month = parseInt($("#leaseTermMonth").val());
				var totalMonth = year + month;
				var startTime = $("#startTime").val();
				if (startTime == "" || totalMonth == 0) {
					$("#endTimeDiv").css("display", "none");
					$("#endTimeLabel").text("");
					$("#endTime").val("");
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
				$("#endTime").val(endTime);
			} 
			
			function changePeople() {
				var optionHtml = '';
				<c:forEach items="${perIdTypes}" var="perIdType">
					optionHtml = optionHtml + '<option value="${perIdType.code}">${perIdType.name}</option>';
				</c:forEach>
				var totalNum = $("#actualPersonNum").val();
				var hasNum = $("#actualPeopleDiv").find(".actualPersonDiv").length;
				var overplusNum = totalNum - hasNum;
				if (overplusNum > 0) {
					var html = '';
					for (var i = 0; i < overplusNum; i++) {
						var number = i + hasNum;
						html = html
								+ '<div class="panel panel-primary mb-30 mt-20 actualPersonDiv">'
								+ '<div class="panel-header">使用者'
								+ (number + 1)
								+ '</div>'
								+ '<div class="panel-body">'
								+ '<div class="responsive">'
								+ '<div class="row cl">'
								+ '<label class="form-label col-3"><span class="c-red">*</span>姓名'
								+ '：</label>'
								+ '<div class="formControls col-2">'
								+ '<input type="text" name="houUserList['
								+ number
								+ '].name" id="name'+number+'" placeholder="姓名" onchange="checkIdNo(\'#name'+number+'\',\'#idNo'
								+number+'\')" class="validate[required,minSize[1],maxSize[100]] input-text">'
								+ '</div>'
								+ '</div>'
								+ '</div>'
								+ '<div class="responsive">'
								+ '<div class="row cl">'
								+ '<label class="form-label col-3"><span class="c-red">*</span>身份证件：</label>'
								+ '<div class="formControls col-2">'
								+ '<span class="select-box"> <select onchange="checkIdNo(\'\',\'#idNo'
								+number+'\')" class="select" name="houUserList['
								+ number
								+ '].idType" id="idType'+number+'">'
								+ optionHtml
								+ '</select>'
								+ '</span>'
								+ '</div>'
								+ '<div class="formControls col-3">'
								+ '<input type="text" name="houUserList['
								+ number
								+ '].idNo" id="idNo'+number+'" placeholder="证件编号" class="validate[required,funcCall[checkIdCard]] input-text">'
								+ '</div>'
								+ '</div>'
								+ '</div>'
								+ '<div class="responsive">'
								+ '<div class="row cl">'
								+ '<label class="form-label col-3"><span class="c-red">*</span>手机：</label>'
								+ '<div class="formControls col-2">'
								+ '<input type="text" name="houUserList['
								+ number
								+ '].mobile" id="mobile'+number+'" placeholder="手机" class="validate[required,custom[mobile]] input-text">'
								+ '</div>'
								+'</div>' + '</div>' + '</div>'
								+ '</div>';
					}
					$("#actualPeopleDiv").append(html);
				} else {
					for (var i = hasNum - 1; i >= totalNum; i--) {
						$(".actualPersonDiv").eq(i).remove();
					}
				}
			}
			
			function initValidate() {
				$("#step2Form").validationEngine('attach', {
					validationEventTrigger : 'change',
					showOneMessage: true,
					maxErrorsPerField : 1,
					addPromptClass: 'formError-noArrow formError-text',
					promptPosition: 'centerRight'
				});
			}
			
			function initRadioButton() {
				$('.skin-minimal input').iCheck({
					checkboxClass: 'icheckbox-blue',
					radioClass: 'iradio-blue',
					increaseArea: '20%'
				});
			}
			
			function changeOwnerPeople() {
				var optionHtml = '';
				<c:forEach items="${perIdTypes}" var="perIdType">
					optionHtml = optionHtml + '<option value="${perIdType.code}">${perIdType.name}</option>';
				</c:forEach>
				var totalNum = $("#ownerPersonNum").val();
				var hasNum = $("#ownerPeopleDiv").find(".ownerPersonDiv").length;
				var overplusNum = totalNum - hasNum;
				if (overplusNum > 0) {
					var html = '';
					for (var i = 0; i < overplusNum; i++) {
						var number = i + hasNum;
						html = html
								+ '<div class="panel panel-primary mb-30 mt-20 ownerPersonDiv">'
								+ '<div class="panel-header">所有权人'
								+ (number + 1)
								+ '</div>'
								+ '<div class="panel-body">'
								+ '<div class="responsive">'
								+ '<div class="row cl">'
								+ '<label class="form-label col-3"><span class="c-red">*</span>姓名'
								+ '：</label>'
								+ '<div class="formControls col-2">'
								+ '<input type="text" name="houCoOwnerList['
								+ number
								+ '].sysPerson.name" id="ownerName'+number+'" placeholder="姓名" onchange="checkIdNo(\'#ownerName'+number+'\',\'#ownerNo'
								+number+'\')" class="validate[required,minSize[1],maxSize[100]] input-text">'
								+ '</div>'
								+ '</div>'
								+ '</div>'
								+ '<div class="responsive">'
								+ '<div class="row cl">'
								+ '<label class="form-label col-3"><span class="c-red">*</span>身份证件：</label>'
								+ '<div class="formControls col-2">'
								+ '<span class="select-box"> <select onchange="checkIdNo(\'\',\'#ownerNo'
								+number+'\')" class="select" name="houCoOwnerList['
								+ number
								+ '].sysPerson.idType" id="idType'+number+'">'
								+ optionHtml
								+ '</select>'
								+ '</span>'
								+ '</div>'
								+ '<div class="formControls col-3">'
								+ '<input type="text" name="houCoOwnerList['
								+ number
								+ '].sysPerson.idNo" id="ownerNo'+number+'" placeholder="证件编号" class="validate[required,funcCall[checkIdCard]] input-text">'
								+ '</div>'
								+ '</div>'
								+ '</div>'
								+'</div>'
								+ '</div>';
					}
					$("#ownerPeopleDiv").append(html);
				} else {
					for (var i = hasNum - 1; i >= totalNum; i--) {
						$(".ownerPersonDiv").eq(i).remove();
					}
				}
			}
		</script>
</body>
</html>