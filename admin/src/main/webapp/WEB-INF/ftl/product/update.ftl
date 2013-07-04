<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div>
	<div class="clearfix"></div>
	<div class="grid">
		<div class="grid-title">
			<div class="pull-left">
				<div class="icon-title"><i class="icon-edit"></i></div>
				<span>编辑产品</span> 
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>   
		</div>
		<#assign id=product.id>
		<#assign url="${ContextPath}/product/update.do">
		<form class="form-horizontal grid-content">
			<div class="tabbable">
				<ul class="nav nav-tabs">
					<li class="active"><a href="javascript:void(0);" data-toggle="tab" data-target="#product-base-tab">基本信息</a></li>
					<li><a href="javascript:void(0);" data-toggle="tab" data-target="#product-detail-tab">详细行程</a></li>
					<li><a href="javascript:void(0);" data-toggle="tab" data-target="#product-calendar-tab">日历</a></li>
					<li><a href="javascript:void(0);" data-toggle="tab" data-target="#product-poster-tab">海报图片 </a></li>
					<li><a href="javascript:void(0);" data-toggle="tab" data-target="#product-photo-tab">相册图片</a></li>
					<li><a href="javascript:void(0);" data-toggle="tab" data-target="#product-comment-tab">评论</a></li>
				</ul>
				<div class="tab-content">
					<div id="product-base-tab" class="tab-pane active">
						<div class="row-fluid">
							<div class="span6">
								<div class="control-group">
									<label class="control-label">产品名称：</label>
									<div class="controls">
										<a id="product_name" href="#" class="editable" data-pk="${id}" data-name="name" data-type="text" data-url="${url}" data-value="${(product.name!)?html}"></a>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">产品代码：</label>
									<div class="controls">
										<a id="product_code" href="#" class="editable" data-pk="${id}" data-name="code" data-type="text" data-url="${url}" data-value="${(product.code!)?html}"></a>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">出发城市：</label>
									<div class="controls">
										<a id="product_departure_cities" href="#" class="editable" data-pk="${id}" data-name="departureCities" data-type="select2" data-url="${url}" data-value="${(product.departureCities!)?html}">${(product.departureCities!)?html}</a>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">目的地：</label>
									<div class="controls">
										<a id="product_destinations" href="#" class="editable" data-pk="${id}" data-name="destinations" data-type="select2" data-url="${url}" data-value="${(product.destinations!)?html}">${(product.destinations!)?html}</a>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">正常价格：</label>
									<div class="controls">
										<a id="product_price" href="#" class="editable" data-pk="${id}" data-name="price" data-type="text" data-url="${url}" data-value="<#if (product.price)?exists>${(product.price/100)?string('0.00')}</#if>"></a>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">儿童价：</label>
									<div class="controls">
										<a id="product_child_price" href="#" class="editable" data-pk="${id}" data-name="childPrice" data-type="text" data-url="${url}" data-value="<#if (product.childPrice)?exists>${(product.childPrice/100)?string('0.00')}</#if>"></a>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">特价：</label>
									<div class="controls">
										<a id="product_special_price" href="#" class="editable" data-pk="${id}" data-name="specialPrice" data-type="text" data-url="${url}" data-value="<#if (product.specialPrice)?exists>${(product.specialPrice/100)?string('0.00')}</#if>"></a>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">生效日期：</label>
									<div class="controls">
										<a id="product_effective_date" href="#" class="select" data-pk="${id}" data-name="effectiveDate" data-type="date" data-url="${url}" data-value="<#if product.effectiveDate?exists>${product.effectiveDate?string('yyyy-MM-dd')}</#if>"></a>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">失效日期：</label>
									<div class="controls">
										<a id="product_expiry_date" href="#" class="select" data-pk="${id}" data-name="expiryDate" data-type="date" data-url="${url}" data-value="<#if product.expiryDate?exists>${product.expiryDate?string('yyyy-MM-dd')}</#if>"></a>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">费用说明：</label>
									<div class="controls">
										<a id="product_price_description" href="#" class="editable" data-pk="${id}" data-name="priceDescription" data-type="textarea" data-url="${url}" data-value="${(product.priceDescription!)?html}"></a>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">行程特色：</label>
									<div class="controls">
										<a id="product_trip_characteristic" href="#" class="editable" data-pk="${id}" data-name="tripCharacteristic" data-type="textarea" data-url="${url}" data-value="${(product.tripCharacteristic!)?html}"></a>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">服务标准：</label>
									<div class="controls">
										<a id="product_service_standard" href="#" class="editable" data-pk="${id}" data-name="serviceStandard" data-type="textarea" data-url="${url}" data-value="${(product.serviceStandard!)?html}"></a>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">友情提示：</label>
									<div class="controls">
										<a id="product_friendly_reminder" href="#" class="editable" data-pk="${id}" data-name="friendlyReminder" data-type="textarea" data-url="${url}" data-value="${(product.friendlyReminder!)?html}"></a>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">推荐项目：</label>
									<div class="controls">
										<a id="product_recommended_item" href="#" class="editable" data-pk="${id}" data-name="recommendedItem" data-type="textarea" data-url="${url}" data-value="${(product.recommendedItem!)?html}"></a>
									</div>
								</div>
								<div class="clearfix"></div>
							</div>
							<div class="span6">
								<div class="control-group">
									<label class="control-label">行程天数：</label>
									<div class="controls">
										<a id="product_days" href="#" class="editable" data-pk="${id}" data-name="days" data-type="text" data-url="${url}" data-value="${(product.days!)?html}"></a>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">行程天数单位：</label>
									<div class="controls">
										<a id="product_days_unit" href="#" class="select" data-pk="${id}" data-name="daysUnit" data-type="select" data-url="${url}" data-value="${(product.daysUnit!)?html}"></a>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">关键词/标签：</label>
									<div class="controls">
										<a id="product_keywords" href="#" class="editable" data-pk="${id}" data-name="keywords" data-type="select2" data-url="${url}" data-value="${(product.keywords!)?html}">${(product.keywords!)?html}</a>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">交通方式：</label>
									<div class="controls">
										<a id="product_traffics" href="#" class="editable" data-pk="${id}" data-name="traffics" data-type="select2" data-url="${url}" data-value="${(product.traffics!)?html}">${(product.traffics!)?html}</a>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">产品类型：</label>
									<div class="controls">
										<a id="product_types" href="#" class="editable" data-pk="${id}" data-name="types" data-type="select2" data-url="${url}" data-value="${(product.types!)?html}">${(product.types!)?html}</a>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">产品等级：</label>
									<div class="controls">
										<a id="product_grades" href="#" class="editable" data-pk="${id}" data-name="grades" data-type="select2" data-url="${url}" data-value="${(product.grades!)?html}">${(product.grades!)?html}</a>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">推荐月份：</label>
									<div class="controls">
										<a id="product_recommended_months" href="#" class="editable" data-pk="${id}" data-name="recommendedMonths" data-type="select2" data-url="${url}" data-value="${(product.recommendedMonths!)?html}">${(product.recommendedMonths!)?html}</a>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">适合人群：</label>
									<div class="controls">
										<a id="product_crowds" href="#" class="editable" data-pk="${id}" data-name="crowds" data-type="select2" data-url="${url}" data-value="${(product.crowds!)?html}">${(product.crowds!)?html}</a>
									</div>
								</div>
								<div class="clearfix"></div>
							</div>
						</div>
						<div class="row-fluid">
						</div>
					</div>
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
										<button data-id="${travel.id}" class="btn btn-mini btn-danger delete">删除</button>
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
					<#include "update_calendar.ftl">
					<div id="product-poster-tab" class="image-gallery tab-pane">
						<div class="text-right"><button id="product-add-poster" type="button" data-extra="product-poster" class="btn btn-primary">添加</button></div>
						<#if product.posters?has_content>
						<div class="row-fluid">
							<ul class="thumbnails">
							<#list product.posters as image>
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
					<div id="product-photo-tab" class="image-gallery tab-pane">
						<div class="text-right"><button id="product-add-photo" type="button" data-extra="product-photo" class="btn btn-primary">添加</button></div>
						<#if product.photos?has_content>
						<div class="row-fluid">
							<ul class="thumbnails">
							<#list product.photos as image>
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
					<#include "update_comment.ftl">
				</div>
			</div>
		</form>
	</div>
</div>
<script>
$('#product_name').editable({
});
$('#product_code').editable({
});
$('#product_days').editable({
});
$('#product_days_unit').editable({
	source: [{value:'天',text:'天'},{value:'月',text:'月'},{value:'年',text:'年'}]
});
cqlybest.editableTag('#product_departure_cities', 'departure-city');
cqlybest.editableTag('#product_destinations', 'destination');
cqlybest.editableTag('#product_keywords', 'keyword');
cqlybest.editableTag('#product_traffics', 'traffic');
cqlybest.editableTag('#product_types', 'product-type');
cqlybest.editableTag('#product_grades', 'product-grade');
$('#product_recommended_months').editable({
	inputclass: 'input-large',
	select2: {
		tags: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
	}
});
$('#product_crowds').editable({
	inputclass: 'input-large',
	select2: {
		tags: ['个人旅行', '团体旅行']
	}
});
$('#product_price').editable({
});
$('#product_child_price').editable({
});
$('#product_special_price').editable({
});
$('#product_effective_date').editable({
});
$('#product_expiry_date').editable({
});
$('#product_price_description').editable({
});
$('#product_trip_characteristic').editable({
});
$('#product_service_standard').editable({
});
$('#product_friendly_reminder').editable({
});
$('#product_recommended_item').editable({
});

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
				row.push('<div class="controls"><button data-id="'+id+'" class="btn btn-mini btn-danger delete">删除</button></div></div>');

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
$('#product-add-poster,#product-add-photo').click(function(){
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