<div id="maldives-island-hotel－tab" class="maldives-island-section">
	<div class="title">
		<h4>酒店详情</h4>
		<hr>
	</div>
	<div class="row-fluid">
		<div class="span6">
			<table class="table table-condensed">
				<tbody>
					<tr>
						<th style="width: 75px;">酒店集团：</th>
						<td>${island.hotelName!}</td>
					</tr>
					<#if island.hotelLevel?has_content>
					<tr>
						<th>酒店星级：</th>
						<td>${island.hotelLevel!}</td>
					</tr>
					</#if>
					<#if island.hotelStart?has_content>
					<tr>
						<th>开始营业：</th>
						<td>${island.hotelStart!}</td>
					</tr>
					</#if>
					<#if island.hotelRoomNum?has_content>
					<tr>
						<th>房间总数：</th>
						<td>${island.hotelRoomNum!}</td>
					</tr>
					</#if>
					<#if island.hotelSite?has_content>
					<tr>
						<th>官方网址：</th>
						<td>${island.hotelSite!}</td>
					</tr>
					</#if>
					<#if island.hotelTel?has_content>
					<tr>
						<th>电话：</th>
						<td>${island.hotelTel!}</td>
					</tr>
					</#if>
					<#if island.hotelFax?has_content>
					<tr>
						<th>传真：</th>
						<td>${island.hotelFax!}</td>
					</tr>
					</#if>
					<#if island.hotelChinese?has_content>
					<tr>
						<th>中文服务：</th>
						<td><#if island.hotelChinese>有<#else>无</#if></td>
					</tr>
					</#if>
					<#if island.hotelAirport?has_content>
					<tr>
						<th>机场柜台：</th>
						<td>${island.hotelAirport!}</td>
					</tr>
					</#if>
				</tbody>
			</table>
		</div>
		<div class="span6" data-toggle="modal-gallery" data-target="#maldives-gallery" data-selector="img">
			<#if island.hotelPictures?has_content>
			<#assign rand = springx.rand(0,island.hotelPictures?size-1) />
			<#list island.hotelPictures as image>
			<img alt="${image.title!}" src="${ContextPath}/image/343/280/${image.id}.${image.extension}"
				 data-href="${ContextPath}/image/${image.id}.${image.extension}"
				 class="<#if image_index!=rand>hide</#if>">
			</#list>
			</#if>
		</div>
	</div>
	<div class="row-fluid">
		<div class="span12">
			<table class="table table-condensed">
				<tbody><tr><td>${island.hotelDescription!}</td></tr></tbody>
			</table>
		</div>
	</div>
</div>