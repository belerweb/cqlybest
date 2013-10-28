<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div id="page-content" class="clearfix">
	<div class="row-fluid">
		<h3 class="header smaller lighter blue">
			成功案例
			<button type="button" class="btn btn-mini btn-primary pull-right" data-action="add" onclick="cqlybest.go('#main-content', '${ContextPath}/admin/example/case/add.do');">增加案例</button>
		</h3>
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th class="center" style="width:40px;"><input type="checkbox"><span class="lbl"></span></th>
					<th>案例</th>
					<th class="center">封面</th>
					<th class="center" style="width:100px;">操作</th>
				</tr>
			</thead>
			<tbody>
				<#list result.items as case>
				<tr>
					<td class="center"><input type="checkbox" value="${case.id}"><span class="lbl"></span></td>
					<td>${case.name!}</td>
					<td class="center"><#if case.cover?has_content>有<#else>无</#if></td>
					<td class="td-actions center">
						<div class="btn-group">
							<button type="button" class="btn btn-mini btn-info" title="修改"
								data-action="edit" data-id="${case.id}">
								<i class="icon-edit bigger-120"></i>
							</button>
							<button type="button" class="btn btn-mini btn-danger" title="删除"
								data-action="delete" data-id="${case.id}" data-name="${case.name!}">
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
<script type="text/javascript">
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
		cqlybest.go('#main-content', '${ContextPath}/admin/example/case.do?' + $.param(q));
	}
});

$('#page-content button[data-action=edit]').click(function(){
	cqlybest.go('#main-content', '${ContextPath}/admin/example/case/update.do?id=' + $(this).data('id'));
});

$('#page-content button[data-action=delete]').click(function(){
	var id = $(this).data('id');
	var name = $(this).data('name');
	bootbox.confirm('确认要删除[' + name + ']？', function(result){
		if(result) {
			$.post('${ContextPath}/admin/example/case/delete.do', {
				id: id
			}).done(function(){
				cqlybest.reload('#main-content');
			}).fail(function(){
				cqlybest.error();
			});
		}
	});
});
</script>