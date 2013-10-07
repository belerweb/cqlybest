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
				 var submenu = $(this).next().get(0);
				 if ($(submenu).is(':visible')) {
					 $(submenu).slideUp(200).parent().removeClass("open")
				 } else {
					 $(submenu).slideToggle(200).parent().toggleClass('open');
				 }
			 } else {
				 $('#sidebar .active').removeClass('active');
				 $(this).parent().addClass('active');
				 $(this).closest('.submenu').parent().addClass('active');
				 cqlybest.go('#main-content', $(this).attr('href'));
				 
			 }
		});
	})();
});