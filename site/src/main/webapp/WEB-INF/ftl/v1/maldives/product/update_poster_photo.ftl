<div id="product-poster-tab" class="image-gallery tab-pane">
	<div class="text-right">
		<button type="button" id="product-add-poster" class="btn btn-primary btn-mini"
			 data-name="productId" data-value="${product.id}" data-url="${ContextPath}/maldives/product/poster/add.do">
			添加
		</button>
	</div>
	<#if product.posters?has_content>
	<div class="row-fluid">
		<ul class="thumbnails">
		<#list product.posters as image>
			<li class="span3">
				<div class="thumbnail">
					<img src="http://${ImageServer}/${image.qiniuKey}-gallery">
					<div class="caption">
						<p><a href="#" class="editable" data-pk="${image.id}" data-name="title" data-type="text" data-value="${image.title!}" data-url="${ContextPath}/maldives/product/poster/update.do"></a></p>
						<p><a href="#" class="editable" data-pk="${image.id}" data-name="description" data-type="textarea" data-url="${ContextPath}/maldives/product/poster/update.do">${image.description!?html}</a></p>
						<button class="delete btn btn-danger" type="button" data-id="${image.id}" data-url="${ContextPath}/maldives/product/poster/delete.do">刪除</button>
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
<div id="product-photo-tab" class="image-gallery tab-pane">
	<div class="text-right">
		<button type="button" id="product-add-photo" class="btn btn-primary btn-mini"
			 data-name="productId" data-value="${product.id}" data-url="${ContextPath}/maldives/product/photo/add.do">
			添加
		</button>
	</div>
	<#if product.photos?has_content>
	<div class="row-fluid">
		<ul class="thumbnails">
		<#list product.photos as image>
			<li class="span3">
				<div class="thumbnail">
					<img src="http://${ImageServer}/${image.qiniuKey}-gallery">
					<div class="caption">
						<p><a href="#" class="editable" data-pk="${image.id}" data-name="title" data-type="text" data-value="${image.title!}" data-url="${ContextPath}/maldives/product/photo/update.do"></a></p>
						<p><a href="#" class="editable" data-pk="${image.id}" data-name="description" data-type="textarea" data-url="${ContextPath}/maldives/product/photo/update.do">${image.description!?html}</a></p>
						<button class="delete btn btn-danger" type="button" data-id="${image.id}" data-url="${ContextPath}/maldives/product/photo/delete.do">刪除</button>
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

<script type="text/javascript">
$('#product-add-poster,#product-add-photo').click(function(){
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
				$('#product-update-tabs a[data-target="#' + tab + '"]').tab('show');
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
					$('#product-update-tabs a[data-target="#' + tab + '"]').tab('show');
				});
			}).fail(function() {
				cqlybest.error();
			});
		}
	});
});
</script>
