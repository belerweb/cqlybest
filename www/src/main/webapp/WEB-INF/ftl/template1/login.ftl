<#include "/template1/header_html.ftl">
<style>
body {
	padding:0;
}
</style>
<div id="register-or-login" class="container">
	<div class="pull-left"><img src="${ContextPath}/template1/img/logo.png"></div>
	<div class="clearfix"></div>
</div>
<div id="login-slider" style="background:#bFE5FA url('${ContextPath}/image/${Options['template1-login-poster']!}') center;">
	<div class="container">
		<div class="row">
			<div class="span8">
				<#if Options['template1-login-poster-link']?has_content>
				<a id="login-poster" href="${Options['template1-login-poster-link']}" target="_blank"></a>
				</#if>
			</div>
			<div class="span4">
				<div id="login-box" class="well well-small">
					<form action="${ContextPath}/user/login" method="post" novalidate="novalidate">
						<#if error?has_content && error>
						<div class="alert alert-error">用户信息不正确！</div>
						</#if>
						<div class="control-group">
							<label><strong>登录名：</strong></label>
							<input type="text" name="j_username" value="${username!}" placeholder="手机号/用户名/邮箱" autocomplete="off"
								required="true" data-validation-required-message="请填写登录名（手机号/用户名/邮箱）">
							<div class="help-block"></div>
						</div>
						<div class="control-group">
							<label><strong>登录密码：</strong></label>
							<input type="password" name="j_password" placeholder="登录密码" autocomplete="off"
								required="true" data-validation-required-message="请填写密码">
							<div class="help-block"></div>
						</div>
						<!-- label class="checkbox">
							<input type="checkbox"> 十天内免登录
						</label -->
						<button class="btn btn-success" type="submit" autocomplete="off"><strong>登 录</strong></button>
						<a href="${ContextPath}/register.html" class="btn pull-right" autocomplete="off"><strong>注 册</strong></a>
						<div class="clearfix"></div>
					</form>
					<hr>
					<a href="${ContextPath}/connector/qq_login"><img alt="QQ登录" src="${ContextPath}/template1/img/connector/Connect_logo_7.png" width="63" height="24"></a>
					<a href="${ContextPath}/connector/weibo_login" class="pull-right"><img alt="用微博登录" src="${ContextPath}/template1/img/connector/loginButton_24.png" width="102" height="24"></a>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	var PageContext = {
		init: function(){
			$('input', '#login-box form').jqBootstrapValidation({
				submitSuccess : function($form, event) {
					$.cookie('username', $('input[name=j_username]', $form).val(), { expires: 3650, path: '/' });
				}
			});
			var username = '${username!}';
			if (username.length) {
				$.cookie('username', username, { expires: 3650, path: '/' });
			}
			$('#login-poster').css('display', 'block').height($('#login-box').height());
		}
	};
</script>
<#include "/template1/footer.ftl">