<#if data?has_content>
<table id="data-table" class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<#list 0..(data[0]?size-1) as i>
			<th>
				<select style="width:100%;">
					<option value=""></option>
					<option value="fullname">姓名</option>
					<option value="sex">性别</option>
					<option value="idCard">身份证</option>
					<option value="passport">护照</option>
					<option value="mobile">手机号</option>
					<option value="delete">删除整列</option>
				</select>
			</th>
			</#list>
			<th>删除行</th>
		</tr>
	</thead>
	<tbody>
	<#list data as row>
		<tr>
			<#list row as cell>
			<td>${cell!'&nbsp;'}</td>
			</#list>
			<td class="td-actions text-center">
				<div class="btn-group">
					<button type="button" class="btn btn-mini btn-danger" data-action="delete">
						<i class="icon-trash bigger-120"></i>
					</button>
				</div>
			</td>
		</tr>
	</#list>
	</tbody>
</table>
</#if>
<#if !data?has_content>
<div class="alert alert-error text-center" style="padding:100px 0;">
	<strong>未解析出任何数据!</strong>
</div>
</#if>
<script type="text/javascript">
$('#data-table .td-actions button[data-action=delete]').click(function(){
	$(this).closest('tr').remove();
});
$('#data-table select').change(function(){
	if ($(this).val()=='delete') {
		var colnum = $(this).parent().prevAll('th').length;
		$('#data-table').find('thead tr th:eq(' + colnum + ')').remove();
		$('#data-table tr').each(function(){
			$(this).find('td:eq(' + colnum + ')').remove();
		});
	}
});
</script>