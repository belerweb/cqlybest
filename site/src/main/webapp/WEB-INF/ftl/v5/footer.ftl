		</div>
	
		<#if dev>
		<script type="text/javascript" src="${ContextPath}/assets/v5/js/jquery.js?v=${version.time}"></script>
		<script type="text/javascript" src="${ContextPath}/assets/v5/js/bootstrap.js?v=${version.time}"></script>
		<script type="text/javascript" src="${ContextPath}/assets/v5/js/waypoints.js?v=${version.time}"></script>
		<script type="text/javascript" src="${ContextPath}/assets/v5/js/jquery.easing.js?v=${version.time}"></script>
		<script type="text/javascript" src="${ContextPath}/assets/v5/js/supersized.js?v=${version.time}"></script>
		<script type="text/javascript" src="${ContextPath}/assets/v5/js/supersized.shutter.js?v=${version.time}"></script>
		<script type="text/javascript" src="${ContextPath}/assets/v5/js/load-image.js?v=${version.time}"></script>
		<script type="text/javascript" src="${ContextPath}/assets/v5/js/bootstrap-image-gallery.js?v=${version.time}"></script>
		<script type="text/javascript" src="${ContextPath}/assets/v5/js/application.js?v=${version.time}"></script>
		<#else>
		<script type="text/javascript" src="${ContextPath}/assets/v5/js/script1.min.js?v=${version.time}"></script>
		</#if>
	</body>
</html>