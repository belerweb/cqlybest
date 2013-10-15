<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div id="page-content" class="clearfix">
	<div class="row-fluid">
		<h3 class="header smaller lighter blue">
			塞舌尔产品
			<button type="button" class="btn btn-mini btn-primary pull-right" data-action="add">增加产品</button>
		</h3>
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th class="center" style="width:40px;"><input type="checkbox"><span class="lbl"></span></th>
					<th>名称</th>
					<th class="center" style="width:70px;">热门</th>
					<th class="center" style="width:70px;">推荐</th>
					<th class="center" style="width:70px;">特价</th>
					<th class="center" style="width:70px;">发布</th>
					<th class="center" style="width:100px;">操作</th>
				</tr>
			</thead>
			<tbody>
				<#list result.items as product>
				<tr>
					<td class="center"><input type="checkbox" value="${product.id}"><span class="lbl"></span></td>
					<td>${product.name!} ${product.briefTrip?join('')}</td>
					<td class="center">
						<label>
							<input name="popular" type="checkbox" <#if product.popular!false>checked="checked"</#if> class="ace-switch ace-switch-5"
								data-id="${product.id}" data-action="toggle-property">
							<span class="lbl"></span>
						</label>
					<td class="center">
						<label>
							<input name="recommend" type="checkbox" <#if product.recommend!false>checked="checked"</#if> class="ace-switch ace-switch-5"
								data-id="${product.id}" data-action="toggle-property">
							<span class="lbl"></span>
						</label>
					<td class="center">
						<label>
							<input name="special" type="checkbox" <#if product.special!false>checked="checked"</#if> class="ace-switch ace-switch-5"
								data-id="${product.id}" data-action="toggle-property">
							<span class="lbl"></span>
						</label>
					</td>
					<td class="center">
						<label>
							<input name="published" type="checkbox" <#if product.published!false>checked="checked"</#if> class="ace-switch ace-switch-5"
								data-id="${product.id}" data-action="toggle-property">
							<span class="lbl"></span>
						</label>
					</td>
					<td class="td-actions center">
						<div class="btn-group">
							<button type="button" class="btn btn-mini btn-success" title="预览" data-id="${product.id}" data-action="preview">
								<i class="icon-external-link bigger-120"></i>
							</button>
							<button type="button" class="btn btn-mini btn-info" title="修改"
								data-id="${product.id}" data-action="edit">
								<i class="icon-edit bigger-120"></i>
							</button>
							<button type="button" class="btn btn-mini btn-danger" title="删除" data-action="delete">
								<i class="icon-trash bigger-120"></i>
							</button>
						</div>
					</td>
				</tr>
				</#list>
			</tbody>
		</table>
	</div>
</div>
<script>
$('#page-content button[data-action=add]').click(function(){
	var action = $(this).parent();
	var form = ['<form class="form-horizontal"><legend>添加塞舌尔产品</legend>'];
	form.push('<div class="control-group"><label class="control-label">名称：</label>');
	form.push('<div class="controls"><input type="text" name="name"></div></div>');
	form.push('</form>');
	var dialog = bootbox.dialog(form.join(''), [{
		label: '取消'
	}, {
		label: '确定',
		callback: function(){
			var name = $.trim($('input[name=name]', dialog).val());
			$.post('${ContextPath}/seychelles/product/add.do', {
				name: name
			}).done(function(data){
				cqlybest.go('#main-content', '${ContextPath}/seychelles/product/update.do?id=' + data);
			}).fail(function() {
				cqlybest.error();
			});
		}
	}]);
	$('form', dialog).on('submit', function(e) {
		e.preventDefault();
		dialog.find(".btn-primary").click();
	});
});
$('#page-content table').dataTable({
	iDeferLoading: ${result.total},
	iDisplayStart: ${result.start},
	iDisplayLength: ${result.pageSize},
	bLengthChange: false,
	bFilter: false,
	bServerSide: true,
	fnServerData: function (sSource, aoData, fnCallback, oSettings) {
		var p = {};
		$.each(aoData, function(i, obj){
			p[obj.name] = obj.value;
		});
		var q = {};
		q.page = p.iDisplayStart / p.iDisplayLength;
		cqlybest.go('#main-content', '${ContextPath}/seychelles/product.do?' + $.param(q));
	}
});
$('#page-content button[data-action=preview]').click(function(){
	<#if (settings.basic.siteUrl)?has_content>
	window.open('${settings.basic.siteUrl}/product/' + $(this).data('id')  + '.html');
	<#else>
	cqlybest.error('请先配置网站地址。');
	</#if>
});
$('#page-content button[data-action=edit]').click(function(){
	cqlybest.go('#main-content', '${ContextPath}/seychelles/product/update.do?id=' + $(this).data('id'));
});
$('#page-content button[data-action=delete]').click(function(){
	cqlybest.error('暂时不能删除数据。');
});
$('#page-content input[data-action=toggle-property]').click(function(){
	var input = this;
	$.post('${ContextPath}/seychelles/product/update.do', {
		pk: $(this).data('id'),
		name: $(this).attr('name'),
		value: this.checked
	}).done(function(data){
	}).fail(function() {
		cqlybest.error();
		input.checked = !input.checked;
	});
});
</script>