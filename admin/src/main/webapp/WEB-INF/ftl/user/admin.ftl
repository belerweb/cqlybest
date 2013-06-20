<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div>
	<div class="clearfix"></div>
	<div class="pagetitle">
		<h1>管理员</h1>
		<div class="btn-group">
			<a id="user-add" href="javascript:void(0);" class="btn btn-primary">增加管理员</a>
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
			<table id="main-list-table" class="table table-striped table-condensed">
				<thead>
					<tr>
						<th>姓名</th>
						<th>手机号</th>
						<th>电子邮件</th>
						<th>用户名</th>
						<th>昵称</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<#list users as user>
					<tr>
						<td>${user.fullname!}</td>
						<td>${user.cellPhone!}</td>
						<td>${user.email!}</td>
						<td>${user.loginUsername!}</td>
						<td>${user.nickname!}</td>
						<td class="action-table">
							<a href="javascript:void(0);" data-url="${ContextPath}/user/admin/update.do?id=${user.id}"
								class="page-load-btn safe" data-target="#mb" title="修改"><i class="icon-edit"></i></a>
							<a href="javascript:void(0);" data-url="${ContextPath}/user/admin/delete.html?id=${user.id}"
								class="ajax-action-btn danger last" data-confirm="true" data-action="删除" data-title="${user.fullname!}"
								title="删除"><i class="icon-remove"></i></a>
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
$('#user-add').click(function(){
	bootbox.prompt("管理员姓名", "取消", "确定", function(result) {
		var name = $.trim(result);
		if (name.length) {
			$.post('${ContextPath}/user/add.do', {
				role: 'admin',
				name: name
			}, function(response){
				var hash = cqlybest.parseHash();
				hash['u'] = '${ContextPath}/user/admin/update.do?id=' + response;
				hash['_t'] = new Date().getTime();
				location.hash = cqlybest.buildHash(hash);
			});
		}
	});
});
</script>