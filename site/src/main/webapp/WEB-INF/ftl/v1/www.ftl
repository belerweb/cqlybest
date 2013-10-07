<#include "header.ftl">
<#assign Title='PC 版网站管理' />
<#include "header_top.ftl">
<#assign Menu=[{
	'name':'首页',
	'url':'/www/index.do'
},{
	'name':'友情链接',
	'url':'/www/friendlylink.do'
}] />
<#include "sidebar.ftl">
<div id="main-content" class="clearfix" data-init="${ContextPath}/www/index.do">
</div><!--/#main-content-->
<#include "footer.ftl">