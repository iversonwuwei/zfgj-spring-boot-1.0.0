<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<c:set var="ctxFront" value="${fns:getSiteUrl()}${fns:getFrontPath()}"/>
<!DOCTYPE HTML>
<html>
<head>

<%@ include file="/WEB-INF/views/include/head2.jsp"%>
<link href="${pageContext.request.contextPath}/static/hui/lib/bootstrap-Switch/bootstrapSwitch.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript">
function page(n,s){
	$("#pageNo").val(n);
	$("#pageSize").val(s);
	doSearch();
	return false;
}

</script>
<title>添加管理员</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		我的任务 <span class="c-gray en">&gt;</span> 委托房源<a
			class="btn btn-success radius r mr-20"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="content">
		<div class="mt-20">
			<legend>委托房源</legend>
			<div class="list_tabs cl ">
				<a href="#" class="">公共房源</a>
			</div>
			<div class="list_sort cl">
				<c:if test="${bs == null || bs ==  '' || bs == 'desc'}">
					<a id="upsort" href="javascript:void(0);" title="降序" onclick="doSort('butimesort', 'asc')" class="active">添加时间 <span> &darr;</span></a>
				
					<!-- <a id="allsort" href="javascript:void(0);" title="无排序" onclick="doSort('butimesort', 'desc')">添加时间 <span> &uarr;</span></a> -->
				</c:if>
				<c:if test="${bs == 'asc'}">
					<a id="downsort" href="javascript:void(0);" title="升序" onclick="doSort('butimesort', 'desc')" class="active">添加时间<span> &uarr;</span></a>
				</c:if>
				
			</div>
			
			<form name="entForm" action="${ctx}/ent/list">
				<input id= "st" type="hidden" name="st" value="${st}">
				<input id="butimesort" type="hidden" name="bs" value="${bs}">
				<input id="pageNo" type="hidden" name="pageNo" value="${page.pageNo}">
				<input id="pageSize" type="hidden" name="pageSize" value="${page.pageSize}">
				<input type="hidden" name="currentPage" value="">
			</form>
			
			<!-- <div class="cl pd-5 bg-1 bk-gray mt-20">
				<a class="btn btn-success btn-refresh radius r mr-20"
					style="line-height: 1.6em; margin-top: 3px"
					href="javascript:location.replace(location.href);" title="刷新">刷新页面</a>
			</div> -->
			<table class="table table-border table-bordered  table-bg">
				<thead>
					<tr class="text-c">
						<th colspan="2">房源详情</th>
						<th width="180">操作</th>
					</tr>
				</thead>
			</table>
				<c:forEach items="${page.list}" var="entInfo" end="10">
			<table class="table table-border table-bordered  table-bg  mb-20">
				<tbody>
				
					<tr class="text-l">
						<td colspan="5" class="hoser_info"><div class="row cl">
								<div class="col-3"><fmt:formatDate value="${entInfo.publishTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
							</div></td>
					</tr>
					<tr class="text-c">
						<td  colspan="2"><p class="house_name">
								${entInfo.community }<!-- <span class="label label-primary">产权</span> -->
							</p></td>
						<td  width="180">${entInfo.parentName } ${entInfo.circleName }</td>
						<td class="text-c" width="180">
							<c:if test="${empty entInfo.agtExamineHouInfo }">
								<c:if test="${entInfo.time < 0 || entInfo.time == 0}">
									<a class="btn btn-danger radius" onclick="openLockDialog('${entInfo.id}','${entInfo.community }','${entInfo.parentName }','${entInfo.circleName }','${entInfo.name }','${entInfo.phone }','${entInfo.gender }')">
										抢
									</a>
								</c:if>
								<c:if test="${entInfo.time > 0 }">
									<a class="btn btn-default radius disabled" >
										抢
										(<span name="time">
											${entInfo.time }
										</span>
										s)
									</a>
									
									<a style="display: none" class="btn btn-danger radius" onclick="openLockDialog('${entInfo.id}','${entInfo.community }','${entInfo.parentName }','${entInfo.circleName }','${entInfo.name }','${entInfo.phone }','${entInfo.gender }')">
										抢
									</a>
								</c:if>
							</c:if>
							<c:if test="${not empty entInfo.agtExamineHouInfo }">
								<a class="btn btn-default radius disabled">已抢</a>
							</c:if>
						</td>
					</tr>
				</tbody>
			</table>
				</c:forEach>
			<div class="pagination">${page}</div>
		</div>
	</div>


	<div class="form form-horizontal pd-20" id="addLock" style="display: none;">
		<form id="LockForm" name="LockForm"  class="form form_layer" method="post" action ="${ctx}/ent/exah">
		<input id="repage" type="hidden" name="repage" value="1">
		<input id="entrustId" type="hidden" name="entrustId">
		
		<div class="responsive">
			<div class="row cl">
				<div class="form_text col-4">
					<p class="house_name">
						<a href="#" id="community">锦绣路8号 2#203</a><!-- <span class="label label-primary">产权</span> community,parentName,circleName-->
					</p>
				</div>
				<div class="form_text col-3">
					<p id="circleName">沙河口区 锦绣</p>
				</div>
			</div>
			<div class="row cl">
				<div class="panel panel-default">
					<div class="panel-body">
						<span style="margin-right: 85px" class="f-24"><strong id="phone">13890000000</strong></span> 
						<span class="f-18"><strong id="name">王先生</strong></span>
						<span class="f-18"><strong id="gender"></strong></span>
					</div>
				</div>
			</div>

			<div class="row cl">
				<label class="form-label col-3">验房时间：</label>
				<div class="formControls col-3">
					<input type="text"  onfocus="WdatePicker({minDate:'%y-%M-{%d}', onpicking:function(dp){dateTimeLinkage(dp.cal.getNewDateStr());}})" id="datemin" name="examineTime" autocomplete="off" class="validate[required] input-text Wdate">
				</div>
				<p class="form_text"></p>
				<div class="formControls col-3">
					<span class="select-box"> 
						<select class="select" size="1" name="examineTime2">
						</select>
					</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-3">备注：</label>
				<div class="formControls col-7">
					<textarea name="comment" cols="" rows="" placeholder="说点什么..." class="textarea"></textarea>
					<!-- <div class="check-box" style="padding-left: 0;">
						<input type="checkbox" id="checkbox-1"> <label for="checkbox-1">对方可见</label>
					</div> -->
				</div>
			</div>
		</div>
		<div class="responsive">
			<div class="row cl btn_group">
				<div class="col-6 col-offset-4">
					<a class="btn btn-primary radius mr-10" onclick="confirmLockDialog()">预约验房</a> 
					<a class="btn btn-default radius mr-10" onclick="closeLockDialog()">取消</a>
				</div>
			</div>
		</div>
		</form>
	</div>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/laypage/1.2/laypage.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/bootstrap-Switch/bootstrapSwitch.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/js/Myjs.js"></script>
	<%@ include file="/WEB-INF/views/include/foot.jsp"%>
	<script type="text/javascript">
		$(function() {
			$('.table-sort').dataTable({
				"aaSorting" : [ [ 1, "desc" ] ],//默认第几个排序
				"bStateSave" : true,//状态保存
				"aoColumnDefs" : [
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
			
			$("#addLock").hide();
			
			$('#LockForm').validationEngine('attach', { binded : false ,showOneMessage: true,maxErrorsPerField : 1});
			$("#datemin").on("focus change input blur", function(){
				dateTimeLinkage();
			});
		});

		<%-- 根据选择日期控制预约时间  --%>
		function dateTimeLinkage(val){
			var value;
			if (!val){
				value = $("#datemin").val();
			} else {
				value = val;
			}
			<%-- 取得预约时间框 --%>
			var $select = $("select[name=examineTime2]").eq(0);
			if (value) {
				var reg =/\d{4}-(0[1-9]|1[0-2])-(0[1-9]|1[0-9]|2[0-9]|3[0-1])/
				<%-- 验证日期是否当天 --%>
				if (reg.test(value)) {
					var date;
					$.ajax({
						type: "GET",
						url: '${ctx}/house/serverDate',
						dataType:"text",
						async : false, 
						success: function(data){
							date = data;
						}
					}); 
					if (date == value) {
						$select.empty();
						addSelectTime(true);
					} else {
						$select.empty();
						addSelectTime(false);
					}
				} else {
					$select.empty();
				}
			} else {
				$select.empty();
			}
		}
		<%--  生成opt 当天时间过滤过期时间 --%>
		function addSelectTime(flg) {
			var time = [];
			var date = new Date();
			for (var i = 9; i <= 20; i++) {
				if (flg && date.getHours() >= i){
					continue;
				}
				time.push(i);
			}
			// 生成opt
			var selectObj = $("select[name=examineTime2]").eq(0);
			$.each(time, function(i,value) {
				var option = $("<option></option>");
				var optValue = value + ":00";
				if (value.length < 2) {
					optValue = "0" + optValue;
					option.val(optValue);
					option.text(optValue);
				} else {
					option.val(optValue);
					option.text(optValue);
				}
				if (value == 18) {
					option.attr("selected", true);
				}
				selectObj.append(option);
			});
		}
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
		
		function doSort(prop, sort) {
			$("#" + prop).val(sort);
			doSearch();
		};
		
		function doSearch() {
			var form = document.forms['entForm'];
			form.submit();
		};
		
		//锁定 '${entInfo.id}','${entInfo.community }','${entInfo.parentName }','${entInfo.circleName }'
		function openLockDialog(id,community,parentName,circleName,name,phone,gender) {
			if(!lockCheck(id)){
				layer.msg("下手慢了，委托已被抢走！");
				return;
			}
			
			$("#entrustId").val(id);
			_didx = layer.open({
				type: 1, //page层
				area: ['60%', '80%'],
				title: '预约验房',
				shade: 0.6, //遮罩透明度
				moveType: 1, //拖拽风格，0是默认，1是传统拖动
				shift: 0, //0-6的动画形式，-1不开启
				content: $('#addLock')
				,cancel: function(){ 
					closeLockDialog();
				  }
			});
			
			$("#community").html(community);
			$("#circleName").html(parentName +' '+circleName);
			$("#name").html(name);
			$("#phone").html(phone);
			var genderText;
			if(1 == gender){
				genderText = "先生";
			} else {
				genderText = "女士";
			}
			$("#gender").html(genderText);
			
			$("#addLock").show();
		};
		
		function confirmLockDialog() {
			var success = $("#LockForm").validationEngine("validate");
			
			if(!success){
				return;
			}
			layer.load();
			var form = $("#LockForm");
			form.submit();
		};
		
		function closeLockDialog() {
			layer.close(_didx);
			$("#Lock").val('');
			$("#addLock").hide();
			$.post("${ctx}/ent/cancel", {id:$("#entrustId").val()})
		};
		
		//抢房源倒计时
		delayURL();
		function delayURL() {
			var $time = $("span[name='time']");
			var t = setTimeout("delayURL()", 1000);
			
			$time.each(function () {
				var delay = $(this).html();
				
				if (delay > 1) {
					delay--;
					$(this).html(delay);
				} else {
					$(this).parent().next().show();
					$(this).parent().hide();
				}
				
				});
			
		}
		
		//锁定房源
		function lockCheck(id){
			var flag = true;
			FC.ajax({
				type: "GET",
				url: '${ctx}/ent/lock',
				data: {
					id: id
				},
				dataType:'json',
				cache: false,
				async : false, 
				success: function(data){
					if(0 == data.success) {
						flag = false;
					}
				}
			}); 
			
			return flag;
		}
		

	</script>
</body>
</html>