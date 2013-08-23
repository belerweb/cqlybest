<div id="product-calendar-tab" class="tab-pane">
	<div class="row-fluid">
		<div class="span6">
			<div class="calendar-container"></div>
		</div>
		<div class="span6">
			<div class="alert">第一次点击日历选择开始日期，第二次点击选择结束日期，然后在设定价格或删除价格。</div>
			<div class="control-group">
				<label class="control-label">开始日期：</label>
				<div class="controls">
					<input type="text" name="start" readonly="readonly">
					<div class="help-block"></div>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">开始日期：</label>
				<div class="controls">
					<input type="text" name="end" readonly="readonly">
					<div class="help-block"></div>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">价格：</label>
				<div class="controls">
					<input type="text" name="price">
					<div class="help-block"></div>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">儿童价：</label>
				<div class="controls">
					<input type="text" name="childPrice">
					<div class="help-block"></div>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">特价：</label>
				<div class="controls">
					<label class="checkbox"><input name="special" type="checkbox"> 是</label>
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<button type="button" class="btn btn-primary save">保存</button>
					<button type="button" class="btn btn-danger del">删除</button>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
var chooseDate = function(data) {
	var date = data.year + '-' + (data.month<10?'0'+data.month:data.month) + '-' + (data.day<10?'0'+data.day:data.day);
	var click = $('#product-calendar-tab .calendar').data('click')|0;
	if (click%2==0) {
		$('#product-calendar-tab input[name=start]').val(date);
	} else {
		$('#product-calendar-tab input[name=end]').val(date);
		var obj = $('td#day_' + data.day).data();
		$('#product-calendar-tab input[name=price]').val(obj.price);
		$('#product-calendar-tab input[name=childPrice]').val(obj.childPrice?obj.childPrice:'');
		$('#product-calendar-tab input[name=special]').attr('checked', obj.special);
	}
	$('#product-calendar-tab .calendar').data('click', click+1);
};

$('#product-calendar-tab button.save').click(function(){
	$.post('${ContextPath}/product/calendar/add.do', {
		productId: '${product.id}',
		start: $('#product-calendar-tab input[name=start]').val(),
		end: $('#product-calendar-tab input[name=end]').val(),
		price: $('#product-calendar-tab input[name=price]').val(),
		childPrice: $('#product-calendar-tab input[name=childPrice]').val(),
		special: $('#product-calendar-tab input[name=special]').is(':checked')
	}).done(function() {
		$('#product-calendar-tab input[name=start]').val('');
		$('#product-calendar-tab input[name=end]').val('');
		$('#product-calendar-tab input[name=price]').val('');
		$('#product-calendar-tab input[name=childPrice]').val('');
		 $('#product-calendar-tab input[name=special]').attr('checked', false);
		$('#product-calendar-tab .calendar-container').data('plugin_Calendar').renderCalendar(new Date());
	}).fail(function(xhr) {
		cqlybest.error(eval(xhr.responseText));
	});
});
$('#product-calendar-tab button.del').click(function(){
	$.post('${ContextPath}/product/calendar/delete.do', {
		productId: '${product.id}',
		start: $('#product-calendar-tab input[name=start]').val(),
		end: $('#product-calendar-tab input[name=end]').val()
	}).done(function() {
		$('#product-calendar-tab .calendar-container').data('plugin_Calendar').renderCalendar(new Date());
	}).fail(function(xhr) {
		cqlybest.error(eval(xhr.responseText));
	});
});
</script>