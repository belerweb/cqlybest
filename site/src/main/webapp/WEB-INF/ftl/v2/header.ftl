<#include "/v2/header_top.ftl">
<div id="top-logo" class="container text-right">
	<div class="pull-left">
		<img src="${ContextPath}/assets/img/logo.png">
	</div>
	<#if (Settings.basic.hotline)?has_content>
	<div class="pull-right">
		<div class="phone"><span><img src="${ContextPath}/assets/v2/img/contact.png" width="24" height="24">${Settings.basic.hotline}</span></div>
		<div class="phone-tip"><i>7x24 小时，用心为您服务</i></div>
	</div>
	</#if>
</div>
<div id="menu" class="navbar">
	<div class="navbar-inner">
		<div class="container">
			<div class="nav-collapse collapse">
				<ul class="nav">
					<li data-active="index"><a href="${ContextPath}/index.html">首页</a></li>
					<li><a href="${ContextPath}/maldives.html">马尔代夫</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>