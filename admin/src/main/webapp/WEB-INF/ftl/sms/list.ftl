<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div>
	<div class="clearfix"></div>
	<div class="pagetitle">
		<h1>短信历史</h1>
		<div class="clearfix"></div>
		<hr>
	</div>
	<div class="grid">
		<div class="grid-title">
			<div class="pull-left">
				<div class="icon-title"><i class="icon-table"></i></div>
				<span>短信列表</span> 
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>   
		</div>
		<div class="grid-content overflow">
			<table id="main-list-table" class="table table-striped">
				<colgroup>
					<col width="" />
					<col width="" />
					<col width="" />
					<col width="80" />
					<col width="170" />
					<col width="120" />
				</colgroup>
				<thead>
					<tr>
						<th>时间</th>
						<th>发信人</th>
						<th>收信人</th>
						<th>收信人号码</th>
						<th>短信内容</th>
						<th>状态</th>
					</tr>
				</thead>
				<tbody>
					<#list smss as sms>
					<tr>
						<td>${sms.sentDate?string('yyyy-MM-dd HH:mm:ss')}</td>
						<td>${sms.from!}</td>
						<td>${sms.to!}</td>
						<td>${sms.phone!}</td>
						<td>${sms.content!}</td>
						<td>
							<#if sms.success>
							<span class="s_green">发送成功</span>
							<#else>
							<span class="s_gray">发送失败</span>
							</#if>
						</td>
					</tr>
					</#list>
				</tbody>
			</table>
			<div class="clearfix"></div>
		</div>
	</div>
</div>
<script>
$('#main-list-table').dataTable({
	iDeferLoading: ${total},
	iDisplayStart: ${(page-1)*pageSize},
	iDisplayLength: ${pageSize},
	bLengthChange: false,
	bFilter: false,
	bServerSide: true,
	fnServerData: function (sSource, aoData, fnCallback, oSettings) {
		var p = {};
		$.each(aoData, function(i, obj){
			p[obj.name] = obj.value;
		});
		var q = {};
		q.page = p.iDisplayStart / p.iDisplayLength + 1;
		var u = '${ContextPath}/sms/list.html?' + $.param(q);
		var hash  = {
			m: 'site',
			n: 'product.list',
			t: '#main',
			u: encodeURIComponent(u)
		};
		location.hash = cqlybest.buildHash(hash);
	}
});
</script>