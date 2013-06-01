<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div>
	<div class="clearfix"></div>
	<div class="grid">
		<div class="grid-title">
			<div class="pull-left">
				<div class="icon-title"><i class="icon-cogs"></i></div>
				<span>网站配置</span> 
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>   
		</div>
		<!-- novalidate -->
		<form class="form-horizontal">
			<div class="grid-content">
				<div class="control-group">
					<label class="control-label">网站名称：</label>
					<div class="controls">
						<a id="site_name" href="#" class="editable" data-type="text" data-url="${ContextPath}/site/config.html" data-value="${(options.site_name!)?html}"></a>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">关键字（词）：</label>
					<div class="controls">
						<a id="site_meta_keyword" href="#" class="editable" data-type="select2" data-url="${ContextPath}/site/config.html" data-value="${(options.site_meta_keyword!)?html}">${(options.site_meta_keyword!)?html}</a>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">网站描述：</label>
					<div class="controls">
						<a id="site_meta_description" href="#" class="editable" data-type="textarea" data-url="${ContextPath}/site/config.html" data-value="${(options.site_meta_description!)?html}"></a>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">ICP备案号：</label>
					<div class="controls">
						<a id="site_icp" href="#" class="editable" data-type="text" data-url="${ContextPath}/site/config.html" data-value="${(options.site_icp!)?html}"></a>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">网站底部版权文字：</label>
					<div class="controls">
						<a id="site_copyright" href="#" class="editable" data-type="text" data-url="${ContextPath}/site/config.html" data-value="${(options.site_copyright!)?html}"></a>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">统计代码：</label>
					<div class="controls">
						<a id="site_statistical_code" href="#" class="editable" data-type="textarea" data-url="${ContextPath}/site/config.html" data-value="${(options.site_statistical_code!)?html}"></a>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
		</form>
	</div>
</div>
<script>
$('#site_name').editable({
	// 长度 256
});
$('#site_meta_keyword').editable({
	inputclass: 'input-large',
	select2: {
		multiple: true,
		ajax: {
			url: '/data/dict.html?action=dict&type=keyword',
			data: function (term, page) {
				return {q:term};
			},
			results: function(response) {
				var result = [];
				$.each(response.tags, function(i, obj){
					result.push({id:obj.name,text:obj.name});
				});
				return {results:result};
			}
		},
		initSelection: function(el, callback) {
			callback(cqlybest.v2ss(el.val()||$('#site_meta_keyword').data('value')));
		}
	}
});
$('#site_meta_description').editable({
	// 长度 1024
});
$('#site_icp').editable({
	// 长度 32
});
$('#site_copyright').editable({
	// 长度 1024
});
$('#site_statistical_code').editable({
	// 长度 1024
});
</script>