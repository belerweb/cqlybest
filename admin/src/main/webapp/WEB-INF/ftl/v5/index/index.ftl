<#include "header.ftl">
<!--=== Top ===-->    
<div class="top">
	<div class="container-fluid">
		<div class="row-fluid">
			<ul class="loginbar inline">
				<#if (Settings.basic.weibo.id)?has_content>
				<li class="pull-left"><wb:follow-button uid="${Settings.basic.weibo.id}" type="red_1" width="67" height="24" ></wb:follow-button></li>
				</#if>
				<li><span>亲，欢迎访问${(Settings.basic.siteName)!}！</span></li>
				<li><a href="${ContextPath}/connector/qq_login" class="mg"><img alt="QQ登录" src="${ContextPath}/assets/v2/img/connector/Connect_logo_7.png" width="63" height="24"></a></li>
				<li><a href="${ContextPath}/connector/weibo_login" class="mg" title="使用新浪微博登录并关注我们的官方微博"><img alt="用微博登录" src="${ContextPath}/assets/v2/img/connector/loginButton_24.png" width="102" height="24"></a></li> 
			</ul>
		</div>
	</div><!--/container-->
</div><!--/top-->
<!--=== End Top ===-->
<!--=== Header ===-->
<div class="header">
	<div class="container-fluid">
		<!-- Logo -->
		<div class="logo">
			<a href="/"><#if (Settings.basic.logo)?has_content><img src="${ContextPath}/image/${Settings.basic.logo.id}.${Settings.basic.logo.extension}" height="60"><#else>${(Settings.basic.siteName)!}</#if></a>
		</div><!-- /logo -->
		<!-- Menu -->
		<div class="navbar">
			<div class="navbar-inner">
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li class="active">
							<a href="#" class="dropdown-toggle">首页</a>
						</li>
						<li>
							<a href="#" class="dropdown-toggle">私属定制</a>
						</li>
						<li>
							<a href="#" class="dropdown-toggle">公商务定制</a>
						</li>
						<li>
							<a href="#" class="dropdown-toggle">主题定制</a>
						</li>
						<li>
							<a href="#" class="dropdown-toggle">马尔代夫</a>
						</li>
						<li>
							<a href="#" class="dropdown-toggle">三峡游轮</a>
						</li>
						<#if (Settings.basic.hotline)?has_content>
						<li><a class="hotline"><i class="icon-phone"></i> ${Settings.basic.hotline}</a></li>
						</#if>
					</ul>
					<div class="search-open search-open-inner">
					</div>
				</div><!-- /nav-collapse -->
			</div><!-- /navbar-inner -->
		</div><!-- /navbar -->
	</div><!-- /container -->
</div><!--/header -->

<#if Page.posters?has_content>
<!--=== Slider ===-->
<div class="fullwidthbanner-container">
	<div class="fullwidthabnner">
		<ul>
			<#list Page.posters as poster>
			<li data-transition="fade" data-masterspeed="1000" data-delay="3000">
				<img alt="${poster.title!}" src="${ContextPath}/image/1600/300/${poster.image.id!}.${poster.image.extension}">
			</li>
			</#list>
		</ul>
	</div>
</div>
</#if>

<#if (Links.items)?has_content>
<div class="container-fluid">
	<div class="headline"><h3>友情链接</h3></div>
	<ul class="friendly">
		<#list Links.items as link>
		<li><a href="${link.link!'#'}" title="${link.title!}" target="_blank">${link.name!}</a></li>
		</#list>
	</ul>
</div>
</#if>

<!--=== End Header ===-->
<div class="copyright fixed">
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span8">
				<p>${Settings.basic.copyright!} ｜ ${Settings.basic.icpLicense!} ｜ <a href="http://www.miitbeian.gov.cn" target="_blank">${Settings.basic.icp!}</a></p>
			</div>
			<div class="span4">
				<div class="hide">${(Settings.basic.statistical_todo)!}</div>
			</div>
		</div><!--/row-fluid-->
	</div><!--/container-->
</div>

<script type="text/javascript">
	window.PageContext = {
		init: function(){
			$('.fullwidthabnner').revolution({
				fullWidth:'on'
			});
		}
	};
</script>
<#include "footer.ftl">