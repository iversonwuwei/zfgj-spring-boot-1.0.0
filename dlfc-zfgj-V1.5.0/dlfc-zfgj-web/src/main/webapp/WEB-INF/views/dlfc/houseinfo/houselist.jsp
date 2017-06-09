<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<c:set var="ctxFront" value="${fns:getSiteUrl()}${fns:getFrontPath()}"/>
<!DOCTYPE HTML>
<html>
<head>

<%@ include file="/WEB-INF/views/include/head2.jsp"%>
<link href="${pageContext.request.contextPath}/static/hui/lib/bootstrap-Switch/bootstrapSwitch.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript">
function page(n,s){
	$("#pageNo").val(n);
	$("#pageSize").val(s);
	doSearch();
	return false;
}

</script>
<title>添加管理员</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		房源管理 <span class="c-gray en">&gt;</span> 房源列表<a
			class="btn btn-success radius r mr-20"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="content">
		<div class="mt-20">
			<legend>房源列表</legend>
			
		<!-- 搜索房源编号 -->
		<form id="myHouseNO" action="${ctx}/house/list" method="post">
			<div class="r row cl col-3">
				<div class="formControls col-9 ">
					<input type="text" class="input-text" placeholder="房源编号" name="searchInfo" value="${searchInfo}">
				</div>
				<div class="formControls col-3">
					<a class="btn btn-primary" onclick="getHouseNo();">搜索</a>
				</div>
			</div>
		</form>	
			
			<div class="list_tabs cl">
				<a href="" class="active">个人房源</a>
				<c:if test="${enname == 'manager'}">
					<a href="${ctx}/house/list?ms=1" >部门房源</a>
				</c:if> 
			</div>
			
			<div class="list_head cl">
				<!-- <div class="r row cl col-3">
					<div class="formControls col-9 ">
						<input type="text" class="input-text" placeholder="房源地址/编号/状态/维护人">
					</div>
					<div class="formControls col-3">
						<a href="" class="btn btn-primary">搜索</a>
					</div>
				</div> -->
				<div class="house-list-tab cl">
					<a id="0" href="${ctx}/house/list?st="  ><span <c:if test="${st == null || st == '' }">class="current"</c:if>>全部房源</span></a>
					<a id="1" href="${ctx}/house/list?st=1" ><span <c:if test="${st == 1}">class="current"</c:if>>收藏房源</span></a>
				</div>
			</div>
			<!-- <div class=" mb-20">
				<div class="house-list-tab cl">
					<span class="current">全部房源</span><span>关注房源</span>
				</div>
			</div> -->
			<div class="panel panel-default mb-20">
				<div class="panel-body">
					<div class="filter_item">
						<span>签约状态：</span>
						<a onclick="screenSignStatus();" <c:if test="${sign_status == null||sign_status == ''}">class="active"</c:if>>全部</a>
						<a onclick="screenSignStatus(3);" <c:if test="${sign_status == 3}">class="active"</c:if>>未签</a>
						<a onclick="screenSignStatus(2);" <c:if test="${sign_status == 2}">class="active"</c:if>>可签</a>
						<a onclick="screenSignStatus(1);" <c:if test="${sign_status == 1}">class="active"</c:if>>签满</a>
					</div>
					<div class="filter_item">
						<span>产权状态：</span>
						<a onclick="screenPropertyId();" <c:if test="${property_id == null||property_id == ''}">class="active"</c:if>>全部</a>
						<c:forEach items="${property_list}" var="code">
							<a onclick="screenPropertyId(${code.code});" <c:if test="${property_id == code.code}">class="active"</c:if>>${code.name}</a>
						</c:forEach>
					</div>
					<div class="filter_item">
						<span>上架状态：</span>
						<a onclick="screenReleaseStatus();" <c:if test="${release_status == null||release_status == ''}">class="active"</c:if>>全部</a>
						<a onclick="screenReleaseStatus(1);" <c:if test="${release_status == 1}">class="active"</c:if>>已上架</a>
						<a onclick="screenReleaseStatus('0');" <c:if test="${release_status == '0'}">class="active"</c:if>>未上架</a>
					</div>
				</div>
			</div>
			<div class="list_sort cl">
				<c:if test="${bs == null || bs ==  '' || bs == 'none'}">
					<a id="allsort" href="javascript:void(0);" title="无排序" onclick="doSort('butimesort', 'desc')">添加时间 <span> &uarr;</span></a>
				</c:if>
				<c:if test="${bs == 'desc'}">
					<a id="upsort" href="javascript:void(0);" title="降序" onclick="doSort('butimesort', 'asc')" class="active">添加时间 <span> &darr;</span></a>
				</c:if>
				<c:if test="${bs == 'asc'}">
					<a id="downsort" href="javascript:void(0);" title="升序" onclick="doSort('butimesort', 'none')" class="active">添加时间<span> &uarr;</span></a>
				</c:if>
				
				<%-- <c:if test="${ss == null || ss ==  '' || ss == 'none'}">
					<a id="allsort" href="javascript:void(0);" title="无排序" onclick="doSort('signtimesort', 'desc')">签约状态 <span> &uarr;</span></a>
				</c:if>
				<c:if test="${ss == 'desc'}">
					<a id="upsort" href="javascript:void(0);" title="降序" onclick="doSort('signtimesort', 'asc')" class="active">签约状态 <span> &darr;</span></a>
				</c:if>
				<c:if test="${ss == 'asc'}">
					<a id="downsort" href="javascript:void(0);" title="升序" onclick="doSort('signtimesort', 'none')" class="active">签约状态<span> &uarr;</span></a>
				</c:if>
				
				<c:if test="${es == null || es ==  '' || es == 'none'}">
					<a id="allsort" href="javascript:void(0);" title="无排序" onclick="doSort('endtimesort', 'desc')">到期时间<span> &uarr;</span></a>
				</c:if>
				<c:if test="${es == 'desc'}">
					<a id="upsort" href="javascript:void(0);" title="降序" onclick="doSort('endtimesort', 'asc')" class="active">到期时间 <span> &darr;</span></a>
				</c:if>
				<c:if test="${es == 'asc'}">
					<a id="downsort" href="javascript:void(0);" title="升序" onclick="doSort('endtimesort', 'none')" class="active">到期时间<span> &uarr;</span></a>
				</c:if> --%>
			</div>
			
			<form name="houseinfolist" action="${ctx}/house/list">
				<input id= "st" type="hidden" name="st" value="${st}">
				<input id="butimesort" type="hidden" name="bs" value="${bs}">
				<input id="endtimesort" type="hidden" name="es" value="${es}">
				<input id="signtimesort" type="hidden" name="ss" value="${ss}">
				<input id="pageNo" type="hidden" name="pageNo" value="${page.pageNo}">
				<input id="pageSize" type="hidden" name="pageSize" value="${page.pageSize}">
				<input type="hidden" name="currentPage" value="">
				
				<input id="propertyId" type="hidden" name="propertyId" value="${property_id }">
				<input id="releaseStatus" type="hidden" name="releaseStatus" value="${release_status }">
				<input id="signStatus" type="hidden" name="signStatus" value="${sign_status }">
			</form>
			<form name="editForm" action="${ctx}/house/edit"  method="post">
				<input id="collectType" type="hidden" name="collectType" value="">
				<input id="e_hid" type="hidden" name="hid" value="">
				<input type="hidden" name="st" value="${st}">
				<input id="repage" type="hidden" name="repage" value="1">
			</form>
			
			<div class="cl pd-5 bg-1 bk-gray mt-20">
				<span class="l">
				<shiro:hasPermission name="emp:house:del">
					<c:if test="${lockFlg=='false'}">
						<a onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i>删除</a>
					</c:if>
				</shiro:hasPermission>
				</span>
			</div>
			<table class="table table-border table-bordered  table-bg">
			<thead>
					<tr class="text-c">
						<th width="55"><input type="checkbox" name="" value="" id="allSelect"></th>
						<th colspan="4">房源详情</th>
						<th width="70">创建人</th>
						<th width="260">操作</th>
					</tr>
				</thead>
			</table>
			
			<c:forEach items="${page.list}" var="houseInfo">
			<table class="table table-border table-bordered table-bg mb-20">
				<tbody>
					 <tr class="text-l">
						<td colspan="8" class="hoser_info">
							<div class="row cl">
								<div class="col-3"><fmt:formatDate value="${houseInfo.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
								<div class="col-6">房源编号：${houseInfo.no}</div>
							</div>
						</td>
					</tr> 
					<tr class="text-c">
						<td width="55"><input type="checkbox" value="${houseInfo.id}" name="checkboxhid"> 
							<c:choose>
								<c:when test="${houseInfo.collectType == null || houseInfo.collectType == '' || houseInfo.collectType == 0 }">
									<a class="Hui-iconfont collect" onclick="collection('${houseInfo.id}','${houseInfo.collectType}');">&#xe69e;</a>
								</c:when>
								<c:otherwise>
									<a class="Hui-iconfont collected" onclick="collection('${houseInfo.id}','${houseInfo.collectType}');">&#xe69d;</a>
								</c:otherwise>
							</c:choose>
						</td>
						<td width="450"><p class="house_name">
								<%-- <c:if test="${not empty houseInfo.leaseInfo }">
								</c:if>
								<c:if test="${empty houseInfo.leaseInfo }">
									<a  onclick="seeHouse('${houseInfo.id}');">
								</c:if> 
								    租房管家ZFGJMG-513 【房源列表】未发布出租信息的房源地址也应链接到出租信息详情页
								--%>
								<c:if test="${not empty houseInfo.lid }">
									<a href="${fns:getSiteUrl()}/zhaofang/${houseInfo.no}.html" target="_blank">
								${houseInfo.houseAddr}</a>
								</c:if>
								<c:if test="${empty houseInfo.lid}">
								${houseInfo.houseAddr}
								</c:if>
								
								<c:if test="${houseInfo.propertyIdType==1}">
									<span class="label label-primary">${houseInfo.propertyIdTypeName}</span>
								</c:if>
								<c:if test="${houseInfo.propertyIdType==2}">
									<span class="label label-success">${houseInfo.propertyIdTypeName}</span>
								</c:if>
								<c:if test="${houseInfo.propertyIdType==3}">
									<span class="label label-success">${houseInfo.propertyIdTypeName}</span>
								</c:if>
								<c:if test="${houseInfo.propertyIdType==4}">
									<span class="label label-success">${houseInfo.propertyIdTypeName}</span>
								</c:if>
									
								<!-- 锁定和无效 -->
								<%-- <c:if test="${houseInfo.activeStatus==2}">
									<span class="label mr-5 label-default-bg">无效</span> 
								</c:if>
								<c:if test="${houseInfo.activeStatus==1&&houseInfo.lockStatus==1}">
									<span class="label mr-5 label-sedefault">锁定</span>
								</c:if> --%>
									
								<%-- <c:if test="${houseInfo.certFlag==0}">
									<span class="label">未认证</span>
								</c:if> 
								<c:if test="${houseInfo.certFlag==1}">
									<span class="label label-purple">认证房源</span>
								</c:if>
								<c:if test="${houseInfo.certFlag==2}">
									<span class="label label-fail">认证失败</span>
								</c:if> 
									
								<c:if test="${houseInfo.auditStatus ==1}">
									<span class="c-red ml-10 f-12">审核中</span>
								</c:if>
								 <c:if test="${houseInfo.auditStatus == 2}">
									<span class="label mr-5 label-success">审核通过</span>
								</c:if> 
								<c:if test="${houseInfo.auditStatus == 3}">
									<span class="label mr-5 label-fail">审核失败</span>
								</c:if>
								
								房源状态				显示结果
								认证中	——		--》	审核中
								认证通过	审核中	--》	审核中
								认证失败	——		--》	审核失败
								认证通过	审核失败	--》	审核失败
								认证通过	审核通过	--》	认证房源
								--%>
								
								<%-- <c:choose>
									<c:when test="${empty houseInfo.certFlag||houseInfo.certFlag==0||((empty houseInfo.auditStatus||houseInfo.auditStatus == 1)&&houseInfo.certFlag==1)}">
										<span class="c-red ml-10 f-12">审核中</span>
									</c:when>
									<c:when test="${houseInfo.certFlag==2||(houseInfo.certFlag==1&&houseInfo.auditStatus == 3)}">
										<span class="label mr-5 label-fail">审核失败</span>
									</c:when>
									<c:when test="${houseInfo.certFlag==1&&houseInfo.auditStatus == 2}">
										<span class="label label-purple">认证房源</span>
									</c:when>
								</c:choose> --%>
								
								<%-- <c:choose>
									<c:when test="${empty houseInfo.auditStatus||houseInfo.auditStatus == 1}">
										<span class="c-red ml-10 f-12">审核中</span>
									</c:when>
									<c:when test="${houseInfo.auditStatus == 3}">
										<span class="label mr-5 label-fail">审核失败</span>
									</c:when>
									<c:when test="${houseInfo.certStatus==1 && houseInfo.auditStatus == 2}">
										<span class="label label-purple">认证房源</span>
									</c:when>
								</c:choose> --%>
								
								
								<!-- 验真房源 -->
								<c:if test="${!empty houseInfo.certStatus }">
									<c:if test="${houseInfo.certStatus == 1 }">
										<span class="label mr-5 label-sedefault">验真成功</span>
									</c:if>
									<c:if test="${houseInfo.certStatus == 0 }">
										<span class="label mr-5 label-warning">验真中</span>
									</c:if>
									<c:if test="${houseInfo.certStatus == 2 }">
										<span class="label mr-5 label-danger">验真失败</span>
									</c:if>
								</c:if>
								
								<!-- 显示房源状态标签 无效>锁定>审核>认证-->
								<c:choose>
									<c:when test="${houseInfo.baseActiveStatus==2}">
										<span class="label mr-5 label-default-bg">无效</span> 
									</c:when>
									<c:when test="${houseInfo.baseActiveStatus==1&&houseInfo.lockStatus==1}">
										<span class="label mr-5 label-sedefault">锁定</span>
									</c:when>
									<c:when test="${(houseInfo.baseActiveStatus==1 || empty houseInfo.baseActiveStatus)&&houseInfo.lockStatus==0}">
										<c:if test="${empty houseInfo.auditStatus||houseInfo.auditStatus == 1}">
											<span class="label mr-5 label-warning">审核中</span>
										</c:if>
										<c:if test="${houseInfo.auditStatus == 3}">
											<span class="label mr-5 label-danger">审核失败</span>
										</c:if>
										<%-- <c:if test="${houseInfo.certFlag == 1 && houseInfo.auditStatus == 2}">
											<span class="label label-purple">认证房源</span>
										</c:if> --%>
									</c:when>
								</c:choose>
								<!-- 显示房源状态标签 -->
							</p>
							<c:if test="${houseInfo.certStatus == 2 }">
								<p class="text-l c-red">失败原因：
									<c:choose>
									<c:when test="${houseInfo.activeType == 2}">${houseInfo.activeReason}</c:when>
									<c:otherwise>${houseInfo.activeDes}</c:otherwise>
									</c:choose>
								</p>
							</c:if>
						</td>

						<td><p>
							<c:if test="${houseInfo.leaseInfo!=null}">
								${houseInfo.leaseInfo.aparmentRoom } 室 ${houseInfo.leaseInfo.aparmentHall }  厅
							</c:if>
							</p>
							<p><fmt:formatNumber value="${houseInfo.buildingArea}" pattern="#.00"/>平方米</p></td>
						<td width="110"><p>${houseInfo.houseOwnerName}</p>
							<p>${houseInfo.houseOwnerTel}</p></td>
						<td width="80">
							<%-- <c:if test="${houseInfo.auditStatus == 2&&houseInfo.certFlag==1}"> --%>
							<c:if test="${houseInfo.auditStatus == 2}">
								<c:if test="${houseInfo.contractStatus==1}">
									<span class="label label-danger">签满</span>
								</c:if>
								<c:if test="${houseInfo.contractStatus==2}">
									<span class="label label-arrow">可签</span>
								</c:if>
								<c:if test="${houseInfo.contractStatus==3}">
									<span class="label label-success">未签</span>
								</c:if>
								<%-- <c:if test="${houseInfo.contractStatus==0}">
									<span class="label label-refuse">锁定</span>
								</c:if> --%>
							</c:if>
						</td>
						 <td width="70"><p>${houseInfo.ename}</p>
							<%-- <p>${houseInfo.emobile}</p> --%></td>
						<td width="260" class="text-c">
							<shiro:hasPermission name="emp:house:edit">
							<c:if test="${houseInfo.leaseInfo==null}">
								<c:choose>
									<%-- 房源状态不等于无效并且没被锁定 --%>
									<c:when test="${ houseInfo.activeStatus != 2 && houseInfo.lockStatus != 1 &&lockFlg=='false'}">
										<a href="${ctx}/lease/toadd?id=${houseInfo.id}" class="btn btn-primary radius mr-5">一键发布</a>
									</c:when>
									<c:otherwise>
										<a class="btn btn-default radius">一键发布</a>
									</c:otherwise>
								</c:choose>
							</c:if>
							</shiro:hasPermission>
							<shiro:hasPermission name="emp:house:edit">
								<c:choose>
									<c:when test="${ houseInfo.auditStatus==3 && houseInfo.lockStatus!=1 && (houseInfo.baseActiveStatus==1 || empty houseInfo.baseActiveStatus) &&lockFlg=='false'}">
										<a class="btn btn-primary radius"  onclick="editHouse('${houseInfo.id}');">编辑</a> 
									</c:when>
									<c:otherwise>
										<a  class="btn btn-default radius disabled" >编辑</a>
									</c:otherwise>
								</c:choose>
							</shiro:hasPermission>
							<shiro:hasPermission name="emp:house:del">
								<c:choose>
									<c:when test="${  empty houseInfo.auditStatus || houseInfo.auditStatus==1 || houseInfo.lockStatus==1||lockFlg!='false'}">
										<a  class="btn btn-default radius disabled" >删除</a>
									</c:when>
									<c:otherwise>
										<a  class="btn btn-danger radius" onclick="doDelete('${houseInfo.id}');">删除</a>
									</c:otherwise>
								</c:choose>
							</shiro:hasPermission>
							
						</td>
					</tr>
					<c:if test="${houseInfo.leaseInfo!=null}">
					<tr>
						<td colspan="8" class="tab_fold"><ul id="Huifold1"
								class="Huifold">
								<li class="item cl">
									<h4>
										<b class="Hui-iconfont f-16">&#xe600;</b>查看出租信息
									</h4>
									<div class="info ">
										<div class="cl house-manger-info">
											<table>
												<tr name="tr_tmp">
													<td width="120"><p class="house-manger-img">
															<img src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/img/${houseInfo.leaseInfo.sysInfoAtt[0].filePath}_middle.jpg" width="110" height="113"
																class="thumbnail">
														</p></td>
													<td width="600"><div class="house-sell-type">
															<table>
																<c:forEach items="${houseInfo.leaseInfo.houLeaseRoomInfo }" var="room">
																<tr>
																	<td>${room.room }</td>
																	<td width="140" class="text-r">
																		
																		<div name="room_switch" class="r switch size-S" data-on="success" id="${room.id}"
																			data-on-label="可租" data-off-label="已租"
																			data-off="danger">
																			<input  type="checkbox" <c:if test='${room.status==0|| houseInfo.lockStatus==1}'>checked</c:if> <c:if test='${houseInfo.releaseStatus==2|| houseInfo.auditStatus !=2|| houseInfo.leaseInfo.auditStatus ==2||houseInfo.lockStatus==1||houseInfo.baseActiveStatus==2||lockFlg!=false}'>disabled</c:if> value="${room.id}"/>
																		</div></td>
																	<td width="180"><span>&yen;<em
																			class="house-sell-price">${room.rent }</em>/月
																	</span></td>
																</tr>
																</c:forEach>
															</table>
														</div></td>
													<td width="240">
														<!-- 基础信息审核中，审核失败状态不能拨动此按钮,出租信息强制下架也不能拨动,出租信息审核失败也不能 -->
														<c:choose>
															<c:when test="${ empty houseInfo.auditStatus || houseInfo.auditStatus !=2|| houseInfo.leaseInfo.auditStatus ==2||houseInfo.leaseInfo.releaseStatus ==2||houseInfo.lockStatus==1||houseInfo.baseActiveStatus==2||lockFlg!='false'}">
																<div name="lease_switch" class="l switch"
																	data-on="success" data-on-label="已上架"
																	data-off-label="已下架" data-off="danger">
																	<input type="checkbox" disabled  value="${houseInfo.lid}" />
																</div>
															</c:when>
															<c:otherwise>
																<div name="lease_switch" class="l switch"
																	data-on="success" data-on-label="已上架"
																	data-off-label="已下架" data-off="danger">
																	<input type="checkbox" name="lease_switch_box" <c:if test='${houseInfo.releaseStatus==1 }'>checked</c:if> <c:if test='${houseInfo.releaseStatus==2||(houseInfo.leaseInfo.roomCount ==0 && houseInfo.leaseMode == 2 )|| houseInfo.lockStatus==1|| houseInfo.baseActiveStatus==2}'>disabled</c:if>  value="${houseInfo.lid}-${houseInfo.leaseMode}" />
																</div>
																
																<div name="lease_switch2" class="l switch" style="display: none"
																	data-on="success" data-on-label="已上架"
																	data-off-label="已下架" data-off="danger">
																	<input type="checkbox" disabled  value="${houseInfo.lid}" />
																</div>
															</c:otherwise>
														</c:choose>
													</td>
													<td>
														<shiro:hasPermission name="emp:house:edit">
														<c:choose>
															<c:when test="${houseInfo.baseActiveStatus==1&&houseInfo.lockStatus!=1&&lockFlg=='false'}">
																<a href="${ctx}/lease/toedit?id=${houseInfo.id}" class="btn btn-primary radius">编辑</a>
															</c:when>
															<c:otherwise>
																<a class="btn btn-default disabled">编辑</a>
															</c:otherwise>
														</c:choose>
														</shiro:hasPermission>
														<shiro:hasPermission name="emp:house:edit">
														<c:choose>
															<c:when test="${houseInfo.freshTime > 0 && houseInfo.auditStatus == 2 && houseInfo.lockStatus!=1&& houseInfo.baseActiveStatus==1&&lockFlg=='false'}">
																<a onclick="refreshLease(this,'${houseInfo.lid}');" class="btn btn-primary">刷新</a>
																<a class="btn btn-default disabled" style="display: none">刷新</a>
															</c:when>
															<c:otherwise>
																<a class="btn btn-default disabled">刷新</a>
															</c:otherwise>
														</c:choose>
														</shiro:hasPermission>
														
														<%-- 申请验真(审核中,锁定状态,无效不可验真  出租信息已发布的状态) --%>
														<c:choose>
															<c:when test="${houseInfo.auditStatus == 2 && houseInfo.lockStatus == 0 && houseInfo.activeStatus == 1 && empty houseInfo.certStatus && houseInfo.leaseAuditStatus == 1}">
																<a class="btn btn-primary radius" onclick="truthHouse(this,'${houseInfo.id}');">申请验真</a>
															</c:when>
														<c:otherwise>
															<a class="btn btn-default radius disabled">申请验真</a>
														</c:otherwise>
														</c:choose>
													</td>
												</tr>
											</table>
										</div>
									</div>
								</li>
							</ul></td>
					</tr>
					</c:if>
					
				</tbody>
			</table>
					</c:forEach>
			<div class="pagination">${page}</div>
		</div>
	</div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/laypage/1.2/laypage.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/bootstrap-Switch/bootstrapSwitch.js"></script>
	<%@ include file="/WEB-INF/views/include/foot.jsp"%>
	<script type="text/javascript">
		var _didx;
		var scope = '${scope}';
		
		<c:if test="${sucessMsg != null}">
			layer.msg('${sucessMsg}');
		</c:if>
		
		$(function() {		
			$('.table-sort').dataTable({
				"aaSorting" : [ [ 1, "desc" ] ],//默认第几个排序
				"bStateSave" : true,//状态保存
				"aoColumnDefs" : [
				//{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
				{
					"orderable" : false,
					"aTargets" : [ 0, 8, 9 ]
				} // 制定列不参与排序
				]
			});
			$('.table-sort tbody').on('click', 'tr', function() {
				if ($(this).hasClass('selected')) {
					$(this).removeClass('selected');
				} else {
					table.$('tr.selected').removeClass('selected');
					$(this).addClass('selected');
				}
			});
			$.Huifold("#Huifold1 .item h4", "#Huifold1 .item .info", "fast", 1,
					"click");
		});

		/*用户-还原*/
		function member_huanyuan(obj, id) {
			layer.confirm('确认要还原吗？', function(index) {

				$(obj).remove();
				layer.msg('已还原!', {
					icon : 6,
					time : 1000
				});
			});
		}

		/*用户-删除*/
		function member_del(obj, id) {
			layer.confirm('确认要删除吗？', function(index) {
				$(obj).parents("tr").remove();
				layer.msg('已删除!', {
					icon : 1,
					time : 1000
				});
			});
		};
		
		function doSort(prop, sort) {
			$("#" + prop).val(sort);
			doSearch();
		};
		
		function doSearch() {
			var form = document.forms['houseinfolist'];
			form.submit();
		};
		
		function collection(id,collectType) {		
			var tar = "${ctx}/house/col";
			doSubmit(tar,id,collectType);
		};
		
		function doSubmit(tar,id,collectType){
			var form = document.forms['editForm'];
			form.action = tar;
			form['hid'].value = id;
			$("#e_hid").val(id);
			$("#collectType").val(collectType);
			form.submit();
		};
		
		function datadel(id){
			
			var idStr = "";
 			$("input[name='checkboxhid']:checked").each(function () {
 				idStr = idStr + this.value +"_";
			});
			
			if(idStr==""){
				return;
			}
			
			//管理员
			if(parseInt(scope)==2){
				layer.confirm(
						'如删除房源则经纪人列表中的此记录也将删除，是否继续？',
						{
							btn : [ '确认', '返回']
						//按钮
						}, function() {
							if(delcheck(idStr)){
								var tar = "${ctx}/house/del";
								doSubmit(tar,idStr);
							}else{
								layer.msg("所选房源中包含审核中或锁定中房源，不能删除");
							}
						}, function() {
							
						});
			}else{
				layer.confirm(
						'确认删除所选房源吗？',
						{
							btn : [ '确认', '返回']
						//按钮
						}, function() {
							if(delcheck(idStr)){
								var tar = "${ctx}/house/del";
								doSubmit(tar,idStr);
							}else{
								layer.msg("所选房源中包含审核中或锁定中房源，不能删除");
							}
						}, function() {
							
						});
			}
		};
		
		function doDelete(id){
			//管理员
			if(parseInt(scope)==2){
				layer.confirm(
						'如删除房源则经纪人列表中的此记录也将删除，是否继续？',
						{
							btn : [ '确认', '返回']
						//按钮
						}, function() {
							if(delcheck(id)){
								var tar = "${ctx}/house/del";
								doSubmit(tar,id);
							}else{
								layer.msg("所选房源审核中或锁定中，不能删除");
							}
						}, function() {
							
						});
			}else{
				layer.confirm(
						'确认删除此房源吗？',
						{
							btn : [ '确认', '返回']
						//按钮
						}, function() {
							if(delcheck(id)){
								var tar = "${ctx}/house/del";
								doSubmit(tar,id);
							}else{
								layer.msg("所选房源审核中或锁定中，不能删除");
							}
						}, function() {
							
						});
			}
		};
		
		function delcheck(id) {
			var flag = true;
			
			$.ajax({
				type: "GET",
				url: '${ctx}/house/delc',
				data: {
					hid: id
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
		
		function seeHouse(id){
			var tar = "${ctx}/house/see";
			doSubmit(tar,id);
		};
	
		function editHouse(id){
			var tar = "${ctx}/house/edit";
			doSubmit(tar,id);
		};
		
		$("div[name='room_switch']").on('switch-change', function (e, data) {
			var $el = $(data.el) , value = data.value;
		
			var $room_id = $el[0];
			
			room_id = $room_id.value;
			
			var status = 1;
			if(value){
				status = 0;
			}

			changeRoomStatus(room_id,status);
		});
		
		//改变房间状态
		function changeRoomStatus(id,status){
			$.ajax({
				type: "GET",
				url: '${ctx}/house/crs',
				data: {
					id: id,
					status: status
				},
				dataType:'json',
				cache: false,
				async : false, 
				success: function(data){
					if(1 == data.success) {//tr_tmp
						if(data.data == 0){
							$("#"+id).parents("tr[name='tr_tmp']").find("div[name='lease_switch']").hide();
							$("#"+id).parents("tr[name='tr_tmp']").find("div[name='lease_switch2']").show();
						}else{
							$("#"+id).parents("tr[name='tr_tmp']").find("div[name='lease_switch']").show();
							$("#"+id).parents("tr[name='tr_tmp']").find("div[name='lease_switch']").bootstrapSwitch('setActive', true);  // true || false
							$("#"+id).parents("tr[name='tr_tmp']").find("div[name='lease_switch']").bootstrapSwitch('setState', false);  // true || false
							$("#"+id).parents("tr[name='tr_tmp']").find("div[name='lease_switch2']").hide();
						}
					}
				}
			}); 
		};
		
		$("div[name='lease_switch']").on('switch-change', function (e, data) {
			var $el = $(data.el) , value = data.value;
		
			var $room_id = $el[0];
			
			room_id = $room_id.value;
			
			var status = 0;
			if(value){
				status = 1;
			}
			
			changeLeaseStatus(room_id,status);
		});
		
		//改变出租信息状态
		function changeLeaseStatus(id,status){
			$.ajax({
				type: "GET",
				url: '${ctx}/house/cls',
				data: {
					id: id,
					status: status
				},
				dataType:'json',
				cache: false,
				async : false, 
				success: function(data){
					
				}
			});
		};
		
		//刷新出租信息
		function refreshLease(it,lid){
			$.ajax({
				type: "GET",
				url: '${ctx}/house/refl',
				data: {
					lid: lid,
				},
				dataType:'json',
				cache: false,
				async : false, 
				success: function(data){
					if(1 == data.success) {
						layer.msg("刷新成功");
						$(it).hide();
						$(it).next().show();
					}
				}
			});
		};
		
		function screenPropertyId(it){
			$("#propertyId").val(it);
			var form = document.forms['houseinfolist'];
			form.submit();
		};
		
		function screenReleaseStatus(it){
			$("#releaseStatus").val(it);
			var form = document.forms['houseinfolist'];
			form.submit();
		};
		
		function screenSignStatus(it){
			$("#signStatus").val(it);
			var form = document.forms['houseinfolist'];
			form.submit();
		};				
		
		$('#allSelect').click(function(){
			if(this.checked){
				$("input[name='checkboxhid']").each(function () {
					$("input[name='checkboxhid']").attr('checked', true);
				});
			}else{
				$("input[name='checkboxhid']").each(function () {
					$("input[name='checkboxhid']").attr('checked', false);
				});
			}
		});
		
		//根据房源编号查询房源
		function getHouseNo(){
			var form = document.forms['myHouseNO'];
			form.submit();
		}; 
		
		//房源验真
		function truthHouse(This,id){
			if(delcheck(id)){
				
				$.ajax({
					type : "POST",
					url : '${ctx}/house/truth',
					data : {
						id : id,
					},
					dataType : 'json',
					cache : false,
					success : function(data) {
						if (1 == data.success) {
							layer.alert(data.message);
							$(This).attr("Class","btn btn-default radius disabled");
							/* var parent = $(This).parent();
							parent.empty();
							var html = '<a class="btn btn-default radius disabled">申请验真</a>';
							parent.append(html); */
						} else {
							layer.alert(data.message);
						}
							
					},
					error : function() {
						layer.msg("网络异常，请稍后重试！", {icon: 5});
					}
				});
				
			}else{
				layer.msg("审核中或锁定中的房源不能验真！");
			}
		}

	</script>
</body>
</html>