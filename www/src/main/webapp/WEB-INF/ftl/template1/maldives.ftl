<#assign Title=(island.zhName!)+(island.enName!) />
<#include "/template1/header.ftl">
<style type="text/css">
#maldives-island-info-nav.affix{
	width: 700px;
	top: 41px;
	background: #FFF;
}
#maldives-island-info-nav .nav{
	margin: 5px 0 0 0;
}
.maldives-island-section .title hr {
	border-color: #309100;
	margin: 2px 0 4px;
}
.room-gallery{
	margin: -15px 0 10px 0;
}
.room-gallery a{
	display: inline-block;
	height:100px;
	width:100px;
}
</style>
<div id="maldives-island" class="container">
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
						<img alt="${image.title!}" src="${ContextPath}/image/${image.id}.${image.imageType}?width=940&height=300">
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
					<li><a href="#maldives-island-play－tab">娱乐设施</a></li>
				</ul>
			</div>
			<#include "maldives_info.ftl">
			<#include "maldives_hotel.ftl">
			<#include "maldives_room.ftl">
			<#include "maldives_dining.ftl">
			<#include "maldives_play.ftl">
		</div>
		<div class="span3">
		</div>
	</div>
</div>
<#include "/template1/image-gallery.ftl">
<@gallery 'maldives-gallery' />

<script type="text/javascript">
	var PageContext = {
		init: function() {
			$(document.body).scrollspy({target: '#maldives-island-info-nav'});
			$('#maldives-island-info-nav').affix({
				offset: {
					top: $('#maldives-island-info-nav').offset().top - 50
				}
			});
		}
	};
</script>
<#include "/template1/footer.ftl">