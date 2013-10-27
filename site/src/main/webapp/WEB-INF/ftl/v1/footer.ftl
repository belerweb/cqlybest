		</div><!--/.fluid-container#main-container-->
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
		<script src="${ContextPath}/assets/v1/js/fullcalendar.js"></script>
		<script src="${ContextPath}/assets/v1/js/jquery.maskedinput.js"></script>
		<script src="${ContextPath}/assets/v1/js/jquery.maskMoney.js"></script>
		<script src="${ContextPath}/assets/v1/js/date.js"></script>
		<script src="${ContextPath}/assets/v1/js/fuelux.wizard.js"></script>
		<script src="${ContextPath}/assets/v1/js/cometd.js"></script>
		<script src="${ContextPath}/assets/v1/js/intro.js"></script>
		<script src="${ContextPath}/assets/v1/js/bootstrap-tour.js"></script>
		<script src="${ContextPath}/assets/v1/js/jquery.hotkeys.js"></script>
		<script src="${ContextPath}/assets/v1/js/bootstrap-wysiwyg.js"></script>
		<script src="${ContextPath}/assets/v1/js/ace-elements.js"></script>
		<script src="${ContextPath}/assets/v1/js/ace.js"></script>
		<script src="${ContextPath}/assets/v1/js/cqlybest.js"></script>
		<script src="${ContextPath}/assets/v1/js/home.js"></script>
		<script type="text/javascript">
		$(function(){
			cqlybest.go('#main-content', $('#main-content').data('init'));
			document.title = '${Title!''}';
			var page = window.PageContext||{};
			if (page.init && $.isFunction(page.init)) {
				page.init();
			}
		});
		</script>
	</body>
</html>