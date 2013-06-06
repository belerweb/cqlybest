<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div>
	<div class="clearfix"></div>
	<div class="pagetitle">
		<h1>产品聚合</h1>
		<div class="btn-group">
			<a id="product-group-add" href="javascript:void(0);" class="btn btn-primary">增加新产品</a>
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
							<a href="javascript:void(0);" data-url="${ContextPath}/product_group/update.do?id=${group.id}"
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
$('#product-group-add').click(function(){
	bootbox.prompt("聚合名称", "取消", "确定", function(result) {
		var name = $.trim(result);
		if (name.length) {
			$.post('${ContextPath}/product_group/add.do', {
				name: name
			}, function(response){
				var hash = cqlybest.parseHash();
				hash['u'] = '${ContextPath}/product_group/update.do?id=' + response;
				hash['_t'] = new Date().getTime();
				location.hash = cqlybest.buildHash(hash);
			});
		}
	});
});
</script>