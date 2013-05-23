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
		<title>重庆易游天下</title>
		<link rel="stylesheet" href="${ContextPath}/template1/css/bootstrap.min.css">
		<link rel="stylesheet" href="${ContextPath}/template1/css/cqlybest.css">
	</head>

	<body>