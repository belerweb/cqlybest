<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div>
	<div class="clearfix"></div>
	<div class="pagetitle">
		<h1>旅游产品</h1>
		<div class="btn-group">
			<a id="product-add" href="javascript:void(0);" class="btn btn-primary">增加新产品</a>
		</div>
		<div class="clearfix"></div>
		<hr>
	</div>
	<div id="search-form" class="form-inline" style="margin-top:20px;">
		<div class="row-fluid">
			<div class="span4">
				<label class="checkbox inline"><strong>热门：</strong></label>
				<label class="radio inline">
					<input name="product-hot" <#if !paramHot?exists>checked="checked"</#if> type="radio" value="-1"> 全部
				</label>
				<label class="radio inline">
					<input name="product-hot" <#if paramHot?exists && paramHot>checked="checked"</#if> type="radio" value="true"> 是
				</label>
				<label class="radio inline">
					<input name="product-hot" <#if paramHot?exists && !paramHot>checked="checked"</#if> type="radio" value="false"> 否
				</label>
			</div>
			<div class="span4">
				<label class="checkbox inline"><strong>推荐：</strong></label>
				<label class="radio inline">
					<input name="product-red" <#if !paramRed?exists>checked="checked"</#if> type="radio" value="-1"> 全部
				</label>
				<label class="radio inline">
					<input name="product-red" <#if paramRed?exists && paramRed>checked="checked"</#if> type="radio" value="true"> 是
				</label>
				<label class="radio inline">
					<input name="product-red" <#if paramRed?exists && !paramRed>checked="checked"</#if> type="radio" value="false"> 否
				</label>
			</div>
			<div class="span4">
				<label class="checkbox inline"><strong>特价：</strong></label>
				<label class="radio inline">
					<input name="product-spe" <#if !paramSpe?exists>checked="checked"</#if> type="radio" value="-1"> 全部
				</label>
				<label class="radio inline">
					<input name="product-spe" <#if paramSpe?exists && paramSpe>checked="checked"</#if> type="radio" value="true"> 是
				</label>
				<label class="radio inline">
					<input name="product-spe" <#if paramSpe?exists && !paramSpe>checked="checked"</#if> type="radio" value="false"> 否
				</label>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span4">
				<label class="checkbox inline"><strong>发布：</strong></label>
				<label class="radio inline">
					<input name="product-pub" <#if !paramPub?exists>checked="checked"</#if> type="radio" checked="checked" value="-1"> 全部
				</label>
				<label class="radio inline">
					<input name="product-pub" <#if paramPub?exists && paramPub>checked="checked"</#if> type="radio" value="true"> 是
				</label>
				<label class="radio inline">
					<input name="product-pub" <#if paramPub?exists && !paramPub>checked="checked"</#if> type="radio" value="false"> 否
				</label>
			</div>
			<div class="span4">
				<label class="checkbox inline"><strong>名称：</strong></label>
				<input type="text" class="input-small" placeholder="关键词" name="product-name" value="${paramName!}">
			</div>
			<div class="span4"><button type="button" class="btn s">搜索</button></div>
		</div>
	</div>
	<div class="grid" style="margin-top:5px;">
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
						<td><input type="checkbox" value="${product.id}"></td>
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
							<a href="javascript:void(0);" data-url="${ContextPath}/product/toggle.do?id=${product.id}&published=false"
								class="ajax-action-btn gray" data-confirm="true" data-action="取消发布" data-title="${product.name!}"
								title="取消发布"><i class="icon-download-alt"></i></a>
							<#else>
							<a href="javascript:void(0);" data-url="${ContextPath}/product/toggle.do?id=${product.id}&published=true"
								class="ajax-action-btn blue" data-confirm="true" data-action="发布" data-title="${product.name!}"
								title="发布"><i class="icon-upload-alt"></i></a>
							</#if>
							<a href="javascript:void(0);" data-url="${ContextPath}/product/update.do?id=${product.id}"
								class="page-load-btn safe" data-target="#mb" title="修改"><i class="icon-edit"></i></a>
							<a href="javascript:void(0);" data-url="${ContextPath}/product/delete.do?ids[]=${product.id}"
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
		var u = '${ContextPath}/product/list.do?' + $.param(q);
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
		bootbox.confirm('确认要对选择的产品标记为热门吗？', '取消', '确认', function(result) {
			if (result) {
				$.post('${ContextPath}/product/hot.do', {
					ids: items,
					hot: true
				}, function(){
					cqlybest.reload();
				});
			}
		});
	}
});
$('#product-marke-unhot').click(function(){
	var items = getCheckedItems();
	if (validateChecked(items)) {
		bootbox.confirm('确认要对选择的产品取消热门标记吗？', '取消', '确认', function(result) {
			if (result) {
				$.post('${ContextPath}/product/hot.do', {
					ids: items,
					hot: false
				}, function(){
					cqlybest.reload();
				});
			}
		});
	}
});
$('#product-marke-red').click(function(){
	var items = getCheckedItems();
	if (validateChecked(items)) {
		bootbox.confirm('确认要对选择的产品标记为推荐吗？', '取消', '确认', function(result) {
			if (result) {
				$.post('${ContextPath}/product/recommend.do', {
					ids: items,
					red: true
				}, function(){
					cqlybest.reload();
				});
			}
		});
	}
});
$('#product-marke-unred').click(function(){
	var items = getCheckedItems();
	if (validateChecked(items)) {
		bootbox.confirm('确认要对选择的产品取消推荐吗？', '取消', '确认', function(result) {
			if (result) {
				$.post('${ContextPath}/product/recommend.do', {
					ids: items,
					red: false
				}, function(){
					cqlybest.reload();
				});
			}
		});
	}
});
$('#product-marke-special').click(function(){
	var items = getCheckedItems();
	if (validateChecked(items)) {
		bootbox.confirm('确认要对选择的产品标记为特价吗？', '取消', '确认', function(result) {
			if (result) {
				$.post('${ContextPath}/product/special.do', {
					ids: items,
					special: true
				}, function(){
					cqlybest.reload();
				});
			}
		});
	}
});
$('#product-marke-unspecial').click(function(){
	var items = getCheckedItems();
	if (validateChecked(items)) {
		bootbox.confirm('确认要对选择的产品取消特价吗？', '取消', '确认', function(result) {
			if (result) {
				$.post('${ContextPath}/product/special.do', {
					ids: items,
					special: false
				}, function(){
					cqlybest.reload();
				});
			}
		});
	}
});
$('#product-marke-pub').click(function(){
	var items = getCheckedItems();
	if (validateChecked(items)) {
		bootbox.confirm('确认要发布选择的产品吗？', '取消', '确认', function(result) {
			if (result) {
				$.post('${ContextPath}/product/pub.do', {
					ids: items,
					pub: true
				}, function(){
					cqlybest.reload();
				});
			}
		});
	}
});
$('#product-marke-unpub').click(function(){
	var items = getCheckedItems();
	if (validateChecked(items)) {
		bootbox.confirm('确认要取消发布选择的产品吗？', '取消', '确认', function(result) {
			if (result) {
				$.post('${ContextPath}/product/pub.do', {
					ids: items,
					pub: false
				}, function(){
					cqlybest.reload();
				});
			}
		});
	}
});
$('#product-marke-del').click(function(){
	var items = getCheckedItems();
	if (validateChecked(items)) {
		bootbox.confirm('确认要删除选择的产品吗？', '取消', '确认', function(result) {
			if (result) {
				$.post('${ContextPath}/product/delete.do', {
					ids: items
				}, function(){
					cqlybest.reload();
				});
			}
		});
	}
});
$('#product-add').click(function(){
	var action = $(this).parent();
	var form = ['<form class="form-horizontal"><legend>添加产品</legend>'];
	form.push('<div class="control-group"><label class="control-label">名称：</label>');
	form.push('<div class="controls"><input type="text" name="name"></div></div>');
	form.push('<div class="control-group"><label class="control-label">类型：</label>');
	form.push('<div class="controls"><label class="radio inline"><input type="radio" name="type" value="0" checked="checked">普通产品</label>');
	form.push('<label class="radio inline"><input type="radio" name="type" value="1"> 马尔代夫</lavel>');
	form.push('</div></div></form>');
	var dialog = bootbox.dialog(form.join(''), [{
		label: '取消'
	}, {
		label: '确定',
		callback: function(){
			var name = $.trim($('input[name=name]', dialog).val());
			var type = $.trim($('input[name=type]:checked', dialog).val());
			if (!/^.{1,32}$/.test(name)) {
				bootbox.alert('请输入产品名称，且长度不超过32位');
				return false;
			}
			$.post('${ContextPath}/product/add.do', {
				name: name,
				type: type
			}).done(function(data){
				var hash = cqlybest.parseHash();
				hash['u'] = '${ContextPath}/product/update.do?id=' + data;
				hash['_t'] = new Date().getTime();
				location.hash = cqlybest.buildHash(hash);
			}).fail(function() {
				cqlybest.error();
			});
		}
	}]);
	$('form', dialog).on('submit', function(e) {
		e.preventDefault();
		dialog.find(".btn-primary").click();
	});
});

$('#search-form button.s').click(function(){
	var hash = cqlybest.parseHash();
	var p = [];
	var hot = $('#search-form input[name=product-hot]:checked').val();
	var red = $('#search-form input[name=product-red]:checked').val();
	var spe = $('#search-form input[name=product-spe]:checked').val();
	var pub = $('#search-form input[name=product-pub]:checked').val();
	var name = $.trim($('#search-form input[name=product-name]').val());
	if (hot!='-1') p.push('hot=' + hot);
	if (red!='-1') p.push('red=' + red);
	if (spe!='-1') p.push('spe=' + spe);
	if (pub!='-1') p.push('pub=' + pub);
	if (!/^\s*$/.test(name)) p.push('name=' + name);
	hash['u'] = '${ContextPath}/product/list.do?' + p.join('&');
	hash['_t'] = new Date().getTime();
	location.hash = cqlybest.buildHash(hash);
});
</script>