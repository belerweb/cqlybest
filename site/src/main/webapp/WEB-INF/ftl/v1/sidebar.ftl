<div id="sidebar" class="fixed">
	<div id="sidebar-shortcuts">
		<div id="sidebar-shortcuts-large">
			<a class="btn btn-small btn-success" href="${ContextPath}/home.do" title="首页"><i class="icon-home"></i></a>
			<a class="btn btn-small btn-inverse" href="${ContextPath}/maldives.do" title="马尔代夫">马</a>
			<a class="btn btn-small btn-info" href="${ContextPath}/mauritius.do" title="毛里求斯">毛</a>
			<a class="btn btn-small btn-danger" href="${ContextPath}/seychelles.do" title="塞舌尔">塞</a>
			<a class="btn btn-small btn-purple" href="${ContextPath}/system.do" title="系统管理"><i class="icon-cog"></i></a>
			<a class="btn btn-small btn-grey" href="${ContextPath}/admin/example.do" title="标杆">标</a>
			<a class="btn btn-small btn-warning" href="${ContextPath}/crm.do" title="客户关系管理">客</a>
			<a class="btn btn-small btn-pink" href="${ContextPath}/www.do" title="PC 版网站管理">网</a>
		</div>
	</div>
	<ul class="nav nav-list">
		<#if Menu?has_content>
		<#list Menu as menu>
		<li>
			<#if menu.sub?has_content>
			<a href="#" class="dropdown-toggle">
				<i class="${menu.icon!'icon-list'}"></i>
				<span>${menu.name}</span>
				<b class="arrow icon-angle-down"></b>
			</a>
			<ul class="submenu">
				<#list menu.sub as menu>
				<li>
					<a href="${ContextPath}${menu.url}">
						<i class="icon-double-angle-right"></i> ${menu.name}
					</a>
				</li>
				</#list>
			</ul>
			<#else>
			<a href="${ContextPath}${menu.url}">
				<i class="${menu.icon!'icon-list'}"></i>
				<span>${menu.name}</span>
			</a>
			</#if>
		</li>
		</#list>
		</#if>
	</ul>
</div>