<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div id="page-content" class="clearfix">
	<div class="row-fluid">
		<div class="widget-box transparent">
			<div class="widget-header">
				<h4 class="lighter smaller">首页内容</h4>
				<div class="widget-toolbar no-border">
					<ul class="nav nav-tabs">
						<li class="active">
							<a href="#index-poster" data-toggle="tab">海报</a>
						</li>
						<li>
							<a href="#index-left" data-toggle="tab">左侧</a>
						</li>
						<li>
							<a href="#index-right" data-toggle="tab">右侧</a>
						</li>
					</ul>
				</div>
			</div>
			<div class="widget-body">
				<div class="widget-main">
					<div class="tab-content" style="overflow:visible;">
						<div class="tab-pane in active" id="index-poster">
							<div class="row-fluid">
								<h5 class="header smaller lighter green" style="margin-top:0;">
									增加海报
									<button type="button" class="btn btn-mini btn-primary pull-right" data-action="expand">展开/收缩</button>
								</h5>
								<form method="post" action="${ContextPath}/www/index/poster/add.do" class="form-horizontal hide" data-action="add">
									<div class="control-group">
										<label class="control-label">名称:</label>
										<div class="controls">
											<input name="name" type="text" placeholder="">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">标题:</label>
										<div class="controls">
											<input name="title" type="text" placeholder="">
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">描述:</label>
										<div class="controls">
											<textarea name="description"></textarea>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">图片：</label>
										<div class="controls">
											<input name="image" type="text" readonly="readonly" placeholder="">
											<button type="button" class="btn btn-info btn-mini" data-action="upload">上传图片</button>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">链接地址：</label>
										<div class="controls">
											<input name="url" type="text" placeholder="">
											<input name="target" type="checkbox" value="_blank" checked="checked">
											<label for="target" class="lbl"> 新窗口打开链接</label>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">显示顺序（小靠前）：</label>
										<div class="controls">
											<input name="displayOrder" type="text" class="input-mini" value="" />
										</div>
									</div>
									<div class="control-group">
										<div class="controls">
											<button type="submit" class="btn btn-primary">确定</button>
										</div>
									</div>
								</form>
							</div>
							<div class="row-fluid">
								<h5 class="header smaller lighter green" style="margin-top:0;">
									现有海报
									<button type="button" class="btn btn-mini btn-primary pull-right" data-action="expand">展开/收缩</button>
								</h5>
								<table class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th>名称</th>
											<th>标题</th>
											<th>描述</th>
											<th>链接</th>
											<th class="center" style="width:50px;">新窗口</th>
											<th class="center" style="width:50px;">顺序</th>
											<th class="center" style="width:50px;">已发布</th>
											<th class="center" style="width:80px;">操作</th>
										</tr>
									</thead>
									<tbody>
										<#assign UpdateURL='${ContextPath}/www/index/poster/update.do'>
										<#if page.posters?has_content>
										<#list page.posters?sort_by('displayOrder') as poster>
										<tr>
											<td>
												<a href="#" class="editable" data-pk="${poster.id}" data-name="name" data-type="text" data-url="${UpdateURL}" data-value="${poster.name!}"></a>
											</td>
											<td>
												<a href="#" class="editable" data-pk="${poster.id}" data-name="title" data-type="text" data-url="${UpdateURL}" data-value="${poster.title!}"></a>
											</td>
											<td>
												<a href="#" class="editable" data-pk="${poster.id}" data-name="description" data-type="textarea" data-url="${UpdateURL}">${poster.description!?html}</a>
											</td>
											<td>
												<a href="#" class="editable" data-pk="${poster.id}" data-name="link" data-type="text" data-url="${UpdateURL}" data-value="${poster.link!}"></a>
											</td>
											<td class="center">
												<label>
													<input name="target" type="checkbox" <#if poster.target?has_content>checked="checked"</#if> class="ace-switch ace-switch-5"
														data-id="${poster.id}" data-action="poster-target">
													<span class="lbl"></span>
												</label>
											</td>
											<td class="center">
												<a href="#" class="editable" data-pk="${poster.id}" data-name="displayOrder" data-type="text" data-url="${UpdateURL}" data-value="${poster.displayOrder!}"></a>
											</td>
											<td class="center">
												<label>
													<input name="published" type="checkbox" <#if poster.published?has_content && poster.published>checked="checked"</#if> class="ace-switch ace-switch-5"
														data-id="${poster.id}" data-action="publish-poster">
													<span class="lbl"></span>
												</label>
											</td>
											<td class="td-actions center">
												<div class="btn-group">
													<a href="http://${ImageServer}/${poster.image.qiniuKey}-gallery" target="_blank" class="btn btn-mini btn-success" title="查看图片">
														<i class="icon-picture bigger-120"></i>
													</a>
													<button type="button" class="btn btn-mini btn-danger" title="删除" data-id="${poster.id}" data-action="delete-poster">
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
						</div>
						<#include "index/section.ftl">
						<div class="tab-pane" id="index-left">
							<@renderSections page.contents "contents" />
						</div>
						<div class="tab-pane" id="index-right">
							<@renderSections page.sidebars "sidebars" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$('#page-content a.editable[data-type=text]').on('shown', function(){
	cqlybest.fixed(false);
}).on('hidden', function(){
	cqlybest.fixed(true);
}).editable();
$('#page-content a.editable[data-type=textarea]').editable();
$('#page-content button[data-action=expand]').click(function(){
	$(this).parent().next().toggleClass('hide');
});
$('#page-content input[data-action=poster-target]').click(function(){
	var checked = this.checked;
	$.post('${ContextPath}/www/index/poster/update.do', {
		pk: $(this).data('id'),
		name: 'target',
		value: checked?'_blank':''
	}).done(function(){
	}).fail(function(){
		cqlybest.error();
		this.checked = !checked;
	});
});
$('#page-content input[data-action=publish-poster]').click(function(){
	var checked = this.checked;
	$.post('${ContextPath}/www/index/poster/update.do', {
		pk: $(this).data('id'),
		name: 'published',
		value: checked
	}).done(function(){
	}).fail(function(){
		cqlybest.error();
		this.checked = !checked;
	});
});
$('#page-content button[data-action=delete-poster]').click(function(){
	var tr = $(this).closest('tr');
	var posterId = $(this).data('id');
	bootbox.confirm('确认删除此海报？', '取消', '确认', function(result) {
		if (result) {
			$.post('${ContextPath}/www/index/poster/delete.do', {
				posterId: posterId
			}).done(function(){
				tr.remove();
			}).fail(function(){
				cqlybest.error();
			});
		}
	});
});

$('#page-content input[name=displayOrder]').ace_spinner({min:0});

$('#page-content button[data-action=upload]').click(function(){
	var images = cqlybest.uploadImage();
	if (images) {
		$(this).prev().val(images[0].id);
	}
});

$('#page-content form select[name=type]').change(function(){
	var type = this.value;
	$('#page-content form .control-group[data-select=type]').hide();
	$('#page-content form .control-group[data-select=type][data-for~=' + type + ']').show();
});

$('#page-content button[data-action=update]').click(function(){
	var form = $(this).closest('form');
	form.attr('action', form.data('update'));
	form.submit();
});
$('#page-content button[data-action=delete]').click(function(){
	var form = $(this).closest('form');
	bootbox.confirm('确认删除此节内容？', '取消', '确认', function(result) {
		if (result) {
			form.attr('action', form.data('delete'));
			form.submit();
			form.parent().remove();
		}
	});
});

$('#page-content form').submit(function(){
	$(this).ajaxSubmit({
		success: function(response, status, xhr, form) {
			if (form.is('[data-action=add]')) {
				cqlybest.reload('#main-content');
			} else {
				cqlybest.success();
			}
		},
		error: function(xhr, status, response, form) {
			cqlybest.error(xhr.responseText);
		}
	});
	return false;
});
</script>