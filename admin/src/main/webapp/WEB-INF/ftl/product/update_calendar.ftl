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
	}
	$('#product-calendar-tab .calendar').data('click', click+1);
};
$('#product-calendar-tab .calendar-container').Calendar({
	events: function() {
		var calendar = {event:[]};
		$.ajax({
			async: false,
			type: 'GET',
			url: '${ContextPath}/product/calendar.do?id=${product.id}',
			success: function(data) {
				$.each(data, function(i, obj){
					var cal = {date: $.format.date(new Date(obj.date), 'yyyy-MM-dd')};
					if (obj.price) {
						cal.price = obj.price/100;
					} else {
						cal.price = <#if (product.price)?exists>${(product.price/100)?string('0.00')}<#else>''</#if>;
					}
					calendar.event.push(cal);
				});
			}
		});
		return calendar;
	}
}).on('changeDay', chooseDate).on('onEvent', chooseDate);

$('#product-calendar-tab button.save').click(function(){
	$.post('${ContextPath}/product/calendar/add.do', {
		productId: '${product.id}',
		start: $('#product-calendar-tab input[name=start]').val(),
		end: $('#product-calendar-tab input[name=end]').val(),
		price: $('#product-calendar-tab input[name=price]').val()
	}, function() {
		$('#product-calendar-tab .calendar-container').data('plugin_Calendar').renderCalendar(new Date());
	});
});
$('#product-calendar-tab button.del').click(function(){
	$.post('${ContextPath}/product/calendar/delete.do', {
		productId: '${product.id}',
		start: $('#product-calendar-tab input[name=start]').val(),
		end: $('#product-calendar-tab input[name=end]').val()
	}, function() {
		$('#product-calendar-tab .calendar-container').data('plugin_Calendar').renderCalendar(new Date());
	});
});
</script>