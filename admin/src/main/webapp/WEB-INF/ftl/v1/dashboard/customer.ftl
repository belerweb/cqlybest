<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div id="${pageId}" class="widget-main customer">
	<div class="row-fluid">
		<div class="span9">
			<input name="keyword" value="${keyword!}" type="text" placeholder="姓名/手机号/邮箱..." class="span12 search-query">
		</div>
		<div class="span3">
			<button type="button" class="btn btn-purple btn-small span12" data-action="search">
				搜索 <i class="icon-search icon-on-right bigger-110"></i>
			</button>
		</div>
	</div>
	<#if keyword?has_content && result.total==0>
	<div class="alert alert-warning text-center" style="padding: 50px 0;margin:5px 0;">
		没有搜索到客户信息
	</div>
	</#if>
	<#if result?has_content && result.total gt 0>
	<div class="alert alert-success">
		共找到${result.total}个客户，仅显示前五个客户信息。
	</div>
	<#list result.items as customer>
	<h4 class="smaller lighter green">
		<i class="icon-use"></i> ${customer.fullname!}
	</h4>
	<table class="table">
		<tbody>
			<tr>
				<th>性别</th>
				<th>出生日期</th>
				<th>籍贯</th>
				<th>身份证</th>
			</tr>
			<tr>
				<td><#if customer.sex?has_content><#if customer.sex==1>男</#if><#if customer.sex==0>女</#if></#if></td>
				<td><#if customer.birth?has_content>${customer.birth.year}-${customer.birth.month}-${customer.birth.dayOfMonth}</#if></td>
				<td>${customer.ancestralLocation!}</td>
				<td>${customer.idCard!}</td>
			</tr>
			<tr>
				<th>手机号</th>
				<th>电子邮件</th>
				<th>QQ</th>
				<th>护照</th>
			</tr>
			<tr>
				<td>${customer.mobile!}</td>
				<td>${customer.email!}</td>
				<td>${customer.qq!}</td>
				<td>${customer.passport!}</td>
			</tr>
		</tbody>
	</table>
	</#list>
	</#if>
	<script type="text/javascript">
	$('#${pageId} button[data-action=search]').click(function(){
		var keyword = $.trim($('#${pageId} input[name=keyword]').val());
		if (keyword=='') {
			return;
		}
		$('#${pageId}').parent().load('${ContextPath}/dashboard/customer.do', {
			keyword:keyword
		});
	});
	</script>
</div>
