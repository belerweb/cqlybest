$(function() {
	var page = window.PageContext || {};
	if (page.fixCopyright === false) {
		$('body').removeClass('fixed-bottom');
		$('.copyright').removeClass('fixed');
	}
	if (!!page.activeNav) {
		$('.header .nav > li[data-active-nav='+page.activeNav+']').addClass('active');
	}
	if (!!page.init && $.isFunction(page.init)) {
		page.init();
	}
});