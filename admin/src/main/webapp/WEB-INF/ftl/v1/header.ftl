<!DOCTYPE html>
<htm>
	<#assign ContextPath=springMacroRequestContext.getContextPath() />
	<#assign User = securityContextHolder.getContext().getAuthentication().getPrincipal().getDetail() />
	<head>
		<meta charset="utf-8" />
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="${ContextPath}/assets/v1/css/introjs.css" />
		<link rel="stylesheet" href="${ContextPath}/assets/v1/css/bootstrap-tour.css" />
		<link rel="stylesheet" href="${ContextPath}/assets/v1/css/bootstrap.css" />
		<link rel="stylesheet" href="${ContextPath}/assets/v1/css/font-awesome.css" />
		<link rel="stylesheet" href="${ContextPath}/assets/v1/css/OpenSans.css" />
		<link rel="stylesheet" href="${ContextPath}/assets/v1/css/select2.css" />
		<link rel="stylesheet" href="${ContextPath}/assets/v1/css/bootstrap-editable.css" />
		<link rel="stylesheet" href="${ContextPath}/assets/v1/css/bootstrap-wysihtml5-0.0.2.css" />
		<link rel="stylesheet" href="${ContextPath}/assets/v1/css/wysiwyg-color.css" />
		<link rel="stylesheet" href="${ContextPath}/assets/v1/css/fullcalendar.css" />
		<link rel="stylesheet" href="${ContextPath}/assets/v1/css/ace.css" />
		<link rel="stylesheet" href="${ContextPath}/assets/v1/css/ace-skins.css" />
		<link rel="stylesheet" href="${ContextPath}/assets/v1/css/style.css"/>
		<!--[if IE 7]>
		  <link rel="stylesheet" href="${ContextPath}/assets/v1/css/font-awesome-ie7.css" />
		<![endif]-->
		<!--[if lt IE 9]>
		  <link rel="stylesheet" href="${ContextPath}/assets/v1/css/ace-ie.css" />
		<![endif]-->
		<script type="text/javascript">
			var ContextPath = '${ContextPath}';
		</script>
	</head>
	<body class="navbar-fixed">