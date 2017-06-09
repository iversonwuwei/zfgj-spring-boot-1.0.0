<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head2.jsp"%>
<link href="${ctxStatic}${theme}/lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctxStatic}${hui}lib/fancybox/jquery.fancybox.css?v=2.1.5" media="screen" />
<title>添加管理员</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		租房合同  <span class="c-gray en">&gt;</span> 解约协议<a
			class="btn btn-success radius r mr-20"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="pd-20">
	<form action="${ctx}/con/dwen?id=${contract.conDissAgr.id}"
		method="post" class="form form-horizontal" id="dissAgr">
		<input name="version" type="hidden"
			value="${contract.conDissAgr.version}">
		<div class="mt-20">
			<c:if test="${contract.conDissAgr.status == 6}">
				<div class=" contract_fail mb-20">
					<i class="Hui-iconfont ">&#xe6e0;</i>审核失败：${contract.conDissAgr.rejectReason}
				</div>
			</c:if>

			<table class="table mb-30 table-border table-bordered ">
				<tbody>
					<tr class="text-l">
						<td colspan="3" class="hoser_info"><div class="item cl">
								<div class="col-3">
									<fmt:formatDate value="${contract.createTime}"
										pattern="yyyy-MM-dd" />
								</div>
								<div class="col-4">${contract.no}</div>
							</div></td>
					</tr>
					<tr class="text-c">
						<td height="64"><p class="house_name">
								<a href="${ctx}/house/see?hid=${contract.hid}">${contract.houseAddr}</a>
							</p> <c:if test="${contract.rentalMode == 2}">
								<p class="text-l f-12">${contract.leaseDomain}</p>
							</c:if> <c:if test="${contract.rentalMode == 2}">
								<p class="text-l f-12">整租</p>
							</c:if></td>
						<td><p class="f-16">
								月租金：¥<strong class="c-orange">${contract.monthlyRent}</strong>/月
							</p>
							<p>
								押金：¥<strong class="c-orange">${contract.depositAmt}</strong>
							</p></td>
						<td><p>
								合同生效：
								<fmt:formatDate value="${contract.startTime}"
									pattern="yyyy-MM-dd " />
							</p>
							<p>
								合同终止：
								<fmt:formatDate value="${contract.endTime}"
									pattern="yyyy-MM-dd " />
							</p></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="panel panel-default mb-20">
			<div class="panel-header">
				<div class="item  cl">
					<div class="col-3">
						合同解除日期：
						<fmt:formatDate value="${contract.conDissAgr.effectiveTime}"
							pattern="yyyy-MM-dd " />
					</div>
					<c:if test="${contract.conDissAgr.reasonName !=null && contract.conDissAgr.reasonName!='' }">
					<div class="col-4">解除原因：${contract.conDissAgr.reasonName}</div>
					</c:if>
				</div>
			</div>
			<div class="panel-body">
				<div class="responsive">

					<c:if test="${contract.conDissAgr.retrunRentAmt !=null}">
						<div class="row cl">
							<label class="form-label col-3">剩余租金：</label>
							<div class="form_text col-4">
								房主向房客支付 <strong class="c-red">${contract.conDissAgr.retrunRentAmt}</strong>
								元
							</div>
						</div>
					</c:if>
						<c:if test="${contract.conDissAgr.paymentAmt !=null}">
							<div class="row cl">
								<label class="form-label col-3">违约补偿金 ：</label>
								 
										<div class="form_text col-4">
											${contract.conDissAgr.paymentPersonTypeName} 支付<strong class="c-red">${contract.conDissAgr.paymentAmt}</strong>
											元
										</div>
							</div>
						</c:if>
					<c:if test="${contract.conDissAgr.retrunRentAmt != null}">
					<div class="row cl">
						<label class="form-label col-3">押金 ：</label>
						<div class="form_text col-4">
							房主扣除 <strong class="c-red">${contract.conDissAgr.retrunRentAmt}</strong>元
							返还房客 <strong class="c-red">${DepositAmt}</strong> 元
						</div>
					</div>
					</c:if>
					<c:choose>
						<c:when
							test="${contract.conDissAgr.description == null || contract.conDissAgr.description ==''}">
						</c:when>
						<c:otherwise>
							<div class="row cl">
								<label class="form-label col-3">补充说明：</label>
								<div class="form_text col-4">
									${contract.conDissAgr.description}</div>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>

		<legend>注意事项</legend>
		<div class="row cl">
			<div class="content_box col-1-1 " style="margin-left:0;">
				<div class="text_con">一、乙方应于收到甲方退还的已付剩余房租后
					个工作日内，按照原状返还房屋及其附属物品、设备设施。甲乙双方应对房屋和附属物品、设备设施及水电使用等情况进行验收，结清各自应当承担的费用。如乙一、乙方应于收到甲方退还的已付剩余房租后
					个工作日内，按照原状返还房屋及其附属物品、设备设施。甲乙双方应对房屋和附属物品、设备设施及水电使用等情况进行验收，结清各自应当承担的费用。如乙方不按时退租，每逾期一天，须向甲方支付原合同月租金3%的违约金方不按时退租，每逾期一天，须向甲方支付原合同月租金3%的违约金</div>
				<a href="" class="text_more">展开 <i class="icos icos_more"></i></a>
			</div>
		</div>
		<legend>历史记录</legend>
		<c:forEach items="${dissLogs}" var="disslog">
		<c:if test="${disslog.rejectReason != null}">
		<div class="row cl">
			<div class="distribution-record">
				<div class="record-list">
					<p class="record-tips">
						<i class="icos ico-record mr-10"></i><span class="mr-20"><fmt:formatDate value="${disslog.createTime}" pattern="yyyy-MM-dd " /> </span><span>[已拒绝:${disslog.statusName}]</span>
					</p>
					<div class="form-item cl">
						<div class="formControls  fn-clear">
							<p class="form_text">拒绝原因：</p> <span class="form_text">${disslog.rejectReason}</span>
						</div>
					</div>
				 </div>
				</div>
		</div>
		</c:if>
		<c:if test="${disslog.rejectReason == null}">
			<div class="row cl">
				<div class="distribution-record">
					<div class="record-list">
						<p class="record-tips">
							<i class="icos ico-record mr-10"></i><span class="mr-20"><fmt:formatDate value="${disslog.createTime}" pattern="yyyy-MM-dd " /></span><span>[${disslog.statusName}:<c:choose><c:when test="${disslog.status == 0}">房客发起解约协议</c:when><c:when test="${disslog.status == 1}">房主发起解约协议</c:when></c:choose>]</span>
						</p>
						<div class="form-item cl">
							<div class="formControls  fn-clear">
								<p class="form_text">合同解除日期：</p> <span class="form_text"><fmt:formatDate value="${disslog.effectiveTime}" pattern="yyyy-MM-dd " /></span>
							</div>
						</div>
						<c:if test="${disslog.reasonName != null && disslog.reasonName !=''}">
						<div class="form-item cl">
							<div class="formControls  fn-clear">
								<p class="form_text">解除原因：</p> <span class="form_text">${disslog.reasonName}</span>
							</div>
						</div>
						</c:if>
						<c:choose>
						<c:when test="${disslog.retrunRentAmt != null}">
						<div class="form-item cl">
							<div class="formControls  fn-clear">
								<p class="form_text">房主需返还剩余租金：</p><span class="form_text"><strong class="c-red">${disslog.retrunRentAmt}</strong>元</span>
							</div>
						</div>
						</c:when>
						<c:when test="${disslog.paymentAmt != null}">
						<div class="form-item cl">
							<div class="formControls  fn-clear">
								<p class="form_text">违约补偿金：</p> <span class="form_text">${disslog.paymentPersonTypeName}支付<strong class="c-red">${disslog.paymentAmt}</strong>元</span>
							</div>
						</div>
						</c:when>
						<c:when test="${disslog.description != null && disslog.description != ''}">
						<div class="form-item cl">
							<div class="formControls  fn-clear">
								<p class="form_text">补充说明：</p> <span class="form_text">${disslog.description}</span>
							</div>
						</div>
						</c:when>
						</c:choose>
					</div>
				</div>
			</div>
		</c:if>
			</c:forEach>
		</div>
		
		<%-- <c:choose>
			<c:when test="${contract.conDissAgr.status == 0}">
				<div class="formControls col-7 pt-20 ">
					<a href="${ctx}/con/back?id=${contract.conDissAgr.id}"
						class="btn-link pl-20"><i class="Hui-iconfont">&#xe6d4;&#xe6d4;</i>修改</a>
				</div>
				<div class="row cl btn_group">
					<div class="col-8 col-offset-4">
						<a class="btn btn-primary radius" onclick="dwn()">确认下载</a> <a
							class="btn btn-default disabled">上传等待审核</a>
					</div>
				</div>
			</c:when>
			<c:when test="${contract.conDissAgr.status == 1}">
				<div class="row cl btn_group">
					<div class="col-8 col-offset-4">
						<a class="btn btn-primary radius" onclick="dwn()">确认下载</a> <a
							class="btn btn-primary radius" onclick="openUploadDialog()">上传等待审核</a>
					</div>
				</div>
			</c:when>
			<c:when test="${contract.conDissAgr.status == 6}">
				<div class="col-8 col-offset-4">
					<a class="btn btn-primary radius" onclick="dwn()">确认下载</a> <a
						class="btn btn-primary radius" onclick="openUploadDialog()">查看上传</a>
				</div>
			</c:when>
			<c:when test="${contract.conDissAgr.status == 3 || contract.conDissAgr.status == 5}">
				<div class="col-8 col-offset-4">
					<a class="btn btn-primary radius" onclick="openUploadDialog()">查看上传</a>
				</div>
			</c:when>
		</c:choose> --%>
	</form>
	</div>
	<div id="uploadDiv" class="pd-20" style="display: none">
		<form action="${ctx}/con/upen?id=${contract.conDissAgr.id}"
			method="post" class="form form-horizontal" id="uploadContract">
			<form:hidden path="contract.conDissAgr.id" />
			<form:hidden path="contract.conDissAgr.version" />
			<input type="hidden" name="infoAttList[0].filePath"
				id="oneSideFilePath" value="${contract.infoAttList[0].filePath}" />
			<input type="hidden" name="infoAttList[0].fileRealName"
				id="oneSideFileRealName"
				value="${contract.infoAttList[0].fileRealName}" /> <input
				type="hidden" name="infoAttList[0].fileName" id="oneSideFileName"
				value="${contract.infoAttList[0].fileName}" />
			<legend>上传解约协议</legend>			
			<div class="row cl ">
			<div class=" formControls text-c col-3"></div>
				<div class=" formControls text-c col-6">
					<div id="uploader" class="wu-example">
						<!--用来存放文件信息-->
						<div id="fileList" class="fileList">
							 <c:if
								test="${!empty contract.infoAttList[0].filePath && !empty contract.infoAttList[0].fileRealName && !empty contract.infoAttList[0].fileName}">
								<div id="WU_FILE_0" class="file-item thumbnail">
									<a href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/img/${contract.infoAttList[0].filePath}" class="fancybox" class="fancybox"><img src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/img/${contract.infoAttList[0].filePath}" /></a>
									<div class="info"
										title=" ${contract.infoAttList[0].fileRealName} ">${contract.infoAttList[0].fileRealName}</div>
								</div>
							 </c:if>
						</div>
						<c:choose>
							<c:when test="${contract.conDissAgr.status ==3 || contract.conDissAgr.status ==5}">
							</c:when>
							<c:otherwise>
							<div class="btns">
								<div id="filePicker" style="float: none;">选择图片</div>
							</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
			<c:choose>
				<c:when test="${contract.conDissAgr.status ==3 || contract.conDissAgr.status ==5}">
				</c:when>
				<c:otherwise>
				<div>
					<div class="cl mt-10 text-c">
						<a class="btn btn-primary mr-10" onclick="submitUploadForm()">确定</a>
						<a class="btn btn-default" onclick="closeDialog()">取消</a>
					</div>
				</div>
				</c:otherwise>
			</c:choose>
		</form>
	</div>

	<%@ include file="/WEB-INF/views/include/foot.jsp"%>
	<script type="text/javascript" src="${ctxStatic}${theme}/lib/webuploader/0.1.5/webuploader.js"></script>	
	<script type="text/javascript" src="${ctxStatic}${theme}/lib/webuploader/0.1.5/webuploaderdlfc.js"></script>	
	<script type="text/javascript" src="${ctxStatic}${hui}lib/fancybox/jquery.fancybox.js?v=2.1.5"></script>
	<script type="text/javascript">
	var _didxx;
	var uploader;
	var refreshTime = 1;
$(function() {
	$('.fancybox').fancybox();
	var status = '${contract.conDissAgr.status}'
		var id = '${contract.id}';
		var flag= '${flag}'
		if(status == 1 && flag == 1){
			window.location.href = "${ctx}/dl/pdf?id=" +id+"&cType="+7;
		}
		
		 uploader = WebUploader.create({
			pick : {
				id : '#filePicker',
				multiple : false
			},
			swf : '${ctxStatic}${theme}/lib/webuploader/0.1.5/Uploader.swf',
			server : '${ctx}/con/upload?id=${contract.id}&fileType=3',//上传的URL
			accept : {
				title : '请上传图片格式',
				extensions : 'jpg,jpeg,bmp,png'
			},
			auto : true,
			fileNumLimit : 1,
			fileSingleSizeLimit : 2097152,
			compress : false
		}); 

		uploader.on('uploadSuccess', function(file) {
			//后台验证失败（文件大小 文件类型验证）
			if (arguments[1].success == 0) {
				layer.alert(arguments[1].message);
			} else if (arguments[1].success == 1) {//上传临时文件夹成功后的操作写在这里 返回filepath和filename
				//layer.alert(arguments[1].data.filepath);	
				$("#oneSideFilePath").val(arguments[1].data.filePath);
				$("#oneSideFileName").val(arguments[1].data.fileName);
				$("#oneSideFileRealName").val(arguments[1].data.fileRealName);
			}
		});
		initUploader(uploader, 300, 300, "#fileList");
		
});
function dwn(){	 
	var status='${contract.conDissAgr.status}'
	if(status == 0){
	layer.confirm("点击此按钮，内容将不能修改，确定要下载合同吗？", function(){
		 $("#dissAgr").submit();
	});
	}else{
	 $("#dissAgr").submit();
	}
}
function closeDialog() {
	layer.close(_didxx);
}
function openUploadDialog() {
	_didxx = layer.open({
		type: 1, //page层
		area: ['600px', '450px'],
		title: '上传解约协议',
		shade: 0.6, //遮罩透明度
		moveType: 1, //拖拽风格，0是默认，1是传统拖动
		shift: 0, //0-6的动画形式，-1不开启
		content: $('#uploadDiv')
	});
	$("#uploadDiv").show();
	if (refreshTime == 1) {
		uploader.refresh();
		refreshTime = 2;
	}
};
 
function submitUploadForm() {
	if ($("#oneSideFilePath").val() == '' || $("#oneSideFileName").val() == '' || $("#oneSideFileRealName").val() == '') {
		layer.alert("请上传图片", {icon : 5});
	}else{
	$("#uploadContract").submit();
	}
}
 

</script>
</body>
</html>