<#include "header.ftl">
<#assign Title='客户关系' />
<#include "header_top.ftl">
<#assign Menu=[{
	'name':'登录帐号',
	'url':'/crm/login/list.do'
},{
	'name':'数据导入',
	'sub':[{
		'name':'客户信息',
		'url':'/crm/import/customer.do'
	}]
}] />
<#include "sidebar.ftl">
<div id="main-content" class="clearfix" data-init="${ContextPath}/crm/login/list.do">
</div><!--/#main-content-->
<#include "footer.ftl">