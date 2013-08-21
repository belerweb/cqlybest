<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div id="page-content" class="clearfix">
	<div class="row-fluid">
		<h3 class="header smaller lighter blue">马尔代夫海岛/酒店
			<button type="button" id="maldives-add" class="btn btn-mini btn-primary pull-right">增加新岛</button>
		</h3>
		<table id="main-list-table" class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th class="center" style="width:40px;"><input type="checkbox"><span class="lbl"></span></th>
					<th>名称</th>
					<th class="center" style="width:100px;">操作</th>
				</tr>
			</thead>
			<tbody>
				<#list result.items as island>
				<tr>
					<td class="center"><input type="checkbox" value="${island.id}"><span class="lbl"></span></td>
					<td>${island.zhName!} ${island.enName!}</td>
					<td class="td-actions center">
						<div class="btn-group">
							<button href="<#if options.site_url?has_content>${options.site_url}/maldives/${island.id}.html<#else>javascript:alert('请先配置网站地址。');</#if>"
								<#if options.site_url?has_content>target="_blank"</#if> class="btn btn-mini btn-success" title="预览"><i class="icon-external-link bigger-120"></i></a>
							<button type="button" data-id="${island.id}" class="btn btn-mini btn-info btn-action-edit" title="修改">
								<i class="icon-edit bigger-120"></i>
							</button>
							<button href="javascript:void(0);" data-url="${ContextPath}/maldives/delete.do?ids[]=${island.id}"
								class="btn btn-mini btn-danger" data-confirm="true" data-action="删除" data-title="${island.zhName!}"
								title="删除"><i class="icon-trash bigger-120"></i></a>
						</div>
					</td>
				</tr>
				</#list>
			</tbody>
		</table>
	</div>
</div>
<script>
$('#maldives-add').click(function(){
	var action = $(this).parent();
	var form = ['<form class="form-horizontal"><legend>添加海岛</legend>'];
	form.push('<div class="control-group"><label class="control-label">中文名称：</label>');
	form.push('<div class="controls"><input type="text" name="zhName"></div></div>');
	form.push('<div class="control-group"><label class="control-label">英文名称：</label>');
	form.push('<div class="controls"><input type="text" name="enName"></div></div>');
	form.push('</form>');
	var dialog = bootbox.dialog(form.join(''), [{
		label: '取消'
	}, {
		label: '确定',
		callback: function(){
			var zhName = $.trim($('input[name=zhName]', dialog).val());
			var enName = $.trim($('input[name=enName]', dialog).val());
			$.post('${ContextPath}/maldives/add.do', {
				zhName: zhName,
				enName: enName
			}).done(function(data){
				cqlybest.go('#main-content', '${ContextPath}/maldives/update.do?id=' + data);
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
$('#main-list-table').dataTable({
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
		cqlybest.go('#main-content', '${ContextPath}/maldives/list.do?' + $.param(q));
	}
});
$('#main-list-table button.btn-action-edit').click(function(){
	cqlybest.go('#main-content', '${ContextPath}/maldives/update.do?id=' + $(this).data('id'));
});
$('#main-list-table thead input:checkbox').click(function(){
	$('#main-list-table tbody input:checkbox').attr('checked', this.checked);
});
var getCheckedItems = function() {
	var result = [];
	$('#main-list-table tbody input:checkbox:checked').each(function(i, obj){
		result.push(obj.value);
	});
	return result;
};
var validateChecked = function(items, length) {
	var message = false;
	if (items.length == 0) {
		message = '请选择海岛';
	}
	if (length && items.length != length) {
		message = '请选择一个海岛';
	}
	if (message) {
		cqlybest.error(message);
	}
	return !message;
};
$('#maldives-marke-del').click(function(){
	var items = getCheckedItems();
	if (validateChecked(items)) {
		bootbox.confirm('确认要删除选择的海岛吗？', '取消', '确认', function(result) {
			if (result) {
				$.post('${ContextPath}/maldives/delete.do', {
					ids: items
				}, function(){
					cqlybest.reload('#main-content');
				});
			}
		});
	}
});
</script>