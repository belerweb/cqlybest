$(function() {
	var page = window.PageContext || {};
	if (!!page.init && $.isFunction(page.init)) {
		page.init();
	}
});