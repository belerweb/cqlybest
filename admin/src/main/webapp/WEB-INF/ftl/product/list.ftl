<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div>
	<div class="clearfix"></div>
	<div class="pagetitle">
		<h1>旅游产品</h1>
		<div class="btn-group">
			<a href="#m=site;n=product.list;u=${ContextPath}/product/add.html;t=#main" class="btn btn-primary">增加新产品</a>
		</div>
		<div class="clearfix"></div>
		<hr>
	</div>
	<div class="grid">
		<div class="grid-title">
			<div class="pull-left">
				<div class="icon-title"><i class="icon-table"></i></div>
				<span>产品列表</span> 
				<div class="clearfix"></div>
			</div>
			<div class="pull-right"> 
				<div class="icon-title"><a href="#" title="发布"><i class="icon-upload-alt"></i></a></div>
				<div class="icon-title"><a href="#" title="取消发布"><i class="icon-download-alt"></i></a></div>
				<div class="icon-title"><a href="#" title="删除"><i class="icon-remove"></i></a></div>
			</div>
			<div class="clearfix"></div>   
		</div>
		<div class="grid-content overflow">
			<table id="product-list-table" class="table table-striped">
				<thead>
					<tr>
						<th><input type="checkbox"></th>
						<th>产品名称</th>
						<th>状态</th>
						<th>操作</th>
						<th>最后修改时间</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><input type="checkbox"></td>
						<td>XXX</td>
						<td>
							<span class="s_green">已发布</span>
							<span class="s_gray">未发布</span>
						</td>
						<td class="action-table">
							<a href="#"><img alt="" src="images/icon/table_edit.png"></a>
							<a href="#"><img alt="" src="images/icon/table_del.png"></a>
						</td>
						<td>2013-4-18 23:34:55</td>
					</tr>
				</tbody>
			</table>
			<div class="clearfix"></div>
		</div>
	</div>
</div>
<script>
$('#product-list-table').dataTable({
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
	bServerSide: true
});
</script>