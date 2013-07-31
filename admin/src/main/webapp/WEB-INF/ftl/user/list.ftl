<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div>
	<div class="clearfix"></div>
	<div class="pagetitle">
		<h1>用户列表</h1>
		<div class="btn-group">
			<a id="user-add" href="javascript:void(0);" class="btn btn-primary">增加用户</a>
		</div>
		<div class="clearfix"></div>
		<hr>
	</div>
	<div class="grid">
		<div class="grid-title">
			<div class="pull-left">
				<div class="icon-title"><i class="icon-table"></i></div>
				<span>用户列表</span> 
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
						<th>姓名/社名/团体名...</th>
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
						<td>
							<#list user.roles as role>
							<span class="label label-success">${role.name}</span>
							</#list>
							${user.fullname!}
						</td>
						<td>${user.cellPhone!}</td>
						<td>${user.email!}</td>
						<td>${user.loginUsername!}</td>
						<td>${user.nickname!}</td>
						<td class="action-table">
							<a href="javascript:void(0);" data-url="${ContextPath}/user/toggleadmin.do?id=${user.id}"
								class="ajax-action-btn danger" data-confirm="true" data-action="设置/取消管理员" data-title=""
								title="设置/取消管理员"><i class="icon-user-md"></i></a>
							<a href="javascript:void(0);" data-url="${ContextPath}/user/update.do?id=${user.id}"
								class="page-load-btn safe" data-target="#mb" title="修改"><i class="icon-edit"></i></a>
							<a href="javascript:void(0);" data-url="${ContextPath}/user/delete.do?id=${user.id}"
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
	var action = $(this).parent();
	var form = ['<form class="form-horizontal"><legend>添加帐号</legend>'];
	form.push('<div class="control-group"><label class="control-label">姓名/社名/团体名...：</label>');
	form.push('<div class="controls"><input type="text" name="name"></div></div>');
	form.push('<div class="control-group"><label class="control-label">类型（不可更改）：</label>');
	form.push('<div class="controls"><label class="radio inline"><input type="radio" name="role" checked="checked"> 普通用户</label>');
	form.push('<label class="radio inline"><input type="radio" name="role" value="ROLE_GROUP"> 团体</label>');
	form.push('<label class="radio inline"><input type="radio" name="role" value="ROLE_AGENCY"> 旅行社</label>');
	form.push('</div></div></form>');
	var dialog = bootbox.dialog(form.join(''), [{
		label: '取消'
	}, {
		label: '确定',
		callback: function(){
			var name = $.trim($('input[name=name]', dialog).val());
			var role = $.trim($('input[name=role]:checked', dialog).val());
			if (!/^.{1,128}$/.test(name)) {
				bootbox.alert('请输入姓名/社名/团体名，且长度不超过128位');
				return false;
			}
			$.post('${ContextPath}/user/add.do', {
				name: name,
				role: role
			}).done(function(data){
				var hash = cqlybest.parseHash();
				hash['u'] = '${ContextPath}/user/update.do?id=' + data;
				hash['_t'] = new Date().getTime();
				location.hash = cqlybest.buildHash(hash);
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
</script>