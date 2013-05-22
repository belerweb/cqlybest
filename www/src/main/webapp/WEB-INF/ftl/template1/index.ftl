<#include "/template1/header.ftl">
<div class="container margin-menu">
	<div class="row">
		<div class="span9">
			<#if posters?has_content>
			<div class="row-fluid">
				<div class="span12">
					<div id="index-top-carousel" class="carousel slide" data-interval="3000">
						<ol class="carousel-indicators">
							<#list 0..posters?size-1 as i>
							<li data-target="#index-top-carousel" data-slide-to="i" class="<#if i=0>active</#if>"></li>
							</#list>
						</ol>
						<div class="carousel-inner">
							<#list posters as poster>
							<div class="<#if poster_index=0>active</#if> item">
								<img alt="${poster.title!}" src="${poster.imageUrl!}?width=700&height=300">
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
		</div>
		<div class="span3"></div>
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
<#include "/template1/footer.ftl">