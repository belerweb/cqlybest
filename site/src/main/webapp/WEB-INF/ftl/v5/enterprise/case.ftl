<#assign Title='${case.name!}考察' />
<#assign Description='${case.name!}考察|考察${case.name!}|标杆考察|企业考察' />
<#assign Keywords='标杆考察 企业考察' />
<#include "../index/header.ftl">
<#include "../index/menu.ftl">
<div class="breadcrumbs margin-bottom-20">
	<div class="container">
		<h1 class="color-green pull-left">成功案例</h1>
		<ul class="pull-right breadcrumb">
			<li><a href="#">企业考察</a> <span class="divider">/</span></li>
			<li class="active">成功案例</li>
		</ul>
	</div>
</div>
<div class="container">
	<div class="row-fluid">
		<div class="span9 margin-bottom-30">
			<div class="blog-page blog-item">
				<div class="blog">
					<h3>${case.name!}</h3>
					<ul class="unstyled inline blog-info">
					</ul>
					<br>
					<div>${case.description!}</div>
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