<#assign Title="毛里求斯自由行 ${(Settings.basic.siteName)!}">
<#assign Description="毛里求斯自由行 ${(Settings.basic.description)!}">
<#include "../index/header.ftl">
<#include "../index/menu.ftl">
<style type="text/css">
	<#include "main.css">
</style>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span9">
			<div class="headline"><h3>毛里求斯酒店</h3></div>
			<div class="row-fluid items">
				<ul class="thumbnails">
				<#assign index=0>
				<#list result.items as item>
					<#if item.hotelPictures?has_content>
					<#if index%3==0>
				</ul>
			</div>
			<div class="row-fluid items">
				<ul class="thumbnails">
					</#if>
					<li class="span4">
						<div class="thumbnail-style thumbnail-kenburn border">
							<h3><a <@mqlink item 'zhName' /> class="hover-effect">${item.zhName!}</a></h3>
							<h4><a <@mqlink item 'enName' /> class="hover-effect">${item.enName!}</a></h4>
							<div class="thumbnail-img">
								<div class="overflow-hidden">
									<a <@mqlink item 'id' /> ><img alt="<@mqalt item />" src="<@randImage item.hotelPictures 'thumbnail' />"></a>
								</div>
								<a <@mqlink item 'enName' /> class="btn-more hover-effect">查看详细 +</a>
							</div>
							<#if item.tags?has_content>
							<ul class="tags">
							<#list item.tags?split(',') as tag>
								<li class="bg${tag_index%7}">${tag}</li>
							</#list>
							</ul>
							<div class="clearfix"> </div>
							</#if>
						</div>
					</li>
					<#assign index=index+1>
					</#if>
				</#list>
				</ul>
			</div>
		</div>
		<div class="span3">
			<div class="travel">
				<div class="headline"><h3>毛里求斯行程</h3></div>
			</div>
		</div>
	</div>
</div>

<#include "../index/copyright.ftl">
<script type="text/javascript">
	window.PageContext = {
		fixCopyright: false,
		activeNav: 'mauritius',
		init: function(){
		}
	};
</script>
<#include "../index/footer.ftl">