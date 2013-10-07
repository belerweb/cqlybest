$(function() {
	$.fn.editable.defaults.emptytext = '未设置';
	$.fn.editable.defaults.send  = 'always';

	var page = window.PageContext || {};

	// active menu
	if (!!page.menu) {
		$('#menu .nav> li[data-active=' + page.menu + ']').addClass('active');
	}

	// load scripts && execute init
	if (!!page.scripts && $.isArray(page.scripts)) {
		var i = 0;
		var getScript = function() {
			$.getScript(page.scripts[i]).done(function(script, textStatus) {
				if (i < page.scripts.length) {
					i++;
					getScript();
				} else {
					if (!!page.init && $.isFunction(page.init)) {
						page.init();
					}
				}
			}).fail(function(jqxhr, settings, exception) {
				// TODO alert error
			});
		};
	} else {
		if (!!page.init && $.isFunction(page.init)) {
			page.init();
		}
	}
});