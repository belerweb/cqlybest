<#import "/springx.ftl" as springx>
<#include "macro.ftl">
<!DOCTYPE HTML>
<html class="expanded">
	<head>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
		<title>${Title!(Settings.basic.siteName)!}</title>
		<meta name="author" content="${(Settings.basic.siteName)!}" >
		<meta name="Keywords" content="${Keywords!(Settings.basic.keywords)!?join(',')}"/>
		<meta name="Description" content="${Description!(Settings.basic.description)!}>"/>
		<meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0;">
		<link href="${ContextPath}/assets/v5/css/bootstrap.css" rel="stylesheet" media="screen">
		<link href="${ContextPath}/assets/v5/css/font-awesome.css" rel="stylesheet">
		<link href="${ContextPath}/assets/v5/css/application.css" rel="stylesheet" media="screen">
		
		<!--[if IE 7]>
			<link href="${ContextPath}/assets/v5/css/font-awesome-ie7.min.css" rel="stylesheet">
		<![endif]-->
	
		<!--[if lt IE 9]>
			<script src="${ContextPath}/assets/v5/js/html5shiv.js"></script>
		<![endif]-->
	</head>

	<body>
		<div id="wrapper">