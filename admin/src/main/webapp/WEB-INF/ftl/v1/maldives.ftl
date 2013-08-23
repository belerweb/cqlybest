<#include "header.ftl">
<#assign Title='马尔代夫' />
<#include "header_top.ftl">
<#assign Menu=[{
	'name':'海岛',
	'url':'/maldives/list.do'
},{
	'name':'航班',
	'url':'/maldives/flight.do'
},{
	'name':'产品',
	'url':'/maldives/product.do'
}] />
<#include "sidebar.ftl">
<div id="main-content" class="clearfix" data-init="${ContextPath}/maldives/list.do">
</div><!--/#main-content-->
<#include "footer.ftl">