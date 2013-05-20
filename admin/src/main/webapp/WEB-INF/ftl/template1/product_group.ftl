<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div class="text-right">
	<form id="template1-tab3-form" action="${ContextPath}/template1/product_group/add.html" method="post" class="control-group form-inline" novalidate="novalidate">
		<label>产品聚合：</label>
		<select name="productGroup.id" required="true" data-validation-required-message="请选择">
			<option value="">请选择</option>
			<#if productGroups?has_content>
			<#list productGroups as group>
			<option value="${group.id}">${group.name!}</option>
			</#list>
			</#if>
		</select>
		<div class="help-block" style="display:inline-block;"></div>
		<button type="submit" class="btn">添加到首页</button>
	</form>
</div>
<div class="grid">
	<div class="grid-title">
		<div class="pull-left">
			<div class="icon-title"><i class="icon-table"></i></div>
			<span>首页产品聚合列表</span> 
			<div class="clearfix"></div>
		</div>
		<div class="clearfix"></div>   
	</div>
	<div class="grid-content overflow">
		<table id="template1-tab3-list-table" class="table table-striped">
			<colgroup>
				<col width="">
				<col width="150">
				<col width="60">
			</colgroup>
			<thead>
				<tr>
					<th>标题</th>
					<th>显示顺序</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<#if template1ProductGroups?has_content>
				<#list template1ProductGroups as obj>
				<tr>
					<td>${obj.productGroup.name!}</td>
					<td><input type="text" class="input" value="${obj.displayOrder!}" data-id="${obj.id}" style="height: 10px; width: 30px; margin: 0px;"></td>
					<td class="action-table">
						<a href="javascript:void(0);" data-url="${ContextPath}/template1/product_group/delete.html?id=${obj.id}"
							class="ajax-action-btn danger last" data-confirm="true" data-action="删除" data-title="${obj.productGroup.name!}"
							title="删除" data-target="#template1-tab3"><i class="icon-remove"></i></a>
					</td>
				</tr>
				</#list>
				</#if>
				<tr>
					<td> </td><td><button id="template1-tab3-save-order" type="button" class="btn">保存显示顺序</button></td><td> </td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
<script>
$('input,textarea,select', '#template1-tab3-form').jqBootstrapValidation({
	submitSuccess : function($form, event) {
		event.preventDefault();
		event.stopPropagation();
		$form.ajaxSubmit({
			success : function(response) {
				cqlybest.success(null, null, function(){
					$('#template1-tab3').load('${ContextPath}/template1/product_group.html');
				});
			},
			error : function() {
				cqlybest.error();
			}
		});
	}
});
$('#template1-tab3-save-order').click(function(){
	var valid = true;
	var ids = [];
	var orders = [];
	$('#template1-tab3-list-table input').each(function(i, obj){
		if (!/^((\d)|([1-9]\d{1,2}))$/.test(obj.value)) {
			valid = false;
			return false;
		}
		ids.push($(obj).attr('data-id'));
		orders.push(obj.value);
	});
	if (!valid) {
		cqlybest.error('显示顺序只能是0~999');
		return;
	}
	$.post('${ContextPath}/template1/product_group/order.html', {ids:ids, orders:orders}, function(){
		cqlybest.success(false, false, function(){
			$('#template1-tab3').load('${ContextPath}/template1/product_group.html');
		});
	});
});
</script>