<#assign ContextPath=springMacroRequestContext.getContextPath() />
<!DOCTYPE html>
<htm>
	<head>
		<meta charset="utf-8" />
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
		<title>后台管理</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />

		<link href="${ContextPath}/assets/v1/css/bootstrap.css" rel="stylesheet" />
		<link rel="stylesheet" href="${ContextPath}/assets/v1/css/font-awesome.css" />
		<link rel="stylesheet" href="${ContextPath}/assets/v1/css/OpenSans.css" />
		<link rel="stylesheet" href="${ContextPath}/assets/v1/css/select2.css" />
		<link rel="stylesheet" href="${ContextPath}/assets/v1/css/bootstrap-editable.css" />
		<link rel="stylesheet" href="${ContextPath}/assets/v1/css/bootstrap-wysihtml5-0.0.2.css" />
		<link rel="stylesheet" href="${ContextPath}/assets/v1/css/wysiwyg-color.css" />
		<link rel="stylesheet" href="${ContextPath}/assets/v1/css/ace.css" />
		<link rel="stylesheet" href="${ContextPath}/assets/v1/css/ace-skins.css" />
		<link href="${ContextPath}/assets/v1/css/style.css" rel="stylesheet" />
		<!--[if IE 7]>
		  <link rel="stylesheet" href="${ContextPath}/assets/v1/css/font-awesome-ie7.css" />
		<![endif]-->
		<!--[if lt IE 9]>
		  <link rel="stylesheet" href="${ContextPath}/assets/v1/css/ace-ie.css" />
		<![endif]-->
	</head>

	<body class="navbar-fixed">
		<div class="navbar navbar-inverse navbar-fixed-top">
			<div class="navbar-inner">
				<div class="container-fluid">
					<a href="#" class="brand">
						<small>后台管理</small>
					</a><!--/.brand-->

					<ul class="nav ace-nav pull-right">
						<li class="light-blue user-profile">
							<a data-toggle="dropdown" href="#" class="user-menu dropdown-toggle">
								<span id="user_info">
								</span>
								<i class="icon-caret-down"></i>
							</a>

							<ul class="pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-closer" id="user_menu">
								<li><a href="${ContextPath}/home.do"><i class="icon-wrench"></i> 旧版本</a></li>
								<li class="divider"></li>
								<li><a href="${ContextPath}/logout.do"><i class="icon-off"></i> 退出</a></li>
							</ul>
						</li>
					</ul><!--/.ace-nav-->
				</div><!--/.container-fluid-->
			</div><!--/.navbar-inner-->
		</div>

		<div class="container-fluid" id="main-container">
			<a id="menu-toggler" href="#">
				<span></span>
			</a>

			<div id="sidebar" class="fixed">
				<ul class="nav nav-list">
					<li>
						<a href="${ContextPath}/maldives/list.do">
							<i class="icon-list"></i>
							<span>海岛</span>
						</a>
					</li>
					<li>
						<a href="${ContextPath}/maldives/flight.do">
							<i class="icon-list"></i>
							<span>航班</span>
						</a>
					</li>
				</ul><!--/.nav-list-->

				<div id="sidebar-collapse">
					<i class="icon-double-angle-left"></i>
				</div>
			</div>

			<div id="main-content" class="clearfix">
			</div><!--/#main-content-->
		</div><!--/.fluid-container#main-container-->

		<script>
		var ContextPath = '${ContextPath}';
		</script>
		<script src="${ContextPath}/assets/v1/js/jquery.js"></script>
		<script src="${ContextPath}/assets/v1/js/bootstrap.js"></script>
		<script src="${ContextPath}/assets/v1/js/bootstrap-editable.js"></script>
		<script src="${ContextPath}/assets/v1/js/jquery-ui-1.10.3.custom.js"></script>
		<script src="${ContextPath}/assets/v1/js/jquery.ui.touch-punch.js"></script>
		<script src="${ContextPath}/assets/v1/js/jquery.slimscroll.js"></script>
		<script src="${ContextPath}/assets/v1/js/jquery.dataTables.js"></script>
		<script src="${ContextPath}/assets/v1/js/jquery.dataTables.bootstrap.js"></script>
		<script src="${ContextPath}/assets/v1/js/bootbox.js"></script>
		<script src="${ContextPath}/assets/v1/js/wysihtml5-0.3.0.js"></script>
		<script src="${ContextPath}/assets/v1/js/bootstrap-wysihtml5-0.0.2.js"></script>
		<script src="${ContextPath}/assets/v1/js/select2.js"></script>
		<script src="${ContextPath}/assets/v1/js/bootstrap-editable.js"></script>
		<script src="${ContextPath}/assets/v1/js/wysihtml5.js"></script>
		<script src="${ContextPath}/assets/v1/js/jquery.form.js"></script>
		<script src="${ContextPath}/assets/v1/js/spinner.js"></script>
		<script src="${ContextPath}/assets/v1/js/ace-elements.js"></script>
		<script src="${ContextPath}/assets/v1/js/ace.js"></script>
		<script src="${ContextPath}/assets/v1/js/cqlybest.js"></script>
		<script src="${ContextPath}/assets/v1/js/home.js"></script>
	</body>
</html>
