		<div class="container">
			<hr style="margin: 0px;">
			<!-- div id="footer-help" class="footer">
				<div class="row-fluid">
					<div class="span2">
						<h4>付款和发票</h4>
						<ul>
							<li><a href="#">门店支付</a></li>
							<li><a href="#">银行汇款</a></li>
						</ul>
					</div>
					<div class="span2">
						<h4>付款和发票</h4>
						<ul>
							<li><a href="#">门店支付</a></li>
							<li><a href="#">银行汇款</a></li>
						</ul>
					</div>
					<div class="span2">
						<h4>付款和发票</h4>
						<ul>
							<li><a href="#">门店支付</a></li>
							<li><a href="#">银行汇款</a></li>
						</ul>
					</div>
					<div class="span2">
						<h4>付款和发票</h4>
						<ul>
							<li><a href="#">门店支付</a></li>
							<li><a href="#">银行汇款</a></li>
						</ul>
					</div>
					<div class="span2">
						<h4>付款和发票</h4>
						<ul>
							<li><a href="#">门店支付</a></li>
							<li><a href="#">银行汇款</a></li>
						</ul>
					</div>
					<div class="span2">
						<h4>付款和发票</h4>
						<ul>
							<li><a href="#">门店支付</a></li>
							<li><a href="#">银行汇款</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="footer text-center trust">
				<a title="可信网站" target="_blank" rel="nofollow" href="＃"><img src="${ContextPath}/assets/v2/img/trust.png"></a>
				<a title="可信网站" target="_blank" rel="nofollow" href="＃"><img src="${ContextPath}/assets/v2/img/trust.png"></a>
				<a title="可信网站" target="_blank" rel="nofollow" href="＃"><img src="${ContextPath}/assets/v2/img/trust.png"></a>
			</div -->
			<div class="footer text-center copyright">
				<#if (Settings.basic.copyright)?has_content>
				<span>${Settings.basic.copyright}</span>
				</#if>
				<#if (Settings.basic.icpLicense)?has_content>
				<span>${Settings.basic.icpLicense}</span>
				</#if>
				<#if (Settings.basic.icp)?has_content>
				<span>${Settings.basic.icp}</span>
				</#if>
			</div>
			<div class="footer text-center link">
				<ul>
					<li><a href="#" target="_blank">关于我们</a></li>
					<li><a href="#" target="_blank">联系我们</a></li>
					<li class="last"><a href="#" target="_blank">商务合作</a></li>
				</ul>
			</div>
			<hr>

			<#if (Links.items)?has_content>
			<div class="footer friendly">
				<ul>
					<li><strong>友情链接：</strong></li>
					<#list Links.items as link>
					<li><a href="${link.link!'#'}" title="${link.title!}" target="_blank">${link.name!}</a></li>
					</#list>
				</ul>
			</div>
			</#if>

			<#if (Settings.basic.statistical)?has_content>
			<div class="hide">${Settings.basic.statistical}</div>
			</#if>
		</div>
		<#if Settings.release?has_content>
		<script src="${ContextPath}/assets/v2/js/application.min.js?build=${Settings.version.buildTime!}"></script>
		<#else>
		<script src="${ContextPath}/assets/v2/js/jquery.js?build=${Settings.version.buildTime!}"></script>
		<script src="${ContextPath}/assets/v2/js/bootstrap.js?build=${Settings.version.buildTime!}"></script>
		<script src="${ContextPath}/assets/v2/js/jquery.cookie.js?build=${Settings.version.buildTime!}"></script>
		<script src="${ContextPath}/assets/v2/js/twitter-bootstrap-hover-dropdown.js?build=${Settings.version.buildTime!}"></script>
		<script src="${ContextPath}/assets/v2/js/jqBootstrapValidation.js?build=${Settings.version.buildTime!}"></script>
		<script src="${ContextPath}/assets/v2/js/jquery.form.js?build=${Settings.version.buildTime!}"></script>
		<script src="${ContextPath}/assets/v2/js/jquery.ad-gallery.js?build=${Settings.version.buildTime!}"></script>
		<script src="${ContextPath}/assets/v2/js/load-image.js?build=${Settings.version.buildTime!}"></script>
		<script src="${ContextPath}/assets/v2/js/bootstrap-image-gallery.js?build=${Settings.version.buildTime!}"></script>
		<script src="${ContextPath}/assets/v2/js/bootstrap.calendar.js?build=${Settings.version.buildTime!}"></script>
		<script src="${ContextPath}/assets/v2/js/bootstrap-editable.js?build=${Settings.version.buildTime!}"></script>
		<script src="${ContextPath}/assets/v2/js/cqlybest.js?build=${Settings.version.buildTime!}"></script>
		</#if>
	</body>

</html>