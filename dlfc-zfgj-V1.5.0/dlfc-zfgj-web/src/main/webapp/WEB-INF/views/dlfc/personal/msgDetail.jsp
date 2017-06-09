<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head2.jsp"%>
<script type='text/javascript' src='/dwr/engine.js'></script>
<script type="text/javascript" src='/dwr/util.js'></script>
<script type="text/javascript" src='/dwr/interface/msgCtl.js'></script>
<script type="text/javascript">
$(document).ready(function() {
    //这个方法用来启动该页面的ReverseAjax功能
    dwr.engine.setActiveReverseAjax(true);
    //设置在页面关闭时，通知服务端销毁会话       
    dwr.engine.setNotifyServerOnPageUnload(true);
    //设置DWR调用服务出错时，不打印(alert)调试信息    
    dwr.engine.setErrorHandler(function() {
    });
    msgCtl.read($('#msgId').val());
})
</script>
</head>
<body>
    <div class="pd-20">
        <form action="" method="post" class="form form-horizontal" id="form-member-add">
            <div class="panel-body">
                <table class="table table-border table-bordered"><input type="hidden" id="msgId" value="${msgDetail.id}"/>
                    <tbody>
                        <tr>
                            <td class="text-r" width="120">标题：</td>
                            <td>${msgDetail.title}</td>
                        </tr>
                        <tr>
                            <td class="text-r">发信时间：</td>
                            <td><fmt:formatDate value="${msgDetail.sendTime}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
                        </tr>
                        <tr>
                            <td class="text-r">发信人：</td>
                            <td>${msgDetail.senderName}</td>
                        </tr>
                        <tr>
                            <td colspan="2">${msgDetail.content}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </form>
    </div>
    <%@ include file="/WEB-INF/views/include/foot.jsp"%>
</body>
</html>