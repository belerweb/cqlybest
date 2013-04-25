<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div>
	<div class="clearfix"></div>
	<div class="pagetitle">
		<h1>旅游产品</h1>
		<div class="btn-group">
			<a href="#m=site;n=product.list;u=${ContextPath}/product/add.html;t=#main" class="btn btn-primary">增加新产品</a>
		</div>
		<div class="clearfix"></div>
		<hr>
	</div>
	<div class="grid">
		<div class="grid-title">
			<div class="pull-left">
				<div class="icon-title"><i class="icon-table"></i></div>
				<span>产品列表</span> 
				<div class="clearfix"></div>
			</div>
			<div class="pull-right"> 
				<div class="icon-title"><a href="#" title="发布"><i class="icon-upload-alt"></i></a></div>
				<div class="icon-title"><a href="#" title="取消发布"><i class="icon-download-alt"></i></a></div>
				<div class="icon-title"><a href="#" title="删除"><i class="icon-remove"></i></a></div>
			</div>
			<div class="clearfix"></div>   
		</div>
		<div class="grid-content overflow">
			<table id="main-list-table" class="table table-striped">
				<thead>
					<tr>
						<th><input type="checkbox"></th>
						<th>产品名称</th>
						<th>状态</th>
						<th>最后修改时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<#list products as product>
					<tr>
						<td><input type="checkbox"></td>
						<td>${product.name!}</td>
						<td>
							<#if product.published>
							<span class="s_green">已发布</span>
							<#else>
							<span class="s_gray">未发布</span>
							</#if>
						</td>
						<td>${product.lastUpdate?string('yyyy-MM-dd HH:mm:ss')}</td>
						<td class="action-table">
							<#if product.published>
							<a href="javascript:void(0);" data-url="${ContextPath}/product/toggle.html?id=${product.id}&published=false"
								class="ajax-action-btn" data-confirm="true" data-action="取消发布" data-title="${product.name!}"
								title="取消发布"><img alt="取消发布" src="images/icon/table_unpublish.png"></a>
							<#else>
							<a href="javascript:void(0);" data-url="${ContextPath}/product/toggle.html?id=${product.id}&published=true"
								class="ajax-action-btn" data-confirm="true" data-action="发布" data-title="${product.name!}"
								title="发布"><img alt="发布" src="images/icon/table_publish.png"></a>
							</#if>
							<a href="#"><img alt="" src="images/icon/table_edit.png"></a>
							<a href="javascript:void(0);" data-url="${ContextPath}/product/delete.html?id=${product.id}"
								class="ajax-action-btn" data-confirm="true" data-action="删除" data-title="${product.name!}"
								title="删除"><img alt="删除" src="images/icon/table_del.png"></a>
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
	oLanguage:{
		oPaginate: {
			sFirst: '首页',
			sLast: '尾页',
			sNext: '下一页',
			sPrevious: '上一页'
		}
	},
	iDeferLoading: ${total},
	iDisplayStart: ${(page-1)*pageSize},
	iDisplayLength: ${pageSize},
	sPaginationType: 'full_numbers',
	bLengthChange: false,
	bFilter: false,
	bInfo: false,
	bServerSide: true,
	fnServerData: function (sSource, aoData, fnCallback, oSettings) {
		var p = {};
		$.each(aoData, function(i, obj){
			p[obj.name] = obj.value;
		});
		var q = {};
		q.page = p.iDisplayStart / p.iDisplayLength + 1;
		var u = '${ContextPath}/product/list.html?' + $.param(q);
		var hash  = {
			m: 'site',
			n: 'product.list',
			t: '#main',
			u: encodeURIComponent(u)
		};
		location.hash = cqlybest.buildHash(hash);
	}
});
</script>