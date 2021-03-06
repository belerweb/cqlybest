<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div id="page-content" class="clearfix">
	<div class="row-fluid">
		<h3 class="header smaller lighter blue">
			毛里求斯航班
			<button type="button" class="btn btn-mini btn-primary pull-right" onclick="cqlybest.go('#main-content', '${ContextPath}/mauritius/flight/update.do');">增加航班</button>
		</h3>
		<table id="main-list-table" class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th class="center" style="width:40px;"><input type="checkbox"><span class="lbl"></span></th>
					<th>航班号</th>
					<th>出发</th>
					<th>到达</th>
					<th>出发时间</th>
					<th>到达时间</th>
					<th>航空公司</th>
					<th>补充信息</th>
					<th class="center" style="width:80px;">操作</th>
				</tr>
			</thead>
			<tbody>
				<#list result.items as flight>
				<tr>
					<td class="center"><input type="checkbox" value="${flight.id}"><span class="lbl"></span></td>
					<td>${flight.number!}</td>
					<td>${flight.from!}${flight.departuresAirportCode!}</td>
					<td>${flight.to!}${flight.arrivalsAirportCode!}</td>
					<td><#if flight.departuresTime?has_content>${flight.departuresTime?string('HH:mm')}</#if></td>
					<td><#if flight.arrivalsTime?has_content>${flight.arrivalsTime?string('HH:mm')} <span class="label label-success">＋${flight.arrivalsTime?string('d')}</span></#if></td>
					<td>${flight.airline!}（${flight.airlineCode!}）</td>
					<td>${flight.extra!}</td>
					<td class="td-actions center">
						<div class="btn-group">
							<button type="button" data-id="${flight.id}" class="btn btn-mini btn-info btn-action-edit" title="修改">
								<i class="icon-edit bigger-120"></i>
							</button>
						</div>
					</td>
				</tr>
				</#list>
			</tbody>
		</table>
	</div>
</div>
<script>
$('#main-list-table button.btn-action-edit').click(function(){
	cqlybest.go('#main-content', '${ContextPath}/mauritius/flight/update.do?flightId=' + $(this).data('id'));
});
$('#main-list-table').dataTable({
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
		cqlybest.go('#main-content', '${ContextPath}/mauritius/flight.do?' + $.param(q));
	}
});
</script>