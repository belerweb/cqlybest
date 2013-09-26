<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div id="page-content" class="clearfix">
	<div class="row-fluid">
		<h3 class="header smaller lighter blue">
			友情链接
			<button type="button" class="btn btn-mini btn-primary pull-right" onclick="cqlybest.go('#main-content', '${ContextPath}/www/friendlylink/add.do');">增加链接</button>
		</h3>
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th>名称</th>
					<th>标题</th>
					<th>图片</th>
					<th>链接</th>
					<th class="center" style="width:50px;">新窗口</th>
					<th class="center" style="width:50px;">顺序</th>
					<th class="center" style="width:80px;">操作</th>
				</tr>
			</thead>
			<tbody>
				<#assign UpdateURL='${ContextPath}/www/friendlylink/update.do'>
				<#list result.items as item>
				<tr>
					<td>
						<a href="#" class="editable" data-pk="${item.id}" data-name="name" data-type="text" data-url="${UpdateURL}" data-value="${item.name!}"></a>
					</td>
					<td>
						<a href="#" class="editable" data-pk="${item.id}" data-name="title" data-type="text" data-url="${UpdateURL}" data-value="${item.title!}"></a>
					</td>
					<td>
						<#if item.image?has_content>
						<img src="${ContextPath}/image/${item.image.id}.${item.image.extension}"><br/>
						</#if>
					</td>
					<td>
						<a href="#" class="editable" data-pk="${item.id}" data-name="link" data-type="text" data-url="${UpdateURL}" data-value="${item.link!}"></a>
					</td>
					<td class="center"><#if item.target?has_content><span class="label label-success">是</span><#else><span class="label label-warning">否</span></#if></td>
					<td>
						<a href="#" class="editable" data-pk="${item.id}" data-name="displayOrder" data-type="text" data-url="${UpdateURL}" data-value="${item.displayOrder!}"></a>
					</td>
					<td class="td-actions center">
						<div class="btn-group">
							<button type="button" class="btn btn-mini btn-info" title="上传图片" data-id="${item.id}" data-action="upload">
								<i class="icon-picture bigger-120"></i>
							</button>
							<button type="button" class="btn btn-mini btn-danger" title="删除" data-id="${item.id}" data-action="delete">
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
$('#page-content a.editable[data-type=text]').editable();
$('#page-content button[data-action=upload]').click(function(){
	var images = cqlybest.uploadImage();
	if (images) {
		$.post('${ContextPath}/www/friendlylink/update.do', {
			pk: $(this).data('id'),
			name: 'image',
			value: images[0].id
		}).done(function(){
			cqlybest.reload('#main-content');
		}).fail(function(){
			cqlybest.error('图片上传失败');
		});
	}
});
$('#page-content button[data-action=delete]').click(function(){
	$.post('${ContextPath}/www/friendlylink/delete.do', {
		linkId: $(this).data('id')
	}).done(function(){
		cqlybest.reload('#main-content');
	}).fail(function(){
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
		cqlybest.go('#main-content', '${ContextPath}/www/friendlylink.do?' + $.param(q));
	}
});
</script>