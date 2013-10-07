<div id="product-traffic-tab" class="tab-pane">
	<div class="text-right"><button type="button" class="btn btn-primary btn-mini add">添加</button></div>
	<table class="table table-striped table-bordered table-hover">
		<thead>
			<tr>
				<th>名称</th>
				<th>航班</th>
				<th>出发 - 到达</th>
				<th>时间</th>
				<th>补充信息</th>
				<th class="center" style="width:40px;">操作</th>
			</td>
		</thead>
		<tbody>
			<#assign PTURL="${ContextPath}/maldives/product/transportation/update.do" />
			<#if product.transportations?has_content>
			<#list product.transportations as item>
			<tr>
				<td>
					<a href="#" class="editable" data-pk="${item.id}" data-name="name" data-type="text" data-url="${PTURL}" data-value="${item.name!}"></a>
				</td>
				<td>
					<a href="#" class="editable" data-pk="${item.id}" data-name="detail" data-type="select2"
						data-url="${PTURL}" data-value="${(item.detail.id)!}" data-text="${(item.detail.number)!}">
						${(item.detail.number)!}
					</a>
				</td>
				<td>
					<#if item.detail?has_content>
					${item.detail.from!}${item.detail.departuresAirportCode!}
					-
					${item.detail.to!}${item.detail.arrivalsAirportCode!}
					</#if>
				</td>
				<td>
					<#if item.detail?has_content>
					<#if item.detail.departuresTime?has_content>
						${item.detail.departuresTime?string('HH:mm')}
					</#if>
					-
					<#if item.detail.arrivalsTime?has_content>
						${item.detail.arrivalsTime?string('HH:mm')} <span class="label label-success">＋${item.detail.arrivalsTime?string('d')}</span>
					</#if>
					</#if>
				</td>
				<td>
					<a href="#" class="editable" data-pk="${item.id}" data-name="extra" data-type="textarea" data-url="${PTURL}" data-value="${item.extra!}"></a>
				</td>
				<td class="td-actions center">
					<div class="btn-group">
						<button type="button" class="btn btn-mini btn-danger delete" title="删除"
							data-id="${item.id}">
							<i class="icon-trash bigger-120"></i>
						</button>
					</div>
				</td>
			</tr>
			</#list>
			</#if>
		</tbody>
	</table>
</div>
<script type="text/javascript">
$('#product-traffic-tab button.add').click(function(){
	var action = $(this).parent();
	var form = ['<form class="form-horizontal"><legend>添加交通</legend>'];
	form.push('<div class="control-group"><label class="control-label">名称：</label>');
	form.push('<div class="controls"><input type="text" name="name" placeholder="如：第一天、回程"></div></div></form>');
	var dialog = bootbox.dialog(form.join(''), [{
		label: '取消'
	}, {
		label: '确定',
		callback: function(){
			var name = $.trim($('input[name=name]', dialog).val());
			$.post('${ContextPath}/maldives/product/transportation/add.do', {
				productId: '${product.id}',
				name: name
			}).done(function(obj){
				cqlybest.reload('#main-content', function(){
					$('#product-update-tabs a[data-target="#product-traffic-tab"]').tab('show');
				});
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
$('#product-traffic-tab a[data-name=detail]').on('save', function(){
	cqlybest.reload('#main-content', function(){
		$('#product-update-tabs a[data-target="#product-traffic-tab"]').tab('show');
	});
}).editable({
	select2: {
		formatSearching: function () { return "搜索中..."; },
		formatNoMatches: function () { return "没有相关航班"; },
		ajax: {
			url: '${ContextPath}/maldives/flight/ajax.do',
			data: function (term, page) {
				return {q:term};
			},
			results: function(response) {
				var result = [];
				$.each(response, function(i, obj){
					result.push({id:obj.id,text:obj.number});
				});
				return {results:result};
			}
		}
	}
});
$('#product-traffic-tab button.delete').on('click', function() {
	var id = $(this).data('id');
	var el = $($(this).parents('tr').get(0));
	bootbox.confirm('确认删除交通？', '取消', '确认', function(result) {
		if (result) {
			$.post('${ContextPath}/maldives/product/transportation/delete.do', {
				id: id
			}).done(function(){
				el.remove();
			}).fail(function() {
				cqlybest.error();
			});
		}
	});
});
</script>