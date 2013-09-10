<#import "/springx.ftl" as springx>
<#include "../macro.ftl">
<!DOCTYPE html>
<!--[if IE 7]> <html lang="zh" class="ie7" xmlns:wb="http://open.weibo.com/wb"> <![endif]-->  
<!--[if IE 8]> <html lang="zh" class="ie8" xmlns:wb="http://open.weibo.com/wb"> <![endif]-->  
<!--[if IE 9]> <html lang="zh" class="ie9" xmlns:wb="http://open.weibo.com/wb"> <![endif]-->  
<!--[if !IE]><!--> <html lang="zh" xmlns:wb="http://open.weibo.com/wb"> <!--<![endif]-->  
<head>
	<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
	<title>${Title!(Settings.basic.siteName)!}</title>
	<meta name="author" content="${(Settings.basic.siteName)!}" >
	<meta name="Keywords" content="${Keywords!(Settings.basic.keywords)!?join(',')}"/>
	<meta name="Description" content="${Description!(Settings.basic.description)!}>"/>
	<meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0;">

	<link href="${ContextPath}/assets/v5/css/bootstrap.css" rel="stylesheet" media="screen">
	<link href="${ContextPath}/assets/v5/css/font-awesome.css" rel="stylesheet">
	<link href="${ContextPath}/assets/v5/css/revolution-settings.css" rel="stylesheet" media="screen">
	<link href="${ContextPath}/assets/v5/css/application2.css" rel="stylesheet" media="screen">
</head> 

<body class="fixed-bottom">