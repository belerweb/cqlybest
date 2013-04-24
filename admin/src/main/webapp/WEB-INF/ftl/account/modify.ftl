<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div>
	<div class="clearfix"></div>
	<div class="grid">
		<div class="grid-title">
			<div class="pull-left">
				<div class="icon-title"><i class="icon-picture"></i></div>
				<span>修改账号: ${(account.name)!}</span> 
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>   
		</div>
		<!-- novalidate -->
		<form id="main-content-form" action="${ContextPath}/account/modify.html" method="post" class="form-horizontal">
			<input type="hidden" name="id" value="${(account.id)!}">
			<div class="grid-content">
				<div class="control-group">
					<label class="control-label">名称：</label>
					<div class="controls">
						<input type="text" class="span input" name="name" value="${(account.name)!}" required="true" maxlength="32"> 
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">网址：</label>
					<div class="controls">
						<input type="text" class="span input" name="url" value="${(account.url)!}" required="true" maxlength="128"> 
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">账号：</label>
					<div class="controls">
						<input type="text" class="span input" name="account" value="${(account.account)!}" required="true" maxlength="32"> 
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">密码：</label>
					<div class="controls">
						<input type="text" class="span input" name="password" value="${(account.password)!}" required="true" maxlength="16"> 
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">备注：</label>
					<div class="controls">
						<textarea rows="3" name="remark" class="span input same-height-1" maxlength="256">${(account.remark)!}</textarea>
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