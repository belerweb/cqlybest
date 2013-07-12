<#include "/template1/header.ftl">
<div class="container margin-menu">
	<div class="row">
		<div class="span3">
			<div class="well nav-sidebar">
				<ul class="nav nav-list">
					<li class="nav-header">用户中心</li>
					<li class="divider"></li>
					<li><a href="#" data-href="${ContextPath}/user/info">我的资料</a></li>
				</ul>
			</div>
		</div>
		<div class="span9 ajax-content">
			<ul class="breadcrumb title">
			</ul>
			<div class="content">
			</div>
		</div>
	</div>
</div>
<script>
	var PageContext = {
		init: function(){
			$('.nav-sidebar li a').on('click', function(e) {
				e.stopPropagation();
				e.preventDefault();
				$('.nav-sidebar li').removeClass('active');
				$(this).parent().addClass('active');
				var p = $($(this).parent().prevAll('.nav-header').get(0)).text();
				var t = $(this).text();
				$('.ajax-content .title').html('<li><a href="javascript:void(0);"><strong>' + p + '</strong></a> <span class="divider">/</span></li><li class="active">' + t + '</li>');
				$('.ajax-content .content').load($(this).data('href'));
			});
			$('.nav-sidebar li a:first').click();
		}
	};
</script>
<#include "/template1/footer.ftl">