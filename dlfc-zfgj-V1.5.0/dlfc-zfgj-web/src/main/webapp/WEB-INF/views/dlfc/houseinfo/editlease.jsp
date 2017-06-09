<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<!DOCTYPE HTML>
<html>
<head>

<%@ include file="/WEB-INF/views/include/head2.jsp"%>
<link href="${pageContext.request.contextPath}/static/hui/lib/icheck/icheck.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/static/hui/lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/static/hui/lib/webuploader/0.1.5/mulImages.css" rel="stylesheet" type="text/css" />
<title>添加管理员</title>
<style>
.queueList .placeholder #filePicker{float:none;}
</style>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		房源管理 <span class="c-gray en">&gt;</span>编辑出租信息<a
			class="btn btn-success radius r mr-20"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>

	<form action="javascript:void(0)" method="post" class="form form-horizontal" id="addLeaseForm">		
		<c:choose>
			<c:when test="${not empty leaseInfo.releaseStatus && leaseInfo.releaseStatus=='2' && leaseInfo.auditStatus!=null && leaseInfo.auditStatus=='2'}">
				<div class=" contract_fail"><i class="Hui-iconfont">&#xe6e0;</i>
					审核失败：${leaseInfo.rejectReason}  出租信息已被强制下架
				</div>
			</c:when>

			<c:when test="${not empty leaseInfo.releaseStatus && leaseInfo.releaseStatus=='2'}">
				<div class=" contract_fail"><i class="Hui-iconfont">&#xe6e0;</i>
					 强制下架：${leaseInfo.shelfReason}     
				</div>
			</c:when>
		</c:choose>

		<form:hidden path="leaseInfo.hid"/>
		<form:hidden path="leaseInfo.id"/>
		<form:hidden path="leaseInfo.releaseStatus" id="releaseStatus"/>
		<form:hidden path="leaseInfo.checkinTime" id="checkinTime"/>
		<input type="hidden" id="dist" value="${leaseInfo.districtDist}">
		
		<input type="hidden" id="mainimgId_path" name="mainimgId_path" value="${mainimgId_path}">
		<input type="hidden" id="layoutimgId_path" name="layoutimgId_path" value="${layoutimgId_path}">
		<input type="hidden" id="mulImage_path" name="mulImage_path" value="${mulImage_path}">
		<input type="hidden" id="buildingArea" name="buildingArea" value="${buildingArea}">
		<input type="hidden" id="longitude" name="longitude" value="${longitude}"/>
		<input type="hidden" id="latitude" name="latitude" value="${latitude}"/>
		<input type="hidden" id="houseAddr" value="${houseAddr}"/>
		
		<legend>出租意向</legend>
		<div class="responsive">
			<%-- <div class="row cl skin-minimal">
				<label class="form-label col-3"><span class="c-red">*</span>房屋类型：</label>
				<div class="formControls	col-6">
					<div class="radio-box">
						<form:radiobutton path="leaseInfo.houseType" value="1" id="houseType" /><label for="houseType">普通住宅</label>
					</div>
					<div class="radio-box">
						<form:radiobutton path="leaseInfo.houseType" value="2" id="houseType2"/><label for="houseType2">别墅</label>
					</div>
				</div>
			</div> --%>
			<div class="row cl skin-minimal">
				<label class="form-label col-3">出租方式：</label>
				<div class="formControls col-6">
					<div class="radio-box">
						<form:radiobutton path="leaseInfo.leaseMode" value="1" id="leaseMode" onclick="changeLeaseType();"/><label for="leaseMode">整租</label>
					</div>
					<div class="radio-box">
						<form:radiobutton path="leaseInfo.leaseMode" value="2" id="leaseMode2" onclick="changeLeaseType();"/><label for="leaseMode2">合租</label>
					</div>
				</div>
			</div>
			<div id="first_div">
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>房屋租金：</label>
				<div class="formControls col-2">
					<form:input path="leaseInfo.rent" maxlength="10" class="validate[required,funcCall[checkNum]] input-text " data-prompt-target="errorTd_rentType"	data-prompt-position="inline"/>
				</div>
				<p class="form_text">元/月</p>
				<div class="formControls col-1">
					<span class="select-box"> 
						<form:select path="leaseInfo.rentDeposit" id="rentDeposit" class="select" size="1">
							<form:options items="${rent_deposit}" itemValue="code" itemLabel="name" />
						</form:select>
					</span>
					
				</div>
				<div class="formControls col-1">
					<span class="select-box"> 
						<form:select path="leaseInfo.rentPayment" id="rentType" class="select" size="1">
							<form:options items="${rent_payment}" itemValue="code" itemLabel="name" />
						</form:select>
					</span>
				</div>
				<div class="form_text" id="errorTd_rentType"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-3">可入住时间：</label>
				<div class="formControls col-2">
					<%-- <form:input path="leaseInfo.checkinTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" maxlength="10" id="datemin" class="validate[required] input-text Wdate"/> --%>
					<input id="checkinTime_temp" type="text" readonly="readonly" maxlength="20" class="validate[funcCall[checkEndTime]]  input-text Wdate" style="width:163px;"
						value="<fmt:formatDate value="${leaseInfo.checkinTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
				</div>
			</div>
			</div>
			<legend>基本信息</legend>

			<div class="row cl">
				<label class="form-label col-3">小区名称：</label>
				<div class="formControls col-3">
					<form:input path="leaseInfo.villageName" maxlength="20" class="input-text "/>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>所属商圈：</label>
				<div class="formControls col-2">
					<span class="select-box">
						<form:select path="leaseInfo.districtArea" id="districtArea" class="validate[required] select" size="1" onclick="changeArea();" data-errormessage="区域必填">
							<form:option value=""> 选择区 </form:option>
							<form:options items="${district_list}" itemValue="areaId" itemLabel="area" />
						</form:select>
					</span>
				</div>
				<div class="formControls col-3">
					<span class="select-box"> 
						<form:select path="leaseInfo.districtDist" id="districtDist" class="validate[required] select" size="1" onclick="changeDist();" data-errormessage="商圈必填">
							<%-- <form:options items="${trade_areas}" itemValue="id" itemLabel="name" /> --%>
						</form:select>
					</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-3">相邻地铁站：</label>
				<div class="formControls col-2">
					<span class="select-box"> 
						<form:select path="leaseInfo.stationid" id="stationid" class="select" size="1">
							<%-- <form:options items="${trade_areas}" itemValue="id" itemLabel="name" /> --%>
						</form:select>
					</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-3">楼号：</label>
				<div class="formControls col-1">
					<form:input path="leaseInfo.noBuildingNo" maxlength="10" class="validate[funcCall[buildNum]] input-text " data-prompt-target="errorTd_no"	data-prompt-position="inline"/>
				</div>
				<p class="form_text">号楼/栋</p>
				<div class="formControls col-1">
					<form:input path="leaseInfo.noUnit" maxlength="10" class="validate[funcCall[buildNum]] input-text " data-prompt-target="errorTd_no"	data-prompt-position="inline"/>
				</div>
				<p class="form_text">单元</p>
				<div class="formControls col-1">
					<form:input path="leaseInfo.noRoom" maxlength="10" class="validate[funcCall[buildNum]] input-text " data-prompt-target="errorTd_no"	data-prompt-position="inline"/>
				</div>
				<p class="form_text">室</p>
				<p class="form_text c-999 f-12">(楼栋号不对外显示)</p>
				<div class="form_text" id="errorTd_no"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>楼层：</label>
<!-- 				<div class="formControls col-1"> -->
<%-- 					<form:input path="leaseInfo.floorNo" maxlength="10" class="validate[required,funcCall[checkNum]] input-text " data-prompt-target="errorTd_floor"	data-prompt-position="inline"/> --%>
<!-- 				</div> -->
<!-- 				<p class="form_text">层，</p> -->
				<p class="form_text">共</p>
				<div class="formControls col-1">
					<form:input path="leaseInfo.floorNum" maxlength="10" class="validate[required,funcCall[checkNum]] input-text " data-prompt-target="errorTd_floor"	data-prompt-position="inline"/>
				</div>
				<p class="form_text">层，在</p>
				<div class="formControls col-1">
				<form:select path="leaseInfo.floorNo" cssClass="validate[required,funcCall[checkFloor]] select" id="floorNo">
					<option value="">请填写总层数</option>
				</form:select>
				</div>
				<div class="form_text" id="errorTd_floor"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-3">建筑时间：</label>
				<div class="formControls col-2">
					<span class="select-box"> 
						<form:select path="leaseInfo.buildTime" id="buildTime" class="select" size="1">
							<form:options items="${build_time}" itemValue="code" itemLabel="name" />
						</form:select>
					</span>
				</div>
			</div>
			<div class="row">
				<div class="line"></div>
			</div>

			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>户型：</label>
				<div class="formControls col-1">
					<form:input path="leaseInfo.aparmentRoom" maxlength="1" class="validate[required,funcCall[checkRoom]] input-text " data-prompt-target="errorTd_apartmentLayout"	data-prompt-position="inline"/>
				</div>
				<p class="form_text">室</p>
				<div class="formControls col-1">
					<form:input path="leaseInfo.aparmentHall" maxlength="1" class="validate[required,funcCall[checkNum]] input-text " data-prompt-target="errorTd_apartmentLayout"	data-prompt-position="inline"/>
				</div>
				<p class="form_text">厅</p>
				<div class="formControls col-1">
					<form:input path="leaseInfo.aparmentToilet" maxlength="1" class="validate[required,funcCall[checkNum]] input-text " data-prompt-target="errorTd_apartmentLayout"	data-prompt-position="inline"/>
				</div>
				<p class="form_text">卫</p>
				<div class="form_text" id="errorTd_apartmentLayout"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-3">户型朝向：</label>
				<div class="formControls col-2">
					<span class="select-box"> 
						<form:select path="leaseInfo.orientation" id="orientation" class="select" size="1">
							<form:options items="${hou_orientation}" itemValue="code" itemLabel="name" />
						</form:select>
					</span>
				</div>
			</div>
			<div class="row cl skin-minimal">
				<label class="form-label col-3">装修：</label>
				<div class="formControls col-3">
					<c:forEach items="${hou_decor}" var="code" varStatus="vstatus">
						<div class="radio-box">
							<form:radiobutton path="leaseInfo.decor" value="${code.code}" id="decor${vstatus.index}" data-prompt-target="errorTd_decor" data-prompt-position="inline" class="validate[required]"/><label for="decor${vstatus.index}">${code.name}</label>
						</div>
					</c:forEach>
				</div>
				<div class="form_text" id="errorTd_decor"></div>
			</div>
			<div class="row cl skin-minimal">
				<label class="form-label col-3">装修时间：</label>
				<div class="formControls col-2">
					<span class="select-box">
						<form:select path="leaseInfo.decorTime" id="decor_time" class="validate[required] select" size="1">
							<form:options items="${decor_time}" itemValue="code" itemLabel="name" />
						</form:select>
					</span>
				</div>
				<div class="form_text" id="errorTd_decorTime"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-3">房屋设施：</label>
				<div class="formControls col-6">
					<div class="skin-minimal">
						<c:forEach items="${house_facilities}" var="code" varStatus="vstatus">
							<div class="radio-box">
								<form:checkbox path="leaseInfo.facilitiesArr" value="${code.code}" id="facilities${vstatus.index}" data-prompt-target="errorTd_facilities"	data-prompt-position="inline" onclick="clickFacilitiesArr()"/><label for="facilities${vstatus.index}">${code.name}</label>
							</div>
						</c:forEach>
						<div class="radio-box"  >
								<input  type="checkbox" onclick="allChecked()" id="all" /><label for="all">全选</label>
						</div>
					</div>
				</div>
				<div class="form_text" id="errorTd_facilities"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-3">配套家电：</label>
				<div class="formControls col-5">
					<div class="skin-minimal">
						<c:forEach items="${accessory_shop}" var="code" varStatus="vstatus">
							<div class="radio-box" name="eleArr">
								<form:checkbox path="leaseInfo.electricArr" value="${code.code}" id="electric${vstatus.index}" data-prompt-target="errorTd_electric"	data-prompt-position="inline" onclick="clickElectricArr()"/><label for="electric${vstatus.index}">${code.name}</label>
							</div>
						</c:forEach>
						<div class="radio-box"  >
								<input  type="checkbox" onclick="eleAllChecked()" id="accessoryAll" /><label for="accessoryAll">全选</label>
						</div>
					</div>
				</div>
				<div class="form_text" id="errorTd_electric"></div>
			</div>
			<div class="row">
				<div class="line"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-3">房源特色：</label>
				<div class="formControls col-5">
					<div class="skin-minimal">
						<c:forEach items="${house_features}" var="code" varStatus="vstatus">
							<div class="radio-box">
								<form:checkbox path="leaseInfo.featureArr" value="${code.code}" id="feature${vstatus.index}" class="validate[maxCheckbox[3]]" data-prompt-target="errorTd_feature"	data-prompt-position="inline" /><label for="feature${vstatus.index}">${code.name}</label>
							</div>
						</c:forEach>
					</div>
				</div>
				<div class="form_text" id="errorTd_feature"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-3">居住要求：</label>
				<div class="formControls col-7">
					<div class="skin-minimal">
						<c:forEach items="${rental_require}" var="code" varStatus="vstatus">
							<div class="radio-box">
								<form:checkbox path="leaseInfo.requirementArr" value="${code.code}" id="requirement${vstatus.index}" data-prompt-target="errorTd_requirement"	data-prompt-position="inline" /><label for="requirement${vstatus.index}">${code.name}</label>
							</div>
						</c:forEach>
					</div>
				</div>
				<div class="form_text" id="errorTd_requirement"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-3">房主承担费用：</label>
				<div class="formControls col-6">
					<div class="skin-minimal">
						<c:forEach items="${lessor_bear}" var="code" varStatus="vstatus">
							<div class="radio-box">
								<form:checkbox path="leaseInfo.ownerCostItemsArr" value="${code.code}" id="ownerCostItems${vstatus.index}" data-prompt-target="errorTd_ownerCostItems" data-prompt-position="inline"/><label for="ownerCostItems${vstatus.index}">${code.name}</label>
							</div>
						</c:forEach>
					</div>
				</div>
				<div class="form_text" id="errorTd_ownerCostItems"></div>
			</div>
			<div class="row cl">
				<label class="form-label col-3">房主承担费用其他：</label>
				<div class="formControls col-3">
				<form:input path="leaseInfo.ownerCost" class="input-text" data-prompt-position="inline" maxlength="30"/>
				</div>
			</div>

			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>房屋图片：</label>
				<div class="formControls col-5">
					<div id="uploader" class="wu-example" name="room" style="width:100%;">
						<div class="queueList" id="queueList">
							<ul class="filelist">
							<c:if test="${!empty sysInfoAtt_house}">
							<c:forEach items="${sysInfoAtt_house}" var="house" varStatus="vstatus">
								<li class="state-complete" onmouseenter="bigImg(this)" id="hpic${vstatus.index}">
									<p class="title"></p>
									<p class="imgWrap">
										<img onclick="doTop('hpic${vstatus.index}')" src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/img/${house.filePath}_middle.jpg" width="110" height="110" alt="房产证件">
									</p>
									<p class="moreInfo">
										<input type="hidden" name="pics" value="${house.filePath}">
									</p>
									<p class="progress">
										<span style="display: none; width: 0px;"></span>
									</p>
									<div class="file-panel" id="delete_panel" style="height: 0px;" onmouseleave="normalImg(this)">
										<span class="cancel" onclick="deleteImg(this)">删除</span>
									</div>
									<c:if test="${house.sort == '12'}">
										<span class="top"></span>
									</c:if>
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
				<div class="col-offset-3 col-6 c-red">注：请上传jpg、png、bmp格式的图片；最少4张，最多12张；最大4M。点击图片可设置首图。</div>
			</div>
			<div class="row cl">
				<label class="form-label col-3">房源标题：</label>
				<div class="formControls col-6">
					<form:input type="text" class="validate[maxSize[30]] input-text" path="leaseInfo.title" id = "titleLength" maxlength="35"/>
					<span class="form-text c-999"><!-- ( 注：0 / 16 ) --></span>
                                    <span id="gptu" style=display:none>你还可以输入<var style="color: #C00">--</var>个字符</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-3">房源评述：</label>
				<div class="formControls col-6">
					<script id="editor" type="text/plain" name='description' style="width: 100%; height: 300px;"></script>
				</div>
			</div>
		</div>
		
		<div id="second_div">
			<c:if test="${roomInfos_size > 0}">
			<legend id="room_info">房间信息</legend>
			<c:forEach items="${roomInfos}" var="room" varStatus="vstatus">
				<div id="room_div">	
				<div class="panel panel-default mt-20" >	
					<div class="panel-header">房间<c:if test="${vstatus.index!=0 }"><a class="label label-danger r" onclick="delRoom(this);"><i class="Hui-iconfont">&#xe6a6;</i></a></c:if></div>	 
					<div class="panel-body">	 
						<div class="row cl">	 
							<label class="form-label col-2"><span class="c-red">*</span>出租房间：</label> 		
							<div class="formControls col-3">	 
								<input type="text" name="roomInfos[${vstatus.index}].room" value="${room.room }" class="validate[required] input-text"/>
							</div> 
						</div> 
						<div class="row cl">	 
							<label class="form-label col-2"><span class="c-red">*</span>房间租金：</label> 
							<div class="formControls col-2">	 
								<input type="text" class="validate[required,funcCall[checkNum]] input-text" value="${room.rent }" name="roomInfos[${vstatus.index}].rent"
								 data-prompt-target="checkinTime_temp${vstatus.index}" data-prompt-position="inline">
							</div> 
							<p class="form_text">元/月</p> 
							
							<div class="formControls col-2">	 
								<span class="select-box">	
									<select class="select" size="1" name="roomInfos[${vstatus.index}].rentDeposit" value="${room.rentDeposit }">
										<c:forEach items="${rent_deposit}" var="code">
											<option value="${code.code}" <c:if test='${code.code==room.rentDeposit}'>selected</c:if>>${code.name}</option> 
										</c:forEach>
									</select>
								</span>
								<div class="form_text" id="errorTd_environment"></div> 
							</div> 
							<div class="formControls col-1">	 
								<span class="select-box">	
									<select class="select" size="1" name="roomInfos[${vstatus.index}].rentPayment" value="${room.rentPayment }">
										<c:forEach items="${rent_payment}" var="code">
											<option value="${code.code}" <c:if test='${code.code==room.rentPayment}'>selected</c:if>>${code.name}</option> 
										</c:forEach>
									</select>
								</span>
								<div class="form_text" id="errorTd_environment"></div> 
							</div> 
				
							<div class="form_text" id="checkinTime_temp${vstatus.index}"></div>
						</div> 
						<div class="row cl">	 
							<label class="form-label col-2">可入住时间：</label>	
							<div class="formControls col-2">	 
								<input type="text" readonly="readonly" maxlength="20" class="validate[funcCall[checkEndTime]]  input-text Wdate" style="width:163px;" 
								name="roomInfos[${vstatus.index}].checkinTime" value="<fmt:formatDate value="${room.checkinTime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
							</div> 
							<div class="form_text" id="checkinTime_temp${vstatus.index}"></div> 
						</div>
						<div class="row cl">	 
							<label class="form-label col-2"><span class="c-red">*</span>出租面积：</label> 
							<div class="formControls col-3">	 
								<input type="text" class="validate[required,funcCall[checkRoomArea]] input-text checkRoomArea" value="${room.area }" name="roomInfos[${vstatus.index}].area" 
									data-prompt-target="room_area${vstatus.index}" data-prompt-position="inline">	
							</div> 
							<p class="form_text">平方米</p>
							<div class="form_text" id="room_area${vstatus.index}"></div>
						</div> 
						
						<div class="row cl">
							<label class="form-label col-2">房源评述：</label>
								<div class="formControls col-6">
									<script id="editor${vstatus.index}" type="text/plain" name="roomInfos[${vstatus.index}].description" style="width: 100%; height: 300px;">
										${room.description }</script>
								</div>
						</div>
						<div class="row cl">
							<label class="form-label col-2"><span class="c-red">*</span>房屋图片：</label>
							<div class="formControls col-6">
								<div id="uploader${vstatus.index}" class="wu-example" style="width:100%;">
									<div class="queueList" name="room" id="queueList${vstatus.index}">
										<ul class="filelist">
										<c:if test="${!empty room.atts}">
										<c:forEach items="${room.atts}" var="att">
											<li class="state-complete" onmouseenter="bigImg(this)">
												<p class="title"></p>
												<p class="imgWrap">
												<img src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/img/${att.filePath}_middle.jpg" 
													width="110" height="110" alt="房屋图片">
												</p>
												<p class="moreInfo">
													<input type="hidden" name="pics${vstatus.index}" value="${att.filePath}">
												</p>
												<p class="progress">
													<span style="display: none; width: 0px;"></span>
												</p>
												<div class="file-panel" id="delete_panel" style="height: 0px;" onmouseleave="normalImg(this)">
													<span class="cancel" onclick="deleteImg(this)">删除</span>
												</div>
											</li>
											
										</c:forEach>
										</c:if>
										</ul>
									</div>
									<div class="statusBar" id="statusBar${vstatus.index}" style="display:none;">
										<div class="progress" id="progress${vstatus.index}">
											<span class="text">0%</span>
											<span class="percentage" id="percentage${vstatus.index}"></span>
										</div><div id="info${vstatus.index}" class="info"></div>
									</div>
									<div class="btns">
										<div id="filePicker${vstatus.index}"></div>
									</div>
								</div>
							</div>
							<div class="col-offset-2 col-6 c-red">注：请上传jpg、png、bmp格式的图片；最多1张；最大4M。</div>
						</div>
						</div> 	
					</div>	
				</div> 
			</c:forEach>
			<div class="row cl">	
				<a onclick="addRoom(this);" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i>添加房间</a>
			</div>
			</c:if>
		</div>
		
		<div class="responsive">
			<div class="row cl btn_group">
				<div class="col-6 col-offset-4">
					<!-- <a class="btn btn-primary radius mr-10" id="saveInfo">保存</a> -->
					<a class="btn btn-default radius mr-10" id="saveInfo2">发布</a>
				</div>
			</div>
		</div>
		
		
	</form>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/icheck/jquery.icheck.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/ueditor/1.4.3/ueditor.config.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/ueditor/1.4.3/ueditor.all.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/ueditor/1.4.3/lang/zh-cn/zh-cn.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/webuploader/0.1.5/webuploader.js"></script>
	<%@ include file="/WEB-INF/views/include/foot.jsp"%>
	<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/webuploader/0.1.5/webuploaderdlfc.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/webuploader/0.1.5/mulImages.js"></script> --%>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/webuploader/0.1.5/mulImgTop.js"></script>
	<script type="text/javascript">
		var file_size = 12 ;
		var room_size = 1;
		var room_index = 0;
		var swfPath ="${pageContext.request.contextPath}/static/hui/lib/webuploader/0.1.5/Uploader.swf";
		
		$(function() {
			$("#second_div").hide();
			initALLCheckedElectricArr();
			initALLChecked();
			initiCheck();
			
			UE.getEditor('editor'); 
			
			$('#addLeaseForm').validationEngine('attach', { binded : false ,showOneMessage: true,maxErrorsPerField : 1,promptPosition: 'centerRight',addPromptClass: 'formError-noArrow formError-text'});
			
			$('#saveInfo').click(function(){
				jQuery('#addLeaseForm').validationEngine('validate');
			});
			
			changeArea();
			
			changeDist();
			
			var dsc = '${leaseInfo.description}';
			$("#editor").html(dsc);
			
			var leaseMode = '${leaseInfo.leaseMode}'
			
			if(leaseMode == 2){
				initRoomInfo();
			}
			//房源标题字数显示
            numberShow();
			
            linkage();
			$("#floorNum").on("keyup input", linkage);
			
			$opts = $("#decor_time").children();
			optDecorTime($("#buildTime")[0]);
			$("#buildTime").on("change", function(){optDecorTime(this);});
			var preferential = "${leaseInfo.preferential}";
			var preferentials = $("input[name=preferential]");
			if (preferential == "03") {
				preferentials.each(function(i, item){
					item.checked = true;
					$(item).parents(".icheckbox-blue").addClass("checked");
				});
			}
		});
		
		var $opts;
		function optDecorTime(Obj) {
			var val= $(Obj).val()
			var $crrOpts = $("#decor_time").children();
			if (val == '3' || val == '4') {
				var html = "";
				$opts.each(function(){
					if ($(this).val() == "1") {
						html += "<option value="+$(this).val()+">"+$(this).text()+"</option>";
					}
				});
				$("#decor_time").html(html);
			} else {
				var html = "";
				var decorTime = '${leaseInfo.decorTime}';
				$opts.each(function(){
					if ($(this).val() != "1") {
						if ('${leaseInfo.decorTime}' == $(this).val()) {
							html += '<option selected="selected" value='+$(this).val()+'>'+$(this).text()+'</option>';
						} else {
							html += '<option value='+$(this).val()+'>'+$(this).text()+'</option>';
						}
					}
				});
				$("#decor_time").html(html);
			}
		}
		
		function initiCheck(){
			$('.skin-minimal input').iCheck({
				checkboxClass : 'icheckbox-blue',
				radioClass : 'iradio-blue',
				increaseArea : '20%'
			});
		};
		
		function initRoomInfo() {
			$("#first_div").hide();
			$("#second_div").show();
			
			var roomInfos_size = '${roomInfos_size}';
			
			for(var i = 0;i<roomInfos_size;i++){
				UE.getEditor('editor'+i);
				
				mulImagesInit('${ctx}',i,room_size,swfPath);
				
				room_index +=1; 
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
		var currentDate = new Date();
		var monthDate = currentDate.getMonth() + 1 + "";
		if(monthDate.length < 2){
			monthDate = "0" + monthDate;
		}
		var checkInDate = currentDate.getFullYear() + "-" + monthDate + "-" + currentDate.getDate();
		
		function changeLeaseType(){
			var agentStatus=$('input:radio[name="leaseMode"]:checked').val();
			if(agentStatus==1){
				//防止重复点合租
				var len = $("div#room_div").length;
				if(len > 0){
					return;
				}
				
				$("#first_div").hide();
				$("#second_div").show();
				
				var html =
				'<div id="room_div">	'+
				'<legend id="room_info">房间信息</legend>		'+
				'<div class="panel panel-default mt-20" >	'+
				'	<div class="panel-header">房间<a class="label label-danger r" onclick="delRoom(this);"><i class="Hui-iconfont">&#xe6a6;</i></a></div>	 '+
				'	<div class="panel-body">	 '+
				'		<div class="row cl">	 '+
				'			<label class="form-label col-2"><span class="c-red">*</span>出租房间：</label> 		'+
				'			<div class="formControls col-3">	 '+
				'				<input type="text" name="roomInfos['+room_index+'].room" class="validate[required] input-text"/>	'+
				'			</div> '+
				'		</div> '+
				'		<div class="row cl">	 '+
				'			<label class="form-label col-2"><span class="c-red">*</span>房间租金：</label> '+
				'			<div class="formControls col-2">	 '+
				'				<input type="text" class="validate[required,funcCall[checkNum]] input-text" name="roomInfos['+room_index+'].rent" data-prompt-target="checkinTime_temp'+room_index+'" data-prompt-position="inline">	 '+
				'			</div> '+
				'			<p class="form_text">元/月</p> '+
				'			<div class="formControls col-1">	 '+
				'				<span class="select-box">	'+
				'					<select class="select" size="1" name="roomInfos['+room_index+'].rentDeposit">	'+
				'						<c:forEach items="${rent_deposit}" var="code" varStatus="vstatus">		'+
				'							<option value="${code.code}">${code.name}</option> '+
				'						</c:forEach>	 '+
				'					</select>	'+
				'				</span>'+
				'				<div class="form_text" id="errorTd_environment"></div> '+
				'			</div> '+
				
				'			<div class="formControls col-1">	 '+
				'				<span class="select-box">	'+
				'					<select class="select" size="1" name="roomInfos['+room_index+'].rentPayment">	'+
				'						<c:forEach items="${rent_payment}" var="code" varStatus="vstatus">		'+
				'							<option value="${code.code}">${code.name}</option> '+
				'						</c:forEach>	 '+
				'					</select>	'+
				'				</span>'+
				'				<div class="form_text" id="errorTd_environment"></div> '+
				'			</div> '+
				'			<div class="form_text" id="checkinTime_temp'+room_index+'"></div> '+
				'		</div> '+
				'		<div class="row cl">	 '+
				'			<label class="form-label col-2">可入住时间：</label>	'+
				'			<div class="formControls col-2">	 '+
				'				<input type="text" readonly="readonly" maxlength="20" class="validate[funcCall[checkEndTime]]  input-text Wdate" style="width:163px;" '+
				'				name="roomInfos['+room_index+'].checkinTime" onclick="WdatePicker({dateFmt:\'yyyy-MM-dd\'});" value=\"' + checkInDate + '\"/>'+
				'			</div> '+
				'			<div class="form_text" id="checkinTime_temp'+room_index+'"></div> '+
				'		</div> '+
				'		<div class="row cl">	 '+
				'			<label class="form-label col-2"><span class="c-red">*</span>出租面积：</label> '+
				'			<div class="formControls col-3">	 '+
				'				<input type="text" class="validate[required,funcCall[checkRoomArea]] input-text checkRoomArea" name="roomInfos['+room_index+'].area" data-prompt-target="room_area'+room_index+'" data-prompt-position="inline">	'+
				'			</div> '+
				'			<p class="form_text">平方米</p>'+
				'			<div class="form_text" id="room_area'+room_index+'"></div> '+
				'		</div> '+
				
				'		<div class="row cl">'+
				'			<label class="form-label col-2">房源评述：</label>'+
				'				<div class="formControls col-6">'+
				'					<script id="editor'+room_index+'" type="text/plain" name="roomInfos['+room_index+'].description" style="width: 100%; height: 300px;"><\/script>'+
				'				</div>'+
				'		</div>'+
				'		<div class="row cl">'+
				'		<label class="form-label col-2"><span class="c-red">*</span>房屋图片：</label>'+
				'			<div class="formControls col-6">'+
				'				<div id="uploader'+room_index+'" class="wu-example" style="width:100%;">'+
				'					<div class="queueList" id="queueList'+room_index+'">'+
				'					</div>'+
				'					<div class="statusBar" id="statusBar'+room_index+'" style="display:none;">'+
				'						<div class="progress" id="progress'+room_index+'">'+
				'							<span class="text">0%</span>'+
				'							<span class="percentage" id="percentage'+room_index+'"></span>'+
				'						</div><div class="info'+room_index+'" class="info"></div>'+
				'					</div>'+
				'					<div class="btns">'+
				'						<div id="filePicker'+room_index+'" style= "float:left;"></div>'+
				'		<div class="col-offset-2 col-6 c-red"></div>'+
				'					</div>'+
				'				</div>'+
				'			</div>'+
				'		<div class="col-offset-2 col-6 c-red">注：请上传jpg、png、bmp格式的图片；最多1张；最大4M。</div>'+
				'		</div>'+
				'		</div> 	'+
				'	</div>	'+
				'	<div class="row cl">	'+
				'		<a onclick="addRoom(this);" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i>添加房间</a>	 '+
				'	</div>'+
				'</div> ';
				
				$("#second_div").append(html);
				
				UE.getEditor('editor'+room_index+'');
				
				mulImagesInit('${ctx}',room_index,room_size,swfPath);
				
				initiCheck();
				
				room_index +=1; 
			}else{
				$("#first_div").show();
				$("div#room_div").remove();
				$("#second_div").hide();
			}
		}
		
		function addRoom(it){
			$(it).parent().remove();
			var html =
				'<div id="room_div">	'+
				'<div class="panel panel-default mt-20" >	'+
				'	<div class="panel-header">房间<a class="label label-danger r" onclick="delRoom(this);"><i class="Hui-iconfont">&#xe6a6;</i></a></div>	 '+
				'	<div class="panel-body">	 '+
				'		<div class="row cl">	 '+
				'			<label class="form-label col-2"><span class="c-red">*</span>出租房间：</label> 		'+
				'			<div class="formControls col-3">	 '+
				'				<input type="text" name="roomInfos['+room_index+'].room" class="validate[required] input-text"/>	'+
				'			</div> '+
				'		</div> '+
				'		<div class="row cl">	 '+
				'			<label class="form-label col-2"><span class="c-red">*</span>房间租金：</label> '+
				'			<div class="formControls col-2">	 '+
				'				<input type="text" class="validate[required,funcCall[checkNum]] input-text" name="roomInfos['+room_index+'].rent" data-prompt-target="checkinTime_temp'+room_index+'" data-prompt-position="inline">	 '+
				'			</div> '+
				'			<p class="form_text">元/月</p> '+
				'			<div class="formControls col-1">	 '+
				'				<span class="select-box">	'+
				'					<select class="select" size="1" name="roomInfos['+room_index+'].rentDeposit">	'+
				'						<c:forEach items="${rent_deposit}" var="code" varStatus="vstatus">		'+
				'							<option value="${code.code}">${code.name}</option> '+
				'						</c:forEach>	 '+
				'					</select>	'+
				'				</span>'+
				'				<div class="form_text" id="errorTd_environment"></div> '+
				'			</div> '+
				
				'			<div class="formControls col-1">	 '+
				'				<span class="select-box">	'+
				'					<select class="select" size="1" name="roomInfos['+room_index+'].rentPayment">	'+
				'						<c:forEach items="${rent_payment}" var="code" varStatus="vstatus">		'+
				'							<option value="${code.code}">${code.name}</option> '+
				'						</c:forEach>	 '+
				'					</select>	'+
				'				</span>'+
				'				<div class="form_text" id="errorTd_environment"></div> '+
				'			</div> '+
				'			<div class="form_text" id="checkinTime_temp'+room_index+'"></div> '+
				'		</div> '+
				'		<div class="row cl">	 '+
				'			<label class="form-label col-2">可入住时间：</label>	'+
				'			<div class="formControls col-2">	 '+
				'				<input type="text" readonly="readonly" maxlength="20" class="validate[funcCall[checkEndTime]]  input-text Wdate" style="width:163px;" '+
				'				name="roomInfos['+room_index+'].checkinTime" onclick="WdatePicker({dateFmt:\'yyyy-MM-dd\'});" value=\"' + checkInDate + '\"/>'+
				'			</div> '+
				'			<div class="form_text" id="checkinTime_temp'+room_index+'"></div> '+
				'		</div> '+
				'		<div class="row cl">	 '+
				'			<label class="form-label col-2"><span class="c-red">*</span>出租面积：</label> '+
				'			<div class="formControls col-3">	 '+
				'				<input type="text" class="validate[required,funcCall[checkRoomArea]] input-text checkRoomArea" name="roomInfos['+room_index+'].area" data-prompt-target="room_area'+room_index+'" data-prompt-position="inline">	'+
				'			</div> '+
				'			<p class="form_text">平方米</p>'+
				'			<div class="form_text" id="room_area'+room_index+'"></div> '+
				'		</div> '+
				
				'		<div class="row cl">'+
				'			<label class="form-label col-2">房源评述：</label>'+
				'				<div class="formControls col-6">'+
				'					<script id="editor'+room_index+'" type="text/plain" name="roomInfos['+room_index+'].description" style="width: 100%; height: 300px;"><\/script>'+
				'				</div>'+
				'		</div>'+
				'		<div class="row cl">'+
				'		<label class="form-label col-2"><span class="c-red">*</span>房屋图片：</label>'+
				'			<div class="formControls col-6">'+
				'				<div id="uploader'+room_index+'" name="room" class="wu-example" style="width:100%;">'+
				'					<div class="queueList" id="queueList'+room_index+'">'+
				'					</div>'+
				'					<div class="statusBar" id="statusBar'+room_index+'" style="display:none;">'+
				'						<div class="progress" id="progress'+room_index+'">'+
				'							<span class="text">0%</span>'+
				'							<span class="percentage" id="percentage'+room_index+'"></span>'+
				'						</div><div class="info'+room_index+'" class="info"></div>'+
				'					</div>'+
				'					<div class="btns">'+
				'						<div id="filePicker'+room_index+'" style= "float:left;"></div>'+
				'		<div class="col-offset-2 col-6 c-red"></div>'+
				'					</div>'+
				'				</div>'+
				'			</div>'+
				'		<div class="col-offset-2 col-6 c-red">注：请上传jpg、png、bmp格式的图片；最多1张；最大4M。</div>'+
				'		</div>'+
				'		</div> 	'+
				'	</div>	'+
				'	<div class="row cl">	'+
				'		<a onclick="addRoom(this);" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i>添加房间</a>	 '+
				'	</div>'+
				'</div> ';
				
				$("#second_div").append(html);
				
				UE.getEditor('editor'+room_index+'');
				
				mulImagesInit('${ctx}',room_index,room_size,swfPath);
				
				initiCheck();
				
				room_index +=1; 
		};
		
		function changeArea(){
			var area = $("#districtArea").val();
			var dist = $("#dist").val();

			if(area == ''){
				$("#districtDist").html('');
				$("#districtDist").append("<option value=''> 选择商圈 </option>");
				return ;
			}

			$.ajax({
				type: "GET",
				url: '${ctx}/lease/getTrade',
				data: {
					area: area,
					dist: dist
				},
				dataType:'json',
				cache: false,
				async : false, 
				success: function(data){
					var msg = data.message;

					var $objSelect = $("#districtDist");
					$objSelect.html('');
					$objSelect.append("<option value=''> 选择商圈 </option>" + msg);
				}
			});
			
			changeDist();
		}
		
		//根据商圈改变地铁
		function changeDist(){
			var area = $("#districtDist").val();
			var station = '${leaseInfo.stationid}';
			$.ajax({
				type: "GET",
				url: '${ctx}/lease/sta',
				data: {
					trade_id: area,
					station: station
				},
				dataType:'json',
				cache: false,
				async : false, 
				success: function(data){
					var msg = data.message;

					var $objSelect = $("#stationid");
					$objSelect.html('');
					$objSelect.append(msg);
				}
			});
		}
		
		/*$('#saveInfo').on('click',function() {
			var success = $("#addLeaseForm").validationEngine("validate");
			
			if(!success){
				return;
			}
			
			var pic = $("input[name=pics]").length;
			if(pic == 0){
				layer.msg("请上传房源图片！");
				return;
			}
			
			$("#checkinTime").val($("#checkinTime_temp").val());
			var form = document.forms['addLeaseForm'];
			form.action="${ctx}/lease/edit";
			form.submit();
		});*/
		
		$('#saveInfo2').on('click',function() {
			var success = $("#addLeaseForm").validationEngine("validate");
			
			if(!success){
				return;
			}
			var roomFlg = false;
			$("div[name=room]").each(function(i){
				var pics = $(this).find("input[type=hidden]").length;
				if (pics == 0) {
					roomFlg = true;
					layer.msg("请上传房间图片！");
					return false;
				} else if (i == 0 && pics < 4) {
					roomFlg = true;
					layer.msg("请上传房房屋图片，最少4张最多12张！");
					return false;
				}
			});
			if (roomFlg) {
				return;
			}
			getLocal();
			$("#releaseStatus").val(1);
			$("#checkinTime").val($("#checkinTime_temp").val());
			var form = document.forms['addLeaseForm'];
			form.action="${ctx}/lease/edit";
			form.submit();
		});
		
		function checkRoomArea(field){
			var area = 0;
			$(".checkRoomArea").each(function(){
				area = area + Math.round(this.value);
			});
			if (isNaN(area)) {
				return "必须是纯数字";
			}
			
			var buildingArea = $("#buildingArea").val();
			if(area > buildingArea){
				return "房间面积总和不能超过建筑面积";
			}
			
		};
		
		function checkNum(field){
			if (isNaN(jQuery.trim(field.val()))) {
				return "必须是纯数字";
			}
		};
		
		function buildNum(field){
			var rex = /^[0-9a-zA-Z]+$/g
			var value = jQuery.trim(field.val());
			if (value && !rex.test(value)) {
				return "输入标准楼号，数字或者字母";
			}
		};
		
		function checkRoom(field){
			if (isNaN(jQuery.trim(field.val()))) {
				return "必须是纯数字";
			}
			
			if(field.val() < 1){
				return "必须是大于0";
			}
		};
		
		
		mulImagesInit('${ctx}','',file_size,swfPath);
		
		//检测入住时间
		function checkEndTime(field){
			var endTime = '${endTime}';//合同到期时间
			//endTime = "2017-12-09 00:00:00";
			
			var checkInTime = field.val();
			
			if(endTime!=null && endTime!=''&&checkInTime!=null&&checkInTime!='' ){
				
				beginTime = checkInTime.substring(0, 10);
				
				var beginTimes = beginTime.substring(0, 10).split('-');
				var endTimes = endTime.substring(0, 10).split('-');
				
				beginTime =  beginTimes[0] + beginTimes[1] + beginTimes[2] ;
				endTime = endTimes[0] + endTimes[1]  + endTimes[2];
				
				var a = endTime - beginTime;
				if (a < 0) {
					//endTime小!");
					
				} else if (a > 0) {
					//endTime大!");
					//return false;
					return "可入住时间不能为合同期限内。";
				} else if (a == 0) {
					//时间相等!");
					//return "可入住时间不能为合同期限内。";
				} 
			}
		};
		
		//删除房间
		function delRoom(it){
			$(it).parent().parent().remove();
		};
		
		//检测楼层数
		function checkFloor(field){
			
			var floorNo = $("#floorNo").val();
			var floorNum = field.val();
			
			if(parseInt(floorNo) > parseInt(floorNum)){
				return "总层数不能小于楼层数";
			}
		};
		
		function checkFile(obj){
			var $it = obj.parent().parent().parent().find(".queueList").find(".filelist").find(".filelist").find(".moreInfo").children()[0];
			
			if(typeof($it)=="undefined"){
				return "房屋图片必填";
			}
			var filename = $it.value;
			
			if(filename ==null||filename ==''){
				return "房屋图片必填";
			}
		}
		function checkLength(titleLength){
            var len = $("#titleLength").val().length;
            if (len > 30||len<1) {
                return "请输入1-30字";
            }
            return true;
        };
        $("#titleLength").on("keyup",numberShow);
         //房源标题字数显示
        function numberShow(){
            var text = $("#titleLength").val();
            var counter = text.length;
            if(counter>30){
                $("#gptu").css("color","red"); 
                $("#gptu").text("字数超出限制");
            } else if (counter<25) {
                $("#gptu").attr("style","block");
                $("#gptu").html('最多可以输入30个字');
            } else {
                $("#gptu").attr("style","block");
                var text = $(this).val();
                var counter = text.length;
                $("#gptu").css("color","");
                //每次减去字符长度
                $("#gptu").html('你还可以输入<var style="color: #C00">'+(30 - counter)+'</var>个字符');
            }
         }

        // 楼层联动
		function linkage(){
			var floorNum = $("#floorNum").val();
			if (floorNum) {
				if (!parseInt(floorNum)) {
					layer.msg("请输入正确的数字！");
					return;
				}
				floorNum = parseInt(floorNum);
				$("#floorNo").empty();
				var floorNo = ${leaseInfo.floorNo};
				for (var i = floorNum; i > 0; i--) {
					if (floorNo == i) {
						$("#floorNo").append('<option value="'+ i +'" selected="selected">'+ i +'层</option>');
					} else {
						$("#floorNo").append("<option value="+ i +">"+ i +"层</option>");
					}
				}
				$("#floorNo").append('<option value="-1">地下1层</option>'+
						'<option value="-2">地下2层</option><option value="-3">地下3层</option>');
			} else {
				$("#floorNo").empty();
				$("#floorNo").append('<option value="">请填写总层数</option>');
			}
		}
		function allChecked(){
			 var checkAll = $("#all").prop('checked');
			 if(checkAll){
				 $("input[name='facilitiesArr']").iCheck('check');
			 }else{
				 $("input[name='facilitiesArr']").iCheck("uncheck");
			 }
		};
		 function initALLChecked(){
			var initCheckbox = '${leaseInfo.facilities}';
			if(initCheckbox.length == 13){
				 $("#all").iCheck('check');
			}else{
				$("#all").iCheck("uncheck");
			}
		} 
		  function clickFacilitiesArr(){
			var checkAll =  $("#all").prop('checked');
			var facilitiesArr= $("input[name='facilitiesArr']:checked");
			if(checkAll){
				$("#all").iCheck("uncheck");
			}else if(facilitiesArr.length == 7){
				$("#all").iCheck("check");
			}
		}  
		  function eleAllChecked(){
				var checkAll = $("#accessoryAll").prop('checked');
				 if(checkAll){
					 $("input[name='electricArr']").iCheck('check');
				 }else{
					 $("input[name='electricArr']").iCheck("uncheck");
				 }
			}; 
			function clickElectricArr(){
				var checkAll =  $("#accessoryAll").prop('checked');
				var facilitiesArr= $("input[name='electricArr']:checked");
				if(checkAll){
					$("#accessoryAll").iCheck("uncheck");
				}else if(facilitiesArr.length == 5){
					$("#accessoryAll").iCheck("check");
				}
			}
			function initALLCheckedElectricArr(){
				var initCheckbox = '${leaseInfo.electric}';
				if(initCheckbox.length == 9){
					 $("#accessoryAll").iCheck('check');
				}else{
					$("#accessoryAll").iCheck("uncheck");
				}
			}
			/*
			 * 获取房源详情经纬度
			 */
			function getLocal(){
				var addr = $("#houseAddr").val();
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
						if (data && data.geocodes && data.geocodes.length != 0) {
							var local = data.geocodes[0].location.split(",");
							$("#longitude").val(local[0]);
							$("#latitude").val(local[1]);
						}
					}
				});
			}
	</script>
</body>
</html>