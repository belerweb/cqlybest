<#include "/v2/header.ftl">
<#include "/v2/macro/macro.ftl">
<div class="container margin-menu">
	<#assign island_index_with_pictures=0>
	<#list result.items as island>
	<#if island.hotelPictures?has_content>
	<#if island_index_with_pictures%3==0>
	<div class="row-fluid islands">
		<ul class="thumbnails">
	</#if>
			<li class="span4">
				<#assign link="${ContextPath}/maldives/${island.id}.html">
				<div class="thumbnail">
					<#if island.hotelLevel?has_content><span class="start start-${island.hotelLevel}">${island.hotelLevel}星</span></#if>
					<a title="${island.zhName!}|${island.enName!}" href="${link}">
						<h4 class="zh">${island.zhName!}</h4>
					</a>
					<a title="${island.zhName!}|${island.enName!}" href="${link}">
						<h4 class="en">${island.enName!}</h4>
					</a>
					<a title="${island.zhName!}|${island.enName!}" href="${link}" class="img">
						<img alt="" src="<@getOneImageUrl2 island.hotelPictures />?width=290&height=150">
					</a>
					<div class="caption">
						<#if island.tags?has_content>
						<#assign colors=['#87C116','#00AFF1','#0BA6FF','#FC8E00','#04B1E0','#FFC400','#1D86C9']>
						<ul class="tags">
						<#list island.tags?split(',') as tag>
							<li style="background-color:${colors[tag_index%colors?size]}">${tag}</li>
						</#list>
						</ul>
						<div class="clearfix"> </div>
						</#if>
					</div>
				</div>
			</li>
	<#if island_index_with_pictures%3==2>
		</ul>
	</div>
	</#if>
	<#assign island_index_with_pictures=island_index_with_pictures+1>
	</#if>
	</#list>
</div>
<script type="text/javascript">
	var PageContext = {
		init: function() {
		}
	};
</script>
<#include "/v2/footer.ftl">