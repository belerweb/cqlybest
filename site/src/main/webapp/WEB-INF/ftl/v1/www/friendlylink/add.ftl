<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div id="page-content" class="clearfix">
	<div class="row-fluid">
		<h3 class="header smaller lighter blue">
			增加友情链接
			<button type="button" class="btn btn-mini btn-primary pull-right" onclick="cqlybest.go('#main-content');">
				<i class="icon-mail-reply"></i> 返回
			</button>
		</h3>
		<form method="POST" action="${ContextPath}/www/friendlylink/add.do" class="form-horizontal">
			<div class="control-group">
				<label class="control-label">链接名称：</label>
				<div class="controls">
					<input name="name" type="text" placeholder="" class="input-xlarge">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">链接标题：</label>
				<div class="controls">
					<input name="title" type="text" placeholder="" class="input-xlarge">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">链接图片（可选）：</label>
				<div class="controls">
					<input name="image" type="text" readonly="readonly" placeholder="" class="input-xlarge">
					<button type="button" class="btn btn-info btn-mini" data-action="upload">上传图片</button>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">链接地址：</label>
				<div class="controls">
					<input name="link" type="text" placeholder="" class="input-xlarge">
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
			<div class="form-actions">
				<button type="submit" class="btn btn-info">
					<i class="icon-ok bigger-110"></i> 保存
				</button>
				<button type="button" class="btn" onclick="cqlybest.go('#main-content');">
					<i class="icon-mail-reply bigger-110"></i> 返回
				</button>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
$('#page-content button[data-action=upload]').click(function(){
	var images = cqlybest.uploadImage();
	if (images) {
		$(this).prev().val(images[0].id);
	}
});

$('#page-content input[name=displayOrder]').ace_spinner({min:0});

$('#page-content form').submit(function() {
	$(this).ajaxSubmit({
		success: function(response, status, xhr, form) {
			cqlybest.success("保存成功，可继续添加。返回列表请点 [返回] 按钮。");
			$('input[type=text]', form).val('');
		},
		error: function(xhr, status, response, form) {
			cqlybest.error(eval(xhr.responseText||'操作失败'));
		}
	});
	return false;
});
</script>