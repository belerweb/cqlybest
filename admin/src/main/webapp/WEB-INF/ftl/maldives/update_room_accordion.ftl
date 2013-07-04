<#assign ContextPath=springMacroRequestContext.getContextPath() />
<style type="text/css">
#island-room-accordion .in.collapse, .tab-content {
	overflow: visible;
}
</style>
<div id="island-room-accordion" class="accordion">
	<#if rooms?exists>
	<#list rooms as room>
	<div class="accordion-group">
		<div class="accordion-heading">
			<a class="accordion-toggle" data-toggle="collapse" data-parent="#island-room-accordion" href="#island-room-accordion-${room_index}">
			${room.zhName!} ${room.enName}
			</a>
		</div>
		<#assign roomId=room.id />
		<#assign roomUrl="${ContextPath}/maldives/room/update.do" />
		<div id="island-room-accordion-${room_index}" class="accordion-body collapse <#if room_index==0>in</#if>">
			<div class="accordion-inner">
				<div class="row-fluid">
					<div class="span3">
						<div class="control-group">
							<label class="control-label">中文名称：</label>
							<div class="controls">
								<a href="#" class="editable" data-pk="${roomId}" data-name="zhName" data-type="text" data-url="${roomUrl}" data-value="${(room.zhName!)?html}"></a>
							</div>
						</div>
					</div>
					<div class="span3">
						<div class="control-group">
							<label class="control-label">英文名称：</label>
							<div class="controls">
								<a href="#" class="editable" data-pk="${roomId}" data-name="enName" data-type="text" data-url="${roomUrl}" data-value="${(room.enName!)?html}"></a>
							</div>
						</div>
					</div>
					<div class="span3">
						<div class="control-group">
							<label class="control-label">房间数量：</label>
							<div class="controls">
								<a href="#" class="editable" data-pk="${roomId}" data-name="num" data-type="text" data-url="${roomUrl}" data-value="${(room.num!)?html}"></a>
							</div>
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12">
						<div class="control-group">
							<label class="control-label">入住要求：</label>
							<div class="controls">
								<a href="#" class="editable" data-pk="${roomId}" data-name="requirements" data-type="textarea" data-url="${roomUrl}" data-value="${(room.requirements!)?html}"></a>
							</div>
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12">
						<div class="control-group">
							<label class="control-label">房间设施：</label>
							<div class="controls">
								<a href="#" class="editable" data-pk="${roomId}" data-name="roomFacility" data-type="textarea" data-url="${roomUrl}" data-value="${(room.roomFacility!)?html}"></a>
							</div>
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12">
						<div class="control-group">
							<label class="control-label">说明：</label>
							<div class="controls">
								<div data-pk="${roomId}" data-name="description" data-type="wysihtml5" data-toggle="manual" data-original-title="编辑房型说明">
									${room.description!}
								</div>
								<button type="button" class="btn btn-success">编辑</button>
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
$('#island-room-accordion a[data-name=zhName]').editable({});
$('#island-room-accordion a[data-name=enName]').editable({});
$('#island-room-accordion a[data-name=num]').editable({});
$('#island-room-accordion a[data-name=requirements]').editable({});
$('#island-room-accordion a[data-name=roomFacility]').editable({});
$('#island-room-accordion div[data-name=description]').editable({});
$('#island-room-accordion div[data-name=description]').next().click(function(e) {
	e.stopPropagation();
	e.preventDefault();
	$(this).prev().editable('toggle');
});
</script>