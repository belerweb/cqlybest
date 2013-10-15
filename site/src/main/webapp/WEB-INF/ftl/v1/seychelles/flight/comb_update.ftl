<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div id="page-content" class="clearfix">
	<div class="row-fluid">
		<h3 class="header smaller lighter blue">
			塞舌尔航班组合信息
			<button type="button" class="btn btn-mini btn-primary pull-right" onclick="cqlybest.go('#main-content');">
				<i class="icon-mail-reply"></i> 返回
			</button>
		</h3>
		<form method="POST" action="${ContextPath}/seychelles/flight/comb_update.do" class="form-horizontal">
			<input name="id" type="hidden" value="${(comb.id)!}">
			<div class="control-group">
				<label class="control-label">去程：</label>
				<div class="controls">
					<input name="transfers" type="hidden" class="input-large">
					<input name="day" type="hidden" class="input-medium">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">返程：</label>
				<div class="controls">
					<input name="transfers" type="hidden" class="input-large">
					<input name="day" type="hidden" class="input-medium">
				</div>
			</div>
			<div class="form-actions">
				<button type="submit" class="btn btn-info">
					<i class="icon-ok bigger-110"></i> 保存
				</button>
				<button type="button" class="btn" onclick="cqlybest.go('#main-content');">
					<i class="icon-mail-reply bigger-110"></i> 返回
				</button>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
$('#page-content input[name=transfers]').on('change', function(e){
	var days = ['星期一','星期二','星期三','星期四','星期五','星期六','星期日'];
	var text = e.added.text;
	var schedule = [];
	$.each(eval(text.substring(text.indexOf('['))), function(i, obj){
		schedule.push({id:obj, text:days[obj-1]});
	});
	$(this).nextAll('input[name=day]').select2('destroy').css('display','').val('').select2({
		data:schedule
	});
}).select2({
	formatSearching: function () { return '搜索中...'; },
	formatNoMatches: function () { return '没有相关航班'; },
	ajax: {
		url: '${ContextPath}/seychelles/flight/ajax.do',
		data: function (term, page) {
			return {q:term};
		},
		results: function(response) {
			var result = [];
			$.each(response, function(i, obj){
				var text = obj.number + '-班期' + '[';
				if (!!obj.schedule) {
					text = text + obj.schedule.join(',');
				}
				text = text + ']';
				result.push({id:obj.id,text:text});
			});
			return {results:result};
		}
	}
});
$('#page-content form').submit(function() {
	$(this).ajaxSubmit({
		success: function(response, status, xhr, form) {
			cqlybest.success("保存成功，可继续添加。返回列表请点 [返回] 按钮。");
			form.clearForm();
		},
		error: function(xhr, status, response, form) {
			cqlybest.error();
		}
	});
	return false;
});
</script>