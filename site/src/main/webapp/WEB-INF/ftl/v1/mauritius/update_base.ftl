<div id="hotel-base-tab" class="tab-pane active">
	<div class="row-fluid">
		<div class="span6">
			<div class="control-group">
				<label class="control-label">中文名称：</label>
				<div class="controls">
					<a href="#" class="editable" data-pk="${id}" data-name="zhName" data-type="text" data-url="${url}" data-value="${hotel.zhName!}"></a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">英文名称：</label>
				<div class="controls">
					<a href="#" class="editable" data-pk="${id}" data-name="enName" data-type="text" data-url="${url}" data-value="${hotel.enName!}"></a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">别名：</label>
				<div class="controls">
					<a href="#" class="editable" data-pk="${id}" data-name="byName" data-type="select2" data-url="${url}" data-value="${hotel.byName!}"></a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">标签：</label>
				<div class="controls">
					<a href="#" class="editable" data-dict="tag" data-pk="${id}" data-name="tags" data-type="select2" data-url="${url}" data-value="${hotel.tags!}">${hotel.tags!}</a>
				</div>
			</div>
		</div>
		<div class="span6">
			<div class="control-group">
				<label class="control-label">参考价格：</label>
				<div class="controls">
					<a href="#" class="editable" data-pk="${id}" data-name="price" data-type="text" data-url="${url}" data-value="${hotel.price!}"></a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">一句话广告词：</label>
				<div class="controls">
					<a href="#" class="editable" data-pk="${id}" data-name="ad" data-type="text" data-url="${url}" data-value="${hotel.ad!}"></a>
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
						${hotel.description!}
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
cqlybest.editableTag('#hotel-base-tab a[data-name=tags]');
$('#hotel-base-tab .editable[data-name=byName]').editable({
	select2: {
		tags: [],
		tokenSeparators: [',']
	}
});
</script>
