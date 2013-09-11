<#include "header.ftl">
<#include "menu.ftl">
<#if Page.posters?has_content>
<!--=== Slider ===-->
<div class="fullwidthbanner-container">
	<div class="fullwidthabnner">
		<ul class="hide">
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

<#include "copyright.ftl">
<script type="text/javascript">
	window.PageContext = {
		activeNav: 'index',
		init: function(){
			$('.fullwidthabnner').revolution({
				fullWidth:'on'
			});
		}
	};
</script>
<#include "footer.ftl">