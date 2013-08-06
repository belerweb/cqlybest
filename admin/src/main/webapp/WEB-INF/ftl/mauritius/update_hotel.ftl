<div id="hotel-hotel-tab" class="tab-pane">
	<div class="row-fluid">
		<div class="span6">
			<div class="control-group">
				<label class="control-label">酒店集团：</label>
				<div class="controls">
					<a href="#" class="editable" data-pk="${id}" data-name="hotelName" data-type="text" data-url="${url}" data-value="${hotel.hotelName!}"></a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">开始营业：</label>
				<div class="controls">
					<a href="#" class="editable" data-pk="${id}" data-name="hotelStart" data-type="text" data-url="${url}" data-value="${hotel.hotelStart!}"></a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">官方网址：</label>
				<div class="controls">
					<a href="#" class="editable" data-pk="${id}" data-name="hotelSite" data-type="text" data-url="${url}" data-value="${hotel.hotelSite!}"></a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">电话：</label>
				<div class="controls">
					<a href="#" class="editable" data-pk="${id}" data-name="hotelTel" data-type="text" data-url="${url}" data-value="${hotel.hotelTel!}"></a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">传真：</label>
				<div class="controls">
					<a href="#" class="editable" data-pk="${id}" data-name="hotelFax" data-type="text" data-url="${url}" data-value="${hotel.hotelFax!}"></a>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="span6">
			<div class="control-group">
				<label class="control-label">酒店星级：</label>
				<div class="controls">
					<a href="#" class="editable" data-pk="${id}" data-name="hotelLevel" data-type="select" data-url="${url}" data-value="${hotel.hotelLevel!}"></a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">房间总数：</label>
				<div class="controls">
					<a href="#" class="editable" data-pk="${id}" data-name="hotelRoomNum" data-type="text" data-url="${url}" data-value="${hotel.hotelRoomNum!}"></a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">中文服务：</label>
				<div class="controls">
					<a href="#" class="editable" data-pk="${id}" data-name="hotelChinese" data-type="select" data-url="${url}" data-value="<#if hotel.hotelChinese?has_content><#if hotel.hotelChinese>True<#else>False</#if></#if>"></a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">机场柜台号：</label>
				<div class="controls">
					<a href="#" class="editable" data-pk="${id}" data-name="hotelAirport" data-type="text" data-url="${url}" data-value="${hotel.hotelAirport!}"></a>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
	<div class="row-fluid">
		<div class="span12">
			<div class="control-group">
				<label class="control-label"><a herf="#" class="editable-wysihtml5">详细信息：</a></label>
				<div class="controls">
					<div data-pk="${id}" data-name="hotelDescription" data-type="wysihtml5" data-url="${url}" data-toggle="manual" data-original-title="编辑详细信息">
						${hotel.hotelDescription!}
					</div>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
	<div class="row-fluid">
		<div class="span12">
			<div class="control-group">
				<label class="control-label">图片：</label>
				<div class="controls image-gallery">
					<button type="button" data-extra="mauritius-hotel-picture" data-extra-key="${hotel.id}" class="btn btn-primary action action-add picture">添加</button>
					<#if hotel.hotelPictures?has_content>
					<div class="row-fluid">
						<ul class="thumbnails">
						<#list hotel.hotelPictures as image>
							<li class="span3">
								<div class="thumbnail">
									<img src="${ContextPath}/image/${image.id}.${image.imageType}">
									<div class="caption">
										<p><a href="#" class="title editable-click <#if !image.title?has_content>editable-empty</#if>" data-pk="${image.id}" data-name="title" data-type="text" data-value="${image.title!}">${image.title!'标题：未设置'}</a></p>
										<p><a href="#" class="description editable-click <#if !image.description?has_content>editable-empty</#if>" data-pk="${image.id}" data-name="description" data-type="textarea">${image.description!'描述：未设置'}</a></p>
										<button class="delete btn btn-danger" type="button" data-id="${image.id}">刪除</button>
									</div>
								</div>
							</li>
							<#if image_index%4==3>
						</ul>
					</div>
					<div class="row-fluid">
						<ul class="thumbnails">
							</#if>
						</#list>
						</ul>
					</div>
					</#if>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
$('#hotel-hotel-tab a[data-name="hotelLevel"]').editable({
	prepend: '',
	source: function() {
		var levels = [];
		for(var i=1;i<=10;i++) levels.push({value:i,text:i});
		return levels;
	}
});
$('#hotel-hotel-tab a[data-name="hotelChinese"]').editable({
	prepend: '',
	source: [{value:'True',text:'有'},{value:'False',text:'无'}]
});
</script>
