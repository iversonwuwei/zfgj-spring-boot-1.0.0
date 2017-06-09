<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head2.jsp"%>
</head>
<body>
	<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 帮助中心<span class="c-gray en">&gt;</span> 意见反馈<a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
		<div class="pd-20">
			<div class="wrap">
				<div class="content">
					<div class=" cl">
						<div class="box  cl">
							<div class="contact-box cl">
								<i class="icos-contact-faq-bg"></i>
								<form id="inputForm" action="${ctx}/feedback/save"
									method="post" class="form-horizontal">
									<input type="hidden" name="type" value="2" /> 
									<input type="hidden" name="name" value="${user.name }" />
									<div class="contact-content form">
										<div class="form-area">
											<div class="form-item mb-10 cl">
												<div class="formControls">
													<p class="contact-user col-offset-3">
														<c:if test="${user.name != null}">
															<i class="icos icos-contact-user"></i>${user.name },
														</c:if>
														<span class="c-black">您好！</span>
													</p>
												</div>
											</div>
											<div class="form-item mb-10 cl">
												<div class="formControls col-offset-3">欢迎留下您宝贵的意见或建议。</div>
											</div>
											<div class="form-item mb-20 cl">
												<label class="form-label col-3">手机号码：</label>
												<div class="formControls col-6">
													<input type="text" name="phone" maxlength="50"
														class="validate[required,custom[mobile]] input-text input-minlarge"
														style="width: 300px;" value="${user.mobile }" /><span
														class="form-text c-999 ml-5">联系方式（必填）</span>
												</div>
											</div>
											<div class="form-item mb-20 cl">
												<label class="form-label col-3">邮箱：</label>
												<div class="formControls col-6">
													<input type="text" name="email" maxlength="50"
														class="validate[required,custom[email]] input-text input-minlarge"
														style="width: 300px;" value="${user.email }" />
												</div>
											</div>
										</div>
										<div class="form-item mb-20 cl">
											<label class="form-label col-3">反馈内容：</label>
											<div class="formControls col-6">
												<textarea name="content" rows="4" maxlength="200"
													class="validate[required]" style="width: 400px;"></textarea>
											</div>
										</div>
										<div class="form-item mb-10 cl">
											<label class="form-label col-3">上传附件：</label>
											<div class="formControls c-999">（注：图片不超过2M，支持格式png、jpeg、gif、bmp）</div>
										</div>
										<div class="form-item cl col-offset-3">
											<div class="formControls">
												<div class="upload-list cl">
													<div id="uploader" class="wu-example fn_left">
														<div class="queueList" id="queueList">
														</div>
														<div class="btns">
															<div id="filePicker"  class="btn btn-primary radius"></div>
														</div>
													</div>
												</div>
											</div>
											<div class="cl"></div>
											<div class="form-item cl col-offset-3">
												<label class="form-label">&nbsp;</label>
												<div class="formControls">
			<!-- 										<input class="btn btn-primary " type="submit" value="提 交" /> -->
													<a onclick="save()" id="save" class="btn btn-primary radius" >提 交</a>
												</div>
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<%@ include file="/WEB-INF/views/include/foot.jsp"%>
			</div>
		</div>
	<script type="text/javascript">
		var file_size = 2;
		var uploader;
		$(function(){
			$(".webuploader-pick").removeClass().addClass("btn-imgload"); 
			uploader.refresh();
			$('#inputForm').validationEngine({
				showOneMessage: true,
				maxErrorsPerField:"1",
// 				addPromptClass: 'formError-text',
				binded:false
			});
		});

		uploader = mulImagesInit('${ctx}','',file_size,'${ctxStatic}${hui}lib/webuploader/0.1.5/Uploader.swf');
		
		function save(){
			var success = $("#inputForm").validationEngine("validate");
			if(success){
				$("#save").removeAttr("onclick");
			}else{
				return;
			}
			var form = document.forms['inputForm'];
			form.submit();
		}
	</script>
</body>
</html>