<#include "index/header.ftl">

<div class="coming-soon-border"></div>
<div class="row-fluid">
	<div class="span8 offset2 coming-soon">
		<h1>即将上线，敬请期待！</h1>
		<p>我们专注于休闲度假式旅游产品的开发，可为国人提供多种优质的出境旅游和国内旅游服务，并以深度主题游、个性化自由行、单团订制为核心产品，在欧洲游和海岛游等海外旅游市场占有资源优势并始终处于领先地位。</p>
		<form method="post" action="${ContextPath}/soon.html" class="form-search">
			<input name="id" type="text" class="input-large search-query" placeholder="您的邮件地址或手机号">
			<button type="submit" class="btn-u">更新时通知我</button>
		</form>
	</div>
</div>
<div class="row-fluid">
	<ul class="thumbnails">
		<li class="span3 offset3">
			<div class="thumbnail-style border">
				<div class="thumbnail-img">
					<img src="${ContextPath}/assets/img/weibo.png">
				</div>
				<h3 style="color:#FFF;">扫一扫，关注微博</h3>
			</div>
		</li>
		<li class="span3">
			<div class="thumbnail-style border">
				<div class="thumbnail-img">
					<img src="${ContextPath}/assets/img/weixin.png">
				</div>
				<h3 style="color:#FFF;">扫一扫，关注微信</h3>
			</div>
		</li>
	</ul>
</div>
<div class="hide">${(Settings.basic.statistical)!}</div>
<script type="text/javascript">
	window.PageContext = {
		init: function(){
			$('body').removeClass('fixed-bottom').addClass('coming-soon-page');
			bootbox.setLocale('zh_CN');
			$('form').submit(function() {
				$(this).ajaxSubmit({
					success: function(response, status, xhr, form) {
						bootbox.alert('<div class="alert alert-success">感谢您的关注，我们将第一时间通知您我们的更新。</div>', function(){
						});
					},
					error: function(xhr, status, response, form) {
						bootbox.alert('<div class="alert alert-error">' + xhr.responseText + '</div>', function(){
						});
					}
				});
				return false;
			});
		}
	};
</script>

<#include "index/footer.ftl">