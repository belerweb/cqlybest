<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div id="page-content" class="clearfix">
	<div class="row-fluid">
		<div class="widget-box transparent">
			<div class="widget-header">
				<h4 class="lighter smaller">
					系统信息
				</h4>
				<div class="widget-toolbar no-border">
					<ul class="nav nav-tabs">
						<li class="active">
							<a href="#system-env" data-toggle="tab">
								环境变量
							</a>
						</li>
						<li>
							<a href="#system-property" data-toggle="tab">
								系统属性
							</a>
						</li>
					</ul>
				</div>
			</div>
			<div class="widget-body">
				<div class="widget-main">
					<div class="tab-content">
						<div class="tab-pane in active" id="system-env">
							<table class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th class="text-left" style="width:150px;">名称</th>
										<th>值</th>
									</tr>
								</thead>
								<tbody>
									<#list env?keys?sort as key>
									<tr>
										<th class="text-left">${key}</th>
										<td>${env[key]!}</td>
									</tr>
									</#list>
								</tbody>
							</table>
						</div>
						<div class="tab-pane" id="system-property">
							<table class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th class="text-left" style="width:150px;">名称</th>
										<th>值</th>
									</tr>
								</thead>
								<tbody>
									<#list property?keys?sort as key>
									<tr>
										<th class="text-left">${key}</th>
										<td>${property[key]!}</td>
									</tr>
									</#list>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
