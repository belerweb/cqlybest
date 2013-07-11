<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div>
	<div class="clearfix"></div>
	<div class="grid">
		<div class="grid-title">
			<div class="pull-left">
				<div class="icon-title"><i class="icon-edit"></i></div>
				<span>编辑产品聚合</span> 
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>   
		</div>
		<#assign id=group.id>
		<#assign url="${ContextPath}/product_group/update.do">
		<form class="form-horizontal">
			<div class="grid-content">
				<div class="row-fluid">
					<div class="span12">
						<div class="control-group">
							<label class="control-label">聚合名称：</label>
							<div class="controls">
								<a id="product_group_name" href="#" class="editable" data-pk="${id}" data-name="name" data-type="text" data-url="${url}" data-value="${(group.name!)?html}"></a>
							</div>
						</div>
						<div class="clearfix"></div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span6">
						<div class="control-group">
							<label class="control-label"><strong>聚合条件：</strong></label>
						</div>
						<hr style="margin: 0px;"/>
						<div class="control-group">
							<label class="control-label">按推荐月份聚合：</label>
							<div class="controls">
								<a id="group_months" href="#" class="editable" data-pk="${id}" data-name="groupMonths" data-type="select2" data-url="${url}" data-value="${(group.groupMonths!)?html}">${(group.groupMonths!)?html}</a>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">按适合人群聚合：</label>
							<div class="controls">
								<a id="group_crowds" href="#" class="editable" data-pk="${id}" data-name="groupCrowds" data-type="select2" data-url="${url}" data-value="${(group.groupCrowds!)?html}">${(group.groupCrowds!)?html}</a>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">按交通方式聚合：</label>
							<div class="controls">
								<a id="group_traffics" href="#" class="editable" data-dict="traffic" data-pk="${id}" data-name="groupTraffics" data-type="select2" data-url="${url}" data-value="${(group.groupTraffics!)?html}">${(group.groupTraffics!)?html}</a>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">按产品类型聚合：</label>
							<div class="controls">
								<a id="group_types" href="#" class="editable" data-dict="product-type" data-pk="${id}" data-name="groupTypes" data-type="select2" data-url="${url}" data-value="${(group.groupTypes!)?html}">${(group.groupTypes!)?html}</a>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">按产品等级聚合：</label>
							<div class="controls">
								<a id="group_grades" href="#" class="editable" data-dict="product-grade" data-pk="${id}" data-name="groupGrades" data-type="select2" data-url="${url}" data-value="${(group.groupGrades!)?html}">${(group.groupGrades!)?html}</a>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">按关键词/标签聚合：</label>
							<div class="controls">
								<a id="group_keywords" href="#" class="editable" data-dict="tag" data-pk="${id}" data-name="groupKeywords" data-type="select2" data-url="${url}" data-value="${(group.groupKeywords!)?html}">${(group.groupKeywords!)?html}</a>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">按出发城市聚合：</label>
							<div class="controls">
								<a id="group_departure_cities" href="#" class="editable" data-dict="departure-city" data-pk="${id}" data-name="groupDepartureCities" data-type="select2" data-url="${url}" data-value="${(group.groupDepartureCities!)?html}">${(group.groupDepartureCities!)?html}</a>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">按目的地聚合：</label>
							<div class="controls">
								<a id="group_destinations" href="#" class="editable" data-dict="destination" data-pk="${id}" data-name="groupDestinations" data-type="select2" data-url="${url}" data-value="${(group.groupDestinations!)?html}">${(group.groupDestinations!)?html}</a>
							</div>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="span6">
						<div class="control-group">
							<label class="control-label"><strong>过滤条件：</strong></label>
						</div>
						<hr style="margin: 0px;"/>
						<div class="control-group">
							<label class="control-label">按推荐月份过滤：</label>
							<div class="controls">
								<a id="filter_months" href="#" class="editable" data-pk="${id}" data-name="filterMonths" data-type="select2" data-url="${url}" data-value="${(group.filterMonths!)?html}">${(group.filterMonths!)?html}</a>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">按适合人群过滤：</label>
							<div class="controls">
								<a id="filter_crowds" href="#" class="editable" data-pk="${id}" data-name="filterCrowds" data-type="select2" data-url="${url}" data-value="${(group.filterCrowds!)?html}">${(group.filterCrowds!)?html}</a>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">按交通方式过滤：</label>
							<div class="controls">
								<a id="filter_traffics" href="#" class="editable" data-dict="traffic" data-pk="${id}" data-name="filterTraffics" data-type="select2" data-url="${url}" data-value="${(group.filterTraffics!)?html}">${(group.filterTraffics!)?html}</a>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">按产品类型过滤：</label>
							<div class="controls">
								<a id="filter_types" href="#" class="editable" data-dict="product-type" data-pk="${id}" data-name="filterTypes" data-type="select2" data-url="${url}" data-value="${(group.filterTypes!)?html}">${(group.filterTypes!)?html}</a>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">按产品等级过滤：</label>
							<div class="controls">
								<a id="filter_grades" href="#" class="editable" data-dict="product-grade" data-pk="${id}" data-name="filterGrades" data-type="select2" data-url="${url}" data-value="${(group.filterGrades!)?html}">${(group.filterGrades!)?html}</a>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">按关键词/标签过滤：</label>
							<div class="controls">
								<a id="filter_keywords" href="#" class="editable" data-dict="tag" data-pk="${id}" data-name="filterKeywords" data-type="select2" data-url="${url}" data-value="${(group.filterKeywords!)?html}">${(group.filterKeywords!)?html}</a>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">按出发城市过滤：</label>
							<div class="controls">
								<a id="filter_departure_cities" href="#" class="editable" data-dict="departure-city" data-pk="${id}" data-name="filterDepartureCities" data-type="select2" data-url="${url}" data-value="${(group.filterDepartureCities!)?html}">${(group.filterDepartureCities!)?html}</a>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">按目的地过滤：</label>
							<div class="controls">
								<a id="filter_destinations" href="#" class="editable" data-dict="destination" data-pk="${id}" data-name="filterDestinations" data-type="select2" data-url="${url}" data-value="${(group.filterDestinations!)?html}">${(group.filterDestinations!)?html}</a>
							</div>
						</div>
						<div class="clearfix"></div>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>
<script>
$('#product_group_name').editable({
});
$('#group_months,#filter_months').editable({
	inputclass: 'input-large',
	select2: {
		tags: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
	}
});
$('#group_crowds,#filter_crowds').editable({
	inputclass: 'input-large',
	select2: {
		tags: ['个人旅行', '团体旅行']
	}
});
cqlybest.editableTag('#group_traffics');
cqlybest.editableTag('#group_types');
cqlybest.editableTag('#group_grades');
cqlybest.editableTag('#group_keywords');
cqlybest.editableTag('#group_departure_cities');
cqlybest.editableTag('#group_destinations');
cqlybest.editableTag('#filter_traffics');
cqlybest.editableTag('#filter_types');
cqlybest.editableTag('#filter_grades');
cqlybest.editableTag('#filter_keywords');
cqlybest.editableTag('#filter_departure_cities');
cqlybest.editableTag('#filter_destinations');
</script>