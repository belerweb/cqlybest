<div id="elfinder"></div>
<script type="text/javascript" charset="utf-8">
$('#elfinder').elfinder({
	url : 'http://elfinder.hahale.tv/connector.elfinder',
	lang: 'zh_CN',
	handlers : {
		select : function(event, elfinderInstance) {
			$('#elfinder').data('files', event.data.selected);
		}
	}
});
</script>
