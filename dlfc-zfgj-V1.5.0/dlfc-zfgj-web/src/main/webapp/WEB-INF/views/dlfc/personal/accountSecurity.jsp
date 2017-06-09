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
<div class="pd-20">
  <form action="" method="post" class="form form-horizontal" id="form-admin-add">
    <legend>安全设置：</legend>
    <table class="user_table security_tab">
        <tr>
            <td width="140" ><i class="Hui-iconfont ico_true">&#xe6e1;</i>登录密码:</td>
            <td width="160" >
	            <c:if test="${user.passwordLevel == 1 }"><p class="c-red text-c">低级</p></c:if>
	            <c:if test="${user.passwordLevel == 2 }"><p class="c-orange text-c">中级</p></c:if>
	            <c:if test="${user.passwordLevel > 2 }"><p class="c-green text-c">高级</p></c:if>
            </td>
            <td width="300" class="c-999">包含字母和数字，至少8个字符，区分大小写</td>
         <td><a href="${ctx}/my/pwdc" class="btn-link">修改</a></td>
        </tr>
        <tr>
        <c:if test="${user.email == null or user.email =='' }">
         	<td><i class="Hui-iconfont ico_false">&#xe6dd;</i>邮箱绑定:</td>
            <td class="tc"><p class="c-red text-c">未设置</p></td>
            <td class="c-999">可以通过邮箱登录和找回密码</td>
            <td><a href="${ctx}/my/mb" class="btn-link">设置</a></td>
        </c:if>
        <c:if test="${user.email != null or user.email !='' }">
         	<td><i class="Hui-iconfont ico_true">&#xe6e1;</i>邮箱绑定:</td>
            <td class="tc"><p class="c-green text-c">已设置</p></td>
            <td class="c-999">可以通过邮箱登录和找回密码</td>
            <td><a href="${ctx}/my/mb" class="btn-link">修改</a></td>
        </c:if>
           
        </tr>
        <tr>
            <td width="140" ><i class="Hui-iconfont ico_true">&#xe6e1;</i>手机绑定:</td>
            <td class="tc"><p class="text-c">${fn:substring(user.mobile,0,3)}****${fn:substring(user.mobile,7,11)}</p></td>
            <td class="c-999">可以通过手机登录和找回密码</td>
            <td><a href="${ctx}/my/mobilec" class="btn-link">修改</a></td>
            <img src="${file}" >
        </tr>
     </table>
  </form>
</div>
<div>
${user.username} <a href="${ctx}/my/pwdc">修改密码</a><a href="${ctx}/my/mb">绑定邮箱</a><a href="${ctx}/my/mobilec">修改手机</a><a href="${ctx}/agtc/login">从业</a><a href="${ctx}/my/mul">多图片上传</a><c:if test="${user.email == null}">123123</c:if>
	</div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/icheck/jquery.icheck.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/Validform/5.3.2/Validform.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/lib/layer/1.9.3/layer.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/js/H-ui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/hui/js/H-ui.admin.js"></script>
</body>
</html>