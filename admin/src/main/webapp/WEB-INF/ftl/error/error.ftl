<#assign ContextPath=springMacroRequestContext.getContextPath() />
<#macro error html>
<!DOCTYPE html>
<html lang="zh" class="body-error">
	<head>
		<meta charset="utf-8">
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
		<title>错误</title>
		<link href="${ContextPath}/css/bootstrap.css" rel="stylesheet">
	</head>
	<body>
		<div class="alert alert-error" style="width: 200px; top: 200px; margin: 200px auto 0px;">
			${html}
		</div>
		<script src="${ContextPath}/js/jquery.js" type="text/javascript"></script>
		<script src="${ContextPath}/js/bootstrap.js" type="text/javascript"></script>
	</body>
</html>
</#macro>