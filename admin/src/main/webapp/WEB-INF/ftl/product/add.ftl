<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div>
	<div class="clearfix"></div>
	<div class="grid">
		<div class="grid-title">
			<div class="pull-left">
				<div class="icon-title"><i class="icon-plus"></i></div>
				<span>增加新 产品</span> 
				<div class="clearfix"></div>
			</div>
			<div class="pull-right"> 
				<div class="icon-title"><a href="javascript:history.go(-1);" title="返回产品列表"><i class="icon-chevron-left"></i></a></div>
			</div>
			<div class="clearfix"></div>   
		</div>
		<!-- novalidate -->
		<form id="product-add-form" action="${ContextPath}/product/add.html" method="post" class="form-horizontal grid-content">
			<div class="row-fluid">
				<div class="span6">
					<div class="control-group">
						<label class="control-label">产品名称：</label>
						<div class="controls">
							<input type="text" class="span" name="name" value="" required="true">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">行程天数：</label>
						<div class="controls">
							<input type="text" class="span6" name="days" value="1">
							<select name="daysUnit" class="span6">
								<option value="d">天</option>
								<option value="m">月</option>
								<option value="y">年</option>
							</select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">价格：</label>
						<div class="controls">
							<div class="input-prepend">
								<span class="add-on">￥</span>
								<input type="text" class="span" name="price">
							</div>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">费用说明：</label>
						<div class="controls">
							<textarea rows="3" name="priceDescription" class="span input same-height-1">${(site.statisticalCode)!}</textarea>
						</div>
					</div>
				</div>
				<div class="span6">
				</div>
			</div>
			<div class="row-fluid">
				<div class="span12">
					<div class="control-group">
						<label class="control-label">旅游介绍/产品描述</label>
						<div class="controls">
							<script type="text/plain" id="product-description" name="description"></script>
						</div>
					</div>
				</div>
			</div>
			<div class="text-center">
				<br>
				<button class="btn btn-primary" type="submit">保存</button>
			</div>
		</form>
	</div>
</div>
<script>
var pdEditor = UE.getEditor('product-description', {
	initialContent: '',
	initialFrameWidth: '100%'
});
$('input,textarea,select', '#product-add-form').jqBootstrapValidation({
	submitSuccess : function($form, event) {
		event.preventDefault();
		event.stopPropagation();
		pdEditor.sync();
		$form.ajaxSubmit({
			success : function(response){
				alert('保存成功');
			},
			error : function() {
				alert('保存失败');
			}
		});
	}
});
</script>