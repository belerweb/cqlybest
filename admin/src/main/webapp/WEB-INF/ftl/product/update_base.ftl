<#assign ID=product.id>
<#assign URL="${ContextPath}/product/update.do">
<div id="product-base-tab" class="tab-pane active">
	<div class="row-fluid">
		<div class="span6">
			<div class="control-group">
				<label class="control-label">产品名称：</label>
				<div class="controls">
					<#if product.productType==0>
					<span class="label label-success">普通产品</span>
					<#elseif product.productType==1>
					<span class="label label-success">马尔代夫</span>
					</#if>
					<a id="product_name" href="#" class="editable" data-pk="${ID}" data-name="name" data-type="text" data-url="${URL}" data-value="${(product.name!)?html}"></a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">产品代码：</label>
				<div class="controls">
					<a id="product_code" href="#" class="editable" data-pk="${ID}" data-name="code" data-type="text" data-url="${URL}" data-value="${(product.code!)?html}"></a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">出发城市：</label>
				<div class="controls">
					<a id="product_departure_cities" href="#" class="editable" data-pk="${ID}" data-name="departureCities" data-type="select2" data-url="${URL}" data-value="${(product.departureCities!)?html}">${(product.departureCities!)?html}</a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">目的地：</label>
				<div class="controls">
					<a id="product_destinations" href="#" class="editable" data-pk="${ID}" data-name="destinations" data-type="select2" data-url="${URL}" data-value="${(product.destinations!)?html}">${(product.destinations!)?html}</a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">市场价：</label>
				<div class="controls">
					<a id="product_market_price" href="#" class="editable" data-pk="${ID}" data-name="marketPrice" data-type="text" data-url="${URL}" data-value="<#if (product.marketPrice)?exists>${(product.marketPrice/100)?string('0.00')}</#if>"></a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">参考价格：</label>
				<div class="controls">
					<a id="product_price" href="#" class="editable" data-pk="${ID}" data-name="price" data-type="text" data-url="${URL}" data-value="<#if (product.price)?exists>${(product.price/100)?string('0.00')}</#if>"></a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">生效日期：</label>
				<div class="controls">
					<a id="product_effective_date" href="#" class="select" data-pk="${ID}" data-name="effectiveDate" data-type="date" data-url="${URL}" data-value="<#if product.effectiveDate?exists>${product.effectiveDate?string('yyyy-MM-dd')}</#if>"></a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">失效日期：</label>
				<div class="controls">
					<a id="product_expiry_date" href="#" class="select" data-pk="${ID}" data-name="expiryDate" data-type="date" data-url="${URL}" data-value="<#if product.expiryDate?exists>${product.expiryDate?string('yyyy-MM-dd')}</#if>"></a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">费用说明：</label>
				<div class="controls">
					<a id="product_price_description" href="#" class="editable" data-pk="${ID}" data-name="priceDescription" data-type="textarea" data-url="${URL}" data-value="${(product.priceDescription!)?html}"></a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">行程特色：</label>
				<div class="controls">
					<a id="product_trip_characteristic" href="#" class="editable" data-pk="${ID}" data-name="tripCharacteristic" data-type="textarea" data-url="${URL}" data-value="${(product.tripCharacteristic!)?html}"></a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">服务标准：</label>
				<div class="controls">
					<a id="product_service_standard" href="#" class="editable" data-pk="${ID}" data-name="serviceStandard" data-type="textarea" data-url="${URL}" data-value="${(product.serviceStandard!)?html}"></a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">友情提示：</label>
				<div class="controls">
					<a id="product_friendly_reminder" href="#" class="editable" data-pk="${ID}" data-name="friendlyReminder" data-type="textarea" data-url="${URL}" data-value="${(product.friendlyReminder!)?html}"></a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">推荐项目：</label>
				<div class="controls">
					<a id="product_recommended_item" href="#" class="editable" data-pk="${ID}" data-name="recommendedItem" data-type="textarea" data-url="${URL}" data-value="${(product.recommendedItem!)?html}"></a>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="span6">
			<div class="control-group">
				<label class="control-label">行程天数：</label>
				<div class="controls">
					<a id="product_days" href="#" class="editable" data-pk="${ID}" data-name="days" data-type="text" data-url="${URL}" data-value="${(product.days!)?html}"></a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">行程天数单位：</label>
				<div class="controls">
					<a id="product_days_unit" href="#" class="select" data-pk="${ID}" data-name="daysUnit" data-type="select" data-url="${URL}" data-value="${(product.daysUnit!)?html}"></a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">关键词/标签：</label>
				<div class="controls">
					<a id="product_keywords" href="#" class="editable" data-pk="${ID}" data-name="keywords" data-type="select2" data-url="${URL}" data-value="${(product.keywords!)?html}">${(product.keywords!)?html}</a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">交通方式：</label>
				<div class="controls">
					<a id="product_traffics" href="#" class="editable" data-pk="${ID}" data-name="traffics" data-type="select2" data-url="${URL}" data-value="${(product.traffics!)?html}">${(product.traffics!)?html}</a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">产品类型：</label>
				<div class="controls">
					<a id="product_types" href="#" class="editable" data-pk="${ID}" data-name="types" data-type="select2" data-url="${URL}" data-value="${(product.types!)?html}">${(product.types!)?html}</a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">产品等级：</label>
				<div class="controls">
					<a id="product_grades" href="#" class="editable" data-pk="${ID}" data-name="grades" data-type="select2" data-url="${URL}" data-value="${(product.grades!)?html}">${(product.grades!)?html}</a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">推荐月份：</label>
				<div class="controls">
					<a id="product_recommended_months" href="#" class="editable" data-pk="${ID}" data-name="recommendedMonths" data-type="select2" data-url="${URL}" data-value="${(product.recommendedMonths!)?html}">${(product.recommendedMonths!)?html}</a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">适合人群：</label>
				<div class="controls">
					<a id="product_crowds" href="#" class="editable" data-pk="${ID}" data-name="crowds" data-type="select2" data-url="${URL}" data-value="${(product.crowds!)?html}">${(product.crowds!)?html}</a>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
	<div class="row-fluid">
	</div>
</div>

<script type="text/javascript">
$('#product_name').editable();
$('#product_code').editable();
$('#product_days').editable();
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
$('#product_price').editable();
$('#product_market_price').editable();
$('#product_effective_date').editable();
$('#product_expiry_date').editable();
$('#product_price_description').editable();
$('#product_trip_characteristic').editable();
$('#product_service_standard').editable();
$('#product_friendly_reminder').editable();
$('#product_recommended_item').editable();
</script>
