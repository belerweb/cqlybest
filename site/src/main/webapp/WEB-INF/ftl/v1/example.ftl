<#include "header.ftl">
<#assign Title='标杆' />
<#include "header_top.ftl">
<#assign Menu=[{
	'name':'标杆介绍',
	'url':'/admin/example/description.do'
},{
	'name':'标杆企业',
	'url':'/admin/example/company.do'
},{
	'name':'成功案例',
	'url':'/admin/example/case.do'
}] />
<#include "sidebar.ftl">
<div id="main-content" class="clearfix" data-init="${ContextPath}/admin/example/company.do">
</div>
<#include "footer.ftl">