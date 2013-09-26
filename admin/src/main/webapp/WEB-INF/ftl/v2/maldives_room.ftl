<div id="maldives-island-room－tab" class="maldives-island-section">
	<div class="title">
		<h4>房型介绍</h4>
		<hr>
	</div>
	<#if island.rooms?has_content>
	<#list island.rooms as room>
	<table class="table table-condensed">
		<colgroup>
			<col width="70" />
			<col width="" />
		</colgroup>
		<tbody>
			<tr style="background:#DFF0D8;">
				<th colspan="2">${room.zhName!}${room.enName!} <#if room.num?has_content><span class="label label-success">${room.num}间</span></#if></th>
			</tr>
			<tr>
				<th style="width: 75px;">入住要求：</th>
				<td>${room.requirements!}</td>
			</tr>
			<tr>
				<th style="width: 75px;">房间设施：</th>
				<td>${room.roomFacility!}</td>
			</tr>
			<tr>
				<th style="width: 75px;">详细信息：</th>
				<td>${room.description!}</td>
			</tr>
		</tbody>
	</table>
	<#if room.pictures?has_content>
	<div class="room-gallery" data-toggle="modal-gallery" data-target="#maldives-gallery" data-selector="a">
		<#if room.pictures?size==1>
		<#list room.pictures as image>
		<a href="http://${ImageServer}/${image.qiniuKey}" title="${image.title!}"><img src="http://${ImageServer}/${image.qiniuKey}?imageView/1/w/696/h/400"></a>
		</#list>
		</#if>
		<#if room.pictures?size==2>
		<#list room.pictures as image>
		<a href="http://${ImageServer}/${image.qiniuKey}" title="${image.title!}"><img src="http://${ImageServer}/${image.qiniuKey}?imageView/1/w/347/h/200"></a>
		</#list>
		</#if>
		<#if room.pictures?size==3>
		<#list room.pictures as image>
		<a href="http://${ImageServer}/${image.qiniuKey}" title="${image.title!}"><img src="http://${ImageServer}/${image.qiniuKey}?imageView/1/w/230/h/170"></a>
		</#list>
		</#if>
		<#if room.pictures?size==4>
		<#list room.pictures as image>
		<a href="http://${ImageServer}/${image.qiniuKey}" title="${image.title!}"><img src="http://${ImageServer}/${image.qiniuKey}?imageView/1/w/173/h/130"></a>
		</#list>
		</#if>
		<#if room.pictures?size gt 4>
		<#list room.pictures as image>
		<a href="http://${ImageServer}/${image.qiniuKey}" title="${image.title!}"><img src="http://${ImageServer}/${image.qiniuKey}?imageView/1/w/137/h/105"></a>
		</#list>
		</#if>
		<div class="clearfix"> </div>
	</div>
	</#if>
	</#list>
	</#if>
</div>