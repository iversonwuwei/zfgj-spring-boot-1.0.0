<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head2.jsp"%>
<script type="text/javascript">
function page(n,s){
    $("#pageNo").val(n);
    $("#pageSize").val(s);
    $("form").submit();
    return false;
}

function detail(id){
    layer.open({
        type: 2,
        title: false,
        shadeClose: true,
        closeBtn: 2,
        shade: 0.7,
        area: ['700px', '400px'],
        shift: 5,
        content: '${ctx}/msg/detail?id='+id,
        cancel : function(){
            $("form").submit();
            parent.msgCtl.count(parent.cnt);
            parent.msgCtl.getFirstTitle(parent.result);
        }
    });
}

function selected() {
    $("form").submit();
}
</script>
</head>
<body>
<form id="form" action="${ctx}/msg/list" method="post">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <div class="pd-20">
        <div class="cl pd-5 bg-1 bk-gray mt-20">
            <span class="l"></span>
            <span class="select-box r inline"> 
                <form:select class="select" path="msg.status" id="status" onchange="selected()">
        			<form:option value="9">全部</form:option>
                    <form:option value="1">已读</form:option>
                    <form:option value="0">未读</form:option>
        		</form:select>
            </span>
        </div>
        <table class="table table-border table-bordered table-bg table-hover table-sort">
            <thead>
                <tr class="text-c">
                    <th width="25">序号</th>
                    <th width="40">状态</th>
                    <th>标题</th>
                    <th width="120">发信人</th>
                    <th width="120">发送时间</th>
                    <th width="180">操作</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${page.list}" var="msg" varStatus="status">
                    <tr class="text-c">
                        <td>${status.index+1}</td>
                        <td>
                            <c:if test="${msg.status == '0'}">未读</c:if>
                            <c:if test="${msg.status == '1'}">已读</c:if>
                        </td>
                        <td class="text-l"><a href="#" onclick="detail('${msg.id}');" ><u>${msg.title}</u></td>
                        <td>${msg.senderName}</td>
                        <td><fmt:formatDate value="${msg.sendTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td class="f-14 td-manage"><a href="${ctx}/msg/del?id=${msg.id}" class="btn btn-danger">删除</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    <div class="pagination">${page}</div>
    </div>
</form>
<%@ include file="/WEB-INF/views/include/foot.jsp"%>
</body>
</html>