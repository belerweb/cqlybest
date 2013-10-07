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
		$(".nav-list").on("click", function(event) {
			if (collapse) {
				return;
			}
			var target = $(event.target);
			if (target.is('a')) {
				if (target.is('.dropdown-toggle')) {
					var ul = target.next().get(0);
					if (!$(ul).is(":visible")) {
						$(".open > .submenu").each(function() {
							if (this != ul && !$(this.parentNode).hasClass("active")) {
								$(this).slideUp(200).parent().removeClass("open")
							}
						})
					}
					$(ul).slideToggle(200).parent().toggleClass("open");
					return false;
				} else {
					$('#main-content').load(target.attr('data-url'));
				}
			}
		});
	})();

});