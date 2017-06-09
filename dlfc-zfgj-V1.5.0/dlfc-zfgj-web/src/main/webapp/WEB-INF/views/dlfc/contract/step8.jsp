<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head2.jsp"%>
<!-- 页面特有css -->
<link href="${ctxStatic}${hui}lib/icheck/icheck.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		租房合同 <span class="c-gray en">&gt;</span> 新建合同<a
			class="btn btn-success radius r mr-20"
			style="line-height: 1.6em; margin-top: 3px"
			onclick="javascript:$('#getForm').submit();"
			href="#" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<form action="${ctx}/contp/step3" method="get" id="getForm">
		<input type="hidden" id="cid" name="cid" value="${con.id}">
	</form>
	<div class="pd-20">
		<form action="${ctx}/contp/step3" method="post" class="form form-horizontal" id="step3Form">
			<form:hidden path="con.id"/>
			<form:hidden path="con.version" />
			<legend>房屋租金</legend>
			<div class="responsive">
				<div class="row cl">
					<label class="form-label col-3"><span class="c-red">*</span>月租金：</label>
					<div class="formControls col-1">
						<form:input path="con.monthlyRent" placeholder="月租金" id="monthlyRent" data-prompt-target="monthlyRentError" data-prompt-position="inline" class="input-text validate[required,custom[integer],max[1000000],min[1]]" onchange="countPayTime()"/>
					</div>
					<p class="form_text">元</p>
					<p class="form_text" id="monthlyRentError"></p>
				</div>
			</div>

			<div class="responsive">
				<div class="row cl">
					<label class="form-label col-3"><span class="c-red">*</span>结算周期：</label>
					<form:hidden path="con.payTimeMonth" id="payTimeMonth"/>
					<form:hidden path="con.payTimeDay" id="payTimeDay"/>
					<div class="formControls skin-minimal col-4">
						<div class="radio-box">
							<form:radiobutton path="con.settlementCycle" id="settlementCycle1" onclick="setPayTime(this)" value="1"/><label for="settlementCycle1">月付</label>
						</div>
						<div class="radio-box">
							<form:radiobutton path="con.settlementCycle" id="settlementCycle2" onclick="setPayTime(this)" value="2" class="ml20"/><label for="settlementCycle2">季付</label>
						</div>
						<div class="radio-box">
							<form:radiobutton path="con.settlementCycle" id="settlementCycle3" onclick="setPayTime(this)"  value="3" class="ml20"/><label for="settlementCycle3">半年付 </label>
						</div>
						<div class="radio-box">
							<form:radiobutton path="con.settlementCycle" id="settlementCycle4" onclick="setPayTime(this)" value="4" class="ml20"/><label for="settlementCycle4">年付 </label>
						</div>
					</div>
				</div>
			</div>

			<div class="responsive">
				<div class="row cl">
					<div class="formControls col-3"></div>
					<div class="formControls col-8">
						<div class="contract_pay_tips">
							<ul id="payTimeUl">
							</ul>
							<p>
								<a href="" class="choose_more link_help">展开 <i
									class="icos icos_more"></i></a>
							</p>
						</div>
					</div>
				</div>
			</div>

			<legend>费用</legend>
			<div class="responsive">
				<div class="row cl">
					<label class="form-label col-3">房主承担：</label>
					<div class="formControls col-6">
						<div class="skin-minimal">
							<div class="check-box">
								<form:checkbox path="con.ownerBears" id="lesseeBear1" value="1" class="ml15 mr5"/><label for="lesseeBear1"> 水费 </label>
								</div>
							<div class="check-box">
								<form:checkbox path="con.ownerBears" id="lesseeBear2" value="2" class="ml15 mr5"/><label for="lesseeBear2"> 电费 </label>
							</div>
							<div class="check-box">
								<form:checkbox path="con.ownerBears" id="lesseeBear3" value="3" class="ml15 mr5"/><label for="lesseeBear3"> 电话费</label>
							</div>
							<div class="check-box">
								<form:checkbox path="con.ownerBears" id="lesseeBear4" value="4" class="ml15 mr5"/><label for="lesseeBear4"> 电视收视费 </label>
							</div>
							<div class="check-box">
								<form:checkbox path="con.ownerBears" id="lesseeBear5" value="5" class="ml15 mr5"/><label for="lesseeBear5"> 供暖费 </label>
							</div>
							<div class="check-box">
								<form:checkbox path="con.ownerBears" id="lesseeBear6" value="6" class="ml15 mr5"/><label for="lesseeBear6"> 燃气费 </label>
							</div>
							<div class="check-box">
								<form:checkbox path="con.ownerBears" id="lesseeBear7" value="7" class="ml15 mr5"/><label for="lesseeBear7"> 物业管理费 </label>
							</div>
							<div class="check-box">
								<form:checkbox path="con.ownerBears" id="lesseeBear8" value="8" class="ml15 mr5"/><label for="lesseeBear8"> 房屋租赁税费 </label>
							</div>
							<div class="check-box">
								<form:checkbox path="con.ownerBears" id="lesseeBear9" value="9" class="ml15 mr5"/><label for="lesseeBear9"> 卫生费 </label>
							</div>
							<div class="check-box">
								<form:checkbox path="con.ownerBears" id="lesseeBear10" value="10" class="ml15 mr5"/><label for="lesseeBear10"> 上网费 </label>
							</div>
							<div class="check-box">
								<form:checkbox path="con.ownerBears" id="lesseeBear11" value="11" class="ml15 mr5"/><label for="lesseeBear11"> 车位费 </label>
							</div>
							<div class="check-box">
								<form:checkbox path="con.ownerBears" id="lesseeBear12" value="12" class="ml15 mr5"/><label for="lesseeBear12"> 室内设施维修费 </label>
							</div>
						</div>

					</div>
				</div>
			</div>
			<div class="row cl">
				<div class="formControls col-3"></div>
				<p class="form_text">其他</p>
				<div class="formControls col-2">
					<form:input path="con.ownerBearOther" id="lesseeBearOther" placeholder="其他" class="validate[maxSize[50]] input-text" data-prompt-target="lesseeBearOtherError" data-prompt-position="inline"/>
				</div>
				
				<p class="form_text" id="lesseeBearOtherError"></p>
			</div>


			<legend>中介费</legend>
			<div class="responsive">
				<div class="row cl">
					<label class="form-label col-3"><span class="c-red">*</span>中介费：</label>
					<div class="formControls col-1">
						<form:input path="con.agencyFee" id="agencyFee" placeholder="中介费" class="validate[required,custom[number],max[1000000],min[1]] input-text" data-prompt-target="agencyFeeError" data-prompt-position="inline"/>
					</div>
					<p class="form_text">元</p>
					<p class="form_text" id="agencyFeeError"></p>
				</div>
			</div>

			<legend>押金</legend>
			<div class="responsive">
				<div class="row cl">
					<label class="form-label col-3"><span class="c-red">*</span>押金：</label>
					<div class="formControls col-1">
						<form:input path="con.depositAmt" id="depositAmt" class="validate[required,custom[number],min[0],max[1000000]] input-text" placeholder="保证金" data-prompt-target="depositAmtError" data-prompt-position="inline"/>
					</div>
					<p class="form_text">元</p>
					<p class="form_text" id="depositAmtError"></p>
				</div>
				<div class="row cl">
					<div class="formControls col-3"></div>
					<div class="formControls col-8">
						<div class="contract_pay_con">
							押金用作乙方履行本合同约定义务的担保，合同期内或合同履行期届满，如乙方违反合同约定，甲方可从押金中抵扣应由乙方承担的租金、违约金、物品损坏赔偿金和相关欠费等，扣款数额由双方协商确认。合同期满，如出现押金不足以抵扣前述款项及费用时，乙方需在10日内补足。
						</div>
					</div>
				</div>
			</div>
			<div class="row cl btn_group">
				<div class="col-9 col-offset-3">
					<a class="btn btn-default radius" href="${ctx}/contp/step2?cid=${con.id}">上一步</a> <a
						class="btn btn-primary radius" onclick="submitForm()">下一步</a>
				</div>
			</div>
		</form>
	</div>
	<%@ include file="/WEB-INF/views/include/foot.jsp"%>
		<!-- 页面特有js -->
		<script type="text/javascript"
			src="${ctxStatic}${hui}lib/layer/1.9.3/layer.js"></script>
		<script type="text/javascript" src="${ctxStatic}${hui}lib/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${ctxStatic}${hui}lib/icheck/jquery.icheck.min.js"></script> 
		<script type="text/javascript" src="${ctxStatic}${hui}js/jquery-migrate-1.1.0.min.js"></script>
		<script type="text/javascript">
			var cycleNum = "${con.settlementCycle}";
			if (cycleNum == "") {
				cycleNum = "1";
			}
			$(function() {
				countPayTime(cycleNum);
				$('.skin-minimal input').iCheck({
					checkboxClass: 'icheckbox-blue',
					radioClass: 'iradio-blue',
					increaseArea: '20%'
				});
				
				$('#step3Form').validationEngine('attach', { 
					showOneMessage: true,
					maxErrorsPerField : 1,
					addPromptClass: 'formError-noArrow formError-text',
					promptPosition: 'centerRight'
					});
				countPayTime(cycleNum);
				initPayTimeCheckNum();
			});
			function checkMoney(gets, obj, curform, regxp) {
				var money = parseFloat(gets);
				if (isNaN(money)) {
					return "请输入数字！";
				} else if (money > 1000000) {
					return "数额不能超过1000000！";
				} else {
					return true;
				}
			}
			
			/* function checkDepositAmt(obj){
				var depositAmt = obj.val();
				var monthlyRent = parseFloat($("#monthlyRent").val());
				var min = monthlyRent * 0.8;
				var max = monthlyRent * 2;
				if (depositAmt > max || depositAmt < min) {
					return "保证金为0.8-2倍月租金!";
				} else {
					return true;
				}
			} */
			
			function submitForm() {
				$("#step3Form").submit();
			}
			
			function hideCycle(id) {
				$(id).parent("div").parent("div").css("display", "none");
			}

			function showCycle(id) {
				$(id).parent("div").parent("div").css("display", "inline");
			}
			
			function initPayTimeCheckNum() {
				var year = parseInt('${con.leaseTermYear}') * 12;
				var month = parseInt('${con.leaseTermMonth}');
				var totalMonth = year + month;
				//设置结算周期
				if (totalMonth < 3) {
					showCycle("#settlementCycle1");
					hideCycle("#settlementCycle2");
					hideCycle("#settlementCycle3");
					hideCycle("#settlementCycle4");
				} else if (totalMonth >= 3 && totalMonth < 6) {
					showCycle("#settlementCycle1");
					showCycle("#settlementCycle2");
					hideCycle("#settlementCycle3");
					hideCycle("#settlementCycle4");
					var checkedVal = $("input[name='settlementCycle']:checked").val();
					if (checkedVal == "3" || checkedVal == "4") {
						$("#settlementCycle1").attr("checked", true);
					}
				} else if (totalMonth >= 6 && totalMonth < 12) {
					showCycle("#settlementCycle1");
					showCycle("#settlementCycle2");
					showCycle("#settlementCycle3");
					hideCycle("#settlementCycle4");
					var checkedVal = $("input[name='settlementCycle']:checked").val();
					if (checkedVal == "4") {
						$("#settlementCycle1").attr("checked", true);
					}
				} else {
					showCycle("#settlementCycle1");
					showCycle("#settlementCycle2");
					showCycle("#settlementCycle3");
					showCycle("#settlementCycle4");
				}
			}
			
			//动态显示支付时间输入区
			function setPayTime(obj) {
				var num = $(obj).val();
				/* if (num == cycleNum) {
					return;
				} */
				/* cycleNum = num; */
				countPayTime(num);
			}
			
			//计算每次支付房租的时间
			function countPayTime(num) {
				var result = checkMoney($("#monthlyRent").val(), "", "", "");
				if (result.toString() != "true") {
					$("#payTimeUl").parent().css("display", "none");
					return;
				}
				setDepositAmt();
				var startTime = '${con.dateStartTime}';
				var endTime = '${con.dateEndTime}';
				var year = parseInt('${con.leaseTermYear}') * 12;
				var month = parseInt('${con.leaseTermMonth}');
				
				var checkedVal;
				if(num == undefined){
					var radioVal=$('input:radio[name="settlementCycle"]:checked').val();
					checkedVal = radioVal;
				}else{
					checkedVal = num;
				}
				if (checkedVal != "1") {
					payTimeMonth = parseInt($("#payTimeMonth").val());
				}
				var monthlyRent = parseInt($("#monthlyRent").val());
				var startDate = newDate(startTime);
				var startMonth = startDate.getMonth() + 1;
				var endDate = newDate(endTime);
				var fromDate = newDate(startTime);
				
				//var d = new Date(endTime);
				//var curMonthDays = new Date(d.getFullYear(), (d.getMonth()+1), 0).getDate();
				//alert(curMonthDays);
				
				var totalMonth = year + month;
				var html = "";
				var cycle = 1;
				var payTimeMonth = fromDate.getMonth()  -1;
				  if (checkedVal == "2") {
					cycle = 3;
					 payTimeMonth = fromDate.getMonth()  ;
				} else if (checkedVal == "3") {
					cycle = 6;
					 payTimeMonth = fromDate.getMonth() +3 ;
				} else if (checkedVal == "4") {
					cycle = 12;
				}
				
				var payTimeDay = fromDate.getDate();
				var fromAfterPayDate = newDate(startTime);
				//本套算法唯一注意的就是没换到一个将要去的月份都要判断当前月份的天数会不会比起始当天日的天数小，解决2月30日这类问题
				for (var i = 0; i < totalMonth; i = i + cycle) {
					var surplus = totalMonth - i;
					var toDateStr = "";
					var rent = 0;
					var afterPayDateStr = "";
					var fromDateStr = "";
					//当第一个循环单独拿出来考虑
					if(i == 0){
						//支付日期就是起始日期
						afterPayDateStr = formatDate(fromAfterPayDate);
						//起始日期就是当天的起始日期
						fromDateStr = formatDate(fromDate);
						//结束日期就需要判断结束的那个月的总天数与起始日期的天数谁大谁小
						var days = new Date(fromDate.getFullYear(), (fromDate.getMonth()+cycle+1), 0).getDate();//获得结束月的天数
						//谁的天数小就取谁的天数-1 这样保证了不真实日期的出现
						if(fromDate.getDate() <= days){
							toDateStr = formatDate(new Date(fromDate.getFullYear(), (fromDate.getMonth()+cycle), fromDate.getDate()-1));
						}else{
							toDateStr = formatDate(new Date(fromDate.getFullYear(), (fromDate.getMonth()+cycle), days-1));
						}
						//金额就是单价*周期
						rent = monthlyRent * cycle; 
					}else{//从第二个循环开始就有了变化，如果是月付还是和以前一样，如果是其他支付情况，需要提前一个月支付
						//如果是月付，其他的判断和之前类似，只需要提前判断一下每一个结束周期月的天数即可
						if(cycle == 1){
							var firstDays = new Date(fromDate.getFullYear(), (fromDate.getMonth()+i+1), 0).getDate();
							if(payTimeDay <= firstDays){
								afterPayDateStr = formatDate(new Date(fromDate.getFullYear(), (fromDate.getMonth()+i), fromDate.getDate()));
							}else{
								afterPayDateStr = formatDate(new Date(fromDate.getFullYear(), (fromDate.getMonth()+i), firstDays));
							}
							var afterPayDate = new Date(afterPayDateStr);
							fromDateStr = formatDate(new Date(afterPayDate.getFullYear(), (afterPayDate.getMonth()), afterPayDate.getDate()));
							var firstOnedays = new Date(fromDate.getFullYear(), (fromDate.getMonth()+cycle+i+1), 0).getDate();
							if(fromDate.getDate() <= firstOnedays){
								toDateStr = formatDate(new Date(fromDate.getFullYear(), (fromDate.getMonth()+cycle+i), fromDate.getDate()-1));
							}else{
								toDateStr = formatDate(new Date(fromDate.getFullYear(), (fromDate.getMonth()+cycle+i), firstOnedays-1));
							}
							rent = monthlyRent * cycle;
						}else{//如果是非月付，提前一个月支付其他判断情况雷同
							var elseDays = new Date(fromDate.getFullYear(), (fromDate.getMonth()+i), 0).getDate();
							if(payTimeDay <= elseDays){
								afterPayDateStr = formatDate(new Date(fromDate.getFullYear(), (fromDate.getMonth()+i-1), fromDate.getDate()));
							}else{
								afterPayDateStr = formatDate(new Date(fromDate.getFullYear(), (fromDate.getMonth()+i-1), elseDays));
							}
							var secondDays = new Date(fromDate.getFullYear(), (fromDate.getMonth()+i+1), 0).getDate();
							if(payTimeDay <= secondDays){
								fromDateStr = formatDate(new Date(fromDate.getFullYear(), (fromDate.getMonth()+i), fromDate.getDate()));
							}else{
								fromDateStr = formatDate(new Date(fromDate.getFullYear(), (fromDate.getMonth()+i), secondDays));
							}
							if (surplus >= cycle) {//这一部分是处理小尾巴的，如果填入2年零5个月，判断零头是否满足一个周期，如果满足就正常按周期取结束时间
								var thirdDays = new Date(fromDate.getFullYear(), (fromDate.getMonth()+i+cycle+1), 0).getDate();
								if(fromDate.getDate() <= thirdDays){
									toDateStr = formatDate(new Date(fromDate.getFullYear(), (fromDate.getMonth()+cycle+i), fromDate.getDate()-1));
								}else{
									toDateStr = formatDate(new Date(fromDate.getFullYear(), (fromDate.getMonth()+cycle+i), thirdDays-1));
								}
								rent = monthlyRent * cycle; 
							} else {//如果不满足一个周期就把剩下的这些小尾巴放一起算出时间和金钱
								toDateStr = endTime;
								rent = monthlyRent * surplus;
							} 
						}
					}
					html = html + '<li><span>' + afterPayDateStr
							+ '</span> 前支付 <span>' + fromDateStr
							+ '</span> 至 <span>' + toDateStr
							+ '</span> 的租金 ，共计 <em>' + rent + '</em> 元</li>'
							+ '';
				/* $('#payTimeMonth').val(payTimeMonth);*/	
				$('#payTimeDay').val(payTimeDay); 
				}
				$("#payTimeUl").html(html);
				$("#payTimeUl").parent().css("display", "block");
			}

			function initPayTimeHtml() {
				var html = '<label class="form-label col-3"><span class="c-red">*</span>支付时间：</label>'
						+ '<p class="form_text">每月</p>'
						+ '<p class="form_text">'
						+ '<span class="select-box"><select name="payTimeDay" id="payTimeDay" onchange="countPayTime()" class="select">';
				for (var i = 1; i <= 31; i++) {
					html = html + '<option value="'+i+'">' + i + '</option>';
				}
				html = html + '</select></span></p><p class="form_text">日，支付下周期费用</p>';
				$("#payTimeDiv").html(html);
			}

			function setPayTimeHtml(num) {
			var html = '<label class="form-label col-3"><span class="c-red">*</span>支付时间：</label>'
						+ '<p class="form_text">每周期第</p>'
						+ '<p class="form_text">'
						+ '<span class="select-box">'
						+ '<select name="payTimeMonth" id="payTimeMonth" onchange="countPayTime()" class="select">';
				for (var i = 1; i <= num; i++) {
					html = html + '<option value="'+i+'">' + i + '</option>';
				}
				html = html
						+ '</select>'
						+ '</span></p>'
						+ '<p class="form_text">月</p>'
						+ '<p class="form_text"><span class="select-box"><select name="payTimeDay" id="payTimeDay" onchange="countPayTime()" class="select">';
				for(var i = 1; i <= 31; i++) {
					html = html + '<option value="'+i+'">' + i + '</option>';
				}
				html = html + '</select></span></p><p class="form_text">日，支付下周期费用</p>';
				$("#payTimeDiv").html(html);
			}
			//输入月租金后，如果保证金是空的，将月租金填入保证金中
			function setDepositAmt() {
				if ($("#monthlyRent").val() != "") {
					if ($("#depositAmt").val() == "") {
						$("#depositAmt").val($("#monthlyRent").val());
					}
				}
				
			}
			var _layer;
			var depurl = '${ctx}/contp/dinfo';
			function depositInfo(){
			_layer =layer.open({
					type: 2, //page层
					area: ['700px', '800px'],
					title: "押金监管协议",
					shade: 0.6, //遮罩透明度
					moveType: 1, //拖拽风格，0是默认，1是传统拖动
					shift: 0, //0-6的动画形式，-1不开启
					closeBtn: 1,
					content: depurl
				});
			}
			
			function close(){
				layer.close(_layer);
			}
		</script>
</body>
</html>