<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div>
	<div class="clearfix"></div>
	<div class="pagetitle">
		<h1>菜单</h1>
		<div class="btn-group">
			<a href="#m=site&n=menu.list&u=${ContextPath}/menu/add.html&t=%23mb" class="btn btn-primary">增加新菜单</a>
		</div>
		<div class="clearfix"></div>
		<hr>
	</div>
	<div class="grid">
		<div class="grid-title">
			<div class="pull-left">
				<div class="icon-title"><i class="icon-table"></i></div>
				<span>菜单列表</span> 
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>   
		</div>
		<div class="grid-content overflow">
			<table id="main-list-table" class="table table-striped">
				<thead>
					<tr>
						<th>顺序</th>
						<th>菜单</th>
						<th>类型</th>
						<th>新窗口</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<#list menus as menu>
					<tr>
						<td>${menu.displayOrder!}</td>
						<td>
							<#if menu.menuType==0>
							${menu.name!}(对应聚合：${(menu.productGroup.name)!})
							<#elseif menu.menuType==1>
							${menu.name!}
							<#elseif menu.menuType==2>
							<a href="${menu.url!}" target="_blank">${menu.name!}</a>
							</#if>
						</td>
						<td>
							<#if menu.menuType==0>
							<span class="label label-success">聚合菜单</span>
							<#elseif menu.menuType==1>
							<span class="label label-info">自定义页面</span>
							<#elseif menu.menuType==2>
							<span class="label label-inverse">外链</span>
							</#if>
						</td>
						<td>
							<#if menu.newWindow>
							<span class="s_green">是</span>
							<#else>
							<span class="s_gray">否</span>
							</#if>
						</td>
						<td>
							<#if menu.published>
							<span class="s_green">已发布</span>
							<#else>
							<span class="s_gray">未发布</span>
							</#if>
						</td>
						<td class="action-table">
							<a href="javascript:void(0);" data-url="${ContextPath}/menu/up.html?id=${menu.id}"
								class="<#if menu_index==0>gray<#else>ajax-action-btn blue</#if>" data-confirm="true" data-action="上移" data-title="${menu.name!}"
								title="上移"><i class="icon-arrow-up"></i></a>
							<a href="javascript:void(0);" data-url="${ContextPath}/menu/down.html?id=${menu.id}"
								class="<#if menu_index==menus?size-1>gray<#else>ajax-action-btn blue</#if>" data-confirm="true" data-action="下移" data-title="${menu.name!}"
								title="下移"><i class="icon-arrow-down"></i></a>
							<#if menu.published>
							<a href="javascript:void(0);" data-url="${ContextPath}/menu/toggle.html?id=${menu.id}&published=false"
								class="ajax-action-btn gray" data-confirm="true" data-action="取消发布" data-title="${menu.name!}"
								title="取消发布"><i class="icon-download-alt"></i></a>
							<#else>
							<a href="javascript:void(0);" data-url="${ContextPath}/menu/toggle.html?id=${menu.id}&published=true"
								class="ajax-action-btn blue" data-confirm="true" data-action="发布" data-title="${menu.name!}"
								title="发布"><i class="icon-upload-alt"></i></a>
							</#if>
							<a href="javascript:void(0);" data-url="${ContextPath}/menu/modify.html?id=${menu.id}"
								class="page-load-btn safe" data-target="#mb" title="修改"><i class="icon-edit"></i></a>
							<a href="javascript:void(0);" data-url="${ContextPath}/menu/delete.html?id=${menu.id}"
								class="ajax-action-btn danger last" data-confirm="true" data-action="删除" data-title="${menu.name!}"
								title="删除"><i class="icon-remove"></i></a>
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