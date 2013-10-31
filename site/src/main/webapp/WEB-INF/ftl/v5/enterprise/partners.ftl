<#assign Title='标杆企业|企业考察|企业考察案例|标杆考察|标杆考察案例' />
<#assign Description='标杆企业|企业考察|企业考察案例|标杆考察|标杆考察案例' />
<#assign Keywords='标杆考察 企业考察' />
<#include "../index/header.ftl">
<#include "../index/menu.ftl">
<div class="breadcrumbs margin-bottom-20">
	<div class="container-fluid">
		<h1 class="color-green pull-left">标杆企业</h1>
		<ul class="pull-right breadcrumb">
			<li><a href="${ContextPath}/enterprise.html">企业考察</a> <span class="divider">/</span></li>
			<li class="active">标杆企业</li>
		</ul>
	</div>
</div>
<div class="container-fluid">
	<div class="row-fluid">
		<ul class="partners clearfix">
			<#assign item_i=0>
			<#list result.items as item>
			<#if item.logo?has_content>
			<li class="span2 <#if item_i%6==0>first</#if>">
				<a title="${item.name!}" href="${ContextPath}/enterprise/partner/${item.id}.html">
					<img alt="${item.name!}" src="http://${ImageServer}/${item.logo.qiniuKey}-companylogo"></em>
				</a>
			</li>
			<#assign item_i=item_i+1>
			</#if>
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
			$('.partners a').height($('.partners a').width()*121/163);
		}
	};
</script>
<#include "../index/footer.ftl">