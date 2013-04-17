<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div class="block">
	<div class="clearfix"></div>
	<div class="grid">
		<div class="grid-title">
			<div class="pull-left">
				<div class="icon-title"><i class="icon-bookmark"></i></div>
				<span>网站配置</span> 
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>   
		</div>
		<form action="${ContextPath}/site/config.html" method="post">
			<div class="grid-content">
				<div class="formRow">
					<label>网站名称：</label>
					<div class="formRight">
						<input type="text" class="span input" name="name" value="${(site.name)!}"> 
					</div>
				</div>
				<div class="formRow">
					<label>关键字（词）：</label>
					<div class="formRight">
						<input type="text" class="span input" name="metaKeyword" value="${(site.metaKeyword)!}"> 
					</div>
				</div>
				<div class="formRow">
					<label>网站描述：</label>
					<div class="formRight">
						<input type="text" class="span input" name="metaDescription" value="${(site.metaDescription)!}"> 
					</div>
				</div>
				<div class="formRow">
					<label>ICP备案号：</label>
					<div class="formRight">
						<input type="text" class="span input" name="icp" value="${(site.icp)!}"> 
					</div>
				</div>
				<div class="formRow">
					<label>网站底部版权文字：</label>
					<div class="formRight">
						<input type="text" class="span input" name="copyright" value="${(site.copyright)!}"> 
					</div>
				</div>
				<div class="formRow">
					<label>统计代码：</label>
					<div class="formRight">
						<textarea rows="3" name="statisticalCode" class="span input same-height-1">${(site.statisticalCode)!}</textarea>
					</div>
				</div>
				<div class="formRow text-center">
					<button class="btn btn-primary" type="submit">保存</button>
				</div>
				<div class="clearfix"></div>
			</div>
		</form>
	</div>
</div>