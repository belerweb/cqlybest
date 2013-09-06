<div id="maldives-island-traffic－tab" class="maldives-island-section">
	<div class="title">
		<h4>交通方式</h4>
	</div>
	<table class="table table-condensed">
		<tbody>
			<#list product.transportations as tran>
			<tr>
				<td>${tran.name!}</td>
				<td>${(tran.detail.from)!} - ${(tran.detail.to)!}</td>
				<td>${(tran.detail.number)!}</td>
				<td>
					<#if (tran.detail.departuresTime)?has_content>${tran.detail.departuresTime?string('HH:mm')} 起飞</#if>
					-
					<#if (tran.detail.arrivalsTime)?has_content>${tran.detail.arrivalsTime?string('HH:mm')} 降落</#if>
				</td>
				<td>${tran.extra!}</td>
			</tr>
			</#list>
		</tbody>
	</table>
</div>