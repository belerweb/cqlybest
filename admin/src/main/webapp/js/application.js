$(function() {
	$(window).hashchange(function() {
		var hashData = location.hash.match(/^#(.+)$/);
		if (hashData) {
			var param = {};
			var pa = hashData[1].split(';');
			for ( var i = 0; i < pa.length; i++) {
				var kv = pa[i].split('=');
				param[kv[0]] = kv[1];
			}
			if (param.mg) {
				$('#menu .main-menu > li').removeClass('active');
				$('#menu .main-menu > li[data-mg=' + param.mg + ']').addClass('active');
				$('#menu .additional-menu > li').hide().removeClass('active');
				$('#menu .additional-menu > li[data-mg=' + param.mg + ']').show();
				if (param.mu) {
					$('#menu .additional-menu > li[data-mu="' + param.mu + '"]').addClass('active');
					$('#main').load(param.mu);
				}
			}
		}
	});

	(function() {
		if (location.hash == '') {
			location.hash = $('#menu .additional-menu > li:first a').attr('href');
		} else {
			$(window).hashchange();
		}
	})();
});