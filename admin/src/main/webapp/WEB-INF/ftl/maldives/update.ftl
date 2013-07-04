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
				<ul class="nav nav-tabs">
					<li class="active"><a href="javascript:void(0);" data-toggle="tab" data-target="#island-base-tab">基本信息</a></li>
					<li><a href="javascript:void(0);" data-toggle="tab" data-target="#island-poster-tab">海报图片</a></li>
				</ul>
				<div class="tab-content">
					<#include "update_base.ftl">
					<div id="island-poster-tab" class="image-gallery tab-pane">
						<div class="text-right"><button id="island-add-poster" type="button" data-extra="maldives-island-poster" class="btn btn-primary">添加</button></div>
						<#if island.posters?has_content>
						<div class="row-fluid">
							<ul class="thumbnails">
							<#list island.posters as image>
								<li class="span3">
									<div class="thumbnail">
										<img src="${ContextPath}/image/${image.id}.${image.imageType}">
										<div class="caption">
											<p><a href="#" class="title editable-click <#if !image.title?has_content>editable-empty</#if>" data-pk="${image.id}" data-name="title" data-type="text" data-value="${(image.title!)?html}">${(image.title!'标题：未设置')?html}</a></p>
											<p><a href="#" class="description editable-click <#if !image.description?has_content>editable-empty</#if>" data-pk="${image.id}" data-name="description" data-type="textarea" data-value="${(image.description!)?html}">${(image.description!'描述：未设置')?html}</a></p>
											<button class="delete btn btn-danger" data-id="${image.id}">刪除</button>
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
var arrangeImags = function(el, images) {
	var imgs = $('li', el).detach();
	$('.row-fluid', el).remove();
	if (images) {
		$.each(images, function(i, obj){
			var image = $('<li class="span3"><div class="thumbnail"><img src="${ContextPath}/image/'+obj.id+'.'+obj.imageType+'"><div class="caption"></div></div></li>');
			$('.caption', image).append('<p><a href="#" class="title editable-click editable-empty" data-pk="'+obj.id+'" data-name="title" data-type="text">标题：未设置</a></p>');
			$('.caption', image).append('<p><a href="#" class="description editable-click editable-empty" data-pk="'+obj.id+'" data-name="description" data-type="textarea">描述：未设置</a></p>');
			$('.caption', image).append('<button class="delete btn btn-danger" data-id="'+obj.id+'">刪除</button>');
			imgs.push(image);
		});
	}
	var row;
	for (var i=0;i<imgs.length;i++) {
		if (i%4==0) {
			row = $('<div class="row-fluid"><ul class="thumbnails"></ul></div>');
			el.append(row);
		}
		$('ul', row).append(imgs[i]);
	}
};
$('#island-add-poster,#island-add-photo').click(function(){
	var images = cqlybest.uploadImage('${ContextPath}');
	if (images) {
		var gallery = $(this).parents('.image-gallery');
		var extra = $(this).attr('data-extra');
		$.each(images, function(i, obj) {
			$.post('${ContextPath}/image/update.do', {
				pk: obj.id,
				name: ['extra', 'extraKey'],
				value: [extra, '${id}']
			}, function(){
				// TODO
			});
		});
		arrangeImags(gallery, images);
	}
});
$('.image-gallery').editable({
	selector: 'a.title,a.description',
	url: '${ContextPath}/image/update.do'
});
$('.image-gallery button.delete').die('click').live('click', function() {
	var id = $(this).attr('data-id');
	var image = $(this).parents('li');
	var el = $(this).parents('.image-gallery');
	bootbox.confirm('确认删除图片', '取消', '确认', function(result) {
		if (result) {
			$.post('${ContextPath}/image/delete.do', {
				id: id
			}).done(function(){
				image.remove();
				arrangeImags(el);
			}).fail(function() {
				cqlybest.error();
			});
		}
	});
});
</script>