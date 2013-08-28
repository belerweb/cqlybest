<div id="maldives-island-traffic－tab" class="maldives-island-section">
	<div class="title">
		<h4>交通方式</h4>
	</div>
	<table class="table table-condensed">
		<tbody>
			<#list product.trafficList as traffic>
			<tr>
			<#if traffic.type?exists>
				<#if traffic.type==1>
				<td>${traffic.name!}</td>
				<td>${traffic.departure!} - ${traffic.destination!}</td>
				<td>${traffic.flights!}</td>
				<td>
					<#if traffic.departureTime?has_content>${traffic.departureTime?string('HH:mm')} 发车</#if>
					-
					<#if traffic.landingTime?has_content>
					<#assign day=traffic.landingTime?string('d')?number>
					<#if day gt 1>
					<span class="label label-info"><#if day==2>次日<#else>第${day}天</#if></span>
					</#if>
					${traffic.landingTime?string('HH:mm')} 到达</#if>
				</td>
				<td>${traffic.extra!}</td>
				</#if>
				<#if traffic.type==2>
				<td>${traffic.name!}</td>
				<td>${traffic.departure!} - ${traffic.destination!}</td>
				<td>${traffic.flights!}</td>
				<td>
					<#if traffic.departureTime?has_content>${traffic.departureTime?string('HH:mm')} 起飞</#if>
					-
					<#if traffic.landingTime?has_content>${traffic.landingTime?string('HH:mm')} 降落</#if>
				</td>
				<td>${traffic.extra!}</td>
				</#if>
				<#else>
				<td>${traffic.name!}</td>
				<td colspan="4">${traffic.extra!}</td>
			</#if>
			</tr>
			</#list>
		</tbody>
	</table>
</div>