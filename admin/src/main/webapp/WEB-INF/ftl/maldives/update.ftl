<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div>
	<div class="clearfix"></div>
	<div class="grid">
		<div class="grid-title">
			<div class="pull-left">
				<div class="icon-title"><i class="icon-edit"></i></div>
				<span>编辑海岛</span> 
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>   
		</div>
		<#assign id=island.id>
		<#assign url="${ContextPath}/maldives/update.do">
		<form class="form-horizontal grid-content">
			<div class="tabbable">
				<ul id="island-update-tabs" class="nav nav-tabs">
					<li class="active"><a href="javascript:void(0);" data-toggle="tab" data-target="#island-base-tab">基本信息</a></li>
					<li><a href="javascript:void(0);" data-toggle="tab" data-target="#island-hotel-tab">酒店信息</a></li>
					<li><a href="javascript:void(0);" data-toggle="tab" data-target="#island-room-tab">房型</a></li>
					<li><a href="javascript:void(0);" data-toggle="tab" data-target="#island-dining-tab">餐饮设施</a></li>
					<li><a href="javascript:void(0);" data-toggle="tab" data-target="#island-play-tab">活动与娱乐设施</a></li>
					<li><a href="javascript:void(0);" data-toggle="tab" data-target="#island-poster-tab">海报图片</a></li>
				</ul>
				<div class="tab-content">
					<#include "update_base.ftl">
					<#include "update_hotel.ftl">
					<#include "update_room.ftl">
					<#include "update_dining.ftl">
					<#include "update_play.ftl">
					<div id="island-poster-tab" class="image-gallery tab-pane">
						<div class="text-right"><button type="button" class="btn btn-primary action action-add picture">添加</button></div>
						<#if island.pictures?has_content>
						<div class="row-fluid">
							<ul class="thumbnails">
							<#list island.pictures as image>
								<li class="span3">
									<div class="thumbnail">
										<img src="${ContextPath}/image/${image.id}.${image.extension}">
										<div class="caption">
											<p><a href="#" class="editable" data-pk="${image.id}" data-name="title" data-type="text" data-value="${image.title!}" data-url="${ContextPath}/maldives/picture/update.do"></a></p>
											<p><a href="#" class="editable" data-pk="${image.id}" data-name="description" data-type="textarea" data-url="${ContextPath}/maldives/picture/update.do">${image.description!?html}</a></p>
											<button class="delete btn btn-danger" type="button" data-id="${image.id}" data-url="${ContextPath}/maldives/picture/delete.do">刪除</button>
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
		</form>
	</div>
</div>
<script type="text/javascript">
$('button.picture.action-add').click(function(){
	var tab = $(this).closest('.tab-pane').attr('id');
	var images = cqlybest.uploadImage('${ContextPath}');
	if (images) {
		var _images = [];
		$.each(images, function(i, obj) {
			_images.push(obj.id + '.' + obj.extension);
		});
		$.post('${ContextPath}/maldives/picture/add.do', {
			islandId: '${island.id}',
			images: _images
		}, function(){
			$('#mb').load('${ContextPath}/maldives/update.do?id=${island.id}', function() {
				$('#island-update-tabs a[data-target="#' + tab + '"]').tab('show');
			});
		});
	}
});
$('.image-gallery button.delete').die('click').live('click', function() {
	var tab = $(this).closest('.tab-pane').attr('id');
	var id = $(this).attr('data-id');
	var url = $(this).attr('data-url');
	bootbox.confirm('确认删除图片?', '取消', '确认', function(result) {
		if (result) {
			$.post(url, {
				imageId: id
			}).done(function(){
				$('#mb').load('${ContextPath}/maldives/update.do?id=${island.id}', function() {
					$('#island-update-tabs a[data-target="#' + tab + '"]').tab('show');
				});
			}).fail(function() {
				cqlybest.error();
			});
		}
	});
});

$('.editable[data-type=text]').editable();
$('.editable[data-type=textarea]').editable();
$('div[data-type=wysihtml5]').editable({
	wysihtml5: {
		stylesheets: ['${ContextPath}/css/wysiwyg-color.css']
	}
});
$('.editable-wysihtml5').click(function(e) {
	e.stopPropagation();
	e.preventDefault();
	$(this).parent().next().children().editable('toggle');
});
</script>