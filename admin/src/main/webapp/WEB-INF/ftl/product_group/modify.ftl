<#assign ContextPath=springMacroRequestContext.getContextPath() />
<style>
.group-cnd {
	margin: 0 0 20px;
}
.group-cnd span {
	margin: 0 10px;
}
.group-cnd-plus {
	margin-left: 5px;
}
.group-cnd-plus > span {
	padding: 2px 5px 1px 2px;
}
.group-cnd-remove {
}
.group-cnd-remove > span{
    margin: 0 0 0 5px;
    padding: 3px 2px 2px 4px;
}
</style>
<div>
	<div class="clearfix"></div>
	<div class="grid">
		<div class="grid-title">
			<div class="pull-left">
				<div class="icon-title"><i class="icon-picture"></i></div>
				<span>修改产品聚合</span> 
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>   
		</div>
		<!-- novalidate -->
		<form id="main-content-form" action="${ContextPath}/product_group/modify.html" method="post" class="form-horizontal">
			<input type="hidden" name="id" value="${(group.id)!}">
			<div class="grid-content">
				<div class="control-group">
					<label class="control-label">聚合名称：</label>
					<div class="controls">
						<input type="text" class="span input" name="name" value="${(group.name)!}" required="true" maxlength="16"> 
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">聚合条件：</label>
					<div id="group-cnds" class="controls">
						<#assign groupCndNames=['推荐月份', '适合人群', '交通方式', '产品类型', '产品等级']>
						<#list groupTypes as type>
						<div class="group-cnd">
							<span class="help-inline"><strong>按${groupCndNames[type]}</strong><a href="javascript:void(0);" class="group-cnd-remove"><span class="label label-important"><i class="icon-remove"></i></span></a>：</span>
							<#if type==0>
								<#list groupValues[type_index]?split(",")  as value>
								<span class="s_green">${value}月</span>
								</#list>
							</#if>
							<#if type==1>
								<#assign crowds={'1':'个人旅行','2':'团体旅行'}>
								<#list groupValues[type_index]?split(",")  as value>
								<span class="s_green">${crowds[value]}</span>
								</#list>
							</#if>
							<#if type==2>
								<#assign items =  groupValues[type_index]?split(",")>
								<#list traffics as dict>
									<#if items?seq_contains('${dict.id}')>
									<span class="s_green">${dict.name!}</span>
									</#if>
								</#list>
							</#if>
							<#if type==3>
								<#assign items =  groupValues[type_index]?split(",")>
								<#list types as dict>
									<#if items?seq_contains('${dict.id}')>
									<span class="s_green">${dict.name!}</span>
									</#if>
								</#list>
							</#if>
							<#if type==4>
								<#assign items =  groupValues[type_index]?split(",")>
								<#list grades as dict>
									<#if items?seq_contains('${dict.id}')>
									<span class="s_green">${dict.name!}</span>
									</#if>
								</#list>
							</#if>
							<input type="hidden" name="groupTypes" value="${type}">
							<input type="hidden" name="groupValues" value="${groupValues[type_index]}">
						</div>
						</#list>
					</div>
				</div>
				<div class="text-center">
					<button class="btn btn-primary" type="submit">保存</button>
				</div>
				<div class="control-group">
					<label class="control-label"><strong>备选条件：</strong></label>
				</div>
				<hr style="margin:0 0 10px;">
				<div class="control-group">
					<label class="control-label">推荐月份<a class="group-cnd-plus" href="javascript:void(0);" data-type="0"><span class="s_green"><i class="icon-plus"></i></span></a>：</label>
					<div class="controls">
						<label class="checkbox inline"><input value="1" type="checkbox"> 1月</label>
						<label class="checkbox inline"><input value="2" type="checkbox"> 2月</label>
						<label class="checkbox inline"><input value="3" type="checkbox"> 3月</label>
						<label class="checkbox inline"><input value="4" type="checkbox"> 4月</label>
						<label class="checkbox inline"><input value="5" type="checkbox"> 5月</label>
						<label class="checkbox inline"><input value="6" type="checkbox"> 6月</label>
						<label class="checkbox inline"><input value="7" type="checkbox"> 7月</label>
						<label class="checkbox inline"><input value="8" type="checkbox"> 8月</label>
						<label class="checkbox inline"><input value="9" type="checkbox"> 9月</label>
						<label class="checkbox inline"><input value="10" type="checkbox"> 10月</label>
						<label class="checkbox inline"><input value="11" type="checkbox"> 11月</label>
						<label class="checkbox inline"><input value="12" type="checkbox"> 12月</label>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">适合人群<a class="group-cnd-plus" href="javascript:void(0);" data-type="1"><span class="s_green"><i class="icon-plus"></i></span></a>：</label>
					<div class="controls">
						<label class="checkbox inline"><input value="1" type="checkbox"> 个人旅行</label>
						<label class="checkbox inline"><input value="2" type="checkbox"> 团体旅行</label>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">交通方式<a class="group-cnd-plus" href="javascript:void(0);" data-type="2"><span class="s_green"><i class="icon-plus"></i></span></a>：</label>
					<div class="controls">
						<#list traffics as dict>
						<label class="checkbox inline"><input value="${dict.id!}" type="checkbox"> ${dict.name!}</label>
						</#list>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">产品类型<a class="group-cnd-plus" href="javascript:void(0);" data-type="3"><span class="s_green"><i class="icon-plus"></i></span></a>：</label>
					<div class="controls">
						<#list types as dict>
						<label class="checkbox inline"><input value="${dict.id!}" type="checkbox"> ${dict.name!}</label>
						</#list>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">产品等级<a class="group-cnd-plus" href="javascript:void(0);" data-type="4"><span class="s_green"><i class="icon-plus"></i></span></a>：</label>
					<div class="controls">
						<#list grades as dict>
						<label class="checkbox inline"><input value="${dict.id!}" type="checkbox"> ${dict.name!}</label>
						</#list>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
		</form>
	</div>
</div>
<script>
var groupCndNames  =  ['推荐月份', '适合人群', '交通方式', '产品类型', '产品等级'];
$('#group-cnds .group-cnd-remove').click(function(){
	$(this).parents('.group-cnd').remove();
});
var generateCnd = function(name, objs, type, value) {
	if ($('#group-cnds .group-cnd').length >= 5) {
		bootbox.alert('<div class="alert alert-error">当前最多只允许5个聚合条件</div>', '确定');
		return;
	}
	var html =  ['<div class="group-cnd"><span class="help-inline"><strong>按' + name + '</strong>'];
	html.push('<a href="javascript:void(0);" class="group-cnd-remove"><span class="label label-important">');
	html.push('<i class="icon-remove"></i></span></a>：</span>');
	$.each(objs, function(i, obj){
		html.push('<span class="s_green">' + obj + '</span>');
	});
	html.push('<input type="hidden" name="groupTypes" value="' + type + '">');
	html.push('<input type="hidden" name="groupValues" value="' + value + '"></div>');
	var dom = $(html.join(''));
	$('.group-cnd-remove', dom).click(function(){
		$(this).parents('.group-cnd').remove();
	});	
	$('#group-cnds').append(dom);
};
var checkboxCnds = ['.group-cnd-plus[data-type=0]'];
checkboxCnds.push('.group-cnd-plus[data-type=1]');
checkboxCnds.push('.group-cnd-plus[data-type=2]');
checkboxCnds.push('.group-cnd-plus[data-type=3]');
checkboxCnds.push('.group-cnd-plus[data-type=4]');
$(checkboxCnds.join(',')).click(function(){
	var type = $(this).attr('data-type');
	var value = [];
	var names =  [];
	var objs = $('input:checked', $(this).parents('.control-group'));
	if (objs.length == 0) {
		bootbox.alert('<div class="alert alert-error">至少勾选一个值</div>', '确定');
		return;
	}
	$.each(objs, function(i, obj){
		value.push($(obj).val());
		names.push($.trim($(obj).parent().text().trim()));
	});
	generateCnd(groupCndNames[type], names, type, value.join(','));
});
$('input,textarea,select', '#main-content-form').not(':hidden').jqBootstrapValidation({
	submitSuccess : cqlybest.ajaxSubmit
});
</script>