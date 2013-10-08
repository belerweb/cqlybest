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
	
	// check session
	$.get(ContextPath + '/session').done(function(user){
		if (user) {
			$('[data-display=session]').show();
			$('[data-text=top-nickname]').html('欢迎您，' + user.nickname + '!');
		} else {
			$('[data-display=nosession]').show();
		}
	}).fail(function(){
	});
});