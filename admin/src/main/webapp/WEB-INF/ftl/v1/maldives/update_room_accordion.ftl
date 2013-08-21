<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div id="island-room-accordion" class="accordion">
	<#if island.rooms?has_content>
	<#list island.rooms as room>
	<div class="accordion-group">
		<div class="accordion-heading" style="position:relative;">
			<a class="accordion-toggle" data-toggle="collapse" data-parent="#island-room-accordion" href="#island-room-accordion-${room_index}">
			${room.zhName!} ${room.enName}
			</a>
			<button data-id="${room.id}" type="button" class="btn btn-danger btn-mini delete" style="position:absolute;right:30px;top:4px;">删除</button>
		</div>
		<#assign roomId=room.id />
		<#assign roomUrl="${ContextPath}/maldives/room/update.do" />
		<div id="island-room-accordion-${room_index}" class="accordion-body collapse <#if room_index==0>in</#if>" style="overflow:visible;">
			<div class="accordion-inner">
				<div class="row-fluid">
					<div class="span4">
						<div class="control-group">
							<label class="control-label">中文名称：</label>
							<div class="controls">
								<a href="#" class="editable" data-pk="${roomId}" data-name="zhName" data-type="text" data-url="${roomUrl}" data-value="${room.zhName!}"></a>
							</div>
						</div>
					</div>
					<div class="span4">
						<div class="control-group">
							<label class="control-label">英文名称：</label>
							<div class="controls">
								<a href="#" class="editable" data-pk="${roomId}" data-name="enName" data-type="text" data-url="${roomUrl}" data-value="${room.enName!}"></a>
							</div>
						</div>
					</div>
					<div class="span4">
						<div class="control-group">
							<label class="control-label">房间数量：</label>
							<div class="controls">
								<a href="#" class="editable" data-pk="${roomId}" data-name="num" data-type="text" data-url="${roomUrl}" data-value="${room.num!}"></a>
							</div>
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span8">
						<div class="control-group">
							<label class="control-label">入住要求：</label>
							<div class="controls">
								<a href="#" class="editable" data-pk="${roomId}" data-name="requirements" data-type="textarea" data-url="${roomUrl}" data-value="${room.requirements!}"></a>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">房间大小：</label>
							<div class="controls">
								<a href="#" class="editable" data-pk="${roomId}" data-name="roomSize" data-type="text" data-url="${roomUrl}" data-value="${room.roomSize!}"></a>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">包含泳池：</label>
							<div class="controls">
								<a href="#" class="editable" data-pk="${roomId}" data-name="containPool" data-type="select" data-url="${roomUrl}" data-value="<#if room.containPool?has_content><#if room.containPool>True<#else>False</#if></#if>"></a>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><a herf="#" class="editable-wysihtml5">房间设施：</a></label>
							<div class="controls">
								<div data-pk="${roomId}" data-name="roomFacility" data-type="wysihtml5" data-url="${roomUrl}" data-toggle="manual" data-original-title="编辑房间设施">
									${room.roomFacility!}
								</div>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><a herf="#" class="editable-wysihtml5">说明：</a></label>
							<div class="controls">
								<div data-pk="${roomId}" data-name="description" data-type="wysihtml5" data-url="${roomUrl}" data-toggle="manual" data-original-title="编辑房型说明">
									${room.description!}
								</div>
							</div>
						</div>
					</div>
					<div class="span4">
						<div class="control-group">
							<label class="control-label">显示顺序：</label>
							<div class="controls">
								<a href="#" class="editable" data-pk="${roomId}" data-name="displayOrder" data-type="text" data-url="${roomUrl}" data-value="${room.displayOrder!}"></a>
							</div>
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12">
						<div class="control-group">
							<label class="control-label">图片：</label>
							<div class="controls image-gallery">
								<button type="button" class="btn btn-primary btn-mini action action-add picture" data-name="roomId" data-value="${roomId}" data-url="${ContextPath}/maldives/room/picture/add.do">添加</button>
								<#if room.pictures?has_content>
								<div class="row-fluid">
									<ul class="thumbnails">
									<#list room.pictures as image>
										<li class="span3">
											<div class="thumbnail">
												<img src="${ContextPath}/image/${image.id}.${image.extension}">
												<div class="caption">
													<p><a href="#" class="editable" data-pk="${image_index}|${image.id}" data-name="title" data-type="text" data-value="${image.title!}" data-url="${ContextPath}/maldives/room/picture/update.do"></a></p>
													<p><a href="#" class="editable" data-pk="${image_index}|${image.id}" data-name="description" data-type="textarea" data-url="${ContextPath}/maldives/room/picture/update.do">${image.description!?html}</a></p>
													<button class="delete btn btn-danger" type="button" data-id="${image.id}" data-url="${ContextPath}/maldives/room/picture/delete.do">刪除</button>
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
		</div>
	</div>
	</#list>
	</#if>
</div>
<script type="text/javascript">
$('#island-room-accordion .accordion-heading button.delete').click(function(e) {
	var id = $(this).attr('data-id');
	var el = $($(this).parents('.accordion-group').get(0));
	bootbox.confirm('确认删除房型?', '取消', '确认', function(result) {
		if (result) {
			$.post('${ContextPath}/maldives/room/delete.do', {
				id: id
			}).done(function(){
				el.remove();
			}).fail(function() {
				cqlybest.error();
			});
		}
	});
});
$('#island-room-tab a[data-name="containPool"]').editable({
	prepend: '',
	source: [{value:'True',text:'是'},{value:'False',text:'否'}]
});
</script>