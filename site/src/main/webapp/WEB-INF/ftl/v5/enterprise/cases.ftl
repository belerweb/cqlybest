<#assign Title='成功案例|企业考察|企业考察案例|标杆考察|标杆考察案例' />
<#assign Description='成功案例|企业考察|企业考察案例|标杆考察|标杆考察案例' />
<#assign Keywords='标杆考察 企业考察' />
<#include "../index/header.ftl">
<#include "../index/menu.ftl">
<div class="breadcrumbs margin-bottom-20">
	<div class="container-fluid">
		<h1 class="color-green pull-left">成功案例</h1>
		<ul class="pull-right breadcrumb">
			<li><a href="${ContextPath}/enterprise.html">企业考察</a> <span class="divider">/</span></li>
			<li class="active">成功案例</li>
		</ul>
	</div>
</div>
<div class="container-fluid">
	<div class="row-fluid">
		<ul class="portfolio cases clearfix">
			<#list result.items as item>
			<li class="span3 <#if item_index%4==0>first</#if>">
				<a href="${ContextPath}/case/${item.id}.html">
					<em class="overflow-hidden"><img src="http://${ImageServer}/${item.cover.qiniuKey}-casecover"></em>
					<span>
						<strong>${item.name!}</strong>
					</span>
				</a>
			</li>
			</#list>
		</ul>
	</div>
</div>
<#include "../index/copyright.ftl">
<script type="text/javascript">
	window.PageContext = {
		fixCopyright: false,
		activeNav: 'example',
		init: function(){
		}
	};
</script>
<#include "../index/footer.ftl">