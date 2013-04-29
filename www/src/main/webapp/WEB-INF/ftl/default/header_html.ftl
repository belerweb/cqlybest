<!doctype html>
<html>
	<#assign ContextPath = springMacroRequestContext.getContextPath() />
	<#assign Authentication = securityContextHolder.getContext().getAuthentication() />
	<#if Authentication?exists && Authentication.getPrincipal()!="anonymousUser">
		<#assign User = Authentication.getPrincipal() />
	</#if>
	<head>
		<meta charset="utf-8">
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
		<title>重庆易游天下</title>
		<link rel="stylesheet" href="${ContextPath}/default/css/bootstrap.min.css">
		<link rel="stylesheet" href="${ContextPath}/default/css/cqlybest.css">
	</head>

	<body>