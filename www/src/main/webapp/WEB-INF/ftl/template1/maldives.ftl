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
				</ul>
			</div>
			<div id="maldives-island-info－tab" class="maldives-island-section">
				<div class="title">
					<h4>岛屿详情</h4>
					<hr>
				</div>
				<table class="table table-condensed">
					<colgroup>
						<col width="70" />
						<col width="280" />
						<col width="70" />
						<col width="280" />
					</colgroup>
					<tbody>
						<tr>
							<th>中文名称：</th>
							<td>${island.zhName!}
							<th>英文名称：</th>
							<td>${island.enName!}
						</tr>
						<tr>
							<th>岛屿级别：</th>
							<td>${island.level!}
							<th>上岛方式：</th>
							<td>${island.way!}
						</tr>
						<tr>
							<th>岛屿大小：</th>
							<td>${island.area!}
							<th>浮潜等级：</th>
							<td>${island.snorkeling!}
						</tr>
						<tr>
							<th>参考价格：</th>
							<td colspan="3">${island.price!}
						</tr>
						<tr>
							<th>详细信息：</th>
							<td colspan="3">${island.description!}
						</tr>
					</tbody>
				</table>
			</div>
			<div id="maldives-island-hotel－tab" class="maldives-island-section">
				<div class="title">
					<h4>酒店详情</h4>
					<hr>
				</div>
			</div>
			<div id="maldives-island-room－tab" class="maldives-island-section">
				<div class="title">
					<h4>房型介绍</h4>
					<hr>
				</div>
				<#if island.rooms?has_content>
				<#list island.rooms as room>
				</#list>
				</#if>
			</div>
		</div>
		<div class="span3">
		</div>
	</div>
</div>
<script>
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