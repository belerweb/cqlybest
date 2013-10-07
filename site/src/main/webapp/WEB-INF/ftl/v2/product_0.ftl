<#assign Title=product.name! />
<#include "/v2/header.ftl">
<div class="container margin-menu">
	<div class="row">
		<div id="product-name" class="span12">
			<#if product.recommend?exists && product.recommend>
			<span><img alt="推荐" src="${ContextPath}/assets/v2/img/product_recommended.png"></span>
			</#if>
			<#if product.specialOffer?exists && product.specialOffer>
			<span><img alt="特价" src="${ContextPath}/assets/v2/img/product_special.png"></span>
			<span><img alt="特价" src="${ContextPath}/assets/v2/img/product_special_1.png"></span>
			</#if>
			<#if product.popular?exists && product.popular>
			<span><img alt="热卖" src="${ContextPath}/assets/v2/img/product_hot.png"></span>
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
			<#if product.specialPrice?exists>
			<p><span class="lname">预定价格：</span><span class="price">¥${(product.price/100)?string('0.00')}</span></p>
			<#else>
			<p><span class="lname">预定价格：</span><span>电话咨询</span></p>
			</#if>
			</#if>
			<#if product.childPrice?has_content>
			<p><span class="lname">儿童价：</span><span class="price">¥${(product.childPrice/100)?string('0.00')}</span></p>
			</#if>
			<#if product.departureCities?has_content>
			<p><span class="lname">出发城市：</span><span><#list product.departureCities?split(",") as item><#if item_index gt 0>,</#if>${item}</#list></span></p>
			</#if>
			<#if product.destinations?has_content>
			<p><span class="lname">目的地：</span><span><#list product.destinations?split(",") as item><#if item_index gt 0>,</#if>${item}</#list></span></span></p>
			</#if>
			<#if product.traffics?has_content>
			<p><span class="lname">交通：</span><span><#list product.traffics?split(",") as item><#if item_index gt 0>,</#if>${item}</#list></span></p>
			</#if>
			<#if product.types?has_content>
			<p><span class="lname">产品类型：</span><span><#list product.types?split(",") as item><#if item_index gt 0>,</#if>${item}</#list></span></p>
			</#if>
			<#if product.grades?has_content>
			<p><span class="lname">产品等级：</span><span><#list product.grades?split(",") as item><#if item_index gt 0>,</#if>${item}</#list></span></p>
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
								<#list product.photos as image>
								<li><a href="${ContextPath}/image/608/400/${image.id}.${image.imageType}"><img alt="${image.title!}" src="${ContextPath}/image/90/60/${image.id}.${image.imageType}"></a></li>
								</#list>
								</#if>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<#if product.travels?has_content>
	<div id="product-trip" class="row">
		<div class="span12">
			<h4 class="title">详细行程</h4>
			<div class="day-trips">
				<#list product.travels as travel>
				<div class="day row-fluid <#if travel_index==product.travels?size-1>last</#if>">
					<div class="span2">
						<div class="day-icon img-circle alert-success text-center"><strong>${travel.name!}</strong></div>
					</div>
					<div class="span10">
						<ul class="nav nav-tabs">
							<li class="active"><a href="#d${travel_index}-1" data-toggle="tab">游</a></li>
							<li><a href="#d${travel_index}-2" data-toggle="tab">行</a></li>
							<li><a href="#d${travel_index}-3" data-toggle="tab">吃</a></li>
							<li><a href="#d${travel_index}-4" data-toggle="tab">住</a></li>
							<li><a href="#d${travel_index}-5" data-toggle="tab">购</a></li>
						</ul>
						<div class="tab-content">
							<div class="tab-pane active" id="d${travel_index}-1">${travel.tour!}</div>
							<div class="tab-pane" id="d${travel_index}-2">${travel.traffic!}</div>
							<div class="tab-pane" id="d${travel_index}-3">${travel.eat!}</div>
							<div class="tab-pane" id="d${travel_index}-4">${travel.live!}</div>
							<div class="tab-pane" id="d${travel_index}-5">${travel.purchase!}</div>
						</div>
					</div>
				</div>
				</#list>
			</div>
		</div>
	</div>
	</#if>
</div>
<script>
	var PageContext = {
		init : function() {
			document.title = '${product.name!}';
			$('.ad-gallery').adGallery({
				loader_image: '${ContextPath}/assets/v2/img/gallery/loader.gif'
			});
		}
	};
</script>
<#include "/v2/footer.ftl">