<#include "header.ftl">
<#include "menu.ftl">
<#if Page.posters?has_content>
<div class="full-width">
	<div class="inner">
		<#list Page.posters as poster>
		<div class="slide">
			<img alt="${poster.title!}" src="http://${ImageServer}/${poster.image.qiniuKey}?imageView/1/w/1600/h/500">
		</div>
		</#list>
	</div>
	<div class="controls">
		<a href="#" class="left">&lt;</a>
		<a href="#" class="right">&gt;</a>
	</div>
	<div class="slide-nav"></div>
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
			$('.full-width').fullWidth({
				maxHeight: 500,
				minHeight: 320,
				scale: 1600/500
			});
		}
	};
</script>
<#include "footer.ftl">