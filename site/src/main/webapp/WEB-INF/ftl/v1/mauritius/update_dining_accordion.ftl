<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div id="hotel-dining-accordion" class="accordion">
	<#if hotel.dinings?exists>
	<#list hotel.dinings as dining>
	<div class="accordion-group">
		<div class="accordion-heading" style="position:relative;">
			<a class="accordion-toggle" data-toggle="collapse" data-parent="#hotel-dining-accordion" href="#hotel-dining-accordion-${dining_index}">
			${dining.zhName!} ${dining.enName!}
			</a>
			<button data-id="${dining.id}" type="button" class="btn btn-danger btn-mini delete" style="position:absolute;right:30px;top:4px;">删除</button>
		</div>
		<#assign diningId=dining.id />
		<#assign diningUrl="${ContextPath}/mauritius/dining/update.do" />
		<div id="hotel-dining-accordion-${dining_index}" class="accordion-body collapse <#if dining_index==0>in</#if>" style="overflow:visible;">
			<div class="accordion-inner">
				<div class="row-fluid">
					<div class="span6">
						<div class="control-group">
							<label class="control-label">中文名称：</label>
							<div class="controls">
								<a href="#" class="editable" data-pk="${diningId}" data-name="zhName" data-type="text" data-url="${diningUrl}" data-value="${dining.zhName!}"></a>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">英文名称：</label>
							<div class="controls">
								<a href="#" class="editable" data-pk="${diningId}" data-name="enName" data-type="text" data-url="${diningUrl}" data-value="${dining.enName!}"></a>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">环境风格：</label>
							<div class="controls">
								<a href="#" class="editable" data-pk="${diningId}" data-name="style" data-type="text" data-url="${diningUrl}" data-value="${dining.style!}"></a>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">美食亮点：</label>
							<div class="controls">
								<a href="#" class="editable" data-pk="${diningId}" data-name="food" data-type="text" data-url="${diningUrl}" data-value="${dining.food!}"></a>
							</div>
						</div>
					</div>
					<div class="span6">
						<div class="control-group">
							<label class="control-label">开放时间：</label>
							<div class="controls">
								<a href="#" class="editable" data-pk="${diningId}" data-name="openTime" data-type="text" data-url="${diningUrl}" data-value="${dining.openTime!}"></a>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">就餐位置：</label>
							<div class="controls">
								<a href="#" class="editable" data-pk="${diningId}" data-name="location" data-type="text" data-url="${diningUrl}" data-value="${dining.location!}"></a>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">是否预约：</label>
							<div class="controls">
								<a href="#" class="editable" data-pk="${diningId}" data-name="reservation" data-type="select" data-url="${diningUrl}" data-value="<#if dining.reservation?has_content><#if dining.reservation>True<#else>False</#if></#if>"></a>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">显示顺序：</label>
							<div class="controls">
								<a href="#" class="editable" data-pk="${diningId}" data-name="displayOrder" data-type="text" data-url="${diningUrl}" data-value="${dining.displayOrder!}"></a>
							</div>
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12">
						<div class="control-group">
							<label class="control-label"><a herf="#" class="editable-wysihtml5">说明：</a></label>
							<div class="controls">
								<div data-pk="${diningId}" data-name="description" data-type="wysihtml5" data-url="${diningUrl}" data-toggle="manual" data-original-title="编辑房型说明">
									${dining.description!}
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span12">
						<div class="control-group">
							<label class="control-label">图片：</label>
							<div class="controls image-gallery">
								<button type="button" class="btn btn-primary btn-mini action action-add picture" data-name="diningId" data-value="${diningId}" data-url="${ContextPath}/mauritius/dining/picture/add.do">添加</button>
								<#if dining.pictures?has_content>
								<div class="row-fluid">
									<ul class="thumbnails">
									<#list dining.pictures as image>
										<li class="span3">
											<div class="thumbnail">
												<img src="http://${ImageServer}/${image.qiniuKey}-gallery">
												<div class="caption">
													<p><a href="#" class="editable" data-pk="${image_index}|${image.id}" data-name="title" data-type="text" data-value="${image.title!}" data-url="${ContextPath}/mauritius/dining/picture/update.do"></a></p>
													<p><a href="#" class="editable" data-pk="${image_index}|${image.id}" data-name="description" data-type="textarea" data-url="${ContextPath}/mauritius/dining/picture/update.do">${image.description!?html}</a></p>
													<button class="delete btn btn-danger" type="button" data-id="${image.id}" data-url="${ContextPath}/mauritius/dining/picture/delete.do">刪除</button>
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
$('#hotel-dining-accordion .accordion-heading button.delete').click(function(e) {
	var id = $(this).attr('data-id');
	var el = $($(this).parents('.accordion-group').get(0));
	bootbox.confirm('确认删除餐饮设施?', '取消', '确认', function(result) {
		if (result) {
			$.post('${ContextPath}/mauritius/dining/delete.do', {
				id: id
			}).done(function(){
				el.remove();
			}).fail(function() {
				cqlybest.error();
			});
		}
	});
});
$('#hotel-dining-tab a[data-name="reservation"]').editable({
	prepend: '',
	source: [{value:'True',text:'是'},{value:'False',text:'否'}]
});
</script>