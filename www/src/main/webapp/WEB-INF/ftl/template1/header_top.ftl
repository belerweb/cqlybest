<#include "/template1/header_html.ftl">
<div id="top-nav" class="navbar navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<div class="pull-left">
				<a href="http://e.weibo.com/3039642623" target="_blank"><img alt="微博加关注" src="${ContextPath}/template1/img/weibo.png"> 加关注</a>
			</div>
			<div class="pull-right">
				<#if User?exists>
				<span>
					<#if User.nickname?has_content>
					${User.nickname!''}
					<#else>
						<#if User.loginUsername?has_content>
						${User.loginUsername!''}
						<#else>
							<#if User.fullname?has_content>
							${User.fullname!''}
							<#else>
								<#if User.cellPhone?has_content>
								${User.cellPhone!''}
								<#else>
									<#if User.email?has_content>
									${User.email!''}
									<#else>
									<a href="#">完善个人信息</a>
									</#if>
								</#if>
							</#if>
						</#if>
					</#if>
				</span>
				<a href="${ContextPath}/logout.do">退出</a>
				<#else>
				<span>亲，欢迎来易游天下！请</span>
				<a href="${ContextPath}/login.html">登陆</a>
				<a href="${ContextPath}/register.html" class="mg">免费注册</a>
				<a href="${ContextPath}/connector/qq_login.do" class="mg"><img alt="QQ登录" src="http://qzonestyle.gtimg.cn/qzone/vas/opensns/res/img/Connect_logo_7.png"></a>
				<a href="${ContextPath}/connector/weibo_login.do" class="mg"><img alt="用微博登录" src="http://timg.sjs.sinajs.cn/t4/appstyle/widget/images/loginButton/loginButton_24.png"></a>
				</#if>
			</div>
		</div>
	</div>
</div>