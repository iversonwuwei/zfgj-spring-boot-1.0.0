<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head2.jsp"%>
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
		<form action="${ctx}/deposit/idi" method="post"
			class="form form-horizontal" id="form-admin-add">
			<div class="mt-20">
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
						<div class="row cl">
							<label class="form-label col-3"><span class="c-red">*</span>出租方扣除：</label>
							<div class="formControls col-2">
								<form:input id="rental_deduct" path="conContract.ConDepositDistAgr.deductAmt" type="text"
									placeholder="出租方扣除"
									
									class="input-text validate[custom[number],min[0],max[${conContract.depositAmt}]]"
									data-prompt-target="errorTd_rentType" style="width: 83%;"/>
								元 <input name="cid" type="hidden" value="${cid}" /> <input
									name="version" type="hidden" value="${version}">
									<form:hidden path="conContract.ConDepositDistAgr.id" />
							</div>
							<div class="col-2">
								返还承租方 ：<strong id="return_lessee" class="c-red"></strong> 元
							</div>
							<div class="form_text" id="errorTd_rentType"></div>
						</div>

						<div class="row cl">
							<label class="form-label col-3"><span class="c-red">*</span>收取原因：</label>
							<div class="formControls col-3">
								<span class="select-box"> <form:select class="select"
										path="conContract.ConDepositDistAgr.chargeReason" id="depositGainReasonList">
										<form:options items="${depositGainReasonList}"
											itemValue="code" itemLabel="name" />
									</form:select>
								</span>
							</div>
						</div>
						<div class="row cl">
							<label class="form-label col-3"><span class="c-red">*</span>补充说明：</label>
							<div class="formControls col-4">
								<form:textarea id="deposit_reason" path="conContract.ConDepositDistAgr.description" htmlEscape="false"
									cols="" rows=""
									class="validate[minSize[0],maxSize[100]] textarea"
									data-prompt-target="messType" placeholder="说点什么..."/>
							</div>
							<div class="form_text" id="messType"></div>
							<div class="Validform_checktip"></div>
						</div>
						<div class="row cl btn_group">
							<div class="col-9 col-offset-3">
								<a id="submit" class="btn btn-primary radius">确认并预览</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	<%@ include file="/WEB-INF/views/include/foot.jsp"%>
	<script type="text/javascript"
		src="${ctxStatic}${hui}js/jquery-migrate-1.1.0.min.js"></script>

	<script type="text/javascript">
		$(function() {
			deposit();
			/*返还承租方焦点触发*/
			$("#rental_deduct").blur(function() {
				deposit();
			});

			$("#submit").click(function() {
				$('#form-admin-add').validationEngine('validate');
				$("#form-admin-add").submit();
			});

			$('#form-admin-add').validationEngine('attach', {
				binded : false,
				showOneMessage : true,
				maxErrorsPerField : 1,
				addPromptClass : 'formError-noArrow formError-text',
				promptPosition : 'inline'
			});
		});

		function deposit(){
			var rental_deduct = $("#rental_deduct").val();
			var deposit = $("#deposit").html();
			var result = Subtr(deposit, rental_deduct);
			$("#return_lessee").html(result);
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

		//js 减法计算  
		//调用：Subtr(arg1,arg2)  
		//返回值：arg1减arg2的精确结果   
		function Subtr(arg1, arg2) {
			var r1, r2, m, n;
			try {
				r1 = arg1.toString().split(".")[1].length
			} catch (e) {
				r1 = 0
			}
			try {
				r2 = arg2.toString().split(".")[1].length
			} catch (e) {
				r2 = 0
			}
			m = Math.pow(10, Math.max(r1, r2));
			//last modify by deeka  
			//动态控制精度长度  
			n = (r1 >= r2) ? r1 : r2;
			return ((arg1 * m - arg2 * m) / m);
		}
	</script>
</body>
</html>