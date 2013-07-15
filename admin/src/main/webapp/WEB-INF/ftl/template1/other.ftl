<div class="row-fluid form-horizontal">
	<div class="span6">
		<div class="control-group">
			<label class="control-label"><a href="#" class="logo">LOGO：</a></label>
			<div class="controls" data-id="<#if options['template1-logo']?has_content>${options['template1-logo']?split('.')[0]}</#if>">
				<#if options['template1-logo']?has_content>
				<img src="${ContextPath}/image/${options['template1-logo']}">
				</#if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><a href="#" class="login-poster">登录页海报：</a></label>
			<div class="controls" data-id="<#if options['template1-login-poster']?has_content>${options['template1-login-poster']?split('.')[0]}</#if>">
				<#if options['template1-login-poster']?has_content>
				<img src="${ContextPath}/image/${options['template1-login-poster']}?width=300&height=100">
				</#if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">登录页海报链接：</label>
			<div class="controls">
				<a href="#" class="editable" data-name="template1-login-poster-link" data-type="text" data-url="${ContextPath}/site/config.html" data-value="${options['template1-login-poster-link']!}"></a>
			</div>
		</div>
	</div>
	<div class="span6">
	</div>
</div>

<script type="text/javascript">
$('#template1 .logo').click(function(e) {
	e.stopPropagation();
	e.preventDefault();
	var images = cqlybest.uploadImage('${ContextPath}');
	if (images) {
		var el = $(this).parent().next();
		var oid = el.attr('data-id');
		var image = images[0].id + '.' + images[0].imageType;
		$.post('${ContextPath}/site/config.html', {
			name: 'template1-logo',
			value: image
		}, function() {
			if (oid && oid.length) {
				$.post('${ContextPath}/image/delete.do', {
					id: oid
				});
			}
			el.attr('data-id', images[0].id).empty().append('<img src="${ContextPath}/image/' + image + '">');
		});
	}
});
$('#template1 .login-poster').click(function(e) {
	e.stopPropagation();
	e.preventDefault();
	var images = cqlybest.uploadImage('${ContextPath}');
	if (images) {
		var el = $(this).parent().next();
		var oid = el.attr('data-id');
		var image = images[0].id + '.' + images[0].imageType;
		$.post('${ContextPath}/site/config.html', {
			name: 'template1-login-poster',
			value: image
		}, function() {
			if (oid && oid.length) {
				$.post('${ContextPath}/image/delete.do', {
					id: oid
				});
			}
			el.attr('data-id', images[0].id).empty().append('<img src="${ContextPath}/image/' + image + '?width=300&height=100">');
		});
	}
});
</script>

