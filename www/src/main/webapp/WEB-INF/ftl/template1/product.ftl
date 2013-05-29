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
		<div id="product-info" class="span4">
			<#if product.specialOffer?exists && product.specialOffer>
			<p><span class="lname">原价：</span><del><span class="price">¥${(product.price/100)?string('0.00')}</span></del></p>
			<#if product.specialPrice?exists>
			<p><span class="lname">预定特价：</span><span class="price">¥${(product.specialPrice/100)?string('0.00')}</span></p>
			<#else>
			<p><span class="lname">预定特价：</span><span>电话咨询</span></p>
			</#if>
			<#else>
			<p><span class="lname">预定价格：</span><span class="price">¥${(product.price/100)?string('0.00')}</span></p>
			</#if>
			<#if product.childPrice?has_content>
			<p><span class="lname">儿童价：</span><span class="price">¥${(product.childPrice/100)?string('0.00')}</span></p>
			</#if>
			<#if product.departureCities?has_content>
			<p><span class="lname">出发城市：</span><span><#list product.departureCities as item><#if item_index gt 0>,</#if>${item.name}</#list></span></p>
			</#if>
			<#if product.destinations?has_content>
			<p><span class="lname">目的地：</span><span><#list product.destinations as item><#if item_index gt 0>,</#if>${item.name}</#list></span></span></p>
			</#if>
			<#if product.traffics?has_content>
			<p><span class="lname">交通：</span><span><#list product.traffics as item><#if item_index gt 0>,</#if>${item.name}</#list></span></p>
			</#if>
			<#if product.types?has_content>
			<p><span class="lname">产品类型：</span><span><#list product.types as item><#if item_index gt 0>,</#if>${item.name}</#list></span></p>
			</#if>
			<#if product.grades?has_content>
			<p><span class="lname">产品等级：</span><span><#list product.grades as item><#if item_index gt 0>,</#if>${item.name}</#list></span></p>
			</#if>
			<#if product.days?has_content>
			<p><span class="lname">行程天数：</span><span>${product.days}天</span></p>
			</#if>
			<p><span class="lname">咨询电话：</span><span class="price">023-63016655</span></p>
			<p><span class="lname"></span><span class="price">023-63067799</span></p>
			<p><span class="lname"></span><span class="price">023-63706551</span></p>
			<br>
			<p><span class="lname"></span><button type="button" class="btn btn-warning"><i class="icon-shopping-cart"></i>立即预定</button></p>
			<br>
			<#if product.priceDescription?has_content>
			<div class="alert alert-success"><strong>费用说明：</strong>${product.priceDescription}</div>
			</#if>
		</div>
		<div class="span8">
			<div id="product-gallery" class="ad-gallery">
				<div class="ad-image-wrapper"></div>
				<div class="ad-controls">
					<div class="ad-nav">
						<div class="ad-thumbs">
							<ul class="ad-thumb-list">
								<#if product.photos?has_content>
								<#list product.photos as photo>
								<li><a href="${photo}?width=608&height=400"><img alt="${product.name!}" src="${photo}?width=90&height=60"></a></li>
								</#list>
								</#if>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="product-trip" class="row">
		<div class="span12">
			<h4 class="title">详细行程</h4>
			<div class="day-trips">
				<div class="day row-fluid">
					<div class="span2">
						<div class="day-icon img-circle alert-success text-center"><strong>第 1 天</strong></div>
					</div>
					<div class="span10">
						<ul class="nav nav-tabs">
							<li class="active"><a href="#d1-1" data-toggle="tab">游</a></li>
							<li><a href="#d1-2" data-toggle="tab">行</a></li>
							<li><a href="#d1-3" data-toggle="tab">吃</a></li>
							<li><a href="#d1-4" data-toggle="tab">住</a></li>
							<li><a href="#d1-5" data-toggle="tab">购</a></li>
						</ul>
						<div class="tab-content">
							<div class="tab-pane active" id="d1-1">早餐后乘车前往【拉市海】（含骑马、划船门票200元，注：如果客人不骑马划船费用不退）游览（15KM,30分钟）。09：00点拉市海边上马，开始游览茶马古道：途经美丽的田园风光，纳西村庄。进入原始森林，感受大自然的气息。12：00—13：00午餐后乘车赴纳西先民在丽江坝子中最早的聚居地之一的【束河古镇】（赠送门票50元），免费参观滇西北最大的玉石城—滇缅玉石城店（120分钟左右），参观藻精华-螺旋藻（约30分钟）返回客栈，晚餐自理</div>
							<div class="tab-pane" id="d1-2">行</div>
							<div class="tab-pane" id="d1-3">吃</div>
							<div class="tab-pane" id="d1-4">住</div>
							<div class="tab-pane" id="d1-5">购</div>
						</div>
					</div>
				</div>
				<div class="day row-fluid last">
					<div class="span2">
						<div class="day-icon img-circle alert-success text-center"><strong>第 2 天</strong></div>
					</div>
					<div class="span10">
						<ul class="nav nav-tabs">
							<li class="active"><a href="#d2-1" data-toggle="tab">游</a></li>
							<li><a href="#d2-2" data-toggle="tab">行</a></li>
							<li><a href="#d2-3" data-toggle="tab">吃</a></li>
							<li><a href="#d2-4" data-toggle="tab">住</a></li>
							<li><a href="#d2-5" data-toggle="tab">购</a></li>
						</ul>
						<div class="tab-content">
							<div class="tab-pane active" id="d2-1">早餐后乘车前往【拉市海】（含骑马、划船门票200元，注：如果客人不骑马划船费用不退）游览（15KM,30分钟）。09：00点拉市海边上马，开始游览茶马古道：途经美丽的田园风光，纳西村庄。进入原始森林，感受大自然的气息。12：00—13：00午餐后乘车赴纳西先民在丽江坝子中最早的聚居地之一的【束河古镇】（赠送门票50元），免费参观滇西北最大的玉石城—滇缅玉石城店（120分钟左右），参观藻精华-螺旋藻（约30分钟）返回客栈，晚餐自理</div>
							<div class="tab-pane" id="d2-2">行</div>
							<div class="tab-pane" id="d2-3">吃</div>
							<div class="tab-pane" id="d2-4">住</div>
							<div class="tab-pane" id="d2-5">购</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	var PageContext = {
		init : function() {
			document.title = '${product.name!}';
			$('.ad-gallery').adGallery({
			});
		}
	};
</script>
<#include "/template1/footer.ftl">