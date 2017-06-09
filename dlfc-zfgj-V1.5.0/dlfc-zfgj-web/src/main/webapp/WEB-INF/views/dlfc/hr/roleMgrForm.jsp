<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>角色管理</title>
<meta name="decorator" content="themehui" />
<%@include file="/WEB-INF/views/include/treeview.jsp"%>
<script type="text/javascript">
		$(document).ready(function(){
// 			$("#name").focus();
// 			$("#inputForm").validate({
// 				rules: {
// 					name: {remote: "${ctx}/hr/role/checkName?oldName=" + encodeURIComponent("${role.name}")},
// 					enname: {remote: "${ctx}/hr/role/checkEnname?oldEnname=" + encodeURIComponent("${role.enname}")}
// 				},
// 				messages: {
// 					name: {remote: "角色名已存在"},
// 					enname: {remote: "英文名已存在"}
// 				},
// 				submitHandler: function(form){
// 					var ids = [], nodes = tree.getCheckedNodes(true);
// 					for(var i=0; i<nodes.length; i++) {
// 						ids.push(nodes[i].id);
// 					}
// 					$("#menuIds").val(ids);
// 					var ids2 = [], nodes2 = tree2.getCheckedNodes(true);
// 					for(var i=0; i<nodes2.length; i++) {
// 						ids2.push(nodes2[i].id);
// 					}
// 					$("#officeIds").val(ids2);
// 					loading('正在提交，请稍等...');
// 					form.submit();
// 				},
// 				errorContainer: "#messageBox",
// 				errorPlacement: function(error, element) {
// 					$("#messageBox").text("输入有误，请先更正。");
// 					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
// 						error.appendTo(element.parent().parent());
// 					} else {
// 						error.insertAfter(element);
// 					}
// 				}
// 			});

			var setting = {check:{enable:true,nocheckInherit:true},view:{selectedMulti:false},
					data:{simpleData:{enable:true}},callback:{beforeClick:function(id, node){
						tree.checkNode(node, !node.checked, true, true);
						return false;
					}}};
			
			// 用户-菜单
			var zNodes=[
					<c:forEach items="${menuList}" var="menu">{id:"${menu.id}", pId:"${not empty menu.parentId?menu.parentId:0}", name:"${not empty menu.parentId?menu.name:'权限列表'}"},
		            </c:forEach>];
			// 初始化树结构
			var tree = $.fn.zTree.init($("#menuTree"), setting, zNodes);
			// 不选择父节点
			tree.setting.check.chkboxType = { "Y" : "ps", "N" : "s" };
			// 默认选择节点
			var ids = "${role.menuIds}".split(",");
			for(var i=0; i<ids.length; i++) {
				var node = tree.getNodeByParam("id", ids[i]);
				try{tree.checkNode(node, true, false);}catch(e){}
			}
			// 默认展开全部节点
			tree.expandAll(true);
			
			// 用户-机构
			var zNodes2=[
					<c:forEach items="${officeList}" var="office">{id:"${office.id}", pId:"${not empty office.parent?office.parent.id:0}", name:"${office.name}"},
		            </c:forEach>];
			// 初始化树结构
			var tree2 = $.fn.zTree.init($("#officeTree"), setting, zNodes2);
			// 不选择父节点
			tree2.setting.check.chkboxType = { "Y" : "ps", "N" : "s" };
			// 默认选择节点
			var ids2 = "${role.officeIds}".split(",");
			for(var i=0; i<ids2.length; i++) {
				var node = tree2.getNodeByParam("id", ids2[i]);
				try{tree2.checkNode(node, true, false);}catch(e){}
			}
			// 默认展开全部节点
			tree2.expandAll(true);
			// 刷新（显示/隐藏）机构
			refreshOfficeTree();
			$("#dataScope").change(function(){
				refreshOfficeTree();
			});
		});
		function refreshOfficeTree(){
			if($("#dataScope").val()==9){
				$("#officeTree").show();
			}else{
				$("#officeTree").hide();
			}
		}
	</script>
</head>
<body>
	<div class="content">
		<form:form id="inputForm" modelAttribute="role"
			action="${ctx}/hr/role/save" method="post"
			class="form form-horizontal">
			<form:hidden path="id" />
			<input type="hidden" id="detail" name="detail" value="${detail }">
			<sys:message content="${message}" />
			<div class="control-group" style="display: none;">
				<label class="control-label">归属机构:</label>
				<div class="controls">
					<sys:treeselect id="office" name="office.id"
						value="${role.office.id}" labelName="office.name"
						labelValue="${role.office.name}" title="机构"
						url="/hr/office/treeData" cssClass="required" />
				</div>
			</div>
			<div class="responsive">
				<legend>基础设置</legend>
				<div class="row cl">
					<label class="form-label col-2"><span class="c-red">*</span>角色名称：</label>
					<div class="formControls col-2">
						<input id="oldName" name="oldName" type="hidden"
							value="${role.name}">
						<form:input path="name" htmlEscape="false" maxlength="50" disabled="${role.id!=null}"
							class="input-text validate[required,funcCall[nameCheck]]"
							data-prompt-position="centerRight"
							data-errormessage-value-missing="* 请输入角色名称." 	
							/>
					</div>
					<label class="form-label col-2"><span class="c-red">*</span>英文名称：</label>
					<div class="formControls col-2">
						<input id="oldEnname" name="oldEnname" type="hidden"
							value="${role.enname}">
						<form:input path="enname" htmlEscape="false" maxlength="50" disabled="${role.id!=null}"
							class="input-text validate[required,funcCall[enNameCheck]]"
							data-prompt-position="centerRight"
							data-errormessage-value-missing="* 请输入英文名称." 
							/>
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-2">角色类别：</label>
					<div class="formControls col-2">
						<span class="select-box"> <form:select path="roleType"
								class="select" disabled="${role.id!=null}">
								<form:option value="comp-role">管理员类别</form:option>
								<form:option value="emp-role">经纪人类别</form:option>
							</form:select>
						</span>
					</div>
					<label class="form-label col-2">是否启用：</label>
					<div class="formControls skin-minimal col-2">

						<div class="radio-box">
							<form:radiobutton path="useable" value="1" />
							<label for="radio-1">启用</label>
						</div>

						<div class="radio-box">
							<form:radiobutton path="useable" value="0" />
							<label for="radio-2">禁用</label>
						</div>
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-2">角色描述：</label>
					<div class="formControls col-5">
						<form:textarea path="remarks" htmlEscape="false" rows="3"
							maxlength="200" class="textarea" placeholder="说点什么..." />
					</div>
				</div>
				<div class="control-group" style="display: none;">
					<label class="control-label">是否系统数据:</label>
					<div class="controls">
						<form:select path="sysData">
							<form:option value="0">否</form:option>
						</form:select>
					</div>
				</div>
				<div class="control-group" style="display: none;">
					<label class="control-label">角色授权:</label>
					<div class="controls">
						<div id="menuTree" class="ztree"
							style="margin-top: 3px; float: left;"></div>
						<form:hidden path="menuIds" />
						<div id="officeTree" class="ztree"
							style="margin-left: 100px; margin-top: 3px; float: left;"></div>
						<form:hidden path="officeIds" />
					</div>
				</div>
				<legend>基础权限</legend>
				<div class="row cl skin-minimal">
					<div class="formControls">
						<c:forEach items="${compRoles}" var="menu">
							<div class="row cl skin-minimal">
								<label class="form-label col-2">${menu.name}：</label>
								<div class="formControls col-10">
									<c:forEach items="${fns:getRoleMenu(menu.name,'like')}"
										var="cmenu">
										<div class="check-box">
											<input type="checkbox" id="checkbox-1" <c:if test="${fn:indexOf(role.menuIds,cmenu.id)>=0}">
											checked="checked"
											</c:if>
												onclick="clickTree('${cmenu.name}')"> ${cmenu.name}
										</div>
									</c:forEach>
								</div>
							</div>
						</c:forEach>		
					</div>
				</div>
			</div>
			<div class="row cl btn_group">
				<div class="col-9 col-offset-3">
					<c:if test="${(role.sysData eq fns:getDictValue('是', 'yes_no', '1') && fns:getUser().admin)||!(role.sysData eq fns:getDictValue('是', 'yes_no', '1'))}">
						<shiro:hasPermission name="hr:role:edit">
							<input id="btnSubmit" class="btn btn-primary radius"
								type="button" value="提交配置" onclick="save()" />&nbsp;</shiro:hasPermission>
						<input id="btnCancel" class="btn radius" type="button" value="返 回"
							onclick="history.go(-1)" />
					</c:if>
				</div>
			</div>
			<div class="control-group" style="display: none;">
				<label class="control-label">数据范围:</label>
				<div class="controls">
					<form:select path="dataScope" class="input-medium">
						<%-- <form:options items="${fns:getDictList('sys_data_scope')}"
							itemLabel="label" itemValue="value" htmlEscape="false" /> --%>
							<form:option value="2" >所在公司及以下数据</form:option>
					</form:select>
					<span class="help-inline">特殊情况下，设置为“按明细设置”，可进行跨机构授权</span>
				</div>
			</div>
			<div class="form-actions"></div>
		</form:form>
	</div>
	<script type="text/javascript">
	//添加验证  add by yuanjiwei
	$(function() {
		var detail = $("#detail").val();
		var txtN = $("input");
		if(detail){
			$("textarea").attr("disabled","disabled");
			for(i=0;i<txtN.length;i++){
				txtN[i].disabled="disabled";
			}	
		}
		$('#inputForm').validationEngine({
			showOneMessage: true,
			maxErrorsPerField:"1",
			addPromptClass: 'formError-text'
		});
	});
	
	function nameCheck(){
		var flag = true;
		$.ajax({
			type:"POST",
			url:"${ctx}/hr/role/checkName",
			data:"oldName="+encodeURIComponent('${role.name}')+"&name="+encodeURIComponent($("#name").val()),
			async:false,
			dataType:"json",
			success:function(data){
				if(data){
					flag = true;
				}else{
					flag = false;
				}
			}
		});
		if(!flag){
			return "* 角色名称存在.";
		}
	}
	
	function enNameCheck(){
		var flag = true;
		$.ajax({
			type:"POST",
			url:"${ctx}/sys/role/checkEnname",
			data:"oldEnname="+encodeURIComponent('${role.enname}')+"&enname="+encodeURIComponent($("#enname").val()),
			async:false,
			dataType:"json",
			success:function(data){
				if(data){
					flag = true;
				}else{
					flag = false;
				}
			}
		});
		if(!flag){
			return "* 英文名称存在.";
		}
	}
	
	function save(){
		var success = $("#inputForm").validationEngine("validate");
		if(!success){
			return;
		}
		var treeObj1 = $.fn.zTree.getZTreeObj("menuTree");
		var ids = [], nodes = treeObj1.getCheckedNodes(true);
		for(var i=0; i<nodes.length; i++) {
			ids.push(nodes[i].id);
		}
		$("#menuIds").val(ids);
		var treeObj2 = $.fn.zTree.getZTreeObj("officeTree");
		var ids2 = [], nodes2 = treeObj2.getCheckedNodes(true);
		for(var i=0; i<nodes2.length; i++) {
			ids2.push(nodes2[i].id);
		}
		$("#officeIds").val(ids2);
		var form = document.forms['inputForm'];
		form.submit();
	}	
	//add end
	
	function clickTree(title){
		$('a[title='+title+']').click();
	}
	$("#roleType").change(function(){
		var name = $("#name").val();
		var enname = $("#enname").val();
		location.href="form?name="+name+"&enname="+enname;
	});
</script>

</body>
</html>