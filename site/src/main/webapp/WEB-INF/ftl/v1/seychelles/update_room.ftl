<div id="island-room-tab" class="tab-pane">
	<div class="text-right"><button id="island-room-add" type="button" class="btn btn-primary btn-mini">添加</button></div>
	<div id="island-room-accordion-container" style="margin-top:5px;">
		<#include "update_room_accordion.ftl">
	</div>
</div>

<script type="text/javascript">
$('#island-room-add').click(function(){
	var action = $(this).parent();
	var form = ['<form class="form-horizontal"><legend>添加房型</legend>'];
	form.push('<div class="control-group"><label class="control-label">中文名称：</label>');
	form.push('<div class="controls"><input type="text" name="zhName"></div></div>');
	form.push('<div class="control-group"><label class="control-label">英文名称：</label>');
	form.push('<div class="controls"><input type="text" name="enName"></div></div>');
	form.push('</form>');
	var dialog = bootbox.dialog(form.join(''), [{
		label: '取消'
	}, {
		label: '确定',
		callback: function(){
			var zhName = $.trim($('input[name=zhName]', dialog).val());
			var enName = $.trim($('input[name=enName]', dialog).val());
			if (!/^.{1,64}$/.test(zhName)) {
				bootbox.alert('请输入中文名称，且长度不超过64位');
				return false;
			}
			if (!/^.{1,128}$/.test(enName)) {
				bootbox.alert('请输入英文名称，且长度不超过128位');
				return false;
			}
			$.post('${ContextPath}/seychelles/room/add.do', {
				islandId: '${island.id}',
				zhName: zhName,
				enName: enName
			}).done(function(data){
				cqlybest.reload('#main-content', function() {
					$('#island-update-tabs a[data-target="#island-room-tab"]').tab('show');
				});
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
</script>