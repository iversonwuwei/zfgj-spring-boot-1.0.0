<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head2.jsp"%>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/banner.jsp"%>
	<aside class="Hui-aside">
		<input runat="server" id="divScrollValue" type="hidden" value="" />
		<input id="menuList" type="hidden" value="${rootMenu}" />
		<div class="menu_dropdown bk_2">

			<c:set var="firstMenu" value=""></c:set>
			<c:set var="firstMenuName" value=""></c:set>
			<c:set var="hasFirst" value="true"></c:set>
			<c:forEach items="${rootMenu}" var="menu" varStatus="idxStatus">
				<c:if test="${menu.parent.id eq '1'&&menu.isShow eq '1'}">
					<dl>
						<dt class="menu_title" id="myFirstMenu" >${menu.name }</dt>
					</dl>
					<!-- 初始化首页 -->
					<c:if test="${not empty menu.href and hasFirst=='true'}">
						<c:set var="firstMenu" value="${fn:indexOf(menu.href, '://') eq -1 ? ctx : ''}${menu.href}"></c:set>
						<c:set var="firstMenuName" value="${menu }"></c:set>
						<c:set var="hasFirst" value="false"></c:set>
					</c:if>

					<c:forEach items="${fns:getMenuList2(menu)}" var="cmenu"
						varStatus="idxStatus2">
						<!-- 初始化首页 -->
						<c:if test="${not empty cmenu.href and hasFirst=='true'}">
							<c:set var="firstMenu" value="${fn:indexOf(cmenu.href, '://') eq -1 ? ctx : ''}${cmenu.href}"></c:set>
							<c:set var="firstMenuName" value="${cmenu.name }"></c:set>
							<c:set var="hasFirst" value="false"></c:set>
						</c:if>
						<dl id="menu-article">
							<dt>
								<i class="boot_icons ${cmenu.icon }"></i> ${cmenu.name }<i
									class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
							</dt>
							<dd>
								<ul>
									<c:forEach items="${fns:getMenuList2(cmenu)}" var="dmenu"
										varStatus="idxStatus2">
										<!-- 初始化首页 -->
										<c:if test="${not empty dmenu.href and hasFirst=='true'}">
											<c:set var="firstMenu" value="${fn:indexOf(dmenu.href, '://') eq -1 ? ctx : ''}${dmenu.href}"></c:set>
											<c:set var="firstMenuName" value="${dmenu.name }"></c:set>
											<c:set var="hasFirst" value="false"></c:set>
										</c:if>
										<li><c:if test="${empty dmenu.href}">

												<a _href="${ctx}/sys/menu/tree?parentId=${dmenu.id}"
													href="javascript:void(0)"><i
													class="boot_icons ${dmenu.icon }"></i> ${dmenu.name }</a>
											</c:if> <c:if test="${not empty dmenu.href}">
												<a id="${dmenu.id}" _href="${fn:indexOf(dmenu.href, '://') eq -1 ? ctx : ''}${dmenu.href}"
													href="javascript:void(0)"><i
													class="boot_icons ${dmenu.icon }"></i> ${dmenu.name }</a>
											</c:if></li>
									</c:forEach>
								</ul>
							</dd>
						</dl>
					</c:forEach>
				</c:if>
			</c:forEach>
		</div>
	</aside>
	<div class="dislpayArrow">
		<a class="pngfix" href="javascript:void(0);"
			onClick="displaynavbar(this)"></a>
	</div>
	<section class="Hui-article-box">
		<div id="Hui-tabNav" class="Hui-tabNav">
			<div class="Hui-tabNav-wp">
				<ul id="min_title_list" class="acrossTab cl">
					<li class="active"><span title="我的桌面"
						data-href="${firstMenu }">${firstMenuName }</span><em></em></li>
				</ul>
			</div>
			<div class="Hui-tabNav-more btn-group">
				<a id="js-tabNav-prev" class="btn radius btn-default size-S"
					href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a
					id="js-tabNav-next" class="btn radius btn-default size-S"
					href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a>
			</div>
		</div>
		<div id="iframe_box" class="Hui-article">
			<div class="show_iframe">
				<div style="display: none" class="loading"></div>
				<iframe id="myFrame" scrolling="yes" frameborder="0" src="${firstMenu }"></iframe>
			</div>
		</div>
	</section>

	<%@ include file="/WEB-INF/views/include/foot.jsp"%>

	<script type="text/javascript">
		$(function() {
			var menu = $("#menuList").val();
			if(menu == null || menu == '[]' || menu == ''){
				layer.msg("角色被禁用，或者无任何菜单权限！");
			}
		});
		
		$('#myFirstMenu').on('click', function() {
			$("#myFrame").attr("src","${firstMenu }"); 
			window.location.replace(window.location.href);
		});
	
		/*资讯-添加*/
		function article_add(title, url) {
			var index = layer.open({
				type : 2,
				title : title,
				content : url
			});
			layer.full(index);
		}
		/*图片-添加*/
		function picture_add(title, url) {
			var index = layer.open({
				type : 2,
				title : title,
				content : url
			});
			layer.full(index);
		}
		/*产品-添加*/
		function product_add(title, url) {
			var index = layer.open({
				type : 2,
				title : title,
				content : url
			});
			layer.full(index);
		}
		/*用户-添加*/
		function member_add(title, url, w, h) {
			layer_show(title, url, w, h);
		}
		
		/*首页-委托房源*/
		function entrust(){
			$("#57caf5c431284ad0b7a93ea94fb74b39").click();
		}
		
		/*首页-验房预约*/
		function appointment(){
			$("#78ce9b665fda4130b86ad6630f8d98bc").click();
		}
		
		
	</script>

</body>
</html>