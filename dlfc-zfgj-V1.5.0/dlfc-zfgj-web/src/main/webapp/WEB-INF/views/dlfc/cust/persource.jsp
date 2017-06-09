<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<!DOCTYPE HTML>
<html>
<head>

<%@ include file="/WEB-INF/views/include/head2.jsp"%>
<script type="text/javascript">
function page(n,s){
	$("#pageNo").val(n);
	$("#pageSize").val(s);
	doSearch();
	return false;
}

</script>
<title>删除的用户</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		客户管理 <span class="c-gray en">&gt;</span>客户列表<a
			class="btn btn-success radius r mr-20"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="content">
		<div class="mt-20">
			<!-- <legend>客源管理</legend> -->
			<div class="list_tabs cl">
			
			<a id="1" href="${ctx}/cust/source?st=1" <c:if test="${sa == null||sa == ''}">class="active"</c:if>>我的客源</a>
			<c:if test="${enname == 'manager'}">
				<a id="0" href="${ctx}/cust/source?sa=1" <c:if test="${sa == '1'}">class="active"</c:if>>部门客源</a>
		  	</c:if> 
			</div>
			<div class="list_head cl">
				<shiro:hasPermission name="emp:cust:add">
				<a class="btn btn-primary add_btn radius" onclick="openAddDialog()"><i class="Hui-iconfont"></i>添加客户</a>
				</shiro:hasPermission>
				<div class="house-list-tab cl">
					<a  id="1" href="${ctx}/cust/source?st=1&sa=" ><span <c:if test="${st == null || st == ''|| st == 1}">class="current"</c:if> >全部未签</span></a>
				<a id="2" href="${ctx}/cust/source?st=2&sa=" ><span <c:if test="${st == 2}">class="current"</c:if>>关注</span></a>
				<%-- <a id="3" href="${ctx}/cust/source?st=3&sa=" ><span <c:if test="${st == 3}">class="current"</c:if>>意向</a> --%>
				<a id="4" href="${ctx}/cust/source?st=4&sa=" ><span <c:if test="${st == 4}">class="current"</c:if>>签约</span></a>
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
					<a id="allsort" href="javascript:void(0);" title="无排序" onclick="doSort('signtimesort', 'desc')">状态 <span> &uarr;</span></a>
				</c:if>
				<c:if test="${ss == 'desc'}">
					<a id="upsort" href="javascript:void(0);" title="降序" onclick="doSort('signtimesort', 'asc')" class="active">状态 <span> &darr;</span></a>
				</c:if>
				<c:if test="${ss == 'asc'}">
					<a id="downsort" href="javascript:void(0);" title="升序" onclick="doSort('signtimesort', 'none')" class="active">状态<span> &uarr;</span></a>
				</c:if> --%>
			</div>
			
			<form name="custinfolist" action="${ctx}/cust/source">
				<input type="hidden" name="st" value="${st}">
				<input type="hidden" name="sa" value="${sa}">
				<input id="butimesort" type="hidden" name="bs" value="${bs}">
				<%-- <input id="signtimesort" type="hidden" name="ss" value="${ss}"> --%>
				<input id="pageNo" type="hidden" name="pageNo" value="${page.pageNo}">
				<input id="pageSize" type="hidden" name="pageSize" value="${page.pageSize}">
				<input type="hidden" name="currentPage" value="">
			</form>
			<form name="submitForm" action="${ctx}/cust/edit"  method="post">
				<input id="custid" type="hidden" name="custid" value="">
				<input id="require" type="hidden" name="require" value="">
				<input id="repage" type="hidden" name="repage" value="1">
				
				<input id="st" name="st" type="hidden" value="${st}">
				<input id="hsa" name="hsa" type="hidden" value="${sa}">
			</form>
			
			<div class="cl pd-5 bg-1 bk-gray mt-20">
				<shiro:hasPermission name="emp:cust:del">
				<span class="l">
					<a href="javascript:;" onclick="datadel('${cust.id}');" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i>删除</a>
				</span>
				</shiro:hasPermission>
			</div>
			<table class="table table-border table-bordered  table-bg">
				<thead>
					<tr class="text-c">
						<th width="55"><input type="checkbox" name="" value="" id="allSelect"></th>
						<th width="120">客源详情</th>
						<th width="300">客户需求</th>
						<th width="80">状态</th>
						<th width="100">操作</th>
					</tr>
				</thead>
			</table>
				<c:forEach items="${page.list}" var="cust">
			<table class="table table-border table-bordered  table-bg mb-20">
				<tbody>
				 	<tr class="text-c">
						<td width="55"><input type="checkbox" value="${cust.id}" name="checkboxid"></td>
						<td width="120"><p class="house_name">
								 ${cust.name}<span class="label label-primary">${cust.resourceName}</span>
							</p>
							<p class="text-l pt-5">${cust.mobile}</p></td>
							<td  id="requirementpanel" class="text-l" width="300">
								<c:forEach items="${cust.requirements}" var="requirement">
									
									<span class="mr-5 mb-10" id="requirementsolo">
										<a class="btn btn-primary size-MINI" >
										<!-- <span class="label label-success mr-5"> -->${requirement.requirement}<!-- </span> -->
											<i class="Hui-iconfont" onclick="delrequire('${requirement.id}',this);">&#xe6a6;</i>
										</a>
									</span>
								</c:forEach>
								
								<input type="hidden" id="requirepanelid">
								<input type="text" class="input-text size-MINI" style="width: 100px;display:none" id="requirepanel" onmouseout="doadd(this);">
								
								<a class="btn btn-primary size-MINI" style="margin-bottom: 0;" onclick="addrequire('${cust.id}',this);">
									<i class="Hui-iconfont">&#xe600;</i>
								</a>
							</td>
						<td width="80"><span class="select-box"> 
								<select class="select" size="1" name="status" onchange="changest('${cust.id}',this);">
									<option value="1" <c:if test="${cust.status == 1}">selected="selected"</c:if>>维护</option>
									<option value="2" <c:if test="${cust.status == 2}">selected="selected"</c:if>>关注</option>
									<%-- <option value="3" <c:if test="${cust.status == 3}">selected="selected"</c:if>>意向</option> --%>
									<option value="4" <c:if test="${cust.status == 4}">selected="selected"</c:if>>签约</option>
								</select>
						</span></td>
						
						<td class="text-c" width="100">
						<shiro:hasPermission name="emp:cust:edit">
						<a class="btn btn-primary radius" onclick="openEditDialog('${cust.id}','${cust.name}','${cust.mobile}','${cust.gender}','${cust.resource}')">编辑</a>
						</shiro:hasPermission>
						<shiro:hasPermission name="emp:cust:del"> 
						<a class="btn btn-danger radius" onclick="doDelete('${cust.id}');">删除</a>
						</shiro:hasPermission>
						</td>
						
					</tr>
					<tr>
						<td colspan="6" class="tab_fold">
							<ul id="Huifold1" class="Huifold">
								<li class="item cl">
									<h4>
										<b class="Hui-iconfont">&#xe600;</b>查看备注信息
									</h4>
									<div class="info">
										<div class="responsive">
											<div class="row cl">
												<div class="col-1-1">
													<a class="fold_add_remarks" onclick="openRemarkDialog('${cust.id}')"><i class="Hui-iconfont">&#xe600;</i>添加备注</a>
												</div>
											</div>
											
											<c:forEach items="${cust.comments}" var="comment">
												<div class="row cl">
													<div class="col-0"><a class="fold_add_remarks" onclick="openUpdateRemarkDialog('${cust.id}','${comment.id}')"><i class="Hui-iconfont">&#xe600;</i>修改备注</a></div>
													<div class="col-1"><a class="fold_add_remarks" onclick="openDeleteRemarkDialog('${cust.id}','${comment.id}')"><i class="Hui-iconfont">&#xe600;</i>删除备注</a></div>
													<div class="col-2"><fmt:formatDate value="${comment.eventTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
													<div class="col-6" style="width:600px;word-wrap:break-word;">
														${comment.comments}
													</div>
												</div>
											</c:forEach>
										</div>
									</div>
								</li>
							</ul>
						</td>
					</tr>
				</tbody>
			</table>
					</c:forEach> 
			<div class="pagination">${page}</div>
		</div>
	</div>

 	<div id="addcust" style="display: none">
		<form id="addForm" name="addForm" class="form form_layer" method="post" action="${ctx}/cust/addc">
			<!-- 避免重复提交 -->
				<input type="hidden" name="${tokenName}" value="${token }"/>
				<input type="hidden" name="tokenName" value="${tokenName}"/>
			<!-- 避免重复提交 -->
			<div class="responsive">
				<div class="row cl">
					<input id="repage" type="hidden" name="repage" value="1">
				</div>
			</div>

			<div class="responsive">
				<div class="row cl">
					<label class="form-label text-r col-3">姓名：</label>
					<div class="formControls col-5">
						<input type="text" class="validate[required,maxSize[15],minSize[2]] input-text size-S"  name="name" id="add_name" maxlength="15" >
						
					</div>
					<p class="col-4 form_text"></p>
				</div>
			</div>
			<div class="row cl ">
				<label class="form-label text-r col-3">性别：</label>
				<div class="skin-minimal col-5">
					<div class="">
						<input type="radio" id="radio-1" name="gender" value="1" checked="checked" ><label for="radio-1">男</label> 
						<input type="radio" id="radio-2" name="gender" value="2"> <label for="radio-2">女</label>
					</div>
					<p class="col-4 form_text"></p>
				</div>
			</div>
			<div class="responsive">
				<div class="row cl">
					<label class="form-label text-r col-3">手机：</label>
					<div class="formControls col-5">
						<input type="text" class="validate[required,maxSize[11],funcCall[checkPhone]] input-text size-S" name="mobile" id="add_mobile"  placeholder="手机" maxlength="11">
					</div>
					<p class="col-4 form_text"></p>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label text-r col-3">来源：</label>
				<div class="formControls col-5">
					<span class="select-box"> 
						<select class="select" size="1" name="resource">
							<option value="1">门店</option>
							<option value="2">个人</option>
							<option value="3">大连市房屋租赁公共服务平台</option>
							<option value="4">官网</option>
							<option value="5">线上平台</option>
							<option value="6">其他</option>
					</select>
					</span>
				</div>
			</div>
			<div class="row cl btn_group">
				<div class="col-9 col-offset-3">
					<a class="btn btn-primary radius" id="tijiao" >提交</a> 
					<a class="btn btn-default radius" onclick="closeAddDialog()">返回</a> 
				</div>
			</div>
			<input type="hidden" name="${tokenName}" value="${token }"/>
			<input type="hidden" name="tokenName" value="${tokenName}"/>
		</form>
	</div>

	<div id="addRemark"  style="display: none">
		<form id="remarkForm" name="remarkForm"  class="form form_layer" method="post" action="${ctx}/cust/mark">
			<br/>
			<input id="remarkForm_custid" type="hidden" name="custid"> 
			<input id="remarkForm_repage" type="hidden" name="repage" value="1">
			
			<div class="responsive">
				<div class="row cl">
					<label class="form-label text-r col-3">备注：</label>
					<div class="formControls col-4-5">
						<textarea class="validate[required,maxSize[300],funcCall[checkRemark]] textarea-text size-S" rows="" cols="" name="remark" id="remark" style="height: 160px;width: 310px;" placeholder="写点什么..."  maxlength="300"></textarea>
					</div>
					<div class="col-4-5"></div>
				</div>
			</div>
			<div class="row cl btn_group">
				<div class="col-9 col-offset-3">
					<a class="btn btn-primary radius" onclick="confirmRemarkDialog()">提交</a> 
					<a class="btn btn-default radius" onclick="closeRemarkDialog()">返回</a> 
				</div>
			</div>
		</form>
	</div>
	
	<div id="updateRemark"  style="display: none">
		<form id="updateRemarkForm" name="updateRemarkForm"  class="form form_layer" method="post" action="${ctx}/cust/um">
			<br/>
			<input id="updateRemarkForm_custid" type="hidden" name="custid">
			<input id="updateRemarkForm_commentid" type="hidden" name="commentid"> 
			<input id="updateRemarkForm_repage" type="hidden" name="commentRepage" value="1">
			
			<div class="responsive">
				<div class="row cl">
					<label class="form-label text-r col-3">备注：</label>
					<div class="formControls col-4-5">
						<textarea class="validate[required,maxSize[300],funcCall[checkUpdateRemark]] textarea-text size-S" rows="" cols="" name="commentRemark" id="commentRemark" style="height: 160px;width: 310px;" placeholder="写点什么..."  maxlength="300"></textarea>
					</div>
					<div class="col-4-5"></div>
				</div>
			</div>
			<div class="row cl btn_group">
				<div class="col-9 col-offset-3">
					<a class="btn btn-primary radius" onclick="confirmUpdateRemarkDialog()">提交</a> 
					<a class="btn btn-default radius" onclick="closeUpdateRemarkDialog()">返回</a> 
				</div>
			</div>
		</form>
	</div>
	
	<div id="deleteRemark"  style="display: none">
		<form id="deleteRemarkForm" name="deleteRemarkForm"  class="form form_layer" method="post" action="${ctx}/cust/dm">
			<br/>
			<input id="deleteRemarkForm_custid" type="hidden" name="custid">
			<input id="deleteRemarkForm_commentid" type="hidden" name="commentid"> 
			<input id="deleteRemarkForm_repage" type="hidden" name="commentRepage" value="1">
		</form>
	</div>
	
	<div id="editcust" style="display: none">
		<form id="editForm" name="editForm"  class="form form_layer" method="post" action="${ctx}/cust/editc">
			<br/>
			<div class="responsive">
				<div class="row cl">
					<input id="editForm_custid" type="hidden" name="custid"> 
					<input id="editForm_repage" type="hidden" name="repage" value="1">
				</div>
			</div>
			<div class="responsive">
				<div class="row cl">
					<label class="form-label text-r col-3">姓名：</label>
					<div class="formControls col-5">
						<input type="text" class="validate[required,maxSize[15],minSize[2]] input-text size-S" name="name" id="edit_name" placeholder="姓名"  maxlength="10">
					</div>
					<p class="col-4 form_text"></p>
				</div>
			</div>
			<div class="row cl ">
				<label class="form-label text-r col-3">性别：</label>
				<div class="skin-minimal col-8">
					<div class="">
						<input type="radio" id="edit_gender_1" name="edit_gender" value="1"><label for="edit_gender_1">男</label> 
						<input type="radio" id="edit_gender_2" name="edit_gender" value="2"> <label for="edit_gender_2">女</label>
					</div>
					<div class="col-3"><span class="Validform_checktip"></span></div>
				</div>
			</div>
			<div class="responsive">
				<div class="row cl">
					<label class="form-label text-r col-3">手机：</label>
					<div class="formControls col-5">
						<input type="text" class="validate[required,maxSize[11],funcCall[checkPhone]] input-text size-S" name="mobile" id="edit_mobile" placeholder="手机"  maxlength="11">
					</div>
					<p class="col-4 form_text"></p>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label text-r col-3">来源：</label>
				<div class="formControls col-5">
					<span class="select-box"> 
						<select class="select" size="1" name="resource" id="edit_resource">
							<option value="1">门店</option>
							<option value="2">个人</option>
							<option value="3">大连市房屋租赁公共服务平台</option>
							<option value="4">官网</option>
							<option value="5">线上平台</option>
							<option value="6">其他</option>
					</select>
					</span>
				</div>
			</div>
			<div class="row cl btn_group">
				<div class="col-9 col-offset-3">
					<a class="btn btn-primary radius" onclick="confirmEditDialog()">提交</a> 
					<a class="btn btn-default radius" onclick="closeEditDialog()">返回</a> 
				</div>
			</div>
		</form>
	</div>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/laypage/1.2/laypage.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/icheck/jquery.icheck.min.js"></script>
	<%@ include file="/WEB-INF/views/include/foot.jsp"%>
	<script type="text/javascript">
		$(function() {
			$("#addcust").hide();
			$("#addRemark").hide();
			$("#editcust").hide();
			$("#updateRemark").hide();
			
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
			
			$('#addForm').validationEngine('attach', { binded : false ,showOneMessage: true,maxErrorsPerField : 1,promptPosition: 'centerRight',addPromptClass: 'formError-noArrow formError-text'});
			$('#editForm').validationEngine('attach', { binded : false ,showOneMessage: true,maxErrorsPerField : 1,promptPosition: 'centerRight',addPromptClass: 'formError-noArrow formError-text'});
			$('#remarkForm').validationEngine('attach', { binded : false ,showOneMessage: true,maxErrorsPerField : 1,promptPosition: 'centerRight',addPromptClass: 'formError-noArrow formError-text'});
			$('#updateRemarkForm').validationEngine('attach', { binded : false ,showOneMessage: true,maxErrorsPerField : 1,promptPosition: 'centerRight',addPromptClass: 'formError-noArrow formError-text'});
			
			$("#tijiao").on("click", confirmAddDialog);
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
		}
		
		//新建客户
		function confirmAddDialog() {
			var form = $("#addForm");
			form.action ="${ctx}/cust/addc";
			form.submit();
		};
		
		function closeAddDialog() {
			layer.close(_didx);
			$("#edit_name").val('');
			$("#edit_mobile").val('');
			$("#edit_gender").val();
			$("#edit_resource").val();
			$("#addcust").hide();
		};
		
		 function openAddDialog() {
			_didx = layer.open({
				type: 1, //page层
				area: ['620px', '350px'],
				title: '新建客户',
				shade: 0.6, //遮罩透明度
				moveType: 1, //拖拽风格，0是默认，1是传统拖动
				shift: 0, //0-6的动画形式，-1不开启
				content: $('#addcust')
			});
				
			$("#addcust").show();
		}; 
		
		//添加备注
		function confirmRemarkDialog() {
			var form = $("#remarkForm");
			form.action ="${ctx}/cust/mark";
			form.submit();
		};
		//修改备注
		function confirmUpdateRemarkDialog() {
			var form = $("#updateRemarkForm");
			form.action ="${ctx}/cust/um";
			form.submit();
		};
		
		function closeRemarkDialog() {
			layer.close(_didx);
			$("#remark").val('');
			$("#addRemark").hide();
		};
		
		function openRemarkDialog(id) {
			$("#remarkForm_custid").val(id);
			_didx = layer.open({
				type: 1, //page层
				area: ['620px', '350px'],
				title: '添加备注',
				shade: 0.6, //遮罩透明度
				moveType: 1, //拖拽风格，0是默认，1是传统拖动
				shift: 0, //0-6的动画形式，-1不开启
				content: $('#addRemark')
			});
				
			$("#addRemark").show();
		};
		
		function closeUpdateRemarkDialog() {
			layer.close(_didx);
			$("#commentRemark").val('');
			$("#updateRemark").hide();
		};
		
		function openUpdateRemarkDialog(custid,commentid) {
			$("#updateRemarkForm_custid").val(custid);
			$("#updateRemarkForm_commentid").val(commentid);
			_didx = layer.open({
				type: 1, //page层
				area: ['620px', '350px'],
				title: '修改备注',
				shade: 0.6, //遮罩透明度
				moveType: 1, //拖拽风格，0是默认，1是传统拖动
				shift: 0, //0-6的动画形式，-1不开启
				content: $('#updateRemark')
			});
				
			$("#updateRemark").show();
		};
		
		function openDeleteRemarkDialog(custid,commentid) {
			$("#deleteRemarkForm_custid").val(custid);
			$("#deleteRemarkForm_commentid").val(commentid);
			
			layer.confirm('确定删除备注吗？', function(index){
				var form = $("#deleteRemarkForm");
				form.action ="${ctx}/cust/dm";
				form.submit();
				layer.close(index);
			});
		};
		
		//编辑客户
		function confirmEditDialog() {
			var form = $("#editForm");
			form.action ="${ctx}/cust/edit";
			form.submit();
		};
		
		function closeEditDialog() {
			layer.close(_didx);
			$("#edit_name").val('');
			$("#edit_mobile").val('');
			$("#edit_gender").val();
			$("#edit_resource").val();
			$("#editcust").hide();
		};
		
		function openEditDialog(id,name,mobile,gender,resource) {
			$("#editForm_custid").val(id);
			$("#edit_name").val(name);
			$("#edit_mobile").val(mobile);
			$("input[type=radio][name=edit_gender][value="+gender+"]").attr("checked",'checked');
			$("#edit_resource").val(resource);
			
			_didx = layer.open({
				type: 1, //page层
				area: ['620px', '350px'],
				title: '编辑客户',
				shade: 0.6, //遮罩透明度
				moveType: 1, //拖拽风格，0是默认，1是传统拖动
				shift: 0, //0-6的动画形式，-1不开启
				content: $('#editcust')
			});
				
			$("#editcust").show();
		};
		
		 function doSort(prop, sort) {
			$("#" + prop).val(sort);
			doSearch();
		};
		
		function doSearch() {
			var form = document.forms['custinfolist'];
			form.submit();
		};
		
		function datadel(id){
			var idStr = "";
 			$("input[name='checkboxid']:checked").each(function () {
 				idStr = idStr + this.value +"_"
			});
			
			if(idStr==""){
				return;
			}
			
			layer.confirm(
				'确认删除所选客户吗？',
				{
					btn : [ '确认', '返回']
				//按钮
				}, function() {
					var tar = "${ctx}/cust/delc";
					doDelSubmit(tar,idStr);
				}, function() {
					
				});
		};
		
		function doDelete(id){
			layer.confirm(
				'确认删除所选客户吗？',
				{
					btn : [ '确认', '返回']
				//按钮
				}, function() {
					var tar = "${ctx}/cust/delc";
					doDelSubmit(tar,id,require);
				}, function() {
					
				});
		};
		
		function doDelSubmit(tar,id,require){
			var st = $("#st").val();
			var sa = $("#hsa").val();
			
			var form = document.forms['submitForm'];
			form.action = tar;
			form['custid'].value = id;
			form['require'].value = require;
			
			form['hsa'].value = sa;
			form['st'].value = st;
			form.submit();
		};
		
		function changest(id,st){
			var select_val = $(st).children('option:selected').val();
			
 			$.ajax({
				type: "GET",
				url: '${ctx}/cust/cst',
				data: {
					id: id,
					status: select_val
				},
				dataType:'json',
				cache: false,
				async : false, 
				success: function(data){
					
				}
			});  
		};
		
		function addrequire(id,it){
			var length = $(it).parent().children("#requirementsolo").length
			
			if (length > 9) {
				layer.msg("最多可添加十个");
				return;
			}
			
			$("#requirepanelid").val(id);
			$(it).prev().show();
		};
		
		function doadd(it){
			$(it).focus();
			var require = $(it).val();
			var id = $("#requirepanelid").val();
			
			if(require==""){
				$(it).hide();
				return;
			}
			
			var len = $(it).val().length;
			if(len > 10){
				layer.msg("请输入10字以内");
				return;
			}
			
			var tar = "${ctx}/cust/addre";
			doSubmit(tar,id,require);
		};
		
		function delrequire(id,it){
			layer.confirm(
				'确认删除客户需求？',
				{
					btn : [ '确认', '返回']
				//按钮
				}, function() {
					var tar = "${ctx}/cust/delre";
					doSubmit(tar,id);
				}, function() {
					
				});
		};
		
		function doSubmit(tar,id,require){
			var form = document.forms['submitForm'];
			form.action = tar;
			form['custid'].value = id;
			form['require'].value = require;
			form.submit();
		};
		
		function checkPhone(phone){
			var partten = /^1[3,4,5,7,8]\d{9}$/;
				
			if (!partten.test(phone.val())) {
				 return "手机号格式不正确";
			}
		};
		
		function checkRemark(remark){
			var len = $("#remark").val().length;
			if (len > 300||len<1) {
				return "请输入1-300字！";
			}
			return true;
		};
		function checkUpdateRemark(remark){
			var len = $("#commentRemark").val().length;
			if (len > 300||len<1) {
				return "请输入1-300字！";
			}
			return true;
		};
		
		$('#allSelect').click(function(){
			if(this.checked){
				$("input[name='checkboxid']").each(function () {
					$("input[name='checkboxid']").prop('checked', true);
				});
			}else{
				$("input[name='checkboxid']").each(function () {
					$("input[name='checkboxid']").removeAttr('checked');
				});
			}
		});

	</script>
</body>
</html>