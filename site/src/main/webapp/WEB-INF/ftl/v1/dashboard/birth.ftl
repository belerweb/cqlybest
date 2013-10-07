<div id="${pageId}" class="widget-main">
	<#if events?has_content>
	<ul class="item-list">
		<#list events as event>
		<li>
			<label class="inline">
				<a href="javascript:void(0);" data-id="${event.customerId!}">${event.description!}</a>
			</label>
			<div class="pull-right">
				<div class="btn-group">
					<button type="button" class="btn btn-mini btn-info" title="发送祝福短信">
						<i class="icon-envelope bigger-125"></i>
					</button>
				</div>
			</div>
		</li>
		</#list>
	</ul>
	<#else>
	<div class="alert alert-info text-center" style="padding:50px 0;">
		今天和明天都没有客户过生日。
	</div>
	</#if>
	<script type="text/javascript">
	$('#${pageId} li a').click(function(){
		$('.widget-main.customer input[name=keyword]').val('_id:' + $(this).data('id'));
		$('.widget-main.customer button[data-action=search]').click();
	});
	</script>
</div>