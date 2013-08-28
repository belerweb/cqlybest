<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div id="page-content" class="clearfix">
	<div class="row-fluid">
		<div class="widget-box transparent">
			<div class="widget-header">
				<h4 class="lighter smaller">首页内容</h4>
				<div class="widget-toolbar no-border">
					<ul class="nav nav-tabs">
						<li class="active">
							<a href="#index-left" data-toggle="tab">左侧</a>
						</li>
						<li>
							<a href="#index-right" data-toggle="tab">右侧</a>
						</li>
					</ul>
				</div>
			</div>
			<div class="widget-body">
				<div class="widget-main">
					<div class="tab-content">
						<#include "index/section.ftl">
						<div class="tab-pane in active" id="index-left">
							<@renderSections page.contents "contents" />
						</div>
						<div class="tab-pane" id="index-right">
							<@renderSections page.sidebars "sidebars" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$('#page-content button[data-action=expand]').click(function(){
	$(this).parent().next().toggleClass('hide');
});

$('#page-content button[data-action=upload]').click(function(){
	var images = cqlybest.uploadImage();
	if (images) {
		$(this).prev().val(images[0].id + '.' + images[0].extension);
	}
});

$('#page-content form select[name=type]').change(function(){
	var type = this.value;
	$('#page-content form .control-group[data-select=type]').hide();
	$('#page-content form .control-group[data-select=type][data-for=' + type + ']').show();
});

$('#page-content button[data-action=update]').click(function(){
	var form = $(this).closest('form');
	form.attr('action', form.data('update'));
	form.submit();
});
$('#page-content button[data-action=delete]').click(function(){
	var form = $(this).closest('form');
	bootbox.confirm('确认删除此节内容？', '取消', '确认', function(result) {
		if (result) {
			form.attr('action', form.data('delete'));
			form.submit();
		}
	});
});

$('#page-content form').submit(function(){
	$(this).ajaxSubmit({
		success: function(response, status, xhr, form) {
			cqlybest.reload('#main-content');
		},
		error: function(xhr, status, response, form) {
			cqlybest.error(eval(xhr.responseText));
		}
	});
	return false;
});
</script>