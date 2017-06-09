<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="编号"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="隐藏域名称（ID）"%>
<%@ attribute name="value" type="java.lang.String" required="false" description="隐藏域值（ID）"%>
<%@ attribute name="labelName" type="java.lang.String" required="false" description="输入框名称（Name）"%>
<%@ attribute name="labelValue" type="java.lang.String" required="false" description="输入框值（Name）"%>
<%@ attribute name="title" type="java.lang.String" required="false" description="选择框标题"%>
<%@ attribute name="url" type="java.lang.String" required="false" description="树结构数据地址"%>
<%@ attribute name="checked" type="java.lang.Boolean" required="false" description="是否显示复选框，如果不需要返回父节点，请设置notAllowSelectParent为true"%>
<%@ attribute name="extId" type="java.lang.String" required="false" description="排除掉的编号（不能选择的编号）"%>
<%@ attribute name="isAll" type="java.lang.Boolean" required="false" description="是否列出全部数据，设置true则不进行数据权限过滤（目前仅对Office有效）"%>
<%@ attribute name="notAllowSelectRoot" type="java.lang.Boolean" required="false" description="不允许选择根节点"%>
<%@ attribute name="notAllowSelectParent" type="java.lang.Boolean" required="false" description="不允许选择父节点"%>
<%@ attribute name="module" type="java.lang.String" required="false" description="过滤栏目模型（只显示指定模型，仅针对CMS的Category树）"%>
<%@ attribute name="selectScopeModule" type="java.lang.Boolean" required="false" description="选择范围内的模型（控制不能选择公共模型，不能选择本栏目外的模型）（仅针对CMS的Category树）"%>
<%@ attribute name="allowClear" type="java.lang.Boolean" required="false" description="是否允许清除"%>
<%@ attribute name="allowInput" type="java.lang.Boolean" required="false" description="文本框可填写"%>
<%@ attribute name="cssClass" type="java.lang.String" required="false" description="css样式"%>
<%@ attribute name="cssStyle" type="java.lang.String" required="false" description="css样式"%>
<%@ attribute name="btnClass" type="java.lang.String" required="false" description="css样式"%>
<%@ attribute name="smallBtn" type="java.lang.Boolean" required="false" description="缩小按钮显示"%>
<%@ attribute name="hideBtn" type="java.lang.Boolean" required="false" description="是否显示按钮"%>
<%@ attribute name="disabled" type="java.lang.String" required="false" description="是否限制选择，如果限制，设置为disabled"%>
<%@ attribute name="dataMsgRequired" type="java.lang.String" required="false" description=""%>
<div class="row cl">
	<label class="form-label col-3"><span class="c-red"></span>&nbsp;</label>
	<input id="${id}Id" name="${name}" class="${cssClass}" type="hidden" value="${value}"/>
	<div class="col-3">
		<input id="${id}Name" name="${labelName}" ${allowInput?'':'readonly="readonly"'} type="text" value="${labelValue}" data-msg-required="${dataMsgRequired}"
			class="${cssClass}" style="${cssStyle}"/>
	</div>
	<div class="col-6">
		<span><a id="${id}Button" href="javascript:" class="btn ${ btnClass} ${disabled} ${hideBtn ? 'hide' : ''}" style="${smallBtn?'padding:4px 2px;':''}">&nbsp;<i class="icon-search"></i>&nbsp;</a>&nbsp;&nbsp;</span>
	</div>
</div>
		<script type="text/javascript">
		
		$("#${id}Button, #${id}Name").click(function(){
			// 是否限制选择，如果限制，设置为disabled
			if ($("#${id}Button").hasClass("disabled")){
				return true;
			}
				top.$.jBox.open("iframe:${ctx}/hr/office/selectEmpInComp?id=${role.id}", "分配人员",600,$(top.document).height()-240,{
					buttons:{"确定分配":"ok", "清除已选":"clear", "关闭":true}, bottomText:"通过选择部门，然后选中列出的人员。",submit:function(v, h, f){
						var pre_ids = h.find("iframe")[0].contentWindow.pre_ids;
						var userName = h.find("iframe")[0].contentWindow.userName;
						var userId = h.find("iframe")[0].contentWindow.userId;
						//var ids = h.find("iframe")[0].contentWindow.ids;
						//nodes = selectedTree.getSelectedNodes();
						if (v=="ok"){
							$("#${id}Id").val(userId);
							$("#${id}Name").val(userName);
					    	return true;
						} else if (v=="clear"){
							$("#${id}Id").val("");
							$("#${id}Name").val("");
							return false;
		                }
					}, loaded:function(h){
						$(".jbox-content", top.document).css("overflow-y","hidden");
					}
				});
			});
		</script>