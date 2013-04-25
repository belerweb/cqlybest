<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div>
	<div class="clearfix"></div>
	<div class="grid">
		<div class="grid-title">
			<div class="pull-left">
				<div class="icon-title"><i class="icon-picture"></i></div>
				<span>添加软文</span> 
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>   
		</div>
		<!-- novalidate -->
		<form id="main-content-form" action="${ContextPath}/advertorial/add.html" method="post" class="form-horizontal">
			<div class="grid-content">
				<div class="control-group">
					<label class="control-label">标题：</label>
					<div class="controls">
						<input type="text" class="span input" name="title" required="true" maxlength="64"> 
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">摘要：</label>
					<div class="controls">
						<textarea rows="3" name="summary" class="span input same-height-1" maxlength="256"></textarea>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">内容：</label>
					<div class="controls">
						<script type="text/plain" id="advertorial-content" name="content"></script>
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
UE.delEditor('advertorial-content');
var pdEditor = UE.getEditor('advertorial-content', {
	initialContent: '',
	initialFrameWidth: '100%'
});
$('input,textarea,select', '#main-content-form').jqBootstrapValidation({
	submitSuccess : cqlybest.ajaxSubmit
});
</script>