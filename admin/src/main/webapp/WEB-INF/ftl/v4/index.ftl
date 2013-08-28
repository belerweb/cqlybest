<!doctype html>
<html>
	<#assign ContextPath=springMacroRequestContext.getContextPath() />
	<#assign User = securityContextHolder.getContext().getAuthentication().getPrincipal() />
	<#assign colors=['#87C116','#00AFF1','#0BA6FF','#FC8E00','#04B1E0','#FFC400','#1D86C9']>
	<#import "springx.ftl" as springx>
	<#include "macro.ftl">
	<head>
		<meta charset="utf-8">
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
		<link href="${ContextPath}/assets/v4/css/bootmetro.css?build=${(Settings.version.buildTime)!}" rel="stylesheet">
		<link href="${ContextPath}/assets/v4/css/cqlybest.css?build=${(Settings.version.buildTime)!}" rel="stylesheet">
	</head>
	<body>
		<div id="weibo-body" class="container metro">
			<#list 0..(islands?size/6)?floor-1 as i>
			<div class="row-fluid">
				<#if i%2==0>
				<div class="span8">
					<div>
						<#assign items=[islands[i*6], islands[i*6+1]]>
						<#list items as item>
						<a href="<#if (Settings.basic.siteUrl)?has_content>${Settings.basic.siteUrl}/maldives/${item.id}.html<#else>#</#if>" class="tile wide imagetext wideimage" style="background-color:${colors[springx.rand(0, colors?size-1)]};" target="_blank">
							<img alt="" src="<@getOneImageUrl2 item.hotelPictures />?width=489&height=200">
							<div class="textover-wrapper transparent">
								<div class="text2"><strong>${item.zhName!}</strong><br>${item.enName!}</div>
							</div>
						</a>
						</#list>
					</div>
				</div>
				<div class="span4">
					<#assign item=islands[i*6+2]>
					<div>
						<a href="<#if (Settings.basic.siteUrl)?has_content>${Settings.basic.siteUrl}/maldives/${item.id}.html<#else>#</#if>" class="tile squarepeek" style="background-color:${colors[springx.rand(0, colors?size-1)]};" target="_blank">
							<img alt="" src="<@getOneImageUrl2 item.hotelPictures />?width=231&height=200">
							<div class="text-inner">
								<div class="text4"><strong>${item.zhName!}</strong><br>${item.enName!}</div>
							</div>
						</a>
					</div>
				</div>
				<#else>
				<div class="span4">
					<#assign item=islands[i*6+2]>
					<div>
						<a href="<#if (Settings.basic.siteUrl)?has_content>${Settings.basic.siteUrl}/maldives/${item.id}.html<#else>#</#if>" class="tile squarepeek" style="background-color:${colors[springx.rand(0, colors?size-1)]};" target="_blank">
							<img alt="" src="<@getOneImageUrl2 item.hotelPictures />?width=231&height=200">
							<div class="text-inner">
								<div class="text4"><strong>${item.zhName!}</strong><br>${item.enName!}</div>
							</div>
						</a>
					</div>
				</div>
				<div class="span8">
					<div>
						<#assign items=[islands[i*6], islands[i*6+1]]>
						<#list items as item>
						<a href="<#if (Settings.basic.siteUrl)?has_content>${Settings.basic.siteUrl}/maldives/${item.id}.html<#else>#</#if>" class="tile wide imagetext wideimage" style="background-color:${colors[springx.rand(0, colors?size-1)]};" target="_blank">
							<img alt="" src="<@getOneImageUrl2 item.hotelPictures />?width=489&height=200">
							<div class="textover-wrapper transparent">
								<div class="text2"><strong>${item.zhName!}</strong><br>${item.enName!}</div>
							</div>
						</a>
						</#list>
					</div>
				</div>
				</#if>
			</div>
			<div class="row-fluid">
				<#assign items=[islands[i*6+3], islands[i*6+4], islands[i*6+5]]>
				<#list items as item>
				<div class="span4">
					<div>
						<a href="<#if (Settings.basic.siteUrl)?has_content>${Settings.basic.siteUrl}/maldives/${item.id}.html<#else>#</#if>" class="tile square image" style="background-color:${colors[springx.rand(0, colors?size-1)]};" target="_blank">
							<img alt="" src="<@getOneImageUrl2 item.hotelPictures />?width=231&height=150">
							<div class="textover-wrapper transparent">
								<div class="text2"><strong>${item.zhName!}</strong><br>${item.enName!}</div>
							</div>
						</a>
					</div>
				</div>
				</#list>
			</div>
			</#list>
		</div>
		<#if (Settings.basic.statistical)?has_content>
		<div class="hide">${Settings.basic.statistical}</div>
		</#if>
		<script src="http://js.t.sinajs.cn/t4/enterprise/js/public/appframe/client.js" type="text/javascript" charset="utf-8"></script>
	</body>
</html>
