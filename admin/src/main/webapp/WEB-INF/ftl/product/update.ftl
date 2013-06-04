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
		<form class="form-horizontal">
			<div class="grid-content">
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
var dictAjax = function(dict) {
	return {
			url: '/data/dict.html?action=dict&type=' + dict,
			data: function (term, page) {
				return {q:term};
			},
			results: function(response) {
				var result = [];
				$.each(response.tags, function(i, obj){
					result.push({id:obj.name,text:obj.name});
				});
				return {results:result};
			}
		};
};
var initSelection = function(el) {
	return function(el, callback) {
			callback(cqlybest.v2ss(el.val()||$(el).data('value')));
		};
};
$('#product_departure_cities').editable({
	inputclass: 'input-large',
	select2: {
		multiple: true,
		ajax: dictAjax('departure-city'),
		initSelection: initSelection('#product_departure_cities')
	}
});
$('#product_destinations').editable({
	inputclass: 'input-large',
	select2: {
		multiple: true,
		ajax: dictAjax('destination'),
		initSelection: initSelection('#product_destinations')
	}
});
$('#product_keywords').editable({
	inputclass: 'input-large',
	select2: {
		multiple: true,
		ajax: dictAjax('keyword'),
		initSelection: initSelection('#product_keywords')
	}
});
$('#product_traffics').editable({
	inputclass: 'input-large',
	select2: {
		multiple: true,
		ajax: dictAjax('traffic'),
		initSelection: initSelection('#product_traffics')
	}
});
$('#product_types').editable({
	inputclass: 'input-large',
	select2: {
		multiple: true,
		ajax: dictAjax('product-type'),
		initSelection: initSelection('#product_types')
	}
});
$('#product_grades').editable({
	inputclass: 'input-large',
	select2: {
		multiple: true,
		ajax: dictAjax('product-grade'),
		initSelection: initSelection('#product_grades')
	}
});
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
</script>