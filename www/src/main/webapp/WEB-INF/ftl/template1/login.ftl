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
<div id="login-slider" style="background:#bFE5FA;">
	<div class="container">
		<div class="row">
			<div class="span8"></div>
			<div class="span4">
				<div id="login-box" class="well well-small">
					<form action="${ContextPath}/login.do" method="post" novalidate="novalidate">
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
					<a href="${ContextPath}/connector/qq_login.do"><img alt="QQ登录" src="http://qzonestyle.gtimg.cn/qzone/vas/opensns/res/img/Connect_logo_7.png"></a>
					<a href="${ContextPath}/connector/weibo_login.do" class="pull-right"><img alt="用微博登录" src="http://timg.sjs.sinajs.cn/t4/appstyle/widget/images/loginButton/loginButton_24.png"></a>
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
					$.cookie('username', $('input[name=j_username]', $form).value(), { expires: 3650, path: '/' });
				}
			});
			var username = '${username!}';
			if (username.length) {
				$.cookie('username', username, { expires: 3650, path: '/' });
			}
		}
	};
</script>
<#include "/template1/footer.ftl">