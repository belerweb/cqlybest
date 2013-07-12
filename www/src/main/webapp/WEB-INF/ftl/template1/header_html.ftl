<!doctype html>
<html>
	<#import "/springx.ftl" as springx>
	<#assign ContextPath = springMacroRequestContext.getContextPath() />
	<#assign Authentication = securityContextHolder.getContext().getAuthentication() />
	<#if Authentication?exists && Authentication.getPrincipal()!="anonymousUser">
		<#assign User = Authentication.getPrincipal() />
	</#if>
	<head>
		<meta charset="utf-8">
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
		<title><#if Title?has_content>${Title}<#else>${(Options.site_name)!}</#if></title>
		<meta name="Keywords" content="<#if Keywords?has_content>${Keywords}<#else>${(Options.site_meta_keyword)!}</#if>"/>
		<meta name="Description" content="<#if Description?has_content>${Description}<#else>${(Options.site_meta_description)!}</#if>"/>
		${(Options.site_meta)!}
		<#if (Options.release)?exists>
		<link rel="stylesheet" href="${ContextPath}/template1/css/application.min.css?build=${(Options.build)!}">
		<#else>
		<link rel="stylesheet" href="${ContextPath}/template1/css/bootstrap.css?build=${(Options.build)!}">
		<link rel="stylesheet" href="${ContextPath}/template1/css/jquery.ad-gallery.css?build=${(Options.build)!}">
		<link rel="stylesheet" href="${ContextPath}/template1/css/bootstrap-image-gallery.css?build=${(Options.build)!}">
		<link rel="stylesheet" href="${ContextPath}/template1/css/bootstrap.calendar.css?build=${(Options.build)!}">
		<link rel="stylesheet" href="${ContextPath}/template1/css/bootstrap-editable.css?build=${(Options.build)!}">
		<link rel="stylesheet" href="${ContextPath}/template1/css/wysiwyg-color.css?build=${(Options.build)!}">
		<link rel="stylesheet" href="${ContextPath}/template1/css/cqlybest.css?build=${(Options.build)!}">
		</#if>
		<!--[if lt IE 7]><link rel="stylesheet" href="${ContextPath}/template1/css/bootstrap-ie6.css"><![endif]-->
		<link rel="shortcut icon" href="${ContextPath}/template1/favicon.ico">
	</head>

	<body>