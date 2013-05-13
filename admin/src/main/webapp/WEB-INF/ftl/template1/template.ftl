<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div id="template1" class="grid">
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
			<div id="template1-tab1" class="tab-pane fade active in"><#include "/template1/menu.ftl"></div>
			<div id="template1-tab2" class="tab-pane fade"><#include "/template1/poster.ftl"></div>
			<div id="template1-tab3" class="tab-pane fade"><#include "/template1/product_group.ftl"></div>
		</div>
	</div>
</div>
<script>
var target = cqlybest.parseHash().dt||'';
if (target != '') {
	$('#template1 a[data-target=' + target + ']').tab('show');
}
</script>
