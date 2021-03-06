<!DOCTYPE html>
<html lang="zh">
	<#assign ContextPath=springMacroRequestContext.getContextPath() />
	<#assign User = securityContextHolder.getContext().getAuthentication().getPrincipal() />
	<head>
		<meta charset="utf-8">
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
		<title>${Options.site_name!}后台管理</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="">
		<link href="${ContextPath}/css/style.css" rel="stylesheet">
		<link href="${ContextPath}/css/bootstrap.css" rel="stylesheet">
		<link href="${ContextPath}/css/jquery-ui-1.8.16.custom.css" rel="stylesheet">
		<link rel="stylesheet" href="${ContextPath}/css/DT_bootstrap.css">
		<link rel="stylesheet" href="${ContextPath}/css/icon/font-awesome.css">
		<link rel="stylesheet" href="${ContextPath}/css/jquery.selectBoxIt.css">
		<link rel="stylesheet" href="${ContextPath}/css/bootstrap-tagmanager.css">
		<link rel="stylesheet" href="${ContextPath}js/select2/select2.css">
		<link rel="stylesheet" href="${ContextPath}/js/bootstrap-editable/css/bootstrap-editable.css">
		<link rel="stylesheet" href="${ContextPath}/js/inputs-ext/wysihtml5/bootstrap-wysihtml5/bootstrap-wysihtml5-0.0.2.css">  
		<link rel="stylesheet" href="${ContextPath}/css/wysiwyg-color.css">  
		<link rel="stylesheet" href="${ContextPath}/css/bootstrap.calendar.css">
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
			window.ImageServer = '${(Options.imageServer)!}';
		</script>
	</head>
	<body>
		<!--BEGIN HEADER-->
		<div id="header" role="banner">
			<a id="menu-link" class="head-button-link menu-hide" href="#menu"><span>菜单</span></a>
			<!--Logo-->
			<span class="logo"><h1>${Options.site_name!}后台管理</h1></span>
			<!--Logo END-->
			<div class="right">
				<!--profile box-->
				<div class="dropdown left profile">
					<a class="dropdown-toggle" data-toggle="dropdown" href="#">
						<#assign avatar='${ContextPath}/img/avatar.gif'>
						<#if User.avatar?has_content>
							<#if User.avatar?starts_with('http')>
								<#assign avatar=User.avatar>
							<#else>
								<#assign avatar='${ContextPath}/image/${User.avatar}'>
							</#if>
						</#if>
						<div class="profile-avatar">
							<img src="${avatar}" width="32" height="32">
						</div>
						<div class="profile-username"><span>你好，</span> ${User.nickname!'未设置昵称'}</div>
						<div class="profile-caret"> <span class="caret"></span></div>
						<span class="double-spacer"></span>
					</a>
					<div class="dropdown-menu pull-right profile-box">
						<div class="triangle-3"></div>
						<ul class="profile-navigation">
							<li><a href="${ContextPath}/logout"><i class="icon-off"></i> 退出</a></li>
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
					<li data-m="dash"><a href="#m=dash"><i class="dashboard"></i> 工作台</a></li>
					<li data-m="site"><a href="#m=site"><i class="site"></i> 网站</a></li>
					<li data-m="user"><a href="#m=user"><i class="user"></i> 用户</a></li>
					<li data-m="sms"><a href="#m=sms"><i class="sms"></i> 短信</a></li>
				</ul>
				<ul class="additional-menu">
					<li class="hide" data-m="dash" data-n="dash.dash"><a href="#m=dash&n=dash.dash&u=${ContextPath}/dashboard.do&t=%23main"><i class="icon-dashboard"></i> 工作台</a></li>
					<li class="hide" data-m="site" data-n="site.config"><a href="#m=site&n=site.config&u=${ContextPath}/site/config.do&t=%23main"><i class="icon-cogs"></i> 网站配置</a></li>
					<li class="hide" data-m="site" data-n="product.list"><a href="#m=site&n=product.list&u=${ContextPath}/product/list.do&t=%23main"><i class="icon-road"></i> 旅游产品</a></li>
					<li class="hide" data-m="site" data-n="maldives.list"><a href="#m=site&n=maldives.list&u=${ContextPath}/maldives/list.do&t=%23main"><i class="icon-road"></i> 马代海岛</a></li>
					<li class="hide" data-m="site" data-n="mauritius.list"><a href="#m=site&n=mauritius.list&u=${ContextPath}/mauritius/list.do&t=%23main"><i class="icon-road"></i> 毛求酒店</a></li>
					<li class="hide" data-m="site" data-n="productgroup.list"><a href="#m=site&n=productgroup.list&u=${ContextPath}/product_group/list.do&t=%23main"><i class="icon-th-large"></i> 产品聚合</a></li>
					<li class="hide" data-m="site" data-n="dict.list"><a href="#m=site&n=dict.list&u=${ContextPath}/dict/list.do?type%3Dtag&t=%23main"><i class="icon-book"></i> 数据字典</a></li>
					<li class="hide" data-m="site" data-n="link"><a href="#m=site&n=link&u=${ContextPath}/link/list.do&t=%23main"><i class="icon-book"></i> 友情链接</a></li>
					<li class="hide" data-m="site" data-n="account.list"><a href="#m=site&n=account.list&u=${ContextPath}/account/list.do&t=%23main"><i class="icon-github"></i> 账号管理</a></li>
					<li class="hide" data-m="site" data-n="advertorial.list"><a href="#m=site&n=advertorial.list&u=${ContextPath}/advertorial/list.do&t=%23main"><i class="icon-file"></i> 软文管理</a></li>
					<li class="hide" data-m="site" data-n="site.template1"><a href="#m=site&n=site.template1&u=${ContextPath}/template1/template.do&t=%23main"><i class="icon-edit"></i> 模板一</a></li>
					<li class="hide" data-m="user" data-n="user.list"><a href="#m=user&n=user.list&u=${ContextPath}/user/list.do&t=%23main"><i class="icon-user"></i> 帐号管理</a></li>
					<li class="hide" data-m="sms" data-n="sms.list"><a href="#m=sms&n=sms.list&u=${ContextPath}/sms/list.do&t=%23main"><i class="icon-comment"></i> 短信历史</a></li>
				</ul>
				<div class="clearfix"></div>
			</div>
			<!--SIDEBAR END-->
			<!--BEGIN MAIN CONTENT-->
			<div id="main" role="main">
				<div class="block"><div id="mb" style="overflow:visible;"></div></div>
			</div>
			<!--MAIN CONTENT END-->
		</div>
		<!--/#wrapper-->
		<script type="text/javascript" src="${ContextPath}/js/jquery.js"></script>
		<script type="text/javascript" src="${ContextPath}/js/bootstrap.js"></script>
		<script type="text/javascript" src="${ContextPath}/js/bootbox.js"></script>
		<script type="text/javascript" src="${ContextPath}/js/jqBootstrapValidation.js"></script>
		<script type="text/javascript" src="${ContextPath}/js/purl.js"></script>
		<script type="text/javascript" src="${ContextPath}/js/jquery-ui.min.js"></script>
		<script type="text/javascript" src="${ContextPath}/js/jquery.form.js"></script>
		<script type="text/javascript" src="${ContextPath}/js/jquery.ba-hashchange.min.js"></script>
		<script type="text/javascript" src="${ContextPath}/js/jquery.dataTables.js"></script>
		<script type="text/javascript" src="${ContextPath}/js/DT_bootstrap.js"></script>
		<script type="text/javascript" src="${ContextPath}/js/jquery.selectBoxIt.js"></script>
		<script type="text/javascript" src="${ContextPath}/js/bootstrap-tagmanager.js"></script>
		<script type="text/javascript" src="${ContextPath}/js/bootstrap-tagmanager-cqlybest.js"></script>
		<script type="text/javascript" src="${ContextPath}/js/ueditor/editor_config.js"></script>
		<script type="text/javascript" src="${ContextPath}/js/ueditor/editor_all.js"></script>
		<script type="text/javascript" src="${ContextPath}/js/moment.js"></script>
		<script type="text/javascript" src="${ContextPath}/js/select2/select2.js"></script>
		<script type="text/javascript" src="${ContextPath}/js/bootstrap-editable/js/bootstrap-editable.js"></script>
		<script type="text/javascript" src="${ContextPath}/js/inputs-ext/wysihtml5/bootstrap-wysihtml5/wysihtml5-0.3.0.js"></script>  
		<script type="text/javascript" src="${ContextPath}/js/inputs-ext/wysihtml5/bootstrap-wysihtml5/bootstrap-wysihtml5-0.0.2.js"></script>  
		<script type="text/javascript" src="${ContextPath}/js/inputs-ext/wysihtml5/wysihtml5.js"></script>  
		<script type="text/javascript" src="${ContextPath}/js/bootstrap.calendar.js"></script>
		<script type="text/javascript" src="${ContextPath}/js/jquery.dateFormat-1.0.js"></script>
		<script type="text/javascript" src="${ContextPath}/js/application.js"></script>
	</body>
</html>
