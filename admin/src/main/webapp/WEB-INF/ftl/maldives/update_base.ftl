<div id="island-base-tab" class="tab-pane active">
	<div class="row-fluid">
		<div class="span6">
			<div class="control-group">
				<label class="control-label">中文名称：</label>
				<div class="controls">
					<a id="island_zh_name" href="#" class="editable" data-pk="${id}" data-name="zhName" data-type="text" data-url="${url}" data-value="${(island.zhName!)?html}"></a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">英文名称：</label>
				<div class="controls">
					<a id="island_en_name" href="#" class="editable" data-pk="${id}" data-name="enName" data-type="text" data-url="${url}" data-value="${(island.enName!)?html}"></a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">岛屿级别：</label>
				<div class="controls">
					<a id="island_level" href="#" class="editable" data-pk="${id}" data-name="level" data-type="text" data-url="${url}" data-value="${(island.level!)?html}"></a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">上岛方式：</label>
				<div class="controls">
					<a id="island_way" href="#" class="editable" data-pk="${id}" data-name="way" data-type="text" data-url="${url}" data-value="${(island.way!)?html}"></a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">岛屿大小：</label>
				<div class="controls">
					<a id="island_area" href="#" class="editable" data-pk="${id}" data-name="area" data-type="text" data-url="${url}" data-value="${(island.area!)?html}"></a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">浮潜等级：</label>
				<div class="controls">
					<a id="island_snorkeling" href="#" class="editable" data-pk="${id}" data-name="snorkeling" data-type="text" data-url="${url}" data-value="${(island.snorkeling!)?html}"></a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">参考价格：</label>
				<div class="controls">
					<a id="island_price" href="#" class="editable" data-pk="${id}" data-name="price" data-type="text" data-url="${url}" data-value="${(island.price!)?html}"></a>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="span6">
			<div class="clearfix"></div>
		</div>
	</div>
	<div class="row-fluid">
	</div>
</div>

<script type="text/javascript">
$('#island_zh_name').editable({
});
$('#island_en_name').editable({
});
$('#island_level').editable({
});
$('#island_way').editable({
});
$('#island_area').editable({
});
$('#island_snorkeling').editable({
});
$('#island_price').editable({
});
</script>