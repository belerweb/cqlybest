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
		<#assign URL='${ContextPath}/site/config.do'>
		<form class="form-horizontal">
			<div class="grid-content">
				<div class="control-group">
					<label class="control-label">网站名称：</label>
					<div class="controls">
						<a href="#" class="editable" data-name="site_name" data-type="text" data-url="${URL}" data-value="${options.site_name!}"></a>
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
					<label class="control-label">统计代码：</label>
					<div class="controls">
						<a href="#" class="editable" data-name="site_statistical_code" data-type="textarea" data-url="${URL}">${options.site_statistical_code!?html}</a>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">微博地址：</label>
					<div class="controls">
						<a href="#" class="editable" data-name="weibo_url" data-type="text" data-url="${URL}" data-value="${options.weibo_url!}"></a>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
		</form>
	</div>
</div>

<script type="text/javascript">
$('#mb a.editable').not('[data-type=select2]').editable();
cqlybest.editableTag('a[data-name=site_meta_keyword]');
</script>
