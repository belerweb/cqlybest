<#assign ContextPath = springMacroRequestContext.getContextPath() />
<#assign Url = ContextPath + '/user/update' />
<div class="row-fluid form-horizontal">
	<div class="span12">
		<div class="control-group">
			<label class="control-label">用户名：</label>
			<div class="controls">
				<#if user.loginUsername?has_content>
				<label class="control-label">${user.loginUsername}</label>
				<#else>
				<a href="#" data-type="text" data-pk="${user.id}" data-name="loginUsername" data-value="" data-url="${Url}"></a>
				<div class="help-block">
				设置用户名后不可更改。用户名以字母开头，只能包含字母和数字，长度4～16。
				</div>
				</#if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手机号：</label>
			<div class="controls">
				<a href="#" data-type="text" data-pk="${user.id}" data-name="cellPhone" data-value="${user.cellPhone!}" data-url="${Url}" class="editable"></a>
				<div class="help-block">
				设置或更改手机号都将收到的验证短信。
				</div>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">电子邮件：</label>
			<div class="controls">
				<a href="#" data-type="text" data-pk="${user.id}" data-name="email" data-value="${user.email!}" data-url="${Url}" class="editable"></a>
				<div class="help-block">
				设置或更改电子邮件地址都将收到验证邮件。
				</div>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">昵称：</label>
			<div class="controls">
				<a href="#" data-type="text" data-pk="${user.id}" data-name="nickname" data-value="${user.nickname!}" data-url="${Url}" class="editable"></a>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">姓名：</label>
			<div class="controls">
				<a href="#" data-type="text" data-pk="${user.id}" data-name="fullname" data-value="${user.fullname!}" data-url="${Url}" class="editable"></a>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$('.ajax-content a[data-name=loginUsername]').on('save',function(e,p){$(this).parent().html('<label class="control-label">'+p.newValue+'</label>');}).editable();
$('.ajax-content a.editable').editable();
</script>
