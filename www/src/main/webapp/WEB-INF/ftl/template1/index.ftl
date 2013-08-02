<#include "/template1/header.ftl">
<#include "/template1/macro/macro.ftl">
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
			<#if specials?has_content>
			<div class="index-brand-section row-fluid">
				<div class="span12">
					<h4>特价产品<span class="more"><a href="${ContextPath}/special.html" target="_blank">更多</a></span></h4>
					<hr>
				</div>
				<ul class="thumbnails">
					<#list specials as product>
					<li class="span3">
						<div class="thumbnail">
							<div class="caption">
								<#if product.posters?has_content>
								<img alt="<@composeProductName product />" src="<@getOneImageUrl product.posters />?width=154&height=99">
								</#if>
								<a class="title" href="${ContextPath}/product/${product.id}.html" target="_blank"><@composeProductName product /></a>
								<p>
									<span><#if (product.specialPrice)?exists>¥${(product.specialPrice/100)?string('0.00')}<#else>特价</#if></span>
									<#if (product.price)?exists><del>原价：¥${(product.price/100)?string('0.00')}</del></#if>
								</p>
							</div>
						</div>
					</li>
					</#list>
				</ul>
			</div>
			</#if>
			<#if groups?has_content>
			<#list groups as group>
			<div class="index-brand-section row-fluid">
				<div class="span12">
					<h4>${(group.group.name)!}<span class="more"><a href="${ContextPath}/group/${(group.group.id)!}.html" target="_blank">更多</a></span></h4>
					<hr>
				</div>
				<ul class="thumbnails">
					<#list group.products as product>
					<li class="span3">
						<div class="thumbnail">
							<div class="caption">
								<#if product.posters?has_content>
								<img alt="<@composeProductName product />" src="<@getOneImageUrl product.posters />?width=154&height=99">
								</#if>
								<a class="title" href="${ContextPath}/product/${product.id}.html" target="_blank"><@composeProductName product /></a>
							</div>
						</div>
					</li>
					</#list>
				</ul>
			</div>
			</#list>
			</#if>
		</div>
		<div class="span3">
			<!-- 特价 -->
			<div class="index-recommend-box box">
				<div class="title title-success"><strong>推荐产品</strong></div>
				<div class="content">
					<#list recommendeds as product>
					<div class="thumbnail">
						<div class="caption">
							<#if product.posters?has_content>
							<img alt="<@composeProductName product />" src="<@getOneImageUrl product.posters />?width=196&height=99">
							</#if>
							<a class="title" href="${ContextPath}/product/${product.id}.html" target="_blank"><@composeProductName product /></a>
						</div>
					</div>
					</#list>
				</div>
			</div>
			<!-- 热门 -->
			<div class="box">
				<div class="title title-important"><strong>热门线路</strong></div>
				<div class="content">
					<ul>
						<#list hots as product>
						<li>
							<span class="badge badge-warning">${product_index+1}</span>
							<a href="${ContextPath}/product/${product.id}.html" target="_blank"><@composeProductName product /></a>
						</li>
						</#list>
					</ul>
				</div>
			</div>
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
<#include "/template1/footer.ftl">