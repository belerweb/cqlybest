<#include "header.ftl">
<#assign Title='毛里求斯' />
<#include "header_top.ftl">
<#assign Menu=[{
	'name':'酒店',
	'url':'/mauritius/list.do'
},{
	'name':'航班',
	'url':'/mauritius/flight.do'
}] />
<#include "sidebar.ftl">
<div id="main-content" class="clearfix" data-init="${ContextPath}/mauritius/list.do">
</div><!--/#main-content-->
<#include "footer.ftl">