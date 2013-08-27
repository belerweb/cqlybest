<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div id="page-content" class="clearfix">
	<div class="row-fluid">
		<h3 class="header smaller lighter blue">
			缓存文件
			<#if cacheEnabled>
			<button type="button" class="btn btn-danger btn-mini pull-right" data-action="clean">清空缓存</button>
			</#if>
			<label class="pull-right">
				<input type="checkbox" <#if cacheEnabled>checked="checked"</#if>
					class="ace-switch ace-switch-7" data-action="enabled">
				<span class="lbl"></span>
			</label>
		</h3>
		<#if cacheEnabled>
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th>文件</th>
					<th class="center" style="width:200px;">缓存时间</th>
				</tr>
			</thead>
			<tbody>
				<#list caches as cache>
				<tr>
					<td>http:/${cache.name!}</td>
					<td class="center">${cache.time?string('yyyy-MM-dd HH:mm:ss')!}</td>
				</tr>
				</#list>
			</tbody>
		</table>
		<#else>
		<div class="alert alert-danger">缓存未开启</div>
		</#if>
	</div>
</div>
<script type="text/javascript">
$('#page-content input[data-action=enabled]').click(function(){
	var enabled = this.checked;
	$.post('${ContextPath}/system/settings/update.do', {
		name: 'cache.enabled',
		value: enabled
	}).done(function(){
		cqlybest.reload('#main-content', function(){
			if(enabled) {
				cqlybest.success('缓存已打开。');
			} else {
				cqlybest.success('缓存已关闭，缓存文件已被清空。');
			}
		});
	}).fail(function(){
	});
});
$('#page-content button[data-action=clean]').click(function(){
	bootbox.confirm('确定清空缓存文件？', '取消', '确认', function(result) {
		if (result) {
			$.post('${ContextPath}/system/cache/clean.do').done(function(){
				cqlybest.reload('#main-content');
			}).fail(function(){
			});
		}
	});
});
</script>