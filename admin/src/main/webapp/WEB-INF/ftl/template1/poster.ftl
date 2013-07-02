<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div class="row-fluid">
	<div class="grid span6">
		<div class="grid-title">
			<div class="pull-left">
				<div class="icon-title"><i class="icon-table"></i></div>
				<span>海报列表</span> 
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>   
		</div>
		<div class="grid-content overflow">
			<table id="template1-tab2-list-table" class="table table-striped">
				<thead>
					<tr>
						<th>标题</th>
						<th>已发布</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<#if posters?has_content>
					<#list posters as poster>
					<tr>
						<td>${poster.title!}</td>
						<td>
							<#if poster.published>
							<span class="s_green">是</span>
							<#else>
							<span class="s_gray">否</span>
							</#if>
						</td>
						<td class="action-table">
							<a href="${poster.imageUrl!}" target="_blank" title="查看图片" class="safe"><i class="icon-picture"></i></a>
							<#if poster.published>
							<a href="javascript:void(0);" data-url="${ContextPath}/template1/poster/toggle.html?id=${poster.id}&published=false"
								class="ajax-action-btn gray" data-confirm="true" data-action="取消发布" data-title="${poster.title!}"
								title="取消发布" data-target="#template1-tab2"><i class="icon-download-alt"></i></a>
							<#else>
							<a href="javascript:void(0);" data-url="${ContextPath}/template1/poster/toggle.html?id=${poster.id}&published=true"
								class="ajax-action-btn blue" data-confirm="true" data-action="发布" data-title="${poster.title!}"
								title="发布" data-target="#template1-tab2"><i class="icon-upload-alt"></i></a>
							</#if>
							<a href="javascript:void(0);" data-url="${ContextPath}/template1/poster/delete.html?id=${poster.id}"
								class="ajax-action-btn danger last" data-confirm="true" data-action="删除" data-title="${poster.title!}"
								title="删除" data-target="#template1-tab2"><i class="icon-remove"></i></a>
						</td>
					</tr>
					</#list>
					</#if>
				</tbody>
			</table>
			<div class="clearfix"></div>
		</div>
	</div>
	<div class="grid span6">
		<div class="grid-title">
			<div class="pull-left">
				<div class="icon-title"><i class="icon-plus"></i></div>
				<span>添加海报</span> 
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>   
		</div>
		<!-- novalidate -->
		<form id="template1-tab2-form" action="${ContextPath}/template1/poster/add.html" method="post" class="form-horizontal">
			<div class="grid-content">
				<div class="control-group">
					<label class="control-label">标题：</label>
					<div class="controls">
						<input type="text" class="span input" name="title" required="true" maxlength="32"> 
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">描述：</label>
					<div class="controls">
						<textarea rows="3" name="description" class="span input same-height-1" maxlength="256"></textarea>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">图片：</label>
					<div class="controls">
						<div class="input-append">
							<input class="input-medium" name="imageUrl" type="text" required="true" maxlength="256">
							<button class="btn btn-success" type="button" data-toggle="upload">选择/上传</button>
						</div>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">链接到：</label>
					<div class="controls">
						<input type="text" class="span input" name="url" maxlength="256"> 
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">打开方式：</label>
					<div class="controls">
						<label class="radio inline">
							<input type="radio" name="newWindow" value="true" checked="checked"> 新窗口
						</label>
						<label class="radio inline">
							<input type="radio" name="newWindow" value="false"> 当前窗口
						</label>
					</div>
				</div>
				<div class="text-center">
					<button class="btn btn-primary" type="submit">保存</button>
				</div>
				<div class="clearfix"></div>
			</div>
		</form>
	</div>
</div>
<script>
$('#template1-tab2-list-table').dataTable({
	bLengthChange: false,
	bFilter: false,
	bInfo: false,
	bPaginate: false
});

$('[data-toggle=upload]').click(function(){
	var images = cqlybest.uploadImage('${ContextPath}');
	if (images && images.length) {
		$(this).prev().val('/image/' + images[0].id + '.' + images[0].imageType);
	}
});

$('input,textarea,select', '#template1-tab2-form').jqBootstrapValidation({
	submitSuccess : function($form, event) {
		event.preventDefault();
		event.stopPropagation();
		$form.ajaxSubmit({
			success : function(response) {
				cqlybest.success(null, null, function(){
					$('#template1-tab2').load('${ContextPath}/template1/poster.html');
				});
			},
			error : function() {
				cqlybest.error();
			}
		});
	}
});
</script>