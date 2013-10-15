<#include "../index/header.ftl">
<#include "../index/menu.ftl">
<style type="text/css">
	<#include "main.css">
</style>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span9">
			<div class="headline"><h3>全部海岛</h3></div>
			<div class="row-fluid islands">
				<ul class="thumbnails">
				<#assign index=0>
				<#list result.items as island>
					<#if island.hotelPictures?has_content>
					<#if index%3==0>
				</ul>
			</div>
			<div class="row-fluid islands">
				<ul class="thumbnails">
					</#if>
					<li class="span4">
						<div class="thumbnail-style thumbnail-kenburn border">
							<h3><a <@mdlink island 'zhName' /> class="hover-effect">${island.zhName!}</a></h3>
							<h4><a <@mdlink island 'enName' /> class="hover-effect">${island.enName!}</a></h4>
							<div class="thumbnail-img">
								<div class="overflow-hidden">
									<a <@mdlink island 'id' /> ><img <@mdalt island /> src="<@randImage island.hotelPictures 290 150 />"></a>
								</div>
								<a <@mdlink island 'enName' /> class="btn-more hover-effect">查看详细 +</a>
							</div>
							<#if island.tags?has_content>
							<ul class="tags">
							<#list island.tags?split(',') as tag>
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
				<div class="headline"><h3>马代自由行</h3></div>
			</div>
		</div>
	</div>
</div>

<#include "../index/copyright.ftl">
<script type="text/javascript">
	window.PageContext = {
		fixCopyright: false,
		activeNav: 'maldives',
		init: function(){
		}
	};
</script>
<#include "../index/footer.ftl">