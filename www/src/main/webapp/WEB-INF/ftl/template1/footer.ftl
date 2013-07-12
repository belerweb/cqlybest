		<div class="container">
			<hr style="margin: 0px;">
			<div id="footer-help" class="footer">
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
				<a title="可信网站" target="_blank" rel="nofollow" href="＃"><img src="${ContextPath}/template1/img/trust.png"></a>
				<a title="可信网站" target="_blank" rel="nofollow" href="＃"><img src="${ContextPath}/template1/img/trust.png"></a>
				<a title="可信网站" target="_blank" rel="nofollow" href="＃"><img src="${ContextPath}/template1/img/trust.png"></a>
			</div>
			<div class="footer text-center copyright">
				<#if (Options.site_copyright)?has_content>
				<span>${Options.site_copyright}</span>
				</#if>
				<#if (Options.site_icp_license)?has_content>
				<span>${Options.site_icp_license}</span>
				</#if>
				<#if (Options.site_icp)?has_content>
				<span>${Options.site_icp}</span>
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
			<div class="footer friendly">
				<ul>
					<li><strong>友情链接：</strong></li>
					<li><a href="#" target="_blank">中国地图</a></li>
					<li><a href="#" target="_blank">同城网</a></li>
					<li><a href="#" target="_blank">携程网</a></li>
					<li><a href="#" target="_blank">12306</a></li>
				</ul>
			</div>
			<#if (Options.site_statistical_code)?has_content>
			<div class="hide">${Options.site_statistical_code}</div>
			</#if>
		</div>
		<#if (Options.release)?exists>
		<script src="${ContextPath}/template1/js/application.min.js?build=${(Options.build)!}"></script>
		<#else>
		<script src="${ContextPath}/template1/js/jquery.js?build=${(Options.build)!}"></script>
		<script src="${ContextPath}/template1/js/bootstrap.js?build=${(Options.build)!}"></script>
		<script src="${ContextPath}/template1/js/jquery.cookie.js?build=${(Options.build)!}"></script>
		<script src="${ContextPath}/template1/js/twitter-bootstrap-hover-dropdown.js?build=${(Options.build)!}"></script>
		<script src="${ContextPath}/template1/js/jqBootstrapValidation.js?build=${(Options.build)!}"></script>
		<script src="${ContextPath}/template1/js/jquery.form.js?build=${(Options.build)!}"></script>
		<script src="${ContextPath}/template1/js/jquery.ad-gallery.js?build=${(Options.build)!}"></script>
		<script src="${ContextPath}/template1/js/load-image.js?build=${(Options.build)!}"></script>
		<script src="${ContextPath}/template1/js/bootstrap-image-gallery.js?build=${(Options.build)!}"></script>
		<script src="${ContextPath}/template1/js/bootstrap.calendar.js?build=${(Options.build)!}"></script>
		<script src="${ContextPath}/template1/js/bootstrap-editable.js?build=${(Options.build)!}"></script>
		<script src="${ContextPath}/template1/js/cqlybest.js?build=${(Options.build)!}"></script>
		</#if>
	</body>

</html>