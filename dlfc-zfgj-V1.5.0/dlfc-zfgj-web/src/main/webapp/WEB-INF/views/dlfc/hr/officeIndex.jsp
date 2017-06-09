<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>机构管理</title>
<meta name="decorator" content="themehui" />
<%@include file="/WEB-INF/views/include/treeview.jsp"%>
<style type="text/css">
.ztree {
	overflow: auto;
	margin: 0;
	_margin-top: 10px;
	padding: 10px 0 0 10px;
}
.ztree > li{height:93%;overflow-y:auto}

.close{position:relative;opacity:1 !important;}
.open{position:relative;opacity:1 !important;}
#refresh{text-decoration:none;}
#refresh:hover{background:none;}
.close i{display:block; position:absolute; width:7px; height:40px; background:#000; right:-5px; top:339px; background: url("${ctxStatic}/hui/skin/blue/icon_arrow.png") no-repeat  0 -10px;}
.close i:hover{background-position:-21px -10px}
.open i{display:block; position:absolute; width:7px; height:40px; background:#000;  right:-5px;  top:339px;background: url("${ctxStatic}/hui/skin/blue/icon_arrow.png") no-repeat  0 -70px;}
.open i:hover{background-position:-21px -70px}
</style>
</head>
<body>
<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span><i class="Hui-iconfont"></i> 人事管理 <span class="c-gray en">&gt;</span>
		部门管理 <span class="c-gray en"></span><a
			class="btn btn-success radius r mr-20"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<sys:message content="${message}" />
	<div id="content" class="row-fluid">

		<div id="left" class="accordion-group">
<!-- 					href="javascript:location.replace(location.href);" title="刷新"><i -->
			<div class="tree_til">
				组织机构<a class="r mr-20" style="line-height: 1.6em; margin-top: 8px" id="refresh"
					href="${ctx}/hr/office/" title="刷新">
					<i class="Hui-iconfont">&#xe68f;</i></a>
			</div>
			<div id="ztree" class="ztree" style="height:94%;"></div>
		</div>
		<div id="openClose" class="close"><i></i></div>
		<div id="right">
			<iframe id="officeContent"
				src="<%-- ${ctx}/hr/office/form?id=&parentIds= --%>" width="100%"
				height="91%" frameborder="0"></iframe>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function(){
			refreshTree();
		})
		function refresh(){
			location.href="${ctx}/hr/office/";
		}
		var setting = {
			view : {
				addHoverDom : addHoverDom,
				removeHoverDom : removeHoverDom,
				selectedMulti : false
			},
			edit : {
				drag : {
					autoExpandTrigger : true,
					prev : dropPrev,
					inner : dropInner,
					next : dropNext
				},
				enable : true,
				showRemoveBtn : showRemoveBtn,
				showRenameBtn : false
			},
			data : {
				simpleData : {
					enable : true,
					idKey : "id",
					pIdKey : "pId",
					rootPId : '0',
				}
			},
			callback : {
				beforeDrag : beforeDrag,
				beforeDrop : beforeDrop,
				beforeRemove: beforeRemove,
				onDrop:onDrop,
				beforeDragOpen : true,
				beforeCollapse: zTreeBeforeCollapse,
				onRemove : function(event, treeId, treeNode) {
// 					alert("我被删了"+treeNode.id);
				},
				onClick : function(event, treeId, treeNode) {
					//判断是修改还是新增
					var id = event.target.id;
                    var bool = id.indexOf("addBtn");
					if ( bool >= 0) {
						//新增
						$('#officeContent').attr( "src","${ctx}/hr/office/form?parent.id=" + treeNode.id);
					}else {
						if(treeNode.pId != '0'){//跟节点不能修改
							$('#officeContent').attr( "src", "${ctx}/hr/office/form?id=" + treeNode.id
											+ "&parentIds=" + treeNode.pIds);
						} 
					}
				}
			}
		};

		function addHoverDom(treeId, treeNode) {
			var sObj = $("#" + treeNode.tId + "_span");
			if (treeNode.editNameFlag
					|| $("#addBtn_" + treeNode.tId).length > 0)
				return;
			var addStr = "<span class='ZtreeAdd_button' id='addBtn_"
					+ treeNode.tId
					+ "' title='add node' onfocus='this.blur();'></span>";
			sObj.after(addStr);
			var btn = $("#addBtn_" + treeNode.tId);
			if (btn){
				btn.bind("click", function() {
				});
			}
		};

		function removeHoverDom(treeId, treeNode) {
			$("#addBtn_" + treeNode.tId).unbind().remove();
		};

		function showRemoveBtn(treeId, treeNode) {
			//如果是父节点或者是顶级节点不允许显示删除按钮
			if (treeNode.isParent || treeNode.parentTId == null || treeNode.parentTId == '') {
				return false;
			} else {
				return true;
			}
		}
		function dropPrev(treeId, nodes, targetNode) {
			if (targetNode.level == 0) {
				return false;
			}
			return true;
		}
		function dropInner(treeId, nodes, targetNode) {
			return true;
		}
		function dropNext(treeId, nodes, targetNode) {
			if (targetNode.level == 0) {
				return false;
			}
			return true;
		}
		function beforeDrag(treeId, treeNodes) {
			for (var i = 0, l = treeNodes.length; i < l; i++) {
				if (treeNodes[i].isParent) {
					return false;
				}
			}
			return true;
		}
		function beforeDrop(treeId, treeNodes, targetNode, moveType, isCopy) {
			if(targetNode != null){//不允许拖拽到根节点
			    if(confirm("确定要拖拽到此处吗？")){
			        return true;
			    }
			}
		    return false;
		}
		function onDrop(event, treeId, treeNodes, targetNode, moveType, isCopy) {
			var parentId;
			var parentIds;
			if (moveType == "inner") {
				parentId = targetNode.id;
				parentIds = getParentIds(targetNode);
			} else {
				parentId = targetNode.getParentNode().id;
				parentIds = getParentIds(targetNode.getParentNode());
			}
			$.ajax({
				type : "POST",
				url : "${ctx}/hr/office/update",
				data : {"id":treeNodes[0].id,
						"parentId":parentId,
						"parentIds":parentIds				
						},
				async : false,
				dataType : "json",
				success : function(data) {
					if (data.success == 0) {
				        layer.msg(data.message);
				    }
				},
				error : function(data) {
					layer.alert("操作失败");
				}
			});
		}
		function getParentIds(targetNode) {
			var nodeIds = [];
			while (targetNode) {
				nodeIds.push(targetNode.id);
				targetNode = targetNode.getParentNode();
			}
			if (nodeIds.length > 0) {
				nodeIds.push("0");
			}
			var parentIds;
			for (var i = 0; i < nodeIds.length; i++) {
				if (i == 0) {
					parentIds = nodeIds[i];
				} else {
					parentIds = nodeIds[i] + "," + parentIds;
				}
			}
			return parentIds;
		}
		function beforeRemove(treeId, treeNode) {
			var flag = false;
			if(confirm('确认删除'+treeNode.name+'吗?')){
				$.ajax({
					type : "POST",
					url : "${ctx}/hr/office/deleteAjax",
					data : "id=" + treeNode.id,
					async : false,
					dataType : "json",
					success : function(data) {
						if (data.success == 0) {
					        layer.msg(data.message);
					    }else{
					    	flag = true;
					    }
					},
					error : function(data) {
						layer.alert("操作失败");
					}
				});
				return flag;
			}else{
				return false;
			}
		}
		function zTreeBeforeCollapse(){//禁止树收缩
			return true;
		}

		function refreshTree() {
			var noCache = Date();
			$.getJSON("${ctx}/hr/office/treeData",{ "noCache": noCache }, function(data) {
				$.fn.zTree.init($("#ztree"), setting, data).expandAll(true);
			});
		}

		var leftWidth = 280; // 左侧窗口大小
		var htmlObj = $("html"), mainObj = $("#main");
		var frameObj = $("#left, #openClose, #right, #right iframe");
		function wSize() {
			var strs = getWindowSize().toString().split(",");
			htmlObj.css({
				"overflow-x" : "hidden",
				"overflow-y" : "hidden"
			});
			mainObj.css("width", "auto");
			frameObj.height(strs[0] - 5);
			var leftWidth = ($("#left").width() < 0 ? 0 : $("#left").width());
			$("#right").width(
				$("#content").width() - leftWidth - $("#openClose").width() - 5);
			$(".ztree").width(leftWidth - 10).height(frameObj.height() - 46);
		}
	</script>
	<script src="${ctxStatic}/common/wsize.min.js" type="text/javascript"></script>
</body>
</html>