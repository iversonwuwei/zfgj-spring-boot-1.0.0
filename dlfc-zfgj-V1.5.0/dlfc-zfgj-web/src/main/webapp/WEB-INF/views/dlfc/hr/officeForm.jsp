<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>机构管理</title>
<meta name="decorator" content="themehui" />
<link href="${ctxStatic}/hui/css/input_label.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript">	

$(function() {
	var refresh = $("#refresh").val();
	var message = $("#message").val();
	if(message != null && message != ''){
		if(refresh == 1 ){
			layer.alert(message, {icon: 1,closeBtn: 0},function(){
				window.parent.window.refresh();
			});
		}else{
			layer.alert(message, {icon: 2,closeBtn: 0});
		}
	}
	$('#inputForm').validationEngine({
		showOneMessage: true,
		maxErrorsPerField:"1",
		addPromptClass: 'formError-text'
	});
	
	var myarea=$(".myarea");
	//删除已选
	$("a em",myarea).live("click",function(){
		var c=$(this).parents("a"),b=c.attr("title"),d=c.attr("value");
		delTips1(b,d);
// 		$('.myarea').append($('.checked a'));
		var areaStr = [];
		if($("#myarea a").length>0){
			$("a",myarea).each(function(){
				if(areaStr.length == 0){
					areaStr = $(this).attr("value");
				}else{
					areaStr =areaStr + ","+$(this).attr("value");
				}
			});
			$("#area").val(areaStr);
		 } else{
			$("#area").val("zero");
		 } 
	});
	delTips1=function(b,c){
		if(!hasTips1(b)){
			return false;
		}
		$("a",myarea).each(function(){
			var d=$(this);
			if(d.attr("title")==b){
				d.remove();
				return false;
			}
		});
// 		searchAjax(b,c,false);
		return true;
	};
	hasTips1=function(b){
		var d=$("a",myarea),c=false;
		d.each(function(){
			if($(this).attr("title")==b){
				c=true;
				return false;
			}
		});
		return c;
	};
	
});	

//点击编辑业务范围
function openAddDialog() {
	//弹出框
	_didx = layer.open({
		type: 1, //page层
		area: ['750px', '500px'],
		title: '编辑业务范围(您最多选择五个商圈)',
		shade: 0.6, //遮罩透明度
		moveType: 1, //拖拽风格，0是默认，1是传统拖动
		shift: 0, //0-6的动画形式，-1不开启
		content: $('#addcust')
	});
	//初始化已选
	var myarea=$(".myarea");
	$("#addcust").show();
	var arrTitle=[];
	var arrValue=[];
	$("a",myarea).each(function(){
		arrTitle.push($(this).attr("title"));
		arrValue.push($(this).attr("value"));
	});
	
	//清空div
	var mytag=$(".checked");
	$("a",mytag).each(function(){
		var d=$(this);
			d.remove();
	});
	//初始化节点
	for(var node in arrTitle){	
		var b ='value="'+arrValue[node]+'"';
		mytag.append($("<a "+b+' title="'+arrTitle[node]+'" href="javascript:void(0);" ><span>'+arrTitle[node]+"</span><em></em></a>"));
	}
	$('#myTags').show();
	
	//设置高亮
	setSelectTips1();
};
var getTips1=function(){
	var myarea=$(".myarea");
	var b=[];
	$("a",myarea).each(function(){
		b.push($(this).attr("title"))
	});
	return b;
};

//设置高亮
var setSelectTips1 = function(){
	var arrName = getTips1();
	if(arrName.length){
		$('#myTags').show();
	}else{
		$('#myTags').hide();
	}
	$('.default-tag a').removeClass('selected');
	$.each(arrName, function(index,name){
		$('.default-tag a').each(function(){
			var $this = $(this);
			if($this.attr('title') == name){
				$this.addClass('selected');
				return false;
			}
		})
	});
}

//编辑完点击确定
function confirmEditDialog() {
	//清空div
	var myarea=$(".myarea");
	$("a",myarea).each(function(){
		var d=$(this);
			d.remove();
	});
	
	$('.myarea').append($('.checked a'));
	var areaStr = [];
	if($("#myarea a").length>0){
		$("a",myarea).each(function(){
			if(areaStr.length == 0){
				areaStr = $(this).attr("value");
			}else{
				areaStr =areaStr + ","+$(this).attr("value");
			}
		});
		$("#area").val(areaStr);
	 } else{
		$("#area").val("zero");
	 }
	layer.close(_didx);
	
}
function closeEditDialog() {
	layer.close(_didx);
};



</script>


</head>
<body>
	<form:form id="inputForm" modelAttribute="office"
		action="${ctx}/hr/office/save" method="post"
		class="form-horizontal user_form">
		<form:hidden path="id" />
		<input type="hidden" id="parentId" name ="parentId" value="${office.parent.id}">
		<input type="hidden" id="parentParentId" name ="parentParentId" value="${office.parent.parent.id}">
		<input type="hidden" id="parentIds" name ="parentIds" value="${office.parent.parentIds}">
		<input type="hidden" id="refresh" value="${refresh }">
		<input type="hidden" id="message" value="${message }">
		<div class="row cl" style="display: none">
			<label class="form-label col-2">上级机构</label>
			<div class="formControls col-4">
				<sys:treeselect id="office" name="parent.id"
					value="${office.parent.id}" labelName="parent.name"
					labelValue="${office.parent.name}" title="机构"
					url="/hr/office/treeData" extId="${office.id}"
					cssClass="input-text" cssStyle="width:80%;" btnClass="btn-success"
					allowClear="${office.currentUser.admin}" />
			</div>
		</div>

		<div class="row cl" style="display: none">
			<label class="form-label col-2">归属区域</label>
			<div class="formControls col-4">
				<sys:treeselect id="area" name="area.id" value="${office.area.id}"
					labelName="area.name" labelValue="${office.area.name}" title="区域"
					url="/sys/area/treeData" cssClass="input-text"
					cssStyle="width:80%;" btnClass="btn-success" />
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>机构名称</label>
			<div class="formControls col-4">
				<input id="oldName" name="oldName" type="hidden" value="${office.name}">
				<form:input path="name" htmlEscape="false" maxlength="50"
					class="input-text validate[required,funcCall[nameCheck]]"
					data-prompt-position="centerRight"
					data-errormessage-value-missing="* 请输入机构名称." />
			</div>
		</div>
		<!-- 添加经纪公司的详细地址 -->
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>区域选择</label>
			<div class="formControls col-4">
				<select	id="code" name="code" style="height:31px;">
					<c:forEach items="${areass}" var="area" varStatus="status">
                        <option value="${area.id}">${area.area}</option>
					</c:forEach>
				</select>
				<input id="address" name="address" type="text" style="width:369px; height:29px;">
			</div>
		</div>
		<div class="row cl" style="margin-top: 20px">
			<label class="form-label col-2">业务范围</label>
			<input type="hidden" id="area" value="" name="area">
			<div class="formControls col-9 plus-tag-box">
				<div class="plus-tag tagbtn clearfix myarea" id="myarea">
					<c:forEach items="${officeAreaLinkList}" var="officeAreaLink" varStatus="status">
						<a title="${officeAreaLink.name}" value="${officeAreaLink.areaId}" href="javascript:void(0);" >
						<span>${officeAreaLink.name}</span><em></em></a>
					</c:forEach>
				</div>

			</div>
		</div>
		<div class="formControls col-1">	
		</div>
			<div class="formControls col-4">
				<a class="btn btn-link add_btn radius" 
					onclick="openAddDialog()"><i class="Hui-iconfont">&#xe60c;</i>编辑业务范围</a>
			</div>
		<div class="control-group" style="display: none">
			<label class="control-label">机构类型:</label>
			<div class="controls">
				<c:if test="${empty office.id}">
					<form:select path="type" class="input-medium">
						<form:option value="2" label="部门" />
					</form:select>
				</c:if>
				<c:if test="${not empty office.id}">
					<form:select path="type" class="input-medium">
						<form:options items="${fns:getDictList('sys_office_type')}"
							itemLabel="label" itemValue="value" htmlEscape="false" />
					</form:select>
				</c:if>
			</div>
		</div>
		<div class="control-group" style="display: none;">
			<label class="control-label">机构级别:</label>
			<div class="controls">
				<form:select path="grade" class="input-medium">
					<form:options items="${fns:getDictList('sys_office_grade')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group" style="display: none;">
			<label class="control-label">是否可用:</label>
			<div class="controls">
				<form:select path="useable">
					<form:options items="${fns:getDictList('yes_no')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
				<span class="help-inline">“是”代表此账号允许登陆，“否”则表示此账号不允许登陆</span>
			</div>
		</div>
		<div class="row cl btn_group">
			<div class="col-9 col-offset-2">
				<shiro:hasPermission name="hr:office:edit">
<!-- 					<a href="javascript:$('#inputForm').submit();" class="btn btn-primary radius">保 存</a> -->
					<a onclick="saveOffice()" class="btn btn-primary radius">保 存</a>
				</shiro:hasPermission>
				<!-- <a onclick="history.go(-1)"
						class="btn btn-default radius">返 回</a> -->
			</div>
		</div>
	</form:form>
	<div id="addcust" style="display: none;">
		<div class="pd-20">
			<div class="responsive">
				<div class="row cl mb-20 ">
					<label class="form-label text-r pr-10 col-2">已选业务范围</label>
					<div class="formControls col-9 plus-tag-box">
						<div class="plus-tag tagbtn clearfix checked" id="myTags"></div>
						<div class="plus-tag-add"></div>
					</div>
				</div>
				<div class="line"></div>
        		<c:forEach items="${fns:getAreasList('210200')}" var="areas" varStatus="status">
					<div class="row cl check-list">
						<label class="form-label text-r pr-10 col-2">${areas.area}</label>
						<div class="formControls col-9">
							<div id="mycard-plus">
								<div class="default-tag tagbtn">
									<div class="clearfix">
										<c:forEach items="${areas.areas}" var="area" varStatus="status">
											<a value="${area.id }" title="${area.name }" href="javascript:void(0);"><span>${area.name }</span><em></em></a>
										</c:forEach>
									</div>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
				<div class="row cl btn_group">
					<div class="col-9 col-offset-2">
						<a class="btn btn-primary radius" onclick="confirmEditDialog()">提交</a> 
						<a class="btn btn-default radius" onclick="closeEditDialog()">返回</a> 
					</div>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
var FancyForm=function(){
	return{
		inputs:".FancyForm input, .FancyForm textarea",
		setup:function(){
			var a=this;
			this.inputs=$(this.inputs);
			a.inputs.each(function(){
				var c=$(this);
				a.checkVal(c);
			});
			a.inputs.live("keyup blur",function(){
				var c=$(this);
				a.checkVal(c);
			});
		},checkVal:function(a){
			a.val().length>0?a.parent("li").addClass("val"):a.parent("li").removeClass("val");
		}
	}
}();
</script>

<script type="text/javascript">
$(document).ready(function() {
	FancyForm.setup();
});
</script>

<script type="text/javascript">
var searchAjax=function(){};
var G_tocard_maxTips=5;

$(function(){(
	function(){	
		var a=$(".checked");
		//删除已选
		$("a em",a).live("click",function(){
			var c=$(this).parents("a"),b=c.attr("title"),d=c.attr("value");
			delTips(b,d);
		});
		
		hasTips=function(b){
			var d=$("a",a),c=false;
			d.each(function(){
				if($(this).attr("title")==b){
					c=true;
					return false;
				}
			});
			return c;
		};

		isMaxTips=function(){
			if($("a",a).length>=G_tocard_maxTips){
				return true;
			}
			return false;
		};
		
		//选中标签
		setTips=function(c,d){
			if(hasTips(c)){
				return false;
			}
			if(isMaxTips()){
				alert("最多添加"+G_tocard_maxTips+"个标签！");
				return false;
			}
			var b=d?'value="'+d+'"':"";
			a.append($("<a "+b+' title="'+c+'" href="javascript:void(0);" ><span>'+c+"</span><em></em></a>"));
			searchAjax(c,d,true);
			return true;
		};

		delTips=function(b,c){
			if(!hasTips(b)){
				return false;
			}
			$("a",a).each(function(){
				var d=$(this);
				if(d.attr("title")==b){
					d.remove();
					return false;
				}
			});
			searchAjax(b,c,false);
			return true;
		};

		getTips=function(){
			var b=[];
			$("a",a).each(function(){
				b.push($(this).attr("title"))
			});
			return b;
		};

		getTipsId=function(){
			var b=[];
			$("a",a).each(function(){
				b.push($(this).attr("value"))
			});
			return b;
		};
		
		getTipsIdAndTag=function(){
			var b=[];
			$("a",a).each(function(){
				b.push($(this).attr("value")+"##"+$(this).attr("title"));
			});
			return b;
		}
	}
	
)()});
</script>

<script type="text/javascript">
// 更新选中标签标签
$(function(){
	setSelectTips();
// 	$('.plus-tag').append($('.plus-tag a'));
});
var searchAjax = function(name, id, isAdd){
	setSelectTips();
};
// 搜索
(function(){
	var $b = $('.plus-tag-add button'),$i = $('.plus-tag-add input');
	$i.keyup(function(e){
		if(e.keyCode == 13){
			$b.click();
		}
	});
	$b.click(function(){
		var name = $i.val().toLowerCase();
		if(name != '') setTips(name,-1);
		$i.val('');
		$i.select();
	});
})();
// 推荐标签
(function(){
	var str = ['展开推荐标签', '收起推荐标签']
	$('.plus-tag-add a').click(function(){
		var $this = $(this),
				$con = $('#mycard-plus');

		if($this.hasClass('plus')){
			$this.removeClass('plus').text(str[0]);
			$con.hide();
		}else{
			$this.addClass('plus').text(str[1]);
			$con.show();
		}
	});
	$('.default-tag a').live('click', function(){
		var $this = $(this),
				name = $this.attr('title'),
				id = $this.attr('value');
		setTips(name, id);
	});
	// 更新高亮显示
	setSelectTips = function(){
		var arrName = getTips();
		if(arrName.length){
			$('#myTags').show();
		}else{
			$('#myTags').hide();
		}
		$('.default-tag a').removeClass('selected');
		$.each(arrName, function(index,name){
			$('.default-tag a').each(function(){
				var $this = $(this);
				if($this.attr('title') == name){
					$this.addClass('selected');
					return false;
				}
			})
		});
	}

})();
// 更换链接
(function(){
	var $b = $('#change-tips'),
		$d = $('.default-tag div'),
		len = $d.length,
		t = 'nowtips';
	$b.click(function(){
		var i = $d.index($('.default-tag .nowtips'));
		i = (i+1 < len) ? (i+1) : 0;
		$d.hide().removeClass(t);
		$d.eq(i).show().addClass(t);
	});
	$d.eq(0).addClass(t);
})();

function nameCheck(){
	var flag = true;
	$.ajax({
		type:"POST",
		url:"${ctx}/hr/office/checkName",
		data:"oldName="+encodeURIComponent($("#oldName").val())+"&name="+encodeURIComponent($("#name").val())
				+"&parentId="+encodeURIComponent($("#parentId").val())+"&parentIds="+encodeURIComponent($("#parentIds").val())
				+"&parentParentId="+encodeURIComponent($("#parentParentId").val())+"&id="+encodeURIComponent($("#id").val()),
		async:false,
		dataType:"json",
		success:function(data){
			if(data){
				flag = true;
			}else{
				flag = false;
			}
		},
		error : function() {
			layer.alert("网络异常，请稍后重试！");
		}
	});
	if(!flag){
		return "* 机构名称存在.";
	}
}

function saveOffice(){
	var success = $("#inputForm").validationEngine("validate");
	if(!success){
		return;
	}
	$('#inputForm').submit();
}

</script>
</body>
</html>
