<#assign ContextPath=springMacroRequestContext.getContextPath() />
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
		<title>登录</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />

		<link href="${ContextPath}/assets/v1/css/bootstrap.css" rel="stylesheet" />
		<link rel="stylesheet" href="${ContextPath}/assets/v1/css/font-awesome.css" />
		<link rel="stylesheet" href="${ContextPath}/assets/v1/css/OpenSans.css" />
		<link rel="stylesheet" href="${ContextPath}/assets/v1/css/ace.css" />
		<!--[if IE 7]>
		  <link rel="stylesheet" href="${ContextPath}/assets/v1/css/font-awesome-ie7.css" />
		<![endif]-->
		<!--[if lt IE 9]>
		  <link rel="stylesheet" href="${ContextPath}/assets/v1/css/ace-ie.css" />
		<![endif]-->
	</head>
	<body class="login-layout">
		<div class="container-fluid" id="main-container">
			<div id="main-content">
				<div class="row-fluid">
					<div class="span12">
						<div class="login-container">
							<div class="row-fluid">
								<div class="space-24"></div>
								<div class="center">
									<h1>
										<span class="white">后台管理</span>
									</h1>
								</div>
							</div>
							<div class="space-6"></div>
							<div class="row-fluid">
								<div class="position-relative">
									<div id="login-box" class="visible widget-box no-border">
										<div class="widget-body">
											<div class="widget-main">
												<h4 class="header blue lighter bigger">
													<i class="icon-coffee green"></i> 输入用户名和密码登录系统
												</h4>
												<div class="space-6"></div>
												<form action="${ContextPath}/login.do" method="post">
													<fieldset>
														<label>
															<span class="block input-icon input-icon-right">
																<input name="j_username" type="text" class="span12" placeholder="用户名" />
																<i class="icon-user"></i>
															</span>
														</label>
														<label>
															<span class="block input-icon input-icon-right">
																<input name="j_password" type="password" class="span12" placeholder="密码" />
																<i class="icon-lock"></i>
															</span>
														</label>
														<div class="space"></div>
														<div class="row-fluid">
															<label class="span8">
																<!-- input type="checkbox" />
																<span class="lbl"> 记住我</span -->
															</label>
															<button type="submit" class="span4 btn btn-small btn-primary">
																<i class="icon-key"></i> 登录
															</button>
														</div>
													</fieldset>
												</form>
											</div><!--/widget-main-->
											<div class="toolbar clearfix">
												<div>
													<a href="${ContextPath}/connector/weibo_login">
														<img src="${ContextPath}/assets/v1/img/connector/loginButton_24.png"
															width="102" height="24" alt="用微博帐号登录">
													</a>
												</div>
												<div>
													<a href="${ContextPath}/connector/qq_login">
														<img src="${ContextPath}/assets/v1/img/connector/Connect_logo_7.png"
															width="63" height="24" alt="用帐号登录">
													</a>
												</div>
											</div>
										</div><!--/widget-body-->
									</div><!--/login-box-->
								</div><!--/position-relative-->
							</div>
						</div>
					</div><!--/span-->
				</div><!--/row-->
			</div>
		</div><!--/.fluid-container-->
	</body>
</html>
