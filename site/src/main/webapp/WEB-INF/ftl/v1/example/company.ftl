<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div id="page-content" class="clearfix">
	<div class="row-fluid">
		<h3 class="header smaller lighter blue">
			标杆企业
			<button type="button" class="btn btn-mini btn-primary pull-right" data-action="add" onclick="cqlybest.go('#main-content', '${ContextPath}/admin/example/company/add.do');">增加标杆企业</button>
		</h3>
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th class="center" style="width:40px;"><input type="checkbox"><span class="lbl"></span></th>
					<th>企业名称</th>
					<th>地区</th>
					<th>企业LOGO</th>
					<th class="center" style="width:100px;">操作</th>
				</tr>
			</thead>
			<tbody>
				<#list result.items as company>
				<tr>
					<td class="center"><input type="checkbox" value="${company.id}"><span class="lbl"></span></td>
					<td>${company.name!}</td>
					<td>${company.area!}</td>
					<td>
					<#if company.logo?has_content>
					<img src="http://${ImageServer}/${company.logo.qiniuKey}-companylogo" style="height:50px;">
					</#if>
					</td>
					<td class="td-actions center">
						<div class="btn-group">
							<button type="button" class="btn btn-mini btn-info" title="修改"
								data-action="edit" data-id="${company.id}">
								<i class="icon-edit bigger-120"></i>
							</button>
							<button type="button" class="btn btn-mini btn-danger" title="删除"
								data-action="delete" data-id="${company.id}" data-name="${company.name!}">
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
		cqlybest.go('#main-content', '${ContextPath}/admin/example/company.do?' + $.param(q));
	}
});

$('#page-content button[data-action=edit]').click(function(){
	cqlybest.go('#main-content', '${ContextPath}/admin/example/company/update.do?id=' + $(this).data('id'));
});

$('#page-content button[data-action=delete]').click(function(){
	var id = $(this).data('id');
	var name = $(this).data('name');
	bootbox.confirm('确认要删除[' + name + ']？', function(result){
		if(result) {
			$.post('${ContextPath}/admin/example/company/delete.do', {
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