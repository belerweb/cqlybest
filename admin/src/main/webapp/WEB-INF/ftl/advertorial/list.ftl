<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div>
	<div class="clearfix"></div>
	<div class="pagetitle">
		<h1>软文</h1>
		<div class="btn-group">
			<a href="#m=site;n=advertorial.list;u=${ContextPath}/advertorial/add.html;t=#mb" class="btn btn-primary">增加新软文</a>
		</div>
		<div class="clearfix"></div>
		<hr>
	</div>
	<div class="grid">
		<div class="grid-title">
			<div class="pull-left">
				<div class="icon-title"><i class="icon-table"></i></div>
				<span>软文列表</span> 
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>   
		</div>
		<div class="grid-content overflow">
			<table id="main-list-table" class="table table-striped">
				<thead>
					<tr>
						<th>标题</th>
						<th>摘要</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<#list advertorials as advertorial>
					<tr>
						<td>${advertorial.title!}</td>
						<td>${advertorial.summary!}</td>
						<td class="action-table">
							<a href="${ContextPath}/advertorial/view.html?id=${advertorial.id!}" target="_blank" title="查看"><img alt="查看" src="images/icon/table_view.png"></a>
							<a href="javascript:void(0);" data-url="${ContextPath}/advertorial/modify.html?id=${advertorial.id}"
								class="page-load-btn" data-target="#mb" title="修改"><img alt="修改" src="images/icon/table_edit.png"></a>
							<a href="javascript:void(0);" data-url="${ContextPath}/advertorial/delete.html?id=${advertorial.id}"
								class="ajax-action-btn" data-confirm="true" data-action="删除"
								data-title="${advertorial.name!}" title="删除"><img alt="删除" src="images/icon/table_del.png"></a>
						</td>
					</tr>
					</#list>
				</tbody>
			</table>
			<div class="clearfix"></div>
		</div>
	</div>
</div>
<script>
$('#main-list-table').dataTable({
	bLengthChange: false,
	bFilter: false,
	bInfo: false,
	bPaginate: false
});
</script>