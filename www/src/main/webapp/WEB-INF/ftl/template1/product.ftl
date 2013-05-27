<#include "/template1/header.ftl">
<div class="container margin-menu">
	<div class="row">
		<div class="span12">
			<h4>${product.name}</h4>
		</div>
	</div>
</div>
<script>
	var PageContext = {
		init : function() {
			document.title = '${product.name!}';
		}
	};
</script>
<#include "/template1/footer.ftl">