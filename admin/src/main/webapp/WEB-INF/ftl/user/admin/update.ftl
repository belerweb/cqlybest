<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div>
	<div class="clearfix"></div>
	<div class="grid">
		<div class="grid-title">
			<div class="pull-left">
				<div class="icon-title"><i class="icon-edit"></i></div>
				<span>修改管理员</span>
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>
		</div>
		<#assign id="${user.id}">
		<#assign url="${ContextPath}/user/admin/update.do" />
		<form class="form-horizontal">
			<div class="grid-content">
				<div class="control-group">
					<label class="control-label">姓名：</label>
					<div class="controls">
						<a id="user_fullname" href="#" class="editable" data-pk="${id}" data-name="fullname" data-type="text" data-url="${url}" data-value="${(user.fullname!)?html}"></a>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">昵称：</label>
					<div class="controls">
						<a id="user_nickname" href="#" class="editable" data-pk="${id}" data-name="nickname" data-type="text" data-url="${url}" data-value="${(user.nickname!)?html}"></a>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">手机号：</label>
					<div class="controls">
						<a id="user_cell_phone" href="#" class="editable" data-pk="${id}" data-name="cellPhone" data-type="text" data-url="${url}" data-value="${(user.cellPhone!)?html}"></a>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">电子邮件：</label>
					<div class="controls">
						<a id="user_email" href="#" class="editable" data-pk="${id}" data-name="email" data-type="text" data-url="${url}" data-value="${(user.email!)?html}"></a>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">用户名：</label>
					<div class="controls">
						<a id="user_login_username" href="#" class="editable" data-pk="${id}" data-name="username" data-type="text" data-url="${url}" data-value="${(user.loginUsername!)?html}"></a>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">密码：</label>
					<div class="controls">
						<a id="user_password" href="#" class="editable" data-pk="${id}" data-name="password" data-type="password" data-url="${url}" data-value="${(user.password!)?html}"></a>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
		</form>
	</div>
</div>
<script>
$('#user_fullname').editable({
});
$('#user_nickname').editable({
});
$('#user_cell_phone').editable({
});
$('#user_email').editable({
});
$('#user_login_username').editable({
});
$('#user_password').editable({
});
</script>