<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div>
	<div class="clearfix"></div>
	<div class="grid">
		<div class="grid-title">
			<div class="pull-left">
				<div class="icon-title"><i class="icon-picture"></i></div>
				<span>添加首页海报</span> 
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>   
		</div>
		<!-- novalidate -->
		<form id="main-content-form" action="${ContextPath}/poster/add.html" method="post" class="form-horizontal">
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
						<input type="text" class="span input" name="imageUrl" required="true" maxlength="128"> 
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">链接到：</label>
					<div class="controls">
						<input type="text" class="span input" name="url" maxlength="128"> 
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">链接打开方式：</label>
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
$('input,textarea,select', '#main-content-form').jqBootstrapValidation({
	submitSuccess : cqlybest.ajaxSubmit
});
</script>