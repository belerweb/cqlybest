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
		<link href="${ContextPath}/css/jquery-ui-1.8.16.custom.css" rel="stylesheet">
		<link rel="stylesheet" href="${ContextPath}/css/DT_bootstrap.css">
		<link rel="stylesheet" href="${ContextPath}/css/icon/font-awesome.css">
		<link rel="stylesheet" href="${ContextPath}/css/bootstrap-responsive.css">
		<link rel="stylesheet" href="${ContextPath}/css/jquery.selectBoxIt.css">
		<link rel="stylesheet" href="${ContextPath}/css/bootstrap-tagmanager.css">
		<link rel="stylesheet" href="${ContextPath}/css/jquery.tagit.css">
		<link rel="stylesheet" href="${ContextPath}/css/elfinder.min.css">
		<link rel="stylesheet" href="${ContextPath}/css/elfinder-theme.css">
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
			window.UEDITOR_HOME_URL = '${ContextPath}/js/ueditor/';
			window.ContextPath = '${ContextPath}';
			window.ImageServer = '${(Site.imageServer)!}';
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
						<div class="profile-avatar"><img src="${(user.avatar)!}" alt=""></div>
						<div class="profile-username"><span>你好，</span> ${(user.nickname)!'朋友'}！</div>
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
					<li data-m="dash"><a href="#m=dash"><i class="tables"></i> 工作台</a></li>
					<li data-m="site"><a href="#m=site"><i class="tables"></i> 网站</a></li>
					<li data-m="user"><a href="#m=user"><i class="tables"></i> 用户</a></li>
					<li data-m="user"><a href="#m=sms"><i class="tables"></i> 短信</a></li>
				</ul>
				<ul class="additional-menu">
					<li class="hide" data-m="dash" data-n="dash.dash"><a href="#m=dash;n=dash.dash;u=${ContextPath}/dashboard.html;t=#main"><i class="icon-dashboard"></i> 工作台</a></li>
					<li class="hide" data-m="site" data-n="site.config"><a href="#m=site;n=site.config;u=${ContextPath}/site/config.html;t=#main"><i class="icon-cogs"></i> 网站配置</a></li>
					<li class="hide" data-m="site" data-n="product.list"><a href="#m=site;n=product.list;u=${ContextPath}/product/list.html;t=#main"><i class="icon-road"></i> 旅游产品</a></li>
					<li class="hide" data-m="site" data-n="productgroup.list"><a href="#m=site;n=productgroup.list;u=${ContextPath}/product_group/list.html;t=#main"><i class="icon-th-large"></i> 产品聚合</a></li>
					<li class="hide" data-m="site" data-n="menu.list"><a href="#m=site;n=menu.list;u=${ContextPath}/menu/list.html;t=#main"><i class="icon-reorder"></i> 菜单管理</a></li>
					<li class="hide" data-m="site" data-n="poster.list"><a href="#m=site;n=poster.list;u=${ContextPath}/poster/list.html;t=#main"><i class="icon-picture"></i> 首页海报</a></li>
					<li class="hide" data-m="site" data-n="data.dict"><a href="#m=site;n=data.dict;u=${ContextPath}/data/dict.html;t=#main"><i class="icon-book"></i> 数据字典</a></li>
					<li class="hide" data-m="site" data-n="account.list"><a href="#m=site;n=account.list;u=${ContextPath}/account/list.html;t=#main"><i class="icon-github"></i> 账号管理</a></li>
					<li class="hide" data-m="site" data-n="advertorial.list"><a href="#m=site;n=advertorial.list;u=${ContextPath}/advertorial/list.html;t=#main"><i class="icon-file"></i> 软文管理</a></li>
					<li class="hide" data-m="site" data-n="file"><a href="#m=site;n=file;u=${ContextPath}/file.html;t=#main"><i class="icon-folder-close"></i> 文件管理</a></li>
					<li class="hide" data-m="user" data-n="user.customers"><a href="#m=user;n=user.customers;u=${ContextPath}/customer/list.html;t=#main"><i class="icon-user"></i> 客户</a></li>
					<li class="hide" data-m="user" data-n="user.administrators"><a href="#m=user;n=user.administrators;u=${ContextPath}/administrator/list.html;t=#main"><i class="icon-user-md"></i> 管理员</a></li>
					<li class="hide" data-m="sms" data-n="sms.list"><a href="#m=sms;n=sms.list;u=${ContextPath}/sms/list.html;t=#main"><i class="icon-comment"></i> 短信历史</a></li>
				</ul>
				<div class="clearfix"></div>
			</div>
			<!--SIDEBAR END-->
			<!--BEGIN MAIN CONTENT-->
			<div id="main" role="main">
				<div class="block"><div id="mb"></div></div>
			</div>
			<!--MAIN CONTENT END-->
		</div>
		<!--/#wrapper-->
		<script src="${ContextPath}/js/jquery.js"></script>
		<script src="${ContextPath}/js/bootstrap.js"></script>
		<script src="${ContextPath}/js/bootbox.js"></script>
		<script src="${ContextPath}/js/jqBootstrapValidation.js"></script>
		<script src="${ContextPath}/js/jquery-ui.min.js"></script>
		<script src="${ContextPath}/js/jquery.form.js"></script>
		<script src="${ContextPath}/js/jquery.ba-hashchange.min.js"></script>
		<script src="${ContextPath}/js/jquery.dataTables.js"></script>
		<script src="${ContextPath}/js/DT_bootstrap.js"></script>
		<script src="${ContextPath}/js/jquery.selectBoxIt.js"></script>
		<script src="${ContextPath}/js/bootstrap-tagmanager.js"></script>
		<script src="${ContextPath}/js/bootstrap-tagmanager-cqlybest.js"></script>
		<script src="${ContextPath}/js/tag-it.js"></script>
		<script src="${ContextPath}/js/ueditor/editor_config.js"></script>
		<script src="${ContextPath}/js/ueditor/editor_all.js"></script>
		<script src="${ContextPath}/js/elfinder.min.js"></script>
		<script src="${ContextPath}/js/i18n/elfinder.zh_CN.js"></script>
		<!--[if IE]>
		<script src="${ContextPath}/js/Jit/Extras/excanvas.js"></script>
		<![endif]-->
		<script language="javascript" type="text/javascript" src="${ContextPath}/js/Jit/jit.js"></script>
		<script src="${ContextPath}/js/application.js"></script>
	</body>
</html>
