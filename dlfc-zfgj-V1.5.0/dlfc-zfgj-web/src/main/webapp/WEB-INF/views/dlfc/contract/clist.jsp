﻿<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>	
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<!DOCTYPE HTML>
<html>
<head>

<%@ include file="/WEB-INF/views/include/head2.jsp"%>

<title>添加管理员</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 租房合同<span class="c-gray en">&gt;</span> 合同列表<a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="content">
  <div class="mt-20">
    <legend>合同列表</legend>
     <div class="list_tabs cl ">
     <c:choose>
     	<c:when test="${root == null || root == ''}">
    	<a>个人合同</a>
    	</c:when>
    	<c:otherwise>
    	 <a onclick="doSort('enname', '')" <c:if test="${enname == '' || enname == null}">class="active"</c:if> >个人合同</a>
    	</c:otherwise>
    </c:choose>
    <c:if test="${root != null && root != ''}">
    <a onclick="doSort('enname', 'manager')" <c:if test="${enname == 'manager'}">class="active"</c:if>  >部门合同</a>
    </c:if>
    </div>
    <form name="contractlist" action="${ctx}/con/clist" id="contractlist">			
			<input type="hidden" id="tSort" name="tSort" value="${tSort}"/>
			<input type="hidden" id="sSort" name="sSort" value="${sSort}"/>
			<input type="hidden" id="eSort" name="eSort" value="${eSort}"/>
			<input type="hidden" id="st" name="st" value="${st}"/>
			<input type="hidden" id="enname" name="enname"  />
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	</form>
	<form name="cancelForm" action="${ctx}/con/del" id="cancelForm"
			method="post">
			<input type="hidden" name="cid" id="cid" />
	</form>
	<form   action="${ctx}/con/dell" id="delForm"
			method="post">
			<input type="hidden" name="cid" id="delcid" />
		</form>
    <div class="list_head cl ">
    	<div class="house-list-tab cl ">
    <a href="${ctx}/con/clist?st=5" ><span <c:if test="${st == 5}">class="current"</c:if>>生效中 </span></a><a href="${ctx}/con/clist?st="><span <c:if test="${st == null || st == '' }">class="current"</c:if>>全部</span></a>
    	</div>
    </div>
    <div class="list_sort cl">   
    <c:choose>
		<c:when test="${tSort == null || tSort ==  '' || tSort == 'none'}">
			<a id="allsort" href="javascript:void(0);" title="无排序" onclick="doSort('tSort', 'desc')"  >创建时间 <span>&uarr;</span></a>
		</c:when>
		<c:when test="${tSort == 'desc'}">
			<a id="upsort" href="javascript:void(0);" title="降序" onclick="doSort('tSort', 'asc')" class="active">创建时间 <span>&darr;</span></a>
		</c:when>
		<c:when test="${tSort == 'asc'}">
			<a id="downsort" href="javascript:void(0);" title="升序" onclick="doSort('tSort', 'none')" class="active">创建时间 <span>&uarr;</span></a>
		</c:when>
	</c:choose>
	 <c:choose>
		<c:when test="${sSort == null || sSort ==  '' || sSort == 'none'}">
			<a id="allsort" href="javascript:void(0);" title="无排序 " onclick="doSort('sSort', 'desc')"  >生效时间 <span>&uarr;</span></a>
		</c:when>
		<c:when test="${sSort == 'desc'}">
			<a id="upsort" href="javascript:void(0);" title="降序" onclick="doSort('sSort', 'asc')" class="active">生效时间 <span>&darr;</span></a>
		</c:when>
		<c:when test="${sSort == 'asc'}">
			<a id="downsort" href="javascript:void(0);" title="升序" onclick="doSort('sSort', 'none')" class="active">生效时间 <span>&uarr;</span></a>
		</c:when>
	</c:choose>
	<c:choose>
		<c:when test="${eSort == null || eSort ==  '' || eSort == 'none'}">
			<a id="allsort" href="javascript:void(0);" title="无排序 " onclick="doSort('eSort', 'desc')"  >终止时间 <span>&uarr;</span></a>
		</c:when>
		<c:when test="${eSort == 'desc'}">
			<a id="upsort" href="javascript:void(0);" title="降序" onclick="doSort('eSort', 'asc')" class="active">终止时间 <span>&darr;</span></a>
		</c:when>
		<c:when test="${eSort == 'asc'}">
			<a id="downsort" href="javascript:void(0);" title="升序" onclick="doSort('eSort', 'none')" class="active">终止时间 <span>&uarr;</span></a>
		</c:when>
	</c:choose>  
   </div>
    <table class="table table-border table-bordered  table-bg">
      <thead>
        <tr class="text-c">
          <th colspan="2">合同详情</th>
          <th width="130">合同期限</th>
          <th width="80">押金</th>
          <th width="100">合同状态</th>
          <th width="140">操作</th>
        </tr>
      </thead>
    </table>
    <c:forEach items="${page.list}" var="contractlist">
    <table class="table table-border table-bordered  table-bg mb-20">
    <tr class="text-l">
          <td colspan="7" class="hoser_info"><div class="row cl">
              <div class="col-3"> <fmt:formatDate value="${contractlist.createTime}" pattern="yyyy-MM-dd " />  </div>
              <div class="col-6"> 合同编号：${contractlist.no}</div>
            </div></td>
        </tr>
        <tr class="text-c">
          <td><p class="house_name"><span  >${contractlist.houseAddr}</span></p>
          		<c:if test="${contractlist.rentalMode == 2}">
            <p class="house_name"><span class="label label-secondary">${contractlist.leaseDomain}</span></p>
            	</c:if>
            	<c:if test="${contractlist.rentalMode == 1}">
            <p class="house_name"><span class="label label-secondary">整租</span></p>
            	</c:if>
            </td>
          <td width="140"><p>月租金</p>
            <p>&yen;<strong class="c-orange">${contractlist.monthlyRent}</strong>/月</p></td>
          <td width="130"><p>生效：<fmt:formatDate value="${contractlist.startTime}" pattern="yyyy-MM-dd " /></p>
            <p>终止：<fmt:formatDate value="${contractlist.endTime}" pattern="yyyy-MM-dd " /></p></td>
          <td width="80"><p>&yen; ${contractlist.depositAmt}</p>
          </td>
          <c:choose>
          <c:when test="${contractlist.status == 5}">
          	<c:choose>
          		<c:when test="${contractlist.conDissAgr != null}">
          		<shiro:hasPermission name="emp:cont:add">
          <td width="100"><p class="c-green">合同生效</p>
            <a href="${ctx}/con/step5?cid=${contractlist.id}">合同详情</a></td>
            <td class="text-c" width="140"></td>
          		</shiro:hasPermission>
          		</c:when>
          		<c:otherwise>
          		<shiro:hasPermission name="emp:cont:add">
          		<td width="100"><p class="c-green">合同生效</p>
            	<a href="${ctx}/con/step5?cid=${contractlist.id}">合同详情</a></td>
          		<td class="text-c" width="140"></td>
          		</shiro:hasPermission>
          		</c:otherwise>
          	</c:choose>
          </c:when>
          <c:when test="${contractlist.status == 6 }">
          	<c:choose>
          	<c:when test="${contractlist.conDissAgr != null && contractlist.conDepositDistAgr != null}">
          	<shiro:hasPermission name="emp:cont:add">
          <td width="100"><p class="c-red">合同终止</p>
            <a href="${ctx}/con/step5?cid=${contractlist.id}">合同详情</a></td>
          <td class="text-c" width="140">
          <shiro:hasPermission name="emp:cont:deposit" >
          </shiro:hasPermission>
          </td>
          </shiro:hasPermission>
          	</c:when>
          	<c:otherwise>
          	<shiro:hasPermission name="emp:cont:add">
          	<td width="100"><p class="c-red">合同终止</p>
            <a href="${ctx}/con/step5?cid=${contractlist.id}">合同详情</a></td>
          <td class="text-c" width="140"> 
          <shiro:hasPermission name="emp:cont:deposit" >
          </shiro:hasPermission>
          </td>
          	</shiro:hasPermission>
          	</c:otherwise>
          </c:choose>
          </c:when>
          <c:when test="${contractlist.status == 7}">
          <td width="100"><p class="c-red">合同终止</p>
            <a href="${ctx}/con/step5?cid=${contractlist.id}">合同详情</a></td>
          <td class="text-c" width="140"> <a class="btn btn-primary radius"  href="${ctx}/con/step5?cid=${contractlist.id}">查看详情</a>  
          </td>
          </c:when>
          <c:when test="${contractlist.status == 8}">
          <shiro:hasPermission name="emp:cont:add">
          <td width="100"><p>已完成</p>
            <a href="${ctx}/con/step5?cid=${contractlist.id}">合同详情</a></td>
          <td class="text-c" width="140"><a class="btn btn-primary radius" href="javascript:void(0);"onclick="delCon('${contractlist.id}')" >删除</a></td>
          </shiro:hasPermission>
          </c:when>
          <c:when test="${contractlist.status == 11}">
          <shiro:hasPermission name="emp:cont:add">
          <td width="100"><p>合同无效</p>
            <a href="${ctx}/con/step5?cid=${contractlist.id}">合同详情</a></td>
          <td class="text-c" width="140"><a class="btn btn-primary radius" href="javascript:void(0);"onclick="delCon('${contractlist.id}')" >删除</a></td>
          </shiro:hasPermission>
          </c:when>
          <c:when test="${contractlist.status == 9}">
          <shiro:hasPermission name="emp:cont:add">
          <td width="100"><p class="c-red">审核中</p>
            <a href="${ctx}/con/step5?cid=${contractlist.id}">合同详情</a></td>
          <td class="text-c" width="140"></td>
          </shiro:hasPermission>
          </c:when>
          <c:when test="${contractlist.status == 10}">
          <shiro:hasPermission name="emp:cont:add">
          <td width="100"><p class="c-red">审核失败</p>
            <a href="${ctx}/con/step5?cid=${contractlist.id}">合同详情</a></td>
          <td class="text-c" width="140"></td>
          </shiro:hasPermission>
          </c:when>
          <c:when test="${contractlist.status == 13}">
          <shiro:hasPermission name="emp:cont:add">
          <td width="100"><p class="c-red">等待上传</p>
            <a href="${ctx}/con/step5?cid=${contractlist.id}">合同详情</a></td>
          <td class="text-c" width="140"></td>
          </shiro:hasPermission>
          </c:when>
          <c:when test="${contractlist.status == 1}">
          <shiro:hasPermission name="emp:cont:add">
          <td width="100"><p class="c-red">创建中</p>
            </td>
          <td class="text-c" width="140"><a class="btn btn-primary radius" href="${ctx}/con/step1?cid=${contractlist.id}" >修改</a> <a class="btn btn-primary radius"  onclick="del('${contractlist.id}')">取消</a></td>
          </shiro:hasPermission>
          </c:when>
          </c:choose>
        </tr>
    </table>
    </c:forEach>
    <div class="pagination">${page}</div>
  </div>
</div>
 <%@ include file="/WEB-INF/views/include/foot.jsp"%>
<script type="text/javascript">
$(function(){
	$('.pos-r a').mouseover(function(){
	$(this).parent().children('.deposit-tips').show(30);
	});
	$('.pos-r a').mouseleave(function(){
	$(this).parent().children('.deposit-tips').hide(10);
	});
	
	$('#tranferFrom').validationEngine('attach', { binded : false ,showOneMessage: true,maxErrorsPerField : 1,promptPosition: 'centerRight',addPromptClass: 'formError-noArrow formError-text',promptPosition: 'centerRight:80,0'});
	
	$('#save_tranfer').click(function(){
		jQuery('#tranferFrom').validationEngine('validate');
	});
});

function dissag(id){
	window.location.href="${ctx}/con/dissd?cid="+id
};

function del(cid){
	layer.confirm("确定取消这个合同吗？", function() {
		$("#cid").val(cid);
		$("#cancelForm").submit();
	}, function() {
	});
}

function page(n,s){
	$("#pageNo").val(n);
	$("#pageSize").val(s);
	doSearch();
	return false;
}

function doSort(prop, sort) {
	$("#" + prop).val(sort);
	doSearch();
}

function doSearch() {
	var form = document.forms['contractlist'];
	form.submit();
}
function delCon(cid) {
	layer.confirm("确定删除这个合同吗？", function() {
		$("#delcid").val(cid);
		$("#delForm").submit();
	}, function() {
	});
};

</script>
</body>
</html>