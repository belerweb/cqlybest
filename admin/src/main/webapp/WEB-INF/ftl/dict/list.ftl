<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div id="dict-page">
	<div class="clearfix"></div>
	<div class="pagetitle">
		<h1>数据字典</h1>
		<div class="pull-right" style="margin-top:15px;">
			<a href="#m=site&n=dict.list&u=${ContextPath}/dict/list.html%3Ftype%3Dtag&t=%23main" class="btn btn-primary">关键词</a>
			<a href="#m=site&n=dict.list&u=${ContextPath}/dict/list.html%3Ftype%3Ddeparture-city&t=%23main" class="btn btn-primary">出发城市</a>
			<a href="#m=site&n=dict.list&u=${ContextPath}/dict/list.html%3Ftype%3Ddestination&t=%23main" class="btn btn-primary">目的地</a>
			<a href="#m=site&n=dict.list&u=${ContextPath}/dict/list.html%3Ftype%3Dtraffic&t=%23main" class="btn btn-primary">交通方式</a>
			<a href="#m=site&n=dict.list&u=${ContextPath}/dict/list.html%3Ftype%3Dproduct-type&t=%23main" class="btn btn-primary">产品类型</a>
			<a href="#m=site&n=dict.list&u=${ContextPath}/dict/list.html%3Ftype%3Dproduct-grade&t=%23main" class="btn btn-primary">产品等级</a>
		</div>
		<div class="clearfix"></div>
		<hr>
	</div>
	<div class="grid" id="dict-main" style="overflow-x: hidden;">
		<div class="grid-title">
			<div class="pull-left">
				<div class="icon-title"><i class="icon-table"></i></div>
				<span>列表</span> 
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>   
		</div>
		<div class="grid-content overflow">
			<form id="main-content-form" action="${ContextPath}/dict/add.do" method="post" class="form-horizontal">
			<table id="main-list-table" class="table table-striped">
				<thead>
					<tr>
						<th>列表</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<input type="hidden" name="type" value="${type}">
							<input type="text" name="name" required="true" maxlength="32">
						</td>
						<td class="action-table">
							<button type="submit" class="btn btn-primary">保存</button>
						</td>
					</tr>
					<#list dicts as dict>
					<tr>
						<td>${dict.name!}</td>
						<td class="action-table">
							<a href="javascript:void(0);" data-url="${ContextPath}/dict/delete.do?id=${dict.id}"
								class="ajax-action-btn danger last" data-confirm="true" data-action="删除"
								data-title="${dict.name!}" title="删除"><i class="icon-remove"></i></a>
						</td>
					</tr>
					</#list>
				</tbody>
			</table>
			</form>
			<div class="clearfix"></div>
		</div>
	</div>
</div>
<script type="text/javascript">
$('#main-list-table').dataTable({
	bLengthChange: false,
	bFilter: false,
	bInfo: false,
	bPaginate: false
});
$('input,textarea,select', '#main-content-form').jqBootstrapValidation({
	submitSuccess : function($form, event) {
		event.preventDefault();
		event.stopPropagation();
		$form.ajaxSubmit({
			success : function(response) {
				cqlybest.success(null, null, function(){
					$('#mb').load('${ContextPath}/dict/list.html?type=${type}');
				});
			},
			error : function() {
				cqlybest.error();
			}
		});
	}
});
</script>
