<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div id="page-content" class="clearfix">
	<div class="row-fluid">
		<h3 class="header smaller lighter blue">
			毛里求斯航班信息
			<button type="button" class="btn btn-mini btn-primary pull-right" onclick="cqlybest.go('#main-content');">
				<i class="icon-mail-reply"></i> 返回
			</button>
		</h3>
		<form id="ajax-form" method="POST" action="${ContextPath}/mauritius/flight/update.do" class="form-horizontal">
			<input name="id" type="hidden" value="${(flight.id)!}">
			<div class="control-group">
				<label class="control-label">航班号：</label>
				<div class="controls">
					<input name="number" type="text" placeholder="航班号" value="${(flight.number)!}">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">出发地：</label>
				<div class="controls">
					<input name="from" type="text" placeholder="出发城市" value="${(flight.from)!}" style="width:130px;">
					<input name="departuresAirportCode" type="text" placeholder="三字码" value="${(flight.departuresAirportCode)!}" style="width:60px">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">目的地：</label>
				<div class="controls">
					<input name="to" type="text" placeholder="目的城市" value="${(flight.to)!}" style="width:130px;">
					<input name="arrivalsAirportCode" type="text" placeholder="三字码" value="${(flight.arrivalsAirportCode)!}" style="width:60px">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">出发时间：</label>
				<div class="controls">
					<input name="departuresTime" type="hidden">
					<input id="departures-time-hour" type="text" class="input-mini" value="<#if (flight.departuresTime)?has_content>${flight.departuresTime?string('H')}<#else>0</#if>" />
					<span class="help-inline"><strong>：</strong></span>
					<input id="departures-time-minute" type="text" class="input-mini" value="<#if (flight.departuresTime)?has_content>${flight.departuresTime?string('m')}<#else>0</#if>" />
					<span class="help-inline">当地时间（几点几分）</span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">到达时间：</label>
				<div class="controls">
					<input name="arrivalsTime" type="hidden">
					<input id="arrivals-time-day" type="text" class="input-mini" value="<#if (flight.arrivalsTime)?has_content>${flight.arrivalsTime?string('d')}<#else>1</#if>" />
					<span class="help-inline"><strong>：</strong></span>
					<input id="arrivals-time-hour" type="text" class="input-mini" value="<#if (flight.arrivalsTime)?has_content>${flight.arrivalsTime?string('H')}<#else>0</#if>" />
					<span class="help-inline"><strong>：</strong></span>
					<input id="arrivals-time-minute" type="text" class="input-mini" value="<#if (flight.arrivalsTime)?has_content>${flight.arrivalsTime?string('m')}<#else>0</#if>" />
					<span class="help-inline">当地时间（第几天几点几分，1表示当天，2表示第二天）</span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">航空公司：</label>
				<div class="controls">
					<input name="airline" type="text" placeholder="名称" value="${(flight.airline)!}" style="width:130px;">
					<input name="airlineCode" type="text" placeholder="代码" value="${(flight.airlineCode)!}" style="width:60px;">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">补充信息：</label>
				<div class="controls">
					<textarea name="extra" placeholder="">${(flight.extra)!}</textarea>
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
$('#ajax-form').submit(function() {
	var departuresTime = $('#departures-time-hour').val()*3600000 + $('#departures-time-minute').val()*60000;
	departuresTime = departuresTime - 30600000;
	$('#ajax-form input[name=departuresTime]').val(departuresTime);

	var arrivalsTime = ($('#arrivals-time-day').val()-1)*86400000 + $('#arrivals-time-hour').val()*3600000 + $('#arrivals-time-minute').val()*60000;
	arrivalsTime = arrivalsTime - 30600000;
	$('#ajax-form input[name=arrivalsTime]').val(arrivalsTime);

	$(this).ajaxSubmit({
		success: function(response, status, xhr, form) {
			cqlybest.success("航班保存成功，可继续添加。返回航班列表请点 [返回] 按钮。");
			form.clearForm();
			$('#ajax-form input[name=id]').val('');
			$('#ajax-form input[name=departuresTime]').val('');
			$('#ajax-form input[name=arrivalsTime]').val('');
		},
		error: function(xhr, status, response, form) {
			cqlybest.error();
		}
	});
	return false;
});
$('#departures-time-hour').ace_spinner({min:0, max:23, value:$('#departures-time-hour').val()});
$('#departures-time-minute').ace_spinner({min:0, max:59, value:$('#departures-time-minute').val()});
$('#arrivals-time-day').ace_spinner({min:1, max:10, value:$('#arrivals-time-day').val()});
$('#arrivals-time-hour').ace_spinner({min:0, max:23, value:$('#arrivals-time-hour').val()});
$('#arrivals-time-minute').ace_spinner({min:0, max:59, value:$('#arrivals-time-minute').val()});
</script>