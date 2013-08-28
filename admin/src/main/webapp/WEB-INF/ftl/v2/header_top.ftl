<#include "/v2/header_html.ftl">
<div id="top-nav" class="navbar">
	<div class="navbar-inner">
		<div class="container">
			<div class="pull-left" style="height:24px;">
				<#if (Settings.basic.weibo.id)?has_content>
				<wb:follow-button uid="${Settings.basic.weibo.id}" type="red_1" width="67" height="24" ></wb:follow-button>
				</#if>
			</div>
			<div class="pull-right dropdown">
				<#if User?exists>
				<#if User.avatar?has_content>
				<#if User.avatar?starts_with('http')>
				<img src="${User.avatar}" class="avatar">
				<#else>
				<img src="${ContextPath}/image/${User.avatar}?width=32&height=32" class="avatar">
				</#if>
				<#else>
				<img src="${ContextPath}/assets/v2/img/avatar.gif" class="avatar">
				</#if>
				<a class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown"
					href="<#if User.nickname?has_content>#<#else>${ContextPath}/user/home</#if>">
					<#if User.nickname?has_content>${User.nickname}<#else>完善个人信息</#if> <b class="caret"></b>
				</a>
				<ul class="dropdown-menu">
					<li><a href="${ContextPath}/user/home">用户中心</a></li>
					<li class="divider"></li>
					<li><a href="${ContextPath}/user/logout">退出</a></li>
				</ul>
				<#else>
				<span>亲，欢迎访问${(Options.site_name)!}！</span>
				<!-- a href="${ContextPath}/login.html">登录</a -->
				<!-- a href="${ContextPath}/register.html" class="mg">免费注册</a -->
				<a href="${ContextPath}/connector/qq_login" class="mg"><img alt="QQ登录" src="${ContextPath}/assets/v2/img/connector/Connect_logo_7.png" width="63" height="24"></a>
				<a href="${ContextPath}/connector/weibo_login" class="mg" title="使用新浪微博登录并关注我们的官方微博"><img alt="用微博登录" src="${ContextPath}/assets/v2/img/connector/loginButton_24.png" width="102" height="24"></a>
				</#if>
			</div>
		</div>
	</div>
</div>