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