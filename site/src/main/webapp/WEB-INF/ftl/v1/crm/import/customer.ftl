<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div id="page-content" class="clearfix">
	<div class="page-header position-relative">
		<h1>
			数据导入
			<small>
				<i class="icon-double-angle-right"></i>
				客户信息
			</small>
		</h1>
	</div>
	<div class="row-fluid">
		<div id="import-wizard" class="row-fluid hidden">
			<ul class="wizard-steps">
				<li data-target="#import－step1" class="active">
					<span class="step">1</span>
					<span class="title">选择文件</span>
				</li>
				<li data-target="#import－step2">
					<span class="step">2</span>
					<span class="title">处理数据</span>
				</li>
				<li data-target="#import－step3">
					<span class="step">3</span>
					<span class="title">完成</span>
				</li>
			</ul>
		</div>
		<hr />
		<div class="step-content row-fluid position-relative">
			<div class="step-pane active" id="import－step1">
				<div class="span3"> </div>
				<div class="span6">
					<form method="post" action="${ContextPath}/crm/import/customer.do?step=1">
						<input name="file" type="file" id="import-file" />
					</form>
					<div id="import－step1-message" class="alert alert-error hide">
						只能选择 xls 或 xlsx 文件
					</div>
				</div>
				<div class="span3"> </div>
			</div>
			<div class="step-pane" id="import－step2">
			</div>
			<div class="step-pane" id="import－step3">
				<div class="alert alert-success text-center" style="padding:100px 0;">
					<strong>你已成功导入数据。</strong>
				</div>
			</div>
		</div>
		<hr />
		<div class="row-fluid wizard-actions">
			<button type="button" class="btn btn-success btn-next" data-last="继续导入">下一步</button>
		</div>
	</div>
</div>
<script type="text/javascript">
(function(){
	$('#import-file').ace_file_input({
		no_file:'请选择表格文件...',
		btn_choose:'选择',
		btn_change:'重新选择',
		droppable:false
	});

	$('#import-wizard').ace_wizard().on('change' , function(e, info){
		if(info.step == 1) {
			if (!/\.xls[x]?$/.test($('#import-file').val())) {
				$('#import－step1-message').show();
				setTimeout(function() {
					$('#import－step1-message').hide(500);
				}, 3000);
				return false;
			}
			var data;
			$('.wizard-actions .btn-next').text('文件上传处理中，请等待...');
			$('#page-content form').ajaxSubmit({
				forceSync:true,
				async:false,
				success: function(response, status, xhr, form) {
					data = response;
					$('#import－step2').html(response);
				},
				error: function(xhr, status, response, form) {
					cqlybest.error(xhr.responseText);
				}
			});
			
			$('.wizard-actions .btn-next').text('下一步');
			return !!data;
		}
		if(info.step == 2) {
			var unused = false;
			$('#data-table thead select').each(function(){
				if ($(this).val()=='') {
					unused = true;
					return false;
				}
			});
			if (unused) {
				cqlybest.error('请为每一列需要的数据选择其代表的意义并删除不需要列。');
				return false;
			}

			var keys = [];
			$('#data-table thead select').each(function(){
				var key = $(this).val();
				if (keys.indexOf(key) == -1) {
					keys.push(key);
				}
			});
			if (keys.length != $('#data-table thead select').length) {
				cqlybest.error('每一列的意义不能重复。');
				return false;
			}
			if (keys.indexOf('idCard')==-1 && keys.indexOf('passport')==-1 && keys.indexOf('mobile')==-1 && keys.indexOf('email')==-1) {
				cqlybest.error('数据中必须包含身份证或护照或电子邮件或手机号。');
				return false;
			}


			var data = [];
			$('#data-table tbody tr').each(function(){
				var row = $(this);
				var customer = {};
				$.each(keys, function(i, key){
					var value = row.find('td:eq(' + i + ')').text();
					if (key=='mobile') {
						value = value.match(/(\d+)/)[0];
					}
					customer[key] = value;
				});
				data.push(customer);
			});

			var success = false;
			$.ajax({
				async:false,
				method:'post',
				url:'${ContextPath}/crm/import/customer.do?step=2',
				data:{
					data: JSON.stringify(data)
				}
			}).done(function(){
				success = true;
			}).fail(function(){
				cqlybest.error();
			});
			return success;
		}
	}).on('changed', function(e) {
	}).on('finished', function(e) {
		cqlybest.go('#main-content', '${ContextPath}/crm/import/customer.do');
	});
})();
</script>