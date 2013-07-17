<div id="island-base-tab" class="tab-pane active">
	<div class="row-fluid">
		<div class="span6">
			<div class="control-group">
				<label class="control-label">中文名称：</label>
				<div class="controls">
					<a href="#" class="editable" data-pk="${id}" data-name="zhName" data-type="text" data-url="${url}" data-value="${island.zhName!}"></a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">英文名称：</label>
				<div class="controls">
					<a href="#" class="editable" data-pk="${id}" data-name="enName" data-type="text" data-url="${url}" data-value="${island.enName!}"></a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">别名：</label>
				<div class="controls">
					<a href="#" class="editable" data-pk="${id}" data-name="byName" data-type="select2" data-url="${url}" data-value="${island.byName!}"></a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">岛屿级别：</label>
				<div class="controls">
					<a href="#" class="editable" data-pk="${id}" data-name="level" data-type="text" data-url="${url}" data-value="${island.level!}"></a>
				</div>
			</div>
		</div>
		<div class="span6">
			<div class="control-group">
				<label class="control-label">上岛方式：</label>
				<div class="controls">
					<a href="#" class="editable" data-pk="${id}" data-name="way" data-type="text" data-url="${url}" data-value="${island.way!}"></a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">岛屿大小：</label>
				<div class="controls">
					<a href="#" class="editable" data-pk="${id}" data-name="area" data-type="text" data-url="${url}" data-value="${island.area!}"></a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">浮潜等级：</label>
				<div class="controls">
					<a href="#" class="editable" data-pk="${id}" data-name="snorkeling" data-type="text" data-url="${url}" data-value="${island.snorkeling!}"></a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">参考价格：</label>
				<div class="controls">
					<a href="#" class="editable" data-pk="${id}" data-name="price" data-type="text" data-url="${url}" data-value="${island.price!}"></a>
				</div>
			</div>
		</div>
	</div>
	<div class="row-fluid">
		<div class="span12">
			<div class="control-group">
				<label class="control-label"><a herf="#" class="editable-wysihtml5">详细信息：</a></label>
				<div class="controls">
					<div data-pk="${id}" data-name="description" data-type="wysihtml5" data-url="${url}" data-toggle="manual" data-original-title="编辑详细信息">
						${island.description!}
					</div>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
	<div class="row-fluid">
	</div>
</div>

<script type="text/javascript">
$('#island-base-tab .editable[data-name=byName]').editable({
	select2: {
		tags: [],
		tokenSeparators: [',']
	}
});
</script>
