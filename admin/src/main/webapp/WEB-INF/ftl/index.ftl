<!DOCTYPE html>
<html lang="zh">
	<#assign ContextPath=springMacroRequestContext.getContextPath() />
	<head>
		<meta charset="utf-8">
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
		<title>重庆易游天下国际旅行社</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="">
		<link href="${ContextPath}/css/style.css" rel="stylesheet">
		<link href="${ContextPath}/css/bootstrap.css" rel="stylesheet">
		<link rel="stylesheet" href="${ContextPath}/css/datatable.css">    
		<link rel="stylesheet" href="${ContextPath}/css/icon/font-awesome.css">    
		<link rel="stylesheet" href="${ContextPath}/css/bootstrap-responsive.css">
		<link rel="alternate stylesheet" type="text/css" media="screen" title="green-theme" href="${ContextPath}/css/color/green.css" />
		<link rel="alternate stylesheet" type="text/css" media="screen" title="red-theme" href="${ContextPath}/css/color/red.css" />
		<link rel="alternate stylesheet" type="text/css" media="screen" title="blue-theme" href="${ContextPath}/css/color/blue.css" />
		<link rel="alternate stylesheet" type="text/css" media="screen" title="orange-theme" href="${ContextPath}/css/color/orange.css" />
		<link rel="alternate stylesheet" type="text/css" media="screen" title="purple-theme" href="${ContextPath}/css/color/purple.css" />
		<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
		<!--[if lt IE 9]>
			<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->
		<script language="JavaScript">
			if(navigator.userAgent.indexOf("Firefox") >= 0) document.write("<link rel='stylesheet' href='${ContextPath}/css/moz.css' type='text/css'>");
		</script>
	</head>
	<body>
		<!--BEGIN HEADER-->
		<div id="header" role="banner">
			<a id="menu-link" class="head-button-link menu-hide" href="#menu"><span>菜单</span></a>
			<!--Logo-->
			<a href="dashboard.html" class="logo"><h1>重庆易游天下国际旅行社</h1></a>
			<!--Logo END-->
			<div class="right">
				<!--profile box-->
				<div class="dropdown left profile">
					<a class="dropdown-toggle" data-toggle="dropdown" href="#">
						<!-- span class="double-spacer"></span -->
						<div class="profile-avatar"><img src="${(userInfo.avatar.avatarURL50)!}" alt=""></div>
						<div class="profile-username"><span>你好，</span> ${(userInfo.nickname)!}！</div>
						<div class="profile-caret"> <span class="caret"></span></div>
						<span class="double-spacer"></span>
					</a>
					<div class="dropdown-menu pull-right profile-box">
						<div class="triangle-3"></div>
						<ul class="profile-navigation">
							<li><a href="${ContextPath}/logout.do"><i class="icon-off"></i> 退出</a></li>
						</ul>
					</div>
				</div>
				<div class="clearfix"></div>
				<!--profile box end-->
			</div>
		</div>
		<!--END HEADER-->
		<div id="wrap">
			<!--BEGIN SIDEBAR-->
			<div id="menu" role="navigation">
				<ul class="main-menu">
					<li data-m="site"><a href="#m=site"><i class="tables"></i> 网站</a></li>
					<li data-m="user"><a href="#m=user"><i class="tables"></i> 用户</a></li>
				</ul>
				<ul class="additional-menu">
					<li class="hide" data-m="site" data-n="site.config"><a href="#m=site;n=site.config;u=${ContextPath}/site/config.html;t=#main"><i class="icon-cogs"></i> 网站配置</a></li>
					<li class="hide" data-m="site" data-n="product.list"><a href="#m=site;n=product.list;u=${ContextPath}/product/list.html;t=#main"><i class="icon-road"></i> 旅游产品</a></li>
					<li class="hide" data-m="user" data-n="user.customers"><a href="#m=user;n=user.customers;u=${ContextPath}/site/customers.html;t=#main"><i class="icon-user"></i> 客户</a></li>
					<li class="hide" data-m="user" data-n="user.administrators"><a href="#m=user;n=user.administrators;u=${ContextPath}/site/administrators.html;t=#main"><i class="icon-user-md"></i> 管理员</a></li>
				</ul>
				<div class="clearfix"></div>
			</div>
			<!--SIDEBAR END-->
			<!--BEGIN MAIN CONTENT-->
			<div id="main" role="main">
			</div>
			<!--MAIN CONTENT END-->
		</div>
		<!--/#wrapper-->
		<script src="${ContextPath}/js/jquery.min.js"></script>
		<script src="${ContextPath}/js/bootstrap.min.js"></script>
		<script src="${ContextPath}/js/jqBootstrapValidation.js"></script>
		<script src="${ContextPath}/js/jquery.form.js"></script>
		<script src="${ContextPath}/js/jquery.ba-hashchange.min.js"></script>
		<script src="${ContextPath}/js/jquery.dataTables.min.js"></script>
		<script src="${ContextPath}/js/application.js"></script>
	</body>
</html>
