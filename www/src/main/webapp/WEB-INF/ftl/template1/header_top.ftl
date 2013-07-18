<#include "/template1/header_html.ftl">
<div id="top-nav" class="navbar navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<div class="pull-left">
				<#if Options.weibo_url?has_content>
				<a href="${Options.weibo_url}" target="_blank"><img alt="微博加关注" src="${ContextPath}/template1/img/weibo.png" width="24" height="24"> 加关注</a>
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
				<img src="${ContextPath}/template1/img/avatar.gif" class="avatar">
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
				<span>亲，欢迎访问${(Options.site_name)!}！请</span>
				<a href="${ContextPath}/login.html">登录</a>
				<a href="${ContextPath}/register.html" class="mg">免费注册</a>
				<a href="${ContextPath}/connector/qq_login" class="mg"><img alt="QQ登录" src="${ContextPath}/template1/img/connector/Connect_logo_7.png" width="63" height="24"></a>
				<a href="${ContextPath}/connector/weibo_login" class="mg"><img alt="用微博登录" src="${ContextPath}/template1/img/connector/loginButton_24.png" width="102" height="24"></a>
				</#if>
			</div>
		</div>
	</div>
</div>