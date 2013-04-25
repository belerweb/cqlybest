<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div>
	<div class="clearfix"></div>
	<div class="pagetitle">
		<h1>账号</h1>
		<div class="btn-group">
			<a href="#m=site;n=account.list;u=${ContextPath}/account/add.html;t=#mb" class="btn btn-primary">增加新账号</a>
		</div>
		<div class="clearfix"></div>
		<hr>
	</div>
	<div class="grid">
		<div class="grid-title">
			<div class="pull-left">
				<div class="icon-title"><i class="icon-table"></i></div>
				<span>账号列表</span> 
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>   
		</div>
		<div class="grid-content overflow">
			<table id="main-list-table" class="table table-striped">
				<thead>
					<tr>
						<th>名称</th>
						<th>网址</th>
						<th>账号</th>
						<th>密码</th>
						<th>备注</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<#list accounts as account>
					<tr>
						<td>${account.name!}</td>
						<td>${account.url!}</td>
						<td>${account.account!}</td>
						<td>${account.password!}</td>
						<td>${account.remark!}</td>
						<td class="action-table">
							<a href="javascript:void(0);" data-url="${ContextPath}/account/modify.html?id=${account.id}"
								class="page-load-btn" data-target="#mb" title="修改"><img alt="修改" src="images/icon/table_edit.png"></a>
							<a href="javascript:void(0);" data-url="${ContextPath}/account/delete.html?id=${account.id}"
								class="ajax-action-btn" data-confirm="true" data-action="删除"
								data-title="${account.name!}" title="删除"><img alt="删除" src="images/icon/table_del.png"></a>
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
	oLanguage: {
		sZeroRecords: '',
		sEmptyTable: ''
	},
	bLengthChange: false,
	bFilter: false,
	bInfo: false,
	bPaginate: false
});
</script>