<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="utf-8"%>

<script type="text/javascript" src="dwr/engine.js"></script>
<script type="text/javascript" src="dwr/util.js"></script>
<script type="text/javascript" src="dwr/interface/msgCtl.js"></script>
<script type="text/javascript">

    $(document).ready(function() {
        //这个方法用来启动该页面的ReverseAjax功能
        dwr.engine.setActiveReverseAjax(true);
        //设置在页面关闭时，通知服务端销毁会话       
        dwr.engine.setNotifyServerOnPageUnload(true);
        //设置DWR调用服务出错时，不打印(alert)调试信息    
        dwr.engine.setErrorHandler(function() {
        });
        
        msgCtl.count(cnt);
        
        
    })
    
    
    var id;
    
    var cnt = function(data) {
        dwr.util.setValue('msgCnt', data);
        if (data == 0) {
            $('#msgList').hide();
        }
    }
    
    var result = function(data) {
        var res = JSON.parse(data);
        dwr.util.setValue('tt', res[0].title);
        id=res[0].id;
    }
    function Check() 
    { 
    	msgCtl.count(cnt);
    } 

    function detail(){
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
                msgCtl.count(cnt);
                
            }
        });
    }
    
    
	
    function openInFrame(href,titleName) {
    	var topWindow=$(window.parent.document);
    	var show_nav=topWindow.find('#min_title_list');
    	var ll = show_nav.find("li.ms_check");
    	if(ll.html() == undefined){
    		creatIframe(href,titleName);
    	}
    }
    
	function creatIframe(href,titleName){
		var topWindow=$(window.parent.document);
		var show_nav=topWindow.find('#min_title_list');
		show_nav.find('li').removeClass("active");
		var iframe_box=topWindow.find('#iframe_box');
		show_nav.append('<li class="active ms_check"><span data-href="'+href+'">'+titleName+'</span><i></i><em></em></li>');
		tabNavallwidth();
		var iframeBox=iframe_box.find('.show_iframe');
		iframeBox.hide();
		iframe_box.append('<div class="show_iframe"><div class="loading"></div><iframe frameborder="0" src='+href+'></iframe></div>');
		var showBox=iframe_box.find('.show_iframe:visible');
		showBox.find('iframe').attr("src",href).load(function(){
			showBox.find('.loading').hide();
		});
	}
</script>
<header class="Hui-header cl">
    <a class="Hui-logo l" title="${fns:getConfig('productName')}" href="/"><img src="${ctxStatic}${hui}/images/index_logo.png" width="144" height="26"></a>
    <ul class="Hui-userbar">
		<c:if test="${(fns:getUser().roleList[0].roleType) ==  'emp-role' || (fns:getUser().roleList[0].id) ==  'f73686b7e74d4a81997f15c09ca80b9d'}">
			<li><a href="${fns:getZhzxPath()}/redirect">${fns:getUser().name}</a></li>
		</c:if>
		<c:if test="${(fns:getUser().roleList[0].roleType) !=  'emp-role' && (fns:getUser().roleList[0].id) !=  'f73686b7e74d4a81997f15c09ca80b9d'}">
			<li>${fns:getUser().name}</li>
		</c:if>
        <li class="dropDown dropDown_hover"><a href="a/logout" class="dropDown_A">退出<!-- <i class="Hui-iconfont">&#xe6d5;</i> --></a>
            <!-- <ul class="dropDown-menu radius box-shadow">
                <li><a href="a/logout">退出</a></li>
            </ul> --></li>
        <li id="Hui-msg" class="top_msg"><a href="#" title="消息" onclick="openInFrame('${ctx}/msg/list','站内信列表');"><span class="badge badge-danger" id="msgCnt"></span><i class="Hui-iconfont" style="font-size: 18px">&#xe68a;</i></a>
            <%-- <div class="top_msg_box">
                <ul>
                    <li id="msgList"><a href="#" onclick="detail();" class="link_help">查看<input type="hidden" id="msgId"/></a><span id="tt"></span></li>
                </ul>
                <p class="top_msg_more">
                    <a href="#" onclick="openInFrame('${ctx}/msg/list','站内信列表');">查看全部&gt;&gt;</a>
                </p>
            </div> --%>
        </li>
        <c:if test="${(fns:getUser().roleList[0].roleType) ==  'emp-role' || (fns:getUser().roleList[0].id) ==  'f73686b7e74d4a81997f15c09ca80b9d'}">
        	<li class="back-web-site ml-20"><a href="${fns:getSiteUrl()}/redirect"><i class="Hui-iconfont f-18 mr-5">&#xe625;</i>大连市房屋租赁公共服务平台</a></li>
        </c:if>
    </ul>
    <a aria-hidden="false" class="Hui-nav-toggle" href="#"></a>
</header>