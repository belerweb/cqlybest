<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div>
	<div class="clearfix"></div>
	<div class="pagetitle">
		<h1>友情链接</h1>
		<div class="btn-group">
			<a href="javascript:void(0);" class="btn btn-primary action-add">增加友情链接</a>
		</div>
		<div class="clearfix"></div>
		<hr>
	</div>
	<div class="grid">
		<div class="grid-title">
			<div class="pull-left">
				<div class="icon-title"><i class="icon-table"></i></div>
				<span>友情链接列表</span> 
				<div class="clearfix"></div>
			</div>
			<div class="pull-right"> 
			</div>
			<div class="clearfix"></div>   
		</div>
		<div class="grid-content overflow">
			<table id="main-list-table" class="table table-striped table-condensed">
				<thead>
					<tr>
						<th>名称</th>
						<th>标题</th>
						<th>链接</th>
						<th>图片</th>
						<th>顺序</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<#assign url='${ContextPath}/link/update.do'>
					<#list links as link>
					<tr>
						<td><a href="#" class="editable" data-pk="${link.id}" data-name="name" data-type="text" data-url="${url}" data-value="${link.name!}"></a></td>
						<td><a href="#" class="editable" data-pk="${link.id}" data-name="title" data-type="text" data-url="${url}" data-value="${link.title!}"></a></td>
						<td><a href="#" class="editable" data-pk="${link.id}" data-name="link" data-type="text" data-url="${url}" data-value="${link.link!}"></a></td>
						<td class="action-table">
							<#if link.imageId?has_content>
							<a href="${ContextPath}/image/${link.image}" target="_blank" class="safe" title="链接图片"><i class="icon-picture"></i></a>
							</#if>
							<button type="button" data-extra="FriendlyLink" data-extra-key="${link.id}" class="btn btn-primary action action-add picture">上传</button>
						</td>
						<td><a href="#" class="editable" data-pk="${link.id}" data-name="displayOrder" data-type="text" data-url="${url}" data-value="${link.displayOrder!}"></a></td>
						<td class="action-table">
							<a href="javascript:void(0);" data-url="${ContextPath}/link/delete.do?ids[]=${link.id}"
								class="ajax-action-btn danger last" data-confirm="true" data-action="删除" data-title="${link.name!}"
								title="删除"><i class="icon-remove"></i></a>
						</td>
					</tr>
					</#list>
				</tbody>
			</table>
			<div class="clearfix"></div>
		</div>
	</div>
</div>
<script>
$('#mb .pagetitle a.action-add').click(function(){
	bootbox.prompt("链接名称", "取消", "确定", function(result) {
		var name = $.trim(result);
		if (name.length) {
			$.post('${ContextPath}/link/add.do', {
				name: name
			}, function(response){
				cqlybest.reload();
			});
		}
	});
});
$('#mb a.editable').editable();

$('button.picture.action-add').click(function(){
	var images = cqlybest.uploadImage('${ContextPath}');
	if (images) {
		var gallery = $(this).closest('.image-gallery');
		var extra = $(this).attr('data-extra');
		var extraKey = $(this).attr('data-extra-key');
		$.post('${ContextPath}/image/update', {
			pk: images[0].id,
			name: ['extra', 'extraKey'],
			value: [extra, extraKey]
		}, function(){
			$.post('${ContextPath}/link/update.do', {
				pk: extraKey,
				name: 'image',
				value: images[0].id + '.' + images[0].imageType
			}, function(){
				cqlybest.reload();
			});
		});
	}
});
</script>