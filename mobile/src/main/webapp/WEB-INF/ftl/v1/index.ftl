<!DOCTYPE html>
<html>
<head>
	<#assign ContextPath=springMacroRequestContext.getContextPath() />
	<meta charset="utf-8">
	<title><#if Title?has_content>${Title}<#else>${(Options.site_name)!}</#if></title>

	<!-- Sets initial viewport load and disables zooming  -->
	<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">

	<!-- Makes your prototype chrome-less once bookmarked to your phone's home screen -->
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">

	<!-- Set Apple icons for when prototype is saved to home screen -->
	<link rel="apple-touch-icon-precomposed" sizes="114x114" href="${ContextPath}/asserts/v1/touch-icons/apple-touch-icon-114x114.png">
	<link rel="apple-touch-icon-precomposed" sizes="72x72" href="${ContextPath}/asserts/v1/touch-icons/apple-touch-icon-72x72.png">
	<link rel="apple-touch-icon-precomposed" sizes="57x57" href="${ContextPath}/asserts/v1/touch-icons/apple-touch-icon-57x57.png">

	<link rel="stylesheet" href="${ContextPath}/asserts/v1/css/ratchet.css">
	<link rel="stylesheet" href="${ContextPath}/asserts/v1/css/cqlybest.css">

	<script src="${ContextPath}/asserts/v1/js/ratchet.js"></script>
</head>
<body>
	<!-- header class="bar-title">
		<h1 class="title"></h1>
	</header -->

	<div class="content">
		<#if posters?has_content>
		<div id="index-top-slider" class="slider">
			<ul>
				<#list posters as poster>
				<li>
					<img alt="${poster.title!}" src="${poster.imageUrl!}?width=700&height=300" width="100%">
				</li>
				<li>
					<img alt="${poster.title!}" src="${poster.imageUrl!}?width=700&height=300" width="100%">
				</li>
				</#list>
			</ul>
			<span class="slide-text">左右滑动试试</span>
		</div>
		</#if>
		<div id="index-maldives" class="index-section">
			<ul class="list">
				<li class="list-divider">马尔代夫 <!-- a href="${ContextPath}/maldives.html">更多</a --></li>
				<#list maldivesIslands as island>
				<li>
					<a href="${ContextPath}/maldives/${island.id}.html">${island.zhName!}|${island.enName!}</a>
				</li>
				</#list>
			</ul>
		</div>
	</div>
</body>
</html>