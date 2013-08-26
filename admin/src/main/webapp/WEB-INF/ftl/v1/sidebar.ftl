<div id="sidebar" class="fixed">
	<div id="sidebar-shortcuts">
		<div id="sidebar-shortcuts-large">
			<a class="btn btn-small btn-success" href="${ContextPath}/home.do" title="首页">
				<i class="icon-home"></i>
			</a>
			<a class="btn btn-small btn-info" href="${ContextPath}/maldives.do" title="马尔代夫">
				<i class="icon-anchor"></i>
			</a>
			<a class="btn btn-small btn-danger" href="${ContextPath}/maldives.do" title="客户关系">
				<i class="icon-group"></i>
			</a>
		</div>

		<div id="sidebar-shortcuts-mini">
			<span class="btn btn-success"></span>
			<span class="btn btn-info"></span>
			<span class="btn btn-danger"></span>
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
	</ul><!--/.nav-list-->

	<div id="sidebar-collapse">
		<i class="icon-double-angle-left"></i>
	</div>
</div>