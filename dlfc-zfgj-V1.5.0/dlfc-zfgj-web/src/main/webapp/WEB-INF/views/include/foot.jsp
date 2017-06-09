<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<div id="footer" class="footer">
</div>
<script type="text/javascript" src="${ctxStatic}/hui/lib/layer/1.9.3/layer.js"></script> 
<script type="text/javascript" src="${ctxStatic}/hui/js/H-ui.js"></script> 
<script type="text/javascript" src="${ctxStatic}/hui/js/H-ui.admin.js"></script>
<link href="${ctxStatic}/hui/lib/jQuery-Validation-Engine-master/css/validationEngine.jquery.css" type="text/css" rel="stylesheet" />
<link href="${ctxStatic}/hui/lib/jQuery-Validation-Engine-master/css/validationEngine_modify.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/hui/lib/jQuery-Validation-Engine-master/js/jquery.validationEngine.js" type="text/javascript"></script>
<script src="${ctxStatic}/hui/lib/jQuery-Validation-Engine-master/js/languages/jquery.validationEngine-zh_CN.js" type="text/javascript"></script>
<!-- webuploader -->
<script type="text/javascript" src="${ctxStatic}/hui/lib/webuploader/0.1.5/webuploader.js"></script>
<%-- <script type="text/javascript" src="${ctxStatic}${dlzf}lib/webuploader/0.1.5/webuploaderdlfc.js"></script> --%>
<script type="text/javascript" src="${ctxStatic}/hui/lib/webuploader/0.1.5/mulImg.js"></script>
<script src="${ctxStatic}/common/common.js" type="text/javascript"></script>

<%--
<script type="text/javascript">
$(function(){
    $("#footer").load("${ctx}/head/bottom?time="+new Date().getTime());
});
</script>
--%>

<script>
	$(function(){
		<c:if test="${error != null}">
			initMsgBox('error', '${error}');
		</c:if>
		<c:if test="${warning != null}">
			initMsgBox('warning', '${warning}');
		</c:if>
		<c:if test="${message != null}">
			initMsgBox('message', '${message}');
		</c:if>
	});
		
// 	/**
// 	 * 初始化返回信息显示框
// 	 * 
// 	 * @param type
// 	 * @param msg
// 	 */
// 	function initMsgBox(type, msg) {
// 		var $msgBox = $(".message_box");
		
// 		if ($msgBox.length <= 0) {
// 			$(".breadcrumb").after("<div class='message_box'></div>");
// 		}
		
// 		var mapper = {
// 			error : 'Huialert Huialert-danger',
// 			warning : 'Huialert Huialert-danger',
// 			message : 'Huialert Huialert-success'
// 		}
		
// 		var cssName = mapper[type];
		
// 		$(".message_box").append("<div class='" + cssName + "'><a onclick='removeMeg()' ><i id='iconfont' class='Hui-iconfont'>&#xe6a6;</i></a>" + msg + "</div>");
// 	}
// 	function removeMeg(){
// 		$(".message_box").remove();
// 	}

</script>

<!-- 百度统计 -->
<script>
var _hmt = _hmt || [];
(function()
{ var hm = document.createElement("script"); hm.src = "https://hm.baidu.com/hm.js?9b6363c5b11906c0f3f981f776b0b560"; var s = document.getElementsByTagName("script")[0]; s.parentNode.insertBefore(hm, s); }
)();
</script>

<!-- Piwik -->
<script type="text/javascript">
    var _paq = _paq || [];
    _paq.push(['trackPageView']);
    _paq.push(['enableLinkTracking']);
    (function() {
    var u="//dlzf.piwikpro.com/";
    _paq.push(['setTrackerUrl', u+'piwik.php']);
    _paq.push(['setSiteId', 1]);
    var d=document, g=d.createElement('script'), s=d.getElementsByTagName('script')[0];
    g.type='text/javascript'; g.async=true; g.defer=true; g.src=u+'piwik.js'; s.parentNode.insertBefore(g,s);
    })();
    </script>
<noscript><p><img src="//dlzf.piwikpro.com/piwik.php?idsite=1" style="border:0;" alt="" /></p></noscript>
<!-- End Piwik Code -->