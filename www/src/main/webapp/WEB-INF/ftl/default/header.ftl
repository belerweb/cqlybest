<!doctype html>
<html>
	<#assign ContextPath = springMacroRequestContext.getContextPath() />
	<#assign Authentication = securityContextHolder.getContext().getAuthentication() />
	<head>
		<meta charset="utf-8">
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
		<title>重庆易游天下</title>
		<link rel="stylesheet" href="${ContextPath}/default/css/bootstrap.min.css">
		<link rel="stylesheet" href="${ContextPath}/default/css/cqlybest.css">
	</head>

	<body>
		<div id="top-nav" class="navbar navbar-fixed-top">
			<div class="navbar-inner">
				<div class="container">
					<div class="pull-left">
						<a href="http://e.weibo.com/3039642623" target="_blank"><img alt="微博加关注" src="${ContextPath}/default/img/weibo.png"> 加关注</a>
					</div>
					<div class="pull-right">
						<span>亲，欢迎来易游天下！请</span>
						<a href="" target="_blank">登陆</a>
						<a href="" target="_blank" class="mg">免费注册</a>
						<a href="${ContextPath}/connector/qq_login.do" class="mg"><img alt="QQ登录" src="http://qzonestyle.gtimg.cn/qzone/vas/opensns/res/img/Connect_logo_7.png"></a>
						<a href="${ContextPath}/connector/weibo_login.do" class="mg"><img alt="用微博登录" src="http://timg.sjs.sinajs.cn/t4/appstyle/widget/images/loginButton/loginButton_24.png"></a>
					</div>
				</div>
			</div>
		</div>
		<div id="top-logo" class="container text-right">
			<div class="pull-left"><img src="${ContextPath}/default/img/logo.png"></div>
			<div class="pull-right">
				<div class="phone"><span><img src="${ContextPath}/default/img/contact.png">400-023-9761</span></div>
				<div class="phone-tip"><i>7x24 小时，用心为您服务</i></div>
			</div>
		</div>
		<div id="menu" class="navbar">
			<div class="navbar-inner">
				<div class="container">
					<a class="brand">全部旅游产品</a>
					<div class="nav-collapse collapse">
						<ul class="nav">
							<li class="active"><a href="${ContextPath}/index.html">首页</a></li>
							<#list Menu as menu>
							<#if menu.menuType==0>
							<li><a <#if menu.newWindow>target="_blank"</#if> href="${ContextPath}/pg/${menu.id}.html">${menu.name}</a></li>
							<#elseif menu.menuType==1>
							<li><a <#if menu.newWindow>target="_blank"</#if> href="${ContextPath}/page/${menu.id}.html">${menu.name}</a></li>
							<#elseif menu.menuType==2>
							<li><a <#if menu.newWindow>target="_blank"</#if> href="${menu.url}">${menu.name}</a></li>
							</#if>
							</#list>
						</ul>
					</div>
				</div>
			</div>
		</div>