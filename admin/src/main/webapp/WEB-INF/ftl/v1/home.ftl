<#include "header.ftl">
<#assign Title='工作台' />
<#include "header_top.ftl">
<#include "sidebar.ftl">
<div id="main-content" class="clearfix">
	<div id="page-content" class="clearfix">
		<div class="row-fluid">
			<div class="span4 column">
				<div class="widget-box" data-page="${ContextPath}/dashboard/birth.do">
					<div class="widget-header">
						<h4 class="lighter smaller">
							<i class="icon-fire orange"></i>
							客户生日提醒
						</h4>
					</div>
					<div class="widget-body">
					</div>
				</div>
			</div>
			<div class="span8 column">
				<div class="widget-box" data-page="${ContextPath}/dashboard/customer.do">
					<div class="widget-header">
						<h4 class="lighter smaller">
							<i class="icon-user"></i>
							客户资料查询
						</h4>
					</div>
					<div class="widget-body">
					</div>
				</div>
			</div>
		</div>
	</div>
</div><!--/#main-content-->
<script type="text/javascript">
var PageContext = {
	init: function(){
		$('.column').sortable({
			connectWith:'.column',
			cancel:'a,input,textarea,button,select,option'
		});//.disableSelection();
		$('.widget-box').each(function(i, obj){
			$('.widget-body', this).load($(this).data('page'));
		});
	}
};
</script>
<#include "footer.ftl">