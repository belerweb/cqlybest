<#include "/template1/header.ftl">
<div class="container">
	<div class="row">
		<div class="span12">
			<div id="diy-page-content">
			${page.pageContent!}
			</div>
		</div>
	</div>
</div>
<script>
	var PageContext = {
		menu : '${page.id!}'
	};
</script>
<#include "/template1/footer.ftl">