<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div id="page-content" class="clearfix" style="position:releative;">
	<div class="row-fluid">
		<h3 class="header smaller lighter blue">
			编辑海岛
			<small>
				<i class="icon-double-angle-right"></i>
				${island.zhName!}
			</small>
			<button type="button" class="btn btn-mini btn-primary pull-right" onclick="cqlybest.go('#main-content');">
				<i class="icon-mail-reply"></i> 返回
			</button>
		</h3>
		<div class="tabbable">
			<#assign id=island.id>
			<#assign url="${ContextPath}/seychelles/update.do">
			<ul id="island-update-tabs" class="nav nav-tabs">
				<li class="active"><a href="javascript:void(0);" data-toggle="tab" data-target="#island-base-tab">基本信息</a></li>
				<li><a href="javascript:void(0);" data-toggle="tab" data-target="#island-hotel-tab">酒店信息</a></li>
				<li><a href="javascript:void(0);" data-toggle="tab" data-target="#island-room-tab">房型</a></li>
				<li><a href="javascript:void(0);" data-toggle="tab" data-target="#island-dining-tab">餐饮设施</a></li>
				<li><a href="javascript:void(0);" data-toggle="tab" data-target="#island-play-tab">活动与娱乐设施</a></li>
				<li><a href="javascript:void(0);" data-toggle="tab" data-target="#island-poster-tab">海报图片</a></li>
			</ul>
			<div class="tab-content form-horizontal" style="min-height:400px;overflow:visible;z-index:13;">
				<#include "update_base.ftl">
				<#include "update_hotel.ftl">
				<#include "update_room.ftl">
				<#include "update_dining.ftl">
				<#include "update_play.ftl">
				<div id="island-poster-tab" class="image-gallery tab-pane">
					<div class="text-right"><button type="button" class="btn btn-primary btn-mini action action-add picture" data-name="islandId" data-value="${island.id}" data-url="${ContextPath}/seychelles/picture/add.do">添加</button></div>
					<#if island.pictures?has_content>
					<div class="row-fluid">
						<ul class="thumbnails">
						<#list island.pictures as image>
							<li class="span3">
								<div class="thumbnail">
									<img src="http://${ImageServer}/${image.qiniuKey}-gallery">
									<div class="caption">
										<p><a href="#" class="editable" data-pk="${image.id}" data-name="title" data-type="text" data-value="${image.title!}" data-url="${ContextPath}/seychelles/picture/update.do"></a></p>
										<p><a href="#" class="editable" data-pk="${image.id}" data-name="description" data-type="textarea" data-url="${ContextPath}/seychelles/picture/update.do">${image.description!?html}</a></p>
										<button class="delete btn btn-danger" type="button" data-id="${image.id}" data-url="${ContextPath}/seychelles/picture/delete.do">刪除</button>
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
$('button.picture.action-add').click(function(){
	var tab = $(this).closest('.tab-pane').attr('id');
	var url = $(this).attr('data-url');
	var images = cqlybest.uploadImage();
	if (images) {
		var param = {images:[]};
		param[$(this).attr('data-name')] = $(this).attr('data-value');
		$.each(images, function(i, obj) {
			param.images.push(obj.id);
		});
		$.post(url, param, function(){
			cqlybest.reload('#main-content', function() {
				$('#island-update-tabs a[data-target="#' + tab + '"]').tab('show');
			});
		});
	}
});
$('.image-gallery button.delete').click(function() {
	var tab = $(this).closest('.tab-pane').attr('id');
	var id = $(this).attr('data-id');
	var url = $(this).attr('data-url');
	bootbox.confirm('确认删除图片?', '取消', '确认', function(result) {
		if (result) {
			$.post(url, {
				imageId: id
			}).done(function(){
				cqlybest.reload('#main-content', function() {
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
$('div[data-type=wysihtml5]').on('shown', function(){
	cqlybest.fixed(false);
}).on('hidden', function(){
	cqlybest.fixed(true);
}).editable({
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