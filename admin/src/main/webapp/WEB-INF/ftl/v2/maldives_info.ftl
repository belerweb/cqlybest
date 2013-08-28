<div id="maldives-island-info－tab" class="maldives-island-section">
	<div class="title">
		<h4>岛屿详情</h4>
		<hr>
	</div>
	<table class="table table-condensed">
		<tbody>
			<tr>
				<th style="width: 75px;">中文名称：</th>
				<td style="width: 275px;">${island.zhName!}</td>
				<th style="width: 75px;">英文名称：</th>
				<td style="width: 275px;">${island.enName!}</td>
			</tr>
			<tr>
				<th>岛屿级别：</th>
				<td>${island.level!}</td>
				<th>上岛方式：</th>
				<td>${island.way!}</td>
			</tr>
			<tr>
				<th>岛屿大小：</th>
				<td>${island.area!}</td>
				<th>浮潜等级：</th>
				<td>${island.snorkeling!}</td>
			</tr>
			<tr>
				<th>参考价格：</th>
				<td colspan="3">${island.price!}</td>
			</tr>
			<tr>
				<td colspan="4">${island.description!}</td>
			</tr>
		</tbody>
	</table>
</div>