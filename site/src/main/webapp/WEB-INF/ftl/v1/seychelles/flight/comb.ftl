<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div id="page-content" class="clearfix">
	<div class="row-fluid">
		<h3 class="header smaller lighter blue">
			塞舌尔航班组合${result.total}
			<button type="button" class="btn btn-mini btn-primary pull-right" onclick="cqlybest.go('#main-content', '${ContextPath}/seychelles/flight/comb_update.do');">增加航班组合</button>
		</h3>
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th class="center" style="width:40px;"><input type="checkbox"><span class="lbl"></span></th>
					<th>航班号</th>
					<th>出发</th>
					<th>到达</th>
					<th>班期</th>
					<th>出发时间</th>
					<th>到达时间</th>
					<th>航空公司</th>
					<th>补充信息</th>
					<th class="center" style="width:80px;">操作</th>
				</tr>
			</thead>
			<tbody>
				<#list result.items as comb>
				<#assign flight=comb.transfers?first>
				<tr>
					<td class="center"><input type="checkbox" value="${flight.id}"><span class="lbl"></span></td>
					<td><span class="label label-success">往</span> ${flight.number!} <#if flight.nonStop!false><span class="label label-success">直飞</span></#if></td>
					<td>${flight.from!}${flight.departuresAirportCode!}</td>
					<td>${flight.to!}${flight.arrivalsAirportCode!}</td>
					<td><#if flight.day?has_content>${['星期一','星期二','星期三','星期四','星期五','星期六','星期日'][flight.day-1]}</#if></td>
					<td><#if flight.departuresTime?has_content>${flight.departuresTime?string('HH:mm')}</#if></td>
					<td><#if flight.arrivalsTime?has_content>${flight.arrivalsTime?string('HH:mm')} <span class="label label-success">＋${flight.arrivalsTime?string('d')}</span></#if></td>
					<td>${flight.airline!}（${flight.airlineCode!}）</td>
					<td>${flight.extra!}</td>
					<td class="td-actions center">
						<div class="btn-group">
							<button type="button" data-id="${comb.id}" class="btn btn-danger btn-mini" title="删除" data-action="delete">
								<i class="icon-trash bigger-120"></i>
							</button>
						</div>
					</td>
				</tr>
				<#assign flight=comb.transfers?last>
				<tr>
					<td> </td>
					<td><span class="label label-warning">返</span> ${flight.number!} <#if flight.nonStop!false><span class="label label-success">直飞</span></#if></td>
					<td>${flight.from!}${flight.departuresAirportCode!}</td>
					<td>${flight.to!}${flight.arrivalsAirportCode!}</td>
					<td><#if flight.day?has_content>${['星期一','星期二','星期三','星期四','星期五','星期六','星期日'][flight.day-1]}</#if></td>
					<td><#if flight.departuresTime?has_content>${flight.departuresTime?string('HH:mm')}</#if></td>
					<td><#if flight.arrivalsTime?has_content>${flight.arrivalsTime?string('HH:mm')} <span class="label label-success">＋${flight.arrivalsTime?string('d')}</span></#if></td>
					<td>${flight.airline!}（${flight.airlineCode!}）</td>
					<td>${flight.extra!}</td>
					<td> </td>
				</tr>
				</#list>
			</tbody>
		</table>
	</div>
</div>
<script>
$('#main-list-table button.btn-action-edit').click(function(){
	cqlybest.go('#main-content', '${ContextPath}/seychelles/flight/update.do?flightId=' + $(this).data('id'));
});
$('#page-content table').dataTable({
	iDeferLoading: ${result.total},
	iDisplayStart: ${result.start},
	iDisplayLength: ${result.pageSize},
	bLengthChange: false,
	bFilter: false,
	bServerSide: true,
	fnServerData: function (sSource, aoData, fnCallback, oSettings) {
		var p = {};
		$.each(aoData, function(i, obj){
			p[obj.name] = obj.value;
		});
		var q = {};
		q.page = p.iDisplayStart / p.iDisplayLength;
		cqlybest.go('#main-content', '${ContextPath}/seychelles/flight/comb.do' + $.param(q));
	}
});
$('#page-content button[data-action=delete]').click(function(){
	var id = $(this).data('id');
	bootbox.confirm('确认要删除？', function(result){
		if(result) {
			$.post('${ContextPath}/seychelles/flight/comb_delete.do', {
				id : id
			}).done(function(){
				cqlybest.reload('#main-content');
			}).fail(function(){
				cqlybest.error();
			});
		}
	});
});
</script>