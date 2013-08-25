<#include "header.ftl">
<#assign Title='系统管理' />
<#include "header_top.ftl">
<#assign Menu=[{
	'name':'系统设置',
	'url':'/system/settings.do'
},{
	'name':'数据字典',
	'sub':[{
		'name':'标签',
		'url':'/datadict/list.do?type=tag'
	},{
		'name':'出发城市',
		'url':'/datadict/list.do?type=departure-city'
	},{
		'name':'目的地',
		'url':'/datadict/list.do?type=destination'
	},{
		'name':'交通方式',
		'url':'/datadict/list.do?type=traffic'
	},{
		'name':'产品类型',
		'url':'/datadict/list.do?type=product-type'
	},{
		'name':'产品等级',
		'url':'/datadict/list.do?type=product-grade'
	}]
},{
	'name':'系统信息',
	'url':'/system/info.do'
}] />
<#include "sidebar.ftl">
<div id="main-content" class="clearfix" data-init="${ContextPath}/datadict/list.do?type=tag">
</div><!--/#main-content-->
<#include "footer.ftl">