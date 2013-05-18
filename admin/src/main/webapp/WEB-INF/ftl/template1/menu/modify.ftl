<#assign ContextPath=springMacroRequestContext.getContextPath() />
<style>
.group-cnd {
	margin: 0 0 10px;
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
<div class="grid">
	<div class="grid-title">
		<div class="pull-left">
			<div class="icon-title"><i class="icon-edit"></i></div>
			<span>修改菜单</span> 
			<div class="clearfix"></div>
		</div>
		<div class="pull-right">
			<div class="icon-title">
				<a href="javascript:history.go(-1);" title="返回">
					<i class="icon-arrow-left"></i>
				</a>
			</div>
		</div>
		<div class="clearfix"></div>   
	</div>
	<div class="grid-content">
		<#if menu.menuType==0>
		<form action="${ContextPath}/template1/menu/modify.html" method="post" class="template1-tab1-form form-horizontal">
			<input type="hidden" name="id" value="${menu.id}">
			<input type="hidden" name="displayOrder" value="${menu.displayOrder}">
			<input type="hidden" name="menuType" value="0">
			<div class="row-fluid">
				<div class="span12">
					<div class="control-group">
						<label class="control-label">菜单名称：</label>
						<div class="controls">
							<input type="text" class="span" name="name" value="${menu.name!}" required="true" maxlength="8">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">新窗口：</label>
						<div class="controls">
							<select name="newWindow" class="input-small">
								<option <#if menu.newWindow>selected="selected"</#if> value="true">是</option>
								<option <#if !menu.newWindow>selected="selected"</#if> value="false">否</option>
							</select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">产品聚合：</label>
						<div class="controls">
							<select name="productGroup.id" class="input-large" required="true">
								<#list productGroups as group>
								<option <#if menu.productGroup?exists && group.id==menu.productGroup.id>selected="selected"</#if> value="${group.id!}">${group.name!}</option>
								</#list>
							</select>
							<div class="help-block">只有菜单和对应的聚合都是发布状态才会被显示到页面上</div>
						</div>
					</div>
					<#assign groupCndNames=['推荐月份', '适合人群', '交通方式', '产品类型', '产品等级', '关键词/标签', '出发城市', '目的地']>
					<#macro selectedDictRender dict values>
						<#assign items = values?split(",")>
						<#list dict as item>
							<#if items?seq_contains('${item.id}')>
							<span class="s_green">${item.name!}</span>
							</#if>
						</#list>
					</#macro>
					<div class="control-group">
						<label class="control-label">子菜单：</label>
						<div id="_menu-cnds" class="controls">
							<#if menu.subMenus?has_content>
							<#list menu.subMenus as item>
							<div class="group-cnd">
								<span class="help-inline"><input type="text" value="${item.title!}" class="input input-small" name="_titles"><a class="group-cnd-remove" href="javascript:void(0);"><span class="label label-important"><i class="icon-remove"></i></span></a>：</span>
								<#if item.menuType==7>
									<@selectedDictRender destinations item.menuValue />
								</#if>
								<input type="hidden" name="_ids" value="${item.id}">
								<input type="hidden" name="_menuTypes" value="${item.menuType}">
								<input type="hidden" name="_menuValues" value="${item.menuValue!}">
							</div>
							</#list>
							</#if>
						</div>
					</div>
					<div class="text-center">
						<button class="btn btn-primary" type="submit">保存</button>
					</div>
				</div>
			</div>
		</form>
		<div class="grid-content form-horizontal">
			<div class="control-group">
				<label class="control-label"><strong>备选子菜单：</strong></label>
			</div>
			<hr style="margin:0 0 10px;">
			<div class="control-group">
				<label class="control-label">目的地<a class="group-cnd-plus" href="javascript:void(0);" data-type="7"><span class="s_green"><i class="icon-plus"></i></span></a>：</label>
				<div class="controls">
					<input type="text" class="input" id="product-destination">
					<div class="help-block"></div>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
		<#elseif menu.menuType==1>
		<form action="${ContextPath}/template1/menu/modify.html" method="post" class="template1-tab1-form form-horizontal">
			<input type="hidden" name="id" value="${menu.id}">
			<input type="hidden" name="displayOrder" value="${menu.displayOrder}">
			<input type="hidden" name="menuType" value="1">
			<div class="row-fluid">
				<div class="span12">
					<div class="control-group">
						<label class="control-label">菜单名称：</label>
						<div class="controls">
							<input type="text" class="span" name="name" value="${menu.name!}" required="true" maxlength="8">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">新窗口：</label>
						<div class="controls">
							<select name="newWindow" class="input-small">
								<option <#if menu.newWindow>selected="selected"</#if> value="true">是</option>
								<option <#if !menu.newWindow>selected="selected"</#if> value="false">否</option>
							</select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">页面内容：</label>
						<div class="controls">
							<script type="text/plain" id="menu-page-content" name="pageContent">${menu.pageContent!}</script>
						</div>
					</div>
					<div class="text-center">
						<button class="btn btn-primary" type="submit">保存</button>
					</div>
				</div>
			</div>
		</form>
		<#elseif menu.menuType==2>
		<form action="${ContextPath}/template1/menu/modify.html" method="post" class="template1-tab1-form form-horizontal">
			<input type="hidden" name="id" value="${menu.id}">
			<input type="hidden" name="displayOrder" value="${menu.displayOrder}">
			<input type="hidden" name="menuType" value="2">
			<div class="row-fluid">
				<div class="span12">
					<div class="control-group">
						<label class="control-label">菜单名称：</label>
						<div class="controls">
							<input type="text" class="span" name="name" value="${menu.name!}" required="true" maxlength="8">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">新窗口：</label>
						<div class="controls">
							<select name="newWindow" class="input-small">
								<option <#if menu.newWindow>selected="selected"</#if> value="true">是</option>
								<option <#if !menu.newWindow>selected="selected"</#if> value="false">否</option>
							</select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">外链地址：</label>
						<div class="controls">
							<input type="text" class="span" name="url" value="${menu.url!}" required="true" maxlength="128">
						</div>
					</div>
					<div class="text-center">
						<button class="btn btn-primary" type="submit">保存</button>
					</div>
				</div>
			</div>
		</form>
		</#if>
	</div>
</div>
<script>
$('select', '.template1-tab1-form').selectBoxIt({autoWidth:false});
UE.delEditor('menu-page-content');
var pdEditor = UE.getEditor('menu-page-content', {
	initialContent: '',
	initialFrameWidth: '100%'
});
$('input,textarea,select', '.template1-tab1-form').not('input[name=_titles]').not(':hidden').jqBootstrapValidation({
	submitSuccess : cqlybest.ajaxSubmit
});

/* 子菜单 */
$('#product-keyword').cqlybestTag({
	hiddenTagListName: 'keywordIds',
	typeahead: true,
	typeaheadAjaxSource: '/data/dict.html?action=dict&type=keyword',
	typeaheadAjaxPolling: true,
	AjaxPush: '/data/dict/add_keyword.html'
});
$('#product-departure-city').cqlybestTag({
	hiddenTagListName: 'departureCityIds',
	typeahead: true,
	typeaheadAjaxSource: '/data/dict.html?action=dict&type=departure-city',
	typeaheadAjaxPolling: true,
	AjaxPush: '/data/dict/add_departure_city.html'
});
$('#product-destination').cqlybestTag({
	hiddenTagListName: 'destIds',
	typeahead: true,
	typeaheadAjaxSource: '/data/dict.html?action=dict&type=destination',
	typeaheadAjaxPolling: true
});

var groupCndNames  =  ['推荐月份', '适合人群', '交通方式', '产品类型', '产品等级', '关键词/标签', '出发城市', '目的地'];
$('#_menu-cnds .group-cnd-remove').click(function(){
	$(this).parents('.group-cnd').remove();
});
var generateCnd = function(t, name, objs, type, value) {
	var html =  ['<div class="group-cnd"><span class="help-inline"><input name="_titles" type="text" class="input input-small" value=' + name + '>'];
	html.push('<a href="javascript:void(0);" class="group-cnd-remove"><span class="label label-important">');
	html.push('<i class="icon-remove"></i></span></a>：</span>');
	$.each(objs, function(i, obj){
		html.push('<span class="s_green">' + obj + '</span>');
	});
	html.push('<input type="hidden" name="_ids" value="">');
	html.push('<input type="hidden" name="' + t + 'Types" value="' + type + '">');
	html.push('<input type="hidden" name="' + t + 'Values" value="' + value + '"></div>');
	var dom = $(html.join(''));
	$('.group-cnd-remove', dom).click(function(){
		$(this).parents('.group-cnd').remove();
	});	
	$('#' + t + '-cnds').append(dom);
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
	generateCnd('_menu', groupCndNames[type], names, type, value.join(','));
});
var tagCnds = ['.group-cnd-plus[data-type=5]'];
tagCnds.push('.group-cnd-plus[data-type=6]');
tagCnds.push('.group-cnd-plus[data-type=7]');
$(tagCnds.join(',')).click(function(){
	var type = $(this).attr('data-type');
	var parent = $(this).parents('.control-group');
	var value = $('input:hidden', parent).val();
	var names = [];
	$('.myTag > span', parent).each(function(i, obj){
		names.push($(obj).text());
	});
	if (names.length==0) {
		bootbox.alert('<div class="alert alert-error">至少输入/选择一个值</div>', '确定');
		return;
	}
	generateCnd('_menu', groupCndNames[type], names, type, value);
});
</script>