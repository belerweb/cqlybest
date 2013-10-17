<div class="top">
	<div class="container-fluid">
		<div class="row-fluid">
			<ul class="loginbar inline">
				<#if (Settings.basic.weibo.id)?has_content>
				<li class="pull-left"><wb:follow-button uid="${Settings.basic.weibo.id}" type="red_1" width="67" height="24" ></wb:follow-button></li>
				</#if>
				<li data-display="nosession" style="display:none;"><span>亲，欢迎访问${(Settings.basic.siteName)!}！</span></li>
				<li data-display="nosession" style="display:none;"><a href="${ContextPath}/connector/qq_login" class="mg"><img alt="QQ登录" src="${ContextPath}/assets/v2/img/connector/Connect_logo_7.png" width="63" height="24"></a></li>
				<li data-display="nosession" style="display:none;"><a href="${ContextPath}/connector/weibo_login" class="mg" title="使用新浪微博登录并关注我们的官方微博"><img alt="用微博登录" src="${ContextPath}/assets/v2/img/connector/loginButton_24.png" width="102" height="24"></a></li> 
				<li data-display="session" style="display:none;">
					<span data-text="top-nickname"></span>
				</li>
				<!-- li data-display="session" style="display:none;"><a href="${ContextPath}/home" title="查看订单、修改资料...">用户中心</a></li --> 
				<li data-display="session" style="display:none;"><a href="${ContextPath}/logout" title="退出登录">退出</a></li> 
			</ul>
		</div>
	</div>
</div>
<div class="header">
	<div class="container-fluid">
		<div class="logo">
			<a href="/"><#if (Settings.basic.logo)?has_content><img src="http://${ImageServer}/${Settings.basic.logo.qiniuKey}" height="60" style="height:60px;"><#else>${(Settings.basic.siteName)!}</#if></a>
		</div>
		<div class="navbar pull-left" style="margin-left:40px;">
			<div class="navbar-inner">
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li data-active-nav="index">
							<a href="${ContextPath}/" class="dropdown-toggle">首页</a>
						</li>
						<li data-active-nav="private">
							<a href="${ContextPath}/soon.html" class="dropdown-toggle">私属定制</a>
						</li>
						<li data-active-nav="business">
							<a href="${ContextPath}/soon.html" class="dropdown-toggle">公商务定制</a>
						</li>
						<li data-active-nav="theme">
							<a href="${ContextPath}/soon.html" class="dropdown-toggle">主题定制</a>
						</li>
						<li data-active-nav="example">
							<a href="${ContextPath}/soon.html" class="dropdown-toggle">企业考察</a>
						</li>
						<li data-active-nav="maldives">
							<a href="${ContextPath}/maldives.html" class="dropdown-toggle">马尔代夫</a>
						</li>
						<li data-active-nav="mauritius">
							<a href="${ContextPath}/mauritius.html" class="dropdown-toggle">毛里求斯</a>
						</li>
						<li data-active-nav="seychelles">
							<a href="${ContextPath}/seychelles.html" class="dropdown-toggle">塞舌尔</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="navbar pull-right">
			<div class="navbar-inner">
				<div class="nav-collapse collapse">
					<ul class="nav">
						<#if (Settings.basic.hotline)?has_content>
						<li><a class="hotline"><i class="icon-phone"></i> ${Settings.basic.hotline}</a></li>
						</#if>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
