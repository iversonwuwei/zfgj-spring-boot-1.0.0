﻿<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>	
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<!DOCTYPE HTML>
<html>
<head>

<%@ include file="/WEB-INF/views/include/head2.jsp"%>

<title>添加管理员</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 合同管理 <span class="c-gray en">&gt;</span> 押金支付说明<a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="content">
  <form action="" method="post" class="form form-horizontal" id="form-admin-add">
    <div class="mt-20">
      <div class="responsive">
      	<div class="row cl form_owner ">
        <div class="form_text col-9"><strong>合同已生成，请尽快支付押金，合同编号：${con.no}</strong></div>
         <div class="form_text col-3">应付金额 <strong class="c-red">${con.depositAmt}元</strong>
</div>
<div class="form_text col-9">请您在 <strong class="c-red">24</strong> 小时内完成付款，否则合同将会自动取消</div>
         <div class="form_text col-3"><a class="btn-link">订单详情</a>
</div>
        </div>
      </div>
      
    </div>
    
    <div class="panel panel-default mb-20">
      <div class="panel-body">
        <div class="responsive">
          <legend>承租方可由以下任一方式交付押金：</legend>
          <div class="row cl">
            <div class="form-text col-6">1、直接支付：<strong class="c-red">支付宝</strong> 或 <strong class="c-red">微信</strong> 移动端直接扫码支付</div>
          </div>
         <div class="row cl">
            <label class="form-text col-6">2、在线代付：任一 <a href="#" class="btn-link">大连市房屋租赁公共服务平台</a> 账户，代为支付</label>
          </div>
          <div class="row cl">
            <label class="form-text col-6">3、线下支付：可在 <a href="#" class="btn-link">银行网点</a> ，窗口支付</label>
          </div>
        </div>
      </div>
    </div>

  </form>
</div>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script> 
<script type="text/javascript" src="lib/laypage/1.2/laypage.js"></script> 
<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/jquery-migrate-1.1.0.min.js"></script>  
<script type="text/javascript" src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="js/H-ui.js"></script> 
<script type="text/javascript" src="js/H-ui.admin.js"></script> 
 <%@ include file="/WEB-INF/views/include/foot.jsp"%>
<script type="text/javascript">
$(function(){
	$('.table-sort').dataTable({
		"aaSorting": [[ 1, "desc" ]],//默认第几个排序
		"bStateSave": true,//状态保存
		"aoColumnDefs": [
		  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
		  {"orderable":false,"aTargets":[0,8,9]}// 制定列不参与排序
		]
	});
	$('.table-sort tbody').on( 'click', 'tr', function () {
		if ( $(this).hasClass('selected') ) {
			$(this).removeClass('selected');
		}
		else {
			table.$('tr.selected').removeClass('selected');
			$(this).addClass('selected');
		}
	});
});

/*用户-还原*/
function member_huanyuan(obj,id){
	layer.confirm('确认要还原吗？',function(index){
		
		$(obj).remove();
		layer.msg('已还原!',{icon: 6,time:1000});
	});
}

/*用户-删除*/
function member_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$(obj).parents("tr").remove();
		layer.msg('已删除!',{icon:1,time:1000});
	});
}
</script>
</body>
</html>