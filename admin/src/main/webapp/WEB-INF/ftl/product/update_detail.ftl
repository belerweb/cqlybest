<div id="product-detail-tab" class="tab-pane">
	<style>
	.product-travel-item .delete {margin-top:10px;}
	</style>
	<div class="text-right"><button id="product-add-travel" type="button" class="btn btn-primary">添加</button></div>
	<#assign travel_url="${ContextPath}/product/travel/update.do">
	<#if product.travels?has_content>
	<#list product.travels as travel>
	<div class="row-fluid product-travel-item">
		<div class="span12">
			<div class="control-group">
				<label class="control-label">
					<strong>
						<a href="#" class="editable editable-click <#if !travel.name?has_content>editable-empty</#if> product_travel" data-pk="${travel.id}" data-name="name" data-type="text" data-url="${travel_url}" data-value="${(travel.name!)?html}"><#if travel.name?has_content>${travel.name}<#else>未设置</#if></a>
					</strong>
				</label>
				<div class="controls">
					<button data-id="${travel.id}" type="button" class="btn btn-mini btn-danger delete">删除</button>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">游：</label>
				<div class="controls">
					<a href="#" class="editable editable-click <#if !travel.tour?has_content>editable-empty</#if> product_travel" data-pk="${travel.id}" data-name="tour" data-type="textarea" data-url="${travel_url}" data-value="${(travel.tour!)?html}">${(travel.tour!'未设置')?html}</a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">行：</label>
				<div class="controls">
					<a href="#" class="editable editable-click <#if !travel.traffic?has_content>editable-empty</#if> product_travel" data-pk="${travel.id}" data-name="traffic" data-type="textarea" data-url="${travel_url}" data-value="${(travel.traffic!)?html}">${(travel.traffic!'未设置')?html}</a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">吃：</label>
				<div class="controls">
					<a href="#" class="editable editable-click <#if !travel.eat?has_content>editable-empty</#if> product_travel" data-pk="${travel.id}" data-name="eat" data-type="textarea" data-url="${travel_url}" data-value="${(travel.eat!)?html}">${(travel.eat!'未设置')?html}</a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">住：</label>
				<div class="controls">
					<a href="#" class="editable editable-click <#if !travel.live?has_content>editable-empty</#if> product_travel" data-pk="${travel.id}" data-name="live" data-type="textarea" data-url="${travel_url}" data-value="${(travel.live!)?html}">${(travel.live!'未设置')?html}</a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">购：</label>
				<div class="controls">
					<a href="#" class="editable editable-click <#if !travel.purchase?has_content>editable-empty</#if> product_travel" data-pk="${travel.id}" data-name="purchase" data-type="textarea" data-url="${travel_url}" data-value="${(travel.purchase!)?html}">${(travel.purchase!'未设置')?html}</a>
				</div>
			</div>
		</div>
	</div>
	</#list>
	</#if>
</div>

<script type="text/javascript">
$('#product-add-travel').click(function(){
	var container = $(this).parents('.tab-pane');
	bootbox.prompt("行程名称", "取消", "确定", function(result) {
		var name = $.trim(result);
		if (name.length) {
			$.post('${ContextPath}/product/travel/add.do', {
				productId: '${product.id}',
				name: name
			}, function(id){
				var row = ['<div class="row-fluid product-travel-item"><div class="span12">'];
				row.push('<div class="control-group"><label class="control-label"><strong>');
				var url = "${ContextPath}/product/travel/update.do";
				row.push('<a data-url="'+url+'" data-type="text" data-name="name" data-pk="'+id+'" class="editable editable-click product_travel" href="#">'+name+'</a>');
				row.push('</strong></label>');
				row.push('<div class="controls"><button data-id="'+id+'" type="button" class="btn btn-mini btn-danger delete">删除</button></div></div>');

				row.push('<div class="control-group"><label class="control-label">游：</label><div class="controls">');
				row.push('<a data-url="'+url+'" data-type="textarea" data-name="tour" data-pk="'+id+'" class="editable editable-click editable-empty product_travel" href="#">未设置</a></div></div>');
				row.push('<div class="control-group"><label class="control-label">行：</label><div class="controls">');
				row.push('<a data-url="'+url+'" data-type="textarea" data-name="traffic" data-pk="'+id+'" class="editable editable-click editable-empty product_travel" href="#">未设置</a></div></div>');
				row.push('<div class="control-group"><label class="control-label">吃：</label><div class="controls">');
				row.push('<a data-url="'+url+'" data-type="textarea" data-name="eat" data-pk="'+id+'" class="editable editable-click editable-empty product_travel" href="#">未设置</a></div></div>');
				row.push('<div class="control-group"><label class="control-label">住：</label><div class="controls">');
				row.push('<a data-url="'+url+'" data-type="textarea" data-name="live" data-pk="'+id+'" class="editable editable-click editable-empty product_travel" href="#">未设置</a></div></div>');
				row.push('<div class="control-group"><label class="control-label">购：</label><div class="controls">');
				row.push('<a data-url="'+url+'" data-type="textarea" data-name="purchase" data-pk="'+id+'" class="editable editable-click editable-empty product_travel" href="#">未设置</a></div></div>');

				row.push('</div></div>');
				container.append(row.join(''));
			});
		}
	});
});
$('#product-detail-tab').editable({
	selector: 'a.product_travel',
	url: '${ContextPath}/product/travel/update.do'
});
$('.product-travel-item button.delete').die('click').live('click', function() {
	var id = $(this).attr('data-id');
	var row = $(this).parents('.row-fluid');
	bootbox.confirm('确认删除行程', '取消', '确认', function(result) {
		if (result) {
			$.post('${ContextPath}/product/travel/delete.do', {
				id: id
			}).done(function(){
				row.remove();
			}).fail(function() {
				cqlybest.error();
			});
		}
	});
});
</script>
