<#include "/template1/header.ftl">
<#include "/template1/macro/product.ftl">
<div class="container margin-menu">
	<div class="row">
		<div class="span9">
			<#assign Filters=[f0,f1,f2,f3,f4,f5,f6,f7,0]>
			<#macro FilterRender index name values=''>
				<#if values?has_content>
				<li>
					<label>${name}:</label>
					<span>
						<#list ('全部,' + values)?split(",") as item>
						<a href="${ContextPath}/group/${menu.id}/<#list Filters as filter><#if filter_index gt 0>-</#if><#if filter_index==index>${item_index}<#else>${filter}</#if></#list>.html" class="<#if item_index==Filters[index]>active</#if>">${item}</a>
						</#list>
					</span>
				</li>
				</#if>
			</#macro>
			<div class="filter-box">
				<div class="filter-main">
					<ul>
						<@FilterRender 0 '推荐月份' menu.productGroup.groupMonths />
						<@FilterRender 1 '适合人群' menu.productGroup.groupCrowds />
						<@FilterRender 2 '交通方式' menu.productGroup.groupTraffics />
						<@FilterRender 3 '产品类型' menu.productGroup.groupTypes />
						<@FilterRender 4 '产品等级' menu.productGroup.groupGrades />
						<@FilterRender 5 '标签' menu.productGroup.groupKeywords />
						<@FilterRender 6 '出发城市' menu.productGroup.groupDepartureCities />
						<@FilterRender 7 '目的地' menu.productGroup.groupDestinations />
					</ul>
				</div>
			</div>
			<div class="index-brand-section row-fluid">
				<#list products as product>
				<#if product_index%4==0>
				<ul class="thumbnails">
				</#if>
					<li class="span3">
						<div class="thumbnail">
							<div class="caption">
								<#if product.posters?has_content>
								<#assign image = product.posters[springx.rand(0,product.posters?size-1)] />
								<img alt="${product.title!}" src="${ContextPath}/image/${image.id}.${image.imageType}?width=154&height=99">
								</#if>
								<a class="title" href="${ContextPath}/product/${product.id}.html" target="_blank"><@composeProductName product /></a>
							</div>
						</div>
					</li>
				<#if product_index%4==3>
				</ul>
				</#if>
				</#list>
			</div>
		</div>
		<div class="span3">
		</div>
	</div>
</div>
<script>
	var PageContext = {
		menu : '${menu.id!}'
	};
</script>
<#include "/template1/footer.ftl">