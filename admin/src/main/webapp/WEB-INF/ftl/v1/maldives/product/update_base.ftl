<#assign ID=product.id>
<#assign URL="${ContextPath}/product/update.do">
<div id="product-base-tab" class="tab-pane active">
	<div class="row-fluid">
		<div class="span6">
			<div class="control-group">
				<label class="control-label">产品名称：</label>
				<div class="controls">
					<a href="#" class="editable" data-pk="${ID}" data-name="name" data-type="text" data-url="${URL}" data-value="${(product.name!)?html}"></a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">产品代码：</label>
				<div class="controls">
					<a href="#" class="editable" data-pk="${ID}" data-name="code" data-type="text" data-url="${URL}" data-value="${(product.code!)?html}"></a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">出发城市：</label>
				<div class="controls">
					<a href="#" class="editable" data-dict="departure-city" data-pk="${ID}" data-name="departureCities" data-type="select2" data-url="${URL}" data-value="${product.departureCities?join(',')}">${product.departureCities?join(',')}</a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">目的地：</label>
				<div class="controls">
					<a href="#" class="editable" data-dict="destination" data-pk="${ID}" data-name="destinations" data-type="select2" data-url="${URL}" data-value="${product.destinations?join(',')}">${product.destinations?join(',')}</a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">市场价：</label>
				<div class="controls">
					<a href="#" class="editable" data-pk="${ID}" data-name="marketPrice" data-type="text" data-url="${URL}" data-value="<#if (product.marketPrice)?exists>${(product.marketPrice/100)?string('0.00')}</#if>"></a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">参考价格：</label>
				<div class="controls">
					<a href="#" class="editable" data-pk="${ID}" data-name="price" data-type="text" data-url="${URL}" data-value="<#if (product.price)?exists>${(product.price/100)?string('0.00')}</#if>"></a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">生效日期：</label>
				<div class="controls">
					<a href="#" class="editable" data-pk="${ID}" data-name="effectiveDate" data-type="date" data-url="${URL}" data-value="<#if product.effectiveDate?exists>${product.effectiveDate?string('yyyy-MM-dd')}</#if>"></a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">失效日期：</label>
				<div class="controls">
					<a href="#" class="editable" data-pk="${ID}" data-name="expiryDate" data-type="date" data-url="${URL}" data-value="<#if product.expiryDate?exists>${product.expiryDate?string('yyyy-MM-dd')}</#if>"></a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">费用说明（包含）：</label>
				<div class="controls">
					<a href="#" class="editable" data-pk="${ID}" data-name="priceDescription" data-type="textarea" data-url="${URL}" data-value="${(product.priceDescription!)?html}"></a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">费用说明（不含）：</label>
				<div class="controls">
					<a href="#" class="editable" data-pk="${ID}" data-name="priceExclusive" data-type="textarea" data-url="${URL}" data-value="${(product.priceExclusive!)?html}"></a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">行程特色：</label>
				<div class="controls">
					<a href="#" class="editable" data-pk="${ID}" data-name="tripCharacteristic" data-type="textarea" data-url="${URL}" data-value="${(product.tripCharacteristic!)?html}"></a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">服务标准：</label>
				<div class="controls">
					<a href="#" class="editable" data-pk="${ID}" data-name="serviceStandard" data-type="textarea" data-url="${URL}" data-value="${(product.serviceStandard!)?html}"></a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">友情提示：</label>
				<div class="controls">
					<a href="#" class="editable" data-pk="${ID}" data-name="friendlyReminder" data-type="textarea" data-url="${URL}" data-value="${(product.friendlyReminder!)?html}"></a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">推荐项目：</label>
				<div class="controls">
					<a href="#" class="editable" data-pk="${ID}" data-name="recommendedItem" data-type="textarea" data-url="${URL}" data-value="${(product.recommendedItem!)?html}"></a>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="span6">
			<div class="control-group">
				<label class="control-label">简要行程（如2水2沙）：</label>
				<div class="controls">
					<a href="#" class="editable" data-pk="${ID}" data-name="briefTrip" data-type="text" data-url="${URL}" data-value="${product.briefTrip?join('')}"></a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">关键词/标签：</label>
				<div class="controls">
					<a href="#" class="editable" data-dict="tag" data-pk="${ID}" data-name="keywords" data-type="select2" data-url="${URL}" data-value="${product.keywords?join(',')}">${product.keywords?join(',')}</a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">交通方式：</label>
				<div class="controls">
					<a href="#" class="editable" data-dict="traffic" data-pk="${ID}" data-name="traffics" data-type="select2" data-url="${URL}" data-value="${product.traffics?join(',')}">${product.traffics?join(',')}</a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">产品类型：</label>
				<div class="controls">
					<a href="#" class="editable" data-dict="product-type" data-pk="${ID}" data-name="types" data-type="select2" data-url="${URL}" data-value="${product.types?join(',')}">${product.types?join(',')}</a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">产品等级：</label>
				<div class="controls">
					<a href="#" class="editable" data-dict="product-grade" data-pk="${ID}" data-name="grades" data-type="select2" data-url="${URL}" data-value="${product.grades?join(',')}">${product.grades?join(',')}</a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">推荐月份：</label>
				<div class="controls">
					<a href="#" class="editable" data-pk="${ID}" data-name="recommendedMonths" data-type="select2" data-url="${URL}" data-value="${product.recommendedMonths?join(',')}">${product.recommendedMonths?join(',')}</a>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">适合人群：</label>
				<div class="controls">
					<a href="#" class="editable" data-pk="${ID}" data-name="crowds" data-type="select2" data-url="${URL}" data-value="${product.crowds?join(',')}">${product.crowds?join(',')}</a>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
	<div class="row-fluid">
	</div>
</div>

<script type="text/javascript">
$('#product-base-tab a.editable[data-name=effectiveDate]').editable();
$('#product-base-tab a.editable[data-name=expiryDate]').editable();
cqlybest.editableTag('#product-base-tab a[data-name=departureCities]');
cqlybest.editableTag('#product-base-tab a[data-name=destinations]');
cqlybest.editableTag('#product-base-tab a[data-name=keywords]');
cqlybest.editableTag('#product-base-tab a[data-name=traffics]');
cqlybest.editableTag('#product-base-tab a[data-name=types]');
cqlybest.editableTag('#product-base-tab a[data-name=grades]');
$('#product-base-tab a.editable[data-name=recommendedMonths]').editable({
	inputclass: 'input-large',
	select2: {
		tags: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
	}
});
$('#product-base-tab a.editable[data-name=crowds]').editable({
	inputclass: 'input-large',
	select2: {
		tags: ['个人旅行', '团体旅行']
	}
});
</script>
