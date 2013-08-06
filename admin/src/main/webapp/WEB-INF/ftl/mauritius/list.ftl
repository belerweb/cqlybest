<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div>
	<div class="clearfix"></div>
	<div class="pagetitle">
		<h1>毛里求斯酒店</h1>
		<div class="btn-group">
			<a id="mauritius-add" href="javascript:void(0);" class="btn btn-primary">增加酒店</a>
		</div>
		<div class="clearfix"></div>
		<hr>
	</div>
	<div class="grid">
		<div class="grid-title">
			<div class="pull-left">
				<div class="icon-title"><i class="icon-table"></i></div>
				<span>酒店列表</span> 
				<div class="clearfix"></div>
			</div>
			<div class="pull-right"> 
				<div class="icon-title"><a id="mauritius-marke-del" href="javascript:void(0);" class="danger" title="删除"><i class="icon-remove"></i></a></div>
			</div>
			<div class="clearfix"></div>   
		</div>
		<div class="grid-content overflow">
			<table id="main-list-table" class="table table-striped">
				<colgroup>
					<col width="40" />
					<col width="" />
					<col width="120" />
				</colgroup>
				<thead>
					<tr>
						<th><input type="checkbox"></th>
						<th>名称</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<#list hotels as hotel>
					<tr>
						<td><input type="checkbox" value="${hotel.id}"></td>
						<td>${hotel.zhName!} ${hotel.enName!}</td>
						<td class="action-table">
							<a href="<#if options.site_url?has_content>${options.site_url}/mauritius/${hotel.id}.html<#else>javascript:alert('请先配置网站地址。');</#if>"
								<#if options.site_url?has_content>target="_blank"</#if> class="page-load-btn safe" title="预览"><i class="icon-external-link"></i></a>
							<a href="javascript:void(0);" data-url="${ContextPath}/mauritius/update.do?id=${hotel.id}"
								class="page-load-btn safe" data-target="#mb" title="修改"><i class="icon-edit"></i></a>
							<a href="javascript:void(0);" data-url="${ContextPath}/mauritius/delete.do?ids[]=${hotel.id}"
								class="ajax-action-btn danger last" data-confirm="true" data-action="删除" data-title="${hotel.zhName!}"
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
		var u = '${ContextPath}/mauritius/list.do?' + $.param(q);
		var hash  = {
			m: 'site',
			n: 'mauritius.list',
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
		message = '请选择酒店';
	}
	if (length && items.length != length) {
		message = '请选择一个酒店';
	}
	if (message) {
		cqlybest.error(message);
	}
	return !message;
};
$('#mauritius-marke-del').click(function(){
	var items = getCheckedItems();
	if (validateChecked(items)) {
		bootbox.confirm('确认要删除选择的酒店吗？', '取消', '确认', function(result) {
			if (result) {
				$.post('${ContextPath}/mauritius/delete.do', {
					ids: items
				}, function(){
					cqlybest.reload();
				});
			}
		});
	}
});
$('#mauritius-add').click(function(){
	var action = $(this).parent();
	var form = ['<form class="form-horizontal"><legend>添加酒店</legend>'];
	form.push('<div class="control-group"><label class="control-label">中文名称：</label>');
	form.push('<div class="controls"><input type="text" name="zhName"></div></div>');
	form.push('<div class="control-group"><label class="control-label">英文名称：</label>');
	form.push('<div class="controls"><input type="text" name="enName"></div></div>');
	form.push('</form>');
	var dialog = bootbox.dialog(form.join(''), [{
		label: '取消'
	}, {
		label: '确定',
		callback: function(){
			var zhName = $.trim($('input[name=zhName]', dialog).val());
			var enName = $.trim($('input[name=enName]', dialog).val());
			if (!/^.{1,64}$/.test(zhName)) {
				bootbox.alert('请输入酒店中文名称，且长度不超过64位');
				return false;
			}
			if (!/^.{1,128}$/.test(enName)) {
				bootbox.alert('请输入酒店英文名称，且长度不超过128位');
				return false;
			}
			$.post('${ContextPath}/mauritius/add.do', {
				zhName: zhName,
				enName: enName
			}).done(function(data){
				var hash = cqlybest.parseHash();
				hash['u'] = '${ContextPath}/mauritius/update.do?id=' + data;
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
</script>