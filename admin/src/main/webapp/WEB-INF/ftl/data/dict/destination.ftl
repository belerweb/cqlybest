<#assign ContextPath=springMacroRequestContext.getContextPath() />
<form id="dest-add-form" action="${ContextPath}/data/dict/add_dest.html" method="post" class="form-inline hide" style="padding: 20px 50px 0px;">
	<label><strong>添加目的地：</strong></label>
	<label><span class="s_green">目的地</span> <strong> / </strong> </label>
	<input name="pid" type="hidden">
	<input name="name" type="text" class="input" required="true" maxlength="32">
	<button type="submit" class="btn btn-primary">保存</button>
	<button type="button" class="del btn btn-danger"></button>
</form>
<hr>
<div id="destination-tree">
</div>
<script>
$('input,textarea,select', '#dest-add-form').jqBootstrapValidation({
	submitSuccess : function($form, event) {
		event.preventDefault();
		event.stopPropagation();
		$form.ajaxSubmit({
			success : function(response){
				$('#dict-main').load('${ContextPath}/data/dict/destination.html');
			},
			error : function() {
				alert('保存失败');
			}
		});
	}
});
$('#dest-add-form button.del').click(function(){
	$.post('${ContextPath}/data/dict/del_dest.html', {
		id: $(this).data('id')
	}, function(){
		$('#dict-main').load('${ContextPath}/data/dict/destination.html');
	});
});
var dataToNode = function(data) {
	data.label = '<span class="s_green">' + data.name + '</span>';
	return {
		id : 'dest-node-' + data.id,
		name : data.name,
		data : data,
		children : []
	};
}
var listToTree = function(oData) {
	var result = [];
	while (oData.length) {
		var data = oData.shift();
		var node = dataToNode(data);
		var i = 0;
		while (oData[i] && oData[i].rgt < data.rgt) {
			i++;
		}
		node.children = listToTree(oData.splice(0, i));
		result.push(node);
	}
	return result;
};
var data = {
	id: 'dest-node-0',
	name: '目的地',
	data: {
		id: 0,
		label: '<span class="s_green">目的地</span>'
	},
	children: []
};
data.children = listToTree(${tree});

var height = $('#mb').height() - 190;
$('#destination-tree').height(height);
var st = new $jit.ST({
	injectInto: 'destination-tree',
	orientation: 'top',
	levelsToShow: 10,
	offsetY: height/2 - 10,
	transition: $jit.Trans.Quart.easeInOut,
	Edge: {
		type: 'bezier',
		lineWidth: 2,
		color: '#23A4FF',
		overridable: true
	},
	Node: {
		autoWidth: true,
		autoHeight: true,
		type: 'rectangle',
		alpha: 1,
		overridable: true
	},
	onCreateLabel: function(label, node){
		$(label).css('cursor', 'pointer').html(node.data.label).click(function(){
			$('#dest-add-form .s_green').text(node.name);
			$('#dest-add-form input[name=pid]').val(node.data.id);
			if (node.data.id) {
				$('#dest-add-form button.del').data(node.data).text('删除' + node.name).show();
			} else {
				$('#dest-add-form button.del').hide();
			}
			$('#dest-add-form').show();
		});
	}
});
st.loadJSON(data);
st.compute();
st.geom.translate(new $jit.Complex(-200, 0), "current");
st.onClick(st.root);
</script>