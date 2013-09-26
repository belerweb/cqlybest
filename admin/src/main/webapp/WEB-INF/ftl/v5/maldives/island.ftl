<#assign Title='${island.zhName!}${island.enName!} 马代 马尔代夫 海岛 自由行' />
<#assign Description=island.description!?html />
<#assign Keywords='${island.zhName!} ${island.enName!} 马代 马尔代夫 海岛 自由行' />
<#include "../header.ftl">
	<style type="text/css">
		<#include "island.css">
	</style>
	<div id="sidebar" class="fixed">
		<a class="btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
		</a>
		<#if (Settings.basic.logo)?has_content>
		<div id="logo">
			<a href="/" target="_blank">
				<#assign image=Settings.basic.logo>
				<img src="http://${ImageServer}/${image.qiniuKey}" width="150">
			</a>
		</div>
		</#if>
		<#if (Settings.basic.hotline)?has_content>
		<div id="hotline">
			<p class="phone">${Settings.basic.hotline}</p>
			<p class="tip"><i>7x24 小时，用心为您服务</i></p>
		</div>
		</#if>
		<nav id="nav" class="navigation" role="navigation">
			<ul class="unstyled">
				<li class="active" data-section="1"><i class="icon-cloud"></i> <span>岛屿详情</span></li>
				<li data-section="2" class=""><i class="icon-building"></i> <span>酒店详情</span></li>
				<li data-section="3" class=""><i class="icon-home"></i> <span>房型介绍</span></li>
				<li data-section="4" class=""><i class="icon-coffee"></i> <span>餐饮设施</span></li>
				<li data-section="5" class=""><i class="icon-suitcase"></i> <span>活动娱乐</span></li>
				<li data-section="6" class=""><i class="icon-tasks"></i> <span>本岛行程</span></li>
				<li data-section="7" class=""><i class="icon-cloud"></i> <span>其他岛屿</span></li>
			</ul>
		</nav><!-- /nav -->
	</div><!-- /sidebar -->
	<div id="container">

		<section id="section-poster" class="section" data-section="1">
			<a id="prevslide" class="load-item"></a>
			<a id="nextslide" class="load-item"></a>
			<div class="row-fluid" style="background: transparent;">
				<div class="span6 offset1 intro">
					<h2>${island.zhName!}</h2>
					<h3>${island.enName!}</h3>
					<p>${island.description!}</p>
					<ul class="tags">
						<#if island.level?has_content><li class="bg0">${island.level}</li></#if>
						<#if island.way?has_content><li class="bg1">${island.way}</li></#if>
						<#if island.area?has_content><li class="bg2">${island.area}</li></#if>
						<#if island.snorkeling?has_content><li class="bg3">${island.snorkeling}级浮潜</li></#if>
						<#if island.tags?has_content>
						<#list island.tags?split(',') as tag>
						<li class="bg${springx.rand(0,6)}">${tag}</li>
						</#list>
						</#if>
					</ul>
				</div>
			</div><!-- /row-fluid -->
		</section><!-- /section -->
		<section id="section-hotel" class="section" data-section="2">
			<div class="content">
				<div class="title">
					<h3>酒店详情</h3>
					<hr />
				</div>
				<div class="row-fluid">
					<div class="span6">
						<#if island.hotelDescription?has_content>
						<p>${island.hotelDescription!}</p>
						</#if>
						<table class="table table-bordered table-condensed">
							<tbody>
								<tr>
									<th style="width: 90px;">酒店集团：</th>
									<td>${island.hotelName!}</td>
								</tr>
								<#if island.hotelLevel?has_content>
								<tr>
									<th>酒店星级：</th>
									<td>${island.hotelLevel!}</td>
								</tr>
								</#if>
								<#if island.hotelStart?has_content>
								<tr>
									<th>开始营业：</th>
									<td>${island.hotelStart!}</td>
								</tr>
								</#if>
								<#if island.hotelRoomNum?has_content>
								<tr>
									<th>房间总数：</th>
									<td>${island.hotelRoomNum!}</td>
								</tr>
								</#if>
								<#if island.hotelSite?has_content>
								<tr>
									<th>官方网址：</th>
									<td>${island.hotelSite!}</td>
								</tr>
								</#if>
								<#if island.hotelTel?has_content>
								<tr>
									<th>电话：</th>
									<td>${island.hotelTel!}</td>
								</tr>
								</#if>
								<#if island.hotelFax?has_content>
								<tr>
									<th>传真：</th>
									<td>${island.hotelFax!}</td>
								</tr>
								</#if>
								<#if island.hotelChinese?has_content>
								<tr>
									<th>中文服务：</th>
									<td><#if island.hotelChinese>有<#else>无</#if></td>
								</tr>
								</#if>
								<#if island.hotelAirport?has_content>
								<tr>
									<th>机场柜台：</th>
									<td>${island.hotelAirport!}</td>
								</tr>
								</#if>
							</tbody>
						</table>
						<#if (Settings.basic.hotline)?has_content>
						<div class="alert bg5 big-hotline">
							24 小时尊贵热线：<span>${Settings.basic.hotline}</span>
						</div>
						</#if>
					</div>
					<div class="span6" data-toggle="modal-gallery" data-target="#maldives-gallery" data-selector="img">
						<#if island.hotelPictures?has_content>
						<#assign rand = springx.rand(0,island.hotelPictures?size-1) />
						<#list island.hotelPictures as image>
						<img alt="${image.title!}" src="http://${ImageServer}/${image.qiniuKey}?imageView/1/w/343/h/280"
							 data-href="http://${ImageServer}/${image.qiniuKey}"
							 class="<#if image_index!=rand>hide</#if>" style="width:100%;">
						</#list>
						</#if>
					</div>
				</div>
			</div>
		</section><!-- /section -->
		<#list island.rooms as room>
		<section id="section-room" class="section style1" data-section="3">
			<div class="content">
				<div class="title">
					<div class="row-fluid">
						<div class="span3">
							<h3>房型介绍</h3>
						</div>
						<div class="span9">
							<h3 class="text-right">${room.zhName!} ${room.enName!}</h3>
						</div>
					</div>
					<hr />
				</div>
				<div class="row-fluid">
					<div class="span4">
					</div>
					<div class="span8">
							<ul class="thumbnails">
								<#list room.pictures as image>
								<#if image_index%2==0>
							</ul>
							<ul class="thumbnails">
								</#if>
								<li class="span6">
									<div class="thumbnail-style thumbnail-kenburn">
										<div class="overflow-hidden">
											<img src="http://${ImageServer}/${image.qiniuKey}?imageView/1/w/696/h/400">
										</div>
									</div>
								</li>
								</#list>
							</ul>
						<div class="clearfix"> </div>
					</div>
				</div>
			</div>
		</section><!-- /section -->
		</#list>
		<#list island.dinings as dining>
		<section id="section-dining" class="section" data-section="4">
			<div class="content">
				<div class="title">
					<div class="row-fluid">
						<div class="span3">
							<h3>餐饮设施</h3>
						</div>
						<div class="span9">
							<h3 class="text-right">${dining.zhName!} ${dining.enName!}</h3>
						</div>
					</div>
					<hr />
				</div>
				<div class="row-fluid">
					<div class="span4">
					</div>
					<div class="span8">
							<ul class="thumbnails">
								<#list dining.pictures as image>
								<#if image_index%2==0>
							</ul>
							<ul class="thumbnails">
								</#if>
								<li class="span6">
									<div class="thumbnail-style thumbnail-kenburn">
										<div class="overflow-hidden">
											<img src="http://${ImageServer}/${image.qiniuKey}?imageView/1/w/696/h/400">
										</div>
									</div>
								</li>
								</#list>
							</ul>
						<div class="clearfix"> </div>
					</div>
				</div>
			</div>
		</section><!-- /section -->
		</#list>

	</div>
	<script type="text/javascript">
		window.PageContext = {
			init: function(){
				$('body').append('<div id="supersized-loader"></div><ul id="supersized"></ul>');
				$.supersized({
					slide_interval: 3000,
					transition:1,
					transition_speed:700,
					slide_links:'blank',
					vertical_center:0,
					slides:[
						<#list island.pictures as image>
						<#if image_index gt 0>,</#if>{image : 'http://${ImageServer}/${image.qiniuKey}'}
						</#list>
					]
				});
				// $('#section-poster').height($(window).height());
				$('.navigation li').click(function (e) {
					e.preventDefault();
					var datasection = $(this).attr('data-section');
					var top = $('.section[data-section="' + datasection + '"]').offset().top;
					$('html,body').animate({
						scrollTop: datasection==1 ? top : (top+10)
					}, 500, 'easeOutQuart');
				});
				$('section').waypoint(function (direction) {
					var datasection = $(this).attr('data-section');
					if (direction === 'down') {
						$('.navigation li[data-section="' + datasection + '"]').addClass('active').siblings().removeClass('active');
					} else {
						var newsection = parseInt(datasection) - 1;
						$('.navigation li[data-section="' + newsection + '"]').addClass('active').siblings().removeClass('active');
					}
				});
			}
		};
	</script>
<#include "../footer.ftl">