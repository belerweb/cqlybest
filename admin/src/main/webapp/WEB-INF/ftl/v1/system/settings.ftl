<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div id="page-content" class="clearfix">
	<div class="widget-box transparent">
		<div class="widget-header">
			<h4 class="lighter smaller">
				系统设置
			</h4>
			<div class="widget-toolbar no-border">
				<ul class="nav nav-tabs">
					<li class="active">
						<a href="#settings-base" data-toggle="tab">基本设置</a>
					</li>
					<li>
						<a href="#settings-mp" data-toggle="tab">微信公众平台</a>
					</li>
				</ul>
			</div>
		</div>
		<div class="widget-body">
			<div class="widget-main">
				<div class="tab-content form-horizontal" style="overflow:visible;">
					<#assign UpdateURL='${ContextPath}/system/settings/update.do'>
					<div class="tab-pane in active" id="settings-base">
						<div class="control-group">
							<label class="control-label">网站名称：</label>
							<div class="controls">
								<a href="#" class="editable" data-name="basic.siteName" data-type="text"
									data-url="${UpdateURL}" data-value="${(settings.basic.siteName)!}"></a>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><a href="#" data-name="basic.logo" data-action="logo">LOGO：</a></label>
							<div class="controls">
								<#if (settings.basic.logo)?has_content>
								<img src="${ContextPath}/image/${settings.basic.logo.id}.${settings.basic.logo.extension}">
								<#else>
								<a class="editable editable-empty">未设置</a>
								</#if>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">网站地址：</label>
							<div class="controls">
								<a href="#" class="editable" data-name="basic.siteUrl" data-type="text"
									data-url="${UpdateURL}" data-value="${(settings.basic.siteUrl)!}"></a>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">移动网站地址：</label>
							<div class="controls">
								<a href="#" class="editable" data-name="basic.mobileSiteUrl" data-type="text"
									data-url="${UpdateURL}" data-value="${(settings.basic.mobileSiteUrl)!}"></a>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">后台地址：</label>
							<div class="controls">
								<a href="#" class="editable" data-name="basic.adminSiteUrl" data-type="text"
									data-url="${UpdateURL}" data-value="${(settings.basic.adminSiteUrl)!}"></a>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">400电话：</label>
							<div class="controls">
								<a href="#" class="editable" data-name="basic.hotline" data-type="text"
									data-url="${UpdateURL}" data-value="${(settings.basic.hotline)!}"></a>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">网页Meta：</label>
							<div class="controls">
								<a href="#" class="editable" data-name="basic.meta" data-type="textarea"
									data-url="${UpdateURL}">${(settings.basic.meta)!?html}</a>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">网页关键词：</label>
							<div class="controls">
								<a href="#" class="editable" data-name="basic.keywords" data-type="select2"
									data-url="${UpdateURL}" data-dict="tag">
									<#if (settings.basic.keywords)?has_content>
									${settings.basic.keywords?join(',')}
									</#if>
								</a>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">网页描述：</label>
							<div class="controls">
								<a href="#" class="editable" data-name="basic.description" data-type="textarea"
									data-url="${UpdateURL}">${(settings.basic.description)!?html}</a>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">ICP备案号：</label>
							<div class="controls">
								<a href="#" class="editable" data-name="basic.icp" data-type="text"
									data-url="${UpdateURL}" data-value="${(settings.basic.icp)!}"></a>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">国际业务许可证号：</label>
							<div class="controls">
								<a href="#" class="editable" data-name="basic.icpLicense" data-type="text"
									data-url="${UpdateURL}" data-value="${(settings.basic.icpLicense)!}"></a>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">网站底部版权文字：</label>
							<div class="controls">
								<a href="#" class="editable" data-name="basic.copyright" data-type="text"
									data-url="${UpdateURL}" data-value="${(settings.basic.copyright)!}"></a>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">官方微博号码（数字）：</label>
							<div class="controls">
								<a href="#" class="editable" data-name="basic.weibo.id" data-type="text"
									data-url="${UpdateURL}" data-value="${(settings.basic.weibo.id)!}"></a>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">官方微博昵称：</label>
							<div class="controls">
								<a href="#" class="editable" data-name="basic.weibo.nickname" data-type="text"
									data-url="${UpdateURL}" data-value="${(settings.basic.weibo.nickname)!}"></a>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">统计代码：</label>
							<div class="controls">
								<a href="#" class="editable" data-name="basic.statistical" data-type="textarea"
									data-url="${UpdateURL}">${(settings.basic.statistical)!?html}</a>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><a href="#" data-name="watermark.img" data-action="watermark">水印图片：</a></label>
							<div class="controls">
								<#if (settings.watermark.img)?has_content>
								<img src="${ContextPath}/image/${settings.watermark.img.id}.${settings.watermark.img.extension}">
								<#else>
								<a class="editable editable-empty">未设置</a>
								</#if>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">图片水印位置：</label>
							<div class="controls">
								<a href="#" class="editable" data-name="watermark.position" data-type="select"
									data-url="${UpdateURL}" data-value="${(settings.watermark.position)!}"></a>
							</div>
						</div>
					</div>
					<div class="tab-pane" id="settings-mp">
						<div class="control-group">
							<label class="control-label">关注欢迎词：</label>
							<div class="controls">
								<a href="#" class="editable" data-name="mp.message.welcome" data-type="textarea"
									data-url="${UpdateURL}">${(settings.mp.message.welcome)!?html}</a>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">帮助信息：</label>
							<div class="controls">
								<a href="#" class="editable" data-name="mp.message.help" data-type="textarea"
									data-url="${UpdateURL}">${(settings.mp.message.help)!?html}</a>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">未知命令回复：</label>
							<div class="controls">
								<a href="#" class="editable" data-name="mp.message.unknown" data-type="textarea"
									data-url="${UpdateURL}">${(settings.mp.message.unknown)!?html}</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$('#page-content a.editable[data-type=text]').editable();
$('#page-content a.editable[data-type=textarea]').editable();
cqlybest.editableTag('#page-content a.editable[data-name="basic.keywords"]');
$('a[data-action=logo],a[data-action=watermark]', '#page-content').click(function(e) {
	e.stopPropagation();
	e.preventDefault();
	var images = cqlybest.uploadImage();
	if (images) {
		var el = $(this).parent().next();
		$.post('${ContextPath}/system/settings/update.do', {
			name: $(this).data('name'),
			value: [images[0].id, images[0].extension]
		}).done(function(){
			cqlybest.reload('#main-content');
		});
	}
});

$('#page-content a.editable[data-name="watermark.position"]').editable({
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
</script>
