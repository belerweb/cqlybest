<#assign Title=product.name! />
<#include "/template1/header.ftl">
<div class="container margin-menu">
	<div class="row">
		<div id="product-name" class="span12">
			<#if product.recommend?exists && product.recommend>
			<span><img alt="推荐" src="${ContextPath}/template1/img/product_recommended.png"></span>
			</#if>
			<#if product.specialOffer?exists && product.specialOffer>
			<span><img alt="特价" src="${ContextPath}/template1/img/product_special.png"></span>
			<span><img alt="特价" src="${ContextPath}/template1/img/product_special_1.png"></span>
			</#if>
			<#if product.popular?exists && product.popular>
			<span><img alt="热卖" src="${ContextPath}/template1/img/product_hot.png"></span>
			</#if>
			<span class="name">${product.name!}</span>
		</div>
	</div>
	<div class="row">
		<div class="span4">
			<div class="product-gallery" data-toggle="modal-gallery" data-target="#product-gallery" data-selector="div.gallery-item">
				<#if product.photos?has_content>
				<#list product.photos as image>
				<div class="gallery-item <#if image_index==0> first</#if><#if image_index%4==1> row-first</#if><#if image_index gt 8> hide</#if>"
					data-href="${ContextPath}/image/${image.id}.${image.imageType}" title="${image.title!}">
					<#if image_index==0>
					<img src="${ContextPath}/image/${image.id}.${image.imageType}?width=298&height=260">
					<#else>
					<img src="${ContextPath}/image/${image.id}.${image.imageType}?width=66&height=50">
					</#if>
				</div>
				</#list>
				</#if>
			</div>
		</div>
		<div class="span4">
			<table class="table table-condensed product-basic-table">
				<tbody>
					<tr>
						<th class="name">游玩岛屿：</th>
						<td>
							<#if distinctIslands?has_content>
							<#list distinctIslands as island>
							<#if island_index gt 0><br></#if><a href="${ContextPath}/maldives/${island.id}.html" target="_blank">${island.zhName!}${island.enName!}</a>
							</#list>
							</#if>
						</td>
					</tr>
					<tr>
						<th class="name">出发城市：</th>
						<td>
							<#if product.departureCities?has_content>
							<#list product.departureCities?split(",") as item>
							<span class="label label-info">${item}</span>
							</#list>
							</#if>
						</td>
					</tr>
					<tr>
						<th class="name">市场价：</th>
						<td class="price del"><del>¥${(product.price/100)?string('0.00')}/人</del></td>
					</tr>
					<#if product.todayPrice?has_content>
					<tr>
						<th class="name">今日特价：</th>
						<td class="price">¥${(product.price/100)?string('0.00')}/人</td>
					</tr>
					<#else>
					<tr>
						<th class="name">预定特价：</th>
						<td class="price"><#if product.specialPrice?exists>¥${(product.specialPrice/100)?string('0.00')}/人<#else>电话咨询预约</#if></td>
					</tr>
					</#if>
				</tbody>
			</table>
		</div>
		<div class="span4">
		</div>
	</div>
	<div class="row">
		<div id="product-calendar" class="span8">
		</div>
		<div class="span4">
		</div>
	</div>
	<div class="row" style="margin-top:10px;margin-bottom:10px;">
		<div class="span8">
			<div id="maldives-island-info-nav">
				<ul class="nav nav-tabs">
					<#if product.trafficList?has_content>
					<li class="active"><a href="#maldives-island-traffic－tab">交通方式</a></li>
					</#if>
					<li><a href="#maldives-island-room－tab">房型介绍</a></li>
					<#if product.priceDescription?has_content>
					<li><a href="#maldives-island-fee－tab">费用说明</a></li>
					</#if>
				</ul>
			</div>
			<#if product.trafficList?has_content>
			<#include "product_1_traffic.ftl">
			</#if>
			<#include "product_1_room.ftl">
			<#if product.priceDescription?has_content>
			<div id="maldives-island-fee－tab" class="maldives-island-section">
				<div class="title">
					<h4>费用说明</h4>
				</div>
				<div>${product.priceDescription}</div>
			</div>
			</#if>
		</div>
		<div class="span4">
		</div>
	</div>
</div>

<div id="product-gallery" class="modal modal-gallery hide fade" tabindex="-1">
	<div class="modal-header">
		<a class="close" data-dismiss="modal">&times;</a>
		<h3 class="modal-title"></h3>
	</div>
	<div class="modal-body"><div class="modal-image"></div></div>
	<div class="modal-footer">
		<a class="btn btn-success modal-play modal-slideshow" data-slideshow="3000"><i class="icon-play icon-white"></i> 自动播放</a>
		<a class="btn btn-info modal-prev"><i class="icon-arrow-left icon-white"></i> 上一张</a>
		<a class="btn btn-primary modal-next">下一张 <i class="icon-arrow-right icon-white"></i></a>
	</div>
</div>

<script>
	var PageContext = {
		init : function() {
			$('#product-calendar').Calendar({
				events: function() {
					return {
						event: [
							<#if product.calendar?has_content>
							<#list product.calendar as day>
							<#if day_index gt 0>,</#if>{date: '${day.date?string('yyyy-MM-dd')}', price: '${day.price!}'}
							</#list>
							</#if>
						]
					}
				}
			});
		}
	};
</script>
<#include "/template1/footer.ftl">