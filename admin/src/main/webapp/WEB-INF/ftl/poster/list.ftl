<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div>
	<div class="clearfix"></div>
	<div class="pagetitle">
		<h1>首页海报</h1>
		<div class="btn-group">
			<a href="#m=site;n=poster.list;u=${ContextPath}/poster/add.html;t=#mb" class="btn btn-primary">增加新海报</a>
		</div>
		<div class="clearfix"></div>
		<hr>
	</div>
	<div class="grid">
		<div class="grid-title">
			<div class="pull-left">
				<div class="icon-title"><i class="icon-table"></i></div>
				<span>海报列表</span> 
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>   
		</div>
		<div class="grid-content overflow">
			<table id="poster-list-table" class="table table-striped">
				<thead>
					<tr>
						<th>标题</th>
						<th>描述</th>
						<th>链接</th>
						<th>新窗口打开</th>
						<th>已发布</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<#list posters as poster>
					<tr>
						<td>${poster.title!}</td>
						<td>${poster.description!}</td>
						<td><a href="${poster.url!}" target="_blank">${poster.url!}</a></td>
						<td>
							<#if poster.newWindow>
							<span class="s_green">是</span>
							<#else>
							<span class="s_gray">否</span>
							</#if>
						</td>
						<td>
							<#if poster.published>
							<span class="s_green">是</span>
							<#else>
							<span class="s_gray">否</span>
							</#if>
						</td>
						<td class="action-table">
							<a href="${poster.imageUrl!}" target="_blank" title="查看图片"><img alt="查看图片" src="images/icon/table_view.png"></a>
							<#if poster.published>
							<a href="javascript:void(0);" data-url="${ContextPath}/poster/toggle.html?id=${poster.id}&published=false" class="ajax-action-btn" data-confirm="true" data-action="取消发布" data-title="${poster.title!}" title="取消发布"><img alt="取消发布" src="images/icon/table_unpublish.png"></a>
							<#else>
							<a href="javascript:void(0);" data-url="${ContextPath}/poster/toggle.html?id=${poster.id}&published=true" class="ajax-action-btn" data-confirm="true" data-action="发布" data-title="${poster.title!}" title="发布"><img alt="发布" src="images/icon/table_publish.png"></a>
							</#if>
							<a href="javascript:void(0);" data-url="${ContextPath}/poster/delete.html?id=${poster.id}" class="ajax-action-btn" data-confirm="true" data-action="删除" data-title="${poster.title!}" title="删除"><img alt="删除" src="images/icon/table_del.png"></a>
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
$('#poster-list-table').dataTable({
	bLengthChange: false,
	bFilter: false,
	bInfo: false,
	bPaginate: false
});
</script>