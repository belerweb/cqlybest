<#assign Title='${island.zhName!}${island.enName!} 马代 马尔代夫 海岛 自由行' />
<#assign Description=island.description!?html />
<#assign Keywords=island.zhName! />
<#include "/v2/header.ftl">
<div id="maldives-island" class="container">
	<style type="text/css">
	#maldives-island-sidebar.affix{
		width: 220px;
		top: 0;
		background: #FFF;
	}
	#maldives-island-info-nav.affix{
		width: 700px;
		top: 0;
		background: #FFF;
	}
	#maldives-island-info-nav .nav{
		margin: 5px 0 0 0;
	}
	.maldives-island-section {
		padding-top: 20px;
	}
	.maldives-island-section .title h4 {
		margin-top: 0;
	}
	.maldives-island-section .title hr {
		border-color: #309100;
		margin: 2px 0 4px;
	}
	.room-gallery{
		margin: 0 0 10px 0;
	}
	.room-gallery a{
		float: left;
		margin: 0 0 2px 2px;
	}
	.maldives-island-section table {
		margin-bottom: 0;
	}
	
	#island-product-accordion .accordion-group {
		border-top: 0;
		border-radius: 0;
		margin: 0;
	}
	#island-product-accordion .accordion-heading {
		background-color:#DFF0D8;
	}
	#island-product-accordion .accordion-heading a{
		color:#333333;
	}
	#island-product-accordion .accordion-inner {
		padding: 0;
	}
	</style>
	<h3 class="name">${island.zhName!} <span>${island.enName!}</span></h3>
	<#if island.pictures?has_content>
	<div class="row">
		<div class="span12">
			<div id="maldives-island-top-carousel" class="carousel slide" data-interval="3000">
				<ol class="carousel-indicators">
					<#list 0..island.pictures?size-1 as i>
					<li data-target="#maldives-island-top-carousel" data-slide-to="i" class="<#if i=0>active</#if>"></li>
					</#list>
				</ol>
				<div class="carousel-inner">
					<#list island.pictures as image>
					<div class="<#if image_index=0>active</#if> item">
						<img alt="${image.title!}" src="${ContextPath}/image/940/300/${image.id}.${image.extension}">
						<div class="carousel-caption">
							<h4>${image.title!}</h4>
							<p>${image.description!}</p>
						</div>
					</div>
					</#list>
				</div>
				<a class="carousel-control left" href="#maldives-island-top-carousel" data-slide="prev">&lsaquo;</a>
				<a class="carousel-control right" href="#maldives-island-top-carousel" data-slide="next">&rsaquo;</a>
			</div>
		</div>
	</div>
	</#if>
	<div class="row">
		<div class="span9">
			<div id="maldives-island-info-nav">
				<ul class="nav nav-tabs">
					<li class="active"><a href="#maldives-island-info－tab">岛屿详情</a></li>
					<li><a href="#maldives-island-hotel－tab">酒店详情</a></li>
					<li><a href="#maldives-island-room－tab">房型介绍</a></li>
					<li><a href="#maldives-island-dining－tab">餐饮设施</a></li>
					<li><a href="#maldives-island-play－tab">活动与娱乐设施</a></li>
				</ul>
			</div>
			<#include "maldives_info.ftl">
			<#include "maldives_hotel.ftl">
			<#include "maldives_room.ftl">
			<#include "maldives_dining.ftl">
			<#include "maldives_play.ftl">
		</div>
		<div class="span3 sidebar">
			<div id="maldives-island-sidebar">
				<#if (Options.site_400)?has_content>
				<div class="phone text-center">
					☎${Options.site_400}
				</div>
				</#if>
				<#if islandProducts?has_content>
				<div class="maldives-island-section">
					<div class="title">
						<h4>本岛行程</h4>
						<hr>
					</div>
					<div id="island-product-accordion" class="accordion">
						<#list islandProducts as product>
						<div class="accordion-group">
							<div class="accordion-heading">
								<a href="#island-product-${product.id}" data-parent="#island-product-accordion" data-toggle="collapse" class="accordion-toggle">
									<#if (product.detail.room1)?has_content && (product.detail.room1Unit)?has_content><#assign room1=product.detail.room1 + product.detail.room1Unit></#if>
									<#if (product.detail.room2)?has_content && (product.detail.room2Unit)?has_content><#assign room2=product.detail.room2 + product.detail.room2Unit></#if>
									<#if (product.detail.room3)?has_content && (product.detail.room3Unit)?has_content><#assign room3=product.detail.room3 + product.detail.room3Unit></#if>
									<#if product.days?has_content>${product.days}天</#if><#if product.nights?has_content>${product.nights}晚</#if> ${room1!}${room2!}${room3!}
									<#if product.price?has_content><span style="color:#F89406">¥${(product.price/100)?string('0.00')}起</span></#if>
								</a>
							</div>
							<div id="island-product-${product.id}" class="accordion-body collapse <#if product_index==0>in</#if>">
								<div class="accordion-inner">
									<a href="${ContextPath}/product/${product.id}.html" target="_blank"
										title="点击查看详情并预定 ${product.name!} <#if product.days?has_content>${product.days}天</#if><#if product.nights?has_content>${product.nights}晚</#if> ${room1!}${room2!}${room3!} <#if product.price?has_content>¥${(product.price/100)?string('0.00')}起</#if>">
									<#if product.posters?has_content>
									<#assign image = product.posters[springx.rand(0,product.posters?size-1)] />
									<img alt="${product.title!}" src="${ContextPath}/image/218/163/${image.id}.${image.extension}">
									<#else>
									点击查看详情并预定 ${product.name!} <#if product.days?has_content>${product.days}天</#if><#if product.nights?has_content>${product.nights}晚</#if>
									${room1!}${room2!}${room3!} <#if product.price?has_content>¥${(product.price/100)?string('0.00')}起</#if>
									</#if>
									</a>
								</div>
							</div>
						</div>
						</#list>
					</div>
				</div>
				</#if>
			</div>
		</div>
	</div>
</div>
<#include "/v2/image-gallery.ftl">
<@gallery 'maldives-gallery' />

<script type="text/javascript">
	var PageContext = {
		init: function() {
			$(document.body).scrollspy({target: '#maldives-island-info-nav', offset: 45});
			$('#maldives-island-info-nav').affix({
				offset: {
					top: $('#maldives-island-info-nav').offset().top - 5
				}
			});
			$('#maldives-island-sidebar').affix({
				offset: {
					top: $('#maldives-island-sidebar').offset().top
				}
			});
		}
	};
</script>
<#include "/v2/footer.ftl">