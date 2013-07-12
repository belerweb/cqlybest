<div id="maldives-island-play－tab" class="maldives-island-section maldives-play-gallery">
	<div class="title">
		<h4>娱乐设施</h4>
		<hr>
	</div>
	<div data-toggle="modal-gallery" data-target="#maldives-gallery" data-selector="div.gallery-item">
		<#if island.plays?has_content>
		<#list island.plays as image>
		<div class="gallery-item<#if image_index%6==0> row-first</#if>"
			data-href="${ContextPath}/image/${image.id}.${image.imageType}" title="${image.title!}">
			<img src="${ContextPath}/image/${image.id}.${image.imageType}?width=108&height=108">
		</div>
		</#list>
		</#if>
	</div>
</div>