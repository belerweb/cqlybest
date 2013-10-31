<#assign Title='标杆企业：${company.name!}' />
<#assign Description='标杆企业：${company.name!}|标杆考察|企业考察' />
<#assign Keywords='标杆考察 企业考察' />
<#include "../index/header.ftl">
<#include "../index/menu.ftl">
<div class="breadcrumbs margin-bottom-20">
	<div class="container">
		<h1 class="color-green pull-left">标杆企业</h1>
		<ul class="pull-right breadcrumb">
			<li><a href="#">企业考察</a> <span class="divider">/</span></li>
			<li class="active">标杆企业</li>
		</ul>
	</div>
</div>
<div class="container">
	<div class="row-fluid">
		<div class="span9 margin-bottom-30">
			<div class="blog-page blog-item">
				<div class="blog">
					<h3>${company.name!}</h3>
					<ul class="unstyled inline blog-info">
					</ul>
					<br>
					<div>${company.description!}</div>
				</div>
			</div>
		</div>
		<div class="span3">
		</div>
	</div>
</div>
<#include "../index/copyright.ftl">
<script type="text/javascript">
	window.PageContext = {
		fixCopyright: false,
		activeNav: 'example',
		init: function(){
		}
	};
</script>
<#include "../index/footer.ftl">