<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<!DOCTYPE HTML>
<html>
<head>

<%@ include file="/WEB-INF/views/include/head2.jsp"%>

<title>添加管理员</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		租房合同 <span class="c-gray en">&gt;</span> 解约协议<a
			class="btn btn-success radius r mr-20"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="pd-20">
	<form action="${ctx}/con/dissd" method="post"
		class="form form-horizontal" id="dissagr">
		<input name="version" type = "hidden" value="${contract.conDissAgr.version}">
		<input name="cid" type = "hidden" value="${contract.id}" />
		
		<div class="mt-20">
			<table class="table mb-30 table-border table-bordered ">
				<tbody>
					<tr class="text-l">
						<td colspan="3" class="hoser_info"><div class="item cl">
								<div class="col-3">
									<fmt:formatDate value="${contract.createTime}"
										pattern="yyyy-MM-dd HH:mm" />
								</div>
								<div class="col-6">${contract.no}</div>
							</div></td>
					</tr>
					<tr class="text-c">
						<td><p class="house_name">
								<a href="${ctx}/house/see?hid=${contract.hid}">${contract.houseAddr}</a>
							</p> <c:if test="${contract.rentalMode == 2}">
								<p class="house_name">
									<span class="label label-secondary">${contract.leaseDomain}</span>
									<span class="label label-secondary">(${contract.leaseArea})平方米</span>
								</p>
							</c:if> <c:if test="${contract.rentalMode == 1}">
								<p class="house_name">
									<span class="label label-secondary">整租</span>
								</p>
							</c:if></td>
						<td><p class="f-16">
								月租金：¥<strong class="c-orange">${contract.monthlyRent}</strong>/月
							</p>
							<p>
								押金：¥<strong class="c-orange" >${contract.depositAmt}</strong>
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
			<div class="panel-body">
				<div class="responsive">
					<div class="row cl">
						<label class="form-label col-3"><span class="c-red">*</span>原因类型：</label>
						<div class="formControls col-2">
							<span class="select-box"> <form:select
									path="dissAgr.reason"
									class="validate[required] select" id="reason">
									<form:options items="${reason}" itemValue="code"
										itemLabel="name" />
								</form:select>
							</span>
						</div>
					</div>
					<div class="row cl">
						<label class="form-label col-3"><span class="c-red">*</span>解约生效日：</label>
						<div class="formControls col-2">
							<fmt:formatDate value="${effectiveTime}"
									pattern="yyyy-MM-dd" />
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="panel panel-default mb-20">
			<div class="panel-body">
				<div class="responsive">
					<legend>租金返还</legend>
					<div class="row cl">
						<label class="form-label col-3">房主需返还租金：</label>
						<div class="formControls col-2">
							<!-- <input type="text"   class="input-text" style="width:83%;"> -->
							<form:input path="dissAgr.retrunRentAmt" style="width:83%;"
								class="validate[funcCall[checkNum],max[999999],min[0]] input-text" />
							元
						</div><div class="col-3"></div>
					</div>
					<legend>违约补偿金额</legend>
					<div class="row cl">
						<div class="formControls col-1 text-l"></div>
						<div class="formControls col-2 text-l">
							<span class="select-box"> <form:select
									path="dissAgr.paymentPersonType"
									class="validate[required] select" id="paymentPersonType">
									<form:options items="${paymentPersonType}" itemValue="code"
										itemLabel="name" />
								</form:select>
							</span>
						</div>
						<p class="form_text">支付</p>
						<div class="formControls col-2">
							<!-- <input type="text"   class="input-text" style="width:83%;"> -->
							<form:input path="dissAgr.paymentAmt" class="validate[funcCall[checkNum],max[999999],min[0]] input-text"
								style="width:83%;" />
							元
						</div><div class="col-3"></div>
					</div>
					<legend>押金返还</legend>
					<div class="row cl">
						<label class="form-label col-3"><span class="c-red">*</span>房主扣除：</label>
						<div class="formControls col-2">
							<!-- <input type="text"   class="input-text" style="width:83%;"> -->
							<form:input path="dissAgr.paymentDepositAmt" class="validate[required,funcCall[checkmoney],min[0]] input-text" id="paymentDepositAmt"
								style="width:83%;"   />
							元
						</div><div class="col-3"></div>
					</div>
					<div class="row cl">
						<label class="form-label col-3">补充说明：</label>
						<div class="formControls col-4">
							<!-- <textarea name="" cols="" rows="" class="textarea" placeholder="说点什么..."></textarea> -->
							<form:textarea path="dissAgr.description" class="validate[maxSize[300]] textarea" /><div class="col-3"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<legend>注意事项</legend>
		<div class="row cl">
			<div class="formControls col-2"></div>
			<div class="content_box col-7 ">
				<div class="text_con">一、乙方应于收到甲方退还的已付剩余房租后
					个工作日内，按照原状返还房屋及其附属物品、设备设施。甲乙双方应对房屋和附属物品、设备设施及水电使用等情况进行验收，结清各自应当承担的费用。如乙一、乙方应于收到甲方退还的已付剩余房租后
					个工作日内，按照原状返还房屋及其附属物品、设备设施。甲乙双方应对房屋和附属物品、设备设施及水电使用等情况进行验收，结清各自应当承担的费用。如乙方不按时退租，每逾期一天，须向甲方支付原合同月租金3%的违约金方不按时退租，每逾期一天，须向甲方支付原合同月租金3%的违约金</div>
				<a href="" class="text_more">展开 <i class="icos icos_more"></i></a>
			</div>
		</div>
		<div class="row cl btn_group">
			<div class="col-9 col-offset-3">
				<a class="btn btn-primary radius" id="enter">确认并预览</a>
			</div>
		</div>
	</form>
</div>
	<%@ include file="/WEB-INF/views/include/foot.jsp"%>
	<script type="text/javascript"
		src="${ctxStatic}${theme}/js/jquery-migrate-1.1.0.min.js"></script>
	<script type="text/javascript">


$('#reason').change(function(){
	var checkValue=$("#reason").val();
	 if(checkValue == 0){
	 	$("#paymentPersonType").val("0");
	 } else if(checkValue == 1){
		 $("#paymentPersonType").val("1");
	 } else if (checkValue == 2) {
		 $("#paymentPersonType").val("0");
	 }
	 $("#paymentPersonType").prev("div").remove();
 })



	
$(function() {
	$('#dissagr').validationEngine('attach', { binded : false ,showOneMessage: true,maxErrorsPerField : 1,promptPosition: 'centerRight',addPromptClass: 'formError-noArrow formError-text',promptPosition: 'centerRight:20,0'});
	
	$('#enter').click(function(){
		$('#dissagr').submit();
	});
	
	
	 
});

function checkNum(field){
	var money = field.val();
	if (isNaN(jQuery.trim(field.val()))) {
		return "必须是纯数字";
	}
	
};
function checkmoney(field){
	var money = field.val();
	var paymentDepositAmt=$("#paymentDepositAmt").val();
	var yajin = ${contract.depositAmt};
	if (isNaN(jQuery.trim(field.val()))) {
		return "必须是纯数字";
	}
	  if(paymentDepositAmt>yajin){
		return "扣除押金不能大于押金总额";
	}
	 
};
</script>
</body>
</html>