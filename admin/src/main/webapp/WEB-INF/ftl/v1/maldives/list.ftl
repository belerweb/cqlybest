<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div id="page-content" class="clearfix">
	<div class="row-fluid">
		<h3 class="header smaller lighter blue">马尔代夫海岛/酒店
			<button type="button" class="btn btn-mini btn-primary pull-right" data-action="add">增加新岛</button>
		</h3>
		<table class="table table-striped table-bordered table-hover">
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
							<button type="button" class="btn btn-mini btn-success" title="预览" data-id="${island.id}" data-action="preview">
								<i class="icon-external-link bigger-120"></i>
							</button>
							<button type="button" class="btn btn-mini btn-info" title="修改"
								data-id="${island.id}" data-action="edit">
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
		cqlybest.go('#main-content', '${ContextPath}/maldives/list.do?' + $.param(q));
	}
});
$('#page-content button[data-action=preview]').click(function(){
	<#if (settings.basic.siteUrl)?has_content>
	window.open('${settings.basic.siteUrl}/maldives/' + $(this).data('id')  + '.html');
	<#else>
	cqlybest.error('请先配置网站地址。');
	</#if>
});
$('#page-content button[data-action=edit]').click(function(){
	cqlybest.go('#main-content', '${ContextPath}/maldives/update.do?id=' + $(this).data('id'));
});
$('#page-content button[data-action=delete]').click(function(){
	cqlybest.error('暂时不能删除数据。');
});
</script>