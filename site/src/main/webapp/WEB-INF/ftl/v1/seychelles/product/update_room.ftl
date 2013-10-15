<div id="product-detail-tab" class="tab-pane">
	<#assign PMURL="${ContextPath}/seychelles/product/room/update.do">
	<div class="text-right"><button type="button" class="btn btn-primary btn-mini add">添加</button></div>
	<table class="table table-striped table-bordered table-hover">
		<thead>
			<tr>
				<th style="width:100px;">名称</th>
				<th>海岛</th>
				<th>房型</th>
				<th>餐</th>
				<th class="center" style="width:40px;">操作</th>
			</td>
		</thead>
		<tbody>
		<#if product.seychellesDetails?has_content>
		<#list product.seychellesDetails as detail>
			<tr>
				<td>
					<a href="#" class="editable" data-pk="${detail.id}" data-name="name" data-type="text"
						data-url="${PMURL}" data-value="${detail.name!}">
					</a>
				</td>
				<td>
					<a href="#" class="editable" data-pk="${detail.id}" data-name="island" data-type="select2"
						data-url="${PMURL}" data-value="${(detail.island.id)!}">
						${(detail.island.zhName)!}
					</a>
				</td>
				<td>
					<#if detail.island?has_content>
					<a href="#" class="editable" data-pk="${detail.id}" data-name="room" data-type="select2"
						data-url="${PMURL}" data-value="${(detail.room.id)!}" data-islandId="${detail.island.id}">
						${(detail.room.zhName)!}
					</a>
					<#else>
					请先选择海岛
					</#if>
				</td>
				<td>
					<a href="#" class="editable" data-pk="${detail.id}" data-name="meals" data-type="checklist"
						data-url="${PMURL}" data-value="${detail.meals?join(',')}">
						${detail.meals?join(',')}
					</a>
				</td>
				<td class="center">
					<div class="btn-group">
						<button type="button" class="btn btn-mini btn-danger delete" title="删除"
							data-id="${detail.id}">
							<i class="icon-trash bigger-120"></i>
						</button>
					</div>
				</td>
			</tr>
			<tr>
				<th>详细信息</th>
				<td colspan="4">
					<a href="#" class="editable" data-pk="${detail.id}" data-name="detail"
						data-type="textarea" data-url="${PMURL}">${detail.detail!}</a>
				</td>
			</tr>
		</#list>
		</#if>
		</tbody>
	</table>
</div>

<script type="text/javascript">
$('#product-detail-tab button.add').click(function(){
	var container = $(this).parents('.tab-pane');
	bootbox.prompt("行程名称", "取消", "确定", function(result) {
		var name = $.trim(result);
		if (name.length) {
			$.post('${ContextPath}/seychelles/product/room/add.do', {
				productId: '${product.id}',
				name: name
			}, function(){
				cqlybest.reload('#main-content', function(){
					$('#product-update-tabs a[data-target="#product-detail-tab"]').tab('show');
				});
			});
		}
	});
});
$('#product-detail-tab button.delete').click(function() {
	var id = $(this).attr('data-id');
	var row = $(this).closest('tr');
	bootbox.confirm('确认删除行程', '取消', '确认', function(result) {
		if (result) {
			$.post('${ContextPath}/seychelles/product/room/delete.do', {
				id: id
			}).done(function(){
				row.next().remove();
				row.remove();
			}).fail(function() {
				cqlybest.error();
			});
		}
	});
});
$('#product-detail-tab a[data-name=island]').on('save', function(){
	cqlybest.reload('#main-content', function(){
		$('#product-update-tabs a[data-target="#product-detail-tab"]').tab('show');
	});
}).editable({
	select2: {
		formatSearching: function () { return "搜索中..."; },
		formatNoMatches: function () { return "没有相关海岛"; },
		ajax: {
			url: '${ContextPath}/seychelles/island/ajax.do',
			data: function (term, page) {
				return {q:term};
			},
			results: function(response) {
				var result = [];
				$.each(response, function(i, obj){
					result.push({id:obj._id,text:obj.zhName});
				});
				return {results:result};
			}
		}
	}
});
$('#product-detail-tab a[data-name=room]').each(function(i, obj){
	var islandId = $(obj).attr('data-islandId');
	$(obj).editable({
		select2: {
			formatSearching: function () { return "搜索中..."; },
			formatNoMatches: function () { return "没有相关房型"; },
			ajax: {
				url: '${ContextPath}/seychelles/room/ajax.do?islandId='+islandId,
				data: function (term, page, context) {
					return {q:term};
				},
				results: function(response) {
					var result = [];
					$.each(response[0].rooms, function(i, obj){
						result.push({id:obj.id,text:obj.zhName});
					});
					return {results:result};
				}
			}
		}
	});
});
$('#product-detail-tab a[data-name=meals]').on('shown', function(){
	cqlybest.fixed(false);
}).on('hidden', function(){
	cqlybest.fixed(true);
}).editable({
	source: ['BB', 'HB', 'FB', 'AI', '早餐', '午餐', '晚餐', '一价全包']
});
</script>
