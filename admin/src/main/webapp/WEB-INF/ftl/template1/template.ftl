<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div class="grid">
	<div class="grid-title"></div>
	<ul class="tabs-nav">
		<li class="active">
			<a data-toggle="tab" href="javascript:void(0);" data-target="#template1-tab1">顶部菜单</a>
		</li>
		<li>
			<a data-toggle="tab" href="javascript:void(0);" data-target="#template1-tab2">首页海报</a>
		</li>
		<li>
			<a data-toggle="tab" href="javascript:void(0);" data-target="#template1-tab3">产品聚合</a>
		</li>
	</ul>
	<div class="clearfix"></div>
	<div class="grid-content">
		<div class="tab-content">
			<#include "/template1/tab1.ftl">
			<#include "/template1/tab2.ftl">
			<#include "/template1/tab3.ftl">
		</div>
	</div>
</div>