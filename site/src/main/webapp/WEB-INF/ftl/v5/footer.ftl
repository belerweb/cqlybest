		</div>
		<div id="modal-gallery" class="modal-fullscreen modal modal-gallery hide fade" tabindex="-1">
			<div class="modal-header">
				<a class="close" data-dismiss="modal">&times;</a>
				<h3 class="modal-title"></h3>
			</div>
			<div class="modal-body"><div class="modal-image"></div></div>
			<div class="modal-footer">
				<a class="btn btn-success modal-play modal-slideshow" data-slideshow="3000"><i class="icon-play icon-white"></i> 自动播放</a>
				<a class="btn btn-info modal-prev"><i class="icon-arrow-left icon-white"></i> 上一张</a>
				<a class="btn btn-primary modal-next">下一张 <i class="icon-arrow-right icon-white"></i></a>
			</div>
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