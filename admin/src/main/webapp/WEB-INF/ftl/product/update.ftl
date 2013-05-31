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
</script>