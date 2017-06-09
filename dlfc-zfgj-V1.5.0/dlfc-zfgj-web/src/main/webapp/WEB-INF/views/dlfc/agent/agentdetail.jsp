<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head2.jsp"%>
<title>经纪人查看</title>
</head>
<body>
<c:if test="${edit==null}">
<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span><i class="Hui-iconfont"></i> 人事管理 <span class="c-gray en">&gt;</span>
		经纪人管理 <span class="c-gray en">&gt;</span> 查看详情<a
			class="btn btn-success radius r mr-20"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	</c:if>
	<c:if test="${edit=='1'}">
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span><i class="Hui-iconfont"></i> 人事管理 <span class="c-gray en">&gt;</span>
		经纪人管理 <span class="c-gray en">&gt;</span> 编辑<a
			class="btn btn-success radius r mr-20"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	</c:if>
	<div class="pd-20">
		<form action="" method="post" class="form form-horizontal" id="form-admin-add">
			<input type="hidden" id="id" value="${empInfo.id}"/>
			<input type="hidden" id="userId" value="${empInfo.userId}"/>
			<div class="responsive">
				<div class="row cl">
					<label class="form-label col-3">头像：</label>
					<div class="formControls col-3">
						<p>
						<c:if test="${empty avatarInfo}">
				          	未上传头像图片
				        </c:if>
				        <c:if test="${!empty avatarInfo}">
				        <img src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/img/${avatarInfo.filePath}?key=${accessKey}" class="avatar size-XXL">
				        </c:if>
						</p>
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-3">姓名：</label>
					<div class="form_text col-3">${empInfo.sysPerson.name}</div>
				</div>
				<div class="row cl">
					<label class="form-label col-3">电话号码：</label>
					<div class="form_text col-3">${empInfo.usrUser.mobile}</div>
				</div>
				<div class="row cl">
					<label class="form-label col-3">身份证件号码：</label>
					<div class="form_text col-3">${empInfo.sysPerson.idNo}</div>
				</div>
				<div class="row cl">
					<label class="form-label col-3">从业资格证号码：</label>
					<div class="form_text col-2">${empInfo.agtCertLink.certCardNo}</div>
				</div>
				<div class="row cl">
			        <div class="formControls col-3"></div>
			        <div class="form_text col-3">
			        <c:forEach items="${sia}" var="sia" varStatus="vstatus">
			          <p><img src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/img/${sia.filePath}?key=${accessKey}" class="avatar certificate_paper"></p>
			         </c:forEach>
			        </div>
		      	</div>
				<c:choose>
					<c:when test="${edit == '1'}">
						<div class="row cl">
							<label class="form-label col-3">部门：</label>
							<div class="formControl col-3">
					            <sys:treeselect id="office" name="office.id" 
					            	value="${empInfo.officeId}" labelName="office.name" labelValue="${empInfo.sysOffice.name}"
									title="部门" url="/sys/office/treeData?type=2"  cssStyle="width:210px;" cssClass="input-text" notAllowSelectRoot="true"/>
							</div>
						</div>
						<div class="row cl">
							<label class="form-label col-3">角色：</label>
							<div class="formControls col-2">
				        		<form:select class="select" path="empInfo.roleCode" id="roleCode">
				        			<form:options items="${roleList}" itemValue="id" itemLabel="name" />
				        		</form:select>  
					        </div>
						</div>
					</c:when>
					<c:otherwise>
						<div class="row cl">
							<label class="form-label col-3">部门：</label>
							<div class="form_text col-2">${empInfo.sysOffice.name}</div>
						</div>
						<div class="row cl">
							<label class="form-label col-3">角色：</label>
							<div class="form_text col-2">${empInfo.sysRole.name}</div>
						</div>
					</c:otherwise>
				</c:choose>
				<div class="row cl">
			        <div class="form-label col-3">备注：</div>
			        <div class="form_text col-2">
						<textarea id="comment" name="comment" cols="40" rows="4" style="agilen:left">${empInfo.comment}</textarea>
			        </div>
		      	</div>
				<div class="row cl">
					<label class="form-label col-3"></label>
					<div class="formControls col-2">
						<c:if test="${edit == '1'}" >
						<a class="btn btn-primary radius" href="#" onclick="save()">保存</a>
						</c:if>
						<a class="btn btn-primary radius" href="#" onclick="history.go(-1)">返回</a>
					</div>
					
				</div>
			</div>
		</form>
	</div>
	<%@ include file="/WEB-INF/views/include/foot.jsp"%>
	<script type="text/javascript">
		function save() {
			var officeId = $('#officeId').val();
			var id = $('#id').val();
			var userId = $('#userId').val();
			var roleId = $("#roleCode").find("option:selected").val(); 
			var comment = $('#comment').val();
			$.ajax({
				type : "GET",
				url : '${ctx}/agt/edit',
				data : {
					oId : officeId,
					rId : roleId,
					id : id,
					userId : userId,
					comment: comment
				},
				dataType : 'json',
				cache: false,
				async : false,
				success : function(data) {
					layer.msg("保存成功！");
					location.href = "${ctx}/agt/onlinelist";
				},
				error : function() {
					layer.msg("保存失败");
				}
			});
		}
	</script>
</body>
</html>