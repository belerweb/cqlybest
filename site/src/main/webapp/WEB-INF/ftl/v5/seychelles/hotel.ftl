<#include "../index/header.ftl">
<#include "../index/menu.ftl">
<div class="breadcrumbs margin-bottom-20">
	<#if hotel.ad?has_content>
	<div class="container-fluid">
		<h1 class="color-green pull-left">${hotel.ad!}</h1>
		<ul class="pull-right breadcrumb">
			<li><a href="${ContextPath}/seychelles.html">塞舌尔</a> <span class="divider">/</span></li>
			<li class="active">${hotel.zhName!}</li>
		</ul>
	</div>
	</#if>
</div>
<div class="container-fluid" data-toggle="modal-gallery" data-target="#modal-gallery" data-selector=".gallery-item">
	<div class="row-fluid">
		<div class="span9 margin-bottom-30">
			<div class="blog-page blog-item">
				<div class="blog">
					<h3>${hotel.zhName!} ${hotel.enName!}</h3>
					<div>${hotel.description!}</div>
					<br>
					<div>${hotel.hotelDescription!}</div>
				</div>
			</div>
			<#if hotel.pictures?has_content>
			<div id="poster-carousel" class="carousel slide">
				<ol class="carousel-indicators">
					<#list hotel.pictures as image>
					<li data-target="#poster-carousel" data-slide-to="${image_index}" class="<#if image_index==0>active</#if>"></li>
					</#list>
				</ol>
				<div class="carousel-inner">
					<#list hotel.pictures as image>
					<div class="<#if image_index==0>active</#if> item">
						<img src="http://${ImageServer}/${image.qiniuKey}">
					</div>
					</#list>
				</div>
				<a class="carousel-control left" href="#poster-carousel" data-slide="prev">&lsaquo;</a>
				<a class="carousel-control right" href="#poster-carousel" data-slide="next">&rsaquo;</a>
			</div>
			</#if>
			<#list hotel.rooms as item>
			<blockquote class="hero">
				<p>${item.zhName!}</p>
			</blockquote>
			<div>${item.description!}</div>
			<div class="row-fluid gallery">
				<ul class="thumbnails">
					<#list item.pictures as image>
					<#if image_index gt 0 && image_index%4==0>
				</ul>
				<ul class="thumbnails">
					</#if>
					<li class="span3">
						<a href="http://${ImageServer}/${image.qiniuKey}-gallery" class="gallery-item thumbnail fancybox-button zoomer">
							<div class="overlay-zoom">
								<img src="http://${ImageServer}/${image.qiniuKey}?imageView/1/w/270/h/170">
								<div class="zoom-icon"></div>
							</div>
						</a>
					</li>
					</#list>
				</ul>
			</div>
			</#list>
			<#list hotel.dinings as item>
			<blockquote class="hero">
				<p>${item.zhName!}</p>
			</blockquote>
			<div>${item.description!}</div>
			<div class="row-fluid gallery">
				<ul class="thumbnails">
					<#list item.pictures as image>
					<#if image_index gt 0 && image_index%4==0>
				</ul>
				<ul class="thumbnails">
					</#if>
					<li class="span3">
						<a href="http://${ImageServer}/${image.qiniuKey}-gallery" class="gallery-item thumbnail fancybox-button zoomer">
							<div class="overlay-zoom">
								<img src="http://${ImageServer}/${image.qiniuKey}?imageView/1/w/270/h/170">
								<div class="zoom-icon"></div>
							</div>
						</a>
					</li>
					</#list>
				</ul>
			</div>
			</#list>
		</div>
		<div class="span3">
		</div>
	</div>
</div>
<div id="modal-gallery" class="modal-fullscreen modal modal-gallery hide fade" tabindex="-1">
	<div class="modal-header">
		<a class="close" data-dismiss="modal">&times;</a>
		<h3 class="modal-title"></h3>
	</div>
	<div class="modal-body"><div class="modal-image"></div></div>
	<div class="modal-footer">
		<a class="btn btn-success modal-play modal-slideshow" data-slideshow="3000"><i class="icon-play icon-white"></i> 自动播放</a>
		<a class="btn btn-info modal-prev"><i class="icon-arrow-left icon-white"></i> 上一张</a>
		<a class="btn btn-primary modal-next">下一张 <i class="icon-arrow-right icon-white"></i></a>
	</div>
</div>

<#include "../index/copyright.ftl">
<script type="text/javascript">
	window.PageContext = {
		fixCopyright: false,
		activeNav: 'seychelles',
		init: function(){
		}
	};
</script>
<#include "../index/footer.ftl">