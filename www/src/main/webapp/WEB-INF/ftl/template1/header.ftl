<#include "/template1/header_top.ftl">
<div id="top-logo" class="container text-right">
	<div class="pull-left"><img src="${ContextPath}/template1/img/logo.png"></div>
	<div class="pull-right">
		<div class="phone"><span><img src="${ContextPath}/template1/img/contact.png">400-023-9761</span></div>
		<div class="phone-tip"><i>7x24 小时，用心为您服务</i></div>
	</div>
</div>
<div id="menu" class="navbar">
	<div class="navbar-inner">
		<div class="container">
			<div class="nav-collapse collapse">
				<ul class="nav">
					<li data-active="index"><a href="${ContextPath}/index.html">首页</a></li>
					<#list Menu as menu>
					<#if menu.menuType==0>
					<li data-active="${menu.id}"><a <#if menu.newWindow>target="_blank"</#if> href="${ContextPath}/group/${menu.id}.html">${menu.name}</a></li>
					<#elseif menu.menuType==1>
					<li data-active="${menu.id}"><a <#if menu.newWindow>target="_blank"</#if> href="${ContextPath}/page/${menu.id}.html">${menu.name}</a></li>
					<#elseif menu.menuType==2>
					<li><a <#if menu.newWindow>target="_blank"</#if> href="${menu.url}">${menu.name}</a></li>
					</#if>
					</#list>
				</ul>
			</div>
		</div>
	</div>
</div>