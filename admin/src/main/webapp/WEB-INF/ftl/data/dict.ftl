<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div id="dict-page">
	<div class="clearfix"></div>
	<div class="pagetitle">
		<h1>数据字典</h1>
		<div class="pull-right" style="margin-top:15px;">
			<a href="#m=site;n=data.dict;u=${ContextPath}/data/dict/destination.html;t=#dict-main" class="btn btn-primary">目的地</a>
			<a href="#m=site;n=data.dict;u=${ContextPath}/data/dict/traffic.html;t=#dict-main" class="btn btn-primary">交通方式</a>
			<a href="#m=site;n=data.dict;u=${ContextPath}/data/dict/product_type.html;t=#dict-main" class="btn btn-primary">产品类型</a>
			<a href="#m=site;n=data.dict;u=${ContextPath}/data/dict/product_grade.html;t=#dict-main" class="btn btn-primary">产品等级</a>
			<a href="#m=site;n=data.dict;u=${ContextPath}/data/dict/keyword.html;t=#dict-main" class="btn btn-primary">关键词</a>
		</div>
		<div class="clearfix"></div>
		<hr>
	</div>
	<div class="grid" id="dict-main" style="overflow-x: hidden;">
	</div>
</div>
