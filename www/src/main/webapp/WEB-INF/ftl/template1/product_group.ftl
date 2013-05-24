<#include "/template1/header.ftl">
<div class="container margin-menu">
	<div class="row">
		<div class="span9">
			<#assign Filters=[f0,f1,f2,f3,f4,f5,f6,f7,0]>
			<#assign filterNames=['推荐月份', '适合人群', '交通方式', '产品类型', '产品等级', '标签', '出发城市', '目的地']>
			<#macro selectedDictRender type dict values>
				<#assign items = values?split(",")>
				<#list dict as item>
					<#if items?seq_contains('${item.id}')>
					<@filterUrl type item.id item.name!'' Filters[type]==item.id/>
					</#if>
				</#list>
			</#macro>
			<#macro filterUrl index value text active>
				<a href="${ContextPath}/group/${menu.id}/<#list Filters as v><#if v_index gt 0>-</#if><#if v_index==index>${value}<#else>${v}</#if></#list>.html" class="<#if active>active</#if>">${text}</a>
			</#macro>
			<#if (menu.productGroup.filterItems)?has_content>
			<div class="filter-box">
				<div class="filter-main">
					<ul>
						<#assign months=[{'id':1,'name':'1月'},{'id':2,'name':'2月'},{'id':3,'name':'3月'},{'id':4,'name':'4月'},{'id':5,'name':'5月'},{'id':6,'name':'6月'},{'id':7,'name':'7月'},{'id':8,'name':'8月'},{'id':9,'name':'9月'},{'id':10,'name':'10月'},{'id':11,'name':'11月'},{'id':12,'name':'12月'}]>
						<#assign crowds=[{'id':1,'name':'个人旅行'},{'id':2,'name':'团体旅行'}]>
						<#list menu.productGroup.filterItems?sort_by('filterType') as item>
						<li>
							<label>${filterNames[item.filterType]}:</label>
							<span>
								<@filterUrl item.filterType 0 '全部' Filters[item.filterType]==0/>
								<#if item.filterType==0><@selectedDictRender item.filterType months item.filterValue /></#if>
								<#if item.filterType==1><@selectedDictRender item.filterType crowds item.filterValue /></#if>
								<#if item.filterType==2><@selectedDictRender item.filterType traffics item.filterValue /></#if>
								<#if item.filterType==3><@selectedDictRender item.filterType types item.filterValue /></#if>
								<#if item.filterType==4><@selectedDictRender item.filterType grades item.filterValue /></#if>
								<#if item.filterType==5><@selectedDictRender item.filterType keywords item.filterValue /></#if>
								<#if item.filterType==6><@selectedDictRender item.filterType departureCities item.filterValue /></#if>
								<#if item.filterType==7><@selectedDictRender item.filterType destinations item.filterValue /></#if>
							</span>
						</li>
						</#list>
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
								<img alt="${product.title!}" src="${springx.rand(0,product.posters?size-1)}?width=154&height=99">
								</#if>
								<a class="title" href="${ContextPath}/product/${product.id}.html" target="_blank">${product.name!}</a>
							</div>
						</div>
					</li>
				<#if product_index%4==3>
				</ul>
				</#if>
				</#list>
			</div>
			</#if>
		</div>
		<div class="span3">
			<div class="box">
				<div class="title title-important"><strong>热门旅游</strong></div>
				<div class="content">
					<ul>
						<li><span class="badge badge-important">1</span><a href="#">塞舌尔9天6晚至爱之旅</a></li>
						<li><span class="badge badge-important">2</span><a href="#">泰国清迈 阿莫拉酒店</a></li>
						<li><span class="badge badge-warning">3</span><a href="#">丽江 大理双飞五日游（半...</a></li>
						<li><span class="badge badge-warning">4</span><a href="#">纯玩丽江自由行双飞五日游</a></li>
					</ul>
				</div>
			</div>
			<div class="box">
				<img src="${ContextPath}/template1/img/c_09.jpg">
			</div>
			<div class="thumbnail">
				<img src="http://www.cqlybest.com/upload_files/73/1_20120429110438_clkrr.jpg">
				<div class="caption">波杜希蒂岛</div>
			</div>
		</div>
	</div>
</div>
<script>
	var PageContext = {
		menu : '${menu.id!}'
	};
</script>
<#include "/template1/footer.ftl">