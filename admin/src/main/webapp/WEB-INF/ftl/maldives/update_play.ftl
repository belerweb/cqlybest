<style>
#island-play-tab ul {margin-left:0;}
#island-play-tab li {float:left; display:block; width:180px;}
</style>
<#macro playOption index name>
<li><label class="checkbox"><input <#if island.plays?has_content && island.plays?substring(index,index+1)=='1'>checked="checked"</#if> type="checkbox"> ${name}</label></li>
</#macro>
<div id="island-play-tab" class="tab-pane">
	<ul class="form-inline">
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
	</ul>
	<div class="clearfix"></div>   
	<div class="text-center"><button type="button" class="btn btn-primary action action-save">保存</button></div>
</div>
<script type="text/javascript">
$('#island-play-tab button.action-save').click(function(){
	var plays = [];
	$('#island-play-tab input').each(function(i, obj){
		plays.push(obj.checked ? 1 : 0);
	});
	$.post('${ContextPath}/maldives/update.do', {
		pk: '${island.id}',
		name: 'plays',
		value: plays.join('')
	}).fail(function() {
		cqlybest.error();
	});
});
</script>