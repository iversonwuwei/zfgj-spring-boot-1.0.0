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
	<form action="${ctx}/contp/step4" method="get" id="getForm">
		<input type="hidden" id="cid" name="cid" value="${con.id}">
	</form>
	<div class="pd-20">
		<form action="${ctx}/contp/step4" method="post"
			class="form form-horizontal" id="step4Form">
			<form:hidden path="con.id" />
			<form:hidden path="con.version" />

			<legend>甲乙丙三方确认的附加条款：</legend>

			<div class="responsive">
				<div class="row cl">
					<label class="form-label col-3"><span class="c-red">*</span>签约当日甲方提供的证件原件：</label>
				</div>
				<div class="row cl">
					<label class="form-label col-3"></label>
					<div class="formControls col-6">
						<div class="skin-minimal lessor">
							<div class="check-box">
								<form:checkbox path="con.lessorIdsArray" id="lessorIds1" value="1"
									class="ml15 mr5" />
								<label for="lessorIds1"> 身份证 </label>
							</div>
							<div class="check-box">
								<form:checkbox path="con.lessorIdsArray" id="lessorIds2" value="2"
									class="ml15 mr5" />
								<label for="lessorIds2"> 军官证 </label>
							</div>
							<div class="check-box">
								<form:checkbox path="con.lessorIdsArray" id="lessorIds3" value="3"
									class="ml15 mr5" />
								<label for="lessorIds3"> 护照</label>
							</div>
							<div class="check-box">
								<form:checkbox path="con.lessorIdsArray" id="lessorIds4" value="4"
									class="ml15 mr5" />
								<label for="lessorIds4"> 士兵证 </label>
							</div>
							<div class="check-box">
								<form:checkbox path="con.lessorIdsArray" id="lessorIds5" value="5"
									class="ml15 mr5" />
								<label for="lessorIds5"> 产权证 </label>
							</div>
							<div class="check-box">
								<form:checkbox path="con.lessorIdsArray" id="lessorIds6" value="6"
									class="ml15 mr5" />
								<label for="lessorIds6"> 使用权证 </label>
							</div>
							<div class="check-box">
								<form:checkbox path="con.lessorIdsArray" id="lessorIds7" value="7"
									class="ml15 mr5" />
								<label for="lessorIds7"> 军产证或发票 </label>
							</div>
							<div class="check-box">
								<form:checkbox path="con.lessorIdsArray" id="lessorIds8" value="8"
									class="ml15 mr5" />
								<label for="lessorIds8"> 购房合同及发票 </label>
							</div>
							<div class="check-box">
								<form:checkbox path="con.lessorIdsArray" id="lessorIds9" value="9"
									class="ml15 mr5" />
								<label for="lessorIds9"> 借款合同及发票 </label>
							</div>
							<div class="check-box">
								<form:checkbox path="con.lessorIdsArray" id="lessorIds10"
									value="a" class="ml15 mr5" />
								<label for="lessorIds10"> 地产证 </label>
							</div>
						</div>
					</div>
					<div class="row cl">
						<div class="formControls col-3"></div>
						<p class="form_text">其他证件</p>
						<div class="formControls col-2">
							<form:input path="con.lessorIdsOther" id="lessorIdsOther"
								placeholder="其他" class="validate[maxSize[50]] input-text"
								data-errormessage-value-missing="*证件原件不能为空"
								data-prompt-target="lessorIdsOtherError"
								data-prompt-position="inline" />
							<p class="form_text" id="lessorIdsOtherError"></p>
						</div>
					</div>
					<div class="row cl">
						<label class="form-label col-3"><span class="c-red">*</span>签约当日乙方提供的证件原件：</label>
					</div>
					<div class="row cl">
						<label class="form-label col-3"></label>
						<div class="formControls col-6">
							<div class="skin-minimal lessee">
								<div class="check-box">
									<form:checkbox path="con.lesseeIdsArray" id="lesseeIds1" value="1"
										class="ml15 mr5" />
									<label for="lesseeIds1"> 身份证 </label>
								</div>
								<div class="check-box">
									<form:checkbox path="con.lesseeIdsArray" id="lesseeIds2" value="2"
										class="ml15 mr5" />
									<label for="lesseeIds2"> 军官证 </label>
								</div>
								<div class="check-box">
									<form:checkbox path="con.lesseeIdsArray" id="lesseeIds3" value="3"
										class="ml15 mr5" />
									<label for="lesseeIds3"> 护照</label>
								</div>
								<div class="check-box">
									<form:checkbox path="con.lesseeIdsArray" id="lesseeIds4" value="4"
										class="ml15 mr5" />
									<label for="lesseeIds4"> 士兵证 </label>
								</div>
							</div>
						</div>
					</div>
					<div class="row cl">
						<div class="formControls col-3"></div>
						<p class="form_text">其他证件</p>
						<div class="formControls col-2">
							<form:input path="con.lesseeIdsOther" id="lesseeIdsOther"
								placeholder="其他" class="validate[maxSize[50]] input-text"
								data-errormessage-value-missing="*证件原件不能为空"
								data-prompt-target="lesseeIdsOtherError"
								data-prompt-position="inline" />
							<p class="form_text" id="lesseeIdsOtherError"></p>
						</div>
					</div>
				</div>
			</div>


			<div class="responsive">
				<div class="row cl">
					<label class="form-label col-3">甲乙丙三方其他约定</label>
				</div>
				<div class="row cl">
					<div class="formControls col-6 col-offset-3">
						<form:textarea class="textarea validate[maxSize[300]]"
							htmlEscape="false" id="additionalTerms"
							path="con.additionalTerms" placeholder="甲乙丙三方其他约定" />
					</div>
				</div>
			</div>
			<div class="responsive">
				<div class="row cl">
					<div class="formControls col-3"></div>
					<div class="formControls col-6">
						<div class="skin-minimal">
							<div class="check-box">
								<c:choose>
									<c:when
										test="${con.houItemsList != null && con.houItemsList.size() != 0}">
										<input type="checkbox" name="houItemFlg" id="houItemFlg"
											value="1" checked="checked"
											onpropertychange="showOrHideItems()"
											onchange="showOrHideItems()">
									</c:when>
									<c:otherwise>
										<input type="checkbox" name="houItemFlg" id="houItemFlg"
											value="1" onpropertychange="showOrHideItems()"
											onchange="showOrHideItems()">
									</c:otherwise>
								</c:choose>
								<label for="houItemFlg">房屋交割清单</label>
							</div>
						</div>
					</div>
				</div>
				<div class="row cl hide" id="houseItemsDiv" style="display: none">
					<div class="formControls col-3"></div>
					<div class="formControls col-8">
						<div class="cl pd-5 bg-1 bk-gray mt-20">
							房屋附属家具、电器、装修及其他设备设施状况及损赔（不在列表内物件不予追偿）</div>
						<table class="table table-border table-bordered  table-bg "
							id="houseItemsTable">
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
								<tr id="errorMessageTr">
									<td colspan="7" id="errorTd"></td>
								</tr>
								<c:choose>
									<c:when
										test="${con.houItemsList != null && con.houItemsList.size() != 0}">
										<c:forEach items="${con.houItemsList}" var="houItems"
											varStatus="status">
											<tr class="text-c">
												<td>${status.index+1}</td>
												<td><input type="text" placeholder="例：电视"
													id="item${status.index}" value="${houItems.item }"
													name="houItemsList[${status.index}].item"
													class="input-text validate[condRequired[brand${status.index},num${status.index},singleprice${status.index},price${status.index}],maxSize[50]]"
													data-prompt-target="errorTd" data-prompt-position="inline"
													data-errormessage="*项目为关联项且不超过50个字符"></td>
												<td><input type="text" placeholder="例：海尔"
													id="brand${status.index}" value="${houItems.brand }"
													name="houItemsList[${status.index}].brand"
													class="input-text validate[condRequired[item${status.index},num${status.index},singleprice${status.index},price${status.index}],maxSize[50]]"
													data-prompt-target="errorTd" data-prompt-position="inline"
													data-errormessage="*品牌为关联项且不超过50个字符"></td>
												<td><input type="text" placeholder="例：1"
													id="num${status.index}" value="${houItems.num }"
													name="houItemsList[${status.index}].num"
													class="input-text validate[condRequired[brand${status.index},item${status.index},singleprice${status.index},price${status.index}],custom[integer],max[9999],min[0]]"
													data-prompt-target="errorTd" data-prompt-position="inline"
													data-errormessage="*数量为关联项且为不超过9999的整数或是负数"></td>
												<td><input type="text" placeholder="元"
													id="singleprice${status.index}" value="${houItems.price }"
													name="houItemsList[${status.index}].price"
													class="input-text validate[condRequired[brand${status.index},item${status.index},num${status.index},price${status.index}],custom[number],max[999999],min[0]]"
													data-prompt-target="errorTd" data-prompt-position="inline"
													data-errormessage="*单价为关联项且为不超过999999的数字或是负数"></td>
												<td><input type="text" placeholder="元"
													id="price${status.index}" value="${houItems.compenAmount }"
													name="houItemsList[${status.index}].compenAmount"
													class="input-text validate[condRequired[brand${status.index},num${status.index},singleprice${status.index},item${status.index}],custom[number],max[999999],min[0]]"
													data-prompt-target="errorTd" data-prompt-position="inline"
													data-errormessage="*损赔金额为关联项且为不超过999999的数字或是负数"></td>
											</tr>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<c:forEach var="i" begin="0" end="4" varStatus="status">
											<tr class="text-c">
												<td>${i+1}</td>
												<td><input type="text" placeholder="例：电视"
													id="item${status.index}" name="houItemsList[${i }].item"
													class="input-text validate[condRequired[brand${i},num${i},singleprice${i},price${i}],maxSize[50]]"
													data-prompt-target="errorTd" data-prompt-position="inline"
													data-errormessage="*项目为关联项且不超过50个字符"></td>
												<td><input type="text" placeholder="例：海尔"
													id="brand${status.index}" name="houItemsList[${i }].brand"
													class="input-text validate[condRequired[item${i},num${i},singleprice${i},price${i}],maxSize[50]]"
													data-prompt-target="errorTd" data-prompt-position="inline"
													data-errormessage="*品牌为关联项且不超过50个字符"></td>
												<td><input type="text" placeholder="例：1"
													id="num${status.index}" name="houItemsList[${i }].num"
													class="input-text validate[condRequired[brand${i},item${i},singleprice${i},price${i}],custom[integer],max[9999],min[0]]"
													data-prompt-target="errorTd" data-prompt-position="inline"
													data-errormessage="*数量为关联项且为不超过9999的整数或是负数"></td>
												<td><input type="text" placeholder="元"
													id="singleprice${status.index}"
													name="houItemsList[${i }].price"
													class="input-text validate[condRequired[brand${i},item${i},num${i},price${i}],max[999999],min[0]]"
													data-prompt-target="errorTd" data-prompt-position="inline"
													data-errormessage="*单价为关联项且为不超过999999的整数或是负数"></td>
												<td><input type="text" placeholder="元"
													id="price${status.index}"
													name="houItemsList[${i }].compenAmount"
													class="input-text validate[condRequired[brand${i},num${i},singleprice${i},item${i}],custom[number],max[999999],min[0]]"
													data-prompt-target="errorTd" data-prompt-position="inline"
													data-errormessage="*损赔金额为关联项且为不超过999999的整数或是负数"></td>
											</tr>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</tbody>
							<tfoot>
								<tr>
									<td colspan="7" class="text-r"><a onclick="addItems()"
										class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i>添加项目</a></td>
								</tr>
							</tfoot>
						</table>
					</div>
				</div>

				<!-- 其他相关费用 -->
				<div class="responsive">
					<div class="row cl">
						<div class="formControls col-3"></div>
						<div class="formControls col-6">
							<div class="skin-minimal">
								<div class="check-box">
									<c:choose>
										<c:when
											test="${con.conOtherCostsList != null && con.conOtherCostsList.size() != 0}">
											<input type="checkbox" name="houOtherItemFlg"
												id="houOtherItemFlg" value="1" checked="checked"
												onpropertychange="showOrHideOtherItems()"
												onchange="showOrHideOtherItems()">
										</c:when>
										<c:otherwise>
											<input type="checkbox" name="houOtherItemFlg"
												id="houOtherItemFlg" value="1"
												onpropertychange="showOrHideOtherItems()"
												onchange="showOrHideOtherItems()">
										</c:otherwise>
									</c:choose>
									<label for="houOtherItemFlg">其他相关费用</label>
								</div>
							</div>
						</div>
					</div>
					<div class="row cl hide" id="houseOtherItemsDiv"
						style="display: none">
						<div class="formControls col-3"></div>
						<div class="formControls col-8">
							<table class="table table-border table-bordered  table-bg "
								id="houseOtherItemsTable">
								<thead>
									<tr class="text-c">
										<th width="60">项目</th>
										<th width="60">单位</th>
										<th width="50">单价</th>
										<th width="60">起计时间</th>
										<th width="80">起计底数</th>
									</tr>
								</thead>
								<tbody>
									<tr id="errorOtherMessageTr">
										<td colspan="5" id="errorOtherTd"></td>
									</tr>
									<c:choose>
										<c:when
											test="${con.conOtherCostsList != null && con.conOtherCostsList.size() != 0}">
											<c:forEach items="${con.conOtherCostsList}" var="houItems"
												varStatus="status">
												<tr class="text-c">
													<td><input type="text" placeholder="例：水费"
														id="otherItem${status.index}" value="${houItems.item }"
														name="conOtherCostsList[${status.index}].item"
														class="input-text validate[condRequired[otherUnit${status.index},otherPrice${status.index},otherStime${status.index},otherScount${status.index}],maxSize[50]]"
														data-prompt-target="errorOtherTd"
														data-prompt-position="inline"
														data-errormessage="*项目为关联项且不超过50个字符"></td>
													<td><input type="text" placeholder="例：元"
														id="otherUnit${status.index}" value="${houItems.unit }"
														name="conOtherCostsList[${status.index}].unit"
														class="input-text validate[condRequired[otherItem${status.index},otherPrice${status.index},otherStime${status.index},otherScount${status.index}],maxSize[50]]"
														data-prompt-target="errorOtherTd"
														data-prompt-position="inline"
														data-errormessage="*单位为关联项且不超过50个字符"></td>
													<td><input type="text" placeholder="例：1"
														id="otherPrice${status.index}" value="${houItems.price }"
														name="conOtherCostsList[${status.index}].price"
														class="input-text validate[condRequired[otherItem${status.index},otherUnit${status.index},otherStime${status.index},otherScount${status.index}],max[999999],min[0]]"
														data-prompt-target="errorOtherTd"
														data-prompt-position="inline"
														data-errormessage="*单价为关联项且为不超过999999的整数或是负数"></td>
													<td><input type="text" placeholder="例：1970-01-01"
														id="otherStime${status.index}"
														value="<fmt:formatDate value="${houItems.stime }" pattern="yyyy-MM-dd" />"
														name="conOtherCostsList[${status.index}].stime"
														class="input-text validate[condRequired[otherItem${status.index},otherUnit${status.index},otherPrice${status.index},otherScount${status.index}],custom[dateFormat]]"
														data-prompt-target="errorOtherTd"
														data-prompt-position="inline"
														data-errormessage="*起计时间格式YYYY-MM-DD"></td>
													<td><input type="text" placeholder="例：1"
														id="otherScount${status.index}"
														value="${houItems.scount }"
														name="conOtherCostsList[${status.index}].scount"
														class="input-text validate[condRequired[otherItem${status.index},otherUnit${status.index},otherPrice${status.index},otherStime${status.index}]],maxSize[8]]"
														data-prompt-target="errorOtherTd"
														data-prompt-position="inline"
														data-errormessage="*起计底数为关联项目且不超过8个字符"></td>
												</tr>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<c:forEach var="sysCode" begin="0" end="10"
												varStatus="status" items="${sysCodeList}">
												<tr class="text-c">
													<input type="hidden" value="${sysCode.name}"
														name="conOtherCostsList[${status.index }].item">
													<td>${sysCode.name}</td>
													<td><input type="text" placeholder="例：吨"
														id="otherUnit${status.index}"
														name="conOtherCostsList[${status.index }].unit"
														class="input-text validate[condRequired[otherPrice${status.index},otherStime${status.index},otherScount${status.index}],maxSize[50]]"
														data-prompt-target="errorOtherTd"
														data-prompt-position="inline"
														data-errormessage="*单位为关联项且不超过50个字符"></td>
													<td><input type="text" placeholder="元"
														id="otherPrice${status.index}"
														name="conOtherCostsList[${status.index }].price"
														class="input-text validate[condRequired[otherUnit${status.index},otherStime${status.index},otherScount${status.index}],max[999999],min[0]]"
														data-prompt-target="errorOtherTd"
														data-prompt-position="inline"
														data-errormessage="*单价为关联项且为不超过999999的整数或是负数"></td>
													<td><input type="text" placeholder="例：1970-01-01"
														id="otherStime${status.index}"
														name="conOtherCostsList[${status.index }].stime"
														class="input-text validate[condRequired[otherUnit${status.index},otherPrice${status.index},otherScount${status.index}],custom[dateFormat]]"
														data-prompt-target="errorOtherTd"
														data-prompt-position="inline"
														data-errormessage="*起计时间格式YYYY-MM-DD"></td>
													<td><input type="text" placeholder="例：1"
														id="otherScount${status.index}"
														name="conOtherCostsList[${status.index }].scount"
														class="input-text validate[condRequired[otherUnit${status.index},otherPrice${status.index},otherStime${status.index}]],maxSize[8]]"
														data-prompt-target="errorOtherTd"
														data-prompt-position="inline"
														data-errormessage="*起计底数为关联项目且不超过8个字符"></td>
												</tr>
											</c:forEach>
										</c:otherwise>
									</c:choose>
								</tbody>
								<tfoot>
									<tr>
										<td colspan="5" class="text-r"><a
											onclick="addOtherItems()" class="btn btn-primary radius"><i
												class="Hui-iconfont">&#xe600;</i>添加项目</a></td>
									</tr>
								</tfoot>
							</table>
						</div>
					</div>
				</div>

			</div>
			<div class="row cl btn_group">
				<div class="col-9 col-offset-3">
					<a class="btn btn-default radius"
						href="${ctx}/contp/step3?cid=${con.id}">上一步</a> <a
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
		src="${ctxStatic}${hui}lib/icheck/jquery.icheck.min.js"></script>
	<script type="text/javascript"
		src="${ctxStatic}${hui}js/jquery-migrate-1.1.0.min.js"></script>
	<script type="text/javascript">
		var num = parseInt('${con.houItemsList.size()}');
		var otherNum = parseInt('${con.conOtherCostsList.size()}');
		if (isNaN(num) || num == 0) {
			num = 5;
		}
		if(isNaN(otherNum) || otherNum == 0){
			otherNum = 11
		}
		$(function() {
			$('.skin-minimal input').iCheck({
				checkboxClass: 'icheckbox-blue',
				radioClass: 'iradio-blue',
				increaseArea: '20%'
			});
			$("#step4Form").validationEngine('attach', {
				validationEventTrigger : 'change',
				showOneMessage: true,
				maxErrorsPerField : 1,
				addPromptClass: 'formError-noArrow formError-text',
				promptPosition: 'centerRight'
			});
			
			showOrHideItems();
			showOrHideOtherItems()
		});
			
		function submitForm() {
			if($(".lessor .icheckbox-blue").hasClass("checked") == false){
				$('#lessorIdsOther').removeClass("validate[maxSize[50]]");
				$('#lessorIdsOther').addClass("validate[required,maxSize[50]]");
			}else{
				$('#lessorIdsOther').addClass("validate[maxSize[50]]");
				$('#lessorIdsOther').removeClass("validate[required,maxSize[50]]");
			}
			
			if($(".lessee .icheckbox-blue").hasClass("checked") == false){
				$('#lesseeIdsOther').removeClass("validate[maxSize[50]]");
				$('#lesseeIdsOther').addClass("validate[required,maxSize[50]]");
			}else{
				$('#lesseeIdsOther').addClass("validate[maxSize[50]]");
				$('#lesseeIdsOther').removeClass("validate[required,maxSize[50]]");
			}
			
			$("#step4Form").submit();
		}
		
		function showOrHideItems() {
			var checked = $("#houItemFlg").attr("checked");
			var ob = document.getElementById("houseItemsDiv");
			if (checked == "checked") {
				/* $("#houseItemsDiv").css("display", "block"); */
				ob.style.display = "block";
			} else {
				/* $("#houseItemsDiv").css("display", "none"); */
				ob.style.display = "none";
			}
		}
		
		function addItems() {
			var html = '<tr class="text-c">'
				+'<td>'+(num+1)+'</td>'
				+'<td><input type="text" id="item'+num+'" placeholder="例：电视" name="houItemsList['+num+'].item" class="input-text validate[condRequired[brand'+num+',num'+num+',singleprice'+num+',price'+num+'],maxSize[50]]" data-prompt-target="errorTd"  data-prompt-position="inline" data-errormessage="*项目为关联项且不超过50个字符"></td>'
				+'<td><input type="text" id="brand'+num+'" placeholder="例：海尔" name="houItemsList['+num+'].brand" class="input-text validate[condRequired[item'+num+',num'+num+',singleprice'+num+',price'+num+'],maxSize[50]]" data-prompt-target="errorTd"  data-prompt-position="inline" data-errormessage="*品牌为关联项且不超过50个字符"></td>'
				+'<td><input type="text" id="num'+num+'" placeholder="例：1" name="houItemsList['+num+'].num" class="input-text validate[condRequired[brand'+num+',item'+num+',singleprice'+num+',price'+num+'],custom[integer],max[9999],min[0]]" data-prompt-target="errorTd"  data-prompt-position="inline" data-errormessage="*数量为关联项且为不超过9999的整数或是负数"></td>'
				+'<td><input type="text" id="singleprice'+num+'" placeholder="元" name="houItemsList['+num+'].price" class="input-text validate[condRequired[brand'+num+',item'+num+',num'+num+',price'+num+'],max[999999],min[0]]]" data-prompt-target="errorTd"  data-prompt-position="inline" data-errormessage="*单价为关联项且为不超过999999的数字或是负数"></td>'
				+'<td><input type="text" id="price'+num+'" placeholder="元" name="houItemsList['+num+'].compenAmount" class="input-text validate[condRequired[brand'+num+',num'+num+',singleprice'+num+',item'+num+'],custom[number],max[999999],min[0]]" data-prompt-target="errorTd"  data-prompt-position="inline" data-errormessage="*损赔金额为关联项且为不超过999999的数字或是负数"></td>';
			$("#houseItemsTable tbody").append(html);
			num++;
		}
		
		function showOrHideOtherItems() {
			var checked = $("#houOtherItemFlg").attr("checked");
			var ob = document.getElementById("houseOtherItemsDiv");
			if (checked == "checked") {
				/* $("#houseItemsDiv").css("display", "block"); */
				ob.style.display = "block";
			} else {
				/* $("#houseItemsDiv").css("display", "none"); */
				ob.style.display = "none";
			}
		}
		
		function addOtherItems() {
			var html = '<tr class="text-c">'
				+'<td><input type="text" id="otherItem'+otherNum+'" placeholder="例：水费" name="conOtherCostsList['+otherNum+'].item" class="input-text validate[condRequired[otherUnit'+otherNum+',otherPrice'+otherNum+',otherStime'+otherNum+',otherScount'+otherNum+'],maxSize[50]]" data-prompt-target="errorOtherTd"  data-prompt-position="inline" data-errormessage="*项目为关联项且不超过50个字符"></td>'
				+'<td><input type="text" id="otherUnit'+otherNum+'" placeholder="例：吨" name="conOtherCostsList['+otherNum+'].unit" class="input-text validate[condRequired[otherItem'+otherNum+',otherPrice'+otherNum+',otherStime'+otherNum+',otherScount'+otherNum+'],maxSize[50]]" data-prompt-target="errorOtherTd"  data-prompt-position="inline" data-errormessage="*单位为关联项且不超过50个字符"></td>'
				+'<td><input type="text" id="otherPrice'+otherNum+'" placeholder="元" name="conOtherCostsList['+otherNum+'].price" class="input-text validate[condRequired[otherItem'+otherNum+',otherUnit'+otherNum+',otherStime'+otherNum+',otherScount'+otherNum+'],max[999999],min[0]]" data-prompt-target="errorOtherTd"  data-prompt-position="inline" data-errormessage="*单价为关联项且为不超过999999的整数或是负数"></td>'
				+'<td><input type="text" id="otherStime'+otherNum+'" placeholder="例：1970-01-01" name="conOtherCostsList['+otherNum+'].stime" class="input-text validate[condRequired[otherItem'+otherNum+',otherUnit'+otherNum+',otherPrice'+otherNum+',otherScount'+otherNum+'],custom[dateFormat]]" data-prompt-target="errorOtherTd"  data-prompt-position="inline" data-errormessage="*起计时间格式YYYY-MM-DD"></td>'
				+'<td><input type="text" id="otherScount'+otherNum+'" placeholder="例：1" name="conOtherCostsList['+otherNum+'].scount" class="input-text validate[condRequired[otherItem'+otherNum+',otherUnit'+otherNum+',otherPrice'+otherNum+',otherStime'+otherNum+'],maxSize[8]]" data-prompt-target="errorOtherTd"  data-prompt-position="inline" data-errormessage="*起计底数为关联项目且不超过8个字符"></td>';
			$("#houseOtherItemsTable tbody").append(html);
			otherNum++;
		}
		</script>
</body>
</html>