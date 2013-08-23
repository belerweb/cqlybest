<div id="product-comment-tab" class="tab-pane">
	<div class="text-right"><button id="product-add-comment" type="button" class="btn btn-primary">添加</button></div>
	<#if product.comments?has_content>
	<#list product.comments as comment>
	<div class="row-fluid">
		<div class="span12">
			<p>
				<span>${comment.commentTime?string('yyyy-MM-dd HH:mm:ss')}</span> <span>${comment.user}</span>
				<a href="javascript:void(0);" class="pull-right delete" data-id="${comment.id}"><i class="icon-remove"></i> 删除</a>
			</p>
			<pre>${comment.content}</pre>
		</div>
	</div>
	</#list>
	</#if>
</div>
<script type="text/javascript">
$('#product-add-comment').click(function(){
	var action = $(this).parent();
	var form = ['<form class="form-horizontal"><legend>添加评论</legend>'];
	form.push('<div class="control-group"><label class="control-label">用户：</label>');
	form.push('<div class="controls"><input type="text" name="user"></div></div>');
	form.push('<div class="control-group"><label class="control-label">评论：</label>');
	form.push('<div class="controls"><textarea name="content"></textarea></div></div>');
	form.push('</form>');
	var dialog = bootbox.dialog(form.join(''), [{
		label: '取消'
	}, {
		label: '确定',
		callback: function(){
			var user = $.trim($('input', dialog).val());
			var content = $.trim($('textarea', dialog).val());
			if (!/^.{1,32}$/.test(user)) {
				bootbox.alert('请输入用户，且长度不超过32位');
				return false;
			}
			if (!/^.{1,1024}$/.test(content)) {
				bootbox.alert('请输入评论，且内容不能超过1024个字');
				return false;
			}
			$.post('${ContextPath}/product/comment/add.do', {
				productId: '${product.id}',
				user: user,
				content: content
			}).done(function(obj){
				var comment = ['<div class="row-fluid"><div class="span12">'];
				comment.push('<p><span>' + $.format.date(new Date(obj.commentTime), 'yyyy-MM-dd HH:mm:ss') + '</span>');
				comment.push('<span>' + obj.user + '</span><a data-id="' + obj.id + '" class="pull-right delete" href="javascript:void(0);">');
				comment.push('<i class="icon-remove"></i> 删除</a><pre>' + obj.content + '</pre></div></div>');
				action.after(comment.join(''));
			}).fail(function() {
				cqlybest.error();
			});
		}
	}]);
	$('form', dialog).on('submit', function(e) {
		e.preventDefault();
		dialog.find(".btn-primary").click();
	});
});
$('#product-comment-tab a.delete').on('click', function() {
	var id = $(this).attr('data-id');
	var el = $(this).parents('.row-fluid');
	bootbox.confirm('确认删除评论', '取消', '确认', function(result) {
		if (result) {
			$.post('${ContextPath}/product/comment/delete.do', {
				id: id
			}).done(function(){
				el.remove();
			}).fail(function() {
				cqlybest.error();
			});
		}
	});
});
</script>