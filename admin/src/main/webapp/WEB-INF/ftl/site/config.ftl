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
		<#assign URL='${ContextPath}/site/config.do'>
		<div class="grid-content row-fluid form-horizontal">
			<div class="span6">
				<div class="control-group">
					<label class="control-label">网站名称：</label>
					<div class="controls">
						<a href="#" class="editable" data-name="site_name" data-type="text" data-url="${URL}" data-value="${options.site_name!}"></a>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">网站地址：</label>
					<div class="controls">
						<a href="#" class="editable" data-name="site_url" data-type="text" data-url="${URL}" data-value="${options.site_url!}"></a>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">后台地址：</label>
					<div class="controls">
						<a href="#" class="editable" data-name="site_admin_url" data-type="text" data-url="${URL}" data-value="${options.site_admin_url!}"></a>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">400电话：</label>
					<div class="controls">
						<a href="#" class="editable" data-name="site_400" data-type="text" data-url="${URL}" data-value="${options.site_400!}"></a>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">Meta：</label>
					<div class="controls">
						<a href="#" class="editable" data-name="site_meta" data-type="textarea" data-url="${URL}">${options.site_meta!?html}</a>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">关键字（词）：</label>
					<div class="controls">
						<a href="#" class="editable" data-name="site_meta_keyword" data-dict="tag" data-type="select2" data-url="${URL}" data-value="${options.site_meta_keyword!}">${options.site_meta_keyword!}</a>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">网站描述：</label>
					<div class="controls">
						<a href="#" class="editable" data-name="site_meta_description" data-type="textarea" data-url="${URL}">${options.site_meta_description!?html}</a>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">ICP备案号：</label>
					<div class="controls">
						<a href="#" class="editable" data-name="site_icp" data-type="text" data-url="${URL}" data-value="${options.site_icp!}"></a>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">许可证号：</label>
					<div class="controls">
						<a href="#" class="editable" data-name="site_icp_license" data-type="text" data-url="${URL}" data-value="${options.site_icp_license!}"></a>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">网站底部版权文字：</label>
					<div class="controls">
						<a href="#" class="editable" data-name="site_copyright" data-type="text" data-url="${URL}" data-value="${options.site_copyright!}"></a>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">微博地址：</label>
					<div class="controls">
						<a href="#" class="editable" data-name="weibo_url" data-type="text" data-url="${URL}" data-value="${options.weibo_url!}"></a>
					</div>
				</div>
			</div>
			<div class="span6">
				<div class="control-group">
					<label class="control-label"><a href="#" class="watermark">水印图片：</a></label>
					<div class="controls" data-id="<#if watermark?has_content>${watermark.id}</#if>">
						<#if watermark?has_content>
						<img src="${ContextPath}/image/${watermark.id}.${watermark.imageType}">
						</#if>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">水印位置：</label>
					<div class="controls">
						<a href="#" class="editable" data-name="watermark_position" data-type="select" data-url="${URL}" data-value="${options.watermark_position!}"></a>
					</div>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="row-fluid form-horizontal">
			<div class="span12">
				<div class="control-group">
					<label class="control-label">统计代码：</label>
					<div class="controls">
						<a href="#" class="editable" data-name="site_statistical_code" data-type="textarea" data-url="${URL}">${options.site_statistical_code!?html}</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
$('#mb a.editable').not('[data-type=select]').not('[data-type=select2]').editable();
cqlybest.editableTag('a[data-name=site_meta_keyword]');
$('#mb a.editable[data-name=watermark_position]').editable({
	source: [
		{value:'TOP_LEFT', text:'左上角'},
		{value:'TOP_CENTER', text:'顶部居中'},
		{value:'TOP_RIGHT', text:'右上角'},
		{value:'CENTER_RIGHT', text:'右侧居中'},
		{value:'BOTTOM_RIGHT', text:'右下角'},
		{value:'BOTTOM_CENTER', text:'底部居中'},
		{value:'BOTTOM_LEFT', text:'左下角'},
		{value:'CENTER_LEFT', text:'左侧居中'},
		{value:'CENTER', text:'正中'}
	]
});

$('#mb .watermark').click(function(e) {
	e.stopPropagation();
	e.preventDefault();
	var images = cqlybest.uploadImage('${ContextPath}');
	if (images) {
		var el = $(this).parent().next();
		var oid = el.attr('data-id');
		$.post('${ContextPath}/site/config.do', {
			name: 'watermark-image-id',
			value: images[0].id
		}, function() {
			if (oid && oid.length) {
				$.post('${ContextPath}/image/delete', {
					id: oid
				});
			}
			el.attr('data-id', images[0].id).empty().append('<img src="${ContextPath}/image/' + images[0].id + '.' + images[0].imageType + '">');
		});
	}
});
</script>
