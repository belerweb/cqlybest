<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div>
	<div class="clearfix"></div>
	<div class="grid">
		<div class="grid-title">
			<div class="pull-left">
				<div class="icon-title"><i class="icon-edit"></i></div>
				<span>编辑产品</span> 
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>   
		</div>
		<form class="form-horizontal grid-content">
			<div class="tabbable">
				<ul id="product-update-tabs" class="nav nav-tabs">
					<li class="active"><a href="javascript:void(0);" data-toggle="tab" data-target="#product-base-tab">基本信息</a></li>
					<li><a href="javascript:void(0);" data-toggle="tab" data-target="#product-traffic-tab">交通</a></li>
					<#if product.productType==1>
					<li><a href="javascript:void(0);" data-toggle="tab" data-target="#product-detail-tab">马代行程</a></li>
					<#else>
					<li><a href="javascript:void(0);" data-toggle="tab" data-target="#product-detail-tab">详细行程</a></li>
					</#if>
					<li><a href="javascript:void(0);" data-toggle="tab" data-target="#product-calendar-tab">日历</a></li>
					<li><a href="javascript:void(0);" data-toggle="tab" data-target="#product-poster-tab">海报图片 </a></li>
					<li><a href="javascript:void(0);" data-toggle="tab" data-target="#product-photo-tab">相册图片</a></li>
					<li><a href="javascript:void(0);" data-toggle="tab" data-target="#product-comment-tab">评论</a></li>
				</ul>
				<div class="tab-content">
					<#include "update_base.ftl">
					<#include "update_traffic.ftl">
					<#if product.productType==0>
						<#include "update_detail.ftl">
					<#elseif product.productType==1>
						<#include "update_maldives.ftl">
					<#elseif product.productType==2>
						<#include "update_mauritius.ftl">
					</#if>
					<#include "update_calendar.ftl">
					<#include "update_poster_photo.ftl">
					<#include "update_comment.ftl">
				</div>
			</div>
		</form>
	</div>
</div>
