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
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>公司首页</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span><i
			class="Hui-iconfont"></i> 公司管理 <span class="c-gray en">&gt;</span>
		公司简介 <span class="c-gray en"></span><a
			class="btn btn-success radius r mr-20"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="pd-20">
		<form action="${ctx}/comp/index" method="post"
			class="form form-horizontal" id="form-admin-add">
			<div class="responsive">
				<div class="row cl">
					<div class="company_head col-6">
						<p class="company_img">
							<img src="${compCompInfo.filePath}" width="180" height="79" alt="">
						</p>
						<div class="company_info">
							<p class="company_title">${compCompInfo.name}</p>
							<div class="cl">
								<p class="mr-30 l">经纪人：${count}人</p>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="line"></div>
			<div class="row cl">
				<div class="company_introduce-content col-7 pr-10">
					<div class="panel panel-default">
						<div id="intro" class="panel-body pb-20">
							<div id="show">${compCompInfo.introduction}</div>
						</div>
					</div>
				</div>
				<div class="formControls col-5">
					<div class="panel panel-default mb-20">
						<p class="panel-header">系统公告</p>
						<div class="panel-body">
							<ul>
								<c:forEach items="${fns:getNotice('66560778221d4e2da48bb5ed9be14886')}" var="notice" varStatus="status">
									<li class="cl">
						        		<span class="l form_text">
						        			<a onclick="noticeDetail('${notice.id}')" class="btn btn-link">
						        				${fns:myAbbr(notice.title,20)}
						        			</a>
						        		</span>
					        			<span class="r form_text" style="margin-top:7px;">
					        				<fmt:formatDate value="${notice.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
					        			</span>
        							</li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-10 col-offset-2 mt-20">
					<shiro:hasPermission name="comp:info:edit">
						<input id="alter" type="button" value="编辑" class="btn btn-primary" />
						<input id="save" type="submit" style="display: none;" value="保存" class="btn btn-primary" />
					</shiro:hasPermission>
				</div>
			</div>
			<div style="display: none">
				<textarea type="hidden" id="hIntro">${compCompInfo.introduction}</textarea>
			</div>
		</form>
		<div id="notice" class="pd-20" style="display: none;">
			<table class="table table-border table-bordered">
				<tbody>
					<tr>
						<td class="text-r" width="120">文章标题：</td>
						<td></td>
					</tr>
					<tr>
						<td class="text-r">发布时间：</td>
						<td></td>
					</tr>
					<tr>
						<td colspan="2"></td>
					</tr>
				</tbody>
			</table>
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
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/static/hui/js/H-ui.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/static/hui/js/H-ui.admin.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/static/hui/js/jquery-migrate-1.1.0.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/static/hui/lib/ueditor/1.4.3/ueditor.config.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/static/hui/lib/ueditor/1.4.3/ueditor.all.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/static/hui/lib/ueditor/1.4.3/lang/zh-cn/zh-cn.js"></script>
		<%@ include file="/WEB-INF/views/include/foot.jsp"%>
	<script type="text/javascript">
		$(function() {

			$('.skin-minimal input').iCheck({
				checkboxClass : 'icheckbox-blue',
				radioClass : 'iradio-blue',
				increaseArea : '20%'
			});

			var display = $("#save").css('display');
			var div = "";
			$("#alter").click(
							function() {
								var hIntro = $("#hIntro").val();
								if (display == 'none') {
									$("#show").remove();
									div = div + "<div id ='edit'>";
									div = div
											+ "<script id='editor' type='text/plain' name='introduction' style='width: 100%; height: 400px;'>";
									div = div + hIntro;
									div = div + "</" + "script></div>"
									$("#save").css('display', 'block');
									$("#alter").css('display', 'none');
									$("#intro").html(div);
									
						            var ue = UE.getEditor('editor',{   
						                toolbars:[  
						                          [   'fullscreen','|',
						                              'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|',
						                              'forecolor', 'backcolor','|',  
						                              'fontfamily', 'fontsize', '|',  
						                              'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 
						                              'insertimage', 'background', '|',  
						                              'horizontal', 'date', 'time', 'spechars', 'snapscreen','|']  
						                      ]  
						            }); 
// 									var ue = UE.getEditor('editor');
									ue.addListener("ready", function() {
										ue.setEnabled();
									});
								} else {
									$("#save").css('display', 'none');
									$("#alter").css('display', 'block');
								}
							});
		});
		function noticeDetail(id) {
			$.post(
			"${ctx}/article/detailAjax",
			{id:id},
			function (data){
				if (data && data.id) {
					var trs = $("#notice").find("tr");
					trs.eq(0).find("td").eq(1).text(data.title);
					trs.eq(1).find("td").eq(1).text(new Date(data.updateDate).Format("yyyy-MM-dd hh:mm:ss"));
					trs.eq(2).find("td").eq(0).html(data.content);
				}
			});
			//公告弹出框
			_didx = layer.open({
				type: 1, //page层
				area: ['1050px', '850px'],
				title: '公告详细',
				shade: 0.6, //遮罩透明度
				moveType: 1, //拖拽风格，0是默认，1是传统拖动
				shift: 0, //0-6的动画形式，-1不开启
				content: $('#notice')
			});
		}
		Date.prototype.Format = function (fmt) { //author: meizz 
		    var o = {
		        "M+": this.getMonth() + 1, //月份 
		        "d+": this.getDate(), //日 
		        "h+": this.getHours(), //小时 
		        "m+": this.getMinutes(), //分 
		        "s+": this.getSeconds(), //秒 
		        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
		        "S": this.getMilliseconds() //毫秒 
		    };
		    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
		    for (var k in o)
		    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
		    return fmt;
		}
	</script>
</body>
</html>
