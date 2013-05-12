<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div>
	<div class="clearfix"></div>
	<div class="pagetitle">
		<h1>产品聚合</h1>
		<div class="btn-group">
			<a href="#m=site&n=productgroup.list&u=${ContextPath}/product_group/add.html&t=#mb" class="btn btn-primary">增加产品聚合</a>
		</div>
		<div class="clearfix"></div>
		<hr>
	</div>
	<div class="grid">
		<div class="grid-title">
			<div class="pull-left">
				<div class="icon-title"><i class="icon-table"></i></div>
				<span>产品聚合列表</span> 
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>   
		</div>
		<div class="grid-content overflow">
			<table id="main-list-table" class="table table-striped">
				<colgroup>
					<col width="" />
					<col width="80" />
					<col width="120" />
				</colgroup>
				<thead>
					<tr>
						<th>聚合</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<#list groups as group>
					<tr>
						<td>${group.name!}</td>
						<td>
							<#if group.published>
							<span class="s_green">已发布</span>
							<#else>
							<span class="s_gray">未发布</span>
							</#if>
						</td>
						<td class="action-table">
							<#if group.published>
							<a href="javascript:void(0);" data-url="${ContextPath}/product_group/toggle.html?id=${group.id}&published=false"
								class="ajax-action-btn gray" data-confirm="true" data-action="取消发布" data-title="${group.name!}"
								title="取消发布"><i class="icon-download-alt"></i></a>
							<#else>
							<a href="javascript:void(0);" data-url="${ContextPath}/product_group/toggle.html?id=${group.id}&published=true"
								class="ajax-action-btn blue" data-confirm="true" data-action="发布" data-title="${group.name!}"
								title="发布"><i class="icon-upload-alt"></i></a>
							</#if>
							<a href="javascript:void(0);" data-url="${ContextPath}/product_group/modify.html?id=${group.id}"
								class="page-load-btn safe" data-target="#mb" title="修改"><i class="icon-edit"></i></a>
							<a href="javascript:void(0);" data-url="${ContextPath}/product_group/delete.html?id=${group.id}"
								class="ajax-action-btn danger last" data-confirm="true" data-action="删除"
								data-title="${group.name!}" title="删除"><i class="icon-remove"></i></a>
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