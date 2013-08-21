$(function() {
	var OpenReport = function(title, url) {
		var iframe = '<iframe src="' + url + '" frameborder="0" style="width:100%;height:0;"></iframe>';
		iframe = $(iframe);
		iframe.on('load', function() {
			$(this).height($(window).height() - $(this).offset().top);
		});
		var content = '<div class="clearfix" id="page-content">' + '<div class="page-header position-relative">'
				+ title + '</div>' + '<div class="row-fluid"><div class="span12"></div></div>'
		content = $(content);
		$('.span12', content).append(iframe);
		$('#main-content').empty().append(content);
	};

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
		$(".nav-list").on(
				"click",
				function(event) {
					if (collapse) {
						return;
					}
					var folder = $(event.target).closest(".dropdown-toggle");
					if (folder && folder.length > 0) {
						var files = folder.next().get(0);
						if (!$(files).is(":visible")) {
							$(".open > .submenu").each(function() {
								if (this != files && !$(this.parentNode).hasClass("active")) {
									$(this).slideUp(200).parent().removeClass("open")
								}
							})
						}
						$(files).slideToggle(200).parent().toggleClass("open");
						return false;
					}
					var file = $(event.target);
					if (file.is('a') && !file.is('.dropdown-toggle')) {
						event.preventDefault();
						event.stopPropagation();
						var title = '<h1>' + file.text() + '<small><i class="icon-double-angle-right"></i> '
								+ $('a:first', file.parents('li').get(1)).text() + '</small></h1>';
						OpenReport(title, file.attr('data-url'));
					}
				});
	})();

	var RepositorySort = function(obj1, obj2) {
		var name1 = $(obj1).attr('name');
		var name2 = $(obj2).attr('name');
		return name1 == name2 ? 0 : name1 > name2 ? 1 : -1;
	};

	// Refresh Repository
	var SRS = '/pentaho/SolutionRepositoryService?component=getSolutionRepositoryDoc';
	$.get(SRS, function(xml) {
		if (RepositoryView === 'tree') {
			$('#sidebar .tree').on('selected', function(e, data) {
				var obj = data.info[0];
				var title = [ '<h1>' ];
				$(obj.xml).parents('file').each(function(i, obj) {
					if (i > 0) {
						title.push(' <i class="icon-double-angle-right"></i> ');
					}
					title.push($(obj).attr('localized-name'));
				});
				title.push('<small><i class="icon-double-angle-right"></i>  ' + obj.name + '</small></h1>');
				OpenReport(title.join(''), $(obj.xml).attr('url'));
			}).Tree({
				selectable : false,
				'open-icon' : 'icon-folder-open',
				'close-icon' : 'icon-folder-close',
				selectable : false,
				'selected-icon' : null,
				'unselected-icon' : null,
				dataSource : {
					data : function(options, callback) {
						var data = [];
						$.each($(options.xml || $('repository', xml)).children('[visible=true]'), function(i, obj) {
							data.push({
								name : $(obj).attr('localized-name'),
								type : $(obj).attr('isDirectory') == 'true' ? 'folder' : 'item',
								xml : obj
							});
						});
						callback({
							data : data
						});
					}
				}
			});
		}
		if (RepositoryView === '2') {
			var folders = $('repository > file[isDirectory=true][visible=true]', xml).sort(RepositorySort);
			$.each(folders, function(i, obj) {
				var files = $('file[isDirectory=false][visible=true]', obj).sort(RepositorySort);
				if (files.length) {
					var folder = '<li><a class="dropdown-toggle" href="#">' + '<i class="icon-th"></i><span>'
							+ $(obj).attr('localized-name') + '</span><b class="arrow icon-angle-down"></b></a>'
							+ '<ul class="submenu"></ul></li>';
					folder = $(folder);
					$.each(files, function(i, obj) {
						var file = '<a href="#" data-url="' + $(obj).attr('url') + '">'
								+ '<i class="icon-double-angle-right"></i> ' + $(obj).attr('localized-name') + '</a>';
						$('ul', folder).append('<li>' + file + '</li>');
					});
					$('#sidebar .nav-list').append(folder);
				}
			});
		}
	}, 'xml');
});