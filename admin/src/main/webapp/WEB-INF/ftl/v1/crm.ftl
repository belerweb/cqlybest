<#include "header.ftl">
<#assign Title='客户关系' />
<#assign TopNav=[{
	'name':'马尔代夫',
	'url':'/maldives.do'
}] />
<#include "header_top.ftl">
<#assign Menu=[{
	'name':'登录帐号',
	'url':'/crm/login/list.do'
}] />
<#include "sidebar.ftl">
<div id="main-content" class="clearfix" data-init="${ContextPath}/crm/login/list.do">
</div><!--/#main-content-->
<#include "footer.ftl">