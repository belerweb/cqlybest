<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div>
	<div class="clearfix"></div>
	<div class="grid">
		<div class="grid-title">
			<div class="pull-left">
				<div class="icon-title"><i class="icon-plus"></i></div>
				<span>添加账号</span> 
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>   
		</div>
		<!-- novalidate -->
		<form id="main-content-form" action="${ContextPath}/account/add.do" method="post" class="form-horizontal">
			<div class="grid-content">
				<div class="control-group">
					<label class="control-label">名称：</label>
					<div class="controls">
						<input type="text" class="span input" name="name" required="true" maxlength="32"> 
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">网址：</label>
					<div class="controls">
						<input type="text" class="span input" name="url" required="true" maxlength="128"> 
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">账号：</label>
					<div class="controls">
						<input type="text" class="span input" name="account" required="true" maxlength="32"> 
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">密码：</label>
					<div class="controls">
						<input type="text" class="span input" name="password" required="true" maxlength="32"> 
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">备注：</label>
					<div class="controls">
						<textarea rows="3" name="remark" class="span input same-height-1" maxlength="256"></textarea>
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