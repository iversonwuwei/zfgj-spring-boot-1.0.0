<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="${ctxStatic}${theme }lib/html5.js"></script>
<script type="text/javascript" src="${ctxStatic}${theme }lib/respond.min.js"></script>
<script type="text/javascript" src="${ctxStatic}${theme }lib/PIE-2.0beta1/PIE_IE678.js"></script>
<![endif]-->
<link
	href="${pageContext.request.contextPath}/static/hui/css/H-ui.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/static/hui/css/H-ui.admin.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/static/hui/css/style.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/static/hui/lib/icheck/icheck.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/static/hui/lib/Hui-iconfont/1.0.1/iconfont.css"
	rel="stylesheet" type="text/css" />
<link href="${ctxStatic}/hui/lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
<link href="${ctxStatic}/hui/lib/webuploader/0.1.5/mulImages.css" rel="stylesheet" type="text/css" />
<link href="${ctxStatic}/hui/lib/jQuery-Validation-Engine-master/css/validationEngine.jquery.css" type="text/css" rel="stylesheet" />
<link href="${ctxStatic}/hui/lib/jQuery-Validation-Engine-master/css/validationEngine_modify.css" type="text/css" rel="stylesheet" />
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>公司设置</title>
<style type="text/css">
	.up-company-logo .webuploader-pick {
		margin-left:28px;
		margin-top:45px;
	}
	.up-company-logo .webuploader-pick + div{
		margin-left:25px;
		margin-top:40px;
	}
</style>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span><i class="Hui-iconfont"></i> 公司管理 <span class="c-gray en">&gt;</span>
		公司设置 <span class="c-gray en"></span><a
			class="btn btn-success radius r mr-20"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="pd-20">
	  	<div class="pd-20">
		  <input type="hidden" name="message" id="message" value="${message}">
		    <div class="responsive" >
		      <div class="row cl">
		        <div class="company_head">
		          <div class="company_info">
		          	<p class="company_img"><img src="${agtCompInfo.filePath}" width="180" height="79" alt=""></p>
		           	<p class="mb-10 mt-5"><a href="#" onclick="openLogo()" class="btn-link">修改logo</a></p>
		          </div>
		          <div class="company_info">
		            <p class="company_til">${agtCompInfo.fullName}</p>
		            <p><strong>公司简称：${agtCompInfo.name}</strong></p>
		            <div class="cl">
		              <p class="mr-30 ">佣金：${agtCompInfo.commissionRate}倍月租金</p>
		            </div>
		          </div>
		          <div class="company_detail ml-10">
		          <p class="star_level">综合评分： <i class="ico_star_level star_five"></i></p>
		            <div class="cl">
		            <p class="mr-30 ">发布房源：${houseTotal}</p>
		              <p class="mr-30 ">在职经纪人： ${agentTotal}</p>
		              <p class="mr-30 ">签订合同：  ${contractTotal}</p>
		            </div>
		          </div>
		        </div>
		      </div>
		    </div>
		    <div class="line mb-20"></div>
		    <div class="">
		      <a href="#" onclick="openCommissionRate()" class="btn btn-primary size-XL">佣金设置</a>
		      <a href="#" onclick="changePassword()" class="btn btn-primary size-XL">修改密码</a>
		      <c:if test="${agtCompInfo.echapterEnabled != 1}">
		      		<a href="#" onclick="openChapter()" class="btn btn-primary size-XL">上传电子章</a>
		      </c:if>
		      <c:if test="${!empty agtCompInfo.echapterPath}">
	     			<a href="#" onclick="window.location.href='${ctx}/comp/downPleBk?id=${agtCompInfo.id}';" class="btn btn-primary size-XL">下载承诺书</a>
	     	  </c:if>
		    </div>
	  	</div>

		<div id="setCommissionRate" style="display: none;">
			<div class="pd-20">
				<div class="responsive">
					<div class="row cl mb-20 ">
						<label class="form-label text-r pr-10 col-2"></label>
						<div class="formControls col-9 plus-tag-box">
							<div class="plus-tag tagbtn clearfix checked" id="myTags"></div>
							<div class="plus-tag-add"></div>
						</div>
					</div>
		
					<div>
						<span>佣金说明：</span> <span>佣金单位为月租金的倍数，如设置为0.8，当房屋月租金为1000元时，佣金为800元（1000*0.8）。</span>
					</div>
					<div></div>
					<form id="inputForm" name="inputForm" action="${ctx}/comp/update" method="post" class="form form-horizontal">
					<div class="row cl">
					<div class="formControls col-6"><input type="hidden" name="id" id="id" value="${agtCompInfo.id }">
						<input type="text" name="commissionRate" id="commissionRate" class="validate[required,custom[number],min[0]] input-text" placeholder="请输入佣金费率" 
						data-errormessage="* 请输入非负数字." value="${agtCompInfo.commissionRate }"></div>
						<div class="form_text col-3">倍租金</div>
					</div>
						
						<div class="row cl btn_group">
							<div class="col-9 col-offset-3">
								<input class="btn btn-primary radius ml-15" type="submit" value="提交" /> 
								<input class="btn btn-primary radius ml-15" type="button" value="返回" onclick="closeCommissionRate()" />
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div id="changePassword" style="display: none;">
			<div class="pd-20">
				<div class="row cl mb-20 ">
					<label class="form-label text-r pr-10 col-2"></label>
					<div class="formControls col-9 plus-tag-box">
						<div class="plus-tag tagbtn clearfix checked" id="myTags"></div>
						<div class="plus-tag-add"></div>
					</div>
				</div>
				<div class="line"></div>
				<form id="myForm" name="myForm" action="${ctx}/comp/changePassword" method="post" class="form form-horizontal">
				    <div class="responsive">
				    	<div class="row cl">
							<label class="form-label col-3"><span class="c-red">*</span>当前密码：</label>
							<div class="formControls col-5">
								<input type="password" id= "password" name="password" placeholder="密码" class="input-text validate[required,funcCall[validatePassword]]"
									data-prompt-position="centerRight"
									data-errormessage-value-missing="* 请输入当前密码." >
							</div>
							<div class="col-4"> </div>
						</div>
					    <div class="row cl">
							<label class="form-label col-3"><span class="c-red">*</span>新密码：</label>
							<div class="formControls col-5">
								<input type="password" id= "newpwd" onKeyUp="pwStrength(this.value)" onBlur="pwStrength(this.value)" name="newpwd" placeholder="至少8个字符，区分大小写" class="input-text validate[required,minSize[8],maxSize[18],funcCall[notEqualTo],custom[onlyLetterNumber]]" 
									data-prompt-position="centerRight"
									data-errormessage-value-missing="* 请输入新密码.">
									<p class="form_text"> &nbsp;<i class="security_level level_low" id="strength_L"></i></p>
							</div>
							<div class="col-4"> </div>
						</div>
						<div class="row cl">
							<label class="form-label col-3"><span class="c-red">*</span>再次确认新密码：</label>
							<div class="formControls col-5">
								<input type="password" id= "newpwd1" name="newpwd1" placeholder="确认新密码" class="input-text validate[equals[newpwd]]"
									data-prompt-position="centerRight"
									data-errormessage-pattern-mismatch="* 密码不一致.">
							</div>
							<div class="col-4"> </div>
						</div>
				      	<div class="row cl btn_group">
				        	<div class="col-9 col-offset-3">
								<input class="btn btn-primary radius ml-15" type="button" value="提交" onclick="save()"/> 
								<input class="btn btn-primary radius ml-15" type="button" value="返回" onclick="closeChangePassword()" />
				         	</div>
				      	</div>
				    </div>
				</form>
			</div>
		</div>
		
		<div id="changeLogo" style="display: none;">
			<div class="pd-10">
				<div class="row cl mb-20 ">
					<label class="form-label text-r pr-10 col-2"></label>
					<div class="formControls col-9 plus-tag-box">
						<div class="plus-tag tagbtn clearfix checked" id="myTags"></div>
						<div class="plus-tag-add"></div>
					</div>
				</div>
			
				<div id="uploader" class="wu-example" style="width:120px; margin:0 auto;">
					<div class="queueList up-company-logo" id="queueList" style="border: 1px solid #ddd;width: 110px;height: 110px;">
						<div class="btns" id="addBtn">
								<div id="filePicker"></div>
						</div>
					</div> 
				</div>
				<div class="item border_none fn_clear" >	
					<div class="form_list col_1_1 fn_clear">
					<p class="form-name fn_left">&nbsp;</p>
					<p class="form-text c-red f-12">请上传尺寸为180*80的公司logo图片</p>
					</div>	
				</div>
				<form id="logoForm" name="logoForm" action="${ctx}/comp/saveLogo" method="post" class="form form-horizontal">
					<input type="hidden" name="id" id="id" value="${agtCompInfo.id }">
					<input type="hidden" name="img1Id" id="img1Id" value="${agtCompInfo.img1Id}">
					<input type="hidden" name="logo" id="logo" value="">
				    <div class="responsive">
				      	<div class="row cl btn_group">
				        	<div class="text-c">
								<input class="btn btn-primary radius ml-15" type="button" value="提交" onclick="saveLogo()"/> 
								<input class="btn btn-primary radius ml-15" type="button" value="返回" onclick="closeChangeLogo()" />
				         	</div>
				      	</div>
				    </div>
				</form>
			</div>
		</div>
		
		<div id="changeChapter" style="display: none;">
			<div class="pd-10">
				<div class="row cl mb-20 ">
					<label class="form-label text-r pr-10 col-2"></label>
					<div class="formControls col-9 plus-tag-box">
						<div class="plus-tag tagbtn clearfix checked" id="myTags"></div>
						<div class="plus-tag-add"></div>
					</div>
				</div>
			
				<div id="uploader1" class="wu-example" style="width:120px; margin:0 auto;">
					<div class="queueList1 up-company-logo" id="queueList1" style="border: 1px solid #ddd;width: 110px;height: 110px;">
						<div class="btns" id="addBtn">
								<div id="filePicker1"></div>
						</div>
					</div> 
				</div>
				<div class="item border_none fn_clear" >	
					<div class="form_list col_1_1 fn_clear">
					<p class="form-name fn_left">&nbsp;</p>
					<p class="form-text c-red f-12">注：请上传透明电子章，格式为PNG，最大2M</p>
					</div>	
				</div>
				<form id="chapterForm" name="chapterForm" action="${ctx}/comp/saveChapter" method="post" class="form form-horizontal">
					<input type="hidden" name="id" id="id" value="${agtCompInfo.id}">
					<input type="hidden" name="chapter" id="chapter" value="">
				    <div class="responsive">
				      	<div class="row cl btn_group">
				        	<div class="text-c">
								<input class="btn btn-primary radius ml-15" type="button" value="提交" onclick="saveChapter()"/> 
								<input class="btn btn-primary radius ml-15" type="button" value="返回" onclick="closeChangeChapter()" />
				         	</div>
				      	</div>
				    </div>
				</form>
			</div>
		</div>
	</div>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/static/hui/lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/static/hui/lib/icheck/jquery.icheck.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/static/hui/lib/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/static/hui/lib/layer/1.9.3/layer.js"></script>
	<script src="${ctxStatic}/hui/lib/jQuery-Validation-Engine-master/js/jquery.validationEngine.js" type="text/javascript"></script>
	<script src="${ctxStatic}/hui/lib/jQuery-Validation-Engine-master/js/languages/jquery.validationEngine-zh_CN.js" type="text/javascript"></script>
	<%@ include file="/WEB-INF/views/include/foot.jsp"%>
	<script type="text/javascript">
	
		$(function(){
// 			var message = $("#message").val();
// 			if(message != null && message != ''){
// 				layer.msg(message);
// 			}
			
			$('#inputForm').validationEngine({
				showOneMessage: true,
				maxErrorsPerField:"1",
// 				addPromptClass: 'formError-text',
				binded:false
			});
			
			$('#myForm').validationEngine({
				showOneMessage: true,
				maxErrorsPerField:"1",
				addPromptClass: 'formError-text',
				binded:false
			});
		
		});
	
		function openCommissionRate() {
			//弹出框
			_commissionRate = layer.open({
				type : 1, //page层
				area : [ '400px', '350px' ],
				title : '佣金设置',
				shade : 0.6, //遮罩透明度
				moveType : 1, //拖拽风格，0是默认，1是传统拖动
				shift : 0, //0-6的动画形式，-1不开启
				content : $('#setCommissionRate')
			});
		}
		
		function closeCommissionRate() {
			layer.close(_commissionRate);
		};
		function changePassword() {
			//弹出框
			_changePassword = layer.open({
				type : 1, //page层
				area : [ '600px', '400px' ],
				title : '密码修改',
				shade : 0.6, //遮罩透明度
				moveType : 1, //拖拽风格，0是默认，1是传统拖动
				shift : 0, //0-6的动画形式，-1不开启
				content : $('#changePassword')
			});
		}
		function closeChangePassword() {
			layer.close(_changePassword);
		};
		
		function openLogo() {
			//弹出框
			_changeLogo = layer.open({
				type : 1, //page层
				area : [ '400px', '400px' ],
				title : '修改图片',
				shade : 0.6, //遮罩透明度
				moveType : 1, //拖拽风格，0是默认，1是传统拖动
				shift : 0, //0-6的动画形式，-1不开启
				content : $('#changeLogo')
			});
			ul.refresh();
		}
		
		function openChapter() {
			//弹出框
			_changeChapter = layer.open({
				type : 1, //page层
				area : [ '400px', '400px' ],
				title : '修改图片',
				shade : 0.6, //遮罩透明度
				moveType : 1, //拖拽风格，0是默认，1是传统拖动
				shift : 0, //0-6的动画形式，-1不开启
				content : $('#changeChapter')
			});
			ul1.refresh();
		}
		
		function closeChangeLogo() {
			layer.close(_changeLogo);
		};
		
		function closeChangeChapter(){
			layer.close(_changeChapter);
		}
		
		function save(){
			var success = $("#myForm").validationEngine("validate");
			if(!success){
				return;
			}
			
			doPassChange();
		}
		function validatePassword(){
			if(!validatePwd()){
				return "* 密码输入错误.";
			}
		}
		
		function doPassChange() {
			var newpwd = $("#newpwd").val();
			var newpwd1 = $("#newpwd1").val();
			var password = $("#password").val();
			$.ajax({
				type : "POST",
				url : 'changePassword',
				data : {
					newpwd : newpwd,
					newpwd1 : newpwd1,
					password : password
				},
				dataType : 'json',
				cache : false,
				success : function(data) {
					if (1 == data.success) {
						layer.msg(data.message, {icon: 1});
						setTimeout( function(){
							window.location.href='select';
							}, 1000 );
					} else {
						layer.alert(data.message);
					}
				},
				error : function() {
					layer.alert("网络异常，请稍后重试！")

				}
			}); 
		}
		
		function notEqualTo(field, rules, i, options) {
			if (field.val() == $("#password").val())
				return "* 新密码不能与当前密码相同";

		}
		
		function validatePwd(){
			var flag = false;
			var password = $("#password").val();
			var newpwd = $("#newpwd").val();
			$.ajax({
				type:"POST",
				url:"${ctx}/comp/validatePassword",
				data:{"password":password,"newpwd":newpwd},
				async:false,
				dataType:"json",
				success:function(data){
					if(data.message == '0'){//0代表验证成功
						flag = true;
					}else{
						flag = false;
					}
				}
			});
			return flag;
		}
		var file_size = 1;
		
		function bigImg(li) {
			var $btns = $(li).find( 'div.file-panel' );
			$btns.animate({height: 30});
		}
		
		function normalImg(bts) {
			var $btns = $(bts);
			$btns.animate({height: 0});
		}
		
		function deleteImg(bts){
			var $btns = $(bts);
			var $queue1 = $btns.parent().parent();
			var $queue = $btns.parent().parent().parent();
			if ($queue.find("li").length <= file_size) {
				$queue.parent().parent().find('.btns').show();
			}
			$queue1.remove();
		}
		
		function saveLogo(){
			var logo = $("input[name='pics']").val();
			if(logo == null || logo ==''){
				layer.msg("请选择图片！");
			}else{
				$("#logo").val(logo);
				var form = $('#logoForm');
				form.submit();
			}
		}
		
		function saveChapter(){
			var chapter = $("input[name='pics1']").val();
			if(chapter == null || chapter ==''){
				layer.msg("请选择图片！");
			}else{
				$("#chapter").val(chapter);
				var form = $('#chapterForm');
				form.submit();
			}
		}
		
		var ul = mulImagesInit('${ctx}','',file_size,'${ctxStatic}/hui/lib/webuploader/0.1.5/Uploader.swf', 
			function(e){
				e.find("#addBtn").hide();
			}, 
			function(e){
				e.find("#addBtn").show();
			});
		var ul1 = mulImagesInit('${ctx}',1,file_size,'${ctxStatic}/hui/lib/webuploader/0.1.5/Uploader.swf', 
			function(e){
				e.find("#addBtn").hide();
			}, 
			function(e){
				e.find("#addBtn").show();
			});
		
		//判断输入密码的类型  
		function CharMode(iN) {
			if (iN >= 48 && iN <= 57) //数字  
				return 1;
			if (iN >= 65 && iN <= 90) //大写  
				return 2;
			if (iN >= 97 && iN <= 122) //小写  
				return 4;
			else
				return 8;
		}
		//bitTotal函数  
		//计算密码模式  
		function bitTotal(num) {
			modes = 0;
			for (i = 0; i < 4; i++) {
				if (num & 1)
					modes++;
				num >>>= 1;
			}
			return modes;
		}
		//返回强度级别  
		function checkStrong(sPW) {
			if (sPW.length <= 4)
				return 0; //密码太短  
			Modes = 0;
			for (i = 0; i < sPW.length; i++) {
				//密码模式  
				Modes |= CharMode(sPW.charCodeAt(i));
			}
			return bitTotal(Modes);
		}
		//显示颜色  
		function pwStrength(pwd) {
			L_color = "security_level";

			if (pwd == null || pwd == '') {
				Lcolor = "security_level";
			} else {
				S_level = checkStrong(pwd);
				switch (S_level) {
				case 0:

					Lcolor = "security_level";
				case 1:
					Lcolor = "security_level level_low";

					break;
				case 2:
					Lcolor = "security_level level_mid";
					break;
				default:

					Lcolor = "security_level level_hig";
				}
			}

			$("#strength_L").attr('class', Lcolor);
			return;
		}
	</script>
</body>
</html>
