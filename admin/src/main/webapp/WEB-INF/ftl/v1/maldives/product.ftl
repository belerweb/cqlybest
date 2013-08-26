<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div id="page-content" class="clearfix">
	<div class="row-fluid">
		<h3 class="header smaller lighter blue">
			马代产品
			<button type="button" class="btn btn-mini btn-primary pull-right" data-action="add">增加产品</button>
		</h3>
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th class="center" style="width:40px;"><input type="checkbox"><span class="lbl"></span></th>
					<th>名称</th>
					<th class="center" style="width:30px;">热门</th>
					<th class="center" style="width:30px;">推荐</th>
					<th class="center" style="width:30px;">特价</th>
					<th class="center" style="width:30px;">发布</th>
					<th class="center" style="width:100px;">操作</th>
				</tr>
			</thead>
			<tbody>
				<#list result.items as product>
				<tr>
					<td class="center"><input type="checkbox" value="${product.id}"><span class="lbl"></span></td>
					<td>${product.name!} ${product.briefTrip?join('')}</td>
					<td class="center"><#if product.popular!false><span class="label label-success">是</span><#else><span class="label label-warning">否</span></#if></td>
					<td class="center"><#if product.recommend!false><span class="label label-success">是</span><#else><span class="label label-warning">否</span></#if></td>
					<td class="center"><#if product.special!false><span class="label label-success">是</span><#else><span class="label label-warning">否</span></#if></td>
					<td class="center"><#if product.published!false><span class="label label-success">是</span><#else><span class="label label-warning">否</span></#if></td>
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
	var form = ['<form class="form-horizontal"><legend>添加马代产品</legend>'];
	form.push('<div class="control-group"><label class="control-label">名称：</label>');
	form.push('<div class="controls"><input type="text" name="name"></div></div>');
	form.push('</form>');
	var dialog = bootbox.dialog(form.join(''), [{
		label: '取消'
	}, {
		label: '确定',
		callback: function(){
			var name = $.trim($('input[name=name]', dialog).val());
			$.post('${ContextPath}/maldives/product/add.do', {
				name: name
			}).done(function(data){
				cqlybest.go('#main-content', '${ContextPath}/maldives/product/update.do?id=' + data);
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
		cqlybest.go('#main-content', '${ContextPath}/maldives/product.do?' + $.param(q));
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
	cqlybest.go('#main-content', '${ContextPath}/maldives/product/update.do?id=' + $(this).data('id'));
});
$('#page-content button[data-action=delete]').click(function(){
	cqlybest.error('暂时不能删除数据。');
});
</script>