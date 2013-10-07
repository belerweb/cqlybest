<style>
#maldives-island-play－tab .metro .tile {
	background-clip: padding-box;
	color: #FFFFFF;
	cursor: pointer;
	display: block;
	float: left;
	height: 50px;
	margin: 2px;
	overflow: hidden;
	padding: 8px;
	text-decoration: none;
	width: 50px;
	-webkit-background-clip: padding;
	-moz-background-clip: padding;
}

#maldives-island-play－tab .metro .tile.rectangle {
	width: 120px;
}

#maldives-island-play－tab .metro .tile .text-wrapper {
	display: table;
	height: 50px;
	width: 50px;
}

#maldives-island-play－tab .metro .tile.rectangle .text-wrapper {
	width: 120px;
}

#maldives-island-play－tab .metro .tile .text-wrapper .text {
	display: table-cell;
	font-size: 16px;
	line-height: 25px;
	text-align: center;
	vertical-align: middle;
}
</style>
<div id="maldives-island-play－tab" class="maldives-island-section maldives-play-gallery">
	<div class="title">
		<h4>活动与娱乐设施</h4>
		<hr>
	</div>
	<div class="metro">
		<#assign colors=['#87C116','#00AFF1','#0BA6FF','#FC8E00','#04B1E0','#FFC400','#1D86C9']>
		<#assign ExistOptionIndex=0>
		<#macro playOption index name>
		<#if island.plays?has_content && island.plays?substring(index,index+1)=='1'>
		<a class="tile <#if ExistOptionIndex%3==0>rectangle</#if>" style="background-color:${colors[springx.rand(0,colors?size-1)]}"><div class="text-wrapper"><div class="text">${name}</div></div></a>
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