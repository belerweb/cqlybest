<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div>
	<div class="clearfix"></div>
	<div class="pagetitle">
		<h1>管理员</h1>
		<div class="btn-group">
			<a href="#m=user;n=user.administrators;u=${ContextPath}/administrator/add.html;t=#main" class="btn btn-primary">增加管理员</a>
		</div>
		<div class="clearfix"></div>
		<hr>
	</div>
	<div class="grid">
		<div class="grid-title">
			<div class="pull-left">
				<div class="icon-title"><i class="icon-table"></i></div>
				<span>管理员列表</span> 
				<div class="clearfix"></div>
			</div>
			<div class="pull-right"> 
				<div class="icon-title"><a href="#" title="删除"><i class="icon-remove"></i></a></div>
			</div>
			<div class="clearfix"></div>   
		</div>
		<div class="grid-content overflow">
			<table id="admin-list-table" class="table table-striped table-condensed">
				<thead>
					<tr>
						<th><input id="admin-check" type="checkbox"><label for="admin-check"><span></span></label></th>
						<th>姓名</th>
						<th>手机号</th>
						<th>电子邮件</th>
						<th>用户名</th>
						<th>昵称</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<#list admins as admin>
					<tr>
						<td><input id="admin-check-${admin_index}" type="checkbox"><label for="admin-check-${admin_index}"><span></span></label></td>
						<td>${admin.fullname!}</td>
						<td>${admin.cellPhone!}</td>
						<td>${admin.email!}</td>
						<td>${admin.loginUsername!}</td>
						<td>${admin.nickname!}</td>
						<td class="action-table">
							<a href="#"><img alt="" src="images/icon/table_edit.png"></a>
							<a href="#"><img alt="" src="images/icon/table_del.png"></a>
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
$('#admin-list-table').dataTable({
	oLanguage:{
		oPaginate: {
			sFirst: '首页',
			sLast: '尾页',
			sNext: '下一页',
			sPrevious: '上一页'
		}
	},
	iDeferLoading: 10000,
	iDisplayStart: 0,
	iDisplayLength: 20,
	sPaginationType: 'full_numbers',
	bLengthChange: false,
	bFilter: false,
	bInfo: false,
	bSort: false,
	bServerSide: true
});
</script>