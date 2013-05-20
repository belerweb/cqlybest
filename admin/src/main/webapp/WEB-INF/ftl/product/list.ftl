<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div>
	<div class="clearfix"></div>
	<div class="pagetitle">
		<h1>旅游产品</h1>
		<div class="btn-group">
			<a href="#m=site&n=product.list&u=${ContextPath}/product/add.html&t=%23main" class="btn btn-primary">增加新产品</a>
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
				<div class="icon-title"><a id="product-marke-hot" href="javascript:void(0);" class="danger" title="批量标记为热门"><i class="icon-fire"></i></a></div>
				<div class="icon-title"><a id="product-marke-unhot" href="javascript:void(0);" class="gray" title="批量取消热门"><i class="icon-fire"></i></a></div>
				<div class="icon-title"><a id="product-marke-red" href="javascript:void(0);" class="blue" title="批量标记为推荐"><i class="icon-thumbs-up"></i></a></div>
				<div class="icon-title"><a id="product-marke-unred" href="javascript:void(0);" class="gray" title="批量取消推荐"><i class="icon-thumbs-down"></i></a></div>
				<div class="icon-title"><a id="product-marke-special" href="javascript:void(0);" class="yl" title="批量标记为特价"><i class="icon-bolt"></i></a></div>
				<div class="icon-title"><a id="product-marke-unspecial" href="javascript:void(0);" class="gray" title="批量取消特价"><i class="icon-bolt"></i></a></div>
				<div class="icon-title"><a id="product-marke-pub" href="javascript:void(0);" class="safe" title="发布"><i class="icon-upload-alt"></i></a></div>
				<div class="icon-title"><a id="product-marke-unpub" href="javascript:void(0);" class="gray" title="取消发布"><i class="icon-download-alt"></i></a></div>
				<div class="icon-title"><a id="product-marke-del" href="javascript:void(0);" class="danger" title="删除"><i class="icon-remove"></i></a></div>
			</div>
			<div class="clearfix"></div>   
		</div>
		<div class="grid-content overflow">
			<table id="main-list-table" class="table table-striped">
				<colgroup>
					<col width="40" />
					<col width="" />
					<col width="80" />
					<col width="80" />
					<col width="80" />
					<col width="80" />
					<col width="120" />
				</colgroup>
				<thead>
					<tr>
						<th><input type="checkbox"></th>
						<th>产品名称</th>
						<th>热门</th>
						<th>推荐</th>
						<th>特价</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<#list products as product>
					<tr>
						<td><input type="checkbox"></td>
						<td>${product.name!}</td>
						<td>
							<#if product.popular?exists && product.popular>
							<span class="s_green">热门</span>
							</#if>
						</td>
						<td>
							<#if product.recommend?exists && product.recommend>
							<span class="s_green">推荐</span>
							</#if>
						</td>
						<td>
							<#if product.specialOffer?exists && product.specialOffer>
							<span class="s_green">特价</span>
							</#if>
						</td>
						<td>
							<#if product.published?exists && product.published>
							<span class="s_green">已发布</span>
							<#else>
							<span class="s_gray">未发布</span>
							</#if>
						</td>
						<td class="action-table">
							<#if product.published?exists && product.published>
							<a href="javascript:void(0);" data-url="${ContextPath}/product/toggle.html?id=${product.id}&published=false"
								class="ajax-action-btn gray" data-confirm="true" data-action="取消发布" data-title="${product.name!}"
								title="取消发布"><i class="icon-download-alt"></i></a>
							<#else>
							<a href="javascript:void(0);" data-url="${ContextPath}/product/toggle.html?id=${product.id}&published=true"
								class="ajax-action-btn blue" data-confirm="true" data-action="发布" data-title="${product.name!}"
								title="发布"><i class="icon-upload-alt"></i></a>
							</#if>
							<a href="javascript:void(0);" data-url="${ContextPath}/product/modify.html?id=${product.id}"
								class="page-load-btn safe" data-target="#mb" title="修改"><i class="icon-edit"></i></a>
							<a href="javascript:void(0);" data-url="${ContextPath}/product/delete.html?id=${product.id}"
								class="ajax-action-btn danger last" data-confirm="true" data-action="删除" data-title="${product.name!}"
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
	iDeferLoading: ${total},
	iDisplayStart: ${(page-1)*pageSize},
	iDisplayLength: ${pageSize},
	bLengthChange: false,
	bFilter: false,
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
			u: u
		};
		location.hash = cqlybest.buildHash(hash);
	}
});
$('#main-list-table thead input:checkbox').click(function(){
	$('#main-list-table tbody input:checkbox').attr('checked', this.checked);
});
var getCheckedItems = function() {
	var result = [];
	$('#main-list-table tbody input:checkbox:checked').each(function(i, obj){
		result.push(obj.value);
	});
	return result;
};
var validateChecked = function(items, length) {
	var message = false;
	if (items.length == 0) {
		message = '请选择产品';
	}
	if (length && items.length != length) {
		message = '请选择一个产品';
	}
	if (message) {
		cqlybest.error(message);
	}
	return !message;
};
$('#product-marke-hot').click(function(){
	var items = getCheckedItems();
	if (validateChecked(items)) {
	}
});
$('#product-marke-unhot').click(function(){
	var items = getCheckedItems();
	if (validateChecked(items)) {
	}
});
$('#product-marke-red').click(function(){
	var items = getCheckedItems();
	if (validateChecked(items)) {
	}
});
$('#product-marke-unred').click(function(){
	var items = getCheckedItems();
	if (validateChecked(items)) {
	}
});
$('#product-marke-special').click(function(){
	var items = getCheckedItems();
	if (validateChecked(items)) {
	}
});
$('#product-marke-unspecial').click(function(){
	var items = getCheckedItems();
	if (validateChecked(items)) {
	}
});
$('#product-marke-pub').click(function(){
	var items = getCheckedItems();
	if (validateChecked(items)) {
	}
});
$('#product-marke-unpub').click(function(){
	var items = getCheckedItems();
	if (validateChecked(items)) {
	}
});
$('#product-marke-del').click(function(){
	var items = getCheckedItems();
	if (validateChecked(items)) {
	}
});
</script>