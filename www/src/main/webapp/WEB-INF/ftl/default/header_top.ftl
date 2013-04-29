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
						<a href="${ContextPath}/register.html" class="mg">免费注册</a>
						<a href="${ContextPath}/connector/qq_login.do" class="mg"><img alt="QQ登录" src="http://qzonestyle.gtimg.cn/qzone/vas/opensns/res/img/Connect_logo_7.png"></a>
						<a href="${ContextPath}/connector/weibo_login.do" class="mg"><img alt="用微博登录" src="http://timg.sjs.sinajs.cn/t4/appstyle/widget/images/loginButton/loginButton_24.png"></a>
					</div>
				</div>
			</div>
		</div>