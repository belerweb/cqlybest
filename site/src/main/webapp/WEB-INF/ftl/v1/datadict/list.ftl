<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div id="page-content" class="clearfix">
	<div class="row-fluid">
		<h3 class="header smaller lighter blue">
			数据字典
			<small>
				<i class="icon-double-angle-right"></i>
				${DataDict.name}
			</small>
			<button type="button" class="btn btn-mini btn-primary pull-right" data-action="add">增加</button>
		</h3>
		<table id="main-list-table" class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th>名称</th>
					<th>拼音</th>
					<th>简拼</th>
					<th class="center" style="width:40px;">操作</th>
				</tr>
			</thead>
			<tbody>
				<#assign UpdateURL='${ContextPath}/datadict/update.do'>
				<#list result.items as dict>
				<tr>
					<td>${dict.name!}</td>
					<td>
						<a href="#" class="editable" data-pk="${dict.id}" data-name="pinyin"
							data-type="text" data-url="${UpdateURL}" data-value="${dict.pinyin!}">
						</a>
					</td>
					<td>
						<a href="#" class="editable" data-pk="${dict.id}" data-name="py"
							data-type="text" data-url="${UpdateURL}" data-value="${dict.py!}">
						</a>
					</td>
					<td class="td-actions center">
						<div class="btn-group">
							<button type="button" class="btn btn-mini btn-danger" title="删除" data-id="${dict.id}" data-action="delete">
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
$('#page-content button[data-action=add]').click(function(){
	bootbox.prompt('名称', '取消', '确定', function(result) {
		var name = $.trim(result);
		if (name.length) {
			$.post('${ContextPath}/datadict/add.do', {
				type: '${type}',
				name: name
			}).done(function(){
				cqlybest.reload('#main-content');
			}).fail(function(){
				cqlybest.error();
			});
		}
	});
});

$('#page-content button[data-action=delete]').click(function(){
	$.post('${ContextPath}/datadict/delete.do', {
		id: $(this).data('id')
	}).done(function(){
		cqlybest.reload('#main-content');
	}).fail(function(){
		cqlybest.error();
	});
});

$('#page-content a.editable').editable();

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
		cqlybest.go('#main-content', '${ContextPath}/datadict/list.do?type=${type}&' + $.param(q));
	}
});
</script>