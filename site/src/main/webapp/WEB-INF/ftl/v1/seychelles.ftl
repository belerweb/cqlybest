<#include "header.ftl">
<#assign Title='塞舌尔' />
<#include "header_top.ftl">
<#assign Menu=[{
	'name':'海岛',
	'url':'/seychelles/list.do'
},{
	'name':'航班',
	'sub':[{
		'name':'航班',
		'url':'/seychelles/flight.do'
	},{
		'name':'航班组合',
		'url':'/seychelles/flight/comb.do'
	}]
}] />
<#include "sidebar.ftl">
<div id="main-content" class="clearfix" data-init="${ContextPath}/seychelles/list.do">
</div>
<#include "footer.ftl">