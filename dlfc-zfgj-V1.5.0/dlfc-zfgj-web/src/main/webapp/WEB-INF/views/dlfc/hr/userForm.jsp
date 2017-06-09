<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head2.jsp"%>
<title>用户管理</title>
<meta name="decorator" content="themehui" />

</head>
<body>
	<div class="pd-20">
	<legend>
		<%-- ${detail eq 'detail' ? '查看管理员':'添加管理员' } --%>
		${cz eq '1' ? '添加管理员':'编辑管理员' }
	</legend>
	<form:form id="inputForm" modelAttribute="user"
		action="${ctx}/hr/user/save" method="post"
		class="form user_form form-horizontal">
		<form:hidden path="id" />
		<input type="hidden" id="detail" value="${detail }">
		<c:if test="${user.userType!=null&&user.userType!='' }">
		<input type="hidden" name="userType" value="${user.userType }">
		</c:if>
		<c:if test="${user.userType==null||user.userType=='' }">
		<input type="hidden" name="userType" value="2">
		</c:if>	
		<sys:message content="${message}" />
		<div class="responsive">
			<div class="row cl" style="display: none;">
				<label class="form-label col-3">归属公司</label>
				<div class="formControls col-4">
					<sys:treeselect id="company" name="company.id"
						value="${user.company.id}" labelName="company.name"
						labelValue="${user.company.name}" title="公司"
						url="/hr/office/treeData?type=1" cssStyle="width:85%;"
						cssClass="input-text" btnClass="btn-success" />
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>帐号</label>
				<div class="formControls col-4">
					<input id="oldLoginName" name="oldLoginName" type="hidden"
						value="${user.loginName}">
					<form:input path="loginName" htmlEscape="false" 
						class="input-text validate[required,maxSize[50],funcCall[loginName]]" 
						data-prompt-position="centerRight" data-errormessage-value-missing="* 请输入登录名."/>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>姓名</label>
				<div class="formControls col-4">
					<form:input path="name" htmlEscape="false" 
						class="input-text validate[required,maxSize[50]]" 
						data-prompt-position="centerRight" data-errormessage-value-missing="* 请输入姓名."/>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-3">电话</label>
				<div class="formControls col-4">
					<form:input path="mobile" htmlEscape="false"
						class="input-text validate[maxSize[50],custom[phone]]" 
						data-prompt-position="centerRight"/>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-3">邮箱</label>
				<div class="formControls col-4">
					<form:input path="email" htmlEscape="false"
						class="input-text validate[maxSize[50],custom[email]]"
						data-prompt-position="centerRight" />
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>是否启用</label>
				<div class="formControls col-1">
					<span class="select-box"> <form:select path="loginFlag"
							class="select validate[required]" data-prompt-position="centerRight">
							<form:options items="${fns:getDictList('yes_no')}"
								itemLabel="label" itemValue="value" htmlEscape="false" />
						</form:select>
					</span>
				</div>
				<div class="formControls col-6">
					<p class="form_text c-999">"是"代表此帐号允许登录，"否"代表此帐号不允许登录</p>
				</div>
			</div>
			<div class="row cl" >
				<label class="form-label col-3">选择部门</label>
				<div class="formControls col-3">
					<sys:treeselect id="office" name="office.id"
						value="${user.office.id}" labelName="office.name"
						labelValue="${user.office.name}" title="部门"
						url="/hr/office/treeData?type=2" cssClass="input-text"
						cssStyle="width:70%;" btnClass="btn-success"
						notAllowSelectParent="false" />
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-3"><span class="c-red">*</span>选择角色</label>
				<div class="formControls col-4">
					<span class="select-box"> 
						<form:select path="roleIdList" multiple="false"
							class="select validate[required]" data-prompt-position="centerRight">
							<form:options items="${allRoles}"
								itemLabel="name" itemValue="id" htmlEscape="false" />
						</form:select>
					</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-3"><c:if
						test="${empty user.id}">
						<span class="c-red">*</span>
					</c:if>登录密码</label>
				<div class="formControls col-4">
					<input id="newPassword" name="newPassword" type="password" value="" id="newPassword"
						class="input-text ${empty user.id?'validate[required,minSize[3],maxSize[50]]':'validate[minSize[3],maxSize[50]]'}" 
			            data-prompt-position="centerRight" data-errormessage-value-missing="* 请输入密码."/>

					<c:if test="${not empty user.id}">
						<span class="help-inline">若不修改密码，请留空。</span>
					</c:if>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-3"> <c:if
						test="${empty user.id}">
						<span class="c-red">*</span>
					</c:if>确认密码
				</label>
				<div class="formControls col-4">
					<input id="confirmNewPassword" name="confirmNewPassword"
						type="password" value="" 
						class="input-text validate[condRequired[newPassword],equals[newPassword],maxSize[50]]"  
						data-prompt-position="centerRight" data-errormessage-value-missing="* 请输入与上面相同的密码."/>
				</div>
			</div>
			<!-- 避免重复提交 -->
				<input type="hidden" name="${tokenName}" value="${token }"/>
				<input type="hidden" name="tokenName" value="${tokenName}"/>
			<!-- 避免重复提交 -->
			<div class="row cl btn_group">
				<div class="col-9 col-offset-3">
						<shiro:hasPermission name="hr:user:edit">
							<input class="btn btn-primary radius" type="submit" value="保 存" style="display:${detail eq 'detail' ?'none':''}" />
						</shiro:hasPermission> 
						<a onclick="history.go(-1)" class="btn btn-default radius">返 回</a>
				</div>
			</div>
		</div>

		
	</form:form>
	</div>
<%@ include file="/WEB-INF/views/include/foot.jsp"%>
<script type="text/javascript">	
	$(function() {
		var detail = $("#detail").val();
		if(detail == "detail"){
			var txtN = $("input");
			var selectN = $("select");
			$("#officeButton").hide();
			for(i=0;i<txtN.length;i++){
	// 				txtN[i].readOnly=true;
					txtN[i].disabled="disabled";
			}
			for(i=0;i<selectN.length;i++){
	// 			txtN[i].readOnly=true;
			selectN[i].disabled="disabled";
			}	
		}
		$('#inputForm').validationEngine({
			showOneMessage: true,
			maxErrorsPerField:"1",
			addPromptClass: 'formError-text'
		});
	});	
	
	function loginName(){
		var flag = true;
		$.ajax({
			type:"GET",
			url:"${ctx}/hr/user/checkLoginName",
			data:"oldLoginName="+encodeURIComponent('${user.loginName}')+"&loginName="+encodeURIComponent($("#loginName").val()),
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
			return "* 登录名存在.";
		}
		
	}

</script>	
</body>
</html>