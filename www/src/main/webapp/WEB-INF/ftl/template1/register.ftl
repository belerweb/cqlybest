<#include "/template1/header_top.ftl">
<div id="register-or-login" class="container">
	<div class="pull-left"><img src="${ContextPath}/template1/img/logo.png"></div>
	<h2 class="pull-left">账户注册</h2>
	<div class="clearfix"></div>
	<hr>
	<div class="row">
		<div class="span8">
			<form id="register-form" action="${ContextPath}/register.do" method="post" class="form-horizontal" novalidate="novalidate">
				<div class="control-group">
					<label class="control-label">手机号：</label>
					<div class="controls">
						<input type="text" id="cellPhone" name="cellPhone" placeholder="手机号" autocomplete="off"
							required="true" data-validation-required-message="请填写手机号"
							data-validation-regex-regex="1[3458]\d{9}" data-validation-regex-message="请填写正确的手机号">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">登录密码：</label>
					<div class="controls">
						<input type="password" name="password" placeholder="登录密码" autocomplete="off"
							require="true" data-validation-required-message="请填写密码"
							minlength="6" data-validation-minlength-message="请填写6位以上密码"
							maxlength="16" data-validation-maxlength-message="请填写16位以下密码">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">确认密码：</label>
					<div class="controls">
						<input type="password" placeholder="与登录密码一致" autocomplete="off"
							data-validation-match-match="password" data-validation-match-message="确认密码与登录密码不一致">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">手机验证码：</label>
					<div class="controls">
						<div class="input-append">
							<input type="text" name="validationCode" class="input-mini" autocomplete="off"
								required="true" data-validation-required-message="请填写密码验证码"
								data-validation-regex-regex="\d{4}" data-validation-regex-message="验证码是4位数字">
							<button id="get-validation-code" class="btn" type="button" autocomplete="off" data-loading-text="发送中...">点击获取验证码</button>
						</div>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<button type="submit" class="btn btn-warning" autocomplete="off" data-loading-text="注册中..."><strong>注册</strong></button>
					</div>
				</div>
			</form>
		</div>
		<div class="span4">
			<h2>合作账户免注册， 直接登录</h2>
			<hr>
			<ul>
				<li><a href="${ContextPath}/connector/qq_login.do" class="mg"><img alt="QQ登录" src="http://qzonestyle.gtimg.cn/qzone/vas/opensns/res/img/Connect_logo_7.png"></a></li>
				<li><a href="${ContextPath}/connector/weibo_login.do" class="mg"><img alt="用微博登录" src="http://timg.sjs.sinajs.cn/t4/appstyle/widget/images/loginButton/loginButton_24.png"></a></li>
			</ul>
		</div>
	</div>
</div>
<script>
	var PageContext = {
		init: function(){
			$('input', '#register-form').jqBootstrapValidation({
				submitSuccess : function($form, event) {
					event.preventDefault();
					event.stopPropagation();
					var btn = $('button[type=submit]', $form);
					btn.button('loading');
					$form.ajaxSubmit({
						success: function(response){
							if (response && response.length) {
								alert(response);
								btn.button('reset');
							} else {
								alert("注册成功，请登录。");
								location.href = '${ContextPath}/login.html';
							}
						},
						error: function() {
							btn.button('reset');
						}
					});
				}
			});
			$('#get-validation-code').click(function(){
				var btn = $(this);
				var resendTime = 60;
				var cellPhone = $('#cellPhone').val();
				if (!/^1[3458]\d{9}$/.test(cellPhone)) {
					alert('请输入正确的手机号');
					return;
				}
				btn.button('loading');
				$.post('${ContextPath}/register_phone_validation.do', {
					cellPhone: cellPhone
				}).success(function(response){
					if (response) {
						alert(response);
					} else {
						var interval = setInterval(function(){
							btn.text('验证码已发送，如果没收到，' + resendTime-- + '秒后可重新获取...');
						}, 1000);
						setTimeout(function(){
							clearInterval(interval);
							btn.button('reset');
						}, 60000);
					}
				}).fail(function(){
					btn.button('reset');
				});
			});
		}
	};
</script>
<#include "/template1/footer.ftl">