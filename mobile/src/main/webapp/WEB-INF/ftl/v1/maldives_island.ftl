<!DOCTYPE html>
<html>
<head>
	<#import "/springx.ftl" as springx>
	<#assign ContextPath=springMacroRequestContext.getContextPath() />
	<meta charset="utf-8">
	<title><#if Title?has_content>${Title}<#else>${(Settings.basic.siteName)!}</#if></title>

	<!-- Sets initial viewport load and disables zooming  -->
	<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">

	<!-- Makes your prototype chrome-less once bookmarked to your phone's home screen -->
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">

	<!-- Set Apple icons for when prototype is saved to home screen -->
	<link rel="apple-touch-icon-precomposed" sizes="114x114" href="${ContextPath}/asserts/v1/touch-icons/apple-touch-icon-114x114.png">
	<link rel="apple-touch-icon-precomposed" sizes="72x72" href="${ContextPath}/asserts/v1/touch-icons/apple-touch-icon-72x72.png">
	<link rel="apple-touch-icon-precomposed" sizes="57x57" href="${ContextPath}/asserts/v1/touch-icons/apple-touch-icon-57x57.png">

	<link rel="stylesheet" href="${ContextPath}/asserts/v1/css/metro-bootstrap.css?build=${(Settings.build)!}">
	<link rel="stylesheet" href="${ContextPath}/asserts/v1/css/cqlybest.css?build=${(Settings.build)!}">
</head>
<body class="metrouicss">
	<header class="bar-title bg-color-blue">
		<a class="btn btn-small btn-info pull-left" title="首页" href="${ContextPath}/index.html">
			首页
		</a>
		<span>${island.zhName}</span>
		<a class="btn btn-small btn-info pull-right" title="${island.zhName}行程报价" href="${ContextPath}/maldives/${island.id}/price.html">
			行程报价
		</a>
		<div class="clearfix"> </div>
	</header>
	<div class="tabbable tabs-below">
		<div class="tab-content" style="margin: 45px 0;">
			<div id="i-island" class="tab-pane active">
				<!-- 海报 -->
				<#if island.pictures?has_content>
				<div class="row-fluid">
					<#assign img=island.pictures[0]>
					<div class="span12"><img alt="${island.zhName}" src="${ContextPath}/image/${img.id}.${img.extension}?width=480&height=200" width="100%"></div>
				</div>
				</#if>
				<div class="p-l-r-5">
					<!-- 海岛描述 -->
					<div>${island.description!}</div>
					<!-- 其他信息 -->
					<table class="table">
						<tbody>
							<#if island.zhName?has_content>
							<tr>
								<th style="width: 75px;">中文名称：</th>
								<td>${island.zhName}</td>
							</tr>
							</#if>
							<#if island.enName?has_content>
							<tr>
								<th>英文名称：</th>
								<td>${island.enName}</td>
							</tr>
							</#if>
							<#if island.level?has_content>
							<tr>
								<th>岛屿级别：</th>
								<td>${island.level}</td>
							</tr>
							</#if>
							<#if island.way?has_content>
							<tr>
								<th>上岛方式：</th>
								<td>${island.way}</td>
							</tr>
							</#if>
							<#if island.area?has_content>
							<tr>
								<th>岛屿大小：</th>
								<td>${island.area}</td>
							</tr>
							</#if>
							<#if island.snorkeling?has_content>
							<tr>
								<th>浮潜等级：</th>
								<td>${island.snorkeling}</td>
							</tr>
							</#if>
							<#if island.price?has_content>
							<tr>
								<th>参考价格：</th>
								<td>${island.price}</td>
							</tr>
							</#if>
						</tbody>
					</table>
				</div>
			</div>
			<div id="i-hotel" class="tab-pane">
				<div class="p-l-r-5">
					<!-- 酒店介绍 -->
					<div>${island.hotelDescription!}</div>
					<!-- 酒店基本信息 -->
					<table class="table">
						<tbody>
							<#if island.hotelName?has_content>
							<tr>
								<th style="width: 75px;">酒店集团：</th>
								<td>${island.hotelName}</td>
							</tr>
							</#if>
							<#if island.hotelLevel?has_content>
							<tr>
								<th>酒店星级：</th>
								<td>${island.hotelLevel}</td>
							</tr>
							</#if>
							<#if island.hotelStart?has_content>
							<tr>
								<th>开始营业：</th>
								<td>${island.hotelStart}</td>
							</tr>
							</#if>
							<#if island.hotelRoomNum?has_content>
							<tr>
								<th>房间总数：</th>
								<td>${island.hotelRoomNum}</td>
							</tr>
							</#if>
							<#if island.hotelSite?has_content>
							<tr>
								<th>官方网址：</th>
								<td>${island.hotelSite}</td>
							</tr>
							</#if>
							<#if island.hotelTel?has_content>
							<tr>
								<th>电话：</th>
								<td>${island.hotelTel}</td>
							</tr>
							</#if>
							<#if island.hotelFax?has_content>
							<tr>
								<th>传真：</th>
								<td>${island.hotelFax}</td>
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
								<td>${island.hotelAirport}</td>
							</tr>
							</#if>
						</tbody>
					</table>
				</div>
			</div>
			<div id="i-room" class="tab-pane">
			<#if island.rooms?has_content>
			<div class="p-l-r-5">
				<#list island.rooms as room>
				<h4 class="no-margin no-padding">${room.zhName!}${room.enName!} <#if room.num?has_content> ${room.num}间</#if></h4>
				<hr class="no-margin m-b-5">
				<#if room.pictures?has_content>
				<div class="row-fluid m-b-5">
					<#assign imgs=room.pictures?chunk(4)[0]>
					<#list imgs as img>
					<div class="span${12/imgs?size}"><img alt="${room.zhName}" src="${ContextPath}/image/${img.id}.${img.extension}?width=${480/imgs?size}&height=${(400/imgs?size)?string('0')}" width="100%"></div>
					</#list>
				</div>
				<div class="alert alert-success">图片已优化，适合移动设备访问，省流量，请放心浏览。欢迎使用电脑访问我们网站(${Settings.basic.siteUrl!})浏览精美图片信息。<br>如须出行，欢迎致电<a href="tel:${Settings.basic.hotline!}">${Settings.basic.hotline!}</a>咨询。</div>
				</#if>
				<!-- 房间介绍 -->
				<div>${room.description!}</div>
				<table class="table">
					<tbody>
						<#if room.requirements?has_content>
						<tr>
							<th style="width: 75px;">入住要求：</th>
							<td>${room.requirements}</td>
						</tr>
						</#if>
						<#if room.roomFacility?has_content>
						<tr>
							<th>房间设施：</th>
							<td>${room.roomFacility}</td>
						</tr>
						</#if>
					</tbody>
				</table>
				</#list>
			</div>
			</#if>
			</div>
			<div id="i-dinner" class="tab-pane">
			<#if island.dinings?has_content>
			<div class="p-l-r-5">
				<#list island.dinings as dining>
				<h4 class="no-margin no-padding">${dining.zhName!}${dining.enName!}</h4>
				<hr class="no-margin m-b-5">
				<#if dining.pictures?has_content>
				<div class="row-fluid m-b-5">
					<#assign imgs=dining.pictures?chunk(4)[0]>
					<#list imgs as img>
					<div class="span${12/imgs?size}"><img alt="${dining.zhName}" src="${ContextPath}/image/${img.id}.${img.extension}?width=${480/imgs?size}&height=${(400/imgs?size)?string('0')}" width="100%"></div>
					</#list>
				</div>
				<div class="alert alert-success">图片已优化，适合移动设备访问，省流量，请放心浏览。欢迎使用电脑访问我们网站(${Settings.basic.siteUrl!})浏览精美图片信息。<br>如须出行，欢迎致电<a href="tel:${Settings.basic.hotline!}">${Settings.basic.hotline!}</a>咨询。</div>
				</#if>
				<!-- 餐厅介绍 -->
				<div>${dining.description!}</div>
				<table class="table">
					<tbody>
						<#if dining.style?has_content>
						<tr>
							<th style="width: 75px;">环境风格：</th>
							<td>${dining.style}</td>
						</tr>
						</#if>
						<#if dining.food?has_content>
						<tr>
							<th>美食亮点：</th>
							<td>${dining.food}</td>
						</tr>
						</#if>
						<#if dining.openTime?has_content>
						<tr>
							<th>开放时间：</th>
							<td>${dining.openTime}</td>
						</tr>
						</#if>
						<#if dining.location?has_content>
						<tr>
							<th>就餐地点：</th>
							<td>${dining.location}</td>
						</tr>
						</#if>
						<#if dining.reservation?has_content>
						<tr>
							<th>是否预约：</th>
							<td><#if dining.reservation>需要预约<#else>无需预约</#if></td>
						</tr>
						</#if>
						<#if dining.description?has_content>
						<tr>
							<th>详细信息：</th>
							<td>${dining.description}</td>
						</tr>
						</#if>
					</tbody>
				</table>
				</#list>
			</div>
			</#if>
			</div>
			<div id="i-play" class="tab-pane">
				<div class="metro">
					<#assign colors=['#87C116','#00AFF1','#0BA6FF','#FC8E00','#04B1E0','#FFC400','#1D86C9']>
					<#assign ExistOptionIndex=0>
					<#macro playOption index name>
					<#if island.plays?has_content && island.plays?substring(index,index+1)=='1'>
					<a class="tile" style="background-color:${colors[springx.rand(0,colors?size-1)]}"><div class="text-wrapper"><div class="text">${name}</div></div></a>
					<#assign ExistOptionIndex=ExistOptionIndex+1>
					</#if>
					</#macro>
					<@playOption 0 '无线网络' />
					<@playOption 1 '迷你酒吧' />
					<@playOption 2 '空调房' />
					<@playOption 3 '冷热水' />
					<@playOption 4 '国际直拨电话' />
					<@playOption 5 '卫星电视' />
					<@playOption 6 '客房服务' />
					<@playOption 7 '洗衣房' />
					<@playOption 8 '干洗' />
					<@playOption 9 '货币兑换' />
					<@playOption 10 '多语种工作人员' />
					<@playOption 11 '家庭医生' />
					<@playOption 12 '婴儿照看' />
					<@playOption 13 '精品店' />
					<@playOption 14 '图书馆' />
					<@playOption 15 '美容理疗会所' />
					<@playOption 16 '健身房' />
					<@playOption 17 '游泳场' />
					<@playOption 18 '极可意（按摩浴缸）' />
					<@playOption 19 '潜水' />
					<@playOption 20 '浮潜' />
					<@playOption 21 '风浪板运动' />
					<@playOption 22 '冲浪运动' />
					<@playOption 23 '香蕉船' />
					<@playOption 24 '脚踏船' />
					<@playOption 25 '牵引伞滑翔' />
					<@playOption 26 '皮划艇运动' />
					<@playOption 27 '滑水板运动' />
					<@playOption 28 '跪板运动' />
					<@playOption 29 '喷气式水橇' />
					<@playOption 30 '双体船' />
					<@playOption 31 '皮划艇' />
					<@playOption 32 '风筝冲浪' />
					<@playOption 33 '帆船运动' />
					<@playOption 34 '远足' />
					<@playOption 35 '钓鱼' />
					<@playOption 36 '黄昏海钓' />
					<@playOption 37 '大鱼拖钓' />
					<@playOption 38 '深海捕鱼' />
					<@playOption 39 '瑜珈' />
					<@playOption 40 '网球' />
					<@playOption 41 '排球' />
					<@playOption 42 '乒乓球' />
					<@playOption 43 '羽毛球' />
					<@playOption 44 '高尔夫' />
					<@playOption 45 '壁球' />
					<@playOption 46 '台球' />
					<@playOption 47 '斯诺克' />
					<@playOption 48 '室内游戏' />
					<@playOption 49 '卡拉OK' />
					<@playOption 50 '迪斯科' />
					<@playOption 51 '文化习俗展示会' />
					<@playOption 52 '现场乐队' />
				</div>
			</div>
		</div>
		<ul class="nav nav-tabs bar-nav-bottom">
			<li class="active"><a data-toggle="tab" href="#i-island">岛屿</a></li>
			<li><a data-toggle="tab" href="#i-hotel">酒店</a></li>
			<li><a data-toggle="tab" href="#i-room">房型</a></li>
			<li><a data-toggle="tab" href="#i-dinner">餐饮</a></li>
			<li><a data-toggle="tab" href="#i-play">娱乐/设施</a></li>
		</ul>
	</div>
	<script src="${ContextPath}/asserts/v1/js/jquery.js?build=${(Settings.build)!}"></script>
	<script src="${ContextPath}/asserts/v1/js/bootstrap.js?build=${(Settings.build)!}"></script>
</body>
</html>