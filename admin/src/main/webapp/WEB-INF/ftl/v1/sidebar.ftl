<div id="sidebar" class="fixed">
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