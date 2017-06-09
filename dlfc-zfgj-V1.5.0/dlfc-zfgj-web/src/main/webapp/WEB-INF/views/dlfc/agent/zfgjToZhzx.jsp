<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<!DOCTYPE HTML>
<html>
<head>

<%@ include file="/WEB-INF/views/include/head2.jsp"%>
<title></title>
	<script type="text/javascript">
</script>
</head>
<body>
<form id="testForm" method="get" action="${fns:getZhzxPath()}/redirect" >
<input type="hidden" name="url" value="/a/vertifyInterface?optkey=3&&srcURL=${fns:getZhzxPath()}/f/my/bainfo&&srcMethod=get">
</form>
<script type="text/javascript">
var srcForm=$("#testForm");
srcForm.submit();
</script>
</body>
</html>