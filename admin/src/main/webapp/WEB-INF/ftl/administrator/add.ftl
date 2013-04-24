<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div>
	<div class="clearfix"></div>
	<div class="grid">
		<div class="grid-title">
			<div class="pull-left">
				<div class="icon-title"><i class="icon-plus"></i></div>
				<span>增加管理员</span> 
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>   
		</div>
		<!-- novalidate -->
		<form id="main-content-form" action="${ContextPath}/administrator/add.html" method="post" class="form-horizontal">
			<div class="grid-content">
				<div class="control-group">
					<label class="control-label">姓名：</label>
					<div class="controls">
						<input type="text" class="span input" name="fullname" required="true" maxlength="16"> 
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">昵称：</label>
					<div class="controls">
						<input type="text" class="span input" name="nickname" maxlength="16"> 
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">手机号：</label>
					<div class="controls">
						<input type="text" class="span input" name="cellPhone" required="true" pattern="\d{11}" data-validation-pattern-message="手机号不正确"> 
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">电子邮件：</label>
					<div class="controls">
						<input type="email" class="span input" name="email"> 
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">用户名：</label>
					<div class="controls">
						<input type="text" class="span input" name="loginUsername" pattern="[a-zA-Z][a-zA-Z0-9_]{3}"> 
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">密码：</label>
					<div class="controls">
						<input type="password" class="span input" name="password" required="true" minlength="6" maxlength="16"> 
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">确认密码：</label>
					<div class="controls">
						<input type="password" class="span input" name="repassword" data-validation-match-match="password" data-validation-match-message="确认密码与密码不一样"> 
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
	submitSuccess : function($form, event) {
		event.preventDefault();
		event.stopPropagation();
		$form.ajaxSubmit({
			success : function(response){
				alert('保存成功');
				history.go(-1);
			},
			error : function() {
				alert('保存失败');
			}
		});
	}
});
</script>