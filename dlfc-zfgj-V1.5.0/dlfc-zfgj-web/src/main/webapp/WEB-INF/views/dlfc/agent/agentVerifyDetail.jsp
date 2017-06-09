<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<!DOCTYPE HTML>
<html>
<head>

<%@ include file="/WEB-INF/views/include/head2.jsp"%>
<title>经纪人审核</title>
</head>
<body>
<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span><i class="Hui-iconfont"></i> 人事管理 <span class="c-gray en">&gt;</span>
		经纪人审核 <span class="c-gray en">&gt;</span><c:if test="${re != '1'}">登记</c:if><c:if test="${re == '1'}">拒绝</c:if>
		<a class="btn btn-success radius r mr-20"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="pd-20">
		<legend>经纪人登记</legend>
		<form action="" method="post" class="form form-horizontal" id="div">
		      <div class="row cl">
		        <label class="form-label col-3">头像：</label>
		        <div class="formControls col-3">
		        <input type="hidden" id="id" value="${aei.id}"/>
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
		        <div class="form_text col-3">${aei.sysPerson.name }</div>
		      </div>
		      <div class="row cl">
		        <label class="form-label col-3">电话：</label>
		        <div class="form_text col-3">${aei.usrUser.mobile }</div>
		      </div>
		      <div class="row cl">
		        <label class="form-label col-3">身份证件：</label>
		        <div class="form_text col-3">${aei.sysPerson.idNo }</div>
		      </div>
		      <div class="row cl">
		        <label class="form-label col-3">从业资格证：</label>
		        <div class="form_text col-2">${aei.agtCertLink.certCardNo }</div>
		      </div>
		      <div class="row cl">
		        <div class="formControls col-3"></div>
		        <div class="form_text col-3">
		        <c:forEach items="${sia}" var="sia" varStatus="vstatus">
		          <p><img src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/img/${sia.filePath}?key=${accessKey}" class="avatar certificate_paper"></p>
		         </c:forEach>
		        </div>
		      </div>
		      <c:if test="${re == '1'}">
		      <div class="row cl">
		        <label class="form-label col-3">拒绝原因：</label>
		        <div class="form_text col-3">
		            <textarea placeholder="说点什么..." class="textarea validate[required,maxSize[300]]" rows="" cols="" id="aa" name="aa" ></textarea>
		        </div>
		        <div class="Validform_checktip"></div>
		      </div>
		      </c:if>
		      <c:if test="${re != '1'}">
		      <div class="row cl">
				<label class="form-label col-3">部门：</label>
				<div class="formControl l">
		            <sys:treeselect id="office" name="office.id" value="${aei.officeId}" labelName="office.name" labelValue=""
						title="部门" url="/sys/office/treeData?type=2"  cssStyle="width:100px;" cssClass="input-text validate[required]" notAllowSelectRoot="true" allowInput="true"/>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-3">角色：</label>
				<div class="formControls col-2">
				<select class="select validate[required]" name="roleCode" id="roleCode" >
					<c:forEach items="${roleList}" var="id">
						<option value="${id.id}">${id.name}</option>
					</c:forEach>
				</select>
		        </div>
			</div> 
			</c:if>
		      <div class="row cl btn_group">
		        <div class="col-9 col-offset-3"> <input class="btn btn-primary radius" id="saveButton" value="确定"/> </div>
		      </div>
		    </form>
	</div>
	<%@ include file="/WEB-INF/views/include/foot.jsp"%>
	<script type="text/javascript">
	
		$(function(){
			$('#div').validationEngine('attach', { binded : false,showOneMessage: true,maxErrorsPerField:1,addPromptClass: 'formError-noArrow formError-text',
				promptPosition: 'centerRight:50,0'});
			$('#saveButton').click(function(){
			    var success = jQuery('#div').validationEngine('validate');
				if(success){
					$('#saveButton').unbind("click");
					save();
				}
			});
	
		});
	
		function save() {
			$globalLayer=layer.load(10,{time: 10*1000});
			var officeId = $('#officeId').val();
			var id = $('#id').val();
			var roleId = $("#roleCode").find("option:selected").val(); 
			var re = ${re};
			var ref = $('#aa').val();
			$.ajax({
				type : "POST",
				url : '${ctx}/agt/vedit',
				data : {
					oId : officeId,
					rId : roleId,
					 id : id,
					 re : re,
					reF : ref
				},
				dataType : 'json',
				cache : false,
				success : function(data) {
					if($globalLayer)
					{
						layer.close($globalLayer);
					}
					if (1 == data.success) {
						window.location.href = "${ctx}/agt/agentlistVerify";
					} else {
						
						layer.alert(data.message);
					}
				},
				error : function() {
					layer.alert("网络异常，请稍后重试！");
				}
			});
		}
	</script>
</body>
</html>