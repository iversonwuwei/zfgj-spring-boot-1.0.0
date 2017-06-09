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
	<form name="houseinfolist" action="${ctx}/house/list">
	<div class="content">
		<div class="mt-20">
			<legend>房源列表</legend>
			<div class="list_head cl mt-20">
				<div class="r row cl col-3">
					<div class="formControls col-9 ">
						<input type="text" class="input-text" name="searchInfo" placeholder="房源地址/编号/维护人" value="${searchInfo }">
					</div>
					<div class="formControls col-3">
						<a onclick="screenSignStatus();" class="btn btn-primary">搜索</a>
					</div>
				</div>
			
				<div class="list_tabs cl">
					<c:if test="${ms==1 && comp_flg != 'manager'}">
						<a href="${ctx}/house/list">个人房源</a>
					</c:if>
					<c:if test="${comp_flg == 'manager'||enname == 'manager'}">
						<a href="" class="active">部门房源</a>
					</c:if> 
				</div>
			</div>
			<div class="list_head cl">
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
					<input id="ms" type="hidden" name="ms" value="1">
				</form>
				<form name="editForm" action="${ctx}/house/edit"  method="post">
					<input id="collectType" type="hidden" name="collectType" value="">
					<input id="e_hid" type="hidden" name="hid" value="">
					<input id="repage" type="hidden" name="repage" value="1">
					<input id="ms" type="hidden" name="ms" value="1">
				</form>
				<%-- <div class="list_tabs cl">
					<a id="0" href="${ctx}/house/list?st=" <c:if test="${st == null || st == '' }">class="active"</c:if> >全部房源</a>
					<a id="1" href="${ctx}/house/list?st=1" <c:if test="${st == 1}">class="active"</c:if>>关注房源</a>
				</div> --%>
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
						<th width="100">维护人</th>
						<th width="180">操作</th>
					</tr>
				</thead>
			</table>
				<c:forEach items="${page.list}" var="houseInfo">
			<table class="table table-border table-bordered  table-bg mb-20">
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
						<td width="400"><p class="house_name">
								<a href="${fns:getSiteUrl()}/lease/info?id=${houseInfo.lid}" target="_blank">
								${houseInfo.houseAddr}</a>
								
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
								</c:if> --%>
									
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
										<c:if test="${houseInfo.certStatus==1 && houseInfo.auditStatus == 2}">
											<span class="label label-purple">认证房源</span>
										</c:if>
									</c:when>
								</c:choose>
								<!-- 显示房源状态标签 -->
								
							</p></td>

						<td><p>
							<c:if test="${houseInfo.leaseInfo!=null}">
								${houseInfo.leaseInfo.aparmentRoom } 室 ${houseInfo.leaseInfo.aparmentHall }  厅
							</c:if>
							</p>
							<p><fmt:formatNumber value="${houseInfo.buildingArea}" pattern="#.00"/>平方米</p>
						</td>
						<td width="110"><p>${houseInfo.houseOwnerName}</p>
							<p>${houseInfo.houseOwnerTel}</p></td>
						<td width="80">
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
							</c:if>
							<%-- <c:if test="${houseInfo.contractStatus==0}">
								<span class="label label-refuse">锁定</span>
							</c:if> --%>
						</td>
						<td width="100"><p>${houseInfo.ename}</p>
							<%-- <p>${houseInfo.emobile}</p> --%></td>
						<td class="text-c" width="180">
							<shiro:hasPermission name="emp:house:del">
								<c:choose>
									<c:when test="${ not empty houseInfo.auditStatus && houseInfo.auditStatus!=1 && houseInfo.lockStatus!=1&&lockFlg=='false'}">
										<a  class="btn btn-danger radius" onclick="doDelete('${houseInfo.id}');">删除</a>
									</c:when>
									<c:otherwise>
										<a  class="btn btn-default radius" >删除</a>
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
										<b class="Hui-iconfont">&#xe600;</b>查看出租信息
									</h4>
									<div class="info ">
										<div class="cl house-manger-info">
											<table>
												<tr>
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
																		
																		<div name="room_switch" class="r switch size-S" data-on="success"
																			data-on-label="可租" data-off-label="已租"
																			data-off="danger">
																			<input  type="checkbox" disabled <c:if test='${room.status==0}'>checked</c:if> <c:if test='${houseInfo.releaseStatus==3}'>disabled</c:if> value="${room.id}"/>
																		</div></td>
																	<td width="180"><span>&yen;<em
																			class="house-sell-price">${room.rent }</em>/月
																	</span></td>
																</tr>
																</c:forEach>
															</table>
														</div></td>
													<td width="240">
														<c:choose>
															<c:when test="${ empty houseInfo.auditStatus || houseInfo.auditStatus !=2|| houseInfo.leaseInfo.auditStatus ==2||houseInfo.leaseInfo.releaseStatus ==2}">
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
																	<input type="checkbox" disabled name="lease_switch_box" <c:if test='${houseInfo.releaseStatus==1}'>checked</c:if> <c:if test='${houseInfo.releaseStatus==2||(houseInfo.leaseInfo.roomCount ==0 && houseInfo.leaseMode == 2 )}'>disabled</c:if>  value="${houseInfo.lid}-${houseInfo.leaseMode}" />
																</div>
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
		};
		
		function doDelete(id){
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
	</script>
</body>
</html>