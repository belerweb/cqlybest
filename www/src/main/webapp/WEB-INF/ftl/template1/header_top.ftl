<#include "/template1/header_html.ftl">
<div id="top-nav" class="navbar navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<div class="pull-left">
				<a href="http://e.weibo.com/3039642623" target="_blank"><img alt="微博加关注" src="${ContextPath}/template1/img/weibo.png"> 加关注</a>
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
				<span>亲，欢迎来易游天下！请</span>
				<a href="${ContextPath}/login.html">登录</a>
				<a href="${ContextPath}/register.html" class="mg">免费注册</a>
				<a href="${ContextPath}/connector/qq_login" class="mg"><img alt="QQ登录" src="http://qzonestyle.gtimg.cn/qzone/vas/opensns/res/img/Connect_logo_7.png"></a>
				<a href="${ContextPath}/connector/weibo_login" class="mg"><img alt="用微博登录" src="http://timg.sjs.sinajs.cn/t4/appstyle/widget/images/loginButton/loginButton_24.png"></a>
				</#if>
			</div>
		</div>
	</div>
</div>