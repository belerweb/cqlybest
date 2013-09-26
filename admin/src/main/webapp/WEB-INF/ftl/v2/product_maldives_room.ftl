<div id="maldives-island-room－tab" class="maldives-island-section">
	<div class="title">
		<h4>详细行程</h4>
	</div>
	<#if product.maldivesDetails?has_content>
	<#list product.maldivesDetails as maldives>
	<div class="row-fluid product-room">
		<div class="span2">
			<div class="day well well-small">${maldives.name!}</div>
		</div>
		<div class="span10">
		<#if !(maldives.island?has_content && maldives.room?has_content)>
			${maldives.detail!}
		<#else>
			<#assign room=maldives.room>
			<table class="table table-condensed">
				<colgroup>
					<col width="75" />
					<col width="" />
				</colgroup>
				<tbody>
					<#assign island=maldives.island>
					<tr>
						<th colspan="2" class="name">${island.zhName!}${island.enName!} <br> ${room.zhName!}${room.enName!}</th>
					</tr>
					<#if maldives.meal?has_content>
					<tr>
						<th>用餐：</th>
						<td>${maldives.meal!}</td>
					</tr>
					</#if>
					<#if maldives.extra?has_content>
					<tr>
						<th>详细行程：</th>
						<td>${maldives.extra}</td>
					</tr>
					</#if>
					<#if room.requirements?has_content>
					<tr>
						<th>入住限制：</th>
						<td>${room.requirements}</td>
					</tr>
					</#if>
					<#if room.description?has_content>
					<tr>
						<th>房间简介：</th>
						<td>${room.description}</td>
					</tr>
					</#if>
					<#if room.roomFacility?has_content>
					<tr>
						<th>房间设施：</th>
						<td>${room.roomFacility}</td>
					</tr>
					</#if>
				</tbody>
			</table>
			<#if room.pictures?has_content>
			<div class="room-gallery" data-toggle="modal-gallery" data-target="#product-gallery">
				<#list room.pictures as image>
				<a data-gallery="gallery" href="http://${ImageServer}/${image.qiniuKey} title="${image.title!}"><img src="http://${ImageServer}/${image.qiniuKey}?100/100"></a>
			</#list>
			</div>
			</#if>
		</#if>
		</div>
	</div>
	</#list>
	</#if>
</div>