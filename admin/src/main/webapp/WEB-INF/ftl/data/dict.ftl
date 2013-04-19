<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div id="dict-page">
	<div class="clearfix"></div>
	<div class="pagetitle">
		<h1>数据字典</h1>
		<div class="btn-group" data-toggle="buttons-radio">
			<a href="#m=site;n=data.dict;u=${ContextPath}/data/dict/destination.html;t=#dict-main" class="btn btn-primary active">目的地</a>
		</div>
		<div class="clearfix"></div>
		<hr>
	</div>
	<div class="grid" id="dict-main" style="overflow-x: hidden;">
	</div>
</div>
<script>
location.hash = $('#dict-page .pagetitle .btn-group > a.active').attr('href');
</script>