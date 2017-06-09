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
	<form action="${ctx}/con/step2" method="get" id="getForm">
		<input type="hidden" id="cid" name="cid" value="${con.id}">
	</form>
	<div class="pd-20">
		<form action="${ctx}/con/step2" method="post"
			class="form form-horizontal" id="step2Form">
			<form:hidden path="con.id" />
			<form:hidden path="con.hid" id="hid" />
			<form:hidden path="con.version" />
			<form:hidden path="con.lessorName" />
			<form:hidden path="con.lessorIdNo" />
			<legend>
				出租房源
<%-- 				<a href="${ctx}/house/toadd" class="btn btn-primary radius r"> --%>
<!-- 				<i class="Hui-iconfont">&#xe600;</i>添加新房源</a> -->
			</legend>
			<div class="responsive">
				<div class="row cl">
					<div class="house_choose_list">
						<c:choose>
							<c:when test="${houseInfos != null && houseInfos.size() > 0}">
								<ul id="houAddr">
									<c:forEach items="${houseInfos}" var="houseInfo">
										<li id="houseInfo${houseInfo.id}">
											<p class="house_choose_operation">
												<!-- <a href="#">默认</a><a href="#">编辑</a><a href="#">删除</a> -->
											</p> 
											<a class="house_address" title="${houseInfo.estate}${houseInfo.houseAddr}" onclick="setHouse('${houseInfo.id}', this)">
											<i class="icos icos_house_choose"></i> 
											<c:choose>
												<c:when test="${houseInfo.estate != null && fn:trim(houseInfo.estate) != ''}">
													<c:choose>
														<c:when test="${fn:length(houseInfo.estate) <= 18}">
														${houseInfo.estate}
														</c:when>
														<c:otherwise>
														${fn:substring(houseInfo.estate, 0, 17)}...
														</c:otherwise>
													</c:choose>
												</c:when>
												<c:otherwise>
													<c:choose>
														<c:when test="${fn:length(houseInfo.houseAddr) <= 14}">
														${houseInfo.houseAddr}
														</c:when>
														<c:otherwise>
														${fn:substring(houseInfo.houseAddr, 0, 13)}...
														</c:otherwise>
													</c:choose>
												</c:otherwise>
											</c:choose> </a>
											
											大连市 ${houseInfo.districtName} ${houseInfo.houseAddr}
											<c:if test="${houseInfo.certifyStatus== 1 }">
												<span class="label label-success ml-5">认证房源</span>
											</c:if>
										</li>
									</c:forEach>
								</ul>
								<p>
									<a href="" class="choose_more link_help">其他房源 <i
										class="icos icos_more"></i></a>
								</p>
							</c:when>
							<c:otherwise>
								<p class="warning_message tc bold">
									无房源所属为出租人的房源，立刻<a href="${ctx}/house/toadd" class="btn-link">新建房源</a>
								</p>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
			
<%-- 			<legend>租金分期</legend>
		      <div class="row cl skin-minimal">
		        <label class="form-label col-3">租房分期：</label>
		        <div class="formControls col-6">
		          <div class="form_text">
		          <div class="c-999 rent-instruction">租金分期服务说明 &gt;&gt;
		            <div class="rent-tips"> <i class="rent-tip-ico"></i>
						“租金分期”是大连市房屋租赁公共服务平台和中国建设银行联合推出的房屋租金信用卡分期支付服务。使用租金分期，房客可享有0息、0首付及12期的分期支付方式，房主则可一次收到全年租金。
					</div>
		          </div>
		          </div>
		        </div>
		        </div>
		        <div class="row cl skin-minimal">
		        <label class="form-label col-3">&nbsp;</label>
		        <div class="formControls  col-6">
		          <div class="check-box">
		            <form:checkbox path="con.stages" id="checkbox-1" onclick="checkboxStage();" value="01"/>
		            <label for="checkbox-1">使用租金分期服务</label>
		          </div>
		        </div>
		      </div>
		      <div class="pd-20" id="layerStage" style="display: none;">
				<p>“租金分期”服务支持租赁期限为一年，使用租金分期后：</p>		      
				<p class="mb-20">房主将一次性收取一年的房租，平台将收取房主年租金额度5%的服务费，此费用直接从房客支付的租金中扣除。</p>
				<p>房客须分12期按月向建行还款，本分期服务<span class="c-red">免息</span>。请确保房客已经申请建行信用卡并有足够的额度：</p>
				<table class="mt-20 mb-20">
					<td><a href="http://creditcard.ccb.com/cn/creditcard/apply/apply.html" class="btn-link f-14" target="_blank">还没有建行信用卡？在线申请&gt;&gt;</a></td><td><a href="${ctx}/con/ew" target="_blank" class="btn-link f-14">其他申请方式&gt;&gt;</a></td>
				</table>
				<p>更多服务条款，详见：“<span class="btn-link" onclick="lessorclause()">《房租一次性支付分期还款服务协议（出租方）》</span>”、“<span class="btn-link" onclick="lesseeclause()">《房租一次性支付分期还款服务协议（承租方）》</span>”，以上两份协议请在下载并由双方签署后与合同一起上传。</p>
		      <p class="text-c mt-20"><a class="btn btn-primary" onclick="closeLayer();">确 定</a></p>
		      </div> --%>
		      
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
								onchange="getCanRentArea()" class="select">
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

					<%-- <c:choose>
						<c:when test="${contractFlag == 1 or contractFlag == 3}"> --%>
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
						<%-- </c:when>
						<c:when test="${contractFlag == 2}">
							<div class="formControls col-3">
								<div class="radio-box skin-minimal">
									<form:radiobutton path="con.rentalMode" id="partRent"
										onclick="showDmAndAreaDiv()" value="2" />
									<label for="partRent" class="form-label">部分</label>
								</div>
							</div>
						</c:when>
					</c:choose> --%>


					<label class="form-label col-2"><span class="c-red">*</span>租赁用途：</label>
					<div class="formControls col-3">
						<div class="radio-box skin-minimal">
							<form:radiobutton path="con.leasePurpose" id="civil" value="1" />
							<label for="civil" class="form-label">住宅</label>
						</div>
						<%--<div class="radio-box skin-minimal">
							<form:radiobutton path="con.leasePurpose" id="business" value="2" />
							<label for="business" class="form-label">商用</label>
						</div> --%>
					</div>
				</div>
			</div>
			<div class="responsive" id="doMainAndAreaDiv">
				<div class="row cl">
					<label class="form-label col-2"><span class="c-red">*</span>出租区域：</label>
					<div class="formControls col-2">
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
						<p class="form_text c-999">
							（剩余可租面积：<span id="overplusArea"></span>平方米）
						</p>
						<p class="form_text" id="areaError"></p>
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
															class="validate[required,funcCall[checkIdCard]] input-text"
															data-prompt-target="errorOtherTd${status.index}"
															data-prompt-position="inline">
													</div>
													<div class="formControls col-4" id="errorOtherTd${status.index}">
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
														class="validate[required,funcCall[checkIdCard]] input-text"
														data-prompt-target="errorOtherTd0" data-prompt-position="inline">
												</div>
												<div class="formControls col-4" id="errorOtherTd0">
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
						href="${ctx}/con/step1?cid=${con.id}">上一步</a> <a
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
				initDefaultHouseInfo('${con.hid}');
				initDmAndAreaDiv();
				initRadioButton();
				initValidate();
				stageInit();
			});
			
			function checkLeaseArea(obj) {
				var leaseAreaVal = 0;
				var objVal = obj.val();
				var fLeaseAreaVal = parseFloat(objVal);
				if (fLeaseAreaVal < 4) {
					return "*出租面积不能小于4平方米";
				} else {
					var overplusAreaVal = parseInt($("#overplusArea").text());
					if (!isNaN(overplusAreaVal)) {
						if (fLeaseAreaVal > overplusAreaVal) {
							return "*出租面积不能大于" + overplusAreaVal + "平方米";
						} else {
							changeSel(parseInt(fLeaseAreaVal));
						}
					} else {
						return "*出租面积不能大于剩余可租面积";
					}
				}
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
					var nameObj = obj.attr("id").replace('idNo', '');
					var name = $("#name" + nameObj).val();
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
				var info;
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
						} else {
							info = data.message;
						}
					}
				});
				if (success) {
					return true;
				} else {
					return info;
				}
			}
			
			function submitForm() {
				$("#leaseTermYear").removeAttr("disabled");
				$("#leaseTermMonth").removeAttr("disabled");
				$("#step2Form").submit();
			}
			
			function hideDmAndAreaDiv() {
				$("#doMainAndAreaDiv").css("display", "none");
				changeSelPeople($("#overplusArea").text());
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
			
			function initDefaultHouseInfo(hid) {
				if (hid != "") {
					var html = "";
					$(".house_choose_list>ul>li").each(function() {
						if (hid == this.id.replace("houseInfo", "")) {
							html = $(this).clone();
							$(this).remove();
							return false;
						}
					});
					$(".house_choose_list>ul").prepend(html);
					$(".house_choose_list>ul>li").eq(0).find("a").eq(0).click();
				} else {
					if ($(".house_choose_list>ul>li").length > 0) {
						$(".house_choose_list>ul>li").eq(0).find("a").eq(0).click();
					}
				}
			}
			
			function setHouse(houseId, obj) {
				$(".house_address").removeClass("active");
				$(obj).addClass("active");
				$("#hid").val(houseId);
				getCanRentArea();
			}
			
			function initSelActualPerson() {
				var leaseArea = '${con.leaseArea}';
				if (leaseArea != "") {
					var num = parseInt(leaseArea) / 4;
					var html = "";
					for (var i = 1; i <= num; i++) {
						html = html + '<option value="' + i + '">' + i
								+ '</option>';
					}
					$("#actualPersonNum").html(html);
					$("#actualPersonNum").val('${actualPersonNum}');
				}
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
					$("#overplusArea").text("");
					return;
				}
				var startDate = newDate(startTime);
				var endDate = newDate(startTime);
				endDate.setMonth(startDate.getMonth() + totalMonth);
				if (startDate.getDate() == endDate.getDate()) {
					endDate.setDate(endDate.getDate()-1);
				} else {
					endDate.setDate(0);
				}
				var endTime = formatDate(endDate);
				var hidVal = $("#hid").val();
				var success = false;
				var overplusArea = '';
				var wholeRentFlg = '';
				$.ajax({
					type : "POST",
					url : '${ctx}/con/gcra',
					data : {
						startTime : startTime,
						endTime : endTime,
						hid : hidVal,
						cid : '${con.id}'
					},
					dataType : 'json',
					cache : false,
					async : false,
					success : function(data) {
						if (1 == data.success) {
							overplusArea = data.data.overplusArea;
							wholeRentFlg = data.data.wholeRentFlg;
							success = true;
							
							if(overplusArea == "0"){
								layer.msg("当前合同起止时间内有生效的合同，无法创建新合同");
							}
						}
					}
				});
				
				if (success) {
					$("#endTimeDiv").css("display", "inline");
					$("#endTimeLabel").text(endTime);
					$("#endTime").val(endTime);
					if (wholeRentFlg == "2") {
						$("input[name='rentalMode']").eq(0).attr("disabled", true);
						$("input[name='rentalMode']").eq(0).removeAttr("onclick");
						$(".iradio-blue").eq(0).removeClass("checked"); 
						$(".iradio-blue").eq(1).addClass("checked");
						$("input[name='rentalMode']").eq(1).click();
						$("#overplusArea").text(overplusArea);
					} else {
						$("input[name='rentalMode']").eq(0).attr("disabled", false);
						var a = $("input[name='rentalMode']").eq(0).attr("onclick");
						if (a != 'hideDmAndAreaDiv()') {
							$("input[name='rentalMode']").eq(0).attr("onclick","hideDmAndAreaDiv()");
						}
						$(".iradio-blue").eq(1).removeClass("checked"); 
						$(".iradio-blue").eq(0).addClass("checked");
						$("#overplusArea").text(overplusArea);
					}
					changeSel(overplusArea);
				} else {
					$("#endTimeDiv").css("display", "none");
					$("#endTimeLabel").text("");
					$("#endTime").val("");
					$("#overplusArea").text("");
				}
				initRadioButton();
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
								+ '].idNo" id="idNo'+number+'" placeholder="证件编号" class="validate[required,funcCall[checkIdCard]] input-text" data-prompt-target="errorOtherTd'+number+'" data-prompt-position="inline">'
								+ '</div>'
								+ '<div class="formControls col-4" id="errorOtherTd'+number+'"></div>'
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
			
			var _layer;
			function checkboxStage() {
				if($("#checkbox-1").prop("checked")){
					_layer = layer.open({
						type : 1, //page层
						area : [ '550px', '350px' ],
						title: false,
						closeBtn: 0,
						shade : 0.6, //遮罩透明度
						moveType : 1, //拖拽风格，0是默认，1是传统拖动
						shift : 0, //0-6的动画形式，-1不开启
						content : $('#layerStage')
					});
					$("#layerStage").show();
					$("#leaseTermYear").val("1");
					$("#leaseTermMonth").val("0");
					$("#leaseTermYear").attr("disabled","disabled");
					$("#leaseTermMonth").attr("disabled","disabled");
					getCanRentArea();
				}else{
					layer.close(_layer);
					$("#leaseTermYear").removeAttr("disabled");
					$("#leaseTermMonth").removeAttr("disabled");
				}
			}
			
			function stageInit() {
				if($("#checkbox-1").prop("checked")){
					$("#leaseTermYear").val("1");
					$("#leaseTermMonth").val("0");
					$("#leaseTermYear").attr("disabled","disabled");
					$("#leaseTermMonth").attr("disabled","disabled");
				}
			}
			
			function closeLayer() {
				layer.close(_layer);
			}
			
			var _lessorlayer;
	 		function lessorclause(){
	 			_lessorlayer=layer.open({
	 				type: 2, //page层
	 				area: ['700px', '600px'],
	 				title: "《房租一次性支付分期还款服务协议（出租方）》",
	 				shade: 0.6, //遮罩透明度
	 				moveType: 1, //拖拽风格，0是默认，1是传统拖动
	 				shift: 0, //0-6的动画形式，-1不开启
	 				closeBtn: 1,
	 				content: '${ctx}/con/orsc'
	 			});
	 		}
	 		
	 		var _lesseelayer;
	 		function lesseeclause(){
	 			_lesseelayer=layer.open({
	 				type: 2, //page层
	 				area: ['700px', '600px'],
	 				title: "《房租一次性支付分期还款服务协议（承租方）》",
	 				shade: 0.6, //遮罩透明度
	 				moveType: 1, //拖拽风格，0是默认，1是传统拖动
	 				shift: 0, //0-6的动画形式，-1不开启
	 				closeBtn: 1,
	 				content: '${ctx}/con/eesc'
	 			});
	 		}
	 		
	 		function lessee_close(){
				layer.close(_lesseelayer);
			}
	 		
	 		function lessor_close(){
				layer.close(_lessorlayer);
			}
	 		$("#houAddr").find("a.house_address").hover(function(){
	 			$(this).next("div.detailed_info").show();
	 		},function(){
	 			$(this).next("div.detailed_info").hide();
 	 		});
		</script>
</body>
</html>