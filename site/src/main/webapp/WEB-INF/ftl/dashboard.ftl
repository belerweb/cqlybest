<#assign ContextPath=springMacroRequestContext.getContextPath() />
<div class="row-fluid">
	<div class="grid span6">
		<div class="grid-title">
			<div class="pull-left">
				<div class="icon-title"><i class="icon-comment"></i></div>
				<span>发送短信</span> 
				<div class="clearfix"></div>
			</div>
		</div>
		<div class="grid-content overflow">
			<form id="dash-sms-send-form" action="${ContextPath}/sms/send.do" method="post">
				<div class="control-group">
					<label>收信人：</label>
					<input type="text" class="span input" name="phone" required="true" data-validation-required-message="请填写手机号"
						data-validation-regex-regex="1[3458]\d{9}" data-validation-regex-message="请填写正确的手机号""> 
					<div class="help-block"></div>
				</div>
				<div class="control-group">
					<label>短信内容：</label>
					<textarea rows="3" name="content" class="span input same-height-1" required＝"true" maxlength="256"></textarea>
					<div class="help-block"></div>
				</div>
				<button type="submit" class="btn btn-success">发送</button>
			</form>
		</div>
	</div>
	<div class="grid span6">
	</div>
</div>
<script>
$('input,textarea', '#dash-sms-send-form').jqBootstrapValidation({
	submitSuccess : cqlybest.ajaxSubmit
});
</script>