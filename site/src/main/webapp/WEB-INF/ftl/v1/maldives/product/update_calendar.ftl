<div id="product-calendar-tab" class="tab-pane">
	<div class="row-fluid">
		<div class="span9">
			<div id="price-calendar"></div>
		</div>
		<div class="span3">
			<form method="POST" action="${ContextPath}/maldives/product/price.do">
				<input name="productId" type="hidden" value="${product.id}">
				<input name="delete" type="hidden" value="false">
				<label>开始日期：如 <small class="text-success">2008-08-08</small></label>
				<div class="input-append">
					<span class="add-on">
						<i class="icon-calendar bigger-110"></i>
					</span>
					<input name="start" type="text" class="input-medium" placeholder="年-月-日">
				</div>
				<div class="space"> </div>
				<label>结束日期：如 <small class="text-success">2008-08-08</small></label>
				<div class="input-prepend">
					<span class="add-on">
						<i class="icon-calendar bigger-110"></i>
					</span>
					<input name="end" type="text" class="input-medium" placeholder="年-月-日">
				</div>
				<div class="space"> </div>
				<label>成人价：如 <small class="text-success">¥10,000.00</small></label>
				<div class="input-prepend">
					<span class="add-on">
						<i class="icon-yen bigger-110"></i>
					</span>
					<input name="price" type="text" class="input-medium" placeholder="如：10,000.00">
				</div>
				<div class="space"> </div>
				<label>儿童价：如 <small class="text-success">¥5,000.00</small></label>
				<div class="input-prepend">
					<span class="add-on">
						<i class="icon-yen bigger-110"></i>
					</span>
					<input name="childPrice" type="text" class="input-medium" placeholder="如：5,000.00">
				</div>
				<div class="space"> </div>
				<label>
					<input name="special" type="checkbox" value="true">
					<span class="lbl"> 特价</span>
				</label>
				<div class="space"> </div>
				<button type="submit" class="btn btn-info">
					<i class="icon-ok bigger-110"></i> 保存
				</button>
				&nbsp;&nbsp;
				<button type="button" class="btn btn-danger delete">
					<i class="icon-trash bigger-110"></i> 删除
				</button>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
$('#price-calendar').fullCalendar({
	monthNames: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
	monthNamesShort: ['一','二','三','四','五','六','七','八','九','十','十一','十二'],
	dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
	dayNamesShort: ['日','一','二','三','四','五','六'],
	buttonText: {
		prev: '<i class="icon-chevron-left"></i>',
		next: '<i class="icon-chevron-right"></i>',
		today: '今天',
		month: '月',
		week: '周',
		day: '天'
	},
	header: {
		left: 'title',
		right: 'prev,next today'
	},
	events: [
	<#if product.priceCalendar?has_content>
		<#list product.priceCalendar as event>
		<#if event_index gt 0>,</#if>
		{title:'<#if event.special>特价<#else>成人价</#if>：¥${event.price/100}',allDay:true,start:new Date('${event.date}'),className:'label-success'}
		<#if event.childPrice?has_content>
		,{title:'儿童价：¥${event.childPrice/100}',allDay:true,start:new Date('${event.date}')}
		</#if>
		</#list>
	</#if>
	],
	dayClick: function(date, allDay, jsEvent, view) {
		var dayClicked = $('#price-calendar').data('dayClicked')||0;
		if (dayClicked%2==0) {
			$('#product-calendar-tab input[name=start]').val(date.format('yyyy-MM-dd'));
		} else {
			$('#product-calendar-tab input[name=end]').val(date.format('yyyy-MM-dd'));
		}
		$('#price-calendar').data('dayClicked', dayClicked+1);
	}
});
$('a[data-target="#product-calendar-tab"]').click(function(){
	if (!($(this).data('init')||false)) {
		$(this).data('init', true);
		setTimeout(function(){
			$('#price-calendar').fullCalendar('today');
		}, 300);
	}
});
$('#product-calendar-tab input[name=start],#product-calendar-tab input[name=end]').mask("9999-99-99")
$('#product-calendar-tab input[name=price],#product-calendar-tab input[name=childPrice]').maskMoney();
$('#product-calendar-tab form').submit(function() {
	$(this).ajaxSubmit({
		success: function(response, status, xhr, form) {
			cqlybest.reload('#main-content', function(){
				$('a[data-target="#product-calendar-tab"]').click();
			});
		},
		error: function(xhr, status, response, form) {
			cqlybest.error();
		}
	});
	return false;
});
$('#product-calendar-tab button.delete').click(function(){
	$('#product-calendar-tab input[name=delete]').val('true');
	$(this).closest('form').submit();
});
</script>