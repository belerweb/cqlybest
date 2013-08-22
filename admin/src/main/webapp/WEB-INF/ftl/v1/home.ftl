<#include "header.ftl">
<#assign Title='工作台' />
<#include "header_top.ftl">
<#include "sidebar.ftl">
<div id="main-content" class="clearfix">
	<div id="page-content" class="clearfix">
		<div class="row-fluid">
		</div>
	</div>
</div><!--/#main-content-->
<script type="text/javascript">
var PageContext = {
	init: function(){
		$('.column').sortable({connectWith:'.column'}).disableSelection();
	}
};
</script>
<#include "footer.ftl">