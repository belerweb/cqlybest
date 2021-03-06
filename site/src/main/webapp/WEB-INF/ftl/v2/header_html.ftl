<!doctype html>
<html xmlns:wb="http://open.weibo.com/wb">
	<#import "/springx.ftl" as springx>
	<#assign ContextPath = springMacroRequestContext.getContextPath() />
	<#assign Authentication = securityContextHolder.getContext().getAuthentication() />
	<#if Authentication?exists && Authentication.getPrincipal()!="anonymousUser">
		<#assign User = Authentication.getPrincipal() />
	</#if>
	<head>
		<meta charset="utf-8">
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
		<title><#if Title?has_content>${Title}<#else>${(Settings.basic.siteName)!}</#if></title>
		<meta name="Keywords" content="<#if Keywords?has_content>${Keywords}<#else>${(Settings.basic.keywords)!?join(',')}</#if>"/>
		<meta name="Description" content="<#if Description?has_content>${Description}<#else>${(Settings.basic.description)!}</#if>"/>
		${(Options.site_meta)!}
		<#if (Options.release)?exists>
		<link rel="stylesheet" href="${ContextPath}/assets/v2/css/application.min.css?build=${(Settings.version.buildTime)!}">
		<#else>
		<link rel="stylesheet" href="${ContextPath}/assets/v2/css/bootstrap.css?build=${(Settings.version.buildTime)!}">
		<link rel="stylesheet" href="${ContextPath}/assets/v2/css/jquery.ad-gallery.css?build=${(Settings.version.buildTime)!}">
		<link rel="stylesheet" href="${ContextPath}/assets/v2/css/bootstrap-image-gallery.css?build=${(Settings.version.buildTime)!}">
		<link rel="stylesheet" href="${ContextPath}/assets/v2/css/bootstrap.calendar.css?build=${(Settings.version.buildTime)!}">
		<link rel="stylesheet" href="${ContextPath}/assets/v2/css/bootstrap-editable.css?build=${(Settings.version.buildTime)!}">
		<link rel="stylesheet" href="${ContextPath}/assets/v2/css/wysiwyg-color.css?build=${(Settings.version.buildTime)!}">
		<link rel="stylesheet" href="${ContextPath}/assets/v1/css/ace.css" />
		<link rel="stylesheet" href="${ContextPath}/assets/v1/css/ace-skins.css" />
		<link rel="stylesheet" href="${ContextPath}/assets/v2/css/application.css?build=${(Settings.version.buildTime)!}">
		</#if>
		<!--[if lt IE 7]><link rel="stylesheet" href="${ContextPath}/assets/v2/css/bootstrap-ie6.css?build=${(Options.build)!}"><![endif]-->
		<link rel="shortcut icon" href="${ContextPath}/v2/favicon.ico?build=${(Options.build)!}">
		<script src="http://tjs.sjs.sinajs.cn/open/api/js/wb.js" type="text/javascript" charset="utf-8"></script>
	</head>

	<body>