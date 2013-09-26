<div id="maldives-island-dining－tab" class="maldives-island-section">
	<div class="title">
		<h4>餐饮设施</h4>
		<hr>
	</div>
	<#if island.dinings?has_content>
	<#list island.dinings as dining>
	<div class="row-fluid">
		<div class="span6">
			<table class="table table-condensed">
				<colgroup>
					<col width="70" />
					<col width="" />
				</colgroup>
				<tbody>
					<tr style="background:#DFF0D8;">
						<th colspan="2">${dining.zhName!}${dining.enName!}</th>
					</tr>
					<#if dining.style?has_content>
					<tr>
						<th style="width: 75px;">环境风格：</th>
						<td>${dining.style}</td>
					</tr>
					</#if>
					<#if dining.food?has_content>
					<tr>
						<th style="width: 75px;">美食亮点：</th>
						<td>${dining.food}</td>
					</tr>
					</#if>
					<#if dining.openTime?has_content>
					<tr>
						<th style="width: 75px;">开放时间：</th>
						<td>${dining.openTime}</td>
					</tr>
					</#if>
					<#if dining.location?has_content>
					<tr>
						<th style="width: 75px;">就餐地点：</th>
						<td>${dining.location}</td>
					</tr>
					</#if>
					<#if dining.reservation?has_content>
					<tr>
						<th style="width: 75px;">是否预约：</th>
						<td><#if dining.reservation>需要预约<#else>无需预约</#if></td>
					</tr>
					</#if>
					<#if dining.description?has_content>
					<tr>
						<th style="width: 75px;">详细信息：</th>
						<td>${dining.description}</td>
					</tr>
					</#if>
				</tbody>
			</table>
		</div>
		<div class="span6" data-toggle="modal-gallery" data-target="#maldives-gallery" data-selector="img">
			<#if dining.pictures?has_content>
			<#assign rand = springx.rand(0,dining.pictures?size-1) />
			<#list dining.pictures as image>
			<img alt="${image.title!}" src="http://${ImageServer}/${image.qiniuKey}?343/280"
				 data-href="http://${ImageServer}/${image.qiniuKey}"
				 class="<#if image_index!=rand>hide</#if>">
			</#list>
			</#if>
		</div>
	</div>
	</#list>
	</#if>
</div>