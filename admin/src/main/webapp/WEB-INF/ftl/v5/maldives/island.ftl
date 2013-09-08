<#assign Title='${island.zhName!}${island.enName!} 马代 马尔代夫 海岛 自由行' />
<#assign Description=island.description!?html />
<#assign Keywords=island.zhName! />
<#include "../header.ftl">
	<style type="text/css">
		<#include "island.css">
	</style>
	<div id="sidebar">
		<a class="btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
		</a>
		<#if (Settings.basic.logo)?has_content>
		<div id="logo">
			<a href="/" target="_blank">
				<#assign image=Settings.basic.logo>
				<img src="${ContextPath}/image/${image.id}.${image.extension}" width="150">
			</a>
		</div>
		</#if>
		<#if (Settings.basic.hotline)?has_content>
		<div id="hotline">
			<p class="phone">${Settings.basic.hotline}</p>
			<p class="tip"><i>7x24 小时，用心为您服务</i></p>
		</div>
		</#if>
		<nav id="nav" class="navigation" role="navigation">
			<ul class="unstyled">
				<li class="active" data-section="1"><i class="icon-cloud"></i> <span>岛屿详情</span></li>
				<li data-section="2" class=""><i class="icon-building"></i> <span>酒店详情</span></li>
				<li data-section="3" class=""><i class="icon-home"></i> <span>房型介绍</span></li>
				<li data-section="4" class=""><i class="icon-coffee"></i> <span>餐饮设施</span></li>
				<li data-section="5" class=""><i class="icon-suitcase"></i> <span>活动娱乐</span></li>
				<li data-section="6" class=""><i class="icon-tasks"></i> <span>本岛行程</span></li>
				<li data-section="7" class=""><i class="icon-cloud"></i> <span>其他岛屿</span></li>
			</ul>
		</nav><!-- /nav -->
	</div><!-- /sidebar -->
	<div id="container">

		<section id="section-poster" class="section"
			<#if island.pictures?has_content>
			<#assign image=island.pictures[0]>
			style="background-image:url(${ContextPath}/image/1600/1067/${image.id}.${image.extension});"
			</#if>
			data-section="1">
			<div class="container-fluid">	
				<div class="row-fluid">
					<div class="span6 intro">
						<h1>${island.zhName!}</h1>
						<h3>${island.enName!}</h3>
						<p>${island.description!}</p>
					</div>
				</div><!-- /row-fluid -->
			</div><!-- /container-fluid -->	
		</section><!-- /section -->

	</div>
<#include "../footer.ftl">