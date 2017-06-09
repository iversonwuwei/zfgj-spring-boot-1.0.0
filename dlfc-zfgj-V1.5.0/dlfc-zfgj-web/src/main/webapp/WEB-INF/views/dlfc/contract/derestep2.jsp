<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head2.jsp"%>
<link href="${ctxStatic}${theme}/lib/webuploader/0.1.5/webuploader.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctxStatic}${hui}lib/fancybox/jquery.fancybox.css?v=2.1.5" media="screen" />
<title>押金返还</title>
</head>
<body>
	 <nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		租房合同 <span class="c-gray en">&gt;</span>押金分配<a
			class="btn btn-success radius r mr-20"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav> 
	<div class="content">
		<form action="${ctx}/deposit/pdl" method="post" class="form form-horizontal" id="form-admin-add">
			<div class="mt-20">
				<c:if test="${conContract.conDepositDistAgr.status == 6}">
					<div class=" contract_fail mb-20">
						<i class="Hui-iconfont ">&#xe6e0;</i>审核失败：${conContract.conDepositDistAgr.rejectReason}
					</div>
				</c:if>
				<table class="table mb-30 table-border table-bordered ">
					<tbody>
						<tr class="text-l">
							<td colspan="3" class="hoser_info"><div class="item cl">
									<div class="col-3">${createTime}</div>
									<div class="col-6">合同编号：${conContract.no}</div>
									<div class="col-3 text-r">
										<!-- <a class=" btn-link">详情</a> -->
									</div>
								</div></td>
						</tr>
						<tr class="text-c">
							<td><p class="house_name">
									<a href="#">${conContract.houseAddr}</a>
								</p>
								<p class="text-l f-12">${conContract.leaseDomain}</p></td>
							<td><p class="f-16">
									月租金：¥<strong class="c-orange">${conContract.monthlyRent}</strong>/月
								</p>
								<p>
									押金：¥<strong id="deposit" class="c-orange">${conContract.depositAmt}</strong>
								</p></td>
							<td><p>生效：${startTime}</p>
								<p>到期：${endTime}</p></td>
						</tr>
					</tbody>
				</table>
			</div>

			<div class="panel panel-default mb-20">
				<div class="panel-body">
					<div class="responsive">
						<legend>押金返还协议</legend>
						<c:if test="${conContract.conDepositDistAgr.deductAmt != null}">
						<div class="row cl">
							<label class="form-label col-3"><span class="c-red">*</span>出租方扣除：</label>
							<div class="form_text col-4">
								<strong class="c-red">${conContract.conDepositDistAgr.deductAmt}</strong>
								元
							</div>
						</div>
						</c:if>
						<div class="row cl">
							<label class="form-label col-3"><span class="c-red">*</span>返还承租方：</label>
							<div class="form_text col-4">
								<strong id="return_lessee" class="c-red">${conContract.depositAmt - conContract.conDepositDistAgr.deductAmt}</strong>
								元
							</div>
						</div>
						<c:if test="${conContract.conDepositDistAgr.deductAmt != null}">
						<div class="row cl">
							<label class="form-label col-3"><span class="c-red">*</span>收取原因：</label>
							<div class="form_text col-4">
								${conContract.conDepositDistAgr.depositTypeName}</div>
						</div>
						</c:if>
						<c:if test="${ not empty conContract.conDepositDistAgr.description   }">
						<div class="row cl">
							<label class="form-label col-3">补充说明：</label>
							<div class="form_text col-4">${conContract.conDepositDistAgr.description}</div>
						</div>
						</c:if>
							<legend>注意事项</legend>
						<div class="row cl ">
							<div class="col-9 col-offset-2">
								<div class="row cl">
									<div class="formControls col-1-2"></div>
									<div class="content_box col-7 ">
										<div class="text_con">一、乙方应于收到甲方退还的已付剩余房租后
											个工作日内，按照原状返还房屋及其附属物品、设备设施。甲乙双方应对房屋和附属物品、设备设施及水电使用等情况进行验收，结清各自应当承担的费用。如乙一、乙方应于收到甲方退还的已付剩余房租后
											个工作日内，按照原状返还房屋及其附属物品、设备设施。甲乙双方应对房屋和附属物品、设备设施及水电使用等情况进行验收，结清各自应当承担的费用。如乙方不按时退租，每逾期一天，须向甲方支付原合同月租金3%的违约金方不按时退租，每逾期一天，须向甲方支付原合同月租金3%的违约金</div>
										<a href="" class="text_more">展开 <i class="icos icos_more"></i></a>
									</div>
									<c:if test="${conContract.conDepositDistAgr.status == 0}">
										<div class="formControls col-7 pt-20"><a id="reone" class="btn-link pl-20">&lt;&lt;修改</a></div>
									</c:if>
								</div>
							</div>
							<div class="row cl btn_group">
								<div class="col-8 col-offset-4">
									<input name="version" type = "hidden" value="${conContract.conDepositDistAgr.version}">
									<c:if test="${conContract.conDepositDistAgr.status == 0 || conContract.conDepositDistAgr.status ==1 || conContract.conDepositDistAgr.status ==6}">
											<input type = "hidden" name = "id" value = "${cid}">
											<a id="download" class="btn btn-primary radius">确认下载</a>
									</c:if>
									<c:if test="${ conContract.conDepositDistAgr.status ==1 || conContract.conDepositDistAgr.status ==6}">
										<a class="btn btn-primary radius" onclick="openUploadDialog()">上传等待审核</a>
									</c:if>
									<c:if test="${conContract.conDepositDistAgr.status ==0}">
										<a class="btn btn-default disabled"  >上传等待审核</a>
									</c:if>
									<c:if test="${conContract.conDepositDistAgr.status ==5 || conContract.conDepositDistAgr.status ==4}">
										<a class="btn btn-primary radius" onclick="openUploadDialog()">查看上传</a>
									</c:if>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
		<div id ="upload" class="pd-20" style="display:none">
			<form id="uploadDeposit" action="${ctx}/deposit/pul" method="post" class="form form-horizontal">
				<form:hidden path="conContract.id" />
				<form:hidden path="conContract.conDepositDistAgr.id" />
				<input type="hidden" name="infoAttList[0].filePath" id="oneSideFilePath" value="${conContract.infoAttList[0].filePath}"/>
				<input type="hidden" name="infoAttList[0].fileRealName" id="oneSideFileRealName" value="${conContract.infoAttList[0].fileRealName}"/>
				<input type="hidden" name="infoAttList[0].fileName" id="oneSideFileName" value="${conContract.infoAttList[0].fileName}"/>
				<input type="hidden" name="conDepositDistAgr.version" value="${conContract.conDepositDistAgr.version}"/>
				 <legend>上传押金分配协议</legend>	
				<div class="row cl ">
				<div class=" formControls text-c col-3"></div>
					<div class=" formControls text-c col-6">
						<div id="uploader" class="wu-example">
							<!--用来存放文件信息-->
							<div id="fileList" class="fileList">
								<div id="WU_FILE_0" class="file-item thumbnail">
									<a href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/img/${conContract.infoAttList[0].filePath}" class="fancybox"><img src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/img/${conContract.infoAttList[0].filePath}" /></a>
									<div class="info" title=" ${con.infoAttList[0].fileRealName} ">${con.infoAttList[0].fileRealName}</div>
								</div>
							</div>
							<c:choose>
								<c:when test="${conContract.conDepositDistAgr.status == 4 || conContract.conDepositDistAgr.status ==5 }">
								</c:when>
							<c:otherwise>
							<div class="btns">
								<div id="filePicker" style=" float:none;">选择图片</div>
							</div>
							</c:otherwise>
							</c:choose>
						</div>
					</div>	
				</div>
				<div>
					<c:choose>
						<c:when test="${conContract.conDepositDistAgr.status == 4 || conContract.conDepositDistAgr.status ==5 }">
						</c:when>
						<c:otherwise>
						<div class="cl row text-c">
							<a class="btn btn-primary mr-10" onclick="submitUploadForm()">确定</a>
							<a class="btn btn-default" onclick="closeDialog(1)">取消</a>
					</div>
						</c:otherwise>
					</c:choose>
				</div>
			</form>
		</div>		
	</div>
<%@ include file="/WEB-INF/views/include/foot.jsp"%>
	<script type="text/javascript" src="${ctxStatic}${theme}/lib/webuploader/0.1.5/webuploader.js"></script>	
	<script type="text/javascript" src="${ctxStatic}${theme}/lib/webuploader/0.1.5/webuploaderdlfc.js"></script>	
	<script type="text/javascript" src="${ctxStatic}${hui}lib/fancybox/jquery.fancybox.js?v=2.1.5"></script>
	<script type="text/javascript">
		var uploader;
		var refreshTime = 1;
		$(function() {
			$('.fancybox').fancybox();
			var cid = '${cid}';
			var status = '${conContract.conDepositDistAgr.status}'
			var lastFlag = '${lastFlag}';
			if(status == 1 && lastFlag == 1){
				window.location.href = "${ctx}/dl/pdf?id=" + cid+"&cType="+8;
			}
			/* $('.table-sort').dataTable({
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
			}); */
			$("#reone").click(function() {
				window.location.href = "${ctx}/deposit/drone?cid=" + cid;
			});

			$("#download").click(function() {
						confirmDialog();
					});
			
			 uploader = WebUploader.create({
				pick : {
					id : '#filePicker',
					multiple : false
				},
				swf : '${ctxStatic}${theme}lib/webuploader/0.1.5/Uploader.swf',
				server : '${ctx}/deposit/upload?cid=${conContract.id}&fileType=4',//上传的URL
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
		
		function openUploadDialog() {
			_didx = layer.open({
				type: 1, //page层
				area: ['600px', '450px'],
				title: '上传押金分配',
				shade: 0.6, //遮罩透明度
				moveType: 1, //拖拽风格，0是默认，1是传统拖动
				shift: 0, //0-6的动画形式，-1不开启
				content: $('#upload')
			});
			$("#upload").show();
			if (refreshTime == 1) {
				uploader.refresh();
				refreshTime = 2;
			}
		};
		
		function confirmDialog() {
			layer.confirm("点击此按钮，合同内容将永远不能修改，确定要下载合同吗？", function(){
				$("#form-admin-add").submit();
			}, function(){
				//closeDialog();
			});	
		};

		
		function closeDialog(type) {
			layer.close(_didx);
		};
		
		
		
		function submitUploadForm(){
			if ($("#oneSideFilePath").val() == '' || $("#oneSideFileName").val() == '' || $("#oneSideFileRealName").val() == '') {
				layer.alert("请上传图片", {icon : 5});
			}else{
				$("#uploadDeposit").submit();
			}
		}
	</script>
</body>
</html>