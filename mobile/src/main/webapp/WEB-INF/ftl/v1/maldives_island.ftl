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

	<link rel="stylesheet" href="${ContextPath}/asserts/v1/css/modern.css">
	<link rel="stylesheet" href="${ContextPath}/asserts/v1/css/modern-responsive.css">
	<link rel="stylesheet" href="${ContextPath}/asserts/v1/css/ratchet.css">
	<link rel="stylesheet" href="${ContextPath}/asserts/v1/css/cqlybest.css">

	<script src="${ContextPath}/asserts/v1/js/ratchet.js"></script>
</head>
<body>
	<header class="bar-title">
		<a class="button" href="${ContextPath}/index.html">
			首页
		</a>
		<h1 class="title">${island.zhName}</h1>
		<a class="button" href="${ContextPath}/maldives/${island.id}/price.html">
			行程报价
		</a>
	</header>

	<div class="content">
		<div class="metrouicss">
			<table class="bordered">
				<tbody>
					<tr>
						<td style="width: 75px;">中文名称：</td>
						<td>${island.zhName!}</td>
					</tr>
					<tr>
						<td>英文名称：</td>
						<td>${island.enName!}</td>
					</tr>
					<tr>
						<td>岛屿级别：</td>
						<td>${island.level!}</td>
					</tr>
					<tr>
						<td>上岛方式：</td>
						<td>${island.way!}</td>
					</tr>
					<tr>
						<td>岛屿大小：</td>
						<td>${island.area!}</td>
					</tr>
					<tr>
						<td>浮潜等级：</td>
						<td>${island.snorkeling!}</td>
					</tr>
					<tr>
						<td>参考价格：</td>
						<td>${island.price!}</td>
					</tr>
					<tr>
						<td colspan="2">${island.description!}</td>
					</tr>
				</tbody>
			</table>
		</div>
		<nav class="bar-tab">
			<ul class="tab-inner">
				<li class="tab-item active">
					<a href="#">
						<!-- img class="tab-icon" src="" -->
						<div class="tab-label">岛屿</div>
					</a>
				</li>
				<li class="tab-item">
					<a href="#">
						<div class="tab-label">酒店</div>
					</a>
				</li>
				<li class="tab-item">
					<a href="#">
						<div class="tab-label">房型</div>
					</a>
				</li>
				<li class="tab-item">
					<a href="#">
						<div class="tab-label">餐饮</div>
					</a>
				</li>
				<li class="tab-item">
					<a href="#">
						<div class="tab-label">活动与设施</div>
					</a>
				</li>
			</ul>
		</nav>
	</div>
</body>
</html>