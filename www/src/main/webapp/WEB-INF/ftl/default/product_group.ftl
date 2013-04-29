<#include "/default/header.ftl">
<div class="container margin-menu">
	<div class="row">
		<div class="span9">
			<h4>${menu.name!}</h4>
		</div>
		<div class="span3">
			<div class="box">
				<div class="title title-important"><strong>热门旅游</strong></div>
				<div class="content">
					<ul>
						<li><span class="badge badge-important">1</span><a href="#">塞舌尔9天6晚至爱之旅</a></li>
						<li><span class="badge badge-important">2</span><a href="#">泰国清迈 阿莫拉酒店</a></li>
						<li><span class="badge badge-warning">3</span><a href="#">丽江 大理双飞五日游（半...</a></li>
						<li><span class="badge badge-warning">4</span><a href="#">纯玩丽江自由行双飞五日游</a></li>
					</ul>
				</div>
			</div>
			<div class="box">
				<img src="${ContextPath}/default/img/c_09.jpg">
			</div>
			<div class="thumbnail">
				<img src="http://www.cqlybest.com/upload_files/73/1_20120429110438_clkrr.jpg">
				<div class="caption">波杜希蒂岛</div>
			</div>
		</div>
	</div>
</div>
<script>
	var PageContext = {
		menu : '${menu.id!}'
	};
</script>
<#include "/default/footer.ftl">