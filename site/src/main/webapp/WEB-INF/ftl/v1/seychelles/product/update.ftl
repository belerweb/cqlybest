<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div id="page-content" class="clearfix" style="position:releative;">
	<div class="row-fluid">
		<h3 class="header smaller lighter blue">
			编辑产品
			<small>
				<i class="icon-double-angle-right"></i>
				${product.name!}
			</small>
			<button type="button" class="btn btn-mini btn-primary pull-right" onclick="cqlybest.go('#main-content');">
				<i class="icon-mail-reply"></i> 返回
			</button>
		</h3>
		<div class="tabbable">
			<ul id="product-update-tabs" class="nav nav-tabs">
				<li class="active"><a href="javascript:void(0);" data-toggle="tab" data-target="#product-base-tab">基本信息</a></li>
				<li><a href="javascript:void(0);" data-toggle="tab" data-target="#product-traffic-tab">交通</a></li>
				<li><a href="javascript:void(0);" data-toggle="tab" data-target="#product-detail-tab">塞舌尔行程</a></li>
				<li><a href="javascript:void(0);" data-toggle="tab" data-target="#product-calendar-tab">日历</a></li>
				<li><a href="javascript:void(0);" data-toggle="tab" data-target="#product-poster-tab">海报图片 </a></li>
				<li><a href="javascript:void(0);" data-toggle="tab" data-target="#product-photo-tab">相册图片</a></li>
			</ul>
			<div class="tab-content form-horizontal" style="min-height:400px;overflow:visible;z-index:13;">
				<#include "update_base.ftl">
				<#include "update_traffic.ftl">
				<#include "update_room.ftl">
				<#include "update_calendar.ftl">
				<#include "update_poster_photo.ftl">
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$('#page-content a[data-type=text],#page-content a[data-type=textarea]').on('shown', function(){
	cqlybest.fixed(false);
}).on('hidden', function(){
	cqlybest.fixed(true);
}).editable();
</script>
