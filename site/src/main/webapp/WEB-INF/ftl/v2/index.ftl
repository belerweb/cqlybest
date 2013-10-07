<#include "/v2/header.ftl">
<#include "/v2/macro/macro.ftl">
<div class="container margin-menu">
	<div class="row">
		<div class="span9">
			<#if Page.posters?has_content>
			<div class="row-fluid">
				<div class="span12">
					<div id="index-top-carousel" class="carousel slide" data-interval="3000">
						<ol class="carousel-indicators">
							<#list 0..Page.posters?size-1 as i>
							<li data-target="#index-top-carousel" data-slide-to="i" class="<#if i=0>active</#if>"></li>
							</#list>
						</ol>
						<div class="carousel-inner">
							<#list Page.posters as poster>
							<div class="<#if poster_index=0>active</#if> item">
								<img alt="${poster.title!}" src="http://${ImageServer}/${poster.image.qiniuKey}?imageView/1/w/${700}/h/${300}">
								<div class="carousel-caption">
									<h4>${poster.title!}</h4>
									<p>${poster.description!}</p>
								</div>
							</div>
							</#list>
						</div>
						<a class="carousel-control left" href="#index-top-carousel" data-slide="prev">&lsaquo;</a>
						<a class="carousel-control right" href="#index-top-carousel" data-slide="next">&rsaquo;</a>
					</div>
				</div>
			</div>
			</#if>
			<#list Page.contents as section>
			<#if section.type=="code">${section.code!}</#if>
			<#if section.type=="img">
			<div class="row-fluid">
				<a <#if section.img.url?has_content>href="${section.img.url}"</#if>>
					<img src="http://${ImageServer}/${section.img.qiniuKey}">
				</a>
			</div>
			</#if>
			<#if section.type=="product" && section.queryResult.items?has_content>
			<div class="widget-box transparent">
				<div class="widget-header widget-header-flat">
					<h4 class="lighter">
						${section.name!}
					</h4>
				</div>
				<div class="widget-body">
					<div class="widget-body-inner">
						<div class="widget-main no-padding">
							<#list section.queryResult.items as product>
							${product.name!}
							</#list>
						</div>
					</div>
				</div>
			</div>
			</#if>
			<#if section.type=="maldives"&& section.queryResult.items?has_content>
			<div class="widget-box transparent">
				<div class="widget-header widget-header-flat">
					<h4 class="lighter">
						${section.name!}
					</h4>
				</div>
				<div class="widget-body">
					<div class="widget-body-inner">
						<div class="widget-main no-padding">
							<#list section.queryResult.items as island>
							${island.zhName!}
							</#list>
						</div>
					</div>
				</div>
			</div>
			</#if>
			</#list>
		</div>
		<div class="span3">
			<#list Page.sidebars as section>
			<#if section.type=="code">${section.code!}</#if>
			</#list>
		</div>
	</div>
</div>
<script>
	var PageContext = {
		menu : 'index',
		init : function() {
			$('.carousel').carousel();
		}
	};
</script>
<#include "/v2/footer.ftl">