<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div>
	<div class="clearfix"></div>
	<div class="grid">
		<div class="grid-title">
			<div class="pull-left">
				<div class="icon-title"><i class="icon-edit"></i></div>
				<span>修改菜单</span> 
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>   
		</div>
		<div class="grid-content">
			<#if menu.menuType==0>
			<form action="${ContextPath}/menu/modify.html" method="post" class="main-content-form form-horizontal">
				<input type="hidden" name="id" value="${menu.id}">
				<input type="hidden" name="displayOrder" value="${menu.displayOrder}">
				<div class="row-fluid">
					<div class="span12">
						<div class="control-group">
							<label class="control-label">菜单名称：</label>
							<div class="controls">
								<input type="hidden" name="menuType" value="0">
								<input type="text" class="span" name="name" value="${menu.name!}" required="true" maxlength="8">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">新窗口：</label>
							<div class="controls">
								<select name="newWindow" class="input-small">
									<option <#if menu.newWindow>selected="selected"</#if> value="true">是</option>
									<option <#if !menu.newWindow>selected="selected"</#if> value="false">否</option>
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">产品聚合：</label>
							<div class="controls">
								<select name="productGroup.id" class="input-large" required="true">
									<#list productGroups as group>
									<option <#if menu.productGroup?exists && group.id==menu.productGroup.id>selected="selected"</#if> value="${group.id!}">${group.name!}</option>
									</#list>
								</select>
								<div class="help-block">只有菜单和对应的聚合都是发布状态才会被显示到页面上</div>
							</div>
						</div>
						<div class="text-center">
							<button class="btn btn-primary" type="submit">保存</button>
						</div>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
						<br>
					</div>
				</div>
			</form>
			<#elseif menu.menuType==1>
			<form action="${ContextPath}/menu/modify.html" method="post" class="main-content-form form-horizontal">
				<input type="hidden" name="id" value="${menu.id}">
				<input type="hidden" name="displayOrder" value="${menu.displayOrder}">
				<div class="row-fluid">
					<div class="span12">
						<div class="control-group">
							<label class="control-label">菜单名称：</label>
							<div class="controls">
								<input type="hidden" name="menuType" value="1">
								<input type="text" class="span" name="name" value="${menu.name!}" required="true" maxlength="8">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">新窗口：</label>
							<div class="controls">
								<select name="newWindow" class="input-small">
									<option <#if menu.newWindow>selected="selected"</#if> value="true">是</option>
									<option <#if !menu.newWindow>selected="selected"</#if> value="false">否</option>
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">页面内容：</label>
							<div class="controls">
								<script type="text/plain" id="menu-page-content" name="pageContent">${menu.pageContent!}</script>
							</div>
						</div>
						<div class="text-center">
							<button class="btn btn-primary" type="submit">保存</button>
						</div>
					</div>
				</div>
			</form>
			<#elseif menu.menuType==2>
			<form action="${ContextPath}/menu/modify.html" method="post" class="main-content-form form-horizontal">
				<input type="hidden" name="id" value="${menu.id}">
				<input type="hidden" name="displayOrder" value="${menu.displayOrder}">
				<div class="row-fluid">
					<div class="span12">
						<div class="control-group">
							<label class="control-label">菜单名称：</label>
							<div class="controls">
								<input type="hidden" name="menuType" value="2">
								<input type="text" class="span" name="name" value="${menu.name!}" required="true" maxlength="8">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">新窗口：</label>
							<div class="controls">
								<select name="newWindow" class="input-small">
									<option <#if menu.newWindow>selected="selected"</#if> value="true">是</option>
									<option <#if !menu.newWindow>selected="selected"</#if> value="false">否</option>
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">外链地址：</label>
							<div class="controls">
								<input type="text" class="span" name="url" value="${menu.url!}" required="true" maxlength="128">
							</div>
						</div>
						<div class="text-center">
							<button class="btn btn-primary" type="submit">保存</button>
						</div>
					</div>
				</div>
			</form>
			</#if>
		</div>
	</div>
</div>
<script>
$('select', '.main-content-form').selectBoxIt({autoWidth:false});
UE.delEditor('menu-page-content');
var pdEditor = UE.getEditor('menu-page-content', {
	initialContent: '',
	initialFrameWidth: '100%'
});
$('input,textarea,select', '.main-content-form').jqBootstrapValidation({
	submitSuccess : cqlybest.ajaxSubmit
});
</script>