<div id="product-traffic-tab" class="tab-pane">
	<div class="text-right"><button type="button" class="btn btn-primary add">添加</button></div>
	<table class="table">
		<thead>
			<tr>
				<th>名称</th>
				<th>出发地</th>
				<th>目的地</th>
				<th>类型</th>
				<th>班次</th>
				<th>出发时间</th>
				<th>到达时间</th>
				<th>补充信息</th>
				<th>删除</th>
			</td>
		</thead>
		<tbody>
			<#assign PTURL="${ContextPath}/product/traffic/update.do" />
			<#if product.trafficList?has_content>
			<#list product.trafficList as traffic>
			<tr>
				<td>
					<a href="#" class="editable" data-pk="${traffic.id}" data-name="name" data-type="text" data-url="${PTURL}" data-value="${traffic.name!}"></a>
				</td>
				<td>
					<a href="#" class="editable" data-pk="${traffic.id}" data-name="departure" data-type="text" data-url="${PTURL}" data-value="${traffic.departure!}"></a>
				</td>
				<td>
					<a href="#" class="editable" data-pk="${traffic.id}" data-name="destination" data-type="text" data-url="${PTURL}" data-value="${traffic.destination!}"></a>
				</td>
				<td>
					<a href="#" class="editable" data-pk="${traffic.id}" data-name="type" data-type="select" data-url="${PTURL}" data-value="${traffic.type!}"></a>
				</td>
				<td>
					<a href="#" class="editable" data-pk="${traffic.id}" data-name="flights" data-type="text" data-url="${PTURL}" data-value="${traffic.flights!}"></a>
				</td>
				<td>
					<a href="#" class="editable" data-pk="${traffic.id}" data-name="departureTime" data-type="combodate" data-url="${PTURL}"
						data-value="<#if traffic.departureTime?has_content>${traffic.departureTime?string('HH:mm')}</#if>" data-format="HH:mm" data-template="HH : mm"></a>
				</td>
				<td>
					<a href="#" class="editable" data-pk="${traffic.id}" data-name="landingTime" data-type="combodate" data-url="${PTURL}"
						data-value="<#if traffic.landingTime?has_content>${traffic.landingTime?string('dd HH:mm')}</#if>" data-format="DD HH:mm" data-viewformat="第D天 HH:mm" data-template="DD HH : mm"></a>
				</td>
				<td>
					<a href="#" class="editable" data-pk="${traffic.id}" data-name="extra" data-type="text" data-url="${PTURL}">${traffic.extra!}</a>
				</td>
				<td>
					<button data-id="${traffic.id}" type="button" class="btn btn-danger delete">删除</button>
				</td>
			</tr>
			</#list>
			</#if>
		</tbody>
	</table>
</div>
<script type="text/javascript">
$('#product-traffic-tab button.add').click(function(){
	var action = $(this).parent();
	var form = ['<form class="form-horizontal"><legend>添加交通</legend>'];
	form.push('<div class="control-group"><label class="control-label">名称：</label>');
	form.push('<div class="controls"><input type="text" name="name" placeholder="如：第一天"></div></div>');
	form.push('<div class="control-group"><label class="control-label">类型：</label>');
	form.push('<div class="controls"><label class="radio inline"><input type="radio" name="type" value="1"> 火车</label>');
	form.push('<label class="radio inline"><input type="radio" name="type" value="2"> 飞机</lavel>');
	form.push('</div></div></form>');
	var dialog = bootbox.dialog(form.join(''), [{
		label: '取消'
	}, {
		label: '确定',
		callback: function(){
			var name = $.trim($('input[name=name]', dialog).val());
			var type = $.trim($('input[name=type]:checked', dialog).val());
			$.post('${ContextPath}/product/traffic/add.do', {
				productId: '${product.id}',
				name: name,
				type: type
			}).done(function(obj){
				$('#mb').load('${ContextPath}/product/update.do?id=${product.id}', function(){
					$('#product-update-tabs a[data-target="#product-traffic-tab"]').tab('show');
				});
			}).fail(function() {
				cqlybest.error();
			});
		}
	}]);
	$('form', dialog).on('submit', function(e) {
		e.preventDefault();
		dialog.find(".btn-primary").click();
	});
});
$('#product-traffic-tab a.editable').not('[data-type=select]').not('[data-type=combodate]').editable();
$('#product-traffic-tab a[data-name=type]').editable({
	prepend: '',
	source: [{value:1, text: '火车'}, {value:2, text: '飞机'}]
});
$('#product-traffic-tab a[data-type=combodate]').editable({
	combodate: {
		minuteStep: 1
	}
});
$('#product-traffic-tab button.delete').on('click', function() {
	var id = $(this).attr('data-id');
	var el = $($(this).parents('tr').get(0));
	bootbox.confirm('确认删除交通？', '取消', '确认', function(result) {
		if (result) {
			$.post('${ContextPath}/product/traffic/delete.do', {
				id: id
			}).done(function(){
				el.remove();
			}).fail(function() {
				cqlybest.error();
			});
		}
	});
});
</script>