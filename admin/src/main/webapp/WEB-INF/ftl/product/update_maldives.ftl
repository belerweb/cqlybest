<div id="product-detail-tab" class="tab-pane">
	<#assign PMURL="${ContextPath}/product/maldives/update.do">
	<div class="row-fluid">
		<div class="span10">
			<div class="control-group">
				<label class="control-label">简要行程（如2沙2水）：</label>
				<div class="controls">
					<a href="#" class="editable" data-pk="${(product.detail.id)!}" data-name="detail.room1" data-type="text" data-url="${PMURL}" data-value="${(product.detail.room1)!}"></a>
					<a href="#" class="editable" data-pk="${(product.detail.id)!}" data-name="detail.room1Unit" data-type="text" data-url="${PMURL}" data-value="${(product.detail.room1Unit)!}"></a>
					<a href="#" class="editable" data-pk="${(product.detail.id)!}" data-name="detail.room2" data-type="text" data-url="${PMURL}" data-value="${(product.detail.room2)!}"></a>
					<a href="#" class="editable" data-pk="${(product.detail.id)!}" data-name="detail.room2Unit" data-type="text" data-url="${PMURL}" data-value="${(product.detail.room2Unit)!}"></a>
					<a href="#" class="editable" data-pk="${(product.detail.id)!}" data-name="detail.room3" data-type="text" data-url="${PMURL}" data-value="${(product.detail.room3)!}"></a>
					<a href="#" class="editable" data-pk="${(product.detail.id)!}" data-name="detail.room3Unit" data-type="text" data-url="${PMURL}" data-value="${(product.detail.room3Unit)!}"></a>
				</div>
			</div>
		</div>
		<div class="span2 text-right">
			<button type="button" class="btn btn-primary add">添加详细</button>
		</div>
	</div>
	<#if product.maldives?has_content>
	<#list product.maldives as maldives>
	<div class="row-fluid">
		<div class="span12">
			<div class="control-group">
				<label class="control-label">
					<strong>
						<a href="#" class="editable" data-pk="${maldives.id}" data-name="maldives.name" data-type="text" data-url="${PMURL}" data-value="${maldives.name!}"></a>
					</strong>
				</label>
				<div class="controls">
					<button data-id="${maldives.id}" type="button" class="btn btn-mini btn-danger delete">删除</button>
				</div>
			</div>
		</div>
	</div>
	<div class="row-fluid">
		<div class="span6">
			<div class="control-group">
				<label class="control-label">海岛：</label>
				<div class="controls">
					<a href="#" class="editable" data-pk="${maldives.id}" data-name="maldives.islandId" data-type="select" data-url="${PMURL}" data-value="${maldives.islandId!}"></a>
				</div>
			</div>
		</div>
		<div class="span6">
			<#if maldives.islandId?has_content>
			<div class="control-group">
				<label class="control-label">房型：</label>
				<div class="controls">
					<a href="#" class="editable" data-islandId="${maldives.islandId}" data-pk="${maldives.id}" data-name="maldives.roomId" data-type="select" data-url="${PMURL}" data-value="${maldives.roomId!}"></a>
				</div>
			</div>
			</#if>
		</div>
	</div>
	<div class="row-fluid">
		<div class="span12">
			<div class="control-group">
				<label class="control-label">用餐：</label>
				<div class="controls">
					<a href="#" class="editable" data-pk="${maldives.id}" data-name="maldives.meal" data-type="text" data-url="${PMURL}" data-value="${maldives.meal!}"></a>
				</div>
			</div>
		</div>
	</div>
	<div class="row-fluid">
		<div class="span12">
			<div class="control-group">
				<label class="control-label">详细信息：</label>
				<div class="controls">
					<a href="#" class="editable" data-pk="${maldives.id}" data-name="maldives.extra" data-type="textarea" data-url="${PMURL}">${maldives.extra!?html}</a>
				</div>
			</div>
		</div>
	</div>
	</#list>
	</#if>
</div>

<script type="text/javascript">
$('#product-detail-tab button.add').click(function(){
	var container = $(this).parents('.tab-pane');
	bootbox.prompt("行程名称", "取消", "确定", function(result) {
		var name = $.trim(result);
		if (name.length) {
			$.post('${ContextPath}/product/maldives/add.do', {
				productId: '${product.id}',
				name: name
			}, function(){
				$('#mb').load('${ContextPath}/product/update.do?id=${product.id}', function(){
					$('#product-update-tabs a[data-target="#product-detail-tab"]').tab('show');
				});
			});
		}
	});
});
$('#product-detail-tab a.editable').not('[data-type=select]').editable();
$('#product-detail-tab a[data-name="maldives.islandId"]').editable({
	prepend: '请选择海岛',
	source: [
		<#list maldivesIslands as island>
		<#if island_index gt 0>,</#if>{value: '${island.id}', text: '${island.zhName!}${island.enName!}'}
		</#list>
	]
}).on('save', function(){
	$('#mb').load('${ContextPath}/product/update.do?id=${product.id}', function(){
		$('#product-update-tabs a[data-target="#product-detail-tab"]').tab('show');
	});
});
$('#product-detail-tab a[data-name="maldives.roomId"]').editable({
	prepend: '请选择房型',
	source: function() {
		var result;
		var islandId = $(this).attr('data-islandId');
		$.ajax({
			async: false,
			method: 'get',
			url: '${ContextPath}/product/maldives/room/list.do?islandId=' + islandId,
			success: function(data) {
				result = data;
			}
		});
		return result;
	}
});
$('#product-detail-tab button.delete').click(function() {
	var id = $(this).attr('data-id');
	var row = $(this).parents('.row-fluid');
	bootbox.confirm('确认删除行程', '取消', '确认', function(result) {
		if (result) {
			$.post('${ContextPath}/product/maldives/delete.do', {
				id: id
			}).done(function(){
				row.next().remove();
				row.next().remove();
				row.remove();
			}).fail(function() {
				cqlybest.error();
			});
		}
	});
});
</script>
