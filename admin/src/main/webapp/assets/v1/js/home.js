$(function() {
	(function() {
		$("#menu-toggler").on("click", function() {
			$("#sidebar").toggleClass("display");
			$(this).toggleClass("display");
			return false;
		});
		var collapse = false;
		$("#sidebar-collapse").on("click", function() {
			$("#sidebar").toggleClass("menu-min");
			$(this.firstChild).toggleClass("icon-double-angle-right");
			collapse = $("#sidebar").hasClass("menu-min");
			if (collapse) {
				$(".open > .submenu").removeClass("open")
			}
		});
		$('#sidebar .nav-list a').click(function(event){
			event.preventDefault();
			event.stopPropagation();
			 if ($(this).is(".dropdown-toggle")) {
				 // TODO
			 } else {
				 $('#main-content').load($(this).attr('href'));
			 }
		});
	})();
});